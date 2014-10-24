package com.company;
import aima.search.framework.SuccessorFunction;

import java.util.ArrayList;
import aima.search.framework.Successor;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: oleksiy.klochay
 * Date: 22/10/14
 * Time: 19:27
 * To change this template use File | Settings | File Templates.
 */
public class EvacuationSuccessorFunction implements SuccessorFunction {
    public List getSuccessors(Object a) {
        State state = (State)a;
        System.out.println("Entro en el generar sucesores.");
        List<Successor>  res = state.generateSuccessors();
        System.out.println("Salgo del generar sucesores.");
        System.out.println("Size list: "+res.size());
        return res;
    }
}
