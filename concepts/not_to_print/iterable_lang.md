
Hvis noe er "Iterable" betyr det at det kan loopes over flere ganger. Det iterable gjør er å gi ut "iterator" objekter som har metoder slik som "getNext()" og "hasNext()" som betyr at de kan gå gjennom iterable-objketet bare en gang. For å kunne bruke denne looping funksjonaliteten må en klasse implementere iterable<objekt som gies ut i hver loop.



En iterator brukes i en klasisk `foreach` løkke slikt:



Eksempel på iterator:



for (Book book :Library){

   ....

}



Vi kan definere noe som iterable sånn at vi kan lage nye iterator objekter som lar oss loope gjennom objekter i koden. Fra sem1 så brukte vi iterator for å loope over alle brikkene til en tetromino når man feks flyttet den.



Ellers så har vi brukt IGrid/Grid i en del av labbene, og disse implementerer iterable slikt at vi kan loope over alle "brikkene" på en skjerm.




Eksempel på iterable



public class Libraray implements iterable<Book>{



this.books = new ArrayList<Book>;



     @Override

      public Iterator<Book> iterator(){

           return this.books.iterator();

      }

}



ArrayList implementere allerede iterable, som gjør at vi ikke trenger å implementere getNext().. og andre metoder, vi kan bare gi jobben videre til ArrayList.







