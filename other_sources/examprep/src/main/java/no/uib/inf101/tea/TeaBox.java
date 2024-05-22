package no.uib.inf101.tea;

import java.util.ArrayList;
import java.util.List;

public class TeaBox<T extends Tea> {
    
    private List<T> teas;

    public TeaBox() {
        teas = new ArrayList<>();
    }

    public void addTea(Tea tea) {
        teas.add((T) tea);
    }
}
