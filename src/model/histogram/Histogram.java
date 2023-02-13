package model.histogram;

/**
 * Class that takes in an image and generates a Histogram.
 */
public interface Histogram {
  /**
   * Returns this Histogram.
   * @return An array of integers containing 256 values.
   */
  public int[] get();
}
