/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.dinamicas;

/**
 *
 * @author mamorales
 */
public class PilaInt {

    private NodoInt tope;

    public PilaInt() {

        this.tope = null;
    }

    public boolean apilar(int elem) {
        //Apilar retorn true, ya que siempre apila
        NodoInt nuevo = new NodoInt(elem, this.tope);
        this.tope = nuevo;
        return true;

    }

    public boolean desapilar() {
        boolean resp = false;
        if (this.tope != null) {
            this.tope = this.tope.getEnlace();
            resp = true;
        }
        return resp;
    }

    public int obtenerTope() {

        return this.tope.getElem();
    }

    public void vaciar() {
        this.tope = null;
    }

    public boolean esVacia() {
        return this.tope == null;
    }

    public PilaInt clonar() {
        PilaInt clon = new PilaInt();
        if (this.tope != null) {
            NodoInt nuevo = new NodoInt(this.tope.getElem());
            clon.tope = nuevo;
            NodoInt aux = this.tope.getEnlace();
            NodoInt auxClon = clon.tope;

            while (aux != null) {
                nuevo = new NodoInt(aux.getElem());
                auxClon.setEnlace(nuevo);
                aux = aux.getEnlace();
                auxClon = auxClon.getEnlace();
            }
        } else {
            clon.tope = null;
        }
        return clon;
    }

    @Override
    public String toString() {
        String s = "";
        NodoInt aux = this.tope;
        while (aux != null) {
            s = s + " " + aux.getElem();
            aux = aux.getEnlace();
        }
        return s;
    }
}
