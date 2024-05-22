package no.uib.inf101.towerdefense.gameobjects.gameentity;

import no.uib.inf101.towerdefense.TestUtils;
import no.uib.inf101.towerdefense.model.GameModel;
import no.uib.inf101.towerdefense.model.gameobjects.IEnemy;
import org.junit.jupiter.api.Test;

/**
 * @author Henrik Brøgger
 */
public class EnemyTest extends TestUtils {

  @Test
  public void checkEnemyMovedTest() {

    GameModel model = initBoardUtils(20, 20, -1, -1);
    // minus one wave and map for tests
    model.clockTick(); // spawn enemies

    for (IEnemy enemy : model.getEnemies()) {
      assert enemy.getEntitySpecificType() == 't';
      assert enemy.getCellPosition().col() == 0;
      assert enemy.getCellPosition().row() == 0;
    }

    model.clockTick(); // move enemies

    for (IEnemy enemy : model.getEnemies()) {
      assert enemy.getEntitySpecificType() == 't';
      assert enemy.getCellPosition().col() == 1;
      assert enemy.getCellPosition().row() == 0;
    }
  }

  /** Test case to check if an enemy is dead when it walks to the end of the board and despawns. */
  @Test
  public void checkEnemyDeadTest() {

    GameModel model = initBoardUtils(20, 20, -1, -1);
    // minus one wave and map for tests
    model.clockTick(); // spawn enemies

    for (int i = 0; i < 20; i++) {
      model.clockTick(); // move enemies
    }

    assert !model.getEnemies().iterator().hasNext();
  }
}
