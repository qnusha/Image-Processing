import org.junit.Before;
import org.junit.Test;

import model.Image;
import model.Pixel;
import model.SimpleImage;
import model.ops.VerticalFlip;

import static org.junit.Assert.assertArrayEquals;

/**
 * Testing Class for VerticalFlip.
 */
public class VerticalFlipTest {
  private VerticalFlip op = new VerticalFlip();
  private Image sunsetpix;
  private Pixel[][] sunsetpixHflipped;
  private Image cblockpix;
  private Pixel[][] cblockpixHflipped;

  @Before
  public void init() {
    Pixel redp = new Pixel(255, 0, 0);
    Pixel orangep = new Pixel(255, 128, 0);
    Pixel pinkp = new Pixel(255, 128, 128);
    Pixel yellowp = new Pixel(255, 225, 0);
    Pixel bluep = new Pixel(0, 150, 225);
    Pixel whitep = new Pixel(255, 225, 225);

    sunsetpix = new SimpleImage(new Pixel[][]{
            {redp, redp, orangep, orangep},
            {redp, redp, orangep, orangep},
            {pinkp, pinkp, yellowp, yellowp},
            {pinkp, pinkp, yellowp, yellowp}});
    sunsetpixHflipped = new Pixel[][]{
            {pinkp, pinkp, yellowp, yellowp},
            {pinkp, pinkp, yellowp, yellowp},
            {redp, redp, orangep, orangep},
            {redp, redp, orangep, orangep}};
    cblockpix = new SimpleImage(new Pixel[][]{
            {bluep, bluep, bluep, whitep, whitep},
            {bluep, bluep, bluep, whitep, whitep},
            {pinkp, pinkp, yellowp, bluep, bluep},
            {pinkp, pinkp, yellowp, bluep, bluep}});
    cblockpixHflipped = new Pixel[][]{
            {pinkp, pinkp, yellowp, bluep, bluep},
            {pinkp, pinkp, yellowp, bluep, bluep},
            {bluep, bluep, bluep, whitep, whitep},
            {bluep, bluep, bluep, whitep, whitep}};
  }

  @Test
  public void testVertFlip() {
    init();
    assertArrayEquals(sunsetpixHflipped, op.apply(sunsetpix).getCopyPixels());
    assertArrayEquals(cblockpixHflipped, op.apply(cblockpix).getCopyPixels());
  }
}