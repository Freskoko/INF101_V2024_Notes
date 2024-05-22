package no.uib.inf101.towerdefense.model.gameobjects;

import java.util.ArrayList;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.towerdefense.model.gameobjects.gameentity.IGameEntity;

public interface IBullet extends IGameEntity {

  /**
   * Performs the tick action for the bullet. 
   * If the bullet has reached its target location or is in
   * the same position as an enemy, it damages them and marks itself for deletion.
   * Otherwise, itmoves towards the target location 
   * and damages any enemies in its path.
   *
   * @param enemies the list of enemies in the game
   */
  public void doTickAction(ArrayList<IEnemy> enemies);

  /**
   * Returns the target cell position of the bullet.
   *
   * @return the target cell position of the bullet
   */
  public CellPosition getTargetCellPosition();
}
