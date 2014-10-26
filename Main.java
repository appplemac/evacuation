package com.company;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.HillClimbingSearch;
import aima.search.informed.SimulatedAnnealingSearch;
import com.company.test.TestHelpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TestHelpers helpers = new TestHelpers();
        State state = helpers.stateFactory(4, 4, 100);
        State solution;
        try {
            solution = InitialSolution.getInitialSolution(state);
            Problem problem = new Problem(solution, new EvacuationSuccessorFunction(), new EvacuationGoalTest(), new EvacuationHeuristicFunction());
            Search search = new HillClimbingSearch();
            SearchAgent searchAgent = new SearchAgent(problem, search);
            List explanations = searchAgent.getActions();
            State a = (State)search.getGoalState();
            System.out.println("The size of explanations list is " + explanations.size()+ " Goal State :" + a.numits);
            for (Object o:explanations) {
                System.out.println((String)o);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            solution = state;
        }
    }

    public static void data_entry(String[] args) {
        Scanner in = new Scanner(System.in);
        int numHelicopters = in.nextInt();

        // Expected input format:
        // An int specifying the number of helicopters, followed by
        // the ids of all the helicopters
        ArrayList<Helicopter> helicopters = new ArrayList<Helicopter>(numHelicopters);
        for (int i = 0; i < numHelicopters; ++i) {
            helicopters.add(new Helicopter(in.nextInt()));
        }


        // Ints for id, x and y of each heaquarter
        int numHqs = in.nextInt();
        int helisPerHq = numHelicopters / numHqs;
        ArrayList<Headquarter> hqs = new ArrayList<Headquarter>(numHqs);
        for (int i = 0; i < numHqs; ++i) {
            Headquarter hq = new Headquarter(in.nextInt(), in.nextInt(), in.nextInt());
            for (int j = 0; j < helisPerHq; ++j) {
                hq.addHeli(helicopters.get(i*helisPerHq + j));
            }
            hqs.add(hq);
        }


        // Ints for number of people in the group, priority,
        // x and y coordinates of the group
        int numGroups = in.nextInt();
        ArrayList<Group> groups = new ArrayList<Group>(numGroups);
        for (int i = 0; i < numGroups; ++i) {
            groups.add(new Group(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt()));
        }

        State state = new State(hqs, helisPerHq, groups);
        State solution;
        try {
            solution = InitialSolution.getInitialSolution(state);
            Problem problem = new Problem(solution, new EvacuationSuccessorFunction(), new EvacuationGoalTest(), new EvacuationHeuristicFunction());
            Search search = new HillClimbingSearch();
            SearchAgent searchAgent = new SearchAgent(problem, search);

            List explanations = searchAgent.getActions();
            System.out.println("The size of explanations list is " + explanations.size());
            for (Object o:explanations) {
                System.out.println((String)o);
            }
            System.out.println("The time taken to rescue all the groups is " + " minutes");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            solution = state;
        }
    }
}