package com.company;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import aima.search.framework.Successor;
/**
 *
 * @author Usuario
 */
public class State {
    private ArrayList<Headquarter> hqs;
    private ArrayList<Group> groups;
    static int numHeli;
    int numits=0;
    int idHeliMaximo =0;

    public State(ArrayList<Headquarter> hq, int numHeliPerHQ, ArrayList<Group> group) {
        hqs = hq;
        groups = group;
        numHeli = numHeliPerHQ;
    }

    public State(State state) {
        hqs = new ArrayList<Headquarter>();
        groups = new ArrayList<Group>();
        for (Headquarter hq: state.hqs) hqs.add(new Headquarter(hq));
        for (Group g: state.groups) groups.add(new Group(g));
        numits = 0;
        idHeliMaximo = 0;
    }

    public void addHQ(Headquarter hq){
        hqs.add(hq);
    }
    
    public ArrayList<Headquarter> getHQs(){
        return hqs;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public int getNumHelicopters() {
        int count = 0;
        for (Headquarter hq: hqs) {
            count += hq.getNumHelicopters();
        }
        return count;
    }

    public Helicopter getHeliWithLongestTravelTime() {
        Helicopter res = hqs.get(0).getHelicopter(0);
        int max = 0;
        for (Headquarter hq:hqs) {
            Helicopter heli = hq.getHeliWithLongestTravelTime(this);
            if (heli.getTravelTime(this) > max) res = heli;
        }
        return res;
    }

    public int getnumHeli(){
        return numHeli;
    }
    
    public void moveGroup(int idHeli1, int idHeli2, int indexOrigen, int indexDestino){
        Pair<Integer,Integer> aux;
        int indexHQ = idHeli1 / numHeli;
        int indexHeli = idHeli1 % numHeli;
        Headquarter auxHQ = hqs.get(indexHQ);
        Helicopter auxHeli = auxHQ.getHelicopter(indexHeli);
        aux = auxHeli.getGroup(indexOrigen);
        ArrayList< Pair<Integer,Integer> > itinerary1 = auxHeli.getItinerary();
        if (itinerary1.get(indexOrigen).getSecond() == 0) {
            if (indexOrigen+1 < itinerary1.size()) {
                if (itinerary1.get(indexOrigen+1).getSecond() == 1) {
                    itinerary1.get(indexOrigen+1).setSecond(0);
                }
            }
        }
        auxHeli.getItinerary().remove(indexOrigen);
        indexHQ = idHeli2 / numHeli;
        indexHeli = idHeli2 % numHeli;
        auxHQ = hqs.get(indexHQ);
        Helicopter auxHeli2 = auxHQ.getHelicopter(indexHeli);
        auxHeli2.getItinerary().add(indexDestino, aux);
        ArrayList< Pair<Integer,Integer> > itinerary2 = auxHeli2.getItinerary();
        if (itinerary2.get(indexDestino).getSecond() == 1) {
            if (indexDestino+1 < itinerary2.size() && indexDestino-1 > 0) {
                if (itinerary2.get(indexDestino+1).getSecond() == 1 && itinerary2.get(indexDestino-1).getSecond() == 1) {
                    itinerary2.get(indexDestino).setSecond(0);
                }
            }
        }
    }

    public void joinRescues(int idHeli, int index1, int index2) {
        int indexHQ = idHeli / numHeli;
        int indexHeli = idHeli % numHeli;
        Headquarter auxHQ = hqs.get(indexHQ);
        auxHQ.getHelicopter(indexHeli).joinGroups(index1, index2);
    }

    public int getRandom(int max) {
        int value;
        SecureRandom rand = new SecureRandom();
        SecureRandom rand2 = new SecureRandom();
        value = (rand.nextInt(max+1) * rand2.nextInt(max+2)) % max;
        return value;
    }
    
    public ArrayList<Successor> generateSuccessors (){
        ArrayList<Successor> successors = new ArrayList<Successor>();
        String explanation;
        int numits = groups.size() * 10;
        for (int i = 0; i < numits; ++i) {
            Headquarter hqOfHeli1 = hqs.get(getRandom(hqs.size()));
            Helicopter heli1 = hqOfHeli1.getHelicopter(getRandom(hqOfHeli1.getNumHelicopters()));
            State modified = new State(this);
            Headquarter hqOfHeli2 = hqs.get(getRandom(hqs.size()));
            Helicopter heli2 = hqOfHeli2.getHelicopter(getRandom(hqOfHeli2.getNumHelicopters()));
            int retryCount = 0;
            while (retryCount < 5 && heli2 == heli1) {
                heli2 = hqOfHeli2.getHelicopter(getRandom(hqOfHeli2.getNumHelicopters()));
                ++retryCount;
            }

            if (heli1.getItineraryLength() == 0 || heli2.getItineraryLength() == 0) {
                continue;
            }
            int indexGrp1 = getRandom(heli1.getItineraryLength());
            int indexGrp2 = getRandom(heli2.getItineraryLength());

            if (retryCount < 5) {
                // Consistent state
                modified.moveGroup(heli1.getIdent(), heli2.getIdent(), indexGrp1, indexGrp2);
                explanation = "Moved group " + indexGrp1 + " from heli " + heli1.getIdent() + " to position " + indexGrp2 + " from heli " + heli2.getIdent();
            }
            else explanation = "";

            indexGrp1 = getRandom(heli2.getItineraryLength());
            indexGrp2 = getRandom(heli2.getItineraryLength());
            retryCount = 0;
            while (retryCount < 5 && !heli2.suitableForJoin(indexGrp1, indexGrp2, groups)) {
                indexGrp1 = getRandom(heli2.getItineraryLength());
                indexGrp2 = getRandom(heli2.getItineraryLength());
                ++retryCount;
            }
            if (retryCount < 5) {
                // Consistent state
                modified.joinRescues(heli2.getIdent(), indexGrp1, indexGrp2);
                explanation = explanation + " AND joined group " + indexGrp1 + " from heli " + heli1.getIdent() + " with group " + indexGrp2 + " from heli " + heli2.getIdent();
            }
            successors.add(new Successor(explanation, modified));
        }
        return successors;
    }

    public boolean isGoal(){
        return false;
    }

//    public int calculateHeuristic(){
//        int itineraryLengthSum = 0;
//        int maxItineraryLength = 0;
//        int numHelis = 0;
//        for (Headquarter hq : hqs){
//            for (Helicopter h: hq.getHelicopters()){
//                int length = h.getItineraryLength();
//                if (length > maxItineraryLength) maxItineraryLength = length;
//                itineraryLengthSum += length;
//                ++numHelis;
//            }
//        }
//
//        int meanItineraryLength = itineraryLengthSum / numHelis;
//        int heuristic = meanItineraryLength + maxItineraryLength^2;
//        return heuristic;
//    }

    public int calculateHeuristic(){
        int sum = 0;
        for (Headquarter hq : hqs){
            for (Helicopter h: hq.getHelicopters()) {
                int time = h.getTravelTime(this);
                sum += time;
            }
        }
        System.out.println(sum);
        return sum;
    }
}
