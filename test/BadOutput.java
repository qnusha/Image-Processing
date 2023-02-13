import java.io.IOException;

/**
 * An Appendable output to test IOExceptions.
 */
public class BadOutput implements Appendable {

  /**
   * Throws an IOException.
   * @param csq The character sequence to append.  If {@code csq} is
   *            {@code null}, then the four characters {@code "null"} are
   *            appended to this Appendable.
   * @return A Big Ol' Pile of IOException.
   * @throws IOException When a gust of wind as much as touches it.
   */
  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException();
  }

  /**
   * Throws an IOException.
   * @param csq   The character sequence from which a subsequence will be
   *              appended.  If {@code csq} is {@code null}, then characters
   *              will be appended as if {@code csq} contained the four
   *              characters {@code "null"}.
   * @param start The index of the first character in the subsequence
   * @param end   The index of the character following the last character in the
   *              subsequence
   * @return Nothing but dust, blood, and IOExceptions.
   * @throws IOException All the time.
   */
  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException();
  }

  /**
   * Throws an IOException.
   * @param c The character to append
   * @return An Exception of the IO Persuasion.
   * @throws IOException If poked. Or if it's been having a bad day.
   */
  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException();
  }

}
