package model.histogram;

import model.Image;
import model.Pixel;

/**
 * Histogram built based on the Red Component Value of pixels.
 * If a Component operation is applied to the image before this, this uses that component instead.
 */
public class RedHistogram implements Histogram {
  private Image myImg;

  public RedHistogram(Image i) {
    this.myImg = i;
  }

  /**
   * Returns this Histogram.
   * @return An array of integers containing 256 values.
   */
  @Override
  public int[] get() {
    int[] product = new int[256];
    for (int i: product) {
      i = 0;
    }
    for (Pixel[] row: this.myImg.getCopyPixels()) {
      for (Pixel p: row) {
        product[p.getRed()] += 1;
      }
    }
    return product;
  }
}
