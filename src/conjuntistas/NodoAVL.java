/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuntistas;

/**
 *
 * @author Matthew
 */
public class NodoAVL {

    private Comparable elemento;
    private NodoAVL izquierdo;
    private NodoAVL derecho;
    private int altura;

    //Constructores
    public NodoAVL() {
        this.elemento = null;
        this.derecho = null;
        this.izquierdo = null;
        this.altura = 0;
    }

    public NodoAVL(Comparable elem) {
        this.elemento = elem;
        this.derecho = null;
        this.izquierdo = null;
        this.altura = 0;
    }

    //Observadores
    public Comparable getElem() {
        return this.elemento;
    }

    public void recalcularAltura() {
        int left, right;
        if (this.izquierdo != null) {
            left = this.izquierdo.altura;
        } else {
            left = -1;
        }
        if (this.derecho != null) {
            right = this.derecho.altura;
        } else {
            right = -1;
        }
        this.altura = Math.max(left, right) + 1;
    }

    public int balance() {
        int left, right, balance;
        if (this.izquierdo != null) {
            left = this.izquierdo.altura;
        } else {
            left = -1;
        }

        if (this.derecho != null) {
            right = this.derecho.altura;
        } else {
            right = -1;
        }
        balance = left - right;
        return balance;
    }

    public int getAltura() {
        return this.altura;
    }

    public NodoAVL getIzquierdo() {
        return this.izquierdo;
    }

    public NodoAVL getDerecho() {
        return this.derecho;
    }

    // Modificadores
    public void setDerecho(NodoAVL nodo) {
        this.derecho = nodo;
    }

    public void setIzquierdo(NodoAVL nodo) {
        this.izquierdo = nodo;

    }

    public void setElem(Comparable elem) {
        this.elemento = elem;
    }

    public void setAltura(int alt) {
        this.altura = alt;
    }

    public void aumentarAltura() {
        this.altura++;
    }

    public void sumarAltura(int n) {
        this.altura += n;
    }

    //Debug
    @Override
    public String toString() {

        String cadena = "NODO: " + this.elemento + " ALTURA: " + this.altura + "\nHIJO IZQUIERDO: ";

        if (this.izquierdo != null) {
            cadena = cadena + this.izquierdo.getElem();
        } else {
            cadena = cadena + "[NO TIENE]";
        }
        cadena = cadena + "\nHIJO DERECHO: ";

        if (this.derecho != null) {
            cadena = cadena + this.derecho.getElem();
        } else {
            cadena = cadena + "[NO TIENE]";
        }

        cadena += "\n";

        return cadena;
    }

}
