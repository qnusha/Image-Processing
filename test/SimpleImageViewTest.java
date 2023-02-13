import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import view.ImageView;
import view.SimpleImageView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * Testing class for SimpleImageView.
 */
public class SimpleImageViewTest {
  private StringBuilder output;
  private ImageView view;

  @Test
  public void invalidInit() throws IllegalArgumentException {
    try {
      output = new StringBuilder();
      view = new SimpleImageView(null);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      assertNotNull(output);
    }
  }

  @Test
  public void validInit() throws IllegalArgumentException {
    output = new StringBuilder();
    view = new SimpleImageView(output);
    assertNotNull(output);
  }

  @Before
  public void resetTestMaterials() {
    output = new StringBuilder();
    view = new SimpleImageView(output);
  }

  @Test
  public void testSendMessage() throws IOException {
    view.sendMessage("nineteen dollar fortnite card");
    assertEquals("nineteen dollar fortnite card\n", output.toString());
  }

  @Test(expected = IOException.class)
  public void testIOError() throws IOException {
    BadOutput output1 = new BadOutput();
    view = new SimpleImageView(output1);
    view.sendMessage("nineteen dollar fortnite card");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullError() throws IOException {
    view = new SimpleImageView(null);
    view.sendMessage("nineteen dollar fortnite card");
    assertEquals("nineteen dollar fortnite card\n", output.toString());
  }
}