package jerarquicas;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;

/**
 *
 * @author Matthew
 */
public class ArbolGenerico {

    //Atributos
    private NodoGenerico raiz;

    //Constructor 
    public ArbolGenerico() {
        this.raiz = null;
    }

    /**
     * Dado un elemento elemNuevo y un elemento elemPadre, agrega elemNuevo como
     * hijo de la primer aparición de elemPadre. Para que la operación termine
     * con éxito debe existir un nodo en el árbol con elemento = elemPadre. No
     * se establece ninguna preferencia respecto a la posición del hijo respecto
     * a sus posibles hermanos. Esta operación devuelve verdadero cuando se pudo
     * agregar elemNuevo a la estructura y falso en caso contrario.
     *
     * @param nuevo el nuevo elemento que se quiere insertar en el arbol
     * @param padre el padre del nuevo elemento (si esta vacio se inserta en la
     * raiz)
     * @return
     */
    public boolean insertar(Comparable nuevo, Comparable padre) {
        boolean exito = true;
        NodoGenerico nodoHijo, nodoNuevo;

        if (this.raiz == null) {
            this.raiz = new NodoGenerico(nuevo);
        } else {
            NodoGenerico nodoPadre = buscarNodo(padre);
            if (nodoPadre != null) {
                nodoHijo = nodoPadre.getHijoIzquierdo();
                nodoNuevo = new NodoGenerico(nuevo);
                if (nodoHijo != null) {
                    nodoNuevo.setHermanoDerecho(nodoHijo);
                    nodoPadre.setHijoIzquierdo(nodoNuevo);
                } else {
                    nodoPadre.setHijoIzquierdo(nodoNuevo);
                }
            } else {
                exito = false;
            }

        }

        return exito;
    }

    /**
     * Devuelve falso si hay al menos un elemento cargado en el árbol y
     * verdadero en caso contrario.
     *
     * @param buscado el elemento que se desea buscar del arbol
     * @return
     */
    public boolean pertenece(Comparable buscado) {
        boolean exito = false;
        NodoGenerico aux = buscarNodo(buscado);
        if (aux != null) {
            exito = true;
        }
        return exito;
    }

    /**
     * Devuelve falso si hay al menos un elemento cargado en el árbol y
     * verdadero en caso contrario.
     *
     * @return
     */
    public boolean esVacio() {
        return this.raiz == null;
    }

    /**
     * Si el elemento se encuentra en el árbol, devuelve una lista con el camino
     * desde la raíz hasta dicho elemento (es decir, con los ancestros del
     * elemento). Si el elemento no está en el árbol devuelve la lista vacía
     *
     * @param elemento el elemento del cual se quieren conocer sus ancestros
     * @return
     */
    public Lista ancestros(Comparable elemento) {
        Lista lista = new Lista();
        ancestros(elemento, this.raiz, lista);
        return lista;
    }

    private boolean ancestros(Comparable buscado, NodoGenerico nodo, Lista lista) {
        boolean encontrado = false;
        while (nodo != null && !encontrado) {
            if (nodo.getElem().compareTo(buscado) == 0) {
                lista.insertarInicio(nodo.getElem());
                encontrado = true;
            } else {
                encontrado = ancestros(buscado, nodo.getHijoIzquierdo(), lista);
                if (encontrado) {
                    lista.insertarInicio(nodo.getElem());
                } else {
                    nodo = nodo.getHermanoDerecho();
                }
            }
        }
        return encontrado;
    }

    /**
     * Devuelve la altura del árbol, es decir la longitud del camino más largo
     * desde la raíz hasta una hoja (Nota: un árbol vacío tiene altura -1 y una
     * hoja tiene altura 0)
     *
     * @return
     */
    public int altura() {
        return altura(this.raiz);
    }

