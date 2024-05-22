package no.uib.inf101.tetris.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import no.uib.inf101.tetris.midi.TetrisSong;
import no.uib.inf101.tetris.model.TetrisModel.GameState;
import no.uib.inf101.tetris.view.TetrisView;

public class TetrisController implements java.awt.event.KeyListener {
  private ControllableTetrisModel controllableTetrisModel;
  private TetrisView tetrisView;
  private TetrisSong tetrisSong;
  private Timer timer;
  private static final double BG_ROTATION_SPEED = 0.0025;

  /**
   * @author Henrik Brøgger
   * @param controllableTetrisModel
   * @param tetrisView
   */
  public TetrisController(
      ControllableTetrisModel controllableTetrisModel,
      TetrisView tetrisView,
      TetrisSong tetrisSong) {

    this.controllableTetrisModel = controllableTetrisModel;

    this.tetrisView = tetrisView;
    this.tetrisView.addKeyListener(this);

    this.tetrisSong = tetrisSong;
    this.tetrisSong.run();

    this.timer = new Timer(controllableTetrisModel.millisBetweenTimer(), this::clockTick);
    this.timer.start();
  }

  /**
   * Repaints board, controls how board is to be viewed depending on GameState Also updates the
   * background rotating
   *
   * @param actionEvent
   */
  private void clockTick(ActionEvent actionEvent) {
    if (controllableTetrisModel.getGameState() == GameState.ACTIVE_GAME) {
      controllableTetrisModel.clockTick();

      double newRotation = tetrisView.getBackgroundRotation() + BG_ROTATION_SPEED;
      tetrisView.setBackgroundRotation(newRotation);
      tetrisView.repaint();
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      // Left arrow pressed, move tetromino left
      this.controllableTetrisModel.moveTetromino(0, -1);
    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      // Right arrow was pressed, move tetromino right
      this.controllableTetrisModel.moveTetromino(0, 1);
    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      // Down arrow was pressed, move tetromino down
      if (this.controllableTetrisModel.moveTetromino(1, 0)) {
        this.timer.restart();
      }
    } else if (e.getKeyCode() == KeyEvent.VK_UP) {
      // Up arrow was pressed, rotate tetromino
      this.controllableTetrisModel.rotateTetromino();
    } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
      // Spacebar was pressed, drop tetromino
      if (this.controllableTetrisModel.dropTetromino()) {
        this.timer.restart();
      }
    }
    this.tetrisView.repaint();
  }

  @Override
  public void keyReleased(KeyEvent arg0) {}

  @Override
  public void keyTyped(KeyEvent arg0) {}
}
