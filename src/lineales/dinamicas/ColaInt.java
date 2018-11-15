/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.dinamicas;

/**
 *
 * @author mamorales
 */
public class ColaInt {

    private NodoInt frente;
    private NodoInt fin;

    public ColaInt() {

        this.frente = null;
        this.fin = null;

    }

    public boolean poner(int tipoElem) {
        boolean exito = true;
        NodoInt nuevo = new NodoInt(tipoElem);

        if (esVacia()) {
            //Cola vacia
            this.frente = nuevo;
            this.fin = nuevo;

        } else {
            //Cola con elementos
            this.fin.setEnlace(nuevo);
            this.fin = this.fin.getEnlace();

        }
        return exito;
    }

    public boolean sacar() {
        boolean exito = true;
        if (esVacia()) {
            exito = false;
        } else {
            this.frente = this.frente.getEnlace();

            if (this.frente == null) {
                this.fin = null;
            }
        }
        return exito;
    }

    public int obtenerFrente() {
        int resp = 99999999;
        if (!esVacia()) {
            resp = this.frente.getElem();
        }
        return resp;
    }

    public boolean esVacia() {
        return this.frente == null;
    }

    public void vaciar() {
        this.fin = null;
        this.frente = null;
    }

    public ColaInt clonar() {
        //clona la cola elemento por elemento
        ColaInt clon = new ColaInt();
        if (!esVacia()) {

            NodoInt aux = this.frente;
            NodoInt nuevo = new NodoInt(aux.getElem());
            clon.frente = nuevo;
            NodoInt aux2 = clon.frente;

            aux = aux.getEnlace();



            while (aux != null) {

                nuevo = new NodoInt(aux.getElem());
                aux2.setEnlace(nuevo);
                aux2 = aux2.getEnlace();
                aux = aux.getEnlace();

            }
            clon.fin = aux2;

        }
        return clon;
    }

    @Override
    public String toString() {
        String s = "";

        if (!esVacia()) {

            s = s + "FRENTE = " + this.frente.getElem() + "\nFIN = " + this.fin.getElem() + "\nElementos:\n";

            NodoInt aux = this.frente;
            while (aux != null) {
                s = s + " " + aux.getElem();
                aux = aux.getEnlace();
            }
        } else {
            s = "LA COLA ESTA VACIA!!";
        }
        return s;
    }
}
