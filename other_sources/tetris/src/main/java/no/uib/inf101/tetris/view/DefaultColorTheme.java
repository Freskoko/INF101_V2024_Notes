package no.uib.inf101.tetris.view;

import java.awt.Color;

public class DefaultColorTheme implements ColorTheme {

  @Override
  public Color getCellColor(Character c) {
    Color color = switch (c) {

      // empty cell
      case '-' -> Color.BLACK;

      // brick colours
      case 'T' -> Color.ORANGE;
      case 'S' -> Color.BLUE;
      case 'Z' -> Color.YELLOW;
      case 'O' -> Color.GREEN;
      case 'L' -> Color.RED;
      case 'J' -> Color.WHITE;
      case 'I' -> Color.PINK;

      // main text on screen
      case 'M' -> Color.MAGENTA;

      default -> throw new IllegalArgumentException("No available color for '" + c + "'");
    };
    return color;
  }

  @Override
  public Color getFrameColor() {
    return Color.WHITE.darker().darker();
  }

  @Override
  public Color getBackgroundColor() {
    return Color.CYAN;
  }

  @Override
  public Color getOverlayColour() {
    return new Color(0, 0, 0, 128);
  }

  @Override
  public Color getTextColour() {
    return Color.WHITE;
  }
}
