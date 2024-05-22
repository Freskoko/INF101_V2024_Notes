package no.uib.inf101.towerdefense.view.viewUtils;

import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import no.uib.inf101.towerdefense.model.gameobjects.gameentity.GameEntity.GameEntityType;
import no.uib.inf101.towerdefense.model.gameobjects.gameentity.IGameEntity;

/**
 * The GameVisualInformation class aims to contain multiple visual config elements including the
 * board and images.
 *
 * @author Henrik Brøgger
 */
public class GameVisualInformation {
  private static final double OUTERMARGIN = 2;
  private static final double BOARD_MARGIN = 0;
  private static final int TEXT_OFFSET = 80;
  private static final double PREVIEW_SPACE_RATIO = 0.2;
  private static final double TOTAL_MARGIN = 25 + OUTERMARGIN;
  private static final int WINDOW_HEIGHT = 800;
  private static final int WINDOW_LENGTH = 1000;
  private static final double GAME_INFO_SPACE_RATIO = 0.2;
  private static final int GAME_RATIO = 1; // keep at 1, not implemented further than this
  private static final int BOARD_ROWS = 20;
  private static final int BOARD_COLS = 20;
  private static final int SPACES_BETWEEN_BLOCKS = BOARD_ROWS + 1;

  private static final HashMap<String, BufferedImage> images = initAndResizeImages();

  private static HashMap<String, BufferedImage> initAndResizeImages() {
    HashMap<String, BufferedImage> images = initImages();
    HashMap<String, BufferedImage> resizedImages = new HashMap<>();

    for (Map.Entry<String, BufferedImage> entry : images.entrySet()) {
      BufferedImage scaledImage = resizeImage(entry.getValue(), entry.getKey());
      resizedImages.put(entry.getKey(), scaledImage);
    }

    return resizedImages;
  }

  public static BufferedImage resizeImage(BufferedImage originalImage, String name) {

    int targetHeight = GameVisualInformation.getMainGameBoardCellSize();
    int targetWidth = GameVisualInformation.getMainGameBoardCellSize();

    if (name.contains("meta")) {
      targetHeight = GameVisualInformation.getWindowHeight();
      targetWidth = GameVisualInformation.getWindowLength();
    }
    // idea, not fully implemented
    if (name.contains("bullet")) {
      targetHeight = 20;
      targetWidth = 20;
    }

    if (name.contains("small")) {
      targetHeight = 30;
      targetWidth = 30;
    }

    if (name.contains("large")) {
      targetHeight = 60;
      targetWidth = 60;
    }

    Image resultingImage =
        originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
    BufferedImage outputImage =
        new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
    outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);

