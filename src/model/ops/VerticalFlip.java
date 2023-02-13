package model.ops;

import model.Image;
import model.Pixel;
import model.SimpleImage;

/**
 * Flip an image vertically.
 */
public class VerticalFlip implements ImageOperation {
  /**
   * Flips an image vertically.
   * @param i The image to run the operation on.
   * @return The image, flipped vertically.
   */
  @Override
  public Image apply(Image i) {
    Pixel[][] copy = i.getCopyPixels();
    Pixel[][] product = new Pixel[copy.length][copy[0].length];
    for (int x = 0; x < copy.length; x++) {
      product[x] = copy[Math.abs(x - (copy.length - 1))];
    }
    return new SimpleImage(product);
  }
}
