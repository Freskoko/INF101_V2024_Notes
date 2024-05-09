# Teller

```java
public interface ICounter {

    /*
    * Gives the current counter number
    *
    * @returns the current counter number
    */
    public int getCounterNumber();

    /*
    * Increases the number on the counter by one
    */
    public void increaseCounterByOne();

    /*
    * Sets the counter to 0
    */
    public void resetCounter();
}
```

```java

public class Counter implements ICounter {

    private int countNum;

    public Counter (){
        this.countNum = 0;
    }

    public Counter (int num){
        if (num < 0){
            throw new IllegalStateException("Counter num cannot be less than 0")
        }
        else{
            this.countNum = num;
        }
    }

    @Override
    public int getCounterNumber(){
        return this.countNum // int immutable so this is good
    }

    @Override
    public void increaseCounterByOne(){
        if (this.countNum + 1 < 0){
            throw new IllegalStateException("Counter num cannot be less than 0")
        }
        else{
            this.countNum ++;
        }
    }

    @Override
    public void resetCounter(){
        this.countNum = 0;
    }
}
```


```java
public interface IPair<T,E> {

    /* @return the first element in the pair */
    public T getFirst();

    /* @return the second element in the pair */
    public E getSecond();
}

public interface IIntegerPair implements IPair<Integer,Integer>{

    /* @return the sum of the two elements*/
    public int getSum();
}

public class IntegerPair implements IIntegerPair {

    private Integer first;
    private Integer second;

    public IntegerPair (Integer first, Integer, second){
        this.first = first;
        this.second = second;
    }

    @Override
    public Integer getFirst(){
        return this.first;
    }

    @Override
    public Integer getSecond(){
        return this.second;
    }

    @Override
    public Integer getSum(){
        return this.first + this.second;
    }
}
```