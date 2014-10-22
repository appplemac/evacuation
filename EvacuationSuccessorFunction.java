package com.company;
import aima.search.framework.SuccessorFunction;
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
        return state.generateSuccessors();
    }
}
