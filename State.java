/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company;
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
        auxHeli.getItinerary().remove(indexOrigen);
        indexHQ = idHeli2 / numHeli;
        indexHeli = idHeli2 % numHeli;
        auxHQ = hqs.get(indexHQ);
        Helicopter auxHeli2 = auxHQ.getHelicopter(indexHeli);
        auxHeli2.getItinerary().add(indexDestino, aux);
        
        ArrayList< Pair<Integer,Integer> > itinerary1 = auxHeli.getItinerary();
        ArrayList< Pair<Integer,Integer> > itinerary2 = auxHeli2.getItinerary();
        int sumcargo = 0;
        boolean first = true;
        for(Pair<Integer,Integer> auxp1 : itinerary1){
            Group g1 = groups.get(auxp1.getFirst());
            int people = g1.getNumPeople();
            if (first){
                auxp1.setSecond(0);
                sumcargo = sumcargo+people;
                first = false;
            }
            else if (people + sumcargo <= 15 && !first){
                auxp1.setSecond(1);
                sumcargo = sumcargo + people;
            }
            else {
                auxp1.setSecond(0);
                sumcargo = people;
            }
        }
        sumcargo = 0;
        first = true;
        for(Pair<Integer,Integer> auxp2 : itinerary2){
            Group g2 = groups.get(auxp2.getFirst());
            int people = g2.getNumPeople();
            if (first){
                auxp2.setSecond(0);
                sumcargo = sumcargo+people;
                first = false;
            }
            else if (people + sumcargo <= 15 && !first){
                auxp2.setSecond(1);
                sumcargo = sumcargo + people;
            }
            else {
                auxp2.setSecond(0);
                sumcargo = people;
            }
        }
    }

    public void joinRescues(int idHeli, int index1, int index2) {
        int indexHQ = idHeli / numHeli;
        int indexHeli = idHeli % numHeli;
        Headquarter auxHQ = hqs.get(indexHQ);
        auxHQ.getHelicopter(indexHeli).joinGroups(index1, index2);
    }

    public ArrayList<Successor> generateSuccessors (){
        ArrayList<Successor> successors = new ArrayList<Successor>();
        Random random = new Random();
        for (int i = 0; i < 100; ++i) {
            Headquarter hqOfHeli1 = hqs.get(random.nextInt(hqs.size()));
            Helicopter heli1 = hqOfHeli1.getHelicopter(random.nextInt(hqOfHeli1.getNumHelicopters()));
            State modified = new State(this);
            Headquarter hqOfHeli2 = hqs.get(random.nextInt(hqs.size()));
            Helicopter heli2 = hqOfHeli2.getHelicopter(random.nextInt(hqOfHeli2.getNumHelicopters()));
            while (heli2 == heli1) heli2 = hqOfHeli2.getHelicopter(random.nextInt(hqOfHeli2.getNumHelicopters()));
            int indexGrp1 = random.nextInt(heli1.getItineraryLength());
            int indexGrp2 = random.nextInt(heli2.getItineraryLength());
            modified.moveGroup(heli1.getIdent(), heli2.getIdent(), indexGrp1, indexGrp2);

            indexGrp1 = random.nextInt(heli2.getItineraryLength());
            indexGrp2 = random.nextInt(heli2.getItineraryLength());
            while (indexGrp1 == indexGrp2) indexGrp2 = random.nextInt(heli2.getItineraryLength());
            if (heli2.getGroup(indexGrp1).getSecond() == 0
                    && heli2.getGroup(indexGrp2).getSecond() == 0) {
                modified.joinRescues(heli2.getIdent(), indexGrp1, indexGrp2);
            }
            successors.add(new Successor("", modified));
        }
        return successors;
    }

    public boolean isGoal(){
        System.out.println("Its1: "+numits);
        if (numits >= 100){
            System.out.println("Its2: "+numits);
            return true;
        }
        else {
            return false;
        }
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
