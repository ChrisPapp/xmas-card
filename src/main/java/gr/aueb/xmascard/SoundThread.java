package gr.aueb.xmascard;

/**
 * A thread that plays a sound
 */
public class SoundThread extends Thread {
  /**
   * The name of the sound file
   */
  private String fileName;
  /*
   * The player to play the soundFile with
   */
  private WavPlayer player;

  /**
   * Initiate the Tread with the soundFile
   * 
   * @param fileName
   *          .wav file to play
   */
  public SoundThread(String fileName) {
    this.fileName = fileName;
    player = new WavPlayer();
  }

  public void run() {
    player.play(fileName);
  }
}
