package model;

/**
 * Represents an Image with pixels.
 */
public interface Image {
  /**
   * Returns the pixels that make up this image as a 2D Array.
   * @return The pixels that make up this image as a 2D Array.
   */
  public Pixel[][] getCopyPixels();

  /**
   * Returns the maximum value of any RGB of any pixel in this image.
   * @return The maximum value of any RGB of any pixel in this image.
   */
  public int getMaxVal();
}
