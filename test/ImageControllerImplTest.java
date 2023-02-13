import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import controller.ImageController;
import controller.ImageControllerImpl;
import model.SimpleImageManager;
import view.ImageView;
import view.SimpleImageView;

import static org.junit.Assert.assertEquals;

/**
 * Testing class for ImageControllerImpl.
 */
public class ImageControllerImplTest {
  private StringBuilder output;
  private SimpleImageManager model;
  private ImageView view;
  private ImageController controller;
  private String[] outputs;

  @Before
  public void resetTestMaterials() {
    output = new StringBuilder();
    model = new SimpleImageManager();
    view = new SimpleImageView(output);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidInit() throws IllegalArgumentException {
    this.controller = new ImageControllerImpl(null, null, null);
  }

  @Test
  public void testHelpAndQuit() {
    this.controller = new ImageControllerImpl(this.model, this.view,
            new StringReader("help\n"
                    + "quit\n"));
    this.controller.imageOperations();
    assertEquals("SUPPORTED OPERATIONS:\n" +
            "\"load <filepath> <output-name>\"\n" +
            "\"save <filepath> <image-name>\"\n" +
            "\"brighten <increment> <image-name> <output-name>\"\n" +
            "\"vertical-flip <image-name> <output-name>\"\n" +
            "\"horizontal-flip <image-name> <output-name>\"\n" +
            "\"sepia-tone <image-name> <output-name>\"\n" +
            "\"greyscale <image-name> <output-name>\"\n" +
            "\"blur <image-name> <output-name>\"\n" +
            "\"sharpen <image-name> <output-name>\"\n" +
            "\"red-component <image-name> <output-name>\"\n" +
            "\"green-component <image-name> <output-name>\"\n" +
            "\"blue-component <image-name> <output-name>\"\n" +
            "\"value-component <image-name> <output-name>\"\n" +
            "\"luma-component <image-name> <output-name>\"\n" +
            "\"intensity-component <image-name> <output-name>\"\n" +
            "\"help\"\n" +
            "\"quit\"\n" +
            "Shutting down.\n", output.toString());
  }

  @Test
  public void testLoadImage() {
    resetTestMaterials();
    this.controller = new ImageControllerImpl(this.model, this.view,
            new StringReader("load res/testImages/tester.ppm tester\n"
                    + "quit\n"));
    this.controller.imageOperations();
    this.outputs = output.toString().split("\n");
    assertEquals("Loaded Image as \"tester\".", outputs[0]);
    assertEquals("Shutting down.", outputs[1]);

    resetTestMaterials();
    this.controller = new ImageControllerImpl(this.model, this.view,
            new StringReader("load res/testImages/pinwheel.png tester\n"
                    + "quit\n"));
    this.controller.imageOperations();
    this.outputs = output.toString().split("\n");
    assertEquals("Loaded Image as \"tester\".", outputs[0]);
    assertEquals("Shutting down.", outputs[1]);

    resetTestMaterials();
    this.controller = new ImageControllerImpl(this.model, this.view,
            new StringReader("load res/testImages/pinwheel.jpeg tester\n"
                    + "quit\n"));
    this.controller.imageOperations();
    this.outputs = output.toString().split("\n");
    assertEquals("Loaded Image as \"tester\".", outputs[0]);
    assertEquals("Shutting down.", outputs[1]);

    resetTestMaterials();
    this.controller = new ImageControllerImpl(this.model, this.view,
            new StringReader("load res/testImages/pinwheel.bmp tester\n"
                    + "quit\n"));
    this.controller.imageOperations();
    this.outputs = output.toString().split("\n");
    assertEquals("Loaded Image as \"tester\".", outputs[0]);
    assertEquals("Shutting down.", outputs[1]);

  }

  @Test
  public void testBrightenImage() {
    this.controller = new ImageControllerImpl(this.model, this.view,
            new StringReader("load res/testImages/tester.ppm tester\n"
                    + "brighten 50 tester tester-a\n"
                    + "quit\n"));
    this.controller.imageOperations();
    this.outputs = output.toString().split("\n");
    assertEquals("Loaded Image as \"tester\".", outputs[0]);
    assertEquals("Brightened Image as \"tester-a\".", outputs[1]);
    assertEquals("Shutting down.", outputs[2]);
  }

  @Test
  public void testVertFlipImage() {
    this.controller = new ImageControllerImpl(this.model, this.view,
            new StringReader("load res/testImages/tester.ppm tester\n"
                    + "vertical-flip tester tester-a\n"
                    + "quit\n"));
    this.controller.imageOperations();
    this.outputs = output.toString().split("\n");
    assertEquals("Loaded Image as \"tester\".", outputs[0]);
    assertEquals("Flipped Image as \"tester-a\".", outputs[1]);
    assertEquals("Shutting down.", outputs[2]);
  }

  @Test
  public void testHorizFlipImage() {
    this.controller = new ImageControllerImpl(this.model, this.view,
            new StringReader("load res/testImages/tester.ppm tester\n"
                    + "horizontal-flip tester tester-a\n"
                    + "quit\n"));
    this.controller.imageOperations();
    this.outputs = output.toString().split("\n");
    assertEquals("Loaded Image as \"tester\".", outputs[0]);
    assertEquals("Flipped Image as \"tester-a\".", outputs[1]);
    assertEquals("Shutting down.", outputs[2]);
  }

  @Test
  public void testValCompImage() {
    this.controller = new ImageControllerImpl(this.model, this.view,
            new StringReader("load res/testImages/tester.ppm tester\n"
                    + "value-component tester tester-a\n"
                    + "quit\n"));
    this.controller.imageOperations();
    this.outputs = output.toString().split("\n");
    assertEquals("Loaded Image as \"tester\".", outputs[0]);
    assertEquals("Took Component of Image as \"tester-a\".", outputs[1]);
    assertEquals("Shutting down.", outputs[2]);
  }

  @Test
  public void testRCompImage() {
    this.controller = new ImageControllerImpl(this.model, this.view,
            new StringReader("load res/testImages/tester.ppm tester\n"
                    + "red-component tester tester-a\n"
                    + "quit\n"));
    this.controller.imageOperations();
    this.outputs = output.toString().split("\n");
    assertEquals("Loaded Image as \"tester\".", outputs[0]);
    assertEquals("Took Component of Image as \"tester-a\".", outputs[1]);
    assertEquals("Shutting down.", outputs[2]);
  }

  @Test
  public void testGCompImage() {
    this.controller = new ImageControllerImpl(this.model, this.view,
            new StringReader("load res/testImages/tester.ppm tester\n"
                    + "green-component tester tester-a\n"
                    + "quit\n"));
    this.controller.imageOperations();
    this.outputs = output.toString().split("\n");
    assertEquals("Loaded Image as \"tester\".", outputs[0]);
    assertEquals("Took Component of Image as \"tester-a\".", outputs[1]);
    assertEquals("Shutting down.", outputs[2]);
  }

  @Test
  public void testBCompImage() {
    this.controller = new ImageControllerImpl(this.model, this.view,
            new StringReader("load res/testImages/tester.ppm tester\n"
                    + "blue-component tester tester-a\n"
                    + "quit\n"));
    this.controller.imageOperations();
    this.outputs = output.toString().split("\n");
    assertEquals("Loaded Image as \"tester\".", outputs[0]);
    assertEquals("Took Component of Image as \"tester-a\".", outputs[1]);
    assertEquals("Shutting down.", outputs[2]);
  }

  @Test
  public void testLumaCompImage() {
    this.controller = new ImageControllerImpl(this.model, this.view,
            new StringReader("load res/testImages/tester.ppm tester\n"
                    + "luma-component tester tester-a\n"
                    + "quit\n"));
    this.controller.imageOperations();
    this.outputs = output.toString().split("\n");
    assertEquals("Loaded Image as \"tester\".", outputs[0]);
    assertEquals("Took Component of Image as \"tester-a\".", outputs[1]);
    assertEquals("Shutting down.", outputs[2]);
  }

  @Test
  public void testICompImage() {
    this.controller = new ImageControllerImpl(this.model, this.view,
            new StringReader("load res/testImages/tester.ppm tester\n"
                    + "intensity-component tester tester-a\n"
                    + "quit\n"));
    this.controller.imageOperations();
    this.outputs = output.toString().split("\n");
    assertEquals("Loaded Image as \"tester\".", outputs[0]);
    assertEquals("Took Component of Image as \"tester-a\".", outputs[1]);
    assertEquals("Shutting down.", outputs[2]);
  }

  @Test
  public void testSepiaImage() {
    this.controller = new ImageControllerImpl(this.model, this.view,
            new StringReader("load res/testImages/tester.ppm tester\n"
                    + "sepia-tone tester tester-a\n"
                    + "quit\n"));
    this.controller.imageOperations();
    this.outputs = output.toString().split("\n");
    assertEquals("Loaded Image as \"tester\".", outputs[0]);
    assertEquals("Saved Filtered Image as \"tester-a\".", outputs[1]);
    assertEquals("Shutting down.", outputs[2]);
  }

  @Test
  public void testGreyscaleImage() {
    this.controller = new ImageControllerImpl(this.model, this.view,
            new StringReader("load res/testImages/tester.ppm tester\n"
                    + "greyscale tester tester-a\n"
                    + "quit\n"));
    this.controller.imageOperations();
    this.outputs = output.toString().split("\n");
    assertEquals("Loaded Image as \"tester\".", outputs[0]);
    assertEquals("Saved Filtered Image as \"tester-a\".", outputs[1]);
    assertEquals("Shutting down.", outputs[2]);
  }

  @Test
  public void testBlurImage() {
    this.controller = new ImageControllerImpl(this.model, this.view,
            new StringReader("load res/testImages/tester.ppm tester\n"
                    + "blur tester tester-a\n"
                    + "quit\n"));
    this.controller.imageOperations();
    this.outputs = output.toString().split("\n");
    assertEquals("Loaded Image as \"tester\".", outputs[0]);
    assertEquals("Saved Blurred Image as \"tester-a\".", outputs[1]);
    assertEquals("Shutting down.", outputs[2]);
  }

  @Test
  public void testSharpenImage() {
    this.controller = new ImageControllerImpl(this.model, this.view,
            new StringReader("load res/testImages/tester.ppm tester\n"
                    + "sharpen tester tester-a\n"
                    + "quit\n"));
    this.controller.imageOperations();
    this.outputs = output.toString().split("\n");
    assertEquals("Loaded Image as \"tester\".", outputs[0]);
    assertEquals("Saved Sharpened Image as \"tester-a\".", outputs[1]);
    assertEquals("Shutting down.", outputs[2]);
  }

  @Test
  public void testSaveImage() {
    this.controller = new ImageControllerImpl(this.model, this.view,
            new StringReader("load res/testImages/tester.ppm tester\n"
                    + "horizontal-flip tester tester-a\n"
                    + "save res/testImages/testerFlipped.ppm tester-a\n"
                    + "quit\n"));
    this.controller.imageOperations();
    this.outputs = output.toString().split("\n");
    assertEquals("Loaded Image as \"tester\".", outputs[0]);
    assertEquals("Flipped Image as \"tester-a\".", outputs[1]);
    assertEquals("Saved Image into \"res/testimages/testerflipped.ppm\".", outputs[2]);
    assertEquals("Shutting down.", outputs[3]);
  }

  @Test
  public void testImproperArgument() {
    this.controller = new ImageControllerImpl(this.model, this.view,
            new StringReader("load desktop/image.ppm\n"
                    + "quit\n"));
    this.controller.imageOperations();
    this.outputs = output.toString().split("\n");
    assertEquals("SYNTAX ERROR: try \"help\" to view correct syntax", outputs[0]);
    assertEquals("Shutting down.", outputs[1]);
  }

  @Test
  public void testInvalidArgument() {
    this.controller = new ImageControllerImpl(this.model, this.view,
            new StringReader("load res/testImages/tester.ppm tester\n"
                    + "filter-by-luma tester tester-a\n"
                    + "quit\n"));
    this.controller.imageOperations();
    this.outputs = output.toString().split("\n");
    assertEquals("Loaded Image as \"tester\".", outputs[0]);
    assertEquals("INVALID OPERATION: try \"help\" to view supported options", outputs[1]);
    assertEquals("Shutting down.", outputs[2]);
  }

  @Test
  public void testUnloadedImage() {
    this.controller = new ImageControllerImpl(this.model, this.view,
            new StringReader("blue-component tester tester-a\n"
                    + "quit\n"));
    this.controller.imageOperations();
    this.outputs = output.toString().split("\n");
    assertEquals("REFERENCE ERROR: could not find requested image", outputs[0]);
    assertEquals("Shutting down.", outputs[1]);
  }

  @Test
  public void testNonexistentImage() {
    this.controller = new ImageControllerImpl(this.model, this.view,
            new StringReader("load just-go-grab-it-from-my-desktop tester\n"
                    + "quit\n"));
    this.controller.imageOperations();
    this.outputs = output.toString().split("\n");
    assertEquals("Not an ImageIO or file not found", outputs[0]);
    assertEquals("Shutting down.", outputs[1]);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullArgument() {
    this.controller = new ImageControllerImpl(null, this.view,
            new StringReader("load res/testImages/tester.ppm tester\n"
                    + "quit\n"));
    this.controller.imageOperations();
    this.outputs = output.toString().split("\n");
    assertEquals("PATH ERROR: could not find requested image", outputs[0]);
    assertEquals("Shutting down.", outputs[1]);
  }
}