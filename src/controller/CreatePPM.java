package controller;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.management.RuntimeErrorException;

import model.Image;
import model.Pixel;

/**
 * Class for the save operation that has an Image field.
 */
public class CreatePPM implements CreateImageType {
  private final Image image;

  /**
   * Creates a new CreatePPM.
   *
   * @param image The image this will create.
   */
  public CreatePPM(Image image) {
    this.image = image;
  }

  /**
   * Saves this object's image within the computer's files.
   *
   * @param path The place to save this object's image to.
   */
  @Override
  public void save(String path) throws RuntimeErrorException {
    StringBuilder s = new StringBuilder();
    Pixel[][] array = this.image.getCopyPixels();
    s.append("P3\n");
    s.append(array[0].length + " " + array.length + "\n");
    s.append(String.valueOf(image.getMaxVal()) + "\n");
    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array[0].length; j++) {
        s.append(String.format("%d %d %d ", array[i][j].getRed(),
                array[i][j].getGreen(), array[i][j].getBlue()));
      }
      s.append("\n");
    }
    try {
      FileOutputStream file = new FileOutputStream(path);
      file.write(s.toString().getBytes());
      file.close();
    } catch (IOException e) {
      throw new RuntimeException();
    }
  }
}
