/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import lineales.dinamicas.ListaInt;

/**
 *
 * @author mamorales
 */
public class testListaInt {

    public static void main(String[] args) {

        ListaInt lista = new ListaInt();

        System.out.println("*********INSERTAR!!!************");
        System.out.println(lista.toString());
        System.out.println(lista.eliminar(5));
        lista.insertar(1, 1);
        lista.insertar(2, 2);
        lista.insertar(5, 5);
        lista.insertar(5, -2);
        lista.insertar(3, 3);
        System.out.println(lista.toString());
        


    }
}
