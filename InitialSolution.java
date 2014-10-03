package com.company;

import java.util.ArrayList;

/**
 * Created by alexey on 02/10/14.
 */
public class InitialSolution {
    public State getInitialSolution(ArrayList<Headquarter> hqs, ArrayList<Group> groups) {
        State state = new State(hqs);
        int numHelicopters = state.getNumHelicopters();
        int numGroups = groups.size();
        int groupsPerHelicopter = numGroups / numHelicopters;
        int lastAssignedGroup = 0;
        for (Headquarter hq: hqs) {
            for (Helicopter heli: hq.getHelicopters()) {
                for (int i = lastAssignedGroup; i < lastAssignedGroup + groupsPerHelicopter; ++i) {
                    heli.addToItinerary(groups.get(i));
                }
                // Not tracking the case when division is not complete
                // Take care of the i condition
                lastAssignedGroup += groupsPerHelicopter;
            }
        }
        return state;
    }
}
