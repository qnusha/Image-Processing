package model.ops;

/**
 * Sharpens an image.
 */
public class Sharpen extends KernelOperation {
  @Override
  protected double[][] getKernel() {
    return new double[][]{
            {-0.125, -0.125, -0.125, -0.125, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, 0.25, 1.0, 0.25, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, -0.125, -0.125, -0.125, -0.125}};
  }
}
