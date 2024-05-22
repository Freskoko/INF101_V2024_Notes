package no.uib.inf101.towerdefense.gameobjects.gameentity;

import java.util.ArrayList;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.towerdefense.TestUtils;
import no.uib.inf101.towerdefense.model.GameModel;
import no.uib.inf101.towerdefense.model.gameobjects.Enemy;
import no.uib.inf101.towerdefense.model.gameobjects.IBullet;
import no.uib.inf101.towerdefense.model.gameobjects.IEnemy;
import no.uib.inf101.towerdefense.model.gameobjects.Tower;
import org.junit.jupiter.api.Test;

/**
 * @author Henrik Brøgger
 */
public class TowerTest extends TestUtils {

  @Test
  public void checkTowerSpawnBulletTest() {

    GameModel model = initBoardUtils(20, 20, -1, -1);

    // init tower
    Tower tower = Tower.newTower('t', new CellPosition(2, 2));
    ArrayList<IEnemy> enemies = new ArrayList<>();
    tower.doTickAction(enemies);

    // add enemy
    IEnemy enemy = Enemy.newEnemy('t', new CellPosition(0, 0));
    enemies.add(enemy);
    enemy.doTickAction(model.getGameBoard());

    // tower should spawn bullet
    tower.doTickAction(enemies);

    IBullet spawnedBullet = tower.doTickAction(enemies);

    // spawns null on no bullet
    assert (spawnedBullet != null);

    // check target
    assert (spawnedBullet.getTargetCellPosition().equals(new CellPosition(0, 1)));
  }
}
