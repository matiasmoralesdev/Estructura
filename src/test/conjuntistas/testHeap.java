/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.conjuntistas;

import conjuntistas.ArbolHeap;

/**
 *
 * @author mamorales
 */
public class testHeap {

    public static void main(String[] args) {
        System.out.println("");
        ArbolHeap ah = new ArbolHeap();
        //ah.insertar(1);
        ah.insertar(2);
        ah.insertar(4);
        ah.insertar(6);
        ah.insertar(3);
        ah.insertar(8);
        
        ah.eliminarCima();
        
        System.out.println(ah.toString());

    }

}
