package no.uib.inf101.towerdefense.view.viewUtils;

import java.awt.geom.Rectangle2D;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridDimension;

/**
 * @author Henrik Brøgger
 */
public class PixelToCellPositionConverter {
  private Rectangle2D box;
  private GridDimension gd;

  public PixelToCellPositionConverter(Rectangle2D box, GridDimension gd) {
    this.box = box;
    this.gd = gd;
  }

  /**
   * Converts a pixel position to a cell position in the grid.
   *
   * @param x The x-coordinate of the pixel.
   * @param y The y-coordinate of the pixel.
   * @return The cell position in the grid.
   */
  public CellPosition getCellPositionForPixel(int x, int y) {
    double cellWidth = ((box.getWidth()) / gd.cols());
    double cellHeight = ((box.getHeight()) / gd.rows());

    int row = (int) Math.floor(y / cellHeight);
    int col = (int) Math.floor(x / cellWidth);

    CellPosition cellPosition = new CellPosition(row, col);

    return cellPosition;
  }
}
