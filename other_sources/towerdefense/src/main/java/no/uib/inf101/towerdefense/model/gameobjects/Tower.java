package no.uib.inf101.towerdefense.model.gameobjects;

import java.util.ArrayList;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.towerdefense.model.gameobjects.gameentity.GameEntity;
import no.uib.inf101.towerdefense.view.viewUtils.GameVisualInformation;

/**
 * @author Henrik Brøgger, with assistance from chatGPT Created with assistance from ChatGPT OpenAI
 *     (2023). ChatGPT (20. april-versjon) [Stor språkmodell]. Hentet fra https://chat.openai.com.
 */
public class Tower extends GameEntity implements ITower {

  private int range;
  private int buyCost;
  private IEnemy target;
  private char bulletType;
  private long lastFiredTime;

  public Tower(
      char towerType,
      double drawScale,
      CellPosition cellPosition,
      int actionInterval,
      int buyCost,
      int range,
      char bulletType,
      IEnemy target) {

    super(towerType, drawScale, cellPosition, actionInterval, GameEntityType.TOWER);
    this.bulletType = bulletType;
    this.buyCost = buyCost;
    this.range = range;
    this.target = target;
  }

  /**
   * Tower constuctor which specifies tower options for the given tower type
   *
   * @param towerType the tower type as a char
   * @param initialPosition the cellposition to spawn the tower on
   * @return A tower with all options specified for its type
   */
  public static Tower newTower(char towerType, CellPosition cellPosition) {

    switch (towerType) {
      case 'w': // windows basic
        return new Tower(towerType, 1, cellPosition, 2500, 200, 6, 'w', null);
      case 'c': // mac close range fast
        return new Tower(towerType, 1, cellPosition, 2500, 500, 5, 'c', null);
      case 'l': // linux sniper
        return new Tower(towerType, 1, cellPosition, 2500, 1000, 30, 'l', null);
      case 'n': // nix machine gun
        return new Tower(towerType, 1, cellPosition, 100, 1000, 15, 'n', null);
      case 'm': // mint fast
        return new Tower(towerType, 1, cellPosition, 500, 1000, 10, 'm', null);
      case 'u': // ubuntu fast
        return new Tower(towerType, 1, cellPosition, 200, 1000, 10, 'u', null);
      case 't': // test tower with 0 action interval
        return new Tower(towerType, 1, cellPosition, 0, 200, 6, 't', null);
      default:
        throw new IllegalArgumentException("Invalid tower type" + towerType);
    }
  }

  @Override
  public Tower shiftedTo(CellPosition cp) {
    Tower copy = newTower(this.getEntitySpecificType(), cp);
    return copy;
  }

  @Override
  public IBullet doTickAction(ArrayList<IEnemy> enemies) {
    if (this.target == null) {
      updateTarget(enemies);
    }

    if (shouldFire()) {
      if (this.target == null) {
        return null;
      }
      return fireAtTarget();
    }

    return null;
  }

  /**
   * Updates the target enemy for the tower. 
   * If the current target does not exist or is no longer
   * active, a new target is selected from the given list of enemies. 
   * If no valid target is found, the target is set to null.
   *
   * @param enemies The list of enemies to choose the target from.
   */
  private void updateTarget(ArrayList<IEnemy> enemies) {
    if (this.target == null || !this.target.getExistenceStatus()) {
      IEnemy possibleTarget = null;
      // enemies in range
      ArrayList<IEnemy> enemiesInRange = new ArrayList<>();
      for (IEnemy enemy : enemies) {
        if (withinRange(enemy)) {
          enemiesInRange.add(enemy);
        }
      }
      possibleTarget = this.lockOnEnemyFirst(enemiesInRange);

      if (possibleTarget != null && possibleTarget.getExistenceStatus()) {
        this.target = possibleTarget;
      } else {
        this.target = null;
      }
    }
  }

  /**
   * Determines if an enemy is within this tower's range. 
   * See notes on Euclidean distance calculations here:
   * https://en.wikipedia.org/wiki/Euclidean_distance#:~:text=Two%20dimensions%5B,by%3A%5B2%5D
   *
   * @param enemy The enemy to check.
   * @return True if the enemy is within range, false otherwise.
   */
  private boolean withinRange(IEnemy enemy) {
    double dx = this.getCellPosition().row() - enemy.getCellPosition().row();
    double dy = this.getCellPosition().col() - enemy.getCellPosition().col();
    double distance = Math.sqrt((dx * dx) + (dy * dy));
    return distance <= this.range;
  }

