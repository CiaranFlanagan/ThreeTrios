package model;

/**
 * A Lens is both a function [forward] from x to y and a function [backward] from y to x.
 * @param <X> forward argument
 * @param <Y> backards argument
 */
public interface Lens<X, Y> {
  Y forward(X x);

  X backward(Y y);

}
