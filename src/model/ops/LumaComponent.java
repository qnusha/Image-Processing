package model.ops;

import model.Image;
import model.Pixel;
import model.SimpleImage;

/**
 * Takes the luma component of an image.
 */
public class LumaComponent implements ImageOperation {
  /**
   * Applies the operation.
   * @param i The image to run the operation on.
   * @return The luma component of the image.
   */
  @Override
  public Image apply(Image i) {
    Pixel[][] copy = i.getCopyPixels();
    for (int x = 0; x < copy.length; x++) {
      for (int y = 0; y < copy[x].length; y++) {
        Pixel p = copy[x][y];
        int grayscaleValue = (int) (p.getRed() * 0.2126);
        grayscaleValue += (int) (p.getGreen() * 0.7152);
        grayscaleValue += (int) (p.getBlue() * 0.0722);
        copy[x][y] = new Pixel(grayscaleValue, grayscaleValue, grayscaleValue);
      }
    }
    return new SimpleImage(copy);
  }
}
