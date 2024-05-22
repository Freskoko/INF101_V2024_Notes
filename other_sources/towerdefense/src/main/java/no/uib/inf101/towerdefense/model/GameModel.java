package no.uib.inf101.towerdefense.model;

import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.towerdefense.controller.ControllableGameModel;
import no.uib.inf101.towerdefense.model.GameBoard.GameBoardType;
import no.uib.inf101.towerdefense.model.gameobjects.Enemy;
import no.uib.inf101.towerdefense.model.gameobjects.IBullet;
import no.uib.inf101.towerdefense.model.gameobjects.IEnemy;
import no.uib.inf101.towerdefense.model.gameobjects.ITower;
import no.uib.inf101.towerdefense.model.gameobjects.Tower;
import no.uib.inf101.towerdefense.model.gameobjects.gameentity.GameEntity.GameEntityType;
import no.uib.inf101.towerdefense.model.gameobjects.gameentity.IGameEntity;
import no.uib.inf101.towerdefense.model.levels.IGameWaveManager;
import no.uib.inf101.towerdefense.view.ViewableGameModel;
import no.uib.inf101.towerdefense.view.viewUtils.GameVisualInformation;
import no.uib.inf101.towerdefense.view.viewUtils.PixelToCellPositionConverter;

/**
 * @author Henrik Brøgger, with assistance from chatGPT Created with assistance from ChatGPT OpenAI
 *     (2023). ChatGPT (20. april-versjon) [Stor språkmodell]. Hentet fra https://chat.openai.com.
 */
public class GameModel implements ViewableGameModel, ControllableGameModel {

  // meta game stuff
  private GameState gameState = GameState.MAIN_MENU;
  private int currentCurrency = 5000; // default
  private int playerHealth = 100; // default

  // boards
  private GameBoard gameBoard;
  private GameBoard buyableItemsGrid;
  private GameBoard mainMenuBoard;

  // game entities
  private ArrayList<IEnemy> enemies;
  private ArrayList<ITower> towers;
  private ArrayList<IBullet> bullets;

  // mouse logic
  private IGameEntity clickedEntity;
  private Point currentMousePoint;
  private GameBoard clickedGameBoard;

  // enemy spawning
  private IGameWaveManager gameWaveManager;

  // game settings
  public int waveLevel = 1;
  public int mapLevel = 2;

  public GameModel(int rows, int cols) {

    // init main menu and side board
    this.mainMenuBoard = new GameBoard(12, 2);
    this.mainMenuBoard.setGameBoardType(GameBoardType.MAIN_MENU);

    this.buyableItemsGrid = new GameBoard(10, 2);
    this.buyableItemsGrid.setGameBoardType(GameBoardType.SIDE_BOARD);

    // init entities
    this.enemies = new ArrayList<IEnemy>();
    this.towers = new ArrayList<ITower>();
    this.bullets = new ArrayList<IBullet>();
  }

  public void initWavesAndMap(int newWaveLevel, int newMapLevel) {
    this.gameBoard =
        new GameBoard(
            GameVisualInformation.getBoardRows(),
            GameVisualInformation.getBoardCols(),
            newMapLevel);
    this.gameBoard.setGameBoardType(GameBoardType.MAIN_BOARD);

    this.gameWaveManager = new GameWaveManager(newWaveLevel);
    this.gameWaveManager.setWaveLevel(newWaveLevel);

    this.gameBoard.setMapLevel(newMapLevel);
    this.gameState = GameState.ACTIVE_GAME;
  }

  public enum GameState {
    MAIN_MENU,
    ACTIVE_GAME,
    GAME_OVER,
  }

