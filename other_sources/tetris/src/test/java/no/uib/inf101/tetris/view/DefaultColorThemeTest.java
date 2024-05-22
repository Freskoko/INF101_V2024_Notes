package no.uib.inf101.tetris.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.Color;
import org.junit.jupiter.api.Test;

public class DefaultColorThemeTest {

  @Test
  public void sanityDefaultColorThemeTest() {

    ColorTheme colors = new DefaultColorTheme();
    assertEquals(Color.CYAN, colors.getBackgroundColor());
    assertEquals(Color.white.darker().darker(), colors.getFrameColor());
    assertEquals(Color.BLACK, colors.getCellColor('-'));
    assertThrows(IllegalArgumentException.class, () -> colors.getCellColor('\n'));
  }
}
