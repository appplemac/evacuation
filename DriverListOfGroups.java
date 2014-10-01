/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class DriverListOfGroups {
        private static void menu() {
        System.out.println("Driver clase ListOfGroups"
                + "\n 0.Salir"
                + "\n 1.ListOfGroups(): void"
                + "\n 2.addGroup(): int"
                + "\n 3.getList(): int");
    }
    
    public static void main(String [] args) {        
        menu();
        Scanner sc = new Scanner(System.in);
        String linea;
        String[] lsplited;
        ListOfGroups lg = null;
        while (sc.hasNext()) {
            try {
                linea = sc.nextLine();
                lsplited = linea.split(" ");
                switch(Integer.parseInt(lsplited[0])) {
                    case 1: {;
                        lg = new ListOfGroups();
                        System.out.println("List of groups created");
                        break;
                    }
                    case 2:{
                        lg.addGroup(Integer.parseInt(lsplited[1]),Integer.parseInt(lsplited[2]),Integer.parseInt(lsplited[3]),Integer.parseInt(lsplited[4]));
                        System.out.println("Group added");
                        break;
                    }
                    case 3: {
                        ArrayList<Group> res = lg.getList();
                        for (Group i : res){
                            System.out.println(Integer.toString(i.getNumPeople())+' '+Integer.toString(i.getPriority())+' '+i.getcoordG());
                        }
                        break;
                    }
                    case 0: {
                        System.exit(0);
                    }
                    default: {
                        System.out.println("Invalid entry\n");
                        break;
                    }       
                }
            }catch(Exception e) {
                System.out.println("Error: " + e.getMessage() + "\n");
            }
        }
    }
}
