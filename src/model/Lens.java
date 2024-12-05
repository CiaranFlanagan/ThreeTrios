package model;

public interface Lens<X, Y> {
  Y forward(X x);

  X backward(Y y);

}
