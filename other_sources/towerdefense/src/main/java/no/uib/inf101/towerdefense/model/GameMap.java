package no.uib.inf101.towerdefense.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.towerdefense.model.levels.IGameMap;
import no.uib.inf101.towerdefense.model.levels.LevelData;
import no.uib.inf101.towerdefense.model.levels.WaveData;

/**
 * @author Henrik Brøgger
 */
public class GameMap implements IGameMap {
  private HashMap<String, LevelData> mapJson;
  private String mapLevel;

  /**
   * Method that returns a list of ints describing which "maps" from the maps json exist.
   *
   * <p>Example return : [1,2,3] means that there are 3 valid map ints
   *
   * @return List of ints
   */
  public static ArrayList<Integer> getAllowedMapInts() {
    // use gson to map json to custom hashmap featuring leveldata
    Gson gson = new Gson();
    Type type = new TypeToken<HashMap<String, LevelData>>() {}.getType();

    HashMap<String, WaveData> mapHash = null;

    try {
      mapHash =
          gson.fromJson(
              new FileReader(
                  "src/main/java/no/uib/inf101/towerdefense/model/levels/level_settings.json"),
              type);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    ArrayList<Integer> mapInts = new ArrayList<>();

    for (String mapLevelStr : mapHash.keySet()) {
      Integer mapInt = Integer.parseInt(mapLevelStr);
      mapInts.add(mapInt);
    }

    return mapInts;
  }

  public GameMap(int rows, int cols, int mapLevel) {
    this.mapLevel = Integer.toString(mapLevel);

    // use gson to map json to custom hasmap featuring leveldata
    Gson gson = new Gson();
    Type type = new TypeToken<HashMap<String, LevelData>>() {}.getType();

    try {
      this.mapJson =
          gson.fromJson(
              new FileReader(
                  "src/main/java/no/uib/inf101/towerdefense/model/levels/level_settings.json"),
              type);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  @Override
  public HashMap<String, LevelData> getMapDict() {
    return this.mapJson;
  }

  @Override
  public CellPosition getStartPosition() {
    LevelData levelData = this.mapJson.get(this.mapLevel);
    return new CellPosition(levelData.startPos.get(0), levelData.startPos.get(1));
  }

  @Override
  public CellPosition getEndPosition() {
    LevelData levelData = this.mapJson.get(this.mapLevel);
    return new CellPosition(levelData.endPos.get(0), levelData.endPos.get(1));
  }
}
