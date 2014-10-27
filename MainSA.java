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
        State state = helpers.stateFactory(4, 1, 100);
        State solution;
        try {
            solution = InitialSolution.getInitialSolution(state);
            Problem problem = new Problem(solution, new EvacuationSuccessorFunction(), new EvacuationGoalTest(), new EvacuationHeuristicFunction());
            Search search = new SimulatedAnnealingSearch(150, 200, 50, 0.0005);
            SearchAgent searchAgent = new SearchAgent(problem, search);
            List explanations = searchAgent.getActions();
            State a = (State)search.getGoalState();
            printState(a);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            solution = state;
        }
    }

    public static void printState(State state) {
        for (Headquarter hq:state.getHQs()) {
            System.out.println("--------------");
            System.out.println("Headquarter " + hq.getIdent());
            System.out.println("--------------");
            for (Helicopter h: hq.getHelicopters()) {
                System.out.print("Helicopter " + h.getIdent() + ": ");
                for (Pair<Integer,Integer> p:h.getItinerary()) {
                    System.out.print(p.getFirst() + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
        System.out.println("--------------");
        System.out.println("--------------");
        System.out.println("Total travel time for all helicopters: " + state.calculateHeuristic());
    }
}
