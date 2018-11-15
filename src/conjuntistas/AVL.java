/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package estructurasLineales;

import utiles.ErrorTDA;

/**
 *
 * @author Matt
 */
public class AVL {
    //Atributos

    private NodoABB raiz;
    //Constructores

    public AVL() {
        this.raiz = null;
    }

    public AVL(Object item, Comparable key) {
        this.raiz = new NodoABB(key, item);
    }

    public void insertar(Comparable clave, Object elemento) throws ErrorTDA {
        this.raiz = insertarInterno(clave, elemento, this.raiz);
    }
    
    public void eliminar (Comparable clave) throws ErrorTDA{
        eliminarInterno (clave, this.raiz);
    }
    
    private NodoABB eliminarInterno(Comparable key, NodoABB node) throws ErrorTDA{
        if (node != null) {
            if (key.compareTo(node.getClave()) < 0) {
                node.setHijoIzq(eliminarInterno(key, node.getHijoIzq()));
                node = balancear (node);
            } else if (key.compareTo(node.getClave()) > 0) {
                node.setHijoDer(eliminarInterno(key, node.getHijoDer()));
                node = balancear (node);
            } else {
                if (node.getHijoIzq() == null && node.getHijoDer() == null) {
                    node = null;
                } else if ((node.getHijoIzq() == null) && node.getHijoDer() != null) {
                    node = node.getHijoDer();
                } else if (node.getHijoIzq() != null && node.getHijoDer() == null) {
                    node = node.getHijoIzq();
                } else {
                    NodoABB aux = recuperarElMinimo(node.getHijoDer());
                    node.setDato(aux.getDato());
                    node.setClave(aux.getClave());
                    node.setHijoDer(eliminarInterno(aux.getClave(), node.getHijoDer()));
                }
            }
        } else {
            throw new ErrorTDA(5);
        }
        return node;
    
    }

    private NodoABB insertarInterno(Comparable key, Object item, NodoABB nodo) throws ErrorTDA {
        if (nodo != null) {
            if (key.compareTo(nodo.getClave()) < 0) {
                if (nodo.getHijoIzq() != null) {
                    nodo.setHijoIzq(insertarInterno(key, item, nodo.getHijoIzq()));
                    nodo = balancear(nodo);
                } else {
                    nodo.setHijoIzq(new NodoABB(key, item));
                }
            } else if (key.compareTo(nodo.getClave()) > 0) {
                if (nodo.getHijoDer() != null) {
                    nodo.setHijoDer(insertarInterno(key, item, nodo.getHijoDer()));
                    nodo = balancear(nodo);
                } else {
                    nodo.setHijoDer(new NodoABB(key, item));
                }
            } else {
                throw new ErrorTDA(6);
            }
        } else {
            nodo = new NodoABB(key, item);
        }
        return nodo;
    }

    private int balanceNodo(NodoABB n) {
        return (altura(n.getHijoIzq()) - altura(n.getHijoDer()));
    }

    private NodoABB balancear(NodoABB node) {
        NodoABB retorno = node;
        int balance = balanceNodo(node);
        if (balance == 2) {
            retorno = node.getHijoIzq();
            NodoABB temp = retorno.getHijoDer();
            if (balanceNodo(node.getHijoIzq()) == 1) {
                //rotacion simple a derecha
                retorno.setHijoDer(node);
                node.setHijoIzq(temp);
            } else {
                //rotacion doble izquierda-derecha
                node.setHijoIzq(temp);
                temp.setHijoIzq(retorno);
                retorno.setHijoDer(null);
                retorno = temp.getHijoDer();
                temp.setHijoDer(node);
                node.setHijoIzq(retorno);
                retorno = temp;
            }
        }
        if (balance == -2) {
            retorno = node.getHijoDer();
            NodoABB temp = retorno.getHijoIzq();
            if (balanceNodo(node.getHijoDer()) == -1) {
                //rotacion simple a izquierda
                retorno.setHijoIzq(node);
                node.setHijoDer(temp);
            } else {
                //rotacion doble derecha-izquierda
                node.setHijoDer(temp);
                NodoABB temp1 = temp.getHijoDer();
                temp.setHijoDer(retorno);
                retorno.setHijoIzq(temp1);
                temp.setHijoIzq(node);
                node.setHijoDer(null);
                retorno = temp;
            }
        }
        return retorno;
    }

    private int altura(NodoABB nodo) {
        int h , h1;
        if (nodo != null) {
//            if (nodo.getHijoIzq() != null) {
                h = 1 + altura(nodo.getHijoIzq());
//            }
//            if (nodo.getHijoDer() != null) {
                h1 = 1 + altura(nodo.getHijoDer());
//            }
            if (h1 > h) {
                h = h1;
            }
        } else {
            h = -1;
        }
        return h;
    }
    
    private NodoABB recuperarElMinimo(NodoABB node) {
        NodoABB retorno;
        if (node.getHijoIzq() != null) {
            retorno = recuperarElMinimo(node.getHijoIzq());
        } else {
            retorno = node;
        }
        return retorno;
    }

//    private NodoABB recuperarElMaximo(NodoABB node) {
//        NodoABB retorno;
//        if (node.getHijoDer() != null) {
//            retorno = recuperarElMaximo(node.getHijoDer());
//        } else {
//            retorno = node;
//        }
//        return retorno;
//    }

    @Override
    public String toString() {
        String s = toString(this.raiz);
        return s;
    }

    private String toString(NodoABB node) {
        String s = "";
        if (node != null) {
            s += node.getDato().toString() + " - Hijos : ";
            if (node.getHijoIzq() != null) {
                s += " Izquierdo : " + node.getHijoIzq().getDato().toString() + " / ";
            }
            if (node.getHijoDer() != null) {
                s += " Derecho : " + node.getHijoDer().getDato().toString();
            }
            s += "\n";
            s += toString(node.getHijoIzq());
            s += toString(node.getHijoDer());
        }
        return s;
    }
}
