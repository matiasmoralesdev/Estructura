/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.dinamicas;

/**
 *
 * @author Matthew
 */
public class Cola {

    private Nodo frente;
    private Nodo fin;

    public Cola() {

        this.frente = null;
        this.fin = null;

    }

    public boolean poner(Comparable tipoElem) {
        boolean exito = true;
        Nodo nuevo = new Nodo(tipoElem);
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

    public Comparable obtenerFrente() {
        return this.frente.getElem();
    }

    public boolean esVacia() {
        return this.frente == null;
    }

    public void vaciar() {
        this.fin = null;
        this.frente = null;
    }

    public Cola clonar() {
        //clona la cola elemento por elemento
        Cola clon = new Cola();
        if (!esVacia()) {
            Nodo aux = this.frente;
            Nodo nuevo = new Nodo(aux.getElem());
            clon.frente = nuevo;
            Nodo aux2 = clon.frente;
            aux = aux.getEnlace();
            while (aux != null) {
                nuevo = new Nodo(aux.getElem());
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

            s = s + "FRENTE = " + this.frente.getElem().toString() + "\nFIN = "
                    + this.fin.getElem().toString() + "\nElementos:\n";

            Nodo aux = this.frente;
            while (aux != null) {
                s = s + " " + aux.getElem().toString();
                aux = aux.getEnlace();
            }
        } else {
            s = "LA COLA ESTA VACIA!!";
        }
        return s;
    }
}
