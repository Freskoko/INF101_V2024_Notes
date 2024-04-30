# Encapsulation

When you have a felt-variabel it is important to keep it private.

Example:

```java

public class Person{

    private String name;
    private ArrayList<Person> friends;

    public Person(String name, ArrayList<Person> friends){
        this.name = name;
        this.friends = friends;
    }

    public String getName(){
        return this.name // encapsulation
    }

    public String getFriends(){ // further than encapsulation, keeping stuff private
        // prevents people from messing with the variable
        return new ArrayList<>(this.friends);
        // todo maybe implement clone?
    }
}

```