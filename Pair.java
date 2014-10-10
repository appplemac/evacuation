package com.company;

/**
 * Created with IntelliJ IDEA.
 * User: maximino.puncernau
 * Date: 10/10/14
 * Time: 14:09
 * To change this template use File | Settings | File Templates.
 */
public class Pair<class1, class2> {
    private class1 first;
    private class2 second;

    Pair(class1 a, class2 b){
            first = a;
            second = b;
    }

    public class1 getFirst(){
            return first;
    }

    public class2 getSecond(){
        return second;
    }

    public void setSecond(class2 b){
         second = b;
    }
}
