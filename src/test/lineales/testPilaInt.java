/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test.lineales;
import lineales.estaticas.PilaInt;
/**
 *
 * @author mamorales
 */
public class testPilaInt {
    public static void main(String[] args) {
        PilaInt p1 = new PilaInt ();
        System.out.println("PILA VACIA!");
        System.out.println(p1.toString());
        p1.apilar(1);
        p1.apilar(2);
        p1.apilar(3);
        System.out.println("--------------------");
        System.out.println("3 elementos apilados");
        System.out.println(p1.toString());
        p1.apilar(4);
        p1.apilar(5);
        p1.apilar(6);
        System.out.println("MAS ELEMENTOS");
        System.out.println(p1.toString());
        PilaInt p2;
        p2 = p1.clonar();
        
        System.out.println("SE MUESTRA p2");
        System.out.println(p2.toString());
        
        p1.vaciar();
        System.out.println("VACIADA!");
        System.out.println(p1.toString());




    }
}
