/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuntistas;

/**
 *
 * @author mamorales
 */
public class ArbolHeap {

    private final int TAM = 20;
    private final int[] heap;
    private int ultimo = 0;

    public ArbolHeap() {
        this.heap = new int[TAM];
    }

    public boolean insertar(int elem) {
        boolean correcto = false;
        if (this.ultimo != TAM - 1) {
            this.ultimo++;
            this.heap[this.ultimo] = elem;
            hacerSubir(this.ultimo);
            correcto = true;
        }

        return correcto;
    }

    private void hacerSubir(int pos) {
        int padre;
        int aux;
        boolean salir = false;
        while (!salir) {

            padre = (int) pos / 2;

            if (padre != 0) {

                if (this.heap[pos] < this.heap[padre]) {
                    aux = this.heap[padre];
                    this.heap[padre] = this.heap[pos];
                    this.heap[pos] = aux;
                    pos = padre;

                } else {
                    salir = true;
                }

            } else {
                salir = true;
            }
        }

    }

    public boolean eliminarCima() {
        boolean exito;
        if (this.ultimo == 0) {
            //Arbol vacio
            exito = false;

        } else {
            this.heap[1] = this.heap[this.ultimo];
            this.ultimo--;
            hacerBajar(1);
            exito = true;
        }
        return exito;
    }

    private void hacerBajar(int pos) {

        int hijoMenor;
        int temp = this.heap[pos];
        boolean salir = false;
        while (!salir) {
            hijoMenor = pos * 2;
            if (hijoMenor <= this.ultimo) {
                //temp tiene al menos 1 hijo
                if (hijoMenor < this.ultimo) {
                    //hijoMenor tiene hermano derecho
                    if (this.heap[hijoMenor + 1] < this.heap[hijoMenor]) {
                        hijoMenor++;
                    }
                }

                if (this.heap[hijoMenor] < temp) {
                    this.heap[pos] = this.heap[hijoMenor];
                    pos = hijoMenor;
                } else {
                    //el padre es menor que sus hijos
                    salir = true;
                }

            } else {
                //hijoMenor es Hoja
                salir = true;
            }
        }
        this.heap[pos] = temp;
    }

    public boolean esVacio() {
        return this.ultimo == 0;
    }

    public int recuperarCima() {
        return this.heap[1];
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 1; i <= this.ultimo; i++) {
            
            s = s + this.heap[i] + " ";
        }
        return s;
    }

}
