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
public class DriverHeadquarter {
    private static void menu() {
        System.out.println("Driver clase Group"
                + "\n 0.Salir"
                + "\n 1.Headquarter(int,int,int): void"
                + "\n 2.getident(): int"
                + "\n 3.getHelicopters(): int"
                + "\n 4.getcoordHQ(): String"
                + "\n 5.addHeli(int): void");
    }
    
        public static void main(String [] args) {        
        menu();
        Scanner sc = new Scanner(System.in);
        String linea;
        String[] lsplited;
        Headquarter hq = null;
        while (sc.hasNext()) {
            try {
                linea = sc.nextLine();
                lsplited = linea.split(" ");
                switch(Integer.parseInt(lsplited[0])) {
                    case 1: {;
                        hq = new Headquarter(Integer.parseInt(lsplited[1]),Integer.parseInt(lsplited[2]),Integer.parseInt(lsplited[3]));
                        System.out.println("HQ created");
                        break;
                    }
                    case 2:{
                        int res = hq.getident();
                        System.out.println(Integer.toString(res));
                        break;
                    }
                    case 3: {
                        ArrayList<Integer> res = hq.getHelicopters();
                        for (Integer hel : res){
                            System.out.println(Integer.toString(hel));
                        }
                        break;
                    }
                    case 4: {
                        String res = hq.getcoordHQ();
                        System.out.println(res);
                        break;
                    }
                    case 5: {
                        hq.addHeli(Integer.parseInt(lsplited[1]));
                        System.out.println("added helicopter identifier");
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
