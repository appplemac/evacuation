package com.company;
import aima.search.framework.HeuristicFunction;
import java.util.ArrayList;

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
        ArrayList<Helicopter> h =  state.getHQs().get(0).getHelicopters();
        Helicopter h1 = h.get(1);
       // for (Helicopter h1: h ){
            System.out.print("Indice helicoptero : "+h1.getIdent());
             for(Pair<Integer,Integer> p : h1.getItinerary()){
                System.out.print(" Indice itinerario : "+p.getFirst());
             }
            System.out.println("");
        //}
        double res;
        res = state.calculateHeuristic();
        //System.out.println(""+res);
        return res;
    }
}
