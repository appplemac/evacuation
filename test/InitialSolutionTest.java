package com.company.test;

import com.company.*;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by alexey on 06/10/14.
 */
public class InitialSolutionTest {
    TestHelpers helpers = new TestHelpers();

    @Test
    public void simpleSolution() {
        State state = helpers.stateFactory(1, 1, 3);

        State initialSolution;
        try {
            initialSolution =
                    InitialSolution.getInitialSolution(state);
        } catch (Exception e) {
            System.out.println("Catched the exception: ");
            System.out.println(e.getMessage());
            e.printStackTrace();
            initialSolution = new State(new ArrayList<Headquarter>(), 0,
                    new ArrayList<Group>());
        }

        assertEquals(initialSolution.getHQs().
                get(0).getHelicopter(0).getItineraryLength(), 3);
    }

    @Test
    public void solutionWithFullDivision() {
        State state = helpers.stateFactory(1, 3, 9);

        State initialSolution;
        try {
            initialSolution =
                    InitialSolution.getInitialSolution(state);
        } catch (Exception e) {
            System.out.println("Catched the exception: ");
            System.out.println(e.getMessage());
            e.printStackTrace();
            initialSolution = new State(new ArrayList<Headquarter>(), 0,
                    new ArrayList<Group>());
        }

        assertEquals(initialSolution.getHQs().
                get(0).getHelicopter(0).getItineraryLength(), 3);
        assertEquals(initialSolution.getHQs().
                get(0).getHelicopter(1).getItineraryLength(), 3);
        assertEquals(initialSolution.getHQs().
                get(0).getHelicopter(2).getItineraryLength(), 3);
    }

    @Test
    public void solutionWithIncompleteDivision() {
        ArrayList<Headquarter> hqlist = helpers.hqArrayFactory(1, 3);
        ArrayList<Group> grplist = helpers.groupArrayFactory(10);
        State state = helpers.stateFactory(1, 3, 10);

        State initialSolution;
        try {
            initialSolution =
                    InitialSolution.getInitialSolution(state);
        } catch (Exception e) {
            System.out.println("Catched the exception: ");
            System.out.println(e.getMessage());
            e.printStackTrace();
            initialSolution = new State(new ArrayList<Headquarter>(), 0,
                    new ArrayList<Group>());
        }

        assertEquals(initialSolution.getHQs().
                get(0).getHelicopter(0).getItineraryLength(), 3);
        assertEquals(initialSolution.getHQs().
                get(0).getHelicopter(1).getItineraryLength(), 3);
        assertEquals(initialSolution.getHQs().
                get(0).getHelicopter(2).getItineraryLength(), 4);
    }

    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main("com.company.test.InitialSolutionTest");
    }
}
