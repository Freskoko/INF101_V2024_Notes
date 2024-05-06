# Interface

*A type of Polymorphism*

*Interface gjør det mulig å ha flere klasser som har samme metoder men forskjellig oppførsel*

A class that takes on the role of several classes through inheritence (extends) or interface (implements) is an example of polymorphism (polymorphism = many forms)

```java
interface CommandLineInterface {
  /** Press a key on the keyboard. */
  void pressKey(char key);

  /** Returns the content to be shown on the screen. */
  String screenContent();
}
```

kan lage en dummy shell (som implementerer CommandLineInterface)

```CommandLineInterface dummy = new DummyShell;```

eller to egene

```java
DummyShell dummy = new DummyShell();
EchoShell echo = new EchoShell();

writeHelloWorld(dummy);
writeHelloWorld(echo);
```

Grensesnitt kan utvides:

`interface CommandLineInterface extends KeypressReceiver, ScreenContentProvider {
}`

![alt text](imgs/interface.png)