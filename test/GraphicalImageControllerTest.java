import org.junit.Before;
import org.junit.Test;

import java.awt.event.ActionEvent;

import static org.junit.Assert.assertArrayEquals;

import controller.GraphicalImageController;
import model.Pixel;
import model.SimpleImage;
import model.SingleImageManager;
import view.GraphicalImageView;

/**
 * Tests the GUI controller.
 */
public class GraphicalImageControllerTest {
  private SingleImageManager model;
  private GraphicalImageController controller;
  private GraphicalImageView view;

  @Before
  public void initialize() {
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
    this.controller.brightnessAmt = -55;
    this.controller.view = this.view;
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullParam() {
    this.controller = new GraphicalImageController(null);
  }

  @Test
  public void testResponse() {
    Pixel nredp = new Pixel(200, 0, 0);
    Pixel norangep = new Pixel(200, 73, 0);
    Pixel npinkp = new Pixel(200, 73, 73);
    Pixel nyellowp = new Pixel(200, 170, 0);
    this.controller.actionPerformed(new ActionEvent(this, 0, "Brighten/Darken Image"));
    assertArrayEquals(this.model.fetch("default").getCopyPixels(),
            new Pixel[][]{
                    {nredp, nredp, norangep, norangep},
                    {nredp, nredp, norangep, norangep},
                    {npinkp, npinkp, nyellowp, nyellowp},
                    {npinkp, npinkp, nyellowp, nyellowp}});
    initialize();
    Pixel redp = new Pixel(255, 0, 0);
    Pixel orangep = new Pixel(255, 128, 0);
    Pixel pinkp = new Pixel(255, 128, 128);
    Pixel yellowp = new Pixel(255, 225, 0);
    controller.actionPerformed(new ActionEvent(this, 1, "Flip Horizontal"));
    assertArrayEquals(this.model.fetch("default").getCopyPixels(),
            new Pixel[][]{
                    {orangep, orangep, redp, redp},
                    {orangep, orangep, redp, redp},
                    {yellowp, yellowp, pinkp, pinkp},
                    {yellowp, yellowp, pinkp, pinkp}});

    assertArrayEquals(this.view.getModel().fetch("default").getCopyPixels(),
            new Pixel[][]{
                    {orangep, orangep, redp, redp},
                    {orangep, orangep, redp, redp},
                    {yellowp, yellowp, pinkp, pinkp},
                    {yellowp, yellowp, pinkp, pinkp}});
  }
}
