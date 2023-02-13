package controller;

import java.io.IOException;
import java.util.Scanner;

import model.ImageManager;
import model.ops.BlueComponent;
import model.ops.Brighten;
import model.ops.GaussianBlur;
import model.ops.GreenComponent;
import model.ops.HorizontalFlip;
import model.ops.IntensityComponent;
import model.ops.LumaComponent;
import model.ops.RedComponent;
import model.ops.SepiaFilter;
import model.ops.Sharpen;
import model.ops.ValueComponent;
import model.ops.VerticalFlip;
import view.ImageView;

/**
 * Parses user inputs and passes them on to the model.
 * Also sends feedback to the view.
 */
public class ImageControllerImpl implements ImageController {
  private final ImageManager model;
  private final ImageView view;
  private final Readable in;

  /**
   * Creates a new ImageController.
   *
   * @param model The model this ImageController is connected to.
   * @param view  The view this ImageController is connected to.
   * @param in    Where this ImageController gets its inputs from.
   * @throws IllegalArgumentException Only throws when given null.
   */
  public ImageControllerImpl(ImageManager model, ImageView view, Readable in)
          throws IllegalArgumentException {
    if (model == null || view == null || in == null) {
      throw new IllegalArgumentException("Null argument");
    }
    this.model = model;
    this.view = view;
    this.in = in;
  }

  /**
   * Parses inputs it receives, from the users, calling the appropriate operations within the model
   * and providing feedback through the view. Runs until it receives the "quit" command.
   *
   * @throws IllegalStateException when input or output is not transmittable.
   */
  @Override
  public void imageOperations() throws IllegalStateException {
    try {
      Scanner scan = new Scanner(this.in);
      String[] nextArg;
      boolean running = true;
      while (running) {
        if (scan.hasNextLine()) {
          nextArg = scan.nextLine().trim().toLowerCase().split(" ");
          if (nextArg.length > 0) {
            try {
              switch (nextArg[0]) {
                case "load":
                  if (nextArg[1].contains(".ppm")) {
                    model.add(nextArg[2], new PPMUtil().read(nextArg[1]));
                    view.sendMessage("Loaded Image as \"" + nextArg[2] + "\".");
                  } else {
                    try {
                      model.add(nextArg[2], new ImageIOUtil().read(nextArg[1]));
                      view.sendMessage("Loaded Image as \"" + nextArg[2] + "\".");
                    } catch (IOException e) {
                      view.sendMessage("Not an ImageIO or file not found");
                    }
                  }
                  break;
                case "save":
                  try {
                    String filename = nextArg[2];
                    new CreatePPM(model.fetch(filename)).save(nextArg[1]);
                    view.sendMessage("Saved Image into \"" + nextArg[1] + "\".");
                  } catch (RuntimeException e) {
                    view.sendMessage("Error while saving image.");
                  }
                  break;
                case "brighten":
                  model.runOp(new Brighten(Integer.parseInt(nextArg[1])), nextArg[2], nextArg[3]);
                  view.sendMessage("Brightened Image as \"" + nextArg[3] + "\".");
                  break;
                case "vertical-flip":
                  model.runOp(new VerticalFlip(), nextArg[1], nextArg[2]);
                  view.sendMessage("Flipped Image as \"" + nextArg[2] + "\".");
                  break;
                case "horizontal-flip":
                  model.runOp(new HorizontalFlip(), nextArg[1], nextArg[2]);
                  view.sendMessage("Flipped Image as \"" + nextArg[2] + "\".");
                  break;
                case "value-component":
                  model.runOp(new ValueComponent(), nextArg[1], nextArg[2]);
                  view.sendMessage("Took Component of Image as \"" + nextArg[2] + "\".");
                  break;
                case "red-component":
                  model.runOp(new RedComponent(), nextArg[1], nextArg[2]);
                  view.sendMessage("Took Component of Image as \"" + nextArg[2] + "\".");
                  break;
                case "blue-component":
                  model.runOp(new BlueComponent(), nextArg[1], nextArg[2]);
                  view.sendMessage("Took Component of Image as \"" + nextArg[2] + "\".");
                  break;
                case "green-component":
                  model.runOp(new GreenComponent(), nextArg[1], nextArg[2]);
                  view.sendMessage("Took Component of Image as \"" + nextArg[2] + "\".");
                  break;
                case "luma-component":
                  model.runOp(new LumaComponent(), nextArg[1], nextArg[2]);
                  view.sendMessage("Took Component of Image as \"" + nextArg[2] + "\".");
                  break;
                case "greyscale":
                  model.runOp(new LumaComponent(), nextArg[1], nextArg[2]);
                  view.sendMessage("Saved Filtered Image as \"" + nextArg[2] + "\".");
                  break;
                case "sepia-tone":
                  model.runOp(new SepiaFilter(), nextArg[1], nextArg[2]);
                  view.sendMessage("Saved Filtered Image as \"" + nextArg[2] + "\".");
                  break;
                case "intensity-component":
                  model.runOp(new IntensityComponent(), nextArg[1], nextArg[2]);
                  view.sendMessage("Took Component of Image as \"" + nextArg[2] + "\".");
                  break;
                case "blur":
                  model.runOp(new GaussianBlur(), nextArg[1], nextArg[2]);
                  view.sendMessage("Saved Blurred Image as \"" + nextArg[2] + "\".");
                  break;
                case "sharpen":
                  model.runOp(new Sharpen(), nextArg[1], nextArg[2]);
                  view.sendMessage("Saved Sharpened Image as \"" + nextArg[2] + "\".");
                  break;
                case "help":
                  view.sendMessage("SUPPORTED OPERATIONS:\n"
                          + "\"load <filepath> <output-name>\"\n"
                          + "\"save <filepath> <image-name>\"\n"
                          + "\"brighten <increment> <image-name> <output-name>\"\n"
                          + "\"vertical-flip <image-name> <output-name>\"\n"
                          + "\"horizontal-flip <image-name> <output-name>\"\n"
                          + "\"sepia-tone <image-name> <output-name>\"\n"
                          + "\"greyscale <image-name> <output-name>\"\n"
                          + "\"blur <image-name> <output-name>\"\n"
                          + "\"sharpen <image-name> <output-name>\"\n"
                          + "\"red-component <image-name> <output-name>\"\n"
                          + "\"green-component <image-name> <output-name>\"\n"
                          + "\"blue-component <image-name> <output-name>\"\n"
                          + "\"value-component <image-name> <output-name>\"\n"
                          + "\"luma-component <image-name> <output-name>\"\n"
                          + "\"intensity-component <image-name> <output-name>\"\n"
                          + "\"help\"\n"
                          + "\"quit\"");
                  break;
                case "quit":
                  running = false;
                  view.sendMessage("Shutting down.");
                  break;
                default:
                  view.sendMessage("INVALID OPERATION: try \"help\" to view supported options");
                  break;
              }
            } catch (ArrayIndexOutOfBoundsException a) {
              view.sendMessage("SYNTAX ERROR: try \"help\" to view correct syntax");
            } catch (NullPointerException n) {
              view.sendMessage("REFERENCE ERROR: could not find requested image");
            } catch (IOException e) {
              view.sendMessage("PATH ERROR: could not find requested image");
            }
          }
        }
      }
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }
}


