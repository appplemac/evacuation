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
public class DriverGroupOfHeadquarters {
    private static void menu() {
        System.out.println("Driver clase DriverGroupHeadquarter"
                + "\n 0.Salir"
                + "\n 1.State(int): void"
                + "\n 2.MakeAssignment(int,int): void"
                + "\n 3.addHQ(): void"
                + "\n 4.getHQs(): ArrayList<>");
    }
    
    public static void main(String [] args) {        
        menu();
        Scanner sc = new Scanner(System.in);
        String linea;
        String[] lsplited;
        State hq = null;
        while (sc.hasNext()) {
            try {
                linea = sc.nextLine();
                lsplited = linea.split(" ");
                switch(Integer.parseInt(lsplited[0])) {
                    case 1: {
                        hq = new State(Integer.parseInt(lsplited[1]));
                        System.out.println("HelicopterFleet created");
                        break;
                    }
                    case 2:{
                       hq.MakeAssignment(Integer.parseInt(lsplited[1]), Integer.parseInt(lsplited[2]));
                        System.out.println("Assigned");
                        break;
                    }
                    case 3: {
                        Headquarter headq = new Headquarter(Integer.parseInt(lsplited[1]),Integer.parseInt(lsplited[2]),Integer.parseInt(lsplited[3]));
                        hq.addHQ(headq);
                        System.out.println("Added");
                        break;
                    }
                    case 4: {
                        ArrayList<Headquarter> res = hq.getHQs();
                        for (Headquarter headq : res){
                            System.out.println(Integer.toString(headq.getident()));
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
