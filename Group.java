/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package com.company;
import java.io.IOException;
/**
 *
 * @author Usuario
 */
public class Group {
    private int numpeople;
    private int priority;
    private int Gcoordx;
    private int Gcoordy;
    /*int TimeOfGroup;*/
    
    public Group(int num, int p, int x, int y){
        numpeople = num;
        priority = p;
        Gcoordx = x;
        Gcoordy = y;
    }
    
    public int getNumPeople(){
        return numpeople;
    } 
    
    public int getPriority(){
        return priority;
    }
    
    public String getcoordG(){
        String pos;
        pos = Integer.toString(Gcoordx)+' '+Integer.toString(Gcoordy);
        return pos;
    }
}
