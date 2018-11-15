/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.dinamicas;

/**
 *
 * @author mamorales
 */
public class NodoInt {

    //Atributos
    private int tipoElemento;
    private NodoInt enlace;

    //Constructores
    public NodoInt(int tipEl) {
        this.tipoElemento = tipEl;
        this.enlace = null;

    }

    public NodoInt(int tipEl, NodoInt link) {
        this.tipoElemento = tipEl;
        this.enlace = link;
    }

    public void setElem(int tipEl) {
        this.tipoElemento = tipEl;
    }

    public int getElem() {
        return this.tipoElemento;
    }

    public void setEnlace(NodoInt link) {
        this.enlace = link;
    }

    public NodoInt getEnlace() {
        return this.enlace;
    }
}
