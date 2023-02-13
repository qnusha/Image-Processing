package model.ops;

/**
 * Applies a Gaussian Blur to an image.
 */
public class GaussianBlur extends KernelOperation {
  @Override
  protected double[][] getKernel() {
    return new double[][]{
            {0.0625, 0.125, 0.0625},
            {0.125, 0.25, 0.125},
            {0.0625, 0.125, 0.0625}};
  }
}