    return outputImage;
  }

  public static String getImageNameFromEntity(IGameEntity entity) {
    if (entity.getGameEntityType() == GameEntityType.ENEMY) {
      switch (entity.getEntitySpecificType()) {
        case 'p': // b
          return "enemies/pythonlogo.jpg";
        case 'j': // i
          return "enemies/small/javalogo.jpg";
        case 'c': // h
          return "enemies/large/cpplogo.jpg";
      }
    }

    if (entity.getGameEntityType() == GameEntityType.TOWER) {
      switch (entity.getEntitySpecificType()) {
        case 'w':
          return "towers/windowslogo.jpg";
        case 'c':
          return "towers/maclogo.jpg";
        case 'l':
          return "towers/linuxlogo.jpg";
        case 'n':
          return "towers/nixlogo.jpg";
        case 'm':
          return "towers/mint.jpg";
        case 'u':
          return "towers/ubuntu.jpg";
      }
    }

    if (entity.getGameEntityType() == GameEntityType.BULLET) {
      switch (entity.getEntitySpecificType()) {
        case 'w':
          return "bullets/yellow.jpg";
        case 'c':
          return "bullets/green.jpg";
        case 'l':
          return "bullets/red.jpg";
        case 'n':
          return "bullets/blue.jpg";
        case 'm':
          return "bullets/mint.jpg";
        case 'u':
          return "bullets/orange.jpg";
      }
    }

    return "missing_texture.jpg";
  }

  public static HashMap<String, BufferedImage> getImages() {
    return images;
  }

  private static HashMap<String, BufferedImage> initImages() {
    return initImages(new File("src/main/resources/"), "");
  }

  private static HashMap<String, BufferedImage> initImages(File dir, String pathProj) {
    HashMap<String, BufferedImage> images = new HashMap<>();

    File[] imgFiles = dir.listFiles();

    if (imgFiles == null) return images;

    for (File imgFile : imgFiles) {

      // if it's dir then do the recursion
      if (imgFile.isDirectory()) {
        images.putAll(initImages(imgFile, pathProj + imgFile.getName() + "/"));
      } else {

        String imgPath = imgFile.getName();

        if (imgPath.toLowerCase().endsWith(".jpg")) {
          try {
            BufferedImage image = ImageIO.read(imgFile);

            images.put(pathProj + imgPath, image);

          } catch (IOException ex) {
            throw new RuntimeException("Failed to load image at " + imgPath, ex);
          }
        }
      }
    }

    return images;
  }

  public static int getGameRatio() {
    return GAME_RATIO;
  }

  public static double getGameInfoSpaceRatio() {
    return GAME_INFO_SPACE_RATIO;
  }

  public static double getOutermargin() {
    return OUTERMARGIN;
  }

  public static double getBoardMargin() {
    return BOARD_MARGIN;
  }

  public static int getTextOffset() {
    return TEXT_OFFSET;
  }

  public static double getPreviewSpaceRatio() {
    return PREVIEW_SPACE_RATIO;
  }

  public static double getTotalMargin() {
    return TOTAL_MARGIN;
  }

  public static int getWindowHeight() {
    return WINDOW_HEIGHT;
  }

  public static int getWindowLength() {
    return WINDOW_LENGTH;
  }

  public static int getBoardRows() {
    return BOARD_ROWS;
  }

  public static int getBoardCols() {
    return BOARD_COLS;
  }

  public static int getMainGameBoardCellSize() {
    return (int)
        ((GameVisualInformation.getWindowLength() / GameVisualInformation.getBoardCols())
            - (2 * getOutermargin()));
  }

  public static int getPreviewBoardCellSize() {
    return (int)
        ((GameVisualInformation.getPreviewArea().getWidth() / GameVisualInformation.getBoardCols())
            - (2 * getOutermargin()));
  }

  public static Rectangle2D getGameArea() {

    double width =
        (GameVisualInformation.getWindowLength()
                * (1 - GameVisualInformation.getPreviewSpaceRatio())
            + GameVisualInformation.SPACES_BETWEEN_BLOCKS * GameVisualInformation.getOutermargin());
    double height = GameVisualInformation.getWindowHeight();

    return new Rectangle2D.Double(
        GameVisualInformation.getBoardMargin(),
        GameVisualInformation.getBoardMargin(),
        width,
        height);
  }

  public static Rectangle2D getMainMenuTextArea() {
    double textAreaWidth = 500;
    double textAreaHeight = 500;
    double textAreaX = 0;
    double textAreaY = 0;

    return new Rectangle2D.Double(textAreaX, textAreaY, textAreaWidth, textAreaHeight);
  }

  public static Rectangle2D getPreviewArea() {

    double previewWidth =
        (GameVisualInformation.getWindowLength() - GameVisualInformation.getGameArea().getWidth());
    double previewHeight =
        GameVisualInformation.getWindowHeight()
            * (1 - GameVisualInformation.getGameInfoSpaceRatio());
    double previewX = GameVisualInformation.getGameArea().getWidth();
    double previewY = GameVisualInformation.getBoardMargin();

    return new Rectangle2D.Double(previewX, previewY, previewWidth, previewHeight);
  }

  public static Rectangle2D getPreviewTextArea() {

    double textAreaWidth =
        (GameVisualInformation.getWindowLength() - GameVisualInformation.getGameArea().getWidth());
    double textAreaHeight =
        GameVisualInformation.getWindowHeight() * GameVisualInformation.getGameInfoSpaceRatio();
    double textAreaX = GameVisualInformation.getGameArea().getWidth();
    double textAreaY = GameVisualInformation.getBoardMargin() + getPreviewArea().getHeight();

    return new Rectangle2D.Double(textAreaX, textAreaY, textAreaWidth, textAreaHeight);
  }
}
