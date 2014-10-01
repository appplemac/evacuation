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
public class ListOfGroups {
    int numGroups;
    ArrayList<Group> ListGroups;
    
    public ListOfGroups(){
        ListGroups = new ArrayList<Group>();
    }
    
    public ArrayList<Group> getList(){
        return ListGroups;
    }
    
    public void addGroup(int n, int p,int x, int y){
        Group g = new Group(n,p,x,y);
        ListGroups.add(g);
    }
}
