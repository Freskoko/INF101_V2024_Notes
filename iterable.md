# Iterable

Et Iterator -objekt kan «bla gjennom» en samling elementer én gang.

Et Iterable -objekt har en metode for å lage nye Iterator-objekter.

Et objekt som er Iterable kan man derfor bla igjennom flere ganger.

Example lager en iterator:
```java
public Iterable<ArrayList<String>> getAllWords(){
	return this.wordList //wordlist er en ArrayList<String>
}
```

Da kan man bruke den:

```java
public void printAllWords(){
	for (String word : this.getAllWords()){
		System.out.println(word)
	}
}

```
*Eksempel fra eksamen:*

todo
```
public Iterable<Page> getAllBookPages(){
	return (this.pages.deepCopy) kanskje igs
}
```
eller
```
for (int i = 0; i =< haroldPoter.size(); i++ ){
	String text = haroldPoter.getPage(i)
}
```