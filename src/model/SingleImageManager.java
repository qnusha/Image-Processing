package model;

import model.ops.ImageOperation;

/**
 * Manager for a Single Image.
 */
public class SingleImageManager implements ImageManager {
  private Image working;

  /**
   * Saves an image into this ImageManager.
   *
   * @param key The name of the image.
   * @param i   The image itself.
   */
  @Override
  public void add(String key, Image i) {
    this.working = i;
  }

  /**
   * Fetches an image from this ImageManager, or null if that image does not exist.
   *
   * @param key The name of the image.
   * @return The requested image, or null if that image is not saved.
   */
  @Override
  public Image fetch(String key) {
    return this.working;
  }

  /**
   * Runs the given operation on the selected image, and saves the result under a new name.
   *
   * @param io      The operation to run on the image.
   * @param name    The name of the image to run the operation on.
   * @param newName The name to save the output under.
   */
  @Override
  public void runOp(ImageOperation io, String name, String newName) {
    this.working = io.apply(this.working);
  }
}
