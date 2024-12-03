package copy.view.utils;

/**
 * Represents an operation that accepts three input arguments and returns no result.
 * This is a functional interface similar to {@link java.util.function.BiConsumer},
 * but it supports three inputs.
 *
 * @param <A> the type of the first argument to the operation
 * @param <B> the type of the second argument to the operation
 * @param <C> the type of the third argument to the operation
 */
@FunctionalInterface
public interface TriConsumer<A, B, C> {

  /**
   * Performs this operation on the given arguments.
   *
   * @param a the first input argument
   * @param b the second input argument
   * @param c the third input argument
   */
  void accept(A a, B b, C c);

}
