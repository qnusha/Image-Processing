import org.junit.Before;
import org.junit.Test;

import model.Pixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Testing class for Pixel.
 */
public class PixelTest {
  private Pixel redP;
  private Pixel otherRedP;
  private Pixel orangeP;
  private Pixel pinkP;
  private Pixel yellowP;

  @Before
  public void init() {
    redP = new Pixel(255, 0, 0);
    otherRedP = new Pixel(255, 0, 0);
    orangeP = new Pixel(250, 128, 10);
    pinkP = new Pixel(245, 118, 118);
    yellowP = new Pixel(225, 225, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPixelValue() {
    redP = new Pixel(69, 420, -8);
  }

  @Test
  public void testGetRed() {
    assertEquals(255, redP.getRed());
    assertEquals(255, otherRedP.getRed());
    assertEquals(250, orangeP.getRed());
    assertEquals(245, pinkP.getRed());
    assertEquals(225, yellowP.getRed());
  }

  @Test
  public void testGetGreen() {
    assertEquals(0, redP.getGreen());
    assertEquals(0, otherRedP.getGreen());
    assertEquals(128, orangeP.getGreen());
    assertEquals(118, pinkP.getGreen());
    assertEquals(225, yellowP.getGreen());
  }

  @Test
  public void testGetBlue() {
    assertEquals(0, redP.getBlue());
    assertEquals(0, otherRedP.getBlue());
    assertEquals(10, orangeP.getBlue());
    assertEquals(118, pinkP.getBlue());
    assertEquals(3, yellowP.getBlue());
  }

  @Test
  public void testEquals() {
    assertEquals(otherRedP, redP);
    assertNotEquals(otherRedP, orangeP);
    assertNotEquals(otherRedP, pinkP);
    assertNotEquals(otherRedP, yellowP);
  }

  @Test
  public void testToString() {
    assertEquals("Pixel[R255 G0 B0]", redP.toString());
    assertEquals("Pixel[R255 G0 B0]", otherRedP.toString());
    assertEquals("Pixel[R250 G128 B10]", orangeP.toString());
    assertEquals("Pixel[R245 G118 B118]", pinkP.toString());
    assertEquals("Pixel[R225 G225 B3]", yellowP.toString());
  }

  @Test
  public void testHashCode() {
    assertEquals(274846, redP.hashCode());
  }
}