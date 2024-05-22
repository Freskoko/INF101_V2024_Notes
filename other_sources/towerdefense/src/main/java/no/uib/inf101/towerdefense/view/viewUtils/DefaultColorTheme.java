package no.uib.inf101.towerdefense.view.viewUtils;

import java.awt.Color;

/**
 * @author Henrik Brøgger
 */
public class DefaultColorTheme implements ColorTheme {

  @Override
  public Color getEnemyColor(char i) {
    if (i == 'b') {
      return Color.BLUE;
    }
    ;
    if (i == 'i') {
      return Color.YELLOW;
    }
    ;
    if (i == 'h') {
      return Color.BLACK;
    }
    ;
    return Color.WHITE;
  }

  @Override
  public Color getTowerColor(char i) {
    if (i == 'b') {
      return Color.ORANGE;
    }
    ;
    if (i == 'i') {
      return Color.YELLOW;
    }
    ;
    if (i == 'h') {
      return Color.GREEN.darker().darker();
    }
    ;
    if (i == 's') {
      return Color.PINK;
    }
    ;
    if (i == 'l') {
      return Color.GREEN;
    }
    ;
    return Color.RED;
  }

  @Override
  public Color getBulletColor(char i) {
    if (i == 'b') {
      return Color.ORANGE;
    }
    ;
    if (i == 'i') {
      return Color.YELLOW;
    }
    ;
    if (i == 'f') {
      return Color.GREEN.darker().darker();
    }
    ;
    if (i == 'l') {
      return Color.RED;
    }
    ;
    if (i == 't') {
      return Color.GREEN;
    }
    ;
    return new Color(255, 255, 0, 128); // yellow color transparency
  }

  @Override
  public Color getCellColor(char s) {
    Color color =
        switch (s) {
            // empty cell
          case '-' -> new Color(0, 0, 0, 0);

          case 'b' -> Color.BLUE;

            // main text on screen
          case 'M' -> Color.MAGENTA;

          default -> throw new IllegalArgumentException("No available color for " + s);
        };
    return color;
  }

  @Override
  public Color getMainMenuTextColour() {
    return Color.WHITE;
  }

  @Override
  public Color getPathColor(String i) {
    return Color.WHITE;
  }

  @Override
  public Color getTransparentColor() {
    return new Color(0, 0, 0, 0);
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
    return Color.BLACK;
  }
}
