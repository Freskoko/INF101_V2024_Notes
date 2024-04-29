# Generics

Box som kan ha hva som helst i seg 

```java
// Implementasjonskode
public class MyBox<T> {
  private T element = null;

  public void set(T element) {
    this.element = element;
  }

  public T get() {
    return element;
  }
}
```

Eller flere ting:

```java
// Implementasjonskode
public class Pair<A, B> {
  private A first;
  private B second;

  public Pair(A first, B second) {
    this.first = first;
    this.second = second;
  }

  public A getFirst() {
    return this.first;
  }

  public B getSecond() {
    return this.second;
  }
}
```

Når man bruker Generics vil 