package no.uib.inf101.position;

public class Position {
    
    int x;

    public Position(int x) {
        this.x = x;
    }

    public Position move(int deltaX) {
        return new Position(x + deltaX);
    }

    @Override
    public String toString() {
        return x+"";
    }
}
