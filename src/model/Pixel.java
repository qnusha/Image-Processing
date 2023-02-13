package model;

import java.util.Objects;

/**
 * Represents a single pixel. 
 */
public class Pixel {
  private int red;
  private int green;
  private int blue;

  /**
   * Create a new Pixel with the given RGB Value.
   * @param red This pixel's R value.
   * @param green This pixel's G value.
   * @param blue This pixel's B value.
   * @throws IllegalArgumentException If any of the RGB values are null, or outside of range 0-255.
   */
  public Pixel(int red, int green, int blue) throws IllegalArgumentException {
    if ((red < 0) || (red > 255) || (green < 0) || (green > 255) || (blue < 0) || (blue > 255)) {
      throw new IllegalArgumentException("Invalid pixel colors");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Test if two pixels are equal.
   * @param other The other pixel we want to evaluate.
   * @return True if this is equal to the other pixel.
   */
  @Override
  public boolean equals(Object other) {
    return other instanceof Pixel
            && this.getRed() == ((Pixel) other).getRed()
            && this.getBlue() == ((Pixel) other).getBlue()
            && this.getGreen() == ((Pixel) other).getGreen();
  }

  /**
   * Expresses this pixel as a String.
   * @return This pixel as a string.
   */
  @Override
  public String toString() {
    return "Pixel[R" + this.getRed() + " G" + this.getGreen() + " B" + this.getBlue() + "]";
  }

  /**
   * Returns this object's hashCode.
   * @return This object's hashCode.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.getRed(), this.getGreen(), this.getBlue());
  }

  /**
   * Returns this pixel's R Value.
   * @return This pixel's R Value.
   */
  public int getRed() {
    return this.red;
  }

  /**
   * Returns this pixel's B Value.
   * @return This pixel's G Value.
   */
  public int getGreen() {
    return this.green;
  }

  /**
   * Returns this pixel's B Value.
   * @return This pixel's B Value.
   */
  public int getBlue() {
    return this.blue;
  }

}
