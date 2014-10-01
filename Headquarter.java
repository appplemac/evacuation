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
    private int HQcoordx;
    private int HQcoordy;
    private int HQident;
    private ArrayList<Integer> Helicopters;
    
    
    public Headquarter(int id,int x, int y){
        HQident = id;
        HQcoordx= x;
        HQcoordy = y;
        Helicopters = new ArrayList<Integer>();
    }
    
    public int getident(){
        return HQident;
    }
    
    public ArrayList<Integer> getHelicopters(){
        return Helicopters;
    }
    
    public void addHeli(int n){
        Helicopters.add(n);
    }
    
    public String getcoordHQ(){
        String pos;
        pos = Integer.toString(HQcoordx)+' '+Integer.toString(HQcoordy);
        return pos;
    }
    
}
