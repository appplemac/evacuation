package com.company;

import java.util.ArrayList;

/**
 * Created by alexey on 02/10/14.
 */
public class InitialSolution {
    public static State getInitialSolution(
            State state
    ) throws Exception {
        ArrayList<Group> groups = state.getGroups();
        ArrayList<Headquarter> hqs = state.getHQs();
        int numHelicopters = state.getNumHelicopters();
        int numGroups = groups.size();
        int groupsPerHelicopter = numGroups / numHelicopters;
        int lastAssignedGroup = 0;
        for (Headquarter hq: hqs) {
            for (Helicopter heli: hq.getHelicopters()) {
                for (int i = lastAssignedGroup;
                         i < lastAssignedGroup + groupsPerHelicopter &&
                         i < numGroups; ++i) {
                    heli.addToItinerary(i);
                }
                lastAssignedGroup += groupsPerHelicopter;
            }
        }
        if (lastAssignedGroup < numGroups) {
            Headquarter hq = hqs.get(hqs.size() - 1);
            Helicopter heli = hq.getHelicopter(hq.getNumHelicopters() - 1);
            for (int i = lastAssignedGroup; i < numGroups; ++i) {
                heli.addToItinerary(i);
            }
        }
        return state;
    }
}
