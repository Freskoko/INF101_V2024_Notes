package no.uib.inf101.grid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Grid<E> implements IGrid<E> {
  private int rows;
  private int cols;
  private ArrayList<ArrayList<GridCell<E>>> grid;

  /**
   * // Init a grid, creates a grid of GridCells with value = defaultGridValue
   *
   * @param rows
   * @param cols
   * @param defaultGridVal
   */
  public Grid(int rows, int cols, E defaultGridVal) {

    this.rows = rows;
    this.cols = cols;
    this.grid = initGrid(defaultGridVal);
  }

  /**
   * // Init a grid, creates a grid of GridCells with value = null
   *
   * @param rows
   * @param cols
   */
  public Grid(int rows, int cols) {

    this.rows = rows;
    this.cols = cols;
    this.grid = initGrid(null);
  }

  /**
   * @param defaultGridVal
   * @return A grid of size this.rows, this.cols, with all values being
   *         defaultValue
   */
  private ArrayList<ArrayList<GridCell<E>>> initGrid(E defaultGridVal) {

    ArrayList<ArrayList<GridCell<E>>> tempGrid = new ArrayList<>(); 
    // get grid from class field variables
    for (int i = 0; i < this.rows; i++) {
      ArrayList<GridCell<E>> rowList = new ArrayList<>(); 
      for (int j = 0; j < this.cols; j++) { 
        CellPosition cellPos = new CellPosition(i, j); // make a cellposition
        GridCell<E> colorObject = new GridCell<E>(cellPos, defaultGridVal); 
        // make a cellcolor using the cellposition
        rowList.add(colorObject); // add color to the newly made row list
      }
      tempGrid.add(rowList); // add list to this.grid list
    }
    return tempGrid;
  }

  @Override
  public E get(CellPosition pos) {
    ArrayList<GridCell<E>> row = this.grid.get(pos.row()); 
    return row.get(pos.col()).value(); 
    // grab GridCell and its Color from col index of row list
  }

  @Override
  public void set(CellPosition pos, E obj) {
    ArrayList<GridCell<E>> row = this.grid.get(pos.row());
    GridCell<E> gridCell = new GridCell<E>(pos, obj);
    row.set(pos.col(), gridCell); 
    // in row from grid, change gridCell at col index to new gridCell
    this.grid.set(
        pos.row(),
        row); 
    // in this.grid, set the newly updated rowlist to the row at index pos.row()
  }

  @Override
  public boolean positionIsOnGrid(CellPosition pos) {

    try {
      this.get(pos);
      return (true);
    } catch (IndexOutOfBoundsException indexError) {
      return (false);
    }
  }

  @Override
  public int rows() {
    return (this.rows);
  }

  @Override
  public int cols() {
    return (this.cols);
  }

  /**
   * @return An iterator which can be used to loop over GridCell<E> objects
   *         representing the grid
   */
  public Iterator<GridCell<E>> iterator() {
    List<GridCell<E>> positions = new ArrayList<GridCell<E>>(); 
    // make new list to store all gridcells

    for (int i = 0; i < rows; i++) { // for each row
      ArrayList<GridCell<E>> row = grid.get(i); // grab row list
      for (int j = 0; j < cols; j++) { // for each value in list
        positions.add(row.get(j)); // add value at index col (j) to positions
      }
    }
    return positions.iterator();
  }
}
