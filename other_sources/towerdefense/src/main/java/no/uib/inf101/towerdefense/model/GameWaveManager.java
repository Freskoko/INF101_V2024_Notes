package no.uib.inf101.towerdefense.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import no.uib.inf101.towerdefense.model.levels.IGameWaveManager;
import no.uib.inf101.towerdefense.model.levels.WaveData;

/**
 * @author Henrik Brøgger
 */
public class GameWaveManager implements IGameWaveManager {

  private int waveLevel;

  private HashMap<String, WaveData> waveData;
  private long lastSpawnedTime;
  private int spawningInterval = 500;
  private int betweenWaveTimer = 5000;
  private int currentStrIndex = 0;

  /**
   * A GameWaveManager instance is much like an enemy factory, 
   * but its inputs for different waves are read from a json.
   */
  public GameWaveManager(int waveLevel) {
    this.waveLevel = waveLevel;

    // use gson to map json to custom hasmap featuring leveldata
    Gson gson = new Gson();
    Type type = new TypeToken<HashMap<String, WaveData>>() {}.getType();

    try {
      this.waveData =
          gson.fromJson(
              new FileReader(
                  "src/main/java/no/uib/inf101/towerdefense/model/levels/wave_settings.json"),
              type);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Character getNextEnemyName() {
    ArrayList<ArrayList<Character>> waves =
        this.waveData.get(Integer.toString(this.waveLevel)).getWaves();

    // check if there are no waves left
    if (waves.isEmpty()) return null;

    ArrayList<Character> wave = waves.get(0); // always get the first wave

    // when all enemies of a wave have been looped through,
    // remove it, reset index and set lastSpawnedTime to delay
    if (currentStrIndex >= wave.size()) {
      waves.remove(0);
      currentStrIndex = 0;
      lastSpawnedTime = System.currentTimeMillis(); // set the delay for next wave
    }

    // if there is a delay between waves
    if (currentStrIndex == 0 && System.currentTimeMillis() - lastSpawnedTime < betweenWaveTimer)
      return null;

    // check if there is delay before spawning next enemy
    if (System.currentTimeMillis() - lastSpawnedTime < spawningInterval) return null;

    Character enemyCharacter = wave.get(currentStrIndex++);

    // enemy spawned, now set spawning time to now
    lastSpawnedTime = System.currentTimeMillis();

    if (enemyCharacter.equals('-')) { // empty spawn
      return null;
    } else {
      return enemyCharacter;
    }
  }

  @Override
  public HashMap<String, WaveData> getMapDict() {
    return this.waveData;
  }

  /**
   * Method that returns a list of ints describing which "waves" from the waves json exist.
   *
   * <p>Example return : [1,2,3] means that there are 3 valid wave ints
   *
   * @return List of ints
   */
  public static ArrayList<Integer> getAllowedWaveInts() {

    // use gson to map json to custom hasmap featuring leveldata
    Gson gson = new Gson();
    Type type = new TypeToken<HashMap<String, WaveData>>() {}.getType();

    HashMap<String, WaveData> waveJson = null;

    try {
      waveJson =
          gson.fromJson(
              new FileReader(
                  "src/main/java/no/uib/inf101/towerdefense/model/levels/wave_settings.json"),
              type);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    ArrayList<Integer> waveInts = new ArrayList<>();

    for (String waveLevelStr : waveJson.keySet()) {
      Integer waveInt = Integer.parseInt(waveLevelStr);
      waveInts.add(waveInt);
    }

    return waveInts;
  }

  @Override
  public void setWaveLevel(int waveLevel) {
    this.waveLevel = waveLevel;
  }
}
