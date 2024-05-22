package no.uib.inf101.generics;

import java.util.ArrayList;
import java.util.List;

public class Grid<T extends ShoppingItem> {
    
    private List<T> cells;
    private int columns;
    private int rows;

    public Grid(int rows, int columns, T initElement) {
        if (rows <= 0 || columns <= 0)
            throw new IllegalArgumentException();
        this.columns = columns;
        this.rows = rows;

        cells = new ArrayList<>(columns * rows);
        for (int i = 0; i < columns * rows; ++i) {
            cells.add(initElement);
        }
    }
}
