package model.ops;

import model.Image;
import model.Pixel;
import model.SimpleImage;

/**
 * Convert a normal color image into a sepia-toned image.
 */
public class SepiaFilter implements ImageOperation {
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
        int redValue = (int) (p.getRed() * 0.393);
        redValue += (int) (p.getGreen() * 0.769);
        redValue += (int) (p.getBlue() * 0.189);
        int greenValue = (int) (p.getRed() * 0.349);
        greenValue += (int) (p.getGreen() * 0.686);
        greenValue += (int) (p.getBlue() * 0.186);
        int blueValue = (int) (p.getRed() * 0.272);
        blueValue += (int) (p.getGreen() * 0.534);
        blueValue += (int) (p.getBlue() * 0.131);
        redValue = Math.min(redValue, 255);
        greenValue = Math.min(greenValue, 255);
        blueValue = Math.min(blueValue, 255);
        copy[x][y] = new Pixel(redValue, greenValue, blueValue);
      }
    }
    return new SimpleImage(copy);
  }
}
