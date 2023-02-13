import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import controller.ImageIOUtil;
import model.Pixel;

import static org.junit.Assert.assertArrayEquals;

/**
 * Testing class for ImageUtil.
 */
public class ImageIOUtilTest {
  private Pixel[][] pinwheelpix;
  private Pixel[][] pinwheelpix2;
  private Pixel[][] checkerspix;
  private Pixel[][] checkerspix2;

  @Before
  public void init() {
    Pixel bluep = new Pixel(0, 0, 255);
    Pixel bluepalt = new Pixel(4, 50, 253);
    Pixel bluepalt2 = new Pixel(11, 50, 255);
    Pixel bluepalt3 = new Pixel(12, 51, 255);
    Pixel bluepalt4 = new Pixel(4, 50, 255);
    Pixel magentap = new Pixel(251, 0, 255);
    Pixel magentapalt = new Pixel(255, 64, 251);
    Pixel magentapalt2 = new Pixel(255, 64, 255);
    Pixel cyanp = new Pixel(33, 255, 255);
    Pixel cyanpalt = new Pixel(0, 249, 251);
    Pixel blackp = new Pixel(0, 0, 0);
    Pixel blackpalt = new Pixel(2, 2, 2);
    Pixel blackpalt2 = new Pixel(3, 3, 3);
    Pixel whitep = new Pixel(255, 255, 255);
    Pixel whitepalt = new Pixel(253, 253, 253);

    pinwheelpix = new Pixel[][]{
            {bluep, magentap, bluep},
            {magentap, cyanp, magentap},
            {bluep, magentap, bluep}};
    pinwheelpix2 = new Pixel[][]{
            {bluepalt, magentapalt, bluepalt2},
            {magentapalt, cyanpalt, magentapalt2},
            {bluepalt3, magentapalt2, bluepalt4}};
    checkerspix = new Pixel[][]{
            {blackp, whitep, blackp, whitep, blackp, whitep},
            {whitep, blackp, whitep, blackp, whitep, blackp},
            {blackp, whitep, blackp, whitep, blackp, whitep},
            {whitep, blackp, whitep, blackp, whitep, blackp}};
    checkerspix2 = new Pixel[][]{
            {blackp, whitep, blackp, whitep, blackp, whitep},
            {whitepalt, blackp, whitepalt, blackp, whitep, blackp},
            {blackpalt, whitepalt, blackpalt, whitep, blackp, whitep},
            {whitepalt, blackpalt, whitepalt, blackp, whitep, blackpalt2}};
  }

  @Test(expected = FileNotFoundException.class)
  public void testInvalidFile() throws FileNotFoundException {
    assertArrayEquals(pinwheelpix, new ImageIOUtil().read("ponwool.png").getCopyPixels());
  }

  @Test
  public void testFileLoading() throws FileNotFoundException {
    assertArrayEquals(pinwheelpix,
            new ImageIOUtil().read("res/testImages/pinwheel.png").getCopyPixels());
    assertArrayEquals(pinwheelpix2,
            new ImageIOUtil().read("res/testImages/pinwheel.jpeg").getCopyPixels());
    assertArrayEquals(pinwheelpix,
            new ImageIOUtil().read("res/testImages/pinwheel.bmp").getCopyPixels());
    assertArrayEquals(checkerspix,
            new ImageIOUtil().read("res/testImages/checkers.png").getCopyPixels());
    assertArrayEquals(checkerspix2,
            new ImageIOUtil().read("res/testImages/checkers.jpeg").getCopyPixels());
    assertArrayEquals(checkerspix,
            new ImageIOUtil().read("res/testImages/checkers.bmp").getCopyPixels());
  }
}