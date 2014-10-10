/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company;

import javax.jnlp.IntegrationService;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Helicopter {
    private static int cargo = 15;

    private int ident;
    private int capacity;
    private int coordx;
    private int coordy;
    private boolean full;
    private ArrayList<Pair<Integer,Integer> > itinerary = new ArrayList<Pair<Integer,Integer> >();
    
    public Helicopter(int id, int x, int y){
        ident = id;
        capacity = 0;
        full = false;
        coordx = x;
        coordy = y;
    }
    
    public int getIdent(){
        return ident;
    }
    
    public int getCapacity(){
        return capacity;
    }
    
    public boolean Full(){
        return full;
    }
    
    public boolean itFits(int num){
        return num + capacity > cargo;
    }
    
    public int getCargo(){
        return cargo;
    }
          
    public String getcoordinates(){
        String pos;
        pos = Integer.toString(coordx)+' '+Integer.toString(coordy);
        return pos;
    }

    public void addToItinerary(int idGroup) /*throws Exception*/ {
        Pair<Integer,Integer> p = new Pair<Integer,Integer>(idGroup, 0);
        itinerary.add(p);
    }

    public ArrayList<Pair<Integer,Integer> > getItinerary(){
        return itinerary;
    }

    public int getGroup(int indexGroup){
        return itinerary.get(indexGroup).getFirst();
    }

    public int getItineraryLength() {
        return itinerary.size();
    }
}
