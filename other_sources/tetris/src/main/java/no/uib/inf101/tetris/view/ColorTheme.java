package no.uib.inf101.tetris.view;

import java.awt.Color;

public interface ColorTheme {

  /**
   * @param c Character used to grab corresponding color
   * @return Color to be used for object with Character c
   */
  Color getCellColor(Character c);

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
