package gr.aueb.xmascard;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Score extends Drawable{
  /**
   * Current Score
   */
  private int score;
  
  public Score(JPanel panel) {
    super(panel);
    score = 0;
  }

  @Override
  public void draw(Graphics g) {
    Color currentColor = g.getColor();
    Font currentFont = g.getFont();
    g.setColor(Color.RED);
    g.setFont(new Font("Arial", Font.BOLD, 30));
    g.drawString(" Score: " + this.score, DrawPanel.WIDTH - 150, 30);
    g.setColor(currentColor);
    g.setFont(currentFont);
    
  }
  
  public void finalScoreAnnounce(Graphics g) {
  Color currentColor = g.getColor();
  Font currentFont = g.getFont();
  g.setColor(Color.BLACK);
  g.setFont(new Font("Arial", Font.BOLD, 100));
  g.drawString("Merry Christmas", DrawPanel.WIDTH / 2 - 400, DrawPanel.HEIGHT / 2 - 100);
  g.drawString("Score: " + this.score, DrawPanel.WIDTH / 2 - 400, DrawPanel.HEIGHT / 2);
  // Message only for the good players
  if (score > 50) {
    g.drawString("Enjoy the turkey!", DrawPanel.WIDTH / 2 - 400, DrawPanel.HEIGHT / 2 + 100);
  }
  g.setColor(currentColor);
  g.setFont(currentFont);
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }
  
}
