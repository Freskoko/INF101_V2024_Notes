package no.uib.inf101.towerdefense.model.levels;

import java.util.ArrayList;

/**
 * @author Henrik Brøgger
 */
public class WaveData {
  private ArrayList<ArrayList<Character>> waves;

  public ArrayList<ArrayList<Character>> getWaves() {
    return waves;
  }

  public void setWaves(ArrayList<ArrayList<Character>> waves) {
    this.waves = waves;
  }
}
