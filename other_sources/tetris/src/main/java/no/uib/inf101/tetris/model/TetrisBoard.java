package no.uib.inf101.tetris.model;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;

public class TetrisBoard extends Grid<Character> {

  /**
   * Represents a Tetris board. The Tetris board is a grid of cells where
   * tetrominos can be placed.
   *
   * @author Henrik Brøgger
   * @param rows the number of rows in the Tetris board
   * @param cols the number of columns in the Tetris board
   */
  public TetrisBoard(int rows, int cols) {
    super(rows, cols, '-');

    if (rows < 4 || cols < 4) {
      System.err.println(
          """
              ---- WARNING ----
              It is not recommended that any rows or columns
              have a length less than 4. Good luck!""");
    } // tests rely on boards less than 4, but this may lead to crashes
    // when large tetrominos are used in actual games
  }

  /**
   * @return A string representation of the TetrisBoard
   */
  public String prettyString() {
    String boardString = "";

    for (int i = 0; i < this.rows(); i++) {
      for (int j = 0; j < this.cols(); j++) {
        boardString += (this.get(new CellPosition(i, j)));
        // add value at index (i,j) to string
      }
      if (i < this.rows() - 1) {
        boardString += "\n"; // add a newline to create board structure
      }
    }
    System.out.println(boardString);
    return boardString;
  }

  /**
   * @param rows A string representation of the TetrisBoard
   * @return A TetrisBoard object, with values from the param "rows"
   */
  public static TetrisBoard getTetrisBoardWithContents(String[] rows) {

    TetrisBoard gridFromString = new TetrisBoard(rows.length, rows[0].length());

    for (int rowIndex = 0; rowIndex < rows.length; rowIndex++) {
      for (int colIndex = 0; colIndex < rows[0].length(); colIndex++) {
        char charAtPos = rows[rowIndex].charAt(colIndex);
        gridFromString.set(new CellPosition(rowIndex, colIndex), charAtPos);
      }
    }

    return gridFromString;
  }

  /**
   * @param row row index of cell to check if exists on board
   * @param col col index of cell to check if exists on board
   * @return A boolean representing if the cell is inhibited by a Tetromino
   *         not '-')
   */
  private boolean elementExistsOnBoard(int row, int col) {
    return (this.get(new CellPosition(row, col)) != '-');
  }

  /**
   * @param row The index of the row to check
   * @return A boolean representation of if the row is completely full
   */
  private boolean checkFullRowExists(int row) {
    for (int i = 0; i < this.cols(); i++) {
      if (!elementExistsOnBoard(row, i)) {
        return false;
      }
    }
    return true;
  }

  /**
   * @param row       The index of the row update all values in
   * @param charValue The value to change all items in the row to
   */
  private void setRowToValue(int row, char charValue) {
    for (int i = 0; i < this.cols(); i++) {
      this.set(new CellPosition(row, i), charValue);
    }
  }

  /**
   * @param removedRow the row to put values from sourceRow into
   * @param sourceRow  the source from which to put values into removedRow from
   */
  private void setCopyRowToOther(int removedRow, int sourceRow) {
    for (int i = 0; i < this.cols(); i++) {

      Character charAtPos = this.get(new CellPosition(sourceRow, i));
      // set same pos in removed row to grabbed from target row
      this.set(new CellPosition(removedRow, i), charAtPos);
    }
  }

  /**
   * Removes full rows in the TetrisBoard, and moves partially filled rows down
   * if rows beneath are cleared
   *
   * @return The number of removed rows
   */
  public int removeAllFullRows() {
    int fullRowCounter = 0;

    // loop from bottom of board to top
    for (int row = this.rows() - 1; row >= 0; row--) {

      if (checkFullRowExists(row)) {

        setRowToValue(row, '-');
        // noter at man har clearet
        fullRowCounter++;

      } else {

        // sjekk hvis det er rader som har blitt clearet
        if (fullRowCounter > 0) {
          // kopier rad med mellomrom = fullRowCounter
          setCopyRowToOther(row + fullRowCounter, row);
        }
      }
    }

    // etter fjernet og kopiert, fjern rader på toppen
    for (int row = 0; row < fullRowCounter; row++) {
      setRowToValue(row, '-');
    }

    return fullRowCounter;
  }
}
