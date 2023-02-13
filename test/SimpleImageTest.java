import org.junit.Before;
import org.junit.Test;

import model.Image;
import model.Pixel;
import model.SimpleImage;
import model.ops.HorizontalFlip;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

/**
 * Testing class for SimpleImage.
 */
public class SimpleImageTest {
  private HorizontalFlip op = new HorizontalFlip();

  private Image sunsetpix;
  private Image sunsetpixHflipped;
  private Image tealpix;

  @Before
  public void init() {
    Pixel redp = new Pixel(255, 0, 0);
    Pixel orangep = new Pixel(255, 128, 0);
    Pixel pinkp = new Pixel(255, 128, 128);
    Pixel yellowp = new Pixel(255, 225, 0);
    Pixel bluep = new Pixel(0, 150, 225);
    Pixel whitep = new Pixel(255, 225, 225);
    Pixel tealp = new Pixel(0, 128, 128);

    sunsetpix = new SimpleImage(new Pixel[][]{
            {redp, redp, orangep, orangep},
            {redp, redp, orangep, orangep},
            {pinkp, pinkp, yellowp, yellowp},
            {pinkp, pinkp, yellowp, yellowp}});
    sunsetpixHflipped = new SimpleImage(new Pixel[][]{
            {orangep, orangep, redp, redp},
            {orangep, orangep, redp, redp},
            {yellowp, yellowp, pinkp, pinkp},
            {yellowp, yellowp, pinkp, pinkp}});
    tealpix = new SimpleImage(new Pixel[][]{
            {tealp, tealp, tealp, tealp, tealp},
            {tealp, tealp, tealp, tealp, tealp}});
  }

  @Test
  public void invalidInit() throws IllegalArgumentException {
    try {
      sunsetpix = new SimpleImage(new Pixel[][]{});
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // test should pass
    }

    try {
      tealpix = new SimpleImage(new Pixel[][]{});
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // test should pass
    }

    try {
      tealpix = new SimpleImage(null);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // test should pass
    }
  }

  @Test
  public void getMaxVal() {
    assertEquals(255, sunsetpix.getMaxVal());
    assertEquals(255, sunsetpixHflipped.getMaxVal());
    assertEquals(128, tealpix.getMaxVal());
  }

  @Test
  public void getCopyPixels() {
    Pixel redp = new Pixel(255, 0, 0);
    Pixel pinkp = new Pixel(255, 128, 128);
    Pixel nredp = new Pixel(255, 0, 0);
    Pixel norangep = new Pixel(255, 128, 0);
    Pixel npinkp = new Pixel(255, 128, 128);
    Pixel nyellowp = new Pixel(255, 225, 0);
    assertArrayEquals(new Pixel[][]{
            {nredp, nredp, norangep, norangep},
            {nredp, nredp, norangep, norangep},
            {npinkp, npinkp, nyellowp, nyellowp},
            {npinkp, npinkp, nyellowp, nyellowp}}, sunsetpix.getCopyPixels());
    assertArrayEquals(new Pixel[][]{
            {norangep, norangep, redp, redp},
            {norangep, norangep, redp, redp},
            {nyellowp, nyellowp, pinkp, pinkp},
            {nyellowp, nyellowp, pinkp, pinkp}}, sunsetpixHflipped.getCopyPixels());

  }

  @Test
  public void testEquals() {
    Pixel nredp = new Pixel(255, 0, 0);
    Pixel norangep = new Pixel(255, 128, 0);
    Pixel npinkp = new Pixel(255, 128, 128);
    Pixel nyellowp = new Pixel(255, 225, 0);
    assertNotEquals(sunsetpixHflipped, sunsetpix);
    assertEquals(new SimpleImage(new Pixel[][]{
            {nredp, nredp, norangep, norangep},
            {nredp, nredp, norangep, norangep},
            {npinkp, npinkp, nyellowp, nyellowp},
            {npinkp, npinkp, nyellowp, nyellowp}}), sunsetpix);
    assertEquals(sunsetpix, sunsetpix);
  }

  @Test
  public void testHashCode() {
    assertEquals(1403375520, sunsetpix.hashCode());
    assertEquals(-992555104, sunsetpixHflipped.hashCode());
    assertEquals(662123235, tealpix.hashCode());
  }
}