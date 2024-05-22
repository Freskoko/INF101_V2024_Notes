package no.uib.inf101.tetris.controller;

import no.uib.inf101.tetris.model.TetrisModel.GameState;

public interface ControllableTetrisModel {

  /**
   * @param deltaRow distance to move Tetromino left/right
   * @param deltaCol distance to move Tetromino down
   * @return if the movement was succesful or not
   */
  public boolean moveTetromino(int deltaRow, int deltaCol);

  /**
   * Rotates the Tetromino clockwise 90 degrees
   *
   * @return if the movement was succesful or not
   */
  public boolean rotateTetromino();

  /**
   * Drops Tetromino to the lowest point possible, and sticks it to the board
   *
   * @return if the movement was succesful or not
   */
  public boolean dropTetromino();

  /**
   * @return Current gamestate from Enum (GameState)
   */
  public GameState getGameState();

  /**
   * @return Millis between ticks in timer (1000 = 1 second)
   */
  public int millisBetweenTimer();

  /** Provides actions to the game, within an interval (millisBetweenTimer) */
  public void clockTick();
}
