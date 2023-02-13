import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import controller.PPMUtil;
import model.Pixel;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Testing class for ImageUtil.
 */
public class PPMUtilTest {
  private Pixel[][] sunsetpix;

  @Before
  public void init() {
    Pixel redp = new Pixel(255, 0, 0);
    Pixel orangep = new Pixel(255, 128, 0);
    Pixel pinkp = new Pixel(255, 128, 128);
    Pixel yellowp = new Pixel(255, 255, 0);

    sunsetpix = new Pixel[][]{
            {yellowp, yellowp, pinkp, pinkp},
            {yellowp, yellowp, pinkp, pinkp},
            {orangep, orangep, redp, redp},
            {orangep, orangep, redp, redp}};
  }

  @Test(expected = FileNotFoundException.class)
  public void testInvalidFile() throws FileNotFoundException {
    assertArrayEquals(sunsetpix, new PPMUtil().read("soonsoot.png").getCopyPixels());
  }

  @Test
  public void testFileLoading() throws FileNotFoundException {
    new PPMUtil().read("res/testImages/sunset.ppm");
    assertEquals(sunsetpix[0][0],
            new PPMUtil().read("res/testImages/sunset.ppm").getCopyPixels()[0][0]);
  }
}