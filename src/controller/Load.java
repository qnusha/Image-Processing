package controller;

import java.io.FileNotFoundException;

import model.Image;

/**
 * Interface for classes that load images.
 */
public interface Load {
  public Image read(String filename) throws FileNotFoundException;
}
