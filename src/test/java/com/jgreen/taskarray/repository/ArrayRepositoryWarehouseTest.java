package com.jgreen.taskarray.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jgreen.taskarray.entity.ArrayEntity;
import com.jgreen.taskarray.entity.IntArrayWrapper;
import com.jgreen.taskarray.entity.DoubleArrayWrapper;
import com.jgreen.taskarray.repository.spec.AverageSpecification;
import com.jgreen.taskarray.repository.spec.ByIdSpecification;
import com.jgreen.taskarray.repository.spec.ByNameSpecification;
import com.jgreen.taskarray.repository.spec.CountSpecification;
import com.jgreen.taskarray.repository.spec.MaxSpecification;
import com.jgreen.taskarray.repository.spec.MinSpecification;
import com.jgreen.taskarray.repository.spec.SumSpecification;
import com.jgreen.taskarray.sorting.EntityComparators;
import com.jgreen.taskarray.warehouse.Warehouse;

class ArrayRepositoryWarehouseTest {

	private final ArrayRepository repository = ArrayRepository.getInstance();
	private final Warehouse warehouse = Warehouse.getInstance();

	@BeforeEach
	void resetSingletons() {
		warehouse.clear();
		repository.clear();
		repository.attach(warehouse.asObserver());
	}

	private IntArrayWrapper intArray(long id, String name, int... values) {
		IntArrayWrapper wrapper = new IntArrayWrapper(values);
		wrapper.setId(id);
		wrapper.setName(name);
		return wrapper;
	}

	private DoubleArrayWrapper doubleArray(long id, String name, double... values) {
		DoubleArrayWrapper wrapper = new DoubleArrayWrapper(values);
		wrapper.setId(id);
		wrapper.setName(name);
		return wrapper;
	}

	@Test
	void warehouseTracksStatisticsOnAdd() {
		IntArrayWrapper a = intArray(1, "a", 1, 2, 3);
		repository.add(a);

		assertEquals(1, warehouse.size());
		assertNotNull(warehouse.getStatistics(1));
		assertEquals(6.0, warehouse.getStatistics(1).getSum());
		assertEquals(2.0, warehouse.getStatistics(1).getAverage());
		assertEquals(1.0, warehouse.getStatistics(1).getMin());
		assertEquals(3.0, warehouse.getStatistics(1).getMax());
		assertEquals(3, warehouse.getStatistics(1).getCount());
	}

	@Test
	void warehouseRecalculatesWhenElementChanges() {
		IntArrayWrapper a = intArray(1, "a", 1, 2, 3);
		repository.add(a);

		a.setElement(0, 10); // triggers observer -> warehouse.recompute

		assertEquals(15.0, warehouse.getStatistics(1).getSum());
		assertEquals(5.0, warehouse.getStatistics(1).getAverage());
		assertEquals(2.0, warehouse.getStatistics(1).getMin());
		assertEquals(10.0, warehouse.getStatistics(1).getMax());
	}

	@Test
	void warehouseRemovesStatisticsOnRemove() {
		IntArrayWrapper a = intArray(1, "a", 1, 2, 3);
		repository.add(a);
		assertTrue(repository.remove(1));
		assertFalse(warehouse.getStatistics(1) != null);
	}

	@Test
	void duplicateIdRejected() {
		repository.add(intArray(1, "a", 1));
		assertThrows(IllegalArgumentException.class, () -> repository.add(intArray(1, "b", 2)));
	}

	@Test
	void nonPositiveIdRejected() {
		assertThrows(IllegalArgumentException.class, () -> repository.add(intArray(0, "zero", 1)));
	}

	@Test
	void findBySpecification() {
		repository.add(intArray(1, "a", 1, 2, 3));   // sum 6, avg 2, count 3, min 1, max 3
		repository.add(intArray(2, "b", 10, 20));    // sum 30, avg 15, count 2, min 10, max 20
		repository.add(doubleArray(3, "c", 5.0, 5.0)); // sum 10, avg 5, count 2

		assertEquals(1, repository.findBySpecification(new ByIdSpecification(2)).size());
		assertEquals(1, repository.findBySpecification(new ByNameSpecification("c")).size());
		assertEquals(2, repository.findBySpecification(SumSpecification.greaterThan(8)).size());
		assertEquals(2, repository.findBySpecification(AverageSpecification.lessThan(10)).size());
		assertEquals(2, repository.findBySpecification(CountSpecification.equalTo(2)).size());
		assertEquals(1, repository.findBySpecification(MaxSpecification.greaterThan(15)).size());
		assertEquals(1, repository.findBySpecification(MinSpecification.equalTo(10)).size());

		// combined specifications
		List<ArrayEntity> combined = repository.findBySpecification(
				SumSpecification.greaterThan(5).and(CountSpecification.lessThan(3)));
		assertEquals(2, combined.size()); // ids 2 and 3
	}

	@Test
	void sortingComparators() {
		repository.add(intArray(3, "gamma", 100));
		repository.add(intArray(1, "alpha", 1, 2));
		repository.add(intArray(2, "beta", 50, 50));

		assertEquals(List.of(1L, 2L, 3L),
				repository.findAllSorted(EntityComparators.byId()).stream().map(ArrayEntity::getId).toList());

		assertEquals(List.of("alpha", "beta", "gamma"),
				repository.findAllSorted(EntityComparators.byName()).stream().map(ArrayEntity::getName).toList());

		// by size (ascending): id3 (size 1) first, then id1 and id2 (size 2) by id
		assertEquals(List.of(3L, 1L, 2L),
				repository.findAllSorted(EntityComparators.bySize().thenComparing(EntityComparators.byId()))
						.stream().map(ArrayEntity::getId).toList());

		// by sum: id3 (100) < id2 (100) then id1? sums: id1=3, id2=100, id3=100
		assertEquals(List.of(1L, 2L, 3L),
				repository.findAllSorted(EntityComparators.bySum().thenComparing(EntityComparators.byId()))
						.stream().map(ArrayEntity::getId).toList());

		// by first element: id1 (1) < id2 (50) < id3 (100)
		assertEquals(List.of(1L, 2L, 3L),
				repository.findAllSorted(EntityComparators.byFirstElement()).stream().map(ArrayEntity::getId).toList());
	}
}