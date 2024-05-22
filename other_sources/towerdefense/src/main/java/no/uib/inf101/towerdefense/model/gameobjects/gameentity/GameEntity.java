package no.uib.inf101.towerdefense.model.gameobjects.gameentity;

import no.uib.inf101.grid.CellPosition;

/**
 * @author Henrik Brøgger
 */
public class GameEntity implements IGameEntity {

  public enum GameEntityType {
    TOWER,
    ENEMY,
    BULLET
  }

  // default values
  private boolean existanceStatus = true;
  private double drawScale = 1; // default
  private int actionInterval = 300; // default

  private CellPosition cellPosition;
  private char entitySpecificType;
  private GameEntityType gameEntityType;

  public GameEntity(
      char entityType,
      double drawScale,
      CellPosition cellPosition,
      int actionInterval,
      GameEntityType gameEntityType) {

    // default constructor for all entites
    this.setEntitySpecificType(entityType);
    this.setCellPosition(cellPosition);
    this.setGameEntityType(gameEntityType);
    this.drawScale = drawScale;
    this.actionInterval = actionInterval;
  }

  @Override
  public int getActionInterval() {
    return actionInterval;
  }

  @Override
  public double getDrawScale() {
    return drawScale;
  }

  @Override
  public GameEntityType getGameEntityType() {
    return gameEntityType;
  }

  @Override
  public void setGameEntityType(GameEntityType gameEntityType) {
    this.gameEntityType = gameEntityType;
  }

  @Override
  public char getEntitySpecificType() {
    return entitySpecificType;
  }

  @Override
  public void setEntitySpecificType(char entitySpecificType) {
    this.entitySpecificType = entitySpecificType;
  }

  @Override
  public boolean getExistenceStatus() {
    return this.existanceStatus;
  }

  @Override
  public void markForDeletion() {
    this.existanceStatus = false;
  }

  @Override
  public CellPosition getCellPosition() {
    return this.cellPosition;
  }

  @Override
  public void setCellPosition(CellPosition cellPosition) {
    this.cellPosition = cellPosition;
  }

  @Override
  public IGameEntity shiftedTo(CellPosition cp) {
    IGameEntity copy =
        new GameEntity(
            this.entitySpecificType, this.drawScale, cp, this.actionInterval, this.gameEntityType);
    return copy;
  }
}
