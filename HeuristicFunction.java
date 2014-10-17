package com.company;

/**
 * Created with IntelliJ IDEA.
 * User: maximino.puncernau
 * Date: 17/10/14
 * Time: 14:26
 * To change this template use File | Settings | File Templates.
 */
public class HeuristicFunction {
    public int getHeuristicValue(State a){
        int res;
        res = a.calculateHeuristic();
        return res;
    }
}
