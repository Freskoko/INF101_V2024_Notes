package no.uib.inf101.towerdefense.model.levels;

import java.util.HashMap;

public interface IGameWaveManager {

  /**
   * Retrieves the name of the next enemy in the game wave.
   *
   * @return The name of the next enemy, or null if there are no more enemies in the wave.
   */
  public Character getNextEnemyName();

  /**
   * Returns the map dictionary containing wave data. 
   * Wave data is made from the json file in levels directory.
   *
   * @return the map dictionary containing wave data
   */
  public HashMap<String, WaveData> getMapDict();

  /**
   * Sets the wave level of the game.
   *
   * @param waveLevel the new wave level
   */
  public void setWaveLevel(int waveLevel);
}
