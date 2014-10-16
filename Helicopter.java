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
public class Helicopter {
    private static int cargo = 15;

    private int ident;
    private int capacity;
    private boolean full;
    private ArrayList<Pair<Integer,Integer> > itinerary = new ArrayList<Pair<Integer,Integer> >();
    
    public Helicopter(int id){
        ident = id;
        capacity = cargo;
        full = false;
    }
    
    public int getIdent(){
        return ident;
    }
    
    public int getCapacity(){
        return capacity;
    }
    
    public boolean full(){
        return full;
    }
    
    public boolean itFits(int num){
        return num + capacity >= cargo;
    }
    
    public int getCargo(){
        return cargo;
    }
          
    public void addToItinerary(int idGroup) /*throws Exception*/ {
        Pair<Integer,Integer> p = new Pair<Integer,Integer>(idGroup, 0);
        itinerary.add(p);
    }

    public ArrayList<Pair<Integer,Integer> > getItinerary(){
        return itinerary;
    }

    public void joinGroups(int indexGroup1, int indexGroup2) {
        // Is there a way to check if we can actually
        // join those groups, e.g. to see if their total size
        // does not exceed the cargo capacity, etc?

        // Remove the second group from the list and
        // insert it back right after the first group
        Pair<Integer, Integer> grp2 = itinerary.remove(indexGroup2);
        // Indicate that the second group is joined with
        // the previous one
        grp2.setSecond(1);
        itinerary.add(indexGroup1+1, grp2);
    }

    public int getGroupId(int indexGroup){
        return itinerary.get(indexGroup).getFirst();
    }

    public int getGroupStatus(int indexGroup){
        return itinerary.get(indexGroup).getSecond();
    }

    public Pair<Integer, Integer> getGroup(int indexGroup) {
        return itinerary.get(indexGroup);
    }

    public int getItineraryLength() {
        return itinerary.size();
    }
}
