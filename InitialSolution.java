package com.company;

import java.util.ArrayList;

/**
 * Created by alexey on 02/10/14.
 */
public class InitialSolution {
    public State getInitialSolution(
            ArrayList<Headquarter> hqs, ArrayList<Group> groups
    ) throws Exception {
        State state = new State(hqs);
        int numHelicopters = state.getNumHelicopters();
        int numGroups = groups.size();
        int groupsPerHelicopter = numGroups / numHelicopters;
        int lastAssignedGroup = 0;
        for (Headquarter hq: hqs) {
            for (Helicopter heli: hq.getHelicopters()) {
                for (int i = lastAssignedGroup;
                         i < lastAssignedGroup + groupsPerHelicopter &&
                         i < numGroups; ++i) {
                    heli.addToItinerary(groups.get(i));
                }
                lastAssignedGroup += groupsPerHelicopter;
            }
        }
        if (lastAssignedGroup < numGroups) {
            Headquarter hq = hqs.get(hqs.size() - 1);
            Helicopter heli = hq.getHelicopter(hq.getNumHelicopters() - 1);
            for (int i = lastAssignedGroup; i < numGroups; ++i) {
                heli.addToItinerary(groups.get(i));
            }
        }
        return state;
    }
}
