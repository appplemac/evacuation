/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company;

/**
 *
 * @author Usuario
 */
public class Helicopter {
    private int ident;
    private int ActualCapacity;
    private int coordx;
    private int coordy;
    private boolean full;
    private int cargo = 15;
    
    public Helicopter(int id, int x, int y){
        ident = id;
        ActualCapacity = 0;
        full = false;
        coordx = x;
        coordy = y;
    }
    
    public int getIdent(){
        return ident;
    }
    
    public int getActualCapacity(){
        return ActualCapacity;
    }
    
    public boolean Full(){
        return full;
    }
    
    public boolean itFills(int num){
        return num + ActualCapacity > cargo;
    }
    
    public void addPeople(int numpeople) throws Exception{
        if (ActualCapacity+numpeople > cargo){
            throw new Exception("El numero de personas supera la capacidad.");
        }
        else {
            ActualCapacity += numpeople;
            if (ActualCapacity == 15) full = true;
        }
    }
    
    public int getCargo(){
        return cargo;
    }
          
    public String getcoordinates(){
        String pos;
        pos = Integer.toString(coordx)+' '+Integer.toString(coordy);
        return pos;
    }
}
