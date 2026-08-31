package com.jgreen.taskarray.factory;

/**
 * Abstract factory that declares the {@code Factory Method} for creating
 * entity (wrapper) instances.
 *
 * <p>The Factory Method pattern defines an interface for creating an object,
 * but lets subclasses decide which class to instantiate. Each concrete
 * implementation ({@code IntArrayFactoryMethodImpl}, {@code DoubleArrayFactoryMethodImpl})
 * decides what exact entity type it produces.</p>
 *
 * @param <T> the type of entity this factory produces
 */
public abstract class ArrayFactoryMethod<T> {

	/**
	 * Factory method: creates a new empty entity instance.
	 *
	 * @return newly created empty entity
	 */
	public abstract T createEmpty();

	/**
	 * Factory method: creates a new entity instance that is a defensive copy
	 * of the supplied prototype entity.
	 *
	 * @param original the source entity to be copied, must not be {@code null}
	 * @return a new independent entity instance with cloned internal data
	 * @throws NullPointerException if {@code original} is {@code null}
	 */
	public abstract T createCopy(T original);

	/**
	 * Convenience factory method that delegates to {@link #createEmpty()}.
	 *
	 * @return newly created empty entity
	 */
	public T create() {
		return createEmpty();
	}
}
