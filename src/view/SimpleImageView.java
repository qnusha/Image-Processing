package view;

import java.io.IOException;

import javax.swing.ImageIcon;

/**
 * A text-based view of ImageProc.
 */
public class SimpleImageView implements ImageView {
  private final Appendable out;

  /**
   * Create a new SimpleImageView.
   *
   * @param out Where the output of this view should go.
   * @throws IllegalArgumentException Only if given a null argument(s).
   */
  public SimpleImageView(Appendable out) throws IllegalArgumentException {
    if (out == null) {
      throw new IllegalArgumentException("Null argument");
    }
    this.out = out;
  }

  /**
   * Directly output a message to the view.
   *
   * @param msg The message to be output.
   * @throws IOException when the output is not transmittable
   */
  @Override
  public void sendMessage(String msg) throws IOException {
    try {
      out.append(msg).append("\n");
    } catch (Exception e) {
      throw new IOException("Not a valid output!");
    }
  }

  /**
   * Updates the image but is not required for a text based view.
   * @return ImageIcon null since it is a text-based view
   */
  @Override
  public ImageIcon updateImage() {
    return null;
  }
}
