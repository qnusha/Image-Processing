package model.ops;

import model.Image;
import model.Pixel;
import model.SimpleImage;

/**
 * Takes the red component of an image.
 */
public class RedComponent implements ImageOperation {
  /**
   * Applies the operation.
   * @param i The image to run the operation on.
   * @return The red component of the image.
   */
  @Override
  public Image apply(Image i) {
    Pixel[][] copy = i.getCopyPixels();
    for (int x = 0; x < copy.length; x++) {
      for (int y = 0; y < copy[x].length; y++) {
        Pixel p = copy[x][y];
        int grayscaleValue = p.getRed();
        copy[x][y] = new Pixel(grayscaleValue, grayscaleValue, grayscaleValue);
      }
    }
    return new SimpleImage(copy);
  }
}
