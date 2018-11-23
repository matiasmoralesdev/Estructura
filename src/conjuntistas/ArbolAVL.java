package conjuntistas;

import lineales.dinamicas.Lista;

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
        if (this.raiz != null) {
            this.raiz = eliminar(elem, this.raiz);
        }

        return exito;
    }

    private NodoAVL eliminar(Comparable elem, NodoAVL nodo) {

        if (elem.compareTo(nodo.getElem()) < 0) {
            if (nodo.getIzquierdo() != null) {
                nodo.setIzquierdo(eliminar(elem, nodo.getIzquierdo()));
                nodo.setAltura(Math.max(altura(nodo.getIzquierdo()), altura(nodo.getDerecho())) + 1);
                nodo = balancear(nodo);
            }
        } else if (elem.compareTo(nodo.getElem()) > 0) {
            if (nodo.getDerecho() != null) {
                nodo.setDerecho(eliminar(elem, nodo.getDerecho()));
                nodo.setAltura(Math.max(altura(nodo.getIzquierdo()), altura(nodo.getDerecho())) + 1);
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
                nodo.setDerecho(eliminar(aux.getElem(), nodo.getDerecho()));
            }
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
        h.setIzquierdo(nodo);
        nodo.setDerecho(temp);

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

    public Lista listar() {
        Lista lista = new Lista();
        listar(this.raiz, lista);
        return lista;
    }

    private void listar(NodoAVL nodo, Lista lista) {
        if (nodo != null) {
            listar(nodo.getIzquierdo(), lista);
            lista.insertarAlFinal(nodo.getElem());
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
                lista.insertarAlFinal(nodo.getElem());
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