  /**
   * Checks if the tower should fire at the current time. 
   * The tower should fire if it has a target
   * and the time elapsed since the last firing is greater than or equal to 
   * the firing interval.
   *
   * @return true if the tower should fire, false otherwise.
   */
  private boolean shouldFire() {
    long currentTime = System.currentTimeMillis();
    return this.target != null && (currentTime - this.lastFiredTime >= this.getActionInterval());
  }

  /**
   * Fires a bullet at the current target and resets the target to null.
   *
   * @return The bullet that was fired.
   */
  private IBullet fireAtTarget() {
    this.lastFiredTime = System.currentTimeMillis();
    IBullet bullet = this.shootEnemySpawnBullet(this.target);
    this.target = null; // reset the target after shooting
    return bullet;
  }

  /**
   * Returns the enemy on the map which is the furthest on the path
   *
   * @return The enemy that was found to be the furthest on the track or null, 
   * if no enemy was found
   */
  private IEnemy lockOnEnemyFirst(ArrayList<IEnemy> enemies) {
    IEnemy first = null;
    int firstValue = -1;

    for (IEnemy enemy : enemies) {

      if (enemy.getExistenceStatus() == false) {
        continue;
      }

      int enemyValue = enemy.getPathIndexValue();

      if (enemyValue > firstValue) {
        firstValue = enemyValue;
        first = enemy;
      }
    }
    return first;
  }

  /**
   * Fires a bullet and sets its target to the wall behind the target enemy
   *
   * @return The bullet
   */
  private IBullet shootEnemySpawnBullet(IEnemy target) {
    CellPosition edgeCellPosition =
        calculateEdgeCellPosition(this.getCellPosition(), target.getCellPosition());

    IBullet bullet = Bullet.newBullet(this.bulletType, this.getCellPosition(), edgeCellPosition);
    // bullet = bullet.shiftedTo(this.getCellPosition());

    return bullet;
  }

  /**
   * Calculates the position of the edge cell that the bullet should aim for 
   * in order to hit the target. 
   * The edge cell is calculated by extrapolating the line between the tower and the target
   * to the border of the game board.
   *
   * @param towerPosition The position of the tower.
   * @param targetPosition The position of the target.
   * @return The position of the edge cell.
   *     <p>learned ternary operator from here:
   *     https://www.w3schools.com/java/java_conditions_shorthand.asp variable = (condition) ?
   *     valueIfTrue : valueIfFalse;
   *     <p>Created with assistance from ChatGPT OpenAI (2023). ChatGPT (20. april-versjon)
   *     [Storspråkmodell]. Hentet fra https://chat.openai.com.
   */
  private CellPosition calculateEdgeCellPosition(
      CellPosition towerPosition, CellPosition targetPosition) {
    // each tower aims at the closest enemy by drawing a line at the enemy
    // the slope of the line we wish to draw is y / x
    // we extrapolate that line to the border of the board by drawing the line past
    // the enemy

    int boardHeight = GameVisualInformation.getBoardRows();
    int boardWidth = GameVisualInformation.getBoardCols();

    // should we shoot up/down? left/right?
    int xDirection = targetPosition.col() - towerPosition.col() >= 0 ? 1 : -1;
    int yDirection = targetPosition.row() - towerPosition.row() >= 0 ? 1 : -1;

    // y / x (rise over run)
    double slope =
        (double) (targetPosition.row() - towerPosition.row())
            / (targetPosition.col() - towerPosition.col());

    int extrapolatedCol, extrapolatedRow;

    if (Math.abs(slope) > 1) {
      // ff slope is greater than 1 hit the top/bottom edge first
      extrapolatedRow = yDirection > 0 ? boardHeight - 1 : 0;
      extrapolatedCol =
          (int) ((extrapolatedRow - towerPosition.row()) / slope) + towerPosition.col();

    } else {
      // otherwise hit the left/right edge first
      extrapolatedCol = xDirection > 0 ? boardWidth - 1 : 0;

      extrapolatedRow =
          (int) ((extrapolatedCol - towerPosition.col()) * slope) + towerPosition.row();
    }

    // make sure position is within board
    extrapolatedCol = Math.max(0, Math.min(boardWidth - 1, extrapolatedCol));
    extrapolatedRow = Math.max(0, Math.min(boardHeight - 1, extrapolatedRow));

    extrapolatedCol = Math.min(boardWidth - 1, extrapolatedCol);
    extrapolatedRow = Math.min(boardHeight - 1, extrapolatedRow);

    return new CellPosition(extrapolatedRow, extrapolatedCol);
  }

  @Override
  public int getPrice() {
    return this.buyCost;
  }
}
