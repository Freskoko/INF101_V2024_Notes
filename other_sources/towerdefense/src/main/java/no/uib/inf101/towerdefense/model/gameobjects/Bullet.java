package no.uib.inf101.towerdefense.model.gameobjects;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.towerdefense.model.gameobjects.gameentity.GameEntity;

/**
 * @author Henrik Brøgger, with assistance from chatGPT Created with assistance from ChatGPT OpenAI
 *     (2023). ChatGPT (20. april-versjon) [Stor språkmodell]. Hentet fra https://chat.openai.com.
 */
public class Bullet extends GameEntity implements IBullet {

  // movement
  private final int speed;
  private long lastMovedTime;
  private final CellPosition cellPositionTarget;
  private Queue<CellPosition> path = new LinkedList<>();

  // damage
  private int damage = 100; // default

  private Bullet(
      char bulletType,
      int actionInterval,
      double drawScale,
      int speed,
      int damage,
      CellPosition cellPosition,
      CellPosition cellPositionTarget) {
    super(bulletType, drawScale, cellPosition, actionInterval, GameEntityType.BULLET);
    this.speed = speed;
    this.damage = damage;
    this.cellPositionTarget = cellPositionTarget;
    this.calculatePath(cellPositionTarget);
  }

  /**
   * Bullet constuctor which specifies bullet options for each bullet type
   *
   * @param bulletType the bullet type as a char
   * @param cellPosition the cellposition to spawn the bullet on
   * @param cellPositionTarget the target cellposition of the bullet
   * @return A bullet with all options specified for its type
   */
  public static IBullet newBullet(
      char bulletType, CellPosition cellPosition, CellPosition cellPositionTarget) {
    switch (bulletType) {
      case 'w':
        return new Bullet(bulletType, 300, 0.7, 1, 10, cellPosition, cellPositionTarget);
      case 'c':
        return new Bullet(bulletType, 15, 1.25, 1, 1000, cellPosition, cellPositionTarget);
      case 'l':
        return new Bullet(bulletType, 300, 0.5, 1, 10, cellPosition, cellPositionTarget);
      case 'n':
        return new Bullet(bulletType, 35, 3, 1, 10, cellPosition, cellPositionTarget);
      case 'u':
        return new Bullet(bulletType, 300, 3, 2, 250, cellPosition, cellPositionTarget);
      case 'm':
        return new Bullet(bulletType, 35, 3, 1, 5, cellPosition, cellPositionTarget);
      case 't': // test bullet 0 seconds move interval
        return new Bullet(bulletType, 0, 0.7, 1, 10, cellPosition, cellPositionTarget);
      default:
        throw new IllegalArgumentException("Invalid bullet type " + bulletType);
    }
  }

  @Override
  public void doTickAction(ArrayList<IEnemy> enemies) {

    if (path.isEmpty()) {
      this.calculatePath(cellPositionTarget);
    }

    if (this.cellPositionTarget.equals(this.getCellPosition())) {
      this.markForDeletion();
    }

    long currentTime = System.currentTimeMillis();
    if (currentTime - this.lastMovedTime > this.getActionInterval()) {
      moveAndIntercept(enemies);
      this.lastMovedTime = currentTime;
    }
  }

  /**
   * Moves the bullet towards the next position in the path 
   * and checks for collisions with enemies.
   * If a collision occurs, the bullet damages the enemy, 
   * and the bullet is marked for deletion.
   *
   * @param enemies the list of enemies in the game
   */
  private void moveAndIntercept(ArrayList<IEnemy> enemies) {
    for (int i = 0; i < speed; i++) {
      if (path.isEmpty()) {
        return;
      }
      CellPosition nextPos = path.poll();
      Boolean hasCollided = collideWithEnemies(enemies, nextPos);
      if (!hasCollided) {
        this.setCellPosition(nextPos);
      }
    }
  }

  /**
   * Calculates the path from the current position 
   * of the bullet to the target position using
   * Bresenham's line algorithm. 
   * The calculated path is stored in the 'path' list.
   *
   * @param target The target position to calculate the path to.
   *     <p>Created with assistance from ChatGPT OpenAI (2023). ChatGPT (20. april-versjon)
   *     [Storspråkmodell]. Hentet fra https://chat.openai.com.
   */
  private void calculatePath(CellPosition target) {
    // Return early if the target position is not defined
    if (target == null) {
      return;
    }

    // Get the current position of the bullet
    CellPosition currentPosition = this.getCellPosition();
    int currentColumn = currentPosition.col();
    int currentRow = currentPosition.row();

    // Extract the column and row of the target position
    int targetColumn = target.col();
    int targetRow = target.row();

    // Calculate the differences in position
    int columnDistance = Math.abs(targetColumn - currentColumn);
    int rowDistance = Math.abs(targetRow - currentRow);

    // Determine the step direction for x and y
    int stepX = (currentColumn < targetColumn) ? 1 : -1;
    int stepY = (currentRow < targetRow) ? 1 : -1;

    // Initialize the error term for Bresenham's algorithm
    int error = columnDistance - rowDistance;

    // Loop until the path reaches the target position
    while (true) {
      // Add the current position to the path
      path.add(new CellPosition(currentRow, currentColumn));

      // Check if the current position has reached the target position
      if (currentColumn == targetColumn && currentRow == targetRow) {
        break;
      }

      // Calculate the error term for the next step
      int error2 = 2 * error;

      // Adjust column position and error term if necessary
      if (error2 > -rowDistance) {
        error -= rowDistance;
        currentColumn += stepX;
      }

      // Check if the position reached target after adjusting column position
      if (currentColumn == targetColumn && currentRow == targetRow) {
        path.add(new CellPosition(currentRow, currentColumn));
        break;
      }

      // Adjust row position and error term if necessary
      if (error2 < columnDistance) {
        error += columnDistance;
        currentRow += stepY;
      }
    }
  }

  /**
   * Helper method that checks if the bullet collides with any enemies 
   * at the given cell position and damages them if so. 
   * Marks the bullet for deletion after collision.
   *
   * @param enemies the list of enemies to check collision against
   * @param cp the cell position to check collision at
   * @return true if the bullet collides with any enemy, false otherwise
   */
  private boolean collideWithEnemies(ArrayList<IEnemy> enemies, CellPosition cp) {
    for (IEnemy enemy : enemies) {
      CellPosition enemyPos = enemy.getCellPosition();

      if (cp.equals(enemyPos)) {
        enemy.takeDamage(this.damage);
        this.markForDeletion();
        return true;
      }
    }
    return false;
  }

  // getters & setters

  @Override
  public CellPosition getTargetCellPosition() {
    return this.cellPositionTarget;
  }

  @Override
  public IBullet shiftedTo(CellPosition cp) {
    IBullet bullet = newBullet(this.getEntitySpecificType(), cp, this.cellPositionTarget);
    return bullet;
  }
}
