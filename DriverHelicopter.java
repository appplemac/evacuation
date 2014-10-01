/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company;

import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class DriverHelicopter {
    private static void menu() {
        System.out.println("Driver clase Helicopter"
                + "\n 0.Salir"
                + "\n 1.Helicopter(int,int,int): void"
                + "\n 2.getIdent(): int"
                + "\n 3.getActualCapacity(): int"
                + "\n 4.getCargo(): int"
                + "\n 5.itFills(int): boolean"
                + "\n 6.addPeople(int): void"
                + "\n 7.Full(): boolean"
                + "\n 8.getcoordinates(): String");
    }
    
    public static void main(String [] args) {        
        menu();
        Scanner sc = new Scanner(System.in);
        String linea;
        String[] lsplited;
        Helicopter h = null;
        while (sc.hasNext()) {
            try {
                linea = sc.nextLine();
                lsplited = linea.split(" ");
                switch(Integer.parseInt(lsplited[0])) {
                    case 1: {;
                        h = new Helicopter(Integer.parseInt(lsplited[1]),Integer.parseInt(lsplited[2]),Integer.parseInt(lsplited[3]));
                        System.out.println("Helicopter created");
                        break;
                    }
                    case 2:{
                        int res = h.getIdent();
                        System.out.println(Integer.toString(res));
                        break;
                    }
                    case 3: {
                        int res = h.getActualCapacity();
                        System.out.println(Integer.toString(res));
                        break;
                    }
                    case 4: {
                        int res = h.getCargo();
                        System.out.println(Integer.toString(res));
                        break;
                    }
                    case 5:{
                        if (h.itFills(Integer.parseInt(lsplited[1]))){
                            System.out.println("Cargo excess");
                        }
                        else {
                            System.out.println("It can be filled");
                        }
                        break;
                    }
                    case 6:{
                        h.addPeople(Integer.parseInt(lsplited[1]));
                        System.out.println("People added");
                        break;
                    }
                    case 7: {
                        
                        if (h.Full()){
                            System.out.println("Full");
                        }
                        else {
                            System.out.println("Not full yet");
                        }
                        break;
                    }
                    case 8: {
                        String res = h.getcoordinates();
                        System.out.println(res);
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
