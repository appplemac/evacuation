package com.company;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.HillClimbingSearch;
import com.company.test.TestHelpers;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        TestHelpers helpers = new TestHelpers();
        State state = helpers.stateFactory(4, 4, 100);
        State solution;
        try {
            solution = InitialSolution.getInitialSolution(state.getHQs(), state.getGroups(), 4);
            Problem problem = new Problem(solution, new EvacuationSuccessorFunction(), new EvacuationGoalTest(), new EvacuationHeuristicFunction());
            Search search = new HillClimbingSearch();
            SearchAgent searchAgent = new SearchAgent(problem, search);

            List explanations = searchAgent.getActions();
            System.out.println("The size of explanations list is " + explanations.size());
            for (Object o:explanations) {
                System.out.println((String)o);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            solution = state;
        }
    }
}
