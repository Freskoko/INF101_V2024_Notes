package no.uib.inf101.towerdefense.gameobjects.gameentity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.towerdefense.TestUtils;
import no.uib.inf101.towerdefense.model.gameobjects.Bullet;
import no.uib.inf101.towerdefense.model.gameobjects.IBullet;
import no.uib.inf101.towerdefense.model.gameobjects.IEnemy;
import org.junit.jupiter.api.Test;

/**
 * @author Henrik Brøgger
 */
public class BulletTest extends TestUtils {

  /** Test case to check if the bullet moves towards its target and dies. */
  @Test
  public void checkBulletMovesToTargetAndDies() {
    IBullet bullet = Bullet.newBullet('t', new CellPosition(10, 10), new CellPosition(5, 5));

    ArrayList<IEnemy> enemies = new ArrayList<>();
    bullet.doTickAction(enemies);

    for (int i = 0; i < 400000; i++) {
      // give the bullet time to move
      bullet.doTickAction(enemies);
    }
    assertEquals(bullet.getCellPosition(), new CellPosition(5, 5));
    assertFalse(bullet.getExistenceStatus());
  }

  /** Test case to check if the bullet dies if it reaches the edge without hitting a target */
  @Test
  public void checkBulletDiesAtEdge() {

    IBullet bullet = Bullet.newBullet('t', new CellPosition(10, 10), new CellPosition(0, 0));
    ArrayList<IEnemy> enemies = new ArrayList<>();

    for (int i = 0; i < 400000; i++) {
      // give the bullet time to move
      bullet.doTickAction(enemies);
    }
    assertEquals(bullet.getCellPosition(), new CellPosition(0, 0));
    assertFalse(bullet.getExistenceStatus());
  }
}
