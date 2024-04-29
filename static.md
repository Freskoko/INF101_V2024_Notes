# Static

```java
/* 
 * Merk: dette eksempelet ser fornuftig ut ved første øyekast, men er IKKE til
 * etterfølgelse. Les «Hvorfor man alltid unngår klassevariabler» -seksjonen
 * under eksempelet for å forstå hvorfor.
 */
 
class Person {
  // Statisk: klassevariabeler, klassemetoder
  static int numberOfPersons = 0;

  static void incrementNumberOfPersons() {
    Person.numberOfPersons++;
  }

  // Ikke-statisk: instansvariabler, konstruktører, instansmetoder
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
  assertEquals(2, Person.numberOfPersons); // feiler
}
```

Siden de forskjellige persons alle peker på samme, vil testen fele siden number of persons er 3!

`Statiske variabler som endrer seg er som globale variabler: ondskapsfulle. De bør unngås for enhver pris`

Bruk heller en managr class ! feks facotory