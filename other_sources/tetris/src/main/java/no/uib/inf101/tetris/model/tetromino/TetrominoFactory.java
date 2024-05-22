package no.uib.inf101.tetris.model.tetromino;

public interface TetrominoFactory {

  /**
   * @return The next Tetromino from the factory
   */
  public Tetromino getNext();
}
