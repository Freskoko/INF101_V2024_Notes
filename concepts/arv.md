# Arv (extends)

*Can be a type of Polymorphism!*

En klasse kan kun arve fra en klasse ved extends. (Pgå `super()`-logikk) '

Men en klasse kan ha flere super-klasser

En interface can extende flere interface.

Bruk extends så kan du hente alle feltvariabeler og metoder fra øvre klasse

Husk @override hvis man skal override metoder fra mother class

Kall på super() for å inite fra øvre klassen!

Hvis

  List<String> myList = new ArrayList<>();
  og la oss si foo() kun finnes i ArrayList
  og jeg prøver å skrive myList.foo(), så kommer en feil!
  Siden List har ikke foo()

```java
Animal mia = new Cat();

// kan ikke skrive
mia.purr() 
// fordi purr kommer fra cat, så animal vet ingentng om det
// vi kan skrive
mia.makeSound()

// MEN !!!

Cat mia = new Cat();

// da kan vi skrive
mia.purr() 
// og
mia.makeSound()

men vi kan også @Override
```java

class Animal {
    void makeSound() {
        System.out.println("Animal sound");
    }
}

class Cat extends Animal {
    void purr() {
        System.out.println("Cat purrs");
    }

    @Override
    void makeSound() {
        super.makeSound()
        System.out.println("Cat meows");
    }
}

Cat mia = new Cat();
mia.makeSound() -> "Animal Sound Cat Meows"
```


```

```java
Thanks thank = new ManyThanks();
System.out.println(thanks.foo())

// også sjekker den første ManyThanks etter foo()
// men hvis ikke det, sjekk general Thanks metoden etter foo()
// hvis ManyThanks sin foo() gjør ett super kall til super foo(),
// så får vi begge ut ex: 
return "kake " + super.foo(); -> "kake is"
```

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
