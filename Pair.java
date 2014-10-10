package com.company;

/**
 * Created with IntelliJ IDEA.
 * User: maximino.puncernau
 * Date: 10/10/14
 * Time: 14:09
 * To change this template use File | Settings | File Templates.
 */
public class Pair {
    private Object first;
    private Object second;

    Pair(Object a, Object b){
            first = a;
            second = b;
    }

    public Object getFirst(){
            return first;
    }

    public Object getSecond(){
        return second;
    }

    public void setSecond(Object b){
         second = b;
    }
}
