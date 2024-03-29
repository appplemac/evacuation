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
    private ArrayList<Pair<Integer,Integer> > itinerary;
    
    public Helicopter(int id){
        itinerary = new ArrayList<Pair<Integer,Integer> >();
        ident = id;
        capacity = cargo;
        full = false;
    }

    public Helicopter(Helicopter h) {
        itinerary = new ArrayList<Pair<Integer,Integer> >();
        ident = h.ident;
        capacity = h.capacity;
        full = h.full;
        for (Pair<Integer,Integer> p: h.itinerary) itinerary.add(new Pair(p));
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
            int id = p.getFirst();
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
                if (i == itinerary.size()-1){
                    Double power1= pow((coordxHQ-coordxHeli),2);
                    Double power2= pow((coordyHQ - coordyHeli),2);
                    Double Euclidean = sqrt( power1+ power2);
                    dist = dist + (Euclidean / 1.667)+20;
                    lastStatus = 0;
                }
                else if (lastStatus == 0 && i > 0){
                    int id2 = h.itinerary.get(i-1).getFirst();
                    Group g2 = a.getGroups().get(id2);
                    String nextc = g2.getcoordG();
                    Pair<Integer,Integer> coordsHeliPrev = coordsToPair(nextc);
                    int coordxHeliPrev = coordsHeliPrev.getFirst();
                    int coordyHeliPrev = coordsHeliPrev.getSecond();
                    Double power1= pow((coordxHeli - coordxHeliPrev),2);
                    Double power2= pow((coordyHeli - coordyHeliPrev),2);
                    Double Euclidean = sqrt( power1+ power2);
                    dist = dist + (Euclidean / 1.667);
                    lastStatus = 1;
                }
                else {
                    int id2 = h.itinerary.get(i+1).getFirst();
                    Group g2 = a.getGroups().get(id2);
                    String nextc = g2.getcoordG();
                    Pair<Integer,Integer> coordsHeliNext = coordsToPair(nextc);
                    int coordxHeliNext = coordsHeliNext.getFirst();
                    int coordyHeliNext = coordsHeliNext.getSecond();
                    Double power1= pow((coordxHeli - coordxHeliNext),2);
                    Double power2= pow((coordyHeli - coordyHeliNext),2);
                    Double Euclidean = sqrt( power1+ power2);
                    dist = dist + (Euclidean / 1.667);
                    lastStatus = 1;
                }
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
        if (indexGroup1 < indexGroup2) itinerary.add(indexGroup1+1, grp2);
        else itinerary.add(indexGroup1, grp2);
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

//    public Pair<Integer, Integer> getFirstAndLastGroupOfRescue(int indexGrp) {
//        int indexInit = indexGrp;
//        int indexEnd = indexGrp;
//        for (int i = indexGrp; i > 0; i--) {
//            if (itinerary.get(i).getSecond() == 0) indexInit = i;
//            break;
//        }
//        for (int i = indexGrp; i < itinerary.size(); ++i) {
//            if (itinerary.get(i).getSecond() == 0) indexEnd = i;
//            break;
//        }
//        return new Pair<Integer, Integer>(indexInit, indexEnd);
//    }

    public int getItineraryLength() {
        return itinerary.size();
    }

    public boolean suitableForJoin(int indexGrp1, int indexGrp2, ArrayList<Group> groups) {
        if (indexGrp1 == indexGrp2) return false;

        if (getGroupStatus(indexGrp1) == 0) {
            if (indexGrp2 > 0 && getGroupStatus(indexGrp2) != 0 && getGroupId(indexGrp2-1) == indexGrp1) {
                // Groups are already joined
                return false;
            }

            if (indexGrp2 > 1 && getGroupStatus(indexGrp2) != 0 && getGroupStatus(indexGrp2-1) != 0 &&
                    getGroupId(indexGrp2-2) == indexGrp1) {
                // Part of three-group cluster already
                return false;
            }

            if (getGroupStatus(indexGrp2) != 0 && indexGrp2 > 0 && getGroupId(indexGrp2-1) == indexGrp1) {
                // Groups are already joined
                return false;
            }

            if ((indexGrp2 < itinerary.size()-1 && getGroupStatus(indexGrp2 + 1) != 0) &&
                    (indexGrp2 > 0 && getGroupStatus(indexGrp2 - 1) != 0)) {
                // Three groups in the cluster already, cannot add this one there
                return false;
            }

            int clusterCapacity = groups.get(getGroupId(indexGrp1)).getNumPeople();
            for (int i = indexGrp1+1; i < itinerary.size() && getGroupStatus(i) != 0; ++i) {
                clusterCapacity += groups.get(getGroupId(i)).getNumPeople();
            }
            if (clusterCapacity + groups.get(getGroupId(indexGrp2)).getNumPeople() > cargo) return false;

            return true;
        }

        return false;
    }
}
