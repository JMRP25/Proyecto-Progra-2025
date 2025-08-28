/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectoprogra2025;

import java.util.Scanner;

/**
 *
 * @author jose2
 */
public class ProyectoProgra2025 {

    public static void main(String[] args) {
        
        System.out.println("Hello World!");
        
         Scanner S = new Scanner(System.in);
        int opcion=0; 
        try {
        
        
        do {
            System.out.println("**Ingrese una opcion de Repaso de las que se muestra a continuación:");
            System.out.println("1: Operaciones con números");
            System.out.println("2: If");
            System.out.println("3: Do");
            System.out.println("4: While");
            System.out.println("5: Salir");
            opcion= S.nextInt();

            switch (opcion) {

                case 1:
                    System.out.println("**Ingreso la opcion 1");
                    System.out.println("Ingrese un número para sumar:");
                    int numero = S.nextInt();
                    System.out.println("Ingrese un número para sumar:");
                    int numero2 = S.nextInt();
                    int total = numero + numero2;
                    System.out.println("La suma de los números es:" + total);
                break;
                case 2:
                    System.out.println("**Ingreso la opcion 2" );
                break;
                case 3:
                    System.out.println("**Ingreso la opcion 3" );
                break;
                case 4:
                    System.out.println("**Ingreso la opcion 4" );
                break;
                case 5:
                    System.out.println("**Ingreso la opcion 5");
                break;
                default:
                    System.out.print("**Ingreso una opcion invalida");
                    break;
            }
        } while (opcion >= 5);
            
        } catch (Exception e) {
            System.out.println("");
    }
}}
