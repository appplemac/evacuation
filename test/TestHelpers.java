package com.company.test;

import com.company.Group;
import com.company.Headquarter;
import com.company.Helicopter;
import com.company.State;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: oleksiy.klochay
 * Date: 17/10/14
 * Time: 14:13
 * To change this template use File | Settings | File Templates.
 */
public class TestHelpers {
    Random random = new Random();

    int hqCount = 0;    // Holds the ids of the generated HQs
    int hqX = 0;        // The coordinates for
    int hqY = 0;        // those generated HQs

    int heliCount = 0;  // Holds the ids of the generated helicopters

    public Helicopter heliFactory() {
        Helicopter heli = new Helicopter(heliCount);
        ++heliCount;
        return heli;
    }

    public Headquarter hqFactory(int numHelisPerHq) {
        Headquarter hq = new Headquarter(hqCount, hqX, hqY);
        hqX += 10;
        hqY += 15;
        for (int i = 0; i < numHelisPerHq; ++i) {
            hq.addHeli(heliFactory());
        }
        return hq;
    }

    public ArrayList<Headquarter> hqArrayFactory(
            int size, int numHelisPerHq) {
        ArrayList<Headquarter> hal = new ArrayList<Headquarter>();
        for (int i = 0; i < size; ++i) {
            hal.add(hqFactory(numHelisPerHq));
        }
        return hal;
    }

    public Group groupFactory(int numPeople) {
        return new Group(numPeople,
                2,                    // priority
                random.nextInt(1000), // x coordinate
                random.nextInt(1000)  // y coordinate
        );
    }

    public ArrayList<Group> groupArrayFactory(int size) {
        ArrayList<Group> gal = new ArrayList<Group>();
        for (int i = 0; i < size; ++i) {
            gal.add(groupFactory(
                    random.nextInt(15) // May return 0!
            ));
        }
        return gal;
    }

    public State stateFactory(int numHqs, int numHelisPerHq, int numGroups) {
        State state = new State(
                hqArrayFactory(numHqs, numHelisPerHq),
                numHelisPerHq,
                groupArrayFactory(numGroups)
        );
        return state;
    }
}