    private int altura(NodoGenerico nodo) {
        int altura = -1, alturaAux, alturaMax;
        while (nodo != null) {

            NodoGenerico nodoHijo = nodo.getHijoIzquierdo();
            alturaAux = altura(nodoHijo) + 1;

            if (alturaAux > altura) {
                altura = alturaAux;
            }

            nodo = nodo.getHermanoDerecho();
        }
        return altura;
    }

    /**
     * Devuelve el nivel de un elemento en el árbol. Si el elemento no existe en
     * el árbol devuelve -1.
     *
     * @param buscado el elemento que se desea buscar dentro del arbol
     * @return
     */
    public int nivel(Comparable buscado) {
        return nivel(buscado, this.raiz);
    }

    private int nivel(Comparable buscado, NodoGenerico nodo) {
        int nivel = -1;
        while (nodo != null && nivel == -1) {
            if (nodo.getElem().compareTo(buscado) == 0) {
                nivel = 0;
            } else {
                nivel = nivel(buscado, nodo.getHijoIzquierdo());
                if (nivel != -1) {
                    nivel += 1;
                }
                nodo = nodo.getHermanoDerecho();
            }
        }
        return nivel;
    }

    /**
     * Dado un elemento devuelve el valor almacenado en su nodo padre (busca la
     * primera aparición de elemento).
     *
     * @param buscado
     * @return
     */
    public Comparable padre(Comparable buscado) {
        return padre(buscado, this.raiz);
    }

    private Comparable padre(Comparable buscado, NodoGenerico nodo) {
        Comparable padre = null;
        NodoGenerico nodoHijo;
        boolean encontrado = false;
        while (nodo != null && padre == null) {

            nodoHijo = nodo.getHijoIzquierdo();
            while (nodoHijo != null && !encontrado) {
                if (nodoHijo.getElem().compareTo(buscado) == 0) {
                    encontrado = true;
                } else {
                    nodoHijo = nodoHijo.getHermanoDerecho();
                }
            }
            if (encontrado) {
                padre = nodo.getElem();
            } else {
                padre = padre(buscado, nodo.getHijoIzquierdo());
                nodo = nodo.getHermanoDerecho();
            }
        }
        return padre;
    }

    /**
     * Devuelve una lista con los elementos del árbol en el recorrido en
     * preorden
     *
     * @return
     */
    public Lista listarPreOrden() {
        Lista lista = new Lista();
        listarPreOrden(this.raiz, lista);
        return lista;
    }

    private void listarPreOrden(NodoGenerico nodo, Lista lista) {
        if (nodo != null) {
            NodoGenerico nodoHijo = nodo.getHijoIzquierdo();
            lista.insertar(nodo.getElem());
            while (nodoHijo != null) {
                listarPreOrden(nodoHijo, lista);
                nodoHijo = nodoHijo.getHermanoDerecho();
            }
        }
    }

    /**
     * Devuelve una lista con los elementos del árbol en el recorrido en
     * preorden
     *
     * @return
     */
    public Lista listarInOrden() {
        Lista lista = new Lista();
        listarInOrden(this.raiz, lista);
        return lista;
    }

    private void listarInOrden(NodoGenerico nodo, Lista lista) {
        if (nodo != null) {
            NodoGenerico nodoHijo = nodo.getHijoIzquierdo();
            listarInOrden(nodoHijo, lista);
            lista.insertar(nodo.getElem());
            if (nodoHijo != null) {
                nodoHijo = nodoHijo.getHermanoDerecho();

                while (nodoHijo != null) {
                    listarInOrden(nodoHijo, lista);
                    nodoHijo = nodoHijo.getHermanoDerecho();
                }

            }

        }
    }

    /**
     * Devuelve una lista con los elementos del árbol en el recorrido en
     * posorden
     *
     * @return
     */
    public Lista listarPosOrden() {
        Lista lista = new Lista();
        listarPosOrden(this.raiz, lista);
        return lista;
    }

    private void listarPosOrden(NodoGenerico nodo, Lista lista) {
        NodoGenerico nodoHijo = nodo.getHijoIzquierdo();

        while (nodoHijo != null) {
            listarPosOrden(nodoHijo, lista);
            nodoHijo = nodoHijo.getHermanoDerecho();
        }
        lista.insertar(nodo.getElem());

    }

    /**
     * Devuelve una lista con los elementos del árbol en el recorrido por
     * niveles
     *
     * @return
     */
    public Lista listarPorNiveles() {
        Lista lista = new Lista();
        Cola q = new Cola();
        NodoGenerico nodo, nodoHijo;
        q.poner(this.raiz);
        while (!q.esVacia()) {
            nodo = (NodoGenerico) q.obtenerFrente();
            q.sacar();
            lista.insertar(nodo.getElem());
            nodoHijo = nodo.getHijoIzquierdo();
            while (nodoHijo != null) {
                q.poner(nodoHijo);
                nodoHijo = nodoHijo.getHermanoDerecho();
            }
        }
        return lista;
    }

    /**
     * Genera y devuelve un árbol genérico que es equivalente (igual estructura
     * y contenido de los nodos) que el árbol original.
     *
     * @return
     */
    public ArbolGenerico clonar() {
        ArbolGenerico clon = new ArbolGenerico();
        return clon;
    }

    /**
     * Quita todos los elementos de la estructura.
     */
    public void vaciar() {
        this.raiz = null;
    }

    private NodoGenerico buscarNodo(Comparable buscado) {
        return buscarNodoAux(this.raiz, buscado);
    }

    private NodoGenerico buscarNodoAux(NodoGenerico nodo, Comparable buscado) {
        NodoGenerico nodoObtenido = null;

        while (nodo != null && nodoObtenido == null) {
            if (nodo.getElem().compareTo(buscado) == 0) {
                nodoObtenido = nodo;
            } else {
                nodoObtenido = buscarNodoAux(nodo.getHijoIzquierdo(), buscado);
                nodo = nodo.getHermanoDerecho();
            }

        }
        return nodoObtenido;
    }

    public boolean verificarCamino(Lista listElem, int niv) {
        return verificarCamino(this.raiz, listElem, niv, 0);
    }

    private boolean verificarCamino(NodoGenerico nodo, Lista listElem, int niv, int actual) {
        boolean exito = false;
        if (actual <= niv && niv < listElem.longitud()) {
            System.out.println("ACTUAL: " + actual + " NIVEL:" + niv);
            while (nodo != null && !exito) {
                Comparable elem = listElem.recuperar(actual + 1);
                if (nodo.getElem().compareTo(elem) == 0) {
                    System.out.println("ENTRO ACA");
                    System.out.println("ELEM:" + elem.toString());
                    System.out.println("NODO:" + nodo.getElem().toString());
                    if (actual == niv) {
                        System.out.println("ENTRO");
                        exito = true;
                    } else {
                        System.out.println("VA POR EL HIJO");
                        exito = verificarCamino(nodo.getHijoIzquierdo(), listElem, niv, actual + 1);
                    }
                }
                nodo = nodo.getHermanoDerecho();
            }
        }
        return exito;
    }

    @Override
    public String toString() {
        return toString(this.raiz);
    }

    private String toString(NodoGenerico nodo) {
        String s = "";
        if (nodo != null) {
            s += nodo.getElem().toString() + "->";
            NodoGenerico nodoHijo = nodo.getHijoIzquierdo();
            while (nodoHijo != null) {
                s += nodoHijo.getElem().toString() + ", ";
                nodoHijo = nodoHijo.getHermanoDerecho();
            }
            nodoHijo = nodo.getHijoIzquierdo();
            while (nodoHijo != null) {
                s += "\n" + toString(nodoHijo);
                nodoHijo = nodoHijo.getHermanoDerecho();
            }

        }
        return s;
    }

}
