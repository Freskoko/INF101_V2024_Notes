package no.uib.inf101.towerdefense.model;

import java.util.HashMap;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.towerdefense.model.GameBoard.GameBoardType;
import no.uib.inf101.towerdefense.model.levels.LevelData;

public interface IGameBoard {

  /**
   * Method that sets the current maplevel to `mapLvl`
   *
   * @param mapLvl what to set the map level to
   */
  public void setMapLevel(int mapLvl);

  /**
   * Draws the path tiles that enemies walk on
   *
   * @param mapData The json containing all map data
   * @param levelId which maplevel to draw the path of
   */
  public void drawBoardPath(HashMap<String, LevelData> mapData, String levelId);

  /**
   * Method to see if the given cellposition contains a gameEntity
   *
   * @param cp The cellposition to check
   * @return boolean representing if the cellposition is not empty
   */
  public boolean cellPositionIsOccupied(CellPosition cp);

  /**
   * @return A string representation of the GameBoard
   */
  public String prettyString();

  /**
   * @return A the current type of the gameboard (from enum GameBoardType)
   */
  public GameBoardType getGameBoardType();

  /** Sets the gameboard to an option from the enum GameBoardType */
  public void setGameBoardType(GameBoardType gameBoardType);

  /**
   * @return A the current GameMap of the GameBoard
   */
  public GameMap getGameMap();
}
