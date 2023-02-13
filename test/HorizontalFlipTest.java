import org.junit.Before;
import org.junit.Test;

import model.Image;
import model.Pixel;
import model.SimpleImage;
import model.ops.HorizontalFlip;

import static org.junit.Assert.assertArrayEquals;

/**
 * Testing Class for HorizontalFlip.
 */
public class HorizontalFlipTest {
  private HorizontalFlip op = new HorizontalFlip();
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
            {orangep, orangep, redp, redp},
            {orangep, orangep, redp, redp},
            {yellowp, yellowp, pinkp, pinkp},
            {yellowp, yellowp, pinkp, pinkp}};
    cblockpix = new SimpleImage(new Pixel[][]{
            {bluep, bluep, bluep, whitep, whitep},
            {bluep, bluep, bluep, whitep, whitep},
            {pinkp, pinkp, yellowp, bluep, bluep},
            {pinkp, pinkp, yellowp, bluep, bluep}});
    cblockpixHflipped = new Pixel[][]{
            {whitep, whitep, bluep, bluep, bluep},
            {whitep, whitep, bluep, bluep, bluep},
            {bluep, bluep, yellowp, pinkp, pinkp},
            {bluep, bluep, yellowp, pinkp, pinkp}};
  }

  @Test
  public void testHorizFlip() {
    init();
    assertArrayEquals(sunsetpixHflipped, op.apply(sunsetpix).getCopyPixels());
    assertArrayEquals(cblockpixHflipped, op.apply(cblockpix).getCopyPixels());
  }
}