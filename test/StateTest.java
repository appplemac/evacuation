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
    TestHelpers helpers = new TestHelpers();
    // defaults
    int numHelisPerHq = 2;
    int numHqs = 5;
    int numGroups = 10;

    @Test
    public void moveGroup() {
        State state = helpers.stateFactory(numHqs, numHelisPerHq, numGroups);

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

        int groupIndex1 = 0;
        state.moveGroup(h1.getIdent(), h2.getIdent(),
                groupIndex1, 0);
        assertThat("groups get moved",
                h2.getGroupId(0),
                equalTo(1)
                );
        assertThat("size of itinerary of h1 has changed",
                h1.getItineraryLength(), equalTo(3));
        assertThat("size of itinerary of h2 has changed",
                h2.getItineraryLength(), equalTo(5));
    }

    @Test
    public void joinRescues() {
        int numGroups = 10;
        State state = helpers.stateFactory(1, 1, numGroups);

        Helicopter heli = state.getHQs().get(0).getHelicopter(0);
        for (int i = 0; i < numGroups; ++i) heli.addToItinerary(i);

        state.joinRescues(0, 0, 1);
        assertThat("group at the index 1 us joined to the rescue of group at the index 0",
                heli.getGroupStatus(1), equalTo(1));
    }
}
