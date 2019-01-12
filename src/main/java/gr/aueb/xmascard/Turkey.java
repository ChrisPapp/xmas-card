package gr.aueb.xmascard;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Turkey extends Drawable {

  /** The Turkey's background color. */
  private final Color color = Color.RED;

  /**
   * The 'x' current coordinate of the turkey.
   */
  protected int coordX;

  /**
   * The 'y' current coordinate of the turkey.
   */
  protected int coordY;

  /**
   * The state of the turkey
   */
  private boolean alive;
  /**
   * Size of the turkey
   */
  private final int size = 80;

  public Turkey(JPanel panel) {
    super(panel);
    coordY = (int) (bounds.height * Math.random());
    coordX = 0;
    this.setAlive(true);
  }

  @Override
  public void draw(Graphics g) {
    
    BufferedImage turkey = null;
    //File file = getClass().getResource("turkey.png");
    try {
      //FileInputStream fis = new FileInputStream(file);
      turkey = ImageIO.read(ClassLoader.getSystemResource("turkey.png"));
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    if (coordX >= bounds.width + bounds.x)
      coordX = 0;
    coordX += 5;
    if (this.isAlive()) {
      g.drawImage(turkey, coordX, coordY, coordX + size, coordY + size,
          0, 0, turkey.getWidth(null), turkey.getHeight(null), null);
    }
  }

  public int getSize() {
    return size;
  }

  public boolean isAlive() {
    return alive;
  }

  public void setAlive(boolean alive) {
    this.alive = alive;
  }

}
