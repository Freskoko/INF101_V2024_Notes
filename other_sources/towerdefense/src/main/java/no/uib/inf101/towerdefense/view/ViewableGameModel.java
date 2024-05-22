package no.uib.inf101.towerdefense.view;

import java.awt.Point;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.towerdefense.model.GameBoard;
import no.uib.inf101.towerdefense.model.GameModel.GameState;
import no.uib.inf101.towerdefense.model.gameobjects.IBullet;
import no.uib.inf101.towerdefense.model.gameobjects.IEnemy;
import no.uib.inf101.towerdefense.model.gameobjects.ITower;
import no.uib.inf101.towerdefense.model.gameobjects.gameentity.IGameEntity;

/**
 * @author Henrik Brøgger
 */
public interface ViewableGameModel {

  /**
   * @return A gridDimension object with information about the rows and cols of the main game board
   */
  public GridDimension getDimensionGameBoard();

  /**
   * @return A gridDimension object with information about the rows and cols of the preview board on
   *     the right
   */
  public GridDimension getDimensionPreviewBoard();

  /**
   * @return A gridDimension object with information about the rows and cols of the main menu board
   */
  public GridDimension getDimensionMainMenu();

  /**
   * @return Iterable object which returns all path grid-cells on the board
   */
  public Iterable<GridCell<String>> getPathTiles();

  /**
   * @return The current gamestate from the enum GameState
   * @see GameState in GameModel
   */
  public GameState getGameState();

  /**
   * @return Current players game score
   */
  public String getGameScore();

  /**
   * @return The dimensions of the buyableitem grid on the right side of the board
   */
  public GridDimension getBuyableItemsGrid();

  /**
   * @return Iterable object which returns all the GameEnteties in the grid on the right side of the
   *     board
   */
  public Iterable<IGameEntity> getBuyableItemsTiles();

  /**
   * @return Iterable object which returns all main-menu grid-cells
   */
  public Iterable<GridCell<String>> getMainMenuTiles();

  /**
   * @return Iterable object containing all tower GameEntities on the board
   */
  public Iterable<ITower> getTowers();

  /**
   * @return Iterable object containing all bullet GameEntities on the board
   */
  public Iterable<IBullet> getBullets();

  /**
   * @return Iterable object containing all enemy GameEntities on the board
   */
  public Iterable<IEnemy> getEnemies();

  /**
   * @return The main-menu game board
   */
  public GameBoard getMainMenuBoard();

  /**
   * @return The GameEntity that the player most recently clicked
   */
  public IGameEntity getClickedEntity();

  /**
   * @return The current position of the player's mouse
   */
  public Point getCurrentMousePoint();

  /**
   * @return The GameBoard that the player most recently clicked
   * @see GameState in GameModel
   */
  public GameBoard getClickedGameBoard();

  /**
   * @return The current amount of currency (money) the player has
   */
  public int getCurrentCurrency();

  /**
   * @return The current amount of health the player has
   */
  public int getPlayerHealth();
}
