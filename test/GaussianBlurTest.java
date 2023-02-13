import org.junit.Before;
import org.junit.Test;

import model.Image;
import model.Pixel;
import model.SimpleImage;
import model.ops.GaussianBlur;
import model.ops.ImageOperation;

import static org.junit.Assert.assertArrayEquals;

/**
 * Testing Class for KernelOperation.
 */
public class GaussianBlurTest {
  private ImageOperation op = new GaussianBlur();
  private Image sunsetpix;
  private Pixel[][] sunsetpixalt;
  private Image cblockpix;
  private Pixel[][] cblockpixalt;

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
    sunsetpixalt = new Pixel[][]{
            {new Pixel(143, 0, 0),
                new Pixel(191, 24, 0),
                new Pixel(191, 72, 0),
                new Pixel(143, 72, 0)},
            {new Pixel(191, 24, 24),
                new Pixel(255, 62, 24),
                new Pixel(255, 122, 8),
                new Pixel(191, 114, 0)},
            {new Pixel(191, 72, 72),
                new Pixel(255, 122, 72),
                new Pixel(255, 174, 24),
                new Pixel(191, 150, 0)},
            {new Pixel(143, 72, 72),
                new Pixel(191, 114, 72),
                new Pixel(191, 150, 24),
                new Pixel(143, 126, 0)}};
    cblockpix = new SimpleImage(new Pixel[][]{
            {bluep, bluep, bluep, whitep, whitep},
            {bluep, bluep, bluep, whitep, whitep},
            {pinkp, pinkp, yellowp, bluep, bluep},
            {pinkp, pinkp, yellowp, bluep, bluep}});
    cblockpixalt = new Pixel[][]{
            {new Pixel(0, 84, 126),
                new Pixel(0, 112, 168),
                new Pixel(47, 126, 168),
                new Pixel(143, 154, 168),
                new Pixel(143, 126, 126)},
            {new Pixel(47, 108, 150),
                new Pixel(63, 150, 192),
                new Pixel(95, 172, 190),
                new Pixel(159, 196, 210),
                new Pixel(143, 154, 168)},
            {new Pixel(143, 100, 114),
                new Pixel(191, 151, 128),
                new Pixel(159, 178, 122),
                new Pixel(95, 178, 182),
                new Pixel(47, 126, 168)},
            {new Pixel(143, 72, 72),
                new Pixel(191, 114, 72),
                new Pixel(143, 136, 66),
                new Pixel(47, 126, 126),
                new Pixel(0, 84, 126)}};
  }

  @Test
  public void testImageOp() {
    assertArrayEquals(sunsetpixalt, op.apply(sunsetpix).getCopyPixels());
    assertArrayEquals(cblockpixalt, op.apply(cblockpix).getCopyPixels());
  }
}