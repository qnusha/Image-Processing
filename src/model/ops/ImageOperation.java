package model.ops;

import model.Image;

/**
 * An operation that can be run on an image.
 */
public interface ImageOperation {
  /**
   * Applies the operation on an image.
   * @param i The image to run the operation on.
   * @return An array of pixels that make up the output of this operation.
   */
  public Image apply(Image i);
}
