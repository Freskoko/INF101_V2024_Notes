package no.uib.inf101.towerdefense.view.viewUtils;

import java.awt.Color;

/**
 * @author Henrik Brøgger
 */
public interface ColorTheme {

  public Color getPathColor(String i);

  /**
   * @param c Character used to grab corresponding color
   * @return Color to be used for object with Character c
   */
  public Color getCellColor(char s);

  /**
   * @param c Character used to grab corresponding color
   * @return Color to be used for enemy with type c
   */
  public Color getEnemyColor(char s);

  /**
   * @param c Character used to grab corresponding color
   * @return Color to be used for tower with type c
   */
  public Color getTowerColor(char i);

  /**
   * @param c Character used to grab corresponding color
   * @return Color to be used for bullet with type c
   */
  public Color getBulletColor(char i);

  /**
   * @return Transparent color to draw path tiles
   */
  public Color getTransparentColor();

  /**
   * @return Text color to use in the main menu
   */
  public Color getMainMenuTextColour();

  /**
   * @return The frame color to show on objects on the board
   */
  Color getFrameColor();

  /**
   * @return The background color to show on the board
   */
  Color getBackgroundColor();

  /**
   * @return The overlay colour to place over the board on a lost game
   */
  Color getOverlayColour();

  /**
   * @return The color to draw text in
   */
  Color getTextColour();
}
