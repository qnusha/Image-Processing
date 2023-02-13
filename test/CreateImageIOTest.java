import org.junit.Before;
import org.junit.Test;

import java.io.File;

import controller.CreateImageIO;
import controller.CreateImageType;
import model.Image;
import model.Pixel;
import model.SimpleImage;
import model.ops.HorizontalFlip;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Testing class for CreatePPM.
 */
public class CreateImageIOTest {
  private HorizontalFlip op = new HorizontalFlip();
  private Image sunsetpix;

  @Before
  public void init() {
    Pixel redp = new Pixel(255, 0, 0);
    Pixel orangep = new Pixel(255, 128, 0);
    Pixel pinkp = new Pixel(255, 128, 128);
    Pixel yellowp = new Pixel(255, 225, 0);

    sunsetpix = new SimpleImage(new Pixel[][]{
            {redp, redp, orangep, orangep},
            {redp, redp, orangep, orangep},
            {pinkp, pinkp, yellowp, yellowp},
            {pinkp, pinkp, yellowp, yellowp}});
  }


  @Test
  public void testSave() {
    CreateImageType cppm = new CreateImageIO(sunsetpix);
    cppm.save("res/testImages/sunset.jpeg");
    File f = new File("res/testImages/sunset.jpeg");
    assertTrue(f.exists());

    cppm.save("res/testImages/sunset.bmp");
    f = new File("res/testImages/sunset.bmp");
    assertTrue(f.exists());

    cppm.save("res/testImages/sunset.png");
    f = new File("res/testImages/sunset.png");
    assertTrue(f.exists());

    f = new File("res/testImages/sunset");
    assertFalse(f.exists());
  }
}