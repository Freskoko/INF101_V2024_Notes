package no.uib.inf101.tetris.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.tetris.model.tetromino.PatternedTetrominoFactory;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;
import no.uib.inf101.tetris.view.ViewableTetrisModel;
import org.junit.jupiter.api.Test;

/**
 * This class contains unit tests for the TetrisModel class.
 * 
 * @author Henrik Brøgger, with assistance from ChatGPT
 */
public class TestTetrisModel {

  private TetrisModel initBoardUtils(int rows, int cols, String symbols) {
    TetrisBoard board = new TetrisBoard(rows, cols);
    TetrominoFactory factory = new PatternedTetrominoFactory(symbols);
    TetrisModel model = new TetrisModel(board, factory);
    return model;
  }

  private List<GridCell<Character>> getBoardCellPositions(ViewableTetrisModel model) {
    List<GridCell<Character>> tetroCells = new ArrayList<>();
    for (GridCell<Character> gc : model.getTetrominoTiles()) {
      tetroCells.add(gc);
    }
    return tetroCells;
  }

  private List<GridCell<Character>> getBoardNonTetrominoPositions(ViewableTetrisModel model) {
    List<GridCell<Character>> tetroCells = new ArrayList<>();
    for (GridCell<Character> gc : model.getTilesOnBoard()) {
      tetroCells.add(gc);
    }
    return tetroCells;
  }

  @Test
  public void initialPositionOfO() {

    ViewableTetrisModel model = initBoardUtils(20, 10, "O");

    List<GridCell<Character>> tetroCells = getBoardCellPositions(model);

    assertEquals(4, tetroCells.size());
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 4), 'O')));
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 5), 'O')));
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 4), 'O')));
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 5), 'O')));
  }

  @Test
  public void initialPositionOfI() {
    ViewableTetrisModel model = initBoardUtils(20, 10, "I");

    List<GridCell<Character>> tetroCells = getBoardCellPositions(model);

    assertEquals(4, tetroCells.size());
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 3), 'I')));
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 4), 'I')));
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 5), 'I')));
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 6), 'I')));
  }

  @Test
  public void StayInBoundsTest() {
    // Det er ikke mulig å flytte en brikke ut av brettet
    // (returnerer false og ingen endring i fallende brikke sin posisjon)
    TetrisModel model = initBoardUtils(20, 3, "L");

    // move left
    assertFalse(model.moveTetromino(0, -1));

    List<GridCell<Character>> tetroCells = getBoardCellPositions(model);
    // see no movement
    assertEquals(4, tetroCells.size());
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 0), 'L')));
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 1), 'L')));
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 2), 'L')));
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 0), 'L')));
  }

  @Test
  public void TrueOnMoveTest() {
    // Vellykket flytting returnerer true
    TetrisModel model = initBoardUtils(20, 3, "L");

    // move down
    assertTrue(model.moveTetromino(1, 0));
  }

  @Test
  public void ChangeInBrickPositionAfterMoveTest() {
    // Vellykket flytting endrer hva man får om man iterererer gjennom den fallende
    // brikkent
    TetrisModel model = initBoardUtils(20, 4, "I");

    List<GridCell<Character>> tetroCells = getBoardCellPositions(model);

    assertEquals(4, tetroCells.size());
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 0), 'I')));
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 1), 'I')));
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 2), 'I')));
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 3), 'I')));

    // move down
    assertTrue(model.moveTetromino(1, 0));

    tetroCells = getBoardCellPositions(model);

    assertEquals(4, tetroCells.size());
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 0), 'I')));
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 1), 'I')));
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 2), 'I')));
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 3), 'I')));
  }

  @Test
  public void MoveToOccupiedFailsTest() {
    // Det er ikke mulig å flytte brikken et sted som er opptatt på brettet
    // (returnerer false og
    // ingen endring i fallende brikke)
    TetrisModel model = initBoardUtils(10, 4, "IS");

    // drop I to the floor, grabs now S
    assertTrue(model.dropTetromino());
    // move S down 9
    for (int i = 0; i < 8; i++) {
      assertTrue(model.moveTetromino(1, 0));
    }

    List<GridCell<Character>> tetroCells = getBoardCellPositions(model);

    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(8, 3), 'S')));
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(8, 2), 'S')));
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(9, 1), 'S')));
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(9, 2), 'S')));

    // cannot move one more down
    assertFalse(model.moveTetromino(1, 0));

    // test no movement
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(8, 3), 'S')));
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(8, 2), 'S')));
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(9, 1), 'S')));
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(9, 2), 'S')));
  }

  @Test
  public void DropBrickTest() {
    TetrisModel model = initBoardUtils(20, 4, "S");

    model.dropTetromino();

    List<GridCell<Character>> boardCells = getBoardNonTetrominoPositions(model);

    assertTrue(boardCells.contains(new GridCell<>(new CellPosition(18, 3), 'S')));
    assertTrue(boardCells.contains(new GridCell<>(new CellPosition(18, 2), 'S')));
    assertTrue(boardCells.contains(new GridCell<>(new CellPosition(19, 1), 'S')));
    assertTrue(boardCells.contains(new GridCell<>(new CellPosition(19, 2), 'S')));
  }

  @Test
  public void ClockTickTest() {
    // Skriv en test for clockTick-metoden i TestTetrisModel
    TetrisModel model = initBoardUtils(20, 4, "I");

    List<GridCell<Character>> tetroCells = getBoardCellPositions(model);

    assertEquals(4, tetroCells.size());
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 0), 'I')));
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 1), 'I')));
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 2), 'I')));
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 3), 'I')));

    // moves down one
    model.clockTick();

    tetroCells = getBoardCellPositions(model);
    assertEquals(4, tetroCells.size());

    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 0), 'I')));
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 1), 'I')));
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 2), 'I')));
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 3), 'I')));
  }
}
