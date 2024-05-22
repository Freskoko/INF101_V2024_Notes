package no.uib.inf101.towerdefense.view.viewUtils;

import java.awt.geom.Rectangle2D;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridDimension;

/**
 * @author Henrik Brøgger
 */
public class CellPositionToPixelConverter {

  private Rectangle2D box;
  private GridDimension gd;
  private double margin;

  public CellPositionToPixelConverter(Rectangle2D box, GridDimension gd, double margin) {
    this.box = box;
    this.gd = gd;
    this.margin = margin;
  }

  /**
   * @param cp A cellposition object
   * @return A rectangle with calculated bounds
   */
  public Rectangle2D getBoundsForCell(CellPosition cp) {

    // how much place does margin take up on col
    double marginWidthSpace = (this.gd.cols() + 1) * this.margin;

    // length of ALL cells on width
    double allCellsCumulativeWidth = box.getWidth() - marginWidthSpace;
    double cellWidth = (allCellsCumulativeWidth / gd.cols());

    // how much place does the margin take up on row
    double marginHeightSpace = (this.gd.rows() + 1) * this.margin;

    // length of ALL cells on rows
    double allCellsCumulativeHeight = box.getHeight() - marginHeightSpace;
    double cellHeight = (allCellsCumulativeHeight / gd.rows());

    double cellX = box.getX() + (margin * (cp.col() + 1)) + (cellWidth * cp.col());
    double cellY = box.getY() + (margin * (cp.row() + 1)) + (cellHeight * cp.row());

    Rectangle2D returnRect =
        new Rectangle2D.Double(
            cellX,
            cellY,
            cellWidth * GameVisualInformation.getGameRatio(),
            cellHeight * GameVisualInformation.getGameRatio());
    return returnRect;
  }
}
