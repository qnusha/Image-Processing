package model.ops;

import model.Image;
import model.Pixel;
import model.SimpleImage;

/**
 * Flip an image horizontally.
 */
public class HorizontalFlip implements ImageOperation {
  /**
   * Run this operation on an image.
   * @param i The image to run the operation on.
   * @return The image, flipped.
   */
  @Override
  public Image apply(Image i) {
    Pixel[][] pixels = i.getCopyPixels();
    int height = pixels.length;
    int width = pixels[0].length;
    Pixel[][] copy = new Pixel[height][width];
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        Pixel p = pixels[row][col];
        copy[row][Math.abs(col - (width - 1))] = new Pixel(p.getRed(), p.getGreen(), p.getBlue());
      }
    }
    return new SimpleImage(copy);
  }
}
