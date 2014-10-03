/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company;
import java.util.ArrayList;
import java.util.ListIterator;
/**
 *
 * @author Usuario
 */
public class GroupOfHeadquarters {
    private ArrayList<Headquarter> HQ;

    public GroupOfHeadquarters(ArrayList<Headquarter> hq) {
        HQ = hq;
    }

    public GroupOfHeadquarters(int n){
        HQ = new ArrayList<Headquarter> (n);
    }
        
    public void MakeAssignment (int identHel, int identHQ){
        for ( Headquarter  i: HQ){
            if (i.getident() ==identHQ){
                i.getHelicopters().add(identHel);                
            }
        }
    }
    
    public void addHQ(Headquarter hq){
        HQ.add(hq);
    }
    
    public ArrayList<Headquarter> getHQs(){
        return HQ;
    }
    
    public  ArrayList<Integer> getHQHelicopters(int identHQ){
          for ( Headquarter  i: HQ){
            if (i.getident() ==identHQ){
                return i.getHelicopters();                
            }
        }
        return null;
    }
    
}
