# Arv (extends)

*Can be a type of Polymorphism!*

Bruk extends så kan du hente alle feltvariabeler og metoder fra øvre klasse

Husk @override hvis man skal override metoder fra mother class

Kall på super() for å inite fra øvre klassen!

### Eksempel:

```java 
public class GameEntity { // mother class

  private int actionInterval = 300; // default
  private CellPosition cellPosition;

  public GameEntity(
      CellPosition cellPosition,
      int actionInterval) {

    this.cellPostion = cellPosition;
    this.actionInterval = actionInterval;
  }
}

```
```java
public class Bullet extends GameEntity {

  private final int speed;

  private Bullet(
      int actionInterval,
      CellPosition cellPosition,
      int speed) {
    super(cellPosition, actionInterval); // super kall til GameEntity constructor
    this.speed = speed;
  }
}
```

### Klasser som er final

Når man definerer en klasse som final vil det ikke være mulig å arve fra denne klassen. Dette er en god praksis.

```java
public final class CantExtendFromMe {
  // ...
}
```
alle klasser arver allerede fra Object klassen, og arver da disse metodene:

toString(), equals(Object), hashCode(), getClass(), noen flere 
