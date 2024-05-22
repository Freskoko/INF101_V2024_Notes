package no.uib.inf101.iterableIterator;

import java.util.ArrayList;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        List<String> myList = new ArrayList<>();
        myList.add("kake");
        myList.add("pai");
        myList.add("taco");

        for (String item : myList) {
            System.out.println(item);
        }
    }
}
