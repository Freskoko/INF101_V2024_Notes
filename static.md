# Static

Static makes the function be able to be ran from anywhere with `Object.method()`
This is good for constants perhaps, but all instances of a class using the method will pont to the same info.

NOTE !!! Static har ikke tilgang til `this`!

```java
 
class Person {
  static int numberOfPersons = 0;

  static void incrementNumberOfPersons() {
    Person.numberOfPersons++;
  }
  String name;

  Person(String name) {
    this.setName(name);
    Person.incrementNumberOfPersons();
  }

  void setName(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Name cannot be null");
    }
    this.name = name;
  }
}

public class Main {
  public static void main(String[] args) {
    Person p1 = new Person("Adam");
    Person p2 = new Person("Eva");
    System.out.println(Person.numberOfPersons); // 2
  }
}
```

virker ok, men failer!

```java
@Test
void testName() {
  Person p1 = new Person("Adam");
  assertEquals("Adam", p1.name);
}

@Test
void testNumberOfPersons() {
  Person p1 = new Person("Adam");
  Person p2 = new Person("Eva");
  assertEquals(2, Person.numberOfPersons); // feiler fordi blir 3
}
```
Siden de forskjellige persons alle peker på samme, vil testen fele siden number of persons er 3!

`Statiske variabler som endrer seg er som globale variabler: ondskapsfulle. De bør unngås for enhver pris`

Bruk heller en manager class ! eller feks facotory

**Dårlig kode**

```java
	static Random random = new Random(); 
    // dette er ikke bra!! alle BadCode klasser vil peke til samme Random!
```