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
public class HelicopterFleet {
    public ArrayList<Helicopter> Helis;
    public int vel = 100;

    
    public HelicopterFleet (int n){
        Helis = new ArrayList<Helicopter>(n);
    }
    
    public ArrayList<Helicopter> getHelicopters(){
        return Helis;
    }
    
    public int getVel(){
        return vel;
    }
    
    public void addHeli(Helicopter h){
        Helis.add(h);
    }
    
    public String getCoordsHeli(int identH){
        
        for (Helicopter h : Helis){
            if (h.getIdent() == identH){
                return h.getcoordinates();
            }
        }
        return "null";
    }
    
    public int getActualCapacityHeli(int identH){
        for (Helicopter h : Helis){
            if (h.getIdent() == identH){
                return h.getActualCapacity();
            }
        }
        return -1;
    }
    
    boolean isHeliFull(int identH){
                for (Helicopter h : Helis){
            if (h.getIdent() == identH){
                return h.Full();
            }
        }
        return false;
    }
    
}

