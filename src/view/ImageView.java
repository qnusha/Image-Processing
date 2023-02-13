package view;

import java.io.IOException;

import javax.swing.ImageIcon;

/**
 * A view of ImageProc.
 */
public interface ImageView {

  /**
   * Appends message to output for text views.
   * @param msg the provided message
   * @throws IOException when transmission of the board to the provided data destination fails
   */
  public void sendMessage(String msg) throws IOException;

  /**
   * Updates the image being shown in a graphical view.
   */
  public ImageIcon updateImage();
}
