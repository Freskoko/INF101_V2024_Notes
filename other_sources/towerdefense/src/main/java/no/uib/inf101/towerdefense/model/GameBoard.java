package no.uib.inf101.towerdefense.model;

import java.util.HashMap;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;
import no.uib.inf101.towerdefense.model.levels.LevelData;
import no.uib.inf101.towerdefense.model.levels.PositionPair;

/**
 * @author Henrik Brøgger
 */
public class GameBoard extends Grid<String> implements IGameBoard {

  public enum GameBoardType {
    MAIN_BOARD,
    SIDE_BOARD,
    MAIN_MENU
  }

  private GameMap gameMap;
  private GameBoardType gameBoardType;

  /** Constructor for the main playable board */
  public GameBoard(int rows, int cols, int maplvl) {
    super(rows, cols, "-");
    this.gameMap = new GameMap(rows, cols, maplvl);
    // System.out.println(prettyString());
    drawBoardPath(this.gameMap.getMapDict(), String.valueOf(maplvl));
  }

  /** Constructor for the main menu and side view boards */
  public GameBoard(int rows, int cols) {
    super(rows, cols, "-");
  }

  @Override
  public void setMapLevel(int mapLvl) {
    this.gameMap = new GameMap(this.rows(), this.cols(), mapLvl);
  }

  @Override
  public void drawBoardPath(HashMap<String, LevelData> mapData, String levelId) {
    LevelData levelData = mapData.get(levelId);

    if (levelData != null) {
      // start
      this.set(new CellPosition(levelData.startPos.get(0), levelData.startPos.get(1)), "0");
      int index = 1;

      // draw each position pair
      for (PositionPair pair : levelData.boardLayout) {
        index = drawHorizontalMovement(pair, index);
        index = drawVerticalMovement(pair, index);
      }
      // end
      this.set(
          new CellPosition(levelData.endPos.get(0), levelData.endPos.get(1)),
          String.valueOf(index));
    }
  }

  /**
   * Helper method to draw a path moving horizontally
   *
   * @param pair a pair containing the start and end 
   * index of the line to draw horizontally
   * @param index the current start index of the path
   * @return The index from params + length of the drawn path
   */
  private int drawHorizontalMovement(PositionPair pair, int index) {
    int startX = pair.position1.get(0);
    int endX = pair.position2.get(0);
    int startY = pair.position1.get(1);

    if (startX != endX) {

      // determine direction of movement
      int increment = (startX < endX) ? 1 : -1;

      for (int x = startX; x != endX + increment; x += increment) {
        this.set(new CellPosition(x, startY), String.valueOf(index));
        index++;
      }
    }

    return index;
  }

  /**
   * Helper method to draw a path moving vertically
   *
   * @param pair a pair containing the start and end 
   * index of the line to draw vertically
   * @param index the current start index of the path
   * @return The index from params + length of the drawn path
   */
  private int drawVerticalMovement(PositionPair pair, int index) {
    int endX = pair.position2.get(0);
    int startY = pair.position1.get(1);
    int endY = pair.position2.get(1);

    if (startY != endY) {

      // determine direction of movement
      int increment = (startY < endY) ? 1 : -1;

      for (int y = startY; y != endY + increment; y += increment) {
        this.set(new CellPosition(endX, y), String.valueOf(index));
        index++;
      }
    }

    return index;
  }

  @Override
  public boolean cellPositionIsOccupied(CellPosition cp) {

    if (cp.row() < 0 || cp.col() < 0) {
      return false;
    }

    if (cp.row() >= this.rows() || cp.col() >= this.cols()) {
      return false;
    }

    return (this.get(cp) != "-");
  }

  @Override
  public String prettyString() {
    String boardString = "";

    for (int i = 0; i < this.rows(); i++) { // for each row
      for (int j = 0; j < this.cols(); j++) { // for each value in list
        boardString += (this.get(new CellPosition(i, j)));
      }
      if (i < this.rows() - 1) {
        boardString += "\n"; // add a newline to create board structure
      }
    }
    System.out.println(boardString);
    return boardString;
  }

  @Override
  public GameBoardType getGameBoardType() {
    return gameBoardType;
  }

  @Override
  public void setGameBoardType(GameBoardType gameBoardType) {
    this.gameBoardType = gameBoardType;
  }

  @Override
  public GameMap getGameMap() {
    return gameMap;
  }
}
