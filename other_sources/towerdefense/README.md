# Tower Defense

Code will not always run in java from command line. Try in vscode clicking run button. 
If that fails, run the project from the command line with the command:

`mvn exec:java -Dexec.mainClass="no.uib.inf101.towerdefense.TowerDefenseMain"`

## Welcome to Tower Defense : Machine vs Language!

This game is all about programming languages infecting operating systems! The player (you) must build OS-defenses to shoot down the programming languages invading your computer.

Click and drag towers to place them on your board. 
They cost money (bottom right)

The enemies spawn on a path, and if they make it to the end they will deal damage.
Your health can be seen in the bottom right corner.

### Video

[link to vid](https://youtu.be/2s55pDovM5o)

### Maps and waves

The waves AND maps are all based off json files. Adding a new map or wave is relativley easy.

For example, a wave in `wave_settings.json` can be defined as :

```json
    "1" : {

        "waves":[
            ["b","-","b","-","b","-","h"],
        ]

    }
```

This means that first a basic enemy will spawn, followed by no enemy, then some more basic ones before finally a "hard" enemy will spawn finally.

On the other hand, maps can be defined in the `level_settings.json` as:

```json

    "-1": { 
        "startPos": [0, 0],
        "endPos": [0, 19],
        "boardLayout": [
            {"position1": [0, 1], "position2": [0, 18]}
        ]
    }
```

This means that the path for the enemies will start at 0,0, and end at 0,19

The following boardlayout includes two positions, defining the start and end spot for a line, on which the path will be drawn.

*So...*

If you wish to add any map/wave, just add it to the corresponding json and select it in the main menu! 

**NOTE**: There is no check to see if the map/wave made is valid, so an invalid map or wave may cause the game to bug out. It is also important to add a wave/map in the next index, so if maps [0,1,2,3] exist, if you try to add map 5, you will notice that you cannot pass 3 in the gamemenu, since this is checked for.

### Notes

The game is not really "balanced". Towers/enemies/bullets may be too strong / too weak. 
However, all classes are designed in such a way where it would be possible to create a "balanced" game.  

### Choices

During the course of the game's development, a few things came to mind:

**gameEntitySpecficType**

Each GameEntity has a "gameEntitySpecficType" which describes what undergroup of their class they are, so an Enemy may have a gameEntitySpecficType of 'b' for basic.

The descion to change this to an enum was considered, but is yet another abstraction layer which wont add much except for some readability. The classes that draw the board all expect the type "char" aswell.  It would require some refactoring.

**doTickAction**

Each class that extends from GameEntity has a "doTickAction" method. I thought that maybe this could be abstracted up a layer to GameEntity. This proved to not be a good idea. While Tower, Enemy and Bullet all have a doTickAction. They all do different things, for instance:

Enemy doTickAction
`public int doTickAction(GameBoard gameBoard)`

Bullet doTickAction
`public void doTickAction(ArrayList<Enemy> enemies)`

Tower doTickAction
`public Bullet doTickAction(ArrayList<Enemy> enemies)`

They all receive different inputs (except bullet and tower share inputs), and all have different returns. Here it would not make sense to abstract the doTickAction up a layer, since these fundementaly have different actions.

Abstraction could look like this:

`public E doTickAction(V information);` 

But now the information about what each class does is lost!

## Vurdering
    Her holder jeg litt styr på om jeg har følgt alt som må til for å få poeng.

### Funksjonalitet:

Dette funker, og jeg tenker dette er komplekst nok.

### OOP-konsepter

Jeg bruker...

**Inheritence/Polymorphism**

Bullet, tower og enemy, arver alle fra GameEntity klassen

GameBoard arver også fra Grid

**Interface**

Klasser bruker ett eller flere interface der det er fornuftig. 

Interfacene er godt dokumentert.

**Iterator/Iterable**

Bruker iterator for å iterere over lister med klassen (feks tower) for å tegne disse på brettet.

**Encapsulsation**

Når klasser returnerer felt-variabler, bruker
de encapsulation der det er fornuftig.

**Mutability**

Klasse-variabler er nesten alltid privat

**Testing**

Det finnes flere tester som alle tester viktig egenskaper ved spillet.

Jeg har ikke 100% test coverage, dersom noen av metodene ved å feks lage maps kan være vanskelig å teste. (Ikke en unnskylding, det er nok mulig å lage)

**Generics**

Bruker generics i grid

### Kodestil

Jeg har brukt [google-java-format repo](https://github.com/google/google-java-format) for å formattere koden, og sett over manuelt

### Javadoc

Det er javadoc på alle metoder (vet at det ikke alltid er nøvdendig på alle private metoder, men det skader ikke)
