package no.uib.inf101.towerdefense.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.towerdefense.TestUtils;
import org.junit.jupiter.api.Test;

/**
 * @author Henrik Brøgger
 */
public class GameModelTest extends TestUtils {

  /** Checks that 20 walkable path tiles are created for the enemies. */
  @Test
  public void checkWalkablePathTiles() {

    GameModel model = initBoardUtils(20, 20, -1, -1);

    int sum = 0;
    Iterable<GridCell<String>> it = model.getPathTiles();

    for (GridCell<String> gridCell : it) {
      if (gridCell.value() != "-") { // "-" = non-path
        sum++;
      }
    }
    assert (sum == 20);
  }

  /** Checks that player health goes down when an enemy makes it to the end of the track */
  @Test
  public void checkPlayerHealthGoesDown() {
    GameModel model = initBoardUtils(20, 20, -1, -1);

    assertEquals(model.getPlayerHealth(), 100);

    // ONE enemy walks to end
    for (int i = 0; i <= 19; i++) {
      model.clockTick();
    }

    assertEquals(model.getPlayerHealth(), 90);
  }
}
