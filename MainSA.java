package com.company;

import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.SimulatedAnnealingSearch;
import com.company.test.TestHelpers;

import java.util.List;

/**
 * Created by alexey on 26/10/14.
 */
public class MainSA {
    public static void main(String[] args) {
        TestHelpers helpers = new TestHelpers();
        State state = helpers.stateFactory(4, 4, 100);
        State solution;
        try {
            solution = InitialSolution.getInitialSolution(state);
            Problem problem = new Problem(solution, new EvacuationSuccessorFunction(), new EvacuationGoalTest(), new EvacuationHeuristicFunction());
            Search search = new SimulatedAnnealingSearch(1000, 100, 50, 0.001);
            SearchAgent searchAgent = new SearchAgent(problem, search);
            List explanations = searchAgent.getActions();
            State a = (State)search.getGoalState();
            System.out.println("The size of explanations list is " + explanations.size()+ " Goal State :" + a.numits);
            for (Object o:explanations) {
//                System.out.println((String)o);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            solution = state;
        }
    }

}
