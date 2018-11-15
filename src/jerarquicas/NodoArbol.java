/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jerarquicas;

/**
 *
 * @author mamorales
 */
public class NodoArbol {

    private int elemento;
    private NodoArbol izquierdo;
    private NodoArbol derecho;

    //Constructor
    public NodoArbol(int elem) {
        this.elemento = elem;
        this.izquierdo = null;
        this.derecho = null;
    }

    public NodoArbol() {
        this.elemento = 0;
        this.izquierdo = null;
        this.derecho = null;
    }

    //Observadores
    public int getElem() {
        return this.elemento;
    }

    public NodoArbol getDerecho() {
        return this.derecho;
    }

    public NodoArbol getIzquierdo() {
        return this.izquierdo;
    }

    //Modificadores
    public void setDerecho(NodoArbol n) {
        this.derecho = n;
    }

    public void setIzquierdo(NodoArbol n) {
        this.izquierdo = n;
    }

    public void setElem(int elem) {
        this.elemento = elem;
    }

    //Debug
    @Override
    public String toString() {

        String cadena = "NODO: " + this.elemento + " ALTURA: " + "\nHIJO IZQUIERDO: ";

        if (this.izquierdo != null) {
            cadena = cadena + this.izquierdo.getElem();
        } else {
            cadena = cadena + "NO TIENE";
        }
        cadena = cadena + "\nHIJO DERECHO: ";

        if (this.derecho != null) {
            cadena = cadena + this.derecho.getElem();
        } else {
            cadena = cadena + "NO TIENE";
        }

        return cadena;
    }
    
}
