package com.company.test;

import com.company.*;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by alexey on 16/10/14.
 */
public class StateTest {
    Random random = new Random();

    int heliCount = 0;
    int hqCount = 0;

    int hqX = 0;
    int hqY = 0;

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

    @Test
    public void moveGroup() {
        int numHelisPerHq = 2;
        int numHqs = 5;
        int numGroups = 10;
        State state = stateFactory(numHqs, numHelisPerHq, numGroups);

        Helicopter h1 = state.getHQs().get(1).getHelicopter(0);
        Helicopter h2 = state.getHQs().get(4).getHelicopter(1);

        h1.addToItinerary(1);
        h1.addToItinerary(2);
        h1.addToItinerary(3);
        h1.addToItinerary(4);

        h2.addToItinerary(5);
        h2.addToItinerary(6);
        h2.addToItinerary(7);
        h2.addToItinerary(8);

        int groupIndex1 = h1.getGroupId(2);
        state.moveGroup(h1.getIdent(), h2.getIdent(),
                groupIndex1, 0);
        assertThat("groups get moved",
                h2.getGroupId(0),
                equalTo(groupIndex1)
                );
    }
}
