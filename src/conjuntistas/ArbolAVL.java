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
public class ArbolAVL {

    private NodoAVL raiz;

    //Constructores
    public ArbolAVL() {
        this.raiz = null;
    }

    public boolean insertar(int elem) {
        boolean exito = true;

        if (this.raiz == null) {
            this.raiz = new NodoAVL(elem);
        } else {
            exito = insertar(elem, this.raiz);
        }

        return exito;
    }

    private boolean insertar(int elem, NodoAVL nodo) {
        //precondicion: nodo no es nulo
        boolean exito = true;

        if (elem < nodo.getElem()) {
            //elem es menor que nodo.getElem()
            //Si tiene hijo izquierdo baja a la izquierda, sino agrega elemento
            if (nodo.getIzquierdo() == null) {
                nodo.setIzquierdo(new NodoAVL(elem));
            } else {
                exito = insertar(elem, nodo.getIzquierdo());
                nodo = balancear(nodo);
            }

        } else if (elem > nodo.getElem()) {
            //elem es mayor que nodo.getElem()
            //Si tiene hijo derecho baja a la derecha, sino agrega elemento
            if (nodo.getDerecho() == null) {
                nodo.setDerecho(new NodoAVL(elem));
            } else {
                exito = insertar(elem, nodo.getDerecho());
            }

        } else {
            //Error Elemento repetido
            exito = false;
        }

        return exito;
    }

    private NodoAVL balancear(NodoAVL nodo) {
        NodoAVL h = nodo;
        NodoAVL temp;
        int balancePadre = balance(nodo);
        int balanceHijo;

        if (balancePadre == -2) {
            //Arbol caido hacia la derecha, se procede a balancearlo hacia la izquierda
            h = nodo.getDerecho();
            temp = h.getIzquierdo();
            balanceHijo = balance(h);

            if (balanceHijo == -1) {
                //Rotacion simple a Izquierda
                h.setIzquierdo(nodo);
                nodo.setDerecho(temp);

            } else {
                //Rotacion  doble Derecha-Izquierda
                h = nodo.getDerecho().getIzquierdo();
                temp = h.getDerecho();
                h.setDerecho(nodo.getDerecho());
                h.getDerecho().setIzquierdo(temp);
                nodo.setDerecho(h);

                temp = nodo.getIzquierdo().getDerecho();
                h.setIzquierdo(nodo);
                nodo.setDerecho(temp);
            }
        }
        if (balancePadre == 2) {
            //Arbol caido a la izquierda, se procede a balancearlo a la derecha
            h = nodo.getIzquierdo();
            temp = h.getDerecho();
            balanceHijo = balance(h);

            if (balanceHijo == 1) {
                //Rotacion simple a izquierda
                h.setDerecho(nodo);
                nodo.setIzquierdo(temp);

            } else {
                //Rotacion doble Izquierda-Derecha
                h = nodo.getIzquierdo().getDerecho();
                temp = h.getIzquierdo();

                h.setIzquierdo(nodo.getIzquierdo());
                h.getIzquierdo().setDerecho(temp);
                nodo.setIzquierdo(h);

                temp = nodo.getIzquierdo().getDerecho();
                h.setDerecho(nodo);
                nodo.setIzquierdo(temp);

            }
        }

        return h;
    }

    private int altura(NodoAVL nodo) {
        int h = -1;
        int h1;
        if (nodo != null) {
            h = 1 + altura(nodo.getIzquierdo());
            h1 = 1 + altura(nodo.getDerecho());

            if (h1 > h) {
                h = h1;
            }
        }
        return h;
    }
    
    private int balance(NodoAVL nodo){
        return altura(nodo.getIzquierdo()) - altura(nodo.getDerecho());
        
    }
    
    /*
    private int balance(NodoAVL nodo) {
        int respuesta;

        respuesta = nodo.getIzquierdo().getAltura() - nodo.getDerecho().getAltura();
        
        return respuesta;
    }
     */
}
