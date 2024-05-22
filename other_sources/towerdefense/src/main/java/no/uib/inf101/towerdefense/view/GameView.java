package no.uib.inf101.towerdefense.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.towerdefense.model.GameModel.GameState;
import no.uib.inf101.towerdefense.model.gameobjects.gameentity.GameEntity.GameEntityType;
import no.uib.inf101.towerdefense.model.gameobjects.gameentity.IGameEntity;
import no.uib.inf101.towerdefense.view.viewUtils.CellPositionToPixelConverter;
import no.uib.inf101.towerdefense.view.viewUtils.ColorTheme;
import no.uib.inf101.towerdefense.view.viewUtils.DefaultColorTheme;
import no.uib.inf101.towerdefense.view.viewUtils.GameVisualInformation;

/**
 * @author Henrik Brøgger, with assistance from chatGPT Created with assistance from ChatGPT OpenAI
 *     (2023). ChatGPT (20. april-versjon) [Stor språkmodell]. Hentet fra https://chat.openai.com.
 */
public class GameView extends JPanel {

  private ViewableGameModel viewableGameModel;
  private ColorTheme colorTheme;

  public GameView(ViewableGameModel viewableGameModel) {
    this.colorTheme = new DefaultColorTheme();
    this.viewableGameModel = viewableGameModel;

    this.setFocusable(true);
    this.setPreferredSize(
        new Dimension(
            GameVisualInformation.getWindowLength(), GameVisualInformation.getWindowHeight()));
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;

    if (viewableGameModel.getGameState() == GameState.ACTIVE_GAME) {
      drawGame(g2);
      drawPreview(g2);
      drawGameText(g2);
      drawMouseitem(g2);
    }
    if (viewableGameModel.getGameState() == GameState.MAIN_MENU) {
      drawMainMenu(g2);
    }

    if (viewableGameModel.getGameState() == GameState.GAME_OVER) {
      drawGameOver(g2);
    }
  }

  /**
   * Draws the game-over screen
   *
   * @param g the graphics to use when drawing
   */
  private void drawGameOver(Graphics2D g) {
    int x = GameVisualInformation.getWindowHeight() / 3;
    int y = GameVisualInformation.getWindowLength() / 3;
    g.setFont(g.getFont().deriveFont(45F));
    g.setColor(colorTheme.getTextColour());
    g.drawString("!!! Game Over !!! ", x, y);
    g.drawString("press any key to exit", x, y + 100); // offset text
  }

  /**
   * Draws the main-menu screen
   *
   * @param g the graphics to use when drawing
   */
  private void drawMainMenu(Graphics2D g) {

    CellPositionToPixelConverter cellPosPixConv =
        new CellPositionToPixelConverter(
            GameVisualInformation.getMainMenuTextArea(),
            viewableGameModel.getDimensionMainMenu(),
            0);

    // draw  background
    BufferedImage img = GameVisualInformation.getImages().get("meta/machinevslanguage2.jpg");
    g.drawImage(img, 0, 0, null);

    drawTextCells(g, viewableGameModel.getMainMenuTiles(), cellPosPixConv, colorTheme);
    // main menu box around
    g.setColor(Color.RED);
  }

  /**
   * Draws the background image on the game
   *
   * @param g the graphics to use when drawing
   * @param imageName name of image used when drawing background
   */
  private void drawBackgroundImage(Graphics2D graphic, String imageName) {
    // get gamebackgroundflowers.jpg image from the HashMap
    BufferedImage img = GameVisualInformation.getImages().get(imageName);
    graphic.drawImage(img, 0, 0, null);
  }

