# Encapsulation

When you have a felt-variabel it is important to keep it private.
You can allow others to acess it through a getter.
However, if the 

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
        return this.name // encapsulation (immutable)
    }

    public String getFriends(){ 
        return this.friends; // encapsulation (mutable)
        // since arraylist is mutable, other classes can mess with it
    }

    public String getFriends(){ 
        // further than encapsulation, keeping stuff private
        // prevents people from messing with the variable
        return new ArrayList<>(this.friends); 
    }
}

```