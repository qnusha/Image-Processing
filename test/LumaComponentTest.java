import org.junit.Before;
import org.junit.Test;

import model.Image;
import model.Pixel;
import model.SimpleImage;
import model.ops.LumaComponent;
import model.ops.ImageOperation;

import static org.junit.Assert.assertArrayEquals;

/**
 * Testing Class for LumaComponent.
 */
public class LumaComponentTest {
  private ImageOperation op = new LumaComponent();
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

    Pixel redpalt = new Pixel(54, 54, 54);
    Pixel orangepalt = new Pixel(145, 145, 145);
    Pixel pinkpalt = new Pixel(154, 154, 154);
    Pixel yellowpalt = new Pixel(214, 214, 214);
    Pixel bluepalt = new Pixel(123, 123, 123);
    Pixel whitepalt = new Pixel(230, 230, 230);

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