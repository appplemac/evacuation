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
public class Headquarter {
    private int HQident;
    private ArrayList<Helicopter> helicopters;
    
    
    public Headquarter(int id){
        HQident = id;
        helicopters = new ArrayList<Helicopter>();
    }
    
    public int getIdent(){
        return HQident;
    }
    
    public ArrayList<Helicopter> getHelicopters(){
        return helicopters;
    }

    public Helicopter getHelicopter(int index) {
        return helicopters.get(index);
    }

    public int getNumHelicopters() {
        return helicopters.size();
    }
    
    public void addHeli(Helicopter h){
        helicopters.add(h);
    }
}
