package com.company;
import aima.search.framework.GoalTest;

/**
 * Created with IntelliJ IDEA.
 * User: maximino.puncernau
 * Date: 17/10/14
 * Time: 14:17
 * To change this template use File | Settings | File Templates.
 */
public class EvacuationGoalTest implements GoalTest {
    public boolean isGoalState(Object a){
        State state = (State)a;
        return  state.isGoal();
    }
}
