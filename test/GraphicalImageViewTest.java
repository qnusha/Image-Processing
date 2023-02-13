import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import controller.GraphicalImageController;
import model.Pixel;
import model.SimpleImage;
import model.SingleImageManager;
import view.GraphicalImageView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * Tests the GUI view for GraphicalImageView class.
 */
public class GraphicalImageViewTest {
  private SingleImageManager model;
  private GraphicalImageController controller;
  private GraphicalImageView view;

  @Test
  public void invalidInit() throws IllegalArgumentException {
    try {
      model = new SingleImageManager();
      controller = new GraphicalImageController(model);
      view = new GraphicalImageView(null, model);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      assertNotNull(controller);
    }
  }

  @Test
  public void validInit() throws IllegalArgumentException {
    try {
      model = new SingleImageManager();
      controller = new GraphicalImageController(model);
      view = new GraphicalImageView(controller, null);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      assertNotNull(controller);
    }
  }

  @Before
  public void resetTestMaterials() {
    Pixel redp = new Pixel(255, 0, 0);
    Pixel orangep = new Pixel(255, 128, 0);
    Pixel pinkp = new Pixel(255, 128, 128);
    Pixel yellowp = new Pixel(255, 225, 0);
    SimpleImage sunsetpix = new SimpleImage(new Pixel[][]{
            {redp, redp, orangep, orangep},
            {redp, redp, orangep, orangep},
            {pinkp, pinkp, yellowp, yellowp},
            {pinkp, pinkp, yellowp, yellowp}});
    model = new SingleImageManager();
    model.add("img", sunsetpix);
    controller = new GraphicalImageController(model);
    view = new GraphicalImageView(controller, model);
  }

  @Test
  public void testSendMessage() throws IOException {
    view.sendMessage("nineteen dollar fortnite card");
    assertEquals("", controller.toString());
  }

  @Test
  public void testUpdateImage() {
    Pixel redp = new Pixel(255, 0, 0);
    Pixel orangep = new Pixel(255, 128, 0);
    Pixel pinkp = new Pixel(0, 128, 128);
    Pixel yellowp = new Pixel(255, 225, 0);
    SimpleImage newsunsetpix = new SimpleImage(new Pixel[][]{
            {redp, redp, orangep, orangep},
            {redp, redp, orangep, orangep},
            {pinkp, pinkp, yellowp, yellowp},
            {pinkp, pinkp, yellowp, yellowp}});
    SingleImageManager newModel = new SingleImageManager();
    newModel.add("img", newsunsetpix);
    GraphicalImageView newView = new GraphicalImageView(new GraphicalImageController(model), model);
    assertNotEquals(view.updateImage(), newView.updateImage());
  }
}