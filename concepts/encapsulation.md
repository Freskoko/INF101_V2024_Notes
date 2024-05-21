# Encapsulation

*Unngå bugs.. Hver del av koden skal ha ansvar for en ting.*

![alt text](/imgs/modifiers.png)

When you have a felt-variabel it is important to keep it private.
You can allow others to access it through a getter.

Example:

```java

public class Person{

    private String name;
    private final ArrayList<Person> friends;

    public Person(String name, ArrayList<Person> friends){
        this.name = name;
        this.friends = friends;
    }

    public String getName(){
        return this.name // encapsulation (immutable)
    }
    public String getFriendsMutable(){ 
        return this.friends; // encapsulation (mutable)
        // since arraylist is mutable, other classes can mess with it (even if final)
    }
    public String getFriendsImmutable(){ 
        // further than encapsulation, keeping stuff private
        // prevents people from messing with the variable
        // instead they can mess with the copy of the variable you gave >:)
        return new ArrayList<>(this.friends); 
    }
}
```
### Data invariant
Noen objekter har krav som må holde til en
hver tid

Feks at ett trafikklys kan være i 1 av 4 tilstander.

fra tidligere eksamen: **To dører, hvor en ALLTID må være lukket**
```java
private void openDoor(Door door) {
    if(!inner.isOpen() && !outer.isOpen())
        door.open();
    else
        System.out.println("Can not open both at the same time");
        
        throw new IllegalStateException("Both doors cannot be open")
}
```
Må ALLTID overholdes!

Question from exam:

```java
public class Main {
    public static void main (String[] args) {
        Person x = new Person("Tor",1999)
        Person x = new Person("To"+"r",1999)
    }
}
```

Hvorfor gir dette false?

Når man sammenligner med == sammenlignes den verdien som er på stacken. Men for alle objeketer, selv om de "ser" helt like ut (samme navn/føldelsår) vil de ligge på forskjellige minneadresser.
Da må man bruke .equals metoden (eller Object.equals()).

Men vanlig equals (fra Object) bruker bare ==, vi trenger å overskrive (@Override) denne metoden. Den må sjekke likhet mellom feltvariabelene og så returnere true hvis de er lik. Når man sammenligner navn må man bruke equals() på den og, siden dette er en String og ikke en primitiv.