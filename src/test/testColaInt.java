/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import lineales.dinamicas.ColaInt;

/**
 *
 * @author mamorales
 */
public class testColaInt {

    public static void main(String[] args) {
        ColaInt cola = new ColaInt();

        ColaInt colaclon = new ColaInt();

        colaclon = cola.clonar();


        System.out.println("CLON VACIO:" + colaclon.toString());


        System.out.println("Cola Creada (vacia)");
        System.out.println(cola.toString());
        cola.poner(1);
        System.out.println("");
        System.out.println("PONE EL ELEMENTO '1'  ");
        System.out.println(cola.toString());
        cola.poner(2);
        cola.poner(3);
        cola.poner(4);
        cola.poner(5);
        System.out.println("COLA LLENA?");
        System.out.println(cola.toString());
        cola.sacar();
        cola.sacar();
        System.out.println("");
        System.out.println("SACO UN ELEMENTO");
        System.out.println(cola.toString());

        System.out.println("");
        System.out.println("AGREGO 5 y 6:");
        cola.poner(5);
        cola.poner(6);
        System.out.println(cola.toString());

        System.out.println("FRENTEEE!!! " +cola.obtenerFrente());


       colaclon = cola.clonar();

        System.out.println("");
        System.out.println("ORIGINAL = " +cola.toString());
        System.out.println("");
        System.out.println("");
        System.out.println("CLON = " + colaclon.toString());

        

        cola.vaciar();
        System.out.println("VACIADOO!!");
        System.out.println(cola.toString());


    }
}
