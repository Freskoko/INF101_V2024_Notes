package no.uib.inf101.tetris.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import no.uib.inf101.grid.CellPosition;
import org.junit.jupiter.api.Test;

/**
 * This class contains unit tests for the TetrisBoard class.
 * 
 * @author Henrik Brøgger, with assistance from ChatGPT
 */
public class TestTetrisBoard {

  @Test
  public void prettyStringTest() {
    TetrisBoard board = new TetrisBoard(3, 4);
    board.set(new CellPosition(0, 0), 'g');
    board.set(new CellPosition(0, 3), 'y');
    board.set(new CellPosition(2, 0), 'r');
    board.set(new CellPosition(2, 3), 'b');
    String expected = String.join("\n", new String[] { "g--y", "----", "r--b" });
    assertEquals(expected, board.prettyString());
  }

  @Test
  public void testRemoveAllFullRows() {
    TetrisBoard board = TetrisBoard.getTetrisBoardWithContents(new String[] { "-T", "TT", "LT", "L-", "LL" });
    assertEquals(3, board.removeAllFullRows());
    String expected = String.join("\n", new String[] { "--", "--", "--", "-T", "L-" });
    assertEquals(expected, board.prettyString());
  }

  // En test med en annen bredde på brettet
  @Test
  public void testRemoveAllFullRowsOddWidth() {
    TetrisBoard board = TetrisBoard.getTetrisBoardWithContents(new String[] { "-TT", "TTT", "LTT", "L--", "LLL" });
    assertEquals(3, board.removeAllFullRows());
    String expected = String.join("\n", new String[] { "---", "---", "---", "-TT", "L--" });
    assertEquals(expected, board.prettyString());
  }

  // En test der nederste rad må beholdes
  @Test
  public void testRemoveAllFullRowsKeepBottomRow() {
    TetrisBoard board = TetrisBoard.getTetrisBoardWithContents(new String[] { "-T", "TT", "LT", "L-", "LL" });
    assertEquals(3, board.removeAllFullRows());
    String expected = String.join("\n", new String[] { "--", "--", "--", "-T", "L-" });
    assertEquals(expected, board.prettyString());
  }

  // En test der øverste rad skal fjernes
  @Test
  public void testRemoveAllFullRowsRemoveTopRow() {
    TetrisBoard board = TetrisBoard.getTetrisBoardWithContents(new String[] { "-T", "TT", "LT", "L-", "LL" });
    assertEquals(3, board.removeAllFullRows());
    String expected = String.join("\n", new String[] { "--", "--", "--", "-T", "L-" });
    assertEquals(expected, board.prettyString());
  }
}
