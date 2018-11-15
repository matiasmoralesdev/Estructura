/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.estaticas;

/**
 *
 * @author mamorales
 */
public class ColaInt {

    private static int TAM = 5;
    private int[] cola = new int[TAM];
    int frente;
    int fin;

    public ColaInt() {
        this.frente = 0;
        this.fin = 0;
    }

    public boolean poner(int newElem) {
        boolean exito;
        if ( (this.fin + 1) % TAM == this.frente) {
            //cola llena
            exito = false;

        } else {
            this.cola[this.fin] = newElem;
            this.fin = (this.fin +1 ) % TAM;
            exito = true;
        }
        return exito;
    }

    public boolean sacar() {
        boolean exito;
        if (esVacia()) {
            //cola vacia
            exito = false;

        } else {
            this.frente = (this.frente + 1) % TAM;
            exito = true;
        }
        return exito;
    }

    public int obtenerFrente() {
        int resp = 9999999;
        if (!esVacia()) {
            resp = this.cola[this.frente];
        }
        return resp;
    }

    public boolean esVacia() {
        return this.frente == this.fin;
    }

    public void vaciar() {
        this.fin = this.frente;
    }

    /*
    public ColaInt clonar() {
    ColaInt clon = new ColaInt();
    if (!esVacia()) {
    for (int i = 0; i < TAM; i++) {
    
    clon.cola[i] = this.cola[i];
    
    }
    clon.fin = this.fin;
    clon.frente = this.frente;
    
    }
    return clon;
    }
    
    
     */
    public ColaInt clonar() {
        ColaInt clon = new ColaInt();
        if (!esVacia()) {
            clon.fin = this.fin;
            clon.frente = this.frente;
            int i = this.frente;
            while (i != this.fin) {
                clon.cola[i] = this.cola[i];
                i = (i + 1) % TAM;
            }
        }
        return clon;
    }

    @Override
    public String toString() {
        String s = "FIN= " + this.fin + "\nFRENTE= " + this.frente + "\nCOLA= \n ";
        if (!esVacia()) {
            int i = this.frente;
            while (i != this.fin) {
                
                s = s + this.cola[i];
                i = (i + 1) % TAM;
            }
        } else {
            s = s + "****COLA VACIA*****";
        }
        return s;
    }
}
