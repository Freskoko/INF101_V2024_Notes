package no.uib.inf101.tetris.model.tetromino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.model.TetrisBoard;

public class Tetromino implements Iterable<GridCell<Character>> {

  private char symbol;
  private boolean[][] shape;
  private CellPosition cellPosition;

  /**
   * @param symbol
   * @param shape
   * @param cellPosition
   * @author Henrik Brøgger, with assistance from ChatGPT
   */
  private Tetromino(char symbol, boolean[][] shape, CellPosition cellPosition) {
    this.symbol = symbol;
    this.shape = shape;
    this.cellPosition = cellPosition;
  }

  /**
   * @param symbolInput the symbol of the Tetromino that is to be placed
   * @return Tetromino with the shape of the given symbol and CellPostion 0,0
   *         Note: this method is
   *         to be used when creating a new Tetromino, in other instances such
   *         as in rotate / shiftby make a direct call to `new Tetromino()`
   *         to update position and rotation
   */
  public static Tetromino newTetromino(char symbolInput) {

    CellPosition cellPos = new CellPosition(0, 0);

    boolean[][] shape = switch (symbolInput) {
      case 'L' ->
        new boolean[][] {
            { false, false, false },
            { true, true, true },
            { true, false, false }
        };

      case 'J' ->
        new boolean[][] {
            { false, false, false },
            { true, true, true },
            { false, false, true }
        };

      case 'S' ->
        new boolean[][] {
            { false, false, false },
            { false, true, true },
            { true, true, false }
        };

      case 'Z' ->
        new boolean[][] {
            { false, false, false },
            { true, true, false },
            { false, true, true }
        };

      case 'T' ->
        new boolean[][] {
            { false, false, false },
            { true, true, true },
            { false, true, false }
        };

      case 'I' ->
        new boolean[][] {
            { false, false, false, false },
            { true, true, true, true },
            { false, false, false, false },
            { false, false, false, false }
        };

      case 'O' ->
        new boolean[][] {
            { false, false, false, false },
            { false, true, true, false },
            { false, true, true, false },
            { false, false, false, false }
        };

      default ->
        throw new IllegalArgumentException("No available symbol for '" +
            symbolInput + "'");
    };
    return new Tetromino(symbolInput, shape, cellPos);
  }

  /**
   * @param deltaRow distance to move Tetromino left/right
   * @param deltaCol distance to move Tetromino down
   * @return a new Tetromino object equal to this.Tetromino, with an updated
   *         CellPosition
   */
  public Tetromino shiftedBy(int deltaRow, int deltaCol) {
    CellPosition newPos = new CellPosition(this.cellPosition.row() + deltaRow,
        this.cellPosition.col() + deltaCol);

    return new Tetromino(this.symbol, this.shape, newPos);
  }

  /**
   * @return a new Tetromino object equal to this.Tetromino, with an updated
   *         shape, rotated 90 degrees clockwise
   */
  public Tetromino rotate() {
    // Created with assistance from ChatGPT
    // OpenAI (2023). ChatGPT (20. april-versjon) [Stor språkmodell].
    // Hentet fra https://chat.openai.com.
    int rows = this.shape.length;
    int cols = this.shape[0].length;
    boolean[][] rotated = new boolean[cols][rows];

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        rotated[j][rows - 1 - i] = shape[i][j];
      }
    }

    return new Tetromino(this.symbol, rotated, this.cellPosition);
  }

  /**
   * Shifts the current tetromino to the waiting box on the Tetris board.
   *
   * @param tetrisBoard the Tetris board to shift the tetromino to
   * @return a new Tetromino instance shifted to the waiting box
   */
  public Tetromino shiftedToWaitingBox(TetrisBoard tetrisBoard) {

    CellPosition newPos = new CellPosition(0, 0);
    return new Tetromino(this.symbol, this.shape, newPos);
  }

  /**
   * @param gridDimension contains info about col and row of tetrisboard,
   *                      used to calculate the location of the tetrisBlock
   * @return a new Tetromino object equal to this.Tetromino, with an updated
   *         CellPosition, equal to the top-middle of the board
   * @throws IllegalArgumentException if the Tetromino cannot be placed on the
   *                                  board
   */
  public Tetromino shiftedToTopCenterOf(GridDimension gridDimension) {

    // CALCULATE COL

    int newCol = -99;
    // grid is even
    if (gridDimension.cols() % 2 == 0) {
      // tetris shape is odd (3)
      if (this.shape.length % 2 == 1) {
        newCol = (gridDimension.cols() / 2) - 1; // must go to one of the sides
      }
      // tetris shape is even (4)
      else {
        newCol = (gridDimension.cols() / 2) - 2;
      }

    }

    // grid is odd
    else {
      // tetris shape is odd (3)
      if (this.shape.length % 2 == 1) {
        newCol = (gridDimension.cols() / 2) - 1;
      }
      // tetris shape is even (4)
      else {
        newCol = (gridDimension.cols() / 2) - 1;
      }
    }

    // CALCULATE ROW
    int newRow = -99;

    // loop through and find first occurance of true
    for (int rowIndex = 0; rowIndex < this.shape.length; rowIndex++) {
      int colLength = this.shape[rowIndex].length;
      for (int colIndex = 0; colIndex < colLength; colIndex++) {
        if (this.shape[rowIndex][colIndex]) {
          newRow = (-rowIndex);
          break;
        }
      }

      // if true found, stop
      if (newRow != -99) {
        break;
      }
    }

    if (newRow == -99 || newCol == -99) {
      // values were not found if this evalutes to true.
      throw new IllegalArgumentException("""
          Could not find a valid position for the tetrisBlock""");
    }

    CellPosition newPos = new CellPosition(newRow, newCol);
    Tetromino copy = newTetromino(this.symbol);
    copy.cellPosition = newPos;

    return copy;
  }

  @Override
  public Iterator<GridCell<Character>> iterator() {
    ArrayList<GridCell<Character>> trueList = new ArrayList<GridCell<Character>>();

    // loop over and find all true, add them to gridcell objects
    for (int i = 0; i < this.shape.length; i++) {
      for (int j = 0; j < this.shape[i].length; j++) {
        if (this.shape[i][j]) {
          // regn ut hvilken posisjon dette tilsvarer på brettet
          // og legg til et nytt GridCell-objekt i listen.

          int newRow = this.cellPosition.row()+i;
          int newCol = this.cellPosition.col()+j;

          CellPosition relativeCellPos = new CellPosition(newRow, newCol);

          trueList.add(new GridCell<Character>(relativeCellPos, this.symbol));
        }
      }
    }

    return trueList.iterator();
  }

  /**
   * @return this.cellPosition
   */
  public CellPosition getCellPosition() {
    return this.cellPosition;
  }

  @Override
  // auto generated
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + symbol;
    result = prime * result + Arrays.deepHashCode(shape);
    result = prime * result + ((cellPosition == null) ? 0 : cellPosition.hashCode());
    return result;
  }

  @Override
  // auto generated
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Tetromino other = (Tetromino) obj;
    if (symbol != other.symbol)
      return false;
    if (!Arrays.deepEquals(shape, other.shape))
      return false;
    if (cellPosition == null) {
      if (other.cellPosition != null)
        return false;
    } else if (!cellPosition.equals(other.cellPosition))
      return false;
    return true;
  }
}
