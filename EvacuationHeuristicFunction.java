package com.company;
import aima.search.framework.HeuristicFunction;

/**
 * Created with IntelliJ IDEA.
 * User: maximino.puncernau
 * Date: 17/10/14
 * Time: 14:26
 * To change this template use File | Settings | File Templates.
 */
public class EvacuationHeuristicFunction implements HeuristicFunction {
    public double getHeuristicValue(Object a){
        State state = (State)a;
        double res;
        res = state.calculateHeuristic();
        return res;
    }
}
