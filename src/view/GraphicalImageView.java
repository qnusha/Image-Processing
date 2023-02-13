package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.GraphicalImageController;
import model.ImageManager;
import model.Pixel;
import model.histogram.Histogram;
import model.histogram.RedHistogram;
import model.ops.BlueComponent;
import model.ops.GreenComponent;
import model.ops.IntensityComponent;

/**
 * A Graphical Image View.
 */
public class GraphicalImageView extends JFrame implements ImageView {
  protected ImageManager model; // access read-only features
  private JPanel mainPanel;
  private JPanel imagePanel;
  private JPanel hPanel;
  private ImageIcon imageIcon;
  private JLabel imageLabel;
  private GraphicalImageController controller;

  /**
   * Constructor.
   * @param controller A Controller.
   * @param model A Model.
   */
  public GraphicalImageView(GraphicalImageController controller, ImageManager model) {
    super();
    if (controller == null || model == null) {
      throw new IllegalArgumentException("Null argument");
    }
    this.model = model;
    this.controller = controller;
    this.controller.view = this;
    setTitle("ImageProc");
    setSize(1500, 1000);

    mainPanel = new JPanel();
    mainPanel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 0;
    c.gridwidth = 3;
    c.gridheight = 2;
    imagePanel = imagePanel();
    mainPanel.add(imagePanel, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 3;
    c.gridy = 0;
    c.gridwidth = 2;
    c.gridheight = 2;
    JPanel oPanel = opsPanel();
    mainPanel.add(oPanel, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 2;
    c.gridwidth = 3;
    c.gridheight = 1;
    mainPanel.add(setImage(), c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 3;
    c.gridy = 2;
    c.gridwidth = 2;
    c.gridheight = 1;
    hPanel = histoPanel();
    mainPanel.add(hPanel, c);
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);
    this.add(mainPanel);
  }

  private JPanel imagePanel() {
    imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createTitledBorder("Showing an image"));
    mainPanel.add(imagePanel);
    imageLabel = new JLabel();
    JScrollPane imageScrollPane = new JScrollPane(imageLabel);
    imageLabel.setIcon(imageIcon);
    imagePanel.add(imageScrollPane);
    return imagePanel;
  }

  private JPanel opsPanel() {
    JPanel opsPanel = new JPanel();
    opsPanel.setLayout(new BoxLayout(opsPanel, BoxLayout.Y_AXIS));
    JPanel brightenPanel = new JPanel();
    brightenPanel.setLayout(new BoxLayout(brightenPanel, BoxLayout.X_AXIS));
    JSlider brightnessSlider = new JSlider(-255, 255, this.controller.brightnessAmt);
    brightnessSlider.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        controller.brightnessAmt = brightnessSlider.getValue();
      }
    });
    brightenPanel.add(brightnessSlider);
    JButton applyButton = new JButton("Brighten/Darken Image");
    applyButton.setActionCommand("Brighten/Darken Image");
    applyButton.addActionListener(this.controller);
    brightenPanel.add(applyButton);
    opsPanel.add(brightenPanel);

    JPanel blurSharpenPanel = new JPanel();
    blurSharpenPanel.setLayout(new BoxLayout(blurSharpenPanel, BoxLayout.X_AXIS));
    JButton blurButton = new JButton("Blur Image");
    applyButton.setActionCommand("Gaussian Blur");
    applyButton.addActionListener(this.controller);
    blurSharpenPanel.add(blurButton);
    JButton sharpenButton = new JButton("Sharpen Image");
    applyButton.setActionCommand("Sharpen");
    applyButton.addActionListener(this.controller);
    blurSharpenPanel.add(sharpenButton);
    opsPanel.add(blurSharpenPanel);

    JPanel flipPanel = new JPanel();
    flipPanel.setLayout(new BoxLayout(flipPanel, BoxLayout.X_AXIS));
    JButton vertButton = new JButton("Flip Image Vertically");
    applyButton.setActionCommand("Flip Vertical");
    applyButton.addActionListener(this.controller);
    flipPanel.add(vertButton);
    JButton horizButton = new JButton("Flip Image Horizontally");
    applyButton.setActionCommand("Flip Horizontal");
    applyButton.addActionListener(this.controller);
    flipPanel.add(horizButton);
    opsPanel.add(flipPanel);

    JPanel recolorPanel = new JPanel();
    recolorPanel.setLayout(new BoxLayout(recolorPanel, BoxLayout.X_AXIS));
    JButton sepiaButton = new JButton("Apply Sepia Recolor");
    applyButton.setActionCommand("Sepia Filter");
    applyButton.addActionListener(this.controller);
    recolorPanel.add(sepiaButton);
    JButton greyscaleButton = new JButton("Apply Greyscale Recolor");
    applyButton.setActionCommand("Luma Component");
    applyButton.addActionListener(this.controller);
    recolorPanel.add(greyscaleButton);
    opsPanel.add(recolorPanel);

    JPanel componentPanelA = new JPanel();
    componentPanelA.setLayout(new BoxLayout(componentPanelA, BoxLayout.X_AXIS));
    JButton redButton = new JButton("Take Red Component");
    applyButton.setActionCommand("Red Component");
    applyButton.addActionListener(this.controller);
    componentPanelA.add(redButton);
    JButton greenButton = new JButton("Take Green Component");
    applyButton.setActionCommand("Green Component");
    applyButton.addActionListener(this.controller);
    componentPanelA.add(greenButton);
    componentPanelA.setLayout(new BoxLayout(componentPanelA, BoxLayout.X_AXIS));
    JButton blueButton = new JButton("Take Blue Component");
    applyButton.setActionCommand("Blue Component");
    applyButton.addActionListener(this.controller);
    componentPanelA.add(blueButton);
    opsPanel.add(componentPanelA);

    JPanel componentPanelB = new JPanel();
    JButton intensityButton = new JButton("Take Intensity Component");
    applyButton.setActionCommand("Intensity Component");
    applyButton.addActionListener(this.controller);
    componentPanelB.add(intensityButton);
    componentPanelB.setLayout(new BoxLayout(componentPanelB, BoxLayout.X_AXIS));
    JButton valueButton = new JButton("Take Max Value Component");
    applyButton.setActionCommand("Value Component");
    applyButton.addActionListener(this.controller);
    componentPanelB.add(valueButton);
    opsPanel.add(componentPanelB);
    return opsPanel;
  }

  public void setModel(ImageManager s) {
    this.model = s;
  }

  private JPanel histoPanel() {
    JPanel histoPanel = new JPanel();
    histoPanel.setLayout(new BoxLayout(histoPanel, BoxLayout.X_AXIS));

    JLabel redHisto = new JLabel();
    ImageIcon icn = new ImageIcon(histoToImg(new RedHistogram(model.fetch("img")),
            new Color(1.0f, 0.0f, 0.0f)));
    redHisto.setIcon(icn);
    histoPanel.add(redHisto);

    JLabel greenHisto = new JLabel();
    icn = new ImageIcon(histoToImg(new RedHistogram(new GreenComponent().apply(model.fetch("img"))),
            new Color(0.0f, 1.0f, 0.0f)));
    greenHisto.setIcon(icn);
    histoPanel.add(greenHisto);

    JLabel blueHisto = new JLabel();
    icn = new ImageIcon(histoToImg(new RedHistogram(new BlueComponent().apply(model.fetch("img"))),
            new Color(0.0f, 1.0f, 0.0f)));
    blueHisto.setIcon(icn);
    histoPanel.add(blueHisto);

    JLabel intHisto = new JLabel();
    Histogram lnLengthIsDumb = new RedHistogram(new IntensityComponent().apply(model.fetch("img")));
    icn = new ImageIcon(histoToImg(lnLengthIsDumb, new Color(0.6f, 0.6f, 0.6f)));
    intHisto.setIcon(icn);
    histoPanel.add(intHisto);

    return histoPanel;
  }

  // Converts a Histogram to an image.
  private static BufferedImage histoToImg(Histogram h, Color c) {
    BufferedImage product = new BufferedImage(257, 257, BufferedImage.TYPE_INT_RGB);
    int[] data = h.get();
    int max = 0;
    for (int i : data) {
      max = Math.max(i, max);
    }
    for (int i = 0; i < data.length; i++) {
      data[i] = (int) (255.0 * ((double) data[i] / (double) max));
    }
    for (int i = 0; i < 257; i++) {
      for (int j = 0; j < 257; j++) {
        if (i == 0 || j == 256) {
          product.setRGB(i, j, new Color(0.0f, 0.0f, 0.0f).getRGB());
        } else if (256 - j <= data[i - 1]) {
          product.setRGB(i, j, c.getRGB());
        } else {
          product.setRGB(i, j, new Color(1.0f, 1.0f, 1.0f).getRGB());
        }
      }
    }
    return product;
  }

  private JPanel setImage() {
    JPanel fileMasterPanel = new JPanel();
    fileMasterPanel.setLayout(new FlowLayout());
    JPanel fileopenPanel = new JPanel();
    JButton fileOpenButton = new JButton("Open a file");
    fileOpenButton.setActionCommand("Open file");
    fileOpenButton.addActionListener(this.controller);
    fileopenPanel.add(fileOpenButton);
    JLabel fileOpenDisplay = new JLabel("File path will appear here");
    imageIcon = new ImageIcon(this.convert());
    fileopenPanel.add(fileOpenDisplay);
    fileMasterPanel.add(fileopenPanel);

    JPanel filesavePanel = new JPanel();
    JButton fileSaveButton = new JButton("Save a file");
    fileSaveButton.setActionCommand("Save file");
    fileSaveButton.addActionListener(this.controller);
    filesavePanel.add(fileSaveButton);
    JLabel fileSaveDisplay = new JLabel("File path will appear here");
    filesavePanel.add(fileSaveDisplay);
    fileMasterPanel.add(filesavePanel);
    return fileMasterPanel;
  }

  private BufferedImage convert() {
    Pixel[][] array = model.fetch("img").getCopyPixels();
    BufferedImage image = new BufferedImage(array[0].length, array.length,
            BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array[0].length; j++) {
        image.setRGB(j, i, new Color(array[i][j].getRed(),
                array[i][j].getGreen(), array[i][j].getBlue()).getRGB());
      }
    }
    return image;
  }

  /**
   * Updates the image being shown by GraphicalImageView.
   */
  public ImageIcon updateImage() {
    hPanel = histoPanel();
    imageIcon = new ImageIcon(this.convert());
    imageLabel.setIcon(imageIcon);
    imageLabel.repaint();
    imageLabel.revalidate();
    imageLabel.repaint();
    imagePanel.revalidate();
    imagePanel.add(imageLabel);
    mainPanel.repaint();
    mainPanel.revalidate();
    return new ImageIcon(this.convert());
  }

  public ImageManager getModel() {
    return this.model;
  }

  /**
   * Appends message to output for text-based views.
   *
   * @param msg the provided message
   * @throws IOException when transmission of the board to the provided data destination fails
   */
  public void sendMessage(String msg) throws IOException {
    // Don't do anything
  }
}
