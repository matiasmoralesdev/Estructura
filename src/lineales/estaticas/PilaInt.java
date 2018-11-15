/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.estaticas;

/**
 *
 * @author mamorales
 */
public class PilaInt {

    //Atributos
    private int[] pila;
    private int tope;
    private static final int TAM = 5;

    //Constrcutores
    public PilaInt() {
        this.pila = new int[TAM];
        this.tope = -1;
    }

    public boolean apilar(int newElem) {
        boolean exito;
        if (this.tope + 1 >= TAM) {
            // Pila llena
            exito = false;
        } else {
            this.tope++;
            this.pila[this.tope] = newElem;
            exito = true;
        }
        return exito;
    }

    public boolean desapilar() {
        boolean exito;
        if (esVacia()) {
            //Pila Vacia
            exito = false;
        } else {
            this.tope--;
            exito = true;
        }
        return exito;
    }

    public int obtenerTope() {
        int resp = 9999999;
        if (this.tope != -1) {
            resp = this.pila[this.tope];
        }
        return resp;
    }

    public boolean esVacia() {
        return this.tope == -1;
    }

    public void vaciar() {
        this.tope = -1;
    }

    //  < con Alt + 60
    //  > con Alt + 62
    public PilaInt clonar() {
        PilaInt clon = new PilaInt();
        if (!esVacia()) {
            clon.tope = this.tope;
            for (int i = this.tope ; i >= 0; i--) {
                clon.pila[i] = this.pila[i];
            }
        }

        return clon;
    }


    @Override
    public String toString (){
        String cadena = "";
        if (!esVacia()){
            for (int i = this.tope ; i>=0 ; i--){
                cadena = cadena + " " + this.pila[i] ;
            }
        }else{
            System.out.println("*** PILAA VACIAA****");
        }
        return cadena;
    }
}
