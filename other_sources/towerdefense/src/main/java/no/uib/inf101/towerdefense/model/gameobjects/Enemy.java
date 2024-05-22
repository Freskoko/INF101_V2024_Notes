package no.uib.inf101.towerdefense.model.gameobjects;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.towerdefense.model.GameBoard;
import no.uib.inf101.towerdefense.model.gameobjects.gameentity.GameEntity;

/**
 * @author Henrik Brøgger
 */
public class Enemy extends GameEntity implements IEnemy {

  // movement
  private int speed;
  private long lastMovedTime;
  private int PathIndexValue = -1;

  // health
  private int health;

  private Enemy(
      int health,
      int speed,
      double drawScale,
      int actionInterval,
      char enemyType,
      CellPosition cellPosition) {
    super(enemyType, drawScale, cellPosition, actionInterval, GameEntityType.ENEMY);
    this.health = health;
    this.speed = speed;
  }

  /**
   * Enemy constuctor which specifies enemy options for the given enemy type
   *
   * @param enemyType the enemy type as a char
   * @param initialPosition the cellposition to spawn the enemy on
   * @return An enemy with all options specified for its type
   */
  public static IEnemy newEnemy(char enemyType, CellPosition initialPosition) {

    switch (enemyType) {
      case 'p':
        return new Enemy(100, 1, 1, 300, enemyType, initialPosition);
      case 'j':
        return new Enemy(50, 2, 0.8, 300, enemyType, initialPosition);
      case 'c':
        return new Enemy(300, 1, 1.8, 300, enemyType, initialPosition);
      case 't':
        // test enemy which moves instantly on every clocktick
        return new Enemy(100, 1, 1, 0, enemyType, initialPosition);
      default:
        throw new IllegalArgumentException("Invalid enemy type " + enemyType);
    }
  }

  @Override
  public int doTickAction(GameBoard gameBoard) {
    CellPosition currentCellPosition = this.getCellPosition();
    Integer currentValue = Integer.parseInt(gameBoard.get(currentCellPosition));

    // move and deal damage
    int damage = moveToNextPos(currentCellPosition, currentValue, gameBoard);

    return damage;
  }

  @Override
  public void takeDamage(int damage) {
    if (damage < 0) {
      throw new IllegalArgumentException("Damage cannot be negative");
    }
    if (this.health - damage <= 0) {
      this.health = 0;
      this.markForDeletion();
    } else {
      this.health -= damage;
    }
  }

  /**
   * Moves the enemy to the next position on the game board.
   *
   * @param currentCellPosition The current position of the enemy.
   * @param currentValue The current value of the current position's path spot.
   * @param gameBoard The game board.
   * @return The damage to deal to the player (default 0)
   */
  private int moveToNextPos(
      CellPosition currentCellPosition, int currentValue, GameBoard gameBoard) {
    // loop movement for speed
    for (int i = 0; i < speed; i++) {
      // find neighbour (up/down/left/right) with highest value
      CellPosition cellWithHighestValue =
          findCellWithHighestValue(gameBoard, currentCellPosition, currentValue);

      // move there
      if (gameBoard.cellPositionIsOccupied(cellWithHighestValue)) {
        long currentTime = System.currentTimeMillis();
        // adjust move interval based on enemy speed
        if (currentTime - this.lastMovedTime >= this.getActionInterval()) {
          this.setCellPosition(cellWithHighestValue);
          this.PathIndexValue = Integer.parseInt(gameBoard.get(cellWithHighestValue));
          this.lastMovedTime = currentTime; // Store the current time

          // update current position and value for the next iteration
          currentCellPosition = cellWithHighestValue;
          currentValue = this.PathIndexValue;
        }
      }

      // return if enemy reached the end position in this tick
      if (currentCellPosition.equals(gameBoard.getGameMap().getEndPosition())) {
        this.markForDeletion();
        return 10;
      }
    }
    return 0;
  }

  /**
   * Finds the neighboring cell with the highest value on the game board.
   *
   * @param gameBoard the game board
   * @param currentCellPosition the current cell position
   * @param currentValue the current cell value
   * @return the cell position with the highest value
   */
  private CellPosition findCellWithHighestValue(
      GameBoard gameBoard, CellPosition currentCellPosition, int currentValue) {
    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    CellPosition cellWithHighestValue = currentCellPosition;
    int highestValue = currentValue;

    for (int[] direction : directions) {
      CellPosition neighborCell = getNeighborCell(currentCellPosition, direction);
      if (gameBoard.cellPositionIsOccupied(neighborCell)) {
        Integer neighborValue = Integer.parseInt(gameBoard.get(neighborCell));
        if (neighborValue > highestValue) {
          highestValue = neighborValue;
          cellWithHighestValue = neighborCell;
        }
      }
    }

    return cellWithHighestValue;
  }

  /**
   * Returns the CellPosition of the neighboring cell in specified direction.
   *
   * @param currentCellPosition Current cell's position.
   * @param direction Array representing the direction of neighbor cell.
   * @return Position of the neighbor cell.
   */
  private CellPosition getNeighborCell(CellPosition currentCellPosition, int[] direction) {
    return new CellPosition(
        currentCellPosition.row() + direction[0], currentCellPosition.col() + direction[1]);
  }

  @Override
  public int getHealth() {
    return this.health;
  }

  @Override
  public int getPathIndexValue() {
    return this.PathIndexValue;
  }

  @Override
  public IEnemy shiftedTo(CellPosition cp) {
    IEnemy copy = newEnemy(this.getEntitySpecificType(), cp);
    return copy;
  }
}
