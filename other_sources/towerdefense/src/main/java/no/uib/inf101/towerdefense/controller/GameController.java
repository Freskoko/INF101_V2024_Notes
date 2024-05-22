package no.uib.inf101.towerdefense.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.Timer;
import no.uib.inf101.towerdefense.model.GameModel.GameState;
import no.uib.inf101.towerdefense.view.GameView;

public class GameController implements KeyListener, MouseListener, MouseMotionListener {
  private ControllableGameModel controllableGameModel;
  private GameView gameView;
  private Timer timer;

  /**
   * @param controllableGameModel
   * @param tetrisView
   * @author Henrik Brøgger, with assistance from chatGPT Created with assistance from ChatGPT
   *     OpenAI (2023). ChatGPT (20. april-versjon) [Stor språkmodell]. Hentet fra
   *     https://chat.openai.com.
   */
  public GameController(ControllableGameModel controllableGameModel, GameView gameView) {

    // parameters
    this.controllableGameModel = controllableGameModel;
    this.gameView = gameView;

    // listeners
    this.gameView.addKeyListener(this);
    this.gameView.addMouseListener(this);
    this.gameView.addMouseMotionListener(this);

    // timer
    this.timer = new Timer(controllableGameModel.millisBetweenTimer(), this::clockTick);
    this.timer.start();
  }

  /**
   * Handles the clocktick, telling the controllableGameModel to clocktick 
   * only if the game is active 
   * (preventing the game from running in the background) of the main menu
   *
   * @param actionEvent
   */
  private void clockTick(ActionEvent actionEvent) {
    if (controllableGameModel.getGameState() == GameState.ACTIVE_GAME) {
      controllableGameModel.clockTick();
    }
    this.gameView.repaint();
  }

  /**
   * Listens to mouseclicks and passes 
   * the clicked points along to the controllableGameModel
   *
   * @param mouseEvent
   */
  public void mouseClicked(MouseEvent mouseEvent) {
    this.controllableGameModel.handleClick(mouseEvent.getPoint());
  }

  @Override
  public void mouseMoved(MouseEvent mouseEvent) {
    this.controllableGameModel.setCurrentMousePoint(mouseEvent.getPoint());
  }

  @Override
  public void keyPressed(KeyEvent e) {
    this.controllableGameModel.handleKeyPress();
  }

  @Override
  public void keyReleased(KeyEvent arg0) {}

  @Override
  public void keyTyped(KeyEvent arg0) {}

  @Override
  public void mouseEntered(MouseEvent arg0) {}

  @Override
  public void mouseExited(MouseEvent arg0) {}

  @Override
  public void mousePressed(MouseEvent arg0) {}

  @Override
  public void mouseReleased(MouseEvent arg0) {}

  @Override
  public void mouseDragged(MouseEvent arg0) {}
}
