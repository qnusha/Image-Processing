package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Image;
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
import view.GraphicalImageView;

/**
 * A graphical image controller.
 */
public class GraphicalImageController implements ImageController, ActionListener {
  public int brightnessAmt;
  public GraphicalImageView view;
  private final ImageManager model;

  /**
   * Constructor.
   * @param model The Model.
   */
  public GraphicalImageController(ImageManager model) {
    if (model == null) {
      throw new IllegalArgumentException("Null argument");
    }
    this.model = model;
    this.brightnessAmt = 0;
  }

  /**
   * Manages all the operations performed on the Image and ImageView.
   */
  @Override
  public void imageOperations() throws IllegalStateException {
    int i = 0;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Open file":
        final JFileChooser fchooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg", "ppm", "bmp", "png");
        fchooser.setFileFilter(filter);
        int retvalue = fchooser.showOpenDialog(this.view);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          try {
            Image img = new ImageIOUtil().read(f.getAbsolutePath());
            model.add("new", img);
            this.view.setModel(model);
            this.view.updateImage();
          } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
          }
        }
        break;
      case "Save file":
        final JFileChooser fch = new JFileChooser(".");
        int value = fch.showSaveDialog(this.view);
        if (value == JFileChooser.APPROVE_OPTION) {
          File f = fch.getSelectedFile();
          new CreateImageIO(model.fetch("img")).save(f.getAbsolutePath());
        }
        break;
      case "Brighten/Darken Image":
        this.model.runOp(new Brighten(this.brightnessAmt), "foo", "foo");
        this.view.setModel(model);
        this.view.updateImage();
        break;
      case "Red Component":
        this.model.runOp(new RedComponent(), "foo", "foo");
        this.view.setModel(model);
        this.view.updateImage();
        break;
      case "Blue Component":
        this.model.runOp(new BlueComponent(), "foo", "foo");
        this.view.setModel(model);
        this.view.updateImage();
        break;
      case "Green Component":
        this.model.runOp(new GreenComponent(), "foo", "foo");
        this.view.setModel(model);
        this.view.updateImage();
        break;
      case "Gaussian Blur":
        this.model.runOp(new GaussianBlur(), "foo", "foo");
        this.view.setModel(model);
        this.view.updateImage();
        break;
      case "Sharpen":
        this.model.runOp(new Sharpen(), "foo", "foo");
        this.view.setModel(model);
        this.view.updateImage();
        break;
      case "Flip Vertical":
        this.model.runOp(new VerticalFlip(), "foo", "foo");
        this.view.setModel(model);
        this.view.updateImage();
        break;
      case "Flip Horizontal":
        this.model.runOp(new HorizontalFlip(), "foo", "foo");
        this.view.setModel(model);
        this.view.updateImage();
        break;
      case "Intensity Component":
        this.model.runOp(new IntensityComponent(), "foo", "foo");
        this.view.setModel(model);
        this.view.updateImage();
        break;
      case "Value Component":
        this.model.runOp(new ValueComponent(), "foo", "foo");
        this.view.setModel(model);
        this.view.updateImage();
        break;
      case "Luma Component":
        this.model.runOp(new LumaComponent(), "foo", "foo");
        this.view.setModel(model);
        this.view.updateImage();
        break;
      case "Sepia Filter":
        this.model.runOp(new SepiaFilter(), "foo", "foo");
        this.view.setModel(model);
        this.view.updateImage();
        break;
      default:
        break;
    }
    this.view.updateImage();
  }
}