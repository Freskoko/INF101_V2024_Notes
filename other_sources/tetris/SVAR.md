For å fullføre laben, ber vi deg om å svare på følgende spørsmål. Svar på spørsmålene ved å fylle ut denne filen.

## Hva har du lært om Java og objekt-orientert programmering under arbeidet med denne oppgaven? Beskriv hvilke deloppgave(r) du jobbet med i læringsøyeblikket.

### Abstract

En viktig ting jeg lærte er hvor mye "abstracting" som skjer når man arbeider klasser som inheriter og implementer. Feks i dette tilfellei TetrisModel:

```java
  @Override
  public Iterable<GridCell<Character>> getTilesOnBoard() {
    return this.tetrisBoard;
  }
```

Hvor man kan returne this.tetromino, siden TetrisBoard extender Grid som implementerer IGrid<E> som extender Iterable<GridCell<E>>.

Så ting kan virkelig ligge gravd, som kan gjøre det till vanskeligere av å til, men gjør også koden mye pengere.

### Interface

Ett annet eksempel er hvor godt jeg synes at interface var å dele opp koden på. Feks at vi har en interface ViewableTetrisModel som styrer kun det å se brettet, og en interface ControllableTetrisModel kun styrer å "styre" brettet. Det å spesifiere dette, og fastslå at hver funksjon i disse interfacene gjør "en" ting, er veldig trygt.

### Testing

Det var litt "humbling", å få testene til å feile. Jeg hadde litt tanker rundt at testene kanskje kunne vært litt "time-waster", men jeg fant ut hvor lure de var, når jeg skulle legge til flere funksjoner, og klarte å ødlegge gamle ting, men testene sa ifra om akkurat hva jeg ødla.

Ja, og enums var en lur måte å lagre data på uten å matte lage en helt ny klasse osv.

## Hva er det neste du ønsker å lære om Java og programmering?

Vil kanskje sette meg inn i mer Grid ting, feks lage en platformer. Har også tenkt på java som backend i nett, men dette var bare en liten ide.

Mye i java har vært veldig lærerikt, og jeg setter god pris på hvor bombesikker man er på typer og returnverdier (type-hints i python er ikke helt det samme), og dette språket vil jeg gjerne bruke videre.

## Hvilke grep gjør vi for å øke modulariteten i koden? Gi noen eksempeler.

Ved å bruke Generics i Grid feks, så tillater vi at Grid kan ha flere typer objekter. Feks hvis vi skulle laget snake, så kunne vi brukt Grid i det tilfelle og. Man kunne sikkert også bruke CellPositionToPixelConverter, og ColorTheme også.

Det at vær funksjon gjør kun en ting, gjør det veldig lett å endre en ting om gangen også, dette synes jeg var supert.

Interfacet TetrominoFactory var helt super, siden det tillot å skrive super lette tester, ved å lage mange forskjellige typer Factories, hvor man vet akkurat hvilken brikke man forventer. 

Jeg synes også at funksjonen paintComponent var super, siden man kan få den til å kalle på andre funksjoner som tegner. Det gjør at forskjellige komponenter på brettet (backgroundimage/preview/hovedspillet) kan håndteres seperat.

Til slutt vil jeg skryte litt :P og si at jeg klarte å sette opp en god initBoardUtils funksjon i TestTetrisModel som gjorde det lett å lage flere tester.

Dette var en supergøy oppgave, fortsett med dette til faget videre.
