import org.junit.Before;
import org.junit.Test;

import model.Image;
import model.Pixel;
import model.SimpleImage;
import model.ops.Brighten;
import model.ops.ImageOperation;

import static org.junit.Assert.assertArrayEquals;

/**
 * Testing Class for Brighten.
 */
public class BrightenTest {
  private ImageOperation op = new Brighten(50);
  private ImageOperation opDarken = new Brighten(-20);
  private Image sunsetpix;
  private Pixel[][] sunsetpixalt;
  private Pixel[][] sunsetpixdark;
  private Image cblockpix;
  private Pixel[][] cblockpixalt;
  private Pixel[][] cblockpixdark;

  @Before
  public void init() {
    Pixel redp = new Pixel(255, 0, 0);
    Pixel orangep = new Pixel(255, 128, 0);
    Pixel pinkp = new Pixel(255, 128, 128);
    Pixel yellowp = new Pixel(255, 225, 0);
    Pixel bluep = new Pixel(0, 150, 225);
    Pixel whitep = new Pixel(255, 225, 225);

    Pixel redpalt = new Pixel(255, 50, 50);
    Pixel orangepalt = new Pixel(255, 178, 50);
    Pixel pinkpalt = new Pixel(255, 178, 178);
    Pixel yellowpalt = new Pixel(255, 255, 50);
    Pixel bluepalt = new Pixel(50, 200, 255);
    Pixel whitepalt = new Pixel(255, 255, 255);

    Pixel redpdark = new Pixel(235, 0, 0);
    Pixel orangepdark = new Pixel(235, 108, 0);
    Pixel pinkpdark = new Pixel(235, 108, 108);
    Pixel yellowpdark = new Pixel(235, 205, 0);
    Pixel bluepdark = new Pixel(0, 130, 205);
    Pixel whitepdark = new Pixel(235, 205, 205);

    sunsetpix = new SimpleImage(new Pixel[][]{
            {redp, redp, orangep, orangep},
            {redp, redp, orangep, orangep},
            {pinkp, pinkp, yellowp, yellowp},
            {pinkp, pinkp, yellowp, yellowp}});
    sunsetpixalt = new Pixel[][]{
            {redpalt, redpalt, orangepalt, orangepalt},
            {redpalt, redpalt, orangepalt, orangepalt},
            {pinkpalt, pinkpalt, yellowpalt, yellowpalt},
            {pinkpalt, pinkpalt, yellowpalt, yellowpalt}};
    sunsetpixdark = new Pixel[][]{
            {redpdark, redpdark, orangepdark, orangepdark},
            {redpdark, redpdark, orangepdark, orangepdark},
            {pinkpdark, pinkpdark, yellowpdark, yellowpdark},
            {pinkpdark, pinkpdark, yellowpdark, yellowpdark}};
    cblockpix = new SimpleImage(new Pixel[][]{
            {bluep, bluep, bluep, whitep, whitep},
            {bluep, bluep, bluep, whitep, whitep},
            {pinkp, pinkp, yellowp, bluep, bluep},
            {pinkp, pinkp, yellowp, bluep, bluep}});
    cblockpixalt = new Pixel[][]{
            {bluepalt, bluepalt, bluepalt, whitepalt, whitepalt},
            {bluepalt, bluepalt, bluepalt, whitepalt, whitepalt},
            {pinkpalt, pinkpalt, yellowpalt, bluepalt, bluepalt},
            {pinkpalt, pinkpalt, yellowpalt, bluepalt, bluepalt}};
    cblockpixdark = new Pixel[][]{
            {bluepdark, bluepdark, bluepdark, whitepdark, whitepdark},
            {bluepdark, bluepdark, bluepdark, whitepdark, whitepdark},
            {pinkpdark, pinkpdark, yellowpdark, bluepdark, bluepdark},
            {pinkpdark, pinkpdark, yellowpdark, bluepdark, bluepdark}};
  }

  @Test
  public void testImageOp() {
    assertArrayEquals(sunsetpixalt, op.apply(sunsetpix).getCopyPixels());
    assertArrayEquals(cblockpixalt, op.apply(cblockpix).getCopyPixels());
    assertArrayEquals(sunsetpixdark, opDarken.apply(sunsetpix).getCopyPixels());
    assertArrayEquals(cblockpixdark, opDarken.apply(cblockpix).getCopyPixels());
  }
}