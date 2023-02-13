import org.junit.Before;
import org.junit.Test;

import model.Image;
import model.ImageManager;
import model.Pixel;
import model.SimpleImage;
import model.SingleImageManager;
import model.ops.Brighten;

import static org.junit.Assert.assertArrayEquals;

/**
 * Testing class for SingleImageManager.
 */
public class SingleImageManagerTest {
  private ImageManager model;
  private Image sunsetpix;
  private Image cblockpix;
  private Image sunsetpixbrightened;

  @Before
  public void init() {
    Pixel redp = new Pixel(255, 0, 0);
    Pixel orangep = new Pixel(255, 128, 0);
    Pixel pinkp = new Pixel(255, 128, 128);
    Pixel yellowp = new Pixel(255, 225, 0);
    Pixel bluep = new Pixel(0, 150, 225);
    Pixel whitep = new Pixel(255, 225, 225);

    Pixel redpalt = new Pixel(255, 52, 52);
    Pixel orangepalt = new Pixel(255, 180, 52);
    Pixel pinkpalt = new Pixel(255, 180, 180);
    Pixel yellowpalt = new Pixel(255, 255, 52);

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
    sunsetpixbrightened = new SimpleImage(new Pixel[][]{
            {redpalt, redpalt, orangepalt, orangepalt},
            {redpalt, redpalt, orangepalt, orangepalt},
            {pinkpalt, pinkpalt, yellowpalt, yellowpalt},
            {pinkpalt, pinkpalt, yellowpalt, yellowpalt}});

    this.model = new SingleImageManager();
  }

  @Test
  public void testAddAndFetchImages() {
    this.model.add("sunset", sunsetpix);
    this.model.add("colorbox", cblockpix);
    assertArrayEquals(sunsetpix.getCopyPixels(), this.model.fetch("sunset").getCopyPixels());
    assertArrayEquals(cblockpix.getCopyPixels(), this.model.fetch("colorbox").getCopyPixels());
  }

  @Test
  public void testRunOp() {
    this.model.add("sunset", sunsetpix);
    this.model.runOp(new Brighten(52), "sunset", "brighterSunset");
    assertArrayEquals(sunsetpix.getCopyPixels(), this.model.fetch("sunset").getCopyPixels());
    assertArrayEquals(sunsetpixbrightened.getCopyPixels(),
            this.model.fetch("brighterSunset").getCopyPixels());
  }
}