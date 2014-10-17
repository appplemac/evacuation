package com.company.test;

import com.company.Helicopter;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by alexey on 15/10/14.
 */
public class HelicopterTest {
    TestHelpers helpers = new TestHelpers();

    @Test
    public void basicGetters() {
        int id = 1;
        Helicopter heli = new Helicopter(id);
        assertThat("identifier",
                heli.getIdent(), equalTo(id));
        assertThat("capacity",
                heli.getCapacity(), equalTo(15)); // WHAT
        assertThat("full",
                heli.full(), equalTo(false));
        assertThat("cargo",
                heli.getCargo(), equalTo(15));
    }

    @Test
    public void fitsUpTo15People() {
        Helicopter heli = helpers.heliFactory();
        assertThat("normal case",
                heli.itFits(1), equalTo(true));
        assertThat("zero",
                heli.itFits(0), equalTo(true));
        assertThat("maximum",
                heli.itFits(15), equalTo(true));
//        assertThat("more than maximum",
//                heli.itFits(16), equalTo(false));
//        assertThat("extreme case",
//                heli.itFits(-1), equalTo(false)); // Or do we want an exception here?
    }

    @Test
    public void joinsGroups() {
        Helicopter heli = helpers.heliFactory();
        heli.addToItinerary(1);
        heli.addToItinerary(2);
        heli.addToItinerary(3);

        heli.joinGroups(0, 2);
        assertThat("group 3 is located at the position 2",
                heli.getGroupId(1), equalTo(3));
        assertThat("group 3 is joined with group 1",
                heli.getGroupStatus(1), equalTo(1));
    }

    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main("com.company.test.HelicopterTest");
    }
}
