package no.uib.inf101.tetris.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.tetris.model.TetrisModel.GameState;

public class TetrisView extends JPanel {

  // view
  private ViewableTetrisModel viewableTetrisModel;
  private ColorTheme colorTheme;

  // constants
  private static final double OUTERMARGIN = 2;
  private static final double BOARD_MARGIN = 50;
  private static final double PREVIEW_SPACE_RATIO = 0.40;
  private static final double MAX_PREVIEW_SIZE = 100.0;
  private static final int TEXT_OFFSET = 40;

  private double TOTAL_MARGIN = 0;

  // image
  private Image backgroundImage;
  private double rotation = 0;
  private double IMAGE_SCALE = 0.8;

  /**
   * Constructs a TetrisView object with the given ViewableTetrisModel.
   * 
   * @author Henrik Brøgger, with assistance from ChatGPT
   * @param viewableTetrisModel the ViewableTetrisModel to be
   *                            associated with this TetrisView
   */
  public TetrisView(ViewableTetrisModel viewableTetrisModel) {
    this.colorTheme = new DefaultColorTheme();
    this.viewableTetrisModel = viewableTetrisModel;
    this.backgroundImage = grabRandomBackgroundImage();

    this.TOTAL_MARGIN = OUTERMARGIN + BOARD_MARGIN;

    this.setFocusable(true);
    this.setPreferredSize(new Dimension(600, 600));
  }

  /**
   * @return Random image from the resources folder, Image must end with .png
   *         to be a possible image that can be returned
   */
  private Image grabRandomBackgroundImage() {
    // Created with assistance from ChatGPT
    // OpenAI (2023). ChatGPT (20. april-versjon) [Stor språkmodell].
    // Hentet fra https://chat.openai.com.

    File dir = new File("src/main/resources/");
    String[] images = dir.list((dir1, name) -> name.toLowerCase().endsWith(".png"));
    if (images == null || images.length == 0) {
      return null;
    }
    int randomIndex = new Random().nextInt(images.length);
    try {
      return ImageIO.read(new File(dir, images[randomIndex]));
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Error reading image");
    }
    return null;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;

    drawBackgroundImage(g2);
    drawGame(g2);
    drawPreview(g2);
  }

  /**
   * Main method responsible for drawing the background image
   *
   * @param graphic object to use when drawing
   */
  private void drawBackgroundImage(Graphics2D graphic) {

    Inf101Graphics.drawCenteredImage(
        graphic,
        backgroundImage,
        this.getWidth() / 2,
        this.getHeight() / 2,
        this.IMAGE_SCALE,
        this.rotation);
  }

  /**
   * @param rotation a new double to set the rotation speed to
   */
  public void setBackgroundRotation(double rotation) {
    this.rotation = rotation;
  }

  /**
   * @return The current background image rotation speed
   */
  public double getBackgroundRotation() {
    return this.rotation;
  }

  /**
   * Main method responsible for drawing the left side of the board
   *
   * @param graphic object to use when drawing
   */
  private void drawGame(Graphics2D graphic) {
    // Created with assistance from ChatGPT
    // OpenAI (2023). ChatGPT (20. april-versjon) [Stor språkmodell].
    // Hentet fra https://chat.openai.com.

    // set board size in regards to constants
    double width = (this.getWidth() - 2 * (TOTAL_MARGIN))
        - this.getWidth() * PREVIEW_SPACE_RATIO;
    double height = (this.getHeight() - 2 * (TOTAL_MARGIN));

    // board background
    graphic.setColor(this.colorTheme.getFrameColor());
    Rectangle2D gridFill = new Rectangle2D.Double(TOTAL_MARGIN,
        TOTAL_MARGIN, width, height);

    graphic.fill(gridFill);

    // normal tetris blocks
    CellPositionToPixelConverter cellPosPixConv = new CellPositionToPixelConverter(gridFill,
        viewableTetrisModel.getDimension(), OUTERMARGIN);

    drawCells(graphic, viewableTetrisModel.getTilesOnBoard(), cellPosPixConv, this.colorTheme);

    if (viewableTetrisModel.getGameState() == GameState.GAME_OVER) {

      // set color to semi-transparent black for overlay
      graphic.setColor(this.colorTheme.getOverlayColour());
      Rectangle2D gridFillGray = new Rectangle2D.Double(TOTAL_MARGIN,
          TOTAL_MARGIN, width, height);

      graphic.fill(gridFillGray);

      // game over text
      int middleX = (int) (OUTERMARGIN + width / 2) - TEXT_OFFSET;
      int middleY = (int) (OUTERMARGIN + height / 2) - TEXT_OFFSET;

      graphic.setColor(this.colorTheme.getTextColour());
      String gameOverText = "GAME OVER! \n YOUR SCORE: " + viewableTetrisModel.getGameScore();
      graphic.drawString(gameOverText, middleX, middleY);
    }
    if (viewableTetrisModel.getGameState() == GameState.ACTIVE_GAME) {
      drawCells(graphic, viewableTetrisModel.getTetrominoTiles(), cellPosPixConv, colorTheme);
    }
  }

  /**
   * Main method responsible for drawing the right side of the board (preview)
   * Includes: next tetris
   * block, and score.
   *
   * @param graphic object to use when drawing
   */
  private void drawPreview(Graphics2D graphic) {

    // define right side area for preview

    double previewSize = Math.min(this.getWidth() * PREVIEW_SPACE_RATIO, MAX_PREVIEW_SIZE);

    double previewX = this.getWidth() - previewSize - (TOTAL_MARGIN);
    double previewY = TOTAL_MARGIN;

    Rectangle2D previewArea = new Rectangle2D.Double(
        previewX - BOARD_MARGIN, previewY + BOARD_MARGIN, previewSize, previewSize);
    graphic.setColor(this.colorTheme.getFrameColor());
    graphic.fill(previewArea);

    // where to show waiting tetromino
    CellPositionToPixelConverter previewConverter = new CellPositionToPixelConverter(
        previewArea, viewableTetrisModel.getSideViewTiles(), OUTERMARGIN);

    if (viewableTetrisModel.getGameState() == GameState.ACTIVE_GAME) {
      drawCells(graphic, viewableTetrisModel.getWaitingTiles(), previewConverter, colorTheme);

      // update font and draw score
      graphic.setColor(this.colorTheme.getTextColour());
      graphic.setFont(graphic.getFont().deriveFont(25F));
      String scoreExplainer = "Current score: " + viewableTetrisModel.getGameScore();
      graphic.drawString(scoreExplainer, (int) previewX - 80, (int) previewY + 20);
    }
  }

  /**
   * Method responsible for drawing cells
   *
   * @param graphic        Object to use when drawing
   * @param cellCollection Iterable contaning GridCells to be drawn
   * @param cellPosPixConv Method used to find pixels to draw cell
   * @param colorTheme     Method used when finding colors to draw with for each
   *                       cell
   */
  private static void drawCells(
      Graphics2D graphic,
      Iterable<GridCell<Character>> cellCollection,
      CellPositionToPixelConverter cellPosPixConv,
      ColorTheme colorTheme) {

    for (GridCell<Character> cellObj : cellCollection) {

      Rectangle2D cellRectangle = cellPosPixConv.getBoundsForCell(cellObj.pos());

      graphic.setColor(colorTheme.getCellColor(cellObj.value()));
      graphic.draw(cellRectangle);
      graphic.fill(cellRectangle);
    }
  }
}
