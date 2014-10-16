/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class State {
    private ArrayList<Headquarter> hqs;
    private ArrayList<Group> groups;
    static int numHeli;

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
}
