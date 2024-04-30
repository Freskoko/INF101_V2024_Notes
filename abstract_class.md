# abstract classes

abstract classes are like "wip" it allows to only implemenet some parts of interface!

we just pick and choose. someone else (or us) will implemenet the rest later

this means we can use all the methods from the interface without having them implemented

```java
public abstract class AbstractCoronaData implement ICoronaData{
    // implements stuff from gameentity but doesnt need to implement all
    // if it wants to only use some it can! 

    @Override
    public ArrayList<Integer> cumulativeDeaths() {
        ArrayList<Integer> cumulativeSum = new ArrayList<>(); 
        int sum = 0
        for (int deaths : getDailyDeaths()){ 
            // get daily deaths is from icorona interface. we dont need o
            // to implement getdailydeaths since class is abstract
            // someone else will do it later!

            sum+=deaths
            cumulativeSum.add(sum)
        }
        return cumulativeSum;
    }

    @Override ArrayList<Double> deathsPerMillion(){
        ArrayList<Double> cumulativeSum = new ArrayList<>(); 
        double millions = (double) this.getPopulation()/1000000
        // get population is from icorona interface. we dont need o
        // to implement get population since class is abstract
        // someone else will do it later!

        for (int deaths : getDailyDeaths()){ 
            rate.add( (double) deaths/millions);
        }
        return rate;
    }
}


```