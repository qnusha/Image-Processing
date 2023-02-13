package model.ops;

import model.Image;
import model.Pixel;
import model.SimpleImage;

/**
 * Brighten or darken an image by the given amount.
 */
public class Brighten implements ImageOperation {
  private final int brightenAmt;

  /**
   * Create a new Brighten.
   *
   * @param i The amount to brighten an image given to this by. Does not throw
   *          exception because all integers are technically valid to brighten or darken the image
   */
  public Brighten(int i) {
    this.brightenAmt = i;
  }

  /**
   * Apply the operation.
   *
   * @param i The image to run the operation on.
   * @return The brightened image.
   */
  @Override
  public Image apply(Image i) {
    Pixel[][] copy = i.getCopyPixels();
    for (int x = 0; x < copy.length; x++) {
      for (int y = 0; y < copy[x].length; y++) {
        Pixel p = copy[x][y];
        copy[x][y] = new Pixel(Math.max(0, Math.min(255, p.getRed() + brightenAmt)),
                Math.max(0, Math.min(255, p.getGreen() + brightenAmt)),
                Math.max(0, Math.min(255, p.getBlue() + brightenAmt)));
      }
    }
    return new SimpleImage(copy);
  }
}