  @Override
  public void clockTick() {

    // bullet logic
    ArrayList<IBullet> newBullets = new ArrayList<>();
    for (ITower tower : this.towers) {

      // tower needs to know about enemies to target them
      IBullet templateBullet = tower.doTickAction(this.enemies);

      if (templateBullet == null) {
        continue;
      }
      if (templateBullet.getExistenceStatus()) {
        newBullets.add(templateBullet);
      }
    }

    this.bullets.addAll(newBullets);
    // add all bullets got to total list to draw them

    // enemy logic
    for (IEnemy enemy : this.enemies) {
      // enemy needs to know about board to see where to go
      int damageTaken = enemy.doTickAction(this.gameBoard);

      if (damageTaken > 0) {

        if (playerHealth - damageTaken < 0) {
          playerHealth = 0;
          this.gameState = GameState.GAME_OVER;
        } else {
          this.playerHealth -= damageTaken;
        }
      }
    }

    // bullet logic
    for (IBullet bullet : this.bullets) {
      // bullet needs to know about enemies to damage them
      bullet.doTickAction(this.enemies);
    }

    removeDeadEntities(this.towers);
    removeDeadEntities(this.bullets);
    removeDeadEntities(this.enemies);

    // spawn enemies
    Character nextEnemyType = this.gameWaveManager.getNextEnemyName();

    if (nextEnemyType == null) {
      return;
    }
    if (nextEnemyType != '-') {

      IEnemy e = Enemy.newEnemy(nextEnemyType, this.gameBoard.getGameMap().getStartPosition());

      e.shiftedTo(gameBoard.getGameMap().getStartPosition());
      enemies.add(e);
    }
  }

  /**
   * Helper method which given a list of entities removes all entities which have 
   * their existanceStatus set to false.
   * Increases player currency if the removed entity is an enemy.
   *
   * @param entities The target position to calculate the path to.
   * @return The amount of removed enemies
   */
  private int removeDeadEntities(ArrayList<? extends IGameEntity> entities) {

    int removedCounter = 0;
    ArrayList<IGameEntity> entitiesToRemove = new ArrayList<>();
    for (IGameEntity entity : entities) {
      if (!entity.getExistenceStatus()) {

        entitiesToRemove.add(entity);
        removedCounter++;

        if (entity.getGameEntityType() == GameEntityType.ENEMY) {
          this.currentCurrency += 100; // money given on death
        }
      }
    }
    entities.removeAll(entitiesToRemove);
    return removedCounter;
  }

  @Override
  public Iterable<GridCell<String>> getMainMenuTiles() {
    ArrayList<GridCell<String>> mainMenuTiles = new ArrayList<>();
    mainMenuTiles.add(new GridCell<>(new CellPosition(0, 0), "Play"));
    mainMenuTiles.add(new GridCell<>(new CellPosition(2, 0), "maplevel ++"));
    mainMenuTiles.add(new GridCell<>(new CellPosition(3, 0), Integer.toString(this.mapLevel)));
    mainMenuTiles.add(new GridCell<>(new CellPosition(4, 0), "maplevel --"));
    mainMenuTiles.add(new GridCell<>(new CellPosition(6, 0), "wavelevel ++"));
    mainMenuTiles.add(new GridCell<>(new CellPosition(7, 0), Integer.toString(this.waveLevel)));
    mainMenuTiles.add(new GridCell<>(new CellPosition(8, 0), "wavelevel --"));
    mainMenuTiles.add(new GridCell<>(new CellPosition(10, 0), "Exit"));
    return mainMenuTiles;
  }

  @Override
  public Iterable<IGameEntity> getBuyableItemsTiles() {
    ArrayList<IGameEntity> buyableItems = new ArrayList<>();
    buyableItems.add(Tower.newTower('w', new CellPosition(0, 0)));
    buyableItems.add(Tower.newTower('c', new CellPosition(0, 1)));
    buyableItems.add(Tower.newTower('l', new CellPosition(1, 0)));
    buyableItems.add(Tower.newTower('n', new CellPosition(1, 1)));
    buyableItems.add(Tower.newTower('m', new CellPosition(2, 0)));
    buyableItems.add(Tower.newTower('u', new CellPosition(2, 1)));
    return buyableItems;
  }

  @Override
  public GridDimension getBuyableItemsGrid() {
    return this.buyableItemsGrid;
  }

  /**
   * Helper method to determine which GameBoard was most recently clicked
   *
   * @param point The mouse click position
   * @return a gameboard element from the GameBoard enum
   * @see GameBoard
   */
  private GameBoard determineClickedBoard(Point point) {
    Rectangle2D gameArea = GameVisualInformation.getGameArea();
    Rectangle2D previewArea = GameVisualInformation.getPreviewArea();
    Rectangle2D getMainMenuTextArea = GameVisualInformation.getMainMenuTextArea();

    if (this.gameState == GameState.MAIN_MENU) {
      if (getMainMenuTextArea.contains(point)) {
        return this.mainMenuBoard;
      }
    }

    if (this.gameState == GameState.ACTIVE_GAME) {
      if (gameArea.contains(point)) {
        return this.gameBoard;
      }
      if (previewArea.contains(point)) {
        return this.buyableItemsGrid;
      } else {
        return null;
      }
    }

    return null;
  }

