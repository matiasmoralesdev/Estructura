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

    private int elemento;
    private NodoAVL izquierdo;
    private NodoAVL derecho;
    private int altura;

    //Constructores
    public NodoAVL() {
        this.elemento = 0;
        this.derecho = null;
        this.izquierdo = null;
        this.altura = 0;
    }

    public NodoAVL(int elem) {
        this.elemento = elem;
        this.derecho = null;
        this.izquierdo = null;
        this.altura = 0;
    }

    //Observadores
    public int getElem() {
        return this.elemento;
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

    public void setAltura(int alt) {
        this.altura = alt;
    }

    public void setElem(int elem) {
        this.elemento = elem;
    }

    //Debug
    @Override
    public String toString() {
        
        String cadena = "NODO: " + this.elemento + " ALTURA: " + this.altura + "\nHIJO IZQUIERDO: ";

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
