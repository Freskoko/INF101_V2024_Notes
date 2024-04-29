bruk extends så kan du hente alle feltvariabeler og metoder fra øvre klasse

husk @override

kall på super() for å inite fra øvre klassen

### Klasser som er final

Når man definerer en klasse er det mulig å definere den som final, for eksempel slik:

```java
public final class Rectangle {
  // ...
}
```

Da vil det ikke være mulig å arve fra denne klassen. Dette er en god praksis.

alle klasser arver allerede fra Object klassen.

alle klasser har da disse metodene:


- toString() (denne metoden kalles automatisk når objektet skrives ut. Ved å overskrive den kan man velge selv hvordan objekter i en klasse representeres som strenger)

- equals(Object) og hashCode() 

- getClass()

- og noen flere...
