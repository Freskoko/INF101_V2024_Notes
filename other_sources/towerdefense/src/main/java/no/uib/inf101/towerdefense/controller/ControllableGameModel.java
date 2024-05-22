package no.uib.inf101.towerdefense.controller;

import java.awt.Point;
import no.uib.inf101.towerdefense.model.GameModel.GameState;

public interface ControllableGameModel {

  /**
   * @return an int representing millis between ticks in timer (1000 = 1 second)
   */
  public int millisBetweenTimer();

  /**
   * Makes the game run one tick. Ticks happen within an interval.
   * During a clocktick all entities will do their tickaction 
   * and dead entities will be removed
   *
   * @see millisBetweenTimer()
   */
  public void clockTick();

  /**
   * Grabs the current state of the game
   *
   * @return any gamestate from the GameState enum
   */
  public GameState getGameState();

  /**
   * Handles mouse clicks
   *
   * @param point the point the mouse was clicked at
   */
  public void handleClick(Point point);

  /**
   * Determines current mouse point
   *
   * @param point the point the mouse was moved to
   */
  public void setCurrentMousePoint(Point point);

  /** Handles key presses */
  public void handleKeyPress();
}
