package model.ops;

import model.Image;
import model.Pixel;
import model.SimpleImage;

/**
 * Takes the intensity component of an image.
 */
public class IntensityComponent implements ImageOperation {
  /**
   * Applies the operation.
   * @param i The image to run the operation on.
   * @return The intensity component of the image.
   */
  @Override
  public Image apply(Image i) {
    Pixel[][] copy = i.getCopyPixels();
    for (int x = 0; x < copy.length; x++) {
      for (int y = 0; y < copy[x].length; y++) {
        Pixel p = copy[x][y];
        int grayscaleValue = (p.getRed() + p.getGreen() + p.getBlue()) / 3;
        copy[x][y] = new Pixel(grayscaleValue, grayscaleValue, grayscaleValue);
      }
    }
    return new SimpleImage(copy);
  }
}
