package no.uib.inf101.towerdefense;

import no.uib.inf101.towerdefense.model.GameModel;

/**
 * @author Henrik Brøgger
 */
public class TestUtils {

  /*
   * A function used to create a test GameModel
   */
  protected GameModel initBoardUtils(int rows, int cols, int mapLevel, int waveLevel) {
    GameModel model = new GameModel(rows, cols);
    model.initWavesAndMap(waveLevel, mapLevel); // minus numbers used for testing
    return model;
  }
}
