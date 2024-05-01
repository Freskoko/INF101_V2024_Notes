# Comparable
todo LES OM SORTERING

for å bruke

`Collections.sort(List<ItemThing>)`

Så må ItemThing implement comparable

Java buildt-in typer implementer allerede comparable (feks String)

```java

public class MusicSorter{

    public static void sortByArtist(){
        collecitons.sort(mucsic)
    }

    public static void sortByReleaseDate(){
        collections.sort(music, new ReleaseDateCompatator)
    }
}

public class ItemThing implements Comparable<ItemThing>{

    // da må vi legge til compare to for å implemente interfacet
    @Override
    public int compareTo(Song o){
        // i dette tilfelle har ItemThing en getArtist metode 
        // som git ett annet objekt, og den har bygd inn compareTo
        return this.getArtist.compareTo(o.getArtist());
    }
};
```
Men hva hvis vi vil ha flere ting sammenlignet? Da må vi lage en annen klasse:
```java
public class ReleaseDateComparator implements Comparator<Song>{

    @Override
    public int compare (Song song1, Song song2){
        return song1.getReleaseDate().compareTo(song2.getReleaseDate)
    }

}
```