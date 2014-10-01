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
public class DriverHelicopterFleet {
    private static void menu() {
        System.out.println("Driver clase HelicopterFleet"
                + "\n 0.Salir"
                + "\n 1.HelicopterFleet(int): void"
                + "\n 2.getHelicopters(): ArrayList<Helicopter>"
                + "\n 3.getVel(): int"
                + "\n 4.addHeli(): void"
                + "\n 5.getActualCapacityHeli(int): int"
                + "\n 6.getCoordsHeli(): String"
                + "\n 7.isHeliFull(): boolean");
    }
    
    public static void main(String [] args) {        
        menu();
        Scanner sc = new Scanner(System.in);
        String linea;
        String[] lsplited;
        HelicopterFleet h = null;
        while (sc.hasNext()) {
            try {
                linea = sc.nextLine();
                lsplited = linea.split(" ");
                switch(Integer.parseInt(lsplited[0])) {
                    case 1: {;
                        h = new HelicopterFleet(Integer.parseInt(lsplited[1]));
                        System.out.println("HelicopterFleet created");
                        break;
                    }
                    case 2:{
                        ArrayList<Helicopter> res = h.getHelicopters();
                        for (Helicopter hel : res){
                            System.out.println(Integer.toString(hel.getIdent()));
                        }
                        break;
                    }
                    case 3: {
                        int res = h.getVel();
                        System.out.println(Integer.toString(res));
                        break;
                    }
                    case 4: {
                        Helicopter hnew = new Helicopter(Integer.parseInt(lsplited[1]),Integer.parseInt(lsplited[2]),Integer.parseInt(lsplited[3]));
                        h.addHeli(hnew);
                        System.out.println("Helicopter added");
                        break;
                    }
                    case 5:{
                        int res = h.getActualCapacityHeli(Integer.parseInt(lsplited[1]));
                        System.out.println(Integer.toString(res));
                        break;
                    }
                    case 6:{
                        String s = h.getCoordsHeli(Integer.parseInt(lsplited[1]));
                        System.out.println(s);
                        break;
                    }
                    case 7: {
                        
                        if (h.isHeliFull(Integer.parseInt(lsplited[1]))){
                            System.out.println("Full");
                        }
                        else {
                            System.out.println("Not full yet");
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
