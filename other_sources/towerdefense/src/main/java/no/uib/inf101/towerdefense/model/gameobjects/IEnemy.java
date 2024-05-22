package no.uib.inf101.towerdefense.model.gameobjects;

import no.uib.inf101.towerdefense.model.GameBoard;
import no.uib.inf101.towerdefense.model.gameobjects.gameentity.IGameEntity;

public interface IEnemy extends IGameEntity {

  /**
   * Performs the tick action for the enemy. 
   * This method is called on each tick of the game loop to
   * update the enemy's position and behavior.
   *
   * @param gameBoard the game board on which the enemy is located
   */
  public int doTickAction(GameBoard gameBoard);

  /**
   * Reduces the enemy's health by the specified amount of damage. 
   * Will mark the enemy for deletion if the health is below or equal to 0
   *
   * @param damage the amount of damage to be inflicted on the enemy
   */
  public void takeDamage(int damage);

  /**
   * Returns the health of the enemy.
   *
   * @return the health of the enemy
   */
  public int getHealth();

  /**
   * Returns the path index value of the enemy is standing on.
   *
   * @return the path index value of the enemy is standing on
   */
  public int getPathIndexValue();
}
