package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.Image;
import model.Pixel;
import model.SimpleImage;

/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class PPMUtil implements Load {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public Image read(String filename) throws FileNotFoundException {
    Scanner sc;

    sc = new Scanner(new FileInputStream(filename));
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      //System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();

    Pixel[][] pixels = new Pixel[height][width];
    for (int rows = 0; rows < height; rows++) {
      for (int col = 0; col < width; col++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        pixels[rows][col] = new Pixel(r, g, b);
        //System.out.println("Color of pixel (" + j + "," + i + "): " + r + "," + g + "," + b);
      }
    }
    return new SimpleImage(pixels);
  }
}

