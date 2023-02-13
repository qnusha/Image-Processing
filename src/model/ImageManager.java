package model;

import model.ops.ImageOperation;

/**
 * Manages all Images that the program is currently working on, and runs operations on them.
 */
public interface ImageManager {

  /**
   * Saves an image into this ImageManager.
   *
   * @param key The name of the image.
   * @param i   The image itself.
   */
  public void add(String key, Image i);

  /**
   * Fetches an image from this ImageManager, or null if that image does not exist.
   *
   * @param key The name of the image.
   * @return The requested image, or null if that image is not saved.
   */
  public Image fetch(String key);

  /**
   * Runs the given operation on the selected image, and saves the result under a new name.
   *
   * @param io      The operation to run on the image.
   * @param name    The name of the image to run the operation on.
   * @param newName The name to save the output under.
   */
  public void runOp(ImageOperation io, String name, String newName);
}