  @Override
  public void handleClick(Point point) {

    GameBoard clickedGameBoard = determineClickedBoard(point);
    if (clickedGameBoard == null) {
      return;
    }
    this.setClickedGameBoard(clickedGameBoard);
    this.setCurrentMousePoint(point);

    if (clickedGameBoard.getGameBoardType().equals(GameBoardType.MAIN_MENU)) {
      handleMainMenuClick(point);
      return;
    }

    // this checks if it is required
    checkAndSpawnNewTower(point);

    if (clickedGameBoard != null) {
      updateCurrentlyClickedEntity(point);
    }
  }

  /**
   * Helper method to handle actions if the MainMenu was clicked
   *
   * @param point The which position on the board was clicked
   */
  private void handleMainMenuClick(Point point) {

    PixelToCellPositionConverter pixelConverter =
        new PixelToCellPositionConverter(
            GameVisualInformation.getMainMenuTextArea(), this.getMainMenuBoard());

    CellPosition cp = pixelConverter.getCellPositionForPixel(point.x, point.y);

    String cpValue = "";
    for (GridCell<String> gc : this.getMainMenuTiles()) {
      if (gc.pos().equals(cp)) {
        cpValue = gc.value();
      }
    }

    ArrayList<Integer> allowedWaveInts = GameWaveManager.getAllowedWaveInts();
    ArrayList<Integer> allowedMapInts = GameMap.getAllowedMapInts();

    int requestedChangeWave = 0;
    int requestedChangeMap = 0;

    if (cpValue.equals("Play")) {
      this.initWavesAndMap(this.waveLevel, this.mapLevel);
    }
    if (cpValue.equals("Exit")) {
      System.exit(0);
    }

    if (cpValue.equals("maplevel ++")) {
      requestedChangeMap += 1;
    }
    if (cpValue.equals("wavelevel ++")) {
      requestedChangeWave += 1;
    }

    if (cpValue.equals("maplevel --")) {
      requestedChangeMap -= 1;
    }
    if (cpValue.equals("wavelevel --")) {
      requestedChangeWave -= 1;
    }

    // logic to change map/wave based on request and if it is allowed
    if (allowedMapInts.contains(this.mapLevel + requestedChangeMap)) {
      this.mapLevel = this.mapLevel + requestedChangeMap;
    }

    if (allowedWaveInts.contains(this.waveLevel + requestedChangeWave)) {
      this.waveLevel = this.waveLevel + requestedChangeWave;
    }
  }

  /**
   * Helper method to spawn a new tower if applicable
   *
   * @param point The which position on the board that was clicked
   */
  private void checkAndSpawnNewTower(Point point) {

    IGameEntity e = this.clickedEntity;

    if (e == null) {
      return;
    }
    if (e.getGameEntityType() != GameEntityType.TOWER) {
      return;
    }
    if (this.clickedGameBoard.getGameBoardType() != GameBoardType.MAIN_BOARD) {
      return;
    }

    PixelToCellPositionConverter pixelConverter =
        new PixelToCellPositionConverter(GameVisualInformation.getGameArea(), this.gameBoard);
    CellPosition cp = pixelConverter.getCellPositionForPixel(point.x, point.y);

    IGameEntity possibleGameEntity = getGameEntityAtPosition(cp, this.clickedGameBoard);
    if (possibleGameEntity == null
        || possibleGameEntity.getGameEntityType() == GameEntityType.BULLET) {
      // is ok to spawn tower on null and bullets

      Tower t = Tower.newTower(this.clickedEntity.getEntitySpecificType(), cp);

      if (t.getPrice() <= this.currentCurrency) {
        this.currentCurrency -= t.getPrice();
        this.towers.add(t);
        this.clickedEntity = null;
      }
    }
  }

