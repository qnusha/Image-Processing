package controller;

/**
 * Class that can save images as a type of file.
 */
public interface CreateImageType {

  /**
   * Saves this object's image within the computer's files.
   *
   * @param path The place to save this object's image to.
   */
  public void save(String path) throws RuntimeException;
}
