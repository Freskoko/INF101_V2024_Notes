package no.uib.inf101.towerdefense.model.levels;

import java.util.HashMap;
import no.uib.inf101.grid.CellPosition;

public interface IGameMap {

  /**
   * Method that returns a hashmap describing all the maps in the game. 
   * Maps are defined in `level_settings.json`
   *
   * @return Hashmap describing levels
   */
  public HashMap<String, LevelData> getMapDict();

  /**
   * @return A cellposition describing where enemies spawn on the current map
   */
  public CellPosition getStartPosition();

  /**
   * @return A cellposition describing where enemies leave the current map
   */
  public CellPosition getEndPosition();
}
