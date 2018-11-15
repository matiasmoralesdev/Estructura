/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jerarquicas;

import lineales.dinamicas.ListaInt;

/**
 *
 * @author mamorales
 */
public class ABB {

    private NodoArbol raiz;

    public ABB() {
        this.raiz = null;
    }

    public boolean insertar(int elem) {

        boolean exito = true;

        if (this.raiz == null) {
            this.raiz = new NodoArbol(elem);
        } else {
            exito = insertar(elem, this.raiz);
        }

        return exito;
    }

    private boolean insertar(int elem, NodoArbol nodo) {
        // precondicion: nodo no es nulo
        boolean exito = true;

        if (elem < nodo.getElem()) {
            //elem es menor que nodo.getElem()
            //Si tiene Hijo Izquierdo baja a la izquierda, sino agrega elemento
            if (nodo.getIzquierdo() != null) {
                insertar(elem, nodo.getIzquierdo());
            } else {
                nodo.setIzquierdo(new NodoArbol(elem));
            }

        } else if (elem > nodo.getElem()) {
            //elem es mayor que nodo.getElem()
            //Si tiene Hijo derecho  baja a la derecha, sino agrega elemento
            if (nodo.getDerecho() != null) {
                exito = insertar(elem, nodo.getDerecho());
            } else {
                nodo.setDerecho(new NodoArbol(elem));
            }

        } else {
            //Reportar error: elemento repetido
            exito = false;

        }
        return exito;
    }

    public boolean pertenece(int elem) {
        boolean esta = false;
        if (this.raiz != null) {
            esta = pertenece(this.raiz, elem);
        }
        return esta;
    }

    private boolean pertenece(NodoArbol nodo, int elem) {

        boolean esta = true;

        if (nodo.getElem() != elem) {

            if (elem < nodo.getElem()) {
                if (nodo.getIzquierdo() != null) {
                    esta = pertenece(nodo.getIzquierdo(), elem);
                } else {
                    esta = false;
                }
            } else if (elem > nodo.getElem()) {
                if (nodo.getDerecho() != null) {
                    esta = pertenece(nodo.getDerecho(), elem);
                } else {
                    esta = false;
                }

            }
        }
        return esta;
    }

    public boolean eliminar(int elem) {
        boolean exito = false;
        if (this.raiz != null) {
            this.raiz = eliminar(elem, this.raiz);
        }
        return exito;
    }

    private NodoArbol eliminar(int elem, NodoArbol nodo) {
        NodoArbol nuevo = new NodoArbol();

        if (nodo != null) {

            if (nodo.getElem() == elem) {
                //Si es Hoja
                if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                    nuevo = null;

                    //Si tiene un solo hijo
                } else if ((nodo.getIzquierdo() != null && nodo.getDerecho() == null)) {
                    nuevo = nodo.getIzquierdo();

                } else if (nodo.getIzquierdo() == null && nodo.getDerecho() != null) {
                    nuevo = nodo.getDerecho();
                    //Si tiene los 2 hijos
                } else {

                    NodoArbol padreCandidato = obtenerPadreCandidato(nodo.getDerecho());

                    if (padreCandidato == nodo.getDerecho()) {

                        nodo.setElem(elem);

                    }

                }

            }

        } else {

        }
        return nuevo;
    }

    public boolean esVacio() {
        return (this.raiz == null);
    }

    public ListaInt listar() {
        ListaInt lista = new ListaInt();
        listar(this.raiz, lista);
        return lista;

    }

    private void listar(NodoArbol nodo, ListaInt lista) {
        if (nodo != null) {
            listar(nodo.getIzquierdo(), lista);
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
            listar(nodo.getDerecho(), lista);
        }
    }

    public ListaInt listarRango(int min, int max) {
        ListaInt lista = new ListaInt();
        listarRango(this.raiz, lista, min, max);
        return lista;

    }

    private void listarRango(NodoArbol nodo, ListaInt lista, int min, int max) {
        if (nodo != null) {

            if (nodo.getElem() > min) {
                listarRango(nodo.getIzquierdo(), lista, min, max);
            }
            if (nodo.getElem() >= min && nodo.getElem() <= max) {
                lista.insertar(nodo.getElem(), lista.longitud() + 1);
            }

            if (nodo.getElem() < max) {
                listarRango(nodo.getDerecho(), lista, min, max);
            }

        }

    }

    public int minimoElem() {
        return minimoElem(this.raiz);
    }

    private int minimoElem(NodoArbol nodo) {
        int minimo;
        if (nodo != null) {
            if (nodo.getIzquierdo() != null) {
                minimo = minimoElem(nodo.getIzquierdo());
            } else {
                minimo = nodo.getElem();
            }
        } else {
            minimo = 37707;
        }
        return minimo;
    }

    private NodoArbol obtenerPadreCandidato(NodoArbol n) {
        NodoArbol resp = null;
        if (n != null) {
            if (n.getIzquierdo() != null) {
                resp = n;
            } else {

                if (n.getIzquierdo().getIzquierdo() == null) {
                    resp = n.getIzquierdo();

                } else {
                    resp = obtenerPadreCandidato(n.getIzquierdo());
                }
            }
        }
        return resp;
    }

    public int maximoElem() {
        return maximoElem(this.raiz);
    }

    private int maximoElem(NodoArbol nodo) {
        int maximo;
        if (nodo != null) {
            if (nodo.getDerecho() != null) {
                maximo = maximoElem(nodo.getDerecho());
            } else {
                maximo = nodo.getElem();
            }
        } else {
            maximo = 37707;
        }
        return maximo;
    }

}
