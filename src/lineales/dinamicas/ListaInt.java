/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.dinamicas;

/**
 *
 * @author mamorales
 */
public class ListaInt {

    private NodoInt cabecera;
    private int longitud;

    public ListaInt() {
        this.cabecera = null;
        this.longitud = 0;
    }

    public boolean insertar(int elem, int pos) {
        boolean exito = true;

        if (pos < 1 || pos > this.longitud + 1) {
            exito = false;
        } else {
            if (pos == 1) {

                this.cabecera = new NodoInt(elem, this.cabecera);

            } else {

                NodoInt aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                NodoInt nuevo = new NodoInt(elem, aux.getEnlace());
                aux.setEnlace(nuevo);

            }
            this.longitud++;
        }
        return exito;
    }

    public boolean eliminar(int pos) {
        boolean exito = true;
        if (pos < 1 || pos > this.longitud + 1) {
            exito = false;
        } else if (this.cabecera == null) {
            exito = false;
        } else {

            NodoInt aux = this.cabecera;
            NodoInt aux2 = this.cabecera.getEnlace();
            int i = 1;
            while (i < pos - 1) {
                aux = aux.getEnlace();
                i++;
            }
            aux.setEnlace(aux.getEnlace().getEnlace());
            this.longitud--;
        }
        return exito;
    }

    public boolean esVacia() {
        return this.cabecera == null;
    }

    public int longitud() {
        return this.longitud;
    }

    @Override
    public String toString() {
        String s = "";

        if (this.cabecera != null) {


            NodoInt aux = this.cabecera;
            while (aux != null) {
                s = s + " " + aux.getElem();
                aux = aux.getEnlace();
            }

        } else {
            s = "LISTA VACIA!!!!";
        }
        return s;
    }
}
