package model;

import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a Simple Image.
 */
public class SimpleImage implements Image {
  private int width;
  private int height;
  private Pixel[][] pixels;

  /**
   * Create a new Simple Image showing the specified visual.
   * @param pixels A 2D Array of pixels that make up an image.
   */
  public SimpleImage(Pixel[][] pixels) {
    if (pixels == null || pixels.length < 1) {
      throw new IllegalArgumentException("Invalid pixels.");
    }
    this.height = pixels.length;
    this.width = pixels[0].length;
    this.pixels = pixels;
  }

  /**
   * Returns the maximum value of any RGB of any pixel in this image.
   * @return The maximum value of any RGB of any pixel in this image.
   */
  public int getMaxVal() {
    int product = 0;
    for (Pixel[] row : pixels) {
      for (Pixel p : row) {
        product = Math.max(Math.max(p.getRed(), p.getGreen()), Math.max(p.getBlue(), product));
      }
    }
    return product;
  }

  /**
   * Returns the pixels that make up this image as a 2D Array.
   * @return The pixels that make up this image as a 2D Array.
   */
  public Pixel[][] getCopyPixels() {
    Pixel[][] copy = new Pixel[height][width];
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        Pixel p = pixels[row][col];
        copy[row][col] = new Pixel(p.getRed(), p.getGreen(), p.getBlue());
      }
    }
    return copy;
  }

  /**
   * Test if two images are equal.
   * @param o The other image we want to evaluate.
   * @return True if this is equal to the other image.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SimpleImage)) {
      return false;
    }

    SimpleImage other = (SimpleImage) o;
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        if (!other.pixels[row][col].equals(this.pixels[row][col])) {
          return false;
        }
      }
    }
    return this.width == other.width && this.height == other.height;
  }

  /**
   * Returns this object's hashCode.
   * @return This object's hashCode.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.width, this.height, Arrays.deepHashCode(this.pixels));
  }
}
