/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jerarquicas;

/**
 *
 * @author Matthew
 */
public class NodoGenerico {

    //Atributos
    private Comparable elem;
    private NodoGenerico hijoIzquierdo;
    private NodoGenerico hermanoDerecho;

    //Constructor
    public NodoGenerico(Comparable elemento) {
        this.elem = elemento;
        this.hijoIzquierdo = null;
        this.hermanoDerecho = null;
    }

    public NodoGenerico() {
        this.elem = null;
        this.hijoIzquierdo = null;
        this.hermanoDerecho = null;
    }

    //Observadores
    public Comparable getElem() {
        return this.elem;
    }

    public NodoGenerico getHermanoDerecho() {
        return hermanoDerecho;
    }

    public NodoGenerico getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    //Modificadores
    public void setElem(Comparable elem) {
        this.elem = elem;
    }

    public void setHermanoDerecho(NodoGenerico hermanoDerecho) {
        this.hermanoDerecho = hermanoDerecho;
    }

    public void setHijoIzquierdo(NodoGenerico hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

}
