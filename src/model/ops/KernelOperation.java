package model.ops;

import model.Image;
import model.Pixel;
import model.SimpleImage;

/**
 * An ImageOperation that applies a Kernel to an Image.
 */
public abstract class KernelOperation implements ImageOperation {
  /**
   * Gives this KernelOperation the kernel to be used on this image.
   *
   * @return A Kernel, in the form of a 2D Array of Doubles
   */
  protected abstract double[][] getKernel();

  /**
   * Applies the operation on an image.
   *
   * @param i The image to run the operation on.
   * @return An array of pixels that make up the output of this operation.
   */
  public Image apply(Image i) {
    double[][] kernel = this.getKernel();
    Pixel[][] pixels = i.getCopyPixels();
    Pixel[][] product = i.getCopyPixels();
    int middleCol = kernel.length / 2;
    int middleRow = kernel[0].length / 2;
    int height = pixels.length;
    int width = pixels[0].length;
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        double newR = 0;
        double newG = 0;
        double newB = 0;
        for (int r = 0; r < kernel.length; r++) {
          for (int c = 0; c < kernel[0].length; c++) {
            try {
              Pixel p = pixels[row + (r - middleRow)][col + (c - middleCol)];
              newR += (p.getRed() * kernel[r][c]);
              newG += (p.getGreen() * kernel[r][c]);
              newB += (p.getBlue() * kernel[r][c]);
            } catch (ArrayIndexOutOfBoundsException e) {
              // to catch when the index is out of bounds
            }
          }
        }
        newR = Math.max(0, Math.min(newR, 255));
        newG = Math.max(0, Math.min(newG, 255));
        newB = Math.max(0, Math.min(newB, 255));
        product[row][col] = new Pixel((int) newR, (int) newG, (int) newB);
      }
    }
    return new SimpleImage(product);
  }
}
