/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company;

import java.lang.*;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.util.*;

/**
 *
 * @author Usuario
 */
public class Helicopter {
    private static int cargo = 15;
    private int time;
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

    
    public int getTravelTime(State a) {
        int aux = 0;
        Helicopter h = this;
        int indexHQ = ident/a.getnumHeli();
        Headquarter hq = a.getHQs().get(indexHQ);
        String coords = hq.getcoordHQ();
        Pair<Integer,Integer> pairCoords = coordsToPair(coords);
        int coordxHQ = pairCoords.getFirst();
        int coordyHQ = pairCoords.getSecond();
        int lastStatus=0;
        for (int i = 0; i < itinerary.size(); ++i){
            Pair<Integer,Integer> p;
            p = itinerary.get(i);
            int id = h.getGroupId(p.getFirst());
            Group g = a.getGroups().get(id);
            Double dist = 0.0;
            String c = g.getcoordG();
            Pair<Integer,Integer> coordsHeli = coordsToPair(c);
            int coordxHeli = coordsHeli.getFirst();
            int coordyHeli = coordsHeli.getSecond();
            int people = g.getNumPeople();
            if (g.getPriority() == 2) {
                people = people * 2;
            }
            if (p.getSecond() == 0){
                Double power1= pow((coordxHQ-coordxHeli),2);
                Double power2= pow((coordyHQ - coordyHeli),2);
                Double Euclidean = sqrt( power1+ power2);
                dist = dist + (Euclidean / 1.667)+20;
                lastStatus = 0;
            }
            else {
                if (lastStatus == 0){
                    int id2 = h.getGroupId(itinerary.get(i-1).getFirst());
                    Group g2 = a.getGroups().get(id2);
                    String nextc = g2.getcoordG();
                    Pair<Integer,Integer> coordsHeliPrev = coordsToPair(nextc);
                    int coordxHeliPrev = coordsHeli.getFirst();
                    int coordyHeliPrev = coordsHeli.getSecond();
                    Double power1= pow((coordxHeli - coordxHeliPrev),2);
                    Double power2= pow((coordyHeli - coordyHeliPrev),2);
                    Double Euclidean = sqrt( power1+ power2);
                    dist = dist + (Euclidean / 1.667);
                    lastStatus = 1;
                }
                int id2 = h.getGroupId(itinerary.get(i+1).getFirst());
                Group g2 = a.getGroups().get(id2);
                String nextc = g2.getcoordG();
                Pair<Integer,Integer> coordsHeliNext = coordsToPair(nextc);
                int coordxHeliNext = coordsHeli.getFirst();
                int coordyHeliNext = coordsHeli.getSecond();
                Double power1= pow((coordxHeli - coordxHeliNext),2);
                Double power2= pow((coordyHeli - coordyHeliNext),2);
                Double Euclidean = sqrt( power1+ power2);
                dist = dist + (Euclidean / 1.667);
                lastStatus = 1;
            }
            aux = people + dist.intValue();
        }
        return aux;
    }
    
    public Pair<Integer,Integer> coordsToPair(String s){
        Pair<Integer,Integer> coords = new Pair<Integer,Integer>(0,0);
                char[] Arrayc = s.toCharArray();
        String auxString = ""; 
        for (char auxchar : Arrayc){
            if (auxchar != ' '){
                auxString = auxString+auxchar;
            }
            else if (auxchar == ' '){
                coords.setFirst(Integer.parseInt(auxString));
                auxString = "";
            }
        }
        coords.setSecond(Integer.parseInt(auxString));
        return coords;
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
