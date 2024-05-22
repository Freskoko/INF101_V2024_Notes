package no.uib.inf101.tetris.view;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.model.TetrisModel.GameState;

public interface ViewableTetrisModel {

  /**
   * @return A gridDimension object with information about the rows and cols of
   *         the board
   */
  public GridDimension getDimension();

  /**
   * @return Iterable object which returns all non-tetromino GridCells on
   *         the board
   */
  public Iterable<GridCell<Character>> getTilesOnBoard();

  /**
   * @return Iterable object which returns the current tetromino GridCells
   *         on the board
   */
  public Iterable<GridCell<Character>> getTetrominoTiles();

  /**
   * @return The current gamestate
   * @see GameState in TetrisModel
   */
  public GameState getGameState();

  /**
   * @return Iterable object which returns the current waiting tetromino
   *         GridCells on the side preview board
   */
  public Iterable<GridCell<Character>> getWaitingTiles();

  /**
   * Returns the dimensions of the view side grid.
   *
   * @return GridDimension object with information about the rows and cols of
   *         the side view grid
   */
  public GridDimension getSideViewTiles();

  /**
   * @return Current players game score
   */
  public String getGameScore();
}
