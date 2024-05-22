package no.uib.inf101.tetris.model.tetromino;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.tetris.model.TetrisBoard;
import org.junit.jupiter.api.Test;

/**
 * This class contains unit tests for the Tetromino class.
 * 
 * @author Henrik Brøgger, with assistance from ChatGPT
 */
public class TestTetromino {

  @Test
  public void testHashCodeAndEquals() {
    Tetromino t1 = Tetromino.newTetromino('T');
    Tetromino t2 = Tetromino.newTetromino('T');
    Tetromino t3 = Tetromino.newTetromino('T').shiftedBy(1, 0);
    Tetromino s1 = Tetromino.newTetromino('S');
    Tetromino s2 = Tetromino.newTetromino('S').shiftedBy(0, 0);

    assertEquals(t1, t2);
    assertEquals(s1, s2);
    assertEquals(t1.hashCode(), t2.hashCode());
    assertEquals(s1.hashCode(), s2.hashCode());
    assertNotEquals(t1, t3);
    assertNotEquals(t1, s1);
  }

  @Test
  public void tetrominoIterationOfT() {
    // Create a standard 'T' tetromino placed at (10, 100) to test
    Tetromino tetro = Tetromino.newTetromino('T');
    tetro = tetro.shiftedBy(10, 100);

    // Collect which objects are iterated through
    List<GridCell<Character>> objs = new ArrayList<>();
    for (GridCell<Character> gc : tetro) {
      objs.add(gc);
    }

    // Check that we got the expected GridCell objects
    assertEquals(4, objs.size());
    assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 100), 'T')));
    assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 101), 'T')));
    assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 102), 'T')));
    assertTrue(objs.contains(new GridCell<>(new CellPosition(12, 101), 'T')));
  }

  @Test
  public void tetrominoIterationOfS() {
    // Create a standard 'S' tetromino placed at (10, 100) to test
    Tetromino tetro = Tetromino.newTetromino('S');
    tetro = tetro.shiftedBy(10, 100);

    // Collect which objects are iterated through
    List<GridCell<Character>> objs = new ArrayList<>();
    for (GridCell<Character> gc : tetro) {
      objs.add(gc);
    }

    // Check that we got the expected GridCell objects
    assertEquals(4, objs.size());
    assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 101), 'S')));
    assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 102), 'S')));
    assertTrue(objs.contains(new GridCell<>(new CellPosition(12, 101), 'S')));
    assertTrue(objs.contains(new GridCell<>(new CellPosition(12, 100), 'S')));
  }

  @Test
  public void tetrominoMoveTest() {
    // checks if moving the piece twice
    // moves it double the distance of moving a piece once

    // first tetro
    Tetromino tetro = Tetromino.newTetromino('T');
    int distanceOne = tetro.getCellPosition().col();
    // move once
    tetro = tetro.shiftedBy(0, 1);

    int newColOne = tetro.getCellPosition().col();
    int diffOne = newColOne - distanceOne;

    // second tetro
    Tetromino tetro2 = Tetromino.newTetromino('T');
    int distanceTwo = tetro2.getCellPosition().col();
    // move once
    tetro2 = tetro2.shiftedBy(0, 2);

    int newColTwo = tetro2.getCellPosition().col();
    int diffTwo = newColTwo - distanceTwo;

    // Check that distance travelled of first is half that of double
    assertTrue(diffOne * 2 == diffTwo);
  }

  @Test
  public void shiftedToTopCenterOfTest() {

    // odd number board
    // peice with odd number of cols
    TetrisBoard tetrisBoard = new TetrisBoard(20, 11);
    Tetromino tetro = Tetromino.newTetromino('T');
    tetro = tetro.shiftedToTopCenterOf(tetrisBoard);
    assertEquals(tetro.getCellPosition().col(), 4);

    // odd number board
    // peice with even number of cols
    tetrisBoard = new TetrisBoard(20, 11);
    tetro = Tetromino.newTetromino('O');
    tetro = tetro.shiftedToTopCenterOf(tetrisBoard);
    assertEquals(tetro.getCellPosition().col(), 4);

    // even number board
    // peice with odd number of cols
    tetrisBoard = new TetrisBoard(20, 16);
    tetro = Tetromino.newTetromino('O');
    tetro = tetro.shiftedToTopCenterOf(tetrisBoard);
    assertEquals(tetro.getCellPosition().col(), 6);

    // even number board
    // peice with even number of cols
    tetrisBoard = new TetrisBoard(20, 16);
    tetro = Tetromino.newTetromino('O');
    tetro = tetro.shiftedToTopCenterOf(tetrisBoard);
    assertEquals(tetro.getCellPosition().col(), 6);
  }

  @Test
  public void rotateTest() {

    TetrisBoard tetrisBoard = new TetrisBoard(20, 11);
    Tetromino tetro = Tetromino.newTetromino('T');
    tetro = tetro.shiftedToTopCenterOf(tetrisBoard);
    assertEquals(tetro.getCellPosition().col(), 4);
  }
}
