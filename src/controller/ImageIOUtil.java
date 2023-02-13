package controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.Image;
import model.Pixel;
import model.SimpleImage;

/**
 * This class contains utility methods to read an image from file and simply print its contents.
 */
public class ImageIOUtil implements Load {

  // document why modified
  @Override
  public Image read(String filename) throws FileNotFoundException {
    BufferedImage img = null;
    try {
      img = ImageIO.read(new File(filename));
    } catch (IOException e) {
      throw new FileNotFoundException();
    }
    Pixel[][] pixels = new Pixel[img.getHeight()][img.getWidth()];
    for (int rows = 0; rows < img.getHeight(); rows++) {
      for (int col = 0; col < img.getWidth(); col++) {
        int r = new Color(img.getRGB(col, rows)).getRed();
        int g = new Color(img.getRGB(col, rows)).getGreen();
        int b = new Color(img.getRGB(col, rows)).getBlue();
        pixels[rows][col] = new Pixel(r, g, b);
      }
    }
    return new SimpleImage(pixels);
  }
}
