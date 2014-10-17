/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company;
import java.util.ArrayList;
import java.util.Random;
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

    public State(int n, int numHeliPerHQ){
        hqs = new ArrayList<Headquarter> (n);
        numHeli = numHeliPerHQ;
    }
        
    public void MakeAssignment (int identHel, int identHQ){
/*        for ( Headquarter  i: hqs){
            if (i.getident() ==identHQ){
                i.getHelicopters().add(identHel);
            }
        }*/
    }
    
    public void addHQ(Headquarter hq){
        hqs.add(hq);
    }
    
    public ArrayList<Headquarter> getHQs(){
        return hqs;
    }

    public int getNumHelicopters() {
        int count = 0;
        for (Headquarter hq: hqs) {
            count += hq.getNumHelicopters();
        }
        return count;
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
    }

    public void joinRescues(int idHeli, int index1, int index2) {
        int indexHQ = idHeli / numHeli;
        int indexHeli = idHeli % numHeli;
        Headquarter auxHQ = hqs.get(indexHQ);
        auxHQ.getHelicopter(indexHeli).joinGroups(index1, index2);
    }

    public ArrayList<State> generateSuccessors (){
        ArrayList<State> successors = new ArrayList<State>();
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
                            successors.add(modif);
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
                            if (found) successors.add(modif);
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
        int value=0;
        for (Headquarter hq : hqs){
             for (Helicopter h: hq.getHelicopters()){

             }
        }
        return value;
    }
}
