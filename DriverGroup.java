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
public class DriverGroup {
    private static void menu() {
        System.out.println("Driver clase Group"
                + "\n 0.Salir"
                + "\n 1.Group(int,int,int,int): void"
                + "\n 2.getNumPeople(): int"
                + "\n 3.getPriority(): int"
                + "\n 4.getcoordG(): String");
    }
    
        public static void main(String [] args) {        
        menu();
        Scanner sc = new Scanner(System.in);
        String linea;
        String[] lsplited;
        Group g = null;
        while (sc.hasNext()) {
            try {
                linea = sc.nextLine();
                lsplited = linea.split(" ");
                switch(Integer.parseInt(lsplited[0])) {
                    case 1: {;
                        g = new Group(Integer.parseInt(lsplited[1]),Integer.parseInt(lsplited[2]),Integer.parseInt(lsplited[3]),Integer.parseInt(lsplited[4]));
                        System.out.println("Group created");
                        break;
                    }
                    case 2:{
                        int res = g.getNumPeople();
                        System.out.println(Integer.toString(res));
                        break;
                    }
                    case 3: {
                        int res = g.getPriority();
                        System.out.println(Integer.toString(res));
                        break;
                    }
                    case 4: {
                        String res = g.getcoordG();
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
