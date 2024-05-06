# Enum

*Lagre informasjon på en smart måte*

```java

public enum Grade {
    
    A(5),
    B(4),
    C(3),
    D(2),
    E(1),
    FAIL(-1);

    public final int numberRepresentation;

    private Grade(int numberRepresentation) {
        this.numberRepresentation = numberRepresentation;
    }
}
```

Hvis vi har denne enum-en og skal hente den ut, så kan vi gjøre sånn:

```java
double gradesum = 0.0;
for (Grade grade: this.grades){
    int gradeval = grade.numberRepresentation; 
    gradesum += gradeval
}
```

Eller så kan vi sjekke om hvilken verdi den har

```java
for (Grade grade: this.grades){
    if (grade.equals(Grade.FAIL)){
        ...
    }
}
```