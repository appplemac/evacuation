/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company;
import java.util.ArrayList;
import java.util.Random;
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

    public State(ArrayList<Headquarter> hq, int numHeliPerHQ, ArrayList<Group> group) {
        hqs = hq;
        groups = group;
        numHeli = numHeliPerHQ;
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
        ArrayList< Pair<Integer,Integer> > itinerary2 = auxHeli.getItinerary();
        int sumcargo = 0;
        boolean first = true;
        for(Pair<Integer,Integer> auxp1 : itinerary1){
            Group g1 = groups.get(auxp1.getFirst());
            int people = g1.getNumPeople();
            if (people + sumcargo <= 15 && !first){
                auxp1.setSecond(1);
                sumcargo = sumcargo + people;
            }
            else {
                auxp1.setSecond(0);
                sumcargo = people;
            }
            if (first){
                auxp1.setSecond(0);
                sumcargo = sumcargo+people;
                first = false;
            }
        }
        sumcargo = 0;
        first = true;
        for(Pair<Integer,Integer> auxp2 : itinerary2){
            Group g2 = groups.get(auxp2.getFirst());
            int people = g2.getNumPeople();
            if (people + sumcargo <= 15 && !first){
                auxp2.setSecond(1);
                sumcargo = sumcargo + people;
            }
            else {
                auxp2.setSecond(0);
                sumcargo = people;
            }
            if (first){
                auxp2.setSecond(0);
                sumcargo = sumcargo+people;
                first = false;
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
        State orig = this;
        State modif;
        for (Headquarter hq : hqs){
            for (Helicopter h : hq.getHelicopters()){
                for(Headquarter hq2 : hqs){
                    for(Helicopter h2 : hq2.getHelicopters()){
                        if (h.getIdent() != h2.getIdent()){
                            modif = orig;
                            Random rand = new Random();
                            int randNum = rand.nextInt();
                            int index1 = (hq.getHelicopters().size())% randNum;
                            randNum = rand.nextInt();
                            int index2 = (hq2.getHelicopters().size())% randNum;
                            modif.moveGroup(h.getIdent(), h2.getIdent(), index1, index2);
                            successors.add(new Successor("", modif));
                        } else {
                            modif = orig;
                            Boolean found = false;
                            for (int i = 0; i < h.getItinerary().size(); ++i) {
                                for (int j = i+1; j < h.getItinerary().size(); ++j) {
                                    if (i < h.getItinerary().size() - 1 && j < h.getItinerary().size())  {
                                        if (h.getItinerary().get(i).getSecond() == 0 && h.getItinerary().get(i+1).getSecond() == 0 &&
                                        h.getItinerary().get(j).getSecond() == 0 && h.getItinerary().get(j+1).getSecond() == 0 &&
                                        groups.get(h.getItinerary().get(i).getFirst()).getNumPeople() +
                                        groups.get(h.getItinerary().get(j).getFirst()).getNumPeople() <= h.getCapacity()) {
                                            modif.joinRescues(h.getIdent(),i,j);
                                            found = true;
                                            break;
                                        }
                                    }
                                }
                                if (found) break;
                            }
                            if (found) successors.add(new Successor("", modif));
                        }
                    }
                }
            }
        }
        return successors;
    }

    public boolean isGoal(){
         return numits == 100;
    }

    public int calculateHeuristic(){
        int itineraryLengthSum = 0;
        int maxItineraryLength = 0;
        int numHelis = 0;
        for (Headquarter hq : hqs){
            for (Helicopter h: hq.getHelicopters()){
                int length = h.getItineraryLength();
                if (length > maxItineraryLength) maxItineraryLength = length;
                itineraryLengthSum += length;
                ++numHelis;
            }
        }

        int meanItineraryLength = itineraryLengthSum / numHelis;
        int heuristic = meanItineraryLength + maxItineraryLength^2;
        return heuristic;
    }
}
