package conjuntistas;

import utiles.ErrorTDA;

public class ArbolAVL {

    private NodoAVL raiz;

    //Constructores
    public ArbolAVL() {
        this.raiz = null;
    }

    public boolean insertar(Comparable elem) {
        boolean exito = true;
        if (this.raiz == null) {
            this.raiz = new NodoAVL(elem);
        } else {
            this.raiz = insertar(elem, this.raiz);
        }
        return exito;
    }

    private NodoAVL insertar(Comparable elemento, NodoAVL nodo) {
        //precondicion: nodo no es nulo

        //elem es menor que nodo.getElem()
        if (elemento.compareTo(nodo.getElem()) < 0) {
            //Si tiene hijo izquierdo baja a la izquierda, sino agrega elemento
            if (nodo.getIzquierdo() == null) {
                nodo.setIzquierdo(new NodoAVL(elemento));
            } else {
                nodo.setIzquierdo(insertar(elemento, nodo.getIzquierdo()));
            }
            nodo.setAltura(Math.max(altura(nodo.getIzquierdo()), altura(nodo.getDerecho())) + 1);

            //elem es mayor que nodo.getElem()
        } else if (elemento.compareTo(nodo.getElem()) > 0) {

            //Si tiene hijo derecho baja a la derecha, sino agrega elemento
            if (nodo.getDerecho() == null) {
                nodo.setDerecho(new NodoAVL(elemento));

            } else {
                nodo.setDerecho(insertar(elemento, nodo.getDerecho()));
            }
            nodo.setAltura(Math.max(altura(nodo.getIzquierdo()), altura(nodo.getDerecho())) + 1);
        } else {
            //Error Elemento repetido
            System.out.println("Elemento Repetido");
        }
        nodo = balancear(nodo);
        return nodo;
    }

    public boolean eliminar(Comparable elem) {
        boolean exito = true;
        return exito;
    }

    private int balance(NodoAVL nodo) {
        return altura(nodo.getIzquierdo()) - altura(nodo.getDerecho());
    }

    private int altura(NodoAVL nodo) {
        int h = -1;
        if (nodo != null) {
            h = nodo.getAltura();
        }
        return h;
    }

    private NodoAVL balancear(NodoAVL nodo) {
        NodoAVL h = nodo;
        NodoAVL temp;
        int balancePadre = balance(nodo);
        int balanceHijo;

        if (balancePadre == -2) {
            //Arbol caido hacia la derecha
            //Se procede a balancearlo hacia la izquierda
            h = nodo.getDerecho();
            temp = h.getIzquierdo();
            balanceHijo = balance(h);

            if (balanceHijo == -1) {
                h = rotSimpleIzq(nodo, h, temp);

            } else {
                h = rotDobleDerIzq(nodo, h, temp);
            }
        }
        if (balancePadre == 2) {
            //Arbol caido a la izquierda
            //Se procede a balancearlo a la derecha
            h = nodo.getIzquierdo();
            temp = h.getDerecho();
            balanceHijo = balance(h);

            if (balanceHijo == 1) {
                //Rotacion simple a Derecha
                h = rotSimpleDer(nodo, h, temp);

            } else {
                h = rotDobleIzqDer(nodo, h, temp);
            }
        }

        return h;
    }

    private NodoAVL rotSimpleIzq(NodoAVL nodo, NodoAVL h, NodoAVL temp) {
        //Rotacion simple a izquierda
        h.setDerecho(nodo);
        nodo.setIzquierdo(temp);

        nodo.sumarAltura(-2);

        return h;
    }

    private NodoAVL rotSimpleDer(NodoAVL nodo, NodoAVL h, NodoAVL temp) {
        //Rotacion simple a Derecha
        h.setDerecho(nodo);
        nodo.setIzquierdo(temp);

        nodo.sumarAltura(-2);

        return h;
    }

    private NodoAVL rotDobleDerIzq(NodoAVL nodo, NodoAVL h, NodoAVL temp) {
        //Rotacion  doble Derecha-Izquierda
        h = nodo.getDerecho().getIzquierdo();
        temp = h.getDerecho();

        nodo.getDerecho().sumarAltura(-1);

        h.setDerecho(nodo.getDerecho());
        h.getDerecho().setIzquierdo(temp);
        nodo.setDerecho(h);

        h.sumarAltura(1);

        temp = null;
        h.setIzquierdo(nodo);
        nodo.setDerecho(temp);

        nodo.sumarAltura(-2);

        return h;
    }

    private NodoAVL rotDobleIzqDer(NodoAVL nodo, NodoAVL h, NodoAVL temp) {
        //Rotacion doble Izquierda-Derecha
        h = nodo.getIzquierdo().getDerecho();
        temp = null;

        nodo.getIzquierdo().sumarAltura(-1);

        h.setIzquierdo(nodo.getIzquierdo());
        h.getIzquierdo().setDerecho(temp);
        nodo.setIzquierdo(h);

        h.sumarAltura(1);

        temp = nodo.getIzquierdo().getDerecho();
        h.setDerecho(nodo);
        nodo.setIzquierdo(temp);

        //
        nodo.sumarAltura(-2);
        //
        return h;
    }

    @Override
    public String toString() {
        return toString(this.raiz);
    }

    private String toString(NodoAVL nodo) {
        String cadena = "";
        if (nodo != null) {
            cadena += nodo.toString();
            cadena += "\n";
            cadena += toString(nodo.getIzquierdo());
            cadena += toString(nodo.getDerecho());
        }
        return cadena;
    }

}
/*
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
 */
