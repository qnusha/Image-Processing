import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

import javax.swing.JFrame;

import controller.GraphicalImageController;
import controller.ImageControllerImpl;
import model.ImageManager;
import model.Pixel;
import model.SimpleImage;
import model.SimpleImageManager;
import model.SingleImageManager;
import view.GraphicalImageView;
import view.ImageView;
import view.SimpleImageView;

/**
 * Main class for ImageProc.
 */
public class Main {
  private static SimpleImage defaultIMG() {
    Pixel whitePX = new Pixel(255, 255, 255);
    Pixel redPX = new Pixel(255, 255, 255);
    Pixel bluePX = new Pixel(255, 255, 255);
    Pixel greenPX = new Pixel(255, 255, 255);
    return new SimpleImage(new Pixel[][]{
            {whitePX, whitePX, whitePX, whitePX, whitePX},
            {whitePX, redPX, bluePX, greenPX, whitePX},
            {whitePX, redPX, bluePX, greenPX, whitePX},
            {whitePX, redPX, bluePX, greenPX, whitePX},
            {whitePX, whitePX, whitePX, whitePX, whitePX}});
  }

  /**
   * Runs the program.
   *
   * @param args The arguments that the program was run with.
   */
  public static void main(String[] args) throws IOException {
    if (args.length > 0) {
      Readable inputSrc;
      File src = new File(args[1]);
      inputSrc = new FileReader(src);
      if (!src.exists()) {
        System.out.println("ERROR: Invalid Filepath");
        System.exit(0);
      } else {
        inputSrc = new InputStreamReader(System.in);
      }
      ImageManager model = new SimpleImageManager();
      ImageView view = new SimpleImageView(System.out);
      new ImageControllerImpl(model, view, inputSrc).imageOperations();
    } else {
      ImageManager model = new SingleImageManager();
      model.add("default", defaultIMG());
      GraphicalImageController controller = new GraphicalImageController(model);
      StringReader inputSrc = new StringReader("");
      GraphicalImageView frame = new GraphicalImageView(controller, model);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
    }
  }
}