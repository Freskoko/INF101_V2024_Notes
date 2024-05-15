# Abstract classes

*Can be a type of Polymorphism*

Setting a class to abstract classes allows them to only implement some parts of the interface it implements.

we just pick and choose. someone else (or us) will implement the rest later

This means we can use all the methods from the interface without having them implemented

Example:

(the interface ICoronaData has several methods: `cumulativeDeaths()`,`getDailyDeaths()`,`deathsPerMillion()`)

```java
public abstract class AbstractCoronaData implement ICoronaData{
    // implements stuff from gameentity but doesnt need to implement all
    // if it wants to only use some it can! 

    @Override
    public ArrayList<Integer> cumulativeDeaths() {
        ArrayList<Integer> cumulativeSum = new ArrayList<>(); 
        int sum = 0
        for (int deaths : this.getDailyDeaths()){ 
            // get daily deaths is from icorona interface. we dont need o
            // to implement getdailydeaths since class is abstract
            // someone else will do it later!

            sum+=deaths
            cumulativeSum.add(sum)
        }
        return cumulativeSum;
    }

    @Override ArrayList<Double> deathsPerMillion(){
        ArrayList<Double> cumulativeSum = new ArrayList<>(); 
        double millions = (double) this.getPopulation()/1000000
        // get population is from icorona interface. we dont need o
        // to implement get population since class is abstract
        // someone else will do it later!

        for (int deaths : getDailyDeaths()){ 
            rate.add( (double) deaths/millions);
        }
        return rate;
    }
}
```

We NEVER want to make a copy of a class which is ONLY abstract.
They can be a good building block though.

```java
public abstract class Game {
    // Tetris/TickTackToe can extend this, but never make a just "Game"

    protected String name; // feltvariabel must have

    public abstract boolean gameOver(); // all games must have
}
```

More facts:

```
An abstract class is a class that is declared with an abstract keyword.

An abstract method is a method that is declared without implementation.

An abstract class may or may not have all abstract methods. Some of them can be concrete methods

A method-defined abstract must always be redefined in the subclass, thus making overriding compulsory or making the subclass itself abstract.

Any class that contains one or more abstract methods must also be declared with an abstract keyword.

There can be no object of an abstract class. That is, an abstract class can not be directly instantiated with the new operator.

An abstract class can have parameterized constructors and the default constructor is always present in an abstract class.
```