  /**
   * Method responsible for drawing cells
   *
   * @param graphic Object to use when drawing
   * @param cellCollection Iterable contaning GridCells to be drawn
   * @param cellPosPixConv Method used to find pixels to draw cell
   * @param colorTheme Method used when finding colors to draw with for each cell
   */
  private static void drawCells(
      Graphics2D graphic,
      Iterable<GridCell<String>> cellCollection,
      CellPositionToPixelConverter cellPosPixConv,
      ColorTheme colorTheme,
      Boolean doText) {

    int x = -99;
    int y = -99;

    for (GridCell<String> cellObj : cellCollection) {

      Rectangle2D cellRectangle = cellPosPixConv.getBoundsForCell(cellObj.pos());
      String value = cellObj.value();

      if (value.matches("\\d+")) { // if value is a number
        graphic.setColor(colorTheme.getPathColor(value));

        graphic.setColor(colorTheme.getBackgroundColor());

        graphic.setColor(colorTheme.getPathColor(value));
      }

      if (value.equals("-")) {
        graphic.setColor(colorTheme.getTransparentColor());
      }

      graphic.draw(cellRectangle);
      graphic.fill(cellRectangle);

      if (doText) {
        // calculate the x and y position at the center of the rectangle
        x = (int) (cellRectangle.getX() + cellRectangle.getWidth() / 2);
        y = (int) (cellRectangle.getY() + cellRectangle.getHeight() / 2);

        // draw the string in the middle of the rectangle
        graphic.setFont(graphic.getFont().deriveFont(15F));

        graphic.setColor(colorTheme.getTextColour());
        graphic.drawString(value, x, y);
      }
    }
  }

  /**
   * Draws cells and text in them
   *
   * @param graphic the graphics to use when drawing
   * @param cellCollection the gridcells of strings to draw
   * @param cellPosPixConv the converter which translates cellpostion to pixel on the screen
   * @param colorTheme which theme to use
   */
  private static void drawTextCells(
      Graphics2D graphic,
      Iterable<GridCell<String>> cellCollection,
      CellPositionToPixelConverter cellPosPixConv,
      ColorTheme colorTheme) {

    int x = -99;
    int y = -99;

    for (GridCell<String> cellObj : cellCollection) {

      Rectangle2D cellRectangle = cellPosPixConv.getBoundsForCell(cellObj.pos());
      String value = cellObj.value();

      // calculate the x and y position at the center of the rectangle
      x = (int) (cellRectangle.getX()) + 20;
      y = (int) (cellRectangle.getY() + cellRectangle.getHeight() / 2) + 10;

      // box around text
      graphic.setColor(Color.GRAY);
      graphic.draw(cellRectangle);
      graphic.fill(cellRectangle);

      // draw the string in the middle of the rectangle
      graphic.setFont(graphic.getFont().deriveFont(30F));
      graphic.setColor(colorTheme.getTextColour());
      graphic.drawString(value, x, y);
    }
  }

  /**
   * Draws cells and images in them
   *
   * @param graphic the graphics to use when drawing
   * @param cellCollection the gridcells of GameEntities to draw
   * @param cellPosPixConv the converter which translates cellpostion to pixel on the screen
   * @param colorTheme which theme to use
   */
  private void drawImagesOnCells(
      Graphics2D graphic,
      Iterable<? extends IGameEntity> cellCollection,
      CellPositionToPixelConverter cellPosPixConv,
      Boolean doText) {

    for (IGameEntity cellObj : cellCollection) {
      Rectangle2D cellRectangle = cellPosPixConv.getBoundsForCell(cellObj.getCellPosition());

      String imageName = GameVisualInformation.getImageNameFromEntity(cellObj);
      Image scaledImage = GameVisualInformation.getImages().get(imageName);

      int x = (int) cellRectangle.getX();
      int y = (int) cellRectangle.getY();

      if (cellObj.getGameEntityType() == GameEntityType.BULLET) {
        x = x + (int) ((cellRectangle.getWidth() - scaledImage.getWidth(null)) / 2);
        y = y + (int) ((cellRectangle.getHeight() - scaledImage.getHeight(null)) / 2);
      }

      graphic.drawImage(scaledImage, x, y, null);

      if (doText) {
        graphic.setColor(Color.BLACK);
        String entityTypeString = String.valueOf(cellObj.getEntitySpecificType());
        graphic.drawString(entityTypeString, x, y + (int) cellRectangle.getHeight());
      }
    }
  }

