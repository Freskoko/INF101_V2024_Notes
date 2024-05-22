package no.uib.inf101.tetris.model;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.controller.ControllableTetrisModel;
import no.uib.inf101.tetris.model.tetromino.Tetromino;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;
import no.uib.inf101.tetris.view.ViewableTetrisModel;

public class TetrisModel implements ViewableTetrisModel, ControllableTetrisModel {
  private TetrisBoard tetrisBoard;
  private TetrisBoard sideViewBoard;
  private TetrominoFactory tetrominoFactory;
  private Tetromino tetromino;
  private Tetromino nextTetromino;
  private GameState gameState = GameState.ACTIVE_GAME;
  private int playerScore = 0;

  /**
   * Constructs a new TetrisModel object with the given TetrisBoard and
   * TetrominoFactory.
   * 
   * @author Henrik Brøgger
   * @param tetrisBoard      the TetrisBoard to be used for the game
   * @param tetrominoFactory the TetrominoFactory to generate Tetrominos
   */
  public TetrisModel(TetrisBoard tetrisBoard, TetrominoFactory tetrominoFactory) {
    this.tetrisBoard = tetrisBoard;
    this.tetrominoFactory = tetrominoFactory;
    this.sideViewBoard = new TetrisBoard(4, 4); 
    // 4x4 side view board to hold next tetromino
    
    this.tetromino = tetrominoFactory.getNext().shiftedToTopCenterOf(tetrisBoard);
    this.nextTetromino = tetrominoFactory.getNext().shiftedToWaitingBox(tetrisBoard);
  }

  public enum GameState {
    ACTIVE_GAME,
    GAME_OVER,
  }

  @Override
  public GridDimension getDimension() {
    return this.tetrisBoard;
  }

  @Override
  public Iterable<GridCell<Character>> getTilesOnBoard() {
    return this.tetrisBoard;
  }

  @Override
  public GridDimension getSideViewTiles() {
    return this.sideViewBoard;
  }

  @Override
  public Iterable<GridCell<Character>> getTetrominoTiles() {
    return this.tetromino;
  }

  @Override
  public Iterable<GridCell<Character>> getWaitingTiles() {
    return this.nextTetromino;
  }

  /**
   * Helper method to see if a potential a candidate Tetromino is placed legally
   *
   * @param candidateBlocks
   * @return A boolean representing if the candidate is placed legally
   */
  private boolean checkLegalMove(Iterable<GridCell<Character>> candidateBlocks) {

    for (GridCell<Character> gc : candidateBlocks) {

      int tetrominoSquareRow = gc.pos().row();
      int tetrominoSquareCol = gc.pos().col();

      // check if something is already there
      for (GridCell<Character> boardPos : this.tetrisBoard) {
        if (boardPos.value() != '-') {
          if (tetrominoSquareCol == boardPos.pos().col()
              && tetrominoSquareRow == boardPos.pos().row()) {
            return false;
          }
        }
      }

      // check rows
      if (tetrominoSquareRow > tetrisBoard.rows() - 1 || tetrominoSquareRow < 0) {
        return false;
      }

      // check cols
      if (tetrominoSquareCol > tetrisBoard.cols() - 1 || tetrominoSquareCol < 0) {
        return false;
      }
    }

    return true;
  }

  @Override
  public boolean moveTetromino(int deltaRow, int deltaCol) {
    Tetromino candidate = this.tetromino.shiftedBy(deltaRow, deltaCol);

    if (checkLegalMove(candidate)) {
      this.tetromino = candidate;
      return true;
    }
    return false;
  }

  @Override
  public boolean rotateTetromino() {
    Tetromino candidate = this.tetromino.rotate();

    if (checkLegalMove(candidate)) {
      this.tetromino = candidate;
      return true;
    }
    return false;
  }

  /**
   * Get a new Tetromino from this.this.tetrominoFactory and places it at the
   * top-middle of the board.
   * Also handles the game-over scenario where there is no free space for a
   * Tetromino at the middle-top of the board
   */
  private void getFallingTetromino() {
    Tetromino candidate = this.nextTetromino;
    candidate = candidate.shiftedToTopCenterOf(tetrisBoard);

    if (checkLegalMove(candidate)) {
      this.tetromino = candidate;
      this.nextTetromino = this.tetrominoFactory.getNext();
      this.nextTetromino = this.nextTetromino.shiftedToWaitingBox(tetrisBoard);
    } else {
      this.gameState = GameState.GAME_OVER;
    }
  }

  /**
   * Sticks the current Tetromino to the board, and initializes a new Tetromino
   * object
   */
  public void stickTetrominoToBoard() {
    // sticks all blocks of the Tetromino to the board
    for (GridCell<Character> gc : tetromino) {
      this.tetrisBoard.set(gc.pos(), gc.value());
    }
    // grab new Tetromino after old tetromino is stuck to board
    this.playerScore += this.tetrisBoard.removeAllFullRows();
    getFallingTetromino();
  }

  @Override
  public boolean dropTetromino() {
    // loop over all, if valid move, stick down

    while (true) {
      Tetromino candidate = this.tetromino.shiftedBy(1, 0);

      if (checkLegalMove(candidate)) {
        this.tetromino = candidate;
      } else {
        stickTetrominoToBoard();
        break;
      }
    }
    return true;
  }

  @Override
  public GameState getGameState() {
    return this.gameState;
  }

  @Override
  public int millisBetweenTimer() {
    return 500;
  }

  /**
   * Advances the game by one clock tick. This method is called periodically to
   * update the game
   * state. It shifts the current tetromino one step down, and if the move is
   * legal, updates the tetromino's position.
   * If the move is not legal, the tetromino is instead stuck to the board.
   */
  @Override
  public void clockTick() {

    Tetromino candidate = this.tetromino.shiftedBy(1, 0);

    if (checkLegalMove(candidate)) {
      this.tetromino = candidate;
    } else {
      stickTetrominoToBoard();
    }
  }

  @Override
  public String getGameScore() {
    return Integer.toString(this.playerScore);
  }
}