  /**
   * Helper method to update the currently clicked entity to a newly clicked one
   *
   * @param point The which position on the board that was clicked
   */
  private void updateCurrentlyClickedEntity(Point point) {
    Rectangle2D clickedBox;
    GridDimension gd;
    Point adjustedPoint = new Point(point);

    if (clickedGameBoard.getGameBoardType() == GameBoardType.MAIN_BOARD) {

      clickedBox = GameVisualInformation.getGameArea();
      gd = this.getDimensionGameBoard();

    } else { // side view

      clickedBox = GameVisualInformation.getPreviewArea();
      gd = this.getDimensionPreviewBoard();

      adjustedPoint.x = point.x - (int) clickedBox.getX();
      adjustedPoint.y = point.y - (int) clickedBox.getY();
    }
    PixelToCellPositionConverter pixelConverter = new PixelToCellPositionConverter(clickedBox, gd);

    CellPosition cellPosition =
        pixelConverter.getCellPositionForPixel(adjustedPoint.x, adjustedPoint.y);

    IGameEntity clickedItem = getGameEntityAtPosition(cellPosition, clickedGameBoard);

    if (clickedGameBoard.getGameBoardType() == GameBoardType.SIDE_BOARD) {
      this.setClickedEntity(clickedItem);
    }
  }

  /**
   * Helper method to grab which GameEntity (if any) is at the given CellPosition
   *
   * @param cp the CellPosition to check for entities at
   * @param gb the gameboard to look for entities from the GameBoard enum
   * @return the found GameEntity, or null if none was found
   */
  private IGameEntity getGameEntityAtPosition(CellPosition cp, GameBoard gb) {
    if (gb.getGameBoardType() == GameBoardType.MAIN_BOARD) {

      for (IEnemy e : this.enemies) {
        if (e.getCellPosition().equals(cp)) {
          return e;
        }
      }
      for (ITower t : this.towers) {
        if (t.getCellPosition().equals(cp)) {
          return t;
        }
      }
      for (IBullet b : this.bullets) {
        if (b.getCellPosition().equals(cp)) {
          return b;
        }
      }

      // this is a bit janky. I want it to return that the path square is "full"
      // however, the function needs to return a GameEntity.
      // Since a path is not a GameEntity, i return a tower. This is not ideal.
      for (GridCell<String> gc : this.getPathTiles()) {
        if (gc.pos().equals(cp)) {
          if (!gc.value().equals("-")) {

            Tower newTower = Tower.newTower('t', cp);
            return newTower;
          }
        }
      }
    }

    if (gb.getGameBoardType() == GameBoardType.SIDE_BOARD) {
      for (IGameEntity ge : this.getBuyableItemsTiles()) {
        CellPosition sideViewItemGc = ge.getCellPosition();

        if (sideViewItemGc.equals(cp)) {
          return ge;
        }
      }
    }

    return null;
  }

  @Override
  public Iterable<ITower> getTowers() {
    return new ArrayList<ITower>(this.towers);
  }

  @Override
  public Iterable<IBullet> getBullets() {
    return new ArrayList<IBullet>(this.bullets);
  }

  @Override
  public Iterable<IEnemy> getEnemies() {
    return new ArrayList<IEnemy>(this.enemies);
  }

  @Override
  public void handleKeyPress() {
    if (this.gameState == GameState.GAME_OVER) {
      System.exit(0);
    }
  }

  @Override
  public IGameEntity getClickedEntity() {
    return this.clickedEntity;
  }

  public void setClickedEntity(IGameEntity clickedEntity) {
    this.clickedEntity = clickedEntity;
  }

  @Override
  public GameState getGameState() {
    return this.gameState;
  }

  @Override
  public int millisBetweenTimer() { // TICKTIME
    return 1;
  }

  @Override
  public String getGameScore() {
    return null;
  }

  @Override
  public GridDimension getDimensionGameBoard() {
    return this.gameBoard;
  }

  @Override
  public GameBoard getMainMenuBoard() {
    return mainMenuBoard;
  }

  @Override
  public GridDimension getDimensionMainMenu() {
    return this.mainMenuBoard;
  }

  @Override
  public Iterable<GridCell<String>> getPathTiles() {
    return this.gameBoard;
  }

  @Override
  public GridDimension getDimensionPreviewBoard() {
    return this.buyableItemsGrid;
  }

  public Point getCurrentMousePoint() {
    return currentMousePoint;
  }

  @Override
  public void setCurrentMousePoint(Point point) {
    this.currentMousePoint = point;
  }

  @Override
  public GameBoard getClickedGameBoard() {
    return clickedGameBoard;
  }

  public void setClickedGameBoard(GameBoard clickedGameBoard) {
    this.clickedGameBoard = clickedGameBoard;
  }

  public int getCurrentCurrency() {
    return currentCurrency;
  }

  public int getPlayerHealth() {
    return playerHealth;
  }

  public GameBoard getGameBoard() {
    return gameBoard;
  }
}
