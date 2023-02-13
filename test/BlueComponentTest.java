import org.junit.Before;
import org.junit.Test;

import model.Image;
import model.Pixel;
import model.SimpleImage;
import model.ops.BlueComponent;
import model.ops.ImageOperation;

import static org.junit.Assert.assertArrayEquals;

/**
 * Testing Class for BlueComponent.
 */
public class BlueComponentTest {
  private ImageOperation op = new BlueComponent();
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

    Pixel redpalt = new Pixel(0, 0, 0);
    Pixel orangepalt = new Pixel(0, 0, 0);
    Pixel pinkpalt = new Pixel(128, 128, 128);
    Pixel yellowpalt = new Pixel(0, 0, 0);
    Pixel bluepalt = new Pixel(225, 225, 225);
    Pixel whitepalt = new Pixel(225, 225, 225);

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
  }

  @Test
  public void testImageOp() {
    assertArrayEquals(sunsetpixalt, op.apply(sunsetpix).getCopyPixels());
    assertArrayEquals(cblockpixalt, op.apply(cblockpix).getCopyPixels());
  }
}