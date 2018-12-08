package conjuntistas;

import lineales.dinamicas.Lista;
import utiles.Booleano;

public class ArbolAVL {

    private NodoAVL raiz;
    private int size;

    //Constructores
    public ArbolAVL() {
        this.raiz = null;
        this.size = 0;
    }

    public boolean insertar(Comparable elem) {
        Booleano valor = new Booleano(true);
        if (this.raiz == null) {
            this.raiz = new NodoAVL(elem);
        } else {
            this.raiz = insertar(elem, this.raiz, valor);
        }

        if (valor.getValor()) {
            this.size++;
        }
        return valor.getValor();
    }

    private NodoAVL insertar(Comparable elemento, NodoAVL nodo, Booleano v) {
        //precondicion: nodo no es nulo
        //elem es menor que nodo.getElem()
        if (elemento.compareTo(nodo.getElem()) < 0) {
            //Si tiene hijo izquierdo baja a la izquierda, sino agrega elemento
            if (nodo.getIzquierdo() == null) {
                nodo.setIzquierdo(new NodoAVL(elemento));
            } else {
                nodo.setIzquierdo(insertar(elemento, nodo.getIzquierdo(), v));
            }
            nodo.recalcularAltura();
            nodo = balancear(nodo);
            //elem es mayor que nodo.getElem()
        } else if (elemento.compareTo(nodo.getElem()) > 0) {
            //Si tiene hijo derecho baja a la derecha, sino agrega elemento
            if (nodo.getDerecho() == null) {
                nodo.setDerecho(new NodoAVL(elemento));
            } else {
                nodo.setDerecho(insertar(elemento, nodo.getDerecho(), v));
            }
            nodo.recalcularAltura();
            nodo = balancear(nodo);
        } else {
            //Error Elemento repetido
            v.setValor(false);
        }

        return nodo;
    }

    public boolean eliminar(Comparable elem) {
        Booleano exito = new Booleano(false);
        if (this.raiz != null) {
            this.raiz = eliminar(elem, this.raiz, exito);
        }
        if (exito.getValor()) {
            this.size--;
        }
        return exito.getValor();
    }

    private NodoAVL eliminar(Comparable elem, NodoAVL nodo, Booleano v) {

        if (elem.compareTo(nodo.getElem()) < 0) {
            if (nodo.getIzquierdo() != null) {
                nodo.setIzquierdo(eliminar(elem, nodo.getIzquierdo(), v));
                nodo.recalcularAltura();
                nodo = balancear(nodo);
            }
        } else if (elem.compareTo(nodo.getElem()) > 0) {
            if (nodo.getDerecho() != null) {
                nodo.setDerecho(eliminar(elem, nodo.getDerecho(), v));
                //nodo.setAltura(Math.max(altura(nodo.getIzquierdo()), altura(nodo.getDerecho())) + 1);
                nodo.recalcularAltura();
                nodo = balancear(nodo);
            }
        } else {
            if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                nodo = null;
            } else if (nodo.getIzquierdo() == null && nodo.getDerecho() != null) {
                nodo = nodo.getDerecho();
            } else if (nodo.getIzquierdo() != null && nodo.getIzquierdo() == null) {
                nodo = nodo.getIzquierdo();
            } else {
                NodoAVL aux = recuperarElMinimo(nodo.getDerecho());
                nodo.setElem(aux.getElem());
                nodo.setDerecho(eliminar(aux.getElem(), nodo.getDerecho(), v));
            }
            //nodo = balancear(nodo);
            v.setValor(true);
        }

        return nodo;
    }

    public Comparable minimoElem() {
        return recuperarElMinimo(this.raiz).getElem();
    }

    private NodoAVL recuperarElMinimo(NodoAVL nodo) {
        NodoAVL retorno;
        if (nodo.getIzquierdo() != null) {
            retorno = recuperarElMinimo(nodo.getIzquierdo());
        } else {
            retorno = nodo;
        }
        return retorno;
    }

    public Comparable maximoElem() {
        return recuperarElMaximo(this.raiz).getElem();
    }

    private NodoAVL recuperarElMaximo(NodoAVL nodo) {
        NodoAVL retorno;
        if (nodo.getDerecho() != null) {
            retorno = recuperarElMaximo(nodo.getDerecho());
        } else {
            retorno = nodo;
        }
        return retorno;
    }

    public Comparable recuperar(Comparable elem) {
        Comparable retorno = null;
        retorno = recuperar(this.raiz, elem);
        return retorno;
    }

    private Comparable recuperar(NodoAVL nodo, Comparable elem) {
        Comparable retorno = null;
        if (nodo != null) {
            if (nodo.getElem().equals(elem)) {
                retorno = nodo.getElem();
            } else if (nodo.getElem().compareTo(elem) < 0) {
                retorno = recuperar(nodo.getDerecho(), elem);
            } else {
                retorno = recuperar(nodo.getIzquierdo(), elem);
            }

        }
        return retorno;
    }

    public int size() {
        return this.size;
    }

    private NodoAVL balancear(NodoAVL nodo) {
        NodoAVL h = nodo;
        int balancePadre = nodo.balance();
        int balanceHijo;
        if (balancePadre == -2) {
            //Arbol caido hacia la derecha
            //Se procede a balancearlo hacia la izquierda
            balanceHijo = nodo.getDerecho().balance();
            if (balanceHijo == -1) {
                h = rotSimpleIzq(nodo);
            } else {
                h = rotDobleDerIzq(nodo);
            }
        }
        if (balancePadre == 2) {
            //Arbol caido a la izquierda
            //Se procede a balancearlo a la derecha
            balanceHijo = nodo.getIzquierdo().balance();

            if (balanceHijo == 1) {
                //Rotacion simple a Derecha
                h = rotSimpleDer(nodo);

            } else {
                h = rotDobleIzqDer(nodo);
            }
        }

        return h;
    }

    private NodoAVL rotSimpleIzq(NodoAVL nodo) {
        //Rotacion simple a izquierda
        NodoAVL h = nodo.getDerecho();
        NodoAVL temp = h.getIzquierdo();

        h.setIzquierdo(nodo);
        nodo.setDerecho(temp);

        nodo.recalcularAltura();
        h.recalcularAltura();
        return h;
    }

    private NodoAVL rotSimpleDer(NodoAVL nodo) {
        //Rotacion simple a Derecha
        NodoAVL h = nodo.getIzquierdo();
        NodoAVL temp = h.getDerecho();
        
        h.setDerecho(nodo);
        nodo.setIzquierdo(temp);
        
        nodo.recalcularAltura();
        h.recalcularAltura();
        return h;
    }

    private NodoAVL rotDobleDerIzq(NodoAVL nodo) {
        //Rotacion  doble Derecha-Izquierda

        //Rotacion simple a derecha con pivote en el hijo derecho del nodo
        NodoAVL r1 = rotSimpleDer(nodo.getDerecho());
        nodo.setDerecho(r1);
        //Rotacion simple a izquierda con pivote en el nodo padre
        nodo = rotSimpleIzq(nodo);
        return nodo;
    }

    private NodoAVL rotDobleIzqDer(NodoAVL nodo) {
        //Rotacion doble Izquierda-Derecha

        //Rotacion simple a izquierda con el hijo izquierdo
        NodoAVL r1 = rotSimpleIzq(nodo.getIzquierdo());
        nodo.setIzquierdo(r1);
        //Rotacion simple a derecha con el nodo padre
        nodo = rotSimpleDer(nodo);
        return nodo;
    }

    public Lista listar() {
        Lista lista = new Lista();
        listar(this.raiz, lista);
        return lista;
    }

    private void listar(NodoAVL nodo, Lista lista) {
        if (nodo != null) {
            listar(nodo.getIzquierdo(), lista);
            lista.insertar(nodo.getElem());
            listar(nodo.getDerecho(), lista);
        }
    }

    public Lista listarEnRango(Comparable min, Comparable max) {
        Lista lista = new Lista();
        listarEnRango(this.raiz, lista, min, max);
        return lista;
    }

    private void listarEnRango(NodoAVL nodo, Lista lista, Comparable min, Comparable max) {
        if (nodo != null) {
            Comparable actual = nodo.getElem();
            if (actual.compareTo(min) > 0) {
                listarEnRango(nodo.getIzquierdo(), lista, min, max);
            }
            if (actual.compareTo(min) >= 0 && actual.compareTo(max) <= 0) {
                lista.insertar(nodo.getElem());
            }

            if (actual.compareTo(max) < 0) {
                listarEnRango(nodo.getDerecho(), lista, min, max);
            }
        }
    }

    public Comparable minimo() {
        return minimo(this.raiz);
    }

    private Comparable minimo(NodoAVL nodo) {
        Comparable minimo;

        if (nodo != null) {

            if (nodo.getIzquierdo() != null) {
                minimo = minimo(nodo.getIzquierdo());
            } else {
                minimo = nodo.getElem();
            }

        } else {
            minimo = "Error";
        }
        return minimo;
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
