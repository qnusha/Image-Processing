package model.ops;

import model.Image;
import model.Pixel;
import model.SimpleImage;

/**
 * Takes the value component of an image.
 */
public class ValueComponent implements ImageOperation {
  /**
   * Applies the operation.
   * @param i The image to run the operation on.
   * @return The value component of the image.
   */
  @Override
  public Image apply(Image i) {
    Pixel[][] copy = i.getCopyPixels();
    for (int x = 0; x < copy.length; x++) {
      for (int y = 0; y < copy[x].length; y++) {
        Pixel p = copy[x][y];
        int grayscaleValue = Math.max(p.getRed(), Math.max(p.getGreen(), p.getBlue()));
        copy[x][y] = new Pixel(grayscaleValue, grayscaleValue, grayscaleValue);
      }
    }
    return new SimpleImage(copy);
  }
}
