package controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import model.Image;
import model.Pixel;

/**
 * Class for the save operation that has an Image field.
 */
public class CreateImageIO implements CreateImageType {
  private final Image image;

  public CreateImageIO(Image image) {
    this.image = image;
  }

  @Override
  public void save(String path) throws RuntimeException {
    Pixel[][] array = this.image.getCopyPixels();
    BufferedImage image = new BufferedImage(array[0].length, array.length,
            BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array[0].length; j++) {
        image.setRGB(j, i, new Color(array[i][j].getRed(),
                array[i][j].getGreen(), array[i][j].getBlue()).getRGB());
      }
    }
    try {
      String[] filetype = path.split(".", 2);
      FileOutputStream file = new FileOutputStream(path);
      ImageIO.write(image, filetype[1], file);
      file.close();
    } catch (Exception e) {
      throw new RuntimeException();
    }
  }
}
