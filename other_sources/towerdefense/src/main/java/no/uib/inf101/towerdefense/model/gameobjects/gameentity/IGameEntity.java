package no.uib.inf101.towerdefense.model.gameobjects.gameentity;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.towerdefense.model.gameobjects.gameentity.GameEntity.GameEntityType;

public interface IGameEntity {

  /**
   * ActionInterval describes how often an entity should carry out their action
   *
   * @return an int representing how many milliseconds between actions for this entity
   */
  public int getActionInterval();

  /**
   * Returns the existence status of the game entity.
   *
   * @return true if the game entity exists, false otherwise
   */
  public boolean getExistenceStatus();

  /**
   * Returns the cell position of the game entity.
   *
   * @return the cell position of the game entity
   */
  public CellPosition getCellPosition();

  /**
   * Returns the game entity type.
   *
   * @return the game entity as an element from the GameEntityType enum
   */
  public GameEntityType getGameEntityType();

  /**
   * Sets the game entity type.
   *
   * @param gameEntityType the new game entity type
   */
  public void setGameEntityType(GameEntityType gameEntityType);

  /**
   * Sets the cell position of the game entity.
   *
   * @param cellPosition the new cell position of the game entity
   */
  public void setCellPosition(CellPosition cellPosition);

  /**
   * Creates a copy of the game entity and sets the cell position of the copy entity to the
   * specified cell position. Returns the copy of the game entity.
   *
   * @param cp the cell position for the copied game entity
   * @return the copied game entity with the specified cell position
   */
  public IGameEntity shiftedTo(CellPosition cp);

  /**
   * Marks the game entity for deletion. 
   * This method is used to indicate that the game entity should
   * be removed from the game. 
   * Once marked for deletion, the game entity will be removed in the next
   * update cycle.
   */
  public void markForDeletion();

  /**
   * Sets the entity specific type of the game entity.
   *
   * @param entitySpecificType the entity specific type of the game entity
   */
  public void setEntitySpecificType(char entitySpecificType);

  /**
   * Returns the entity specific type of the game entity.
   *
   * @return the entity specific type of the game entity
   */
  public char getEntitySpecificType();

  /**
   * Returns the draw scale of the game entity.
   *
   * @return the draw scale of the game entity
   */
  public double getDrawScale();
}
