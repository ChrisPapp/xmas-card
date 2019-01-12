/*-
 * Copyright 2005-2018 Diomidis Spinellis
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package gr.aueb.xmascard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * The program's main window. Extends JFrame to display the window where the
 * trees and snow are drawn. Implements the {@link java.lang.Runnable Runnable}
 * interface so as to create a thread that repeatedly calls the
 * {@link gr.aueb.xmascard.Drawable#draw() draw}method.
 *
 * @author Giorgos Gousios, Diomidis Spinellis
 * @opt nodefillcolor lightblue
 * @assoc 1 drawablePanel 1 DrawablePanel
 */
public class DrawPanel extends JFrame implements Runnable, MouseListener {

  /** The window's width. */
  public static final int WIDTH = 1024;
  /** The window's height. */
  public static final int HEIGHT = 768;

  /** The window's background color (blue). */
  public static final Color backgroundColor = new Color(0, 153, 204);

  /* A table that holds the objects to be drawn */
  private Vector<Drawable> drawObjects = null;

  /* The drawing thread */
  private Thread thread;

  /* The canvas to draw onto */
  private DrawablePanel drawablePanel = null;
  /* The player's score */
  private Score score;
  /* Gameover status */
  private boolean gameOver = false;
  /* Midi Player */
  private WavPlayer player = new WavPlayer();

  /**
   * Serial number of persistant data. Required, because JFrame implements
   * serializable.
   */
  static final long serialVersionUID = 1L;

  /**
   * Constructor to initialize and display the window and starts the animation.
   *
   */
  public DrawPanel() {
    super("Christmas Card");
    drawObjects = new Vector<Drawable>();
    initializeGraphics();
    initializeThread();
    score = new Score(getCanvas());
    drawObjects.add(score);
    this.addMouseListener(this);
  }

  /** Initialize the main window. */
  private void initializeGraphics() {
    // Make our window look nice
    JFrame.setDefaultLookAndFeelDecorated(true);

    // Create our drawing canvas
    drawablePanel = new DrawablePanel(this);
    drawablePanel.setBackground(backgroundColor);
    drawablePanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    setContentPane(drawablePanel);

    // Handle termination
    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

    // Exit when the window is closed
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });

    // Our size
    setSize(WIDTH, HEIGHT);

    // Force the parent window to expand the canvas to all available space
    pack();

    // Display the window
    setVisible(true);
  }

  /** Start the execution of the drawing thread. */
  private void initializeThread() {
    if (thread == null) {
      thread = new Thread(this);
      thread.setPriority(Thread.MIN_PRIORITY);
      thread.start();
    }
  }

  /** Add a component to be drawn. */
  public void addDrawObject(Drawable drawObject) {
    drawObjects.add(drawObject);
  }

  /** Return a copy of the component list to be drawn */
  public Vector<Drawable> getDrawables() {
    return new Vector<Drawable>(drawObjects);
  }

  /**
   * The method to be executed by the running thread. Executes the
   * {@link DrawablePanel#repaint()}method periodically.
   */
  public void run() {
    Thread me = Thread.currentThread();

    // Allow termination by setting thread to null
    while (thread == me && !gameOver) {
      // tell drawablePanel to repaint its contents
      drawablePanel.repaint();
      try {
        Thread.sleep(30);
      } catch (InterruptedException e) {
      }
    }
    JOptionPane.showMessageDialog(getCanvas(),
        "You missed! \nEnjoy your Turkey \nScore:" + score.getScore(),
        "Game Over!!", JOptionPane.PLAIN_MESSAGE);
    thread = null;
    System.exit(0);
  }

  /**
   * Get the canvas's drawing panel
   *
   * @return javax.swing.JPanel
   */
  public JPanel getCanvas() {
    return drawablePanel;
  }

  public void mouseClicked(MouseEvent e) {
    new SoundThread("shotgun.wav").start();
    boolean missed = true;
    int x = e.getX();
    int y = e.getY();
    int offset = 50;

    for (int i = 0; i < this.drawObjects.size(); i++) {
      if (drawObjects.get(i) instanceof Turkey) {
        Turkey toCheck = (Turkey) drawObjects.get(i);
        if (x >= toCheck.coordX - offset
            && x <= toCheck.coordX + toCheck.getSize()
            && y >= toCheck.coordY - offset
            && y <= toCheck.coordY + toCheck.getSize() + offset) {
          missed = false;
          drawObjects.remove(i);
          // Increment Score
          score.setScore(score.getScore() + 1);
          ;
        }
      }
    }
    if (missed) {
      gameOver = true;
    }

  }

  public void mouseEntered(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  public void mouseExited(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  public void mousePressed(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  public void mouseReleased(MouseEvent e) {
    // TODO Auto-generated method stub

  }

}
