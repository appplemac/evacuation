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

    public State(ArrayList<Headquarter> hq) {
        hqs = hq;
    }

    public State(int n){
        hqs = new ArrayList<Headquarter> (n);
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
}
