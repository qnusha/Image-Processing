import org.junit.Before;
import org.junit.Test;

import java.io.File;

import controller.CreateImageType;
import controller.CreatePPM;
import model.Image;
import model.Pixel;
import model.SimpleImage;
import model.ops.HorizontalFlip;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Testing class for CreatePPM.
 */
public class CreatePPMTest {
  private HorizontalFlip op = new HorizontalFlip();
  private Image sunsetpix;
  private Image cblockpix;

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
    cblockpix = new SimpleImage(new Pixel[][]{
            {bluep, bluep, bluep, whitep, whitep},
            {bluep, bluep, bluep, whitep, whitep},
            {pinkp, pinkp, yellowp, bluep, bluep},
            {pinkp, pinkp, yellowp, bluep, bluep}});
  }


  @Test
  public void testSave() {
    CreateImageType cppm = new CreatePPM(sunsetpix);
    cppm.save("res/testImages/sunset.ppm");
    File f = new File("res/testImages/sunset.ppm");
    assertTrue(f.exists());
    f = new File("res/testImages/sunset");
    assertFalse(f.exists());

    cppm = new CreatePPM(cblockpix);
    cppm.save("res/testImages/colorBlock.ppm");
    f = new File("res/testImages/colorBlock.ppm");
    assertTrue(f.exists());
    f = new File("res/testImages/colorBlock");
    assertFalse(f.exists());
  }
}