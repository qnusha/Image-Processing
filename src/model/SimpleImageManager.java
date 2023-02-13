package model;

import java.util.HashMap;
import java.util.Map;

import model.ops.ImageOperation;

/**
 * Manages all Images that the program is currently working on, and runs operations on them.
 */
public class SimpleImageManager implements ImageManager {
  private Map<String, Image> map;

  /**
   * Creates a new SimpleImageManager that is tracking no images.
   */
  public SimpleImageManager() {
    this.map = new HashMap<String, Image>();
  }

  /**
   * Saves an image into the HashMap.
   *
   * @param key The name of the image.
   * @param i   The image itself.
   */
  public void add(String key, Image i) {
    map.put(key, i);
  }

  /**
   * Fetches an image from the HashMap, or null if that image does not exist.
   *
   * @param key The name of the image.
   * @return The requested image, or null if that image is not saved.
   */
  @Override
  public Image fetch(String key) {
    return map.get(key);
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
    this.add(newName, io.apply(this.map.get(name)));
  }
}
