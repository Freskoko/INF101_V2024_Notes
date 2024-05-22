package no.uib.inf101.grid;

// Les om records her: https://inf101.ii.uib.no/notat/mutabilitet/#record

/**
 * A GridCell contains a CellPosition and a obj.
 *
 * @param cellPosition the position of the cell
 * @param obj          the object at the pos
 */
public record GridCell<E>(CellPosition pos, E value) {
}