  /**
   * Main method responsible for drawing the left side of the board
   *
   * @param graphic object to use when drawing Created with assistance from ChatGPT OpenAI (2023).
   *     ChatGPT (20. april-versjon) [Stor språkmodell]. Hentet fra https://chat.openai.com.
   */
  private void drawGame(Graphics2D graphic) {

    Rectangle2D gridFill = GameVisualInformation.getGameArea();
    graphic.setColor(this.colorTheme.getFrameColor());
    graphic.fill(gridFill);

    CellPositionToPixelConverter cellPosPixConv =
        new CellPositionToPixelConverter(
            gridFill,
            viewableGameModel.getDimensionGameBoard(),
            GameVisualInformation.getOutermargin());

    drawBackgroundImage(graphic, "meta/compascci.jpg");

    // doText can be turned on to true for debugging purposes
    drawCells(graphic, viewableGameModel.getPathTiles(), cellPosPixConv, this.colorTheme, false);

    drawImagesOnCells(graphic, viewableGameModel.getTowers(), cellPosPixConv, false);
    drawImagesOnCells(graphic, viewableGameModel.getBullets(), cellPosPixConv, false);
    drawImagesOnCells(graphic, viewableGameModel.getEnemies(), cellPosPixConv, false);
  }

  /**
   * Main method responsible for drawing the right side of the board
   *
   * @param graphic object to use when drawing
   */
  private void drawPreview(Graphics2D graphic) {

    graphic.setColor(this.colorTheme.getFrameColor());
    Rectangle2D previewArea = GameVisualInformation.getPreviewArea();
    graphic.fill(previewArea);

    CellPositionToPixelConverter previewConverter =
        new CellPositionToPixelConverter(
            previewArea,
            viewableGameModel.getBuyableItemsGrid(),
            GameVisualInformation.getOutermargin());

    drawImagesOnCells(graphic, viewableGameModel.getBuyableItemsTiles(), previewConverter, true);
  }

  /**
   * Responsible for drawing the text on the right side of the board
   *
   * @param graphic object to use when drawing
   */
  private void drawGameText(Graphics2D graphic) {
    Rectangle2D textArea = GameVisualInformation.getPreviewTextArea();

    int x = (int) textArea.getX();
    int y = (int) textArea.getY() + GameVisualInformation.getTextOffset();

    String currency = "Money : " + Integer.toString(viewableGameModel.getCurrentCurrency());
    String health = "Health : " + Integer.toString(viewableGameModel.getPlayerHealth());

    graphic.setFont(graphic.getFont().deriveFont(20F));
    graphic.setColor(this.colorTheme.getMainMenuTextColour());
    graphic.drawString(currency, x, y);
    graphic.drawString(health, x, y + 30);
  }

  /**
   * Responsible for drawing the image ontop of the mouse
   *
   * @param graphic object to use when drawing
   */
  private void drawMouseitem(Graphics2D graphic) {

    IGameEntity clickedEntity = this.viewableGameModel.getClickedEntity();
    Point currentMousePoint = this.viewableGameModel.getCurrentMousePoint();

    if (clickedEntity == null || currentMousePoint == null) {
      return;
    }

    String imageName = GameVisualInformation.getImageNameFromEntity(clickedEntity);
    Image scaledImage = GameVisualInformation.getImages().get(imageName);

    int itemSize = GameVisualInformation.getMainGameBoardCellSize();

    int x = currentMousePoint.x - itemSize / 2;
    int y = currentMousePoint.y - itemSize / 2;

    graphic.drawImage(scaledImage, x, y, itemSize, itemSize, null);
    graphic.setColor(colorTheme.getTowerColor(clickedEntity.getEntitySpecificType()));
  }
}
