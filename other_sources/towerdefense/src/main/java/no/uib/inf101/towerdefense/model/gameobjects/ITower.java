package no.uib.inf101.towerdefense.model.gameobjects;

import java.util.ArrayList;
import no.uib.inf101.towerdefense.model.gameobjects.gameentity.IGameEntity;

// import no.uib.inf101.grid.CellPosition;

public interface ITower extends IGameEntity {

  /**
   * Performs the tick action of the tower, 
   * which includes updating the target enemy and firing a
   * bullet if necessary.
   *
   * @param enemies the list of enemies in the game
   * @return the bullet fired by the tower, or null if no bullet was fired
   */
  public IBullet doTickAction(ArrayList<IEnemy> enemies);

  /**
   * @return The cost of the tower as an int
   */
  public int getPrice();
}
