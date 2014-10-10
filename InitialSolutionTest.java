package com.company;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by alexey on 06/10/14.
 */
public class InitialSolutionTest {
    Random random = new Random();

    public ArrayList<Headquarter> generateHqList(int size) {
        ArrayList<Headquarter> hqlist = new ArrayList<Headquarter>();
        for (int i = 0; i < size; ++i) {
            Headquarter hq;
            if (i % 2 == 0) {
                hq = new Headquarter(i, 0, random.nextInt(100));
            }
            else {
                hq = new Headquarter(i, random.nextInt(100), 0);
            }

            hqlist.add(hq);
        }
        return hqlist;
    }

    public ArrayList<Group> generateGroupList(int size) {
        ArrayList<Group> grplist = new ArrayList<Group>();
        for (int i = 0; i < size; ++i) {
            grplist.add(new Group(
                    random.nextInt(15),  // random int from 0 to 15
                    2,                   // priority 2
                    random.nextInt(100), // random from 0 to 100
                    random.nextInt(100)  // random from 0 to 100
            ));
        }
        return grplist;
    }

    @Test
    public void simpleSolution() {
        Helicopter heli = new Helicopter(1, 0, 0);
        // add the helicopter to an hq of the hq list
        ArrayList<Headquarter> hqlist = generateHqList(1);
        hqlist.get(0).addHeli(heli);

        // create a bunch of groups
        Group grp1 = new Group(10, 2, 15, 15);
        Group grp2 = new Group(12, 2, 25, 30);
        Group grp3 = new Group(15, 2, 50, 10);

        // create a list of groups
        ArrayList<Group> grplist = new ArrayList<Group>(3);
        grplist.add(grp1);
        grplist.add(grp2);
        grplist.add(grp3);

        State initialSolution;
        try {
            initialSolution =
                    InitialSolution.getInitialSolution(hqlist, grplist, 1);
        } catch (Exception e) {
            System.out.println("Catched the exception: ");
            System.out.println(e.getMessage());
            e.printStackTrace();
            initialSolution = new State(new ArrayList<Headquarter>(), 0);
        }

        assertEquals(initialSolution.getHQs().
                get(0).getHelicopter(0).getItineraryLength(), 3);
    }

    @Test
    public void solutionWithFullDivision() {
        ArrayList<Headquarter> hqlist = generateHqList(1);
        Headquarter hq = hqlist.get(0);
        for (int i = 0; i < 3; ++i) {
            hq.addHeli(new Helicopter(i, 0, 0));
        }

        ArrayList<Group> grplist = generateGroupList(9);

        State initialSolution;
        try {
            initialSolution =
                    InitialSolution.getInitialSolution(hqlist, grplist, 3);
        } catch (Exception e) {
            System.out.println("Catched the exception: ");
            System.out.println(e.getMessage());
            e.printStackTrace();
            initialSolution = new State(new ArrayList<Headquarter>(), 0);
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
        ArrayList<Headquarter> hqlist = generateHqList(1);
        Headquarter hq = hqlist.get(0);
        for (int i = 0; i < 3; ++i) {
            hq.addHeli(new Helicopter(i, 0, 0));
        }

        ArrayList<Group> grplist = generateGroupList(10);

        State initialSolution;
        try {
            initialSolution =
                    InitialSolution.getInitialSolution(hqlist, grplist, 3);
        } catch (Exception e) {
            System.out.println("Catched the exception: ");
            System.out.println(e.getMessage());
            e.printStackTrace();
            initialSolution = new State(new ArrayList<Headquarter>(), 0);
        }

        assertEquals(initialSolution.getHQs().
                get(0).getHelicopter(0).getItineraryLength(), 3);
        assertEquals(initialSolution.getHQs().
                get(0).getHelicopter(1).getItineraryLength(), 3);
        assertEquals(initialSolution.getHQs().
                get(0).getHelicopter(2).getItineraryLength(), 4);
    }

    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main("com.company.InitialSolutionTest");
    }
}
