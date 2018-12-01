package grafos;

import lineales.dinamicas.Lista;

/**
 *
 * @author Matthew
 */
public class GrafoEtiquetado {

    private NodoVertice inicio;

    /**
     * Constructor Vacio Crea un grafo vacío
     */
    public GrafoEtiquetado() {
        this.inicio = null;
    }

    /**
     * Dado un elemento de tipoVertice se lo agrega a la estructura controlando
     * que no se inserten vértices repetidos.
     *
     * @param nuevoVertice: elemento que tendra el nuevo vertice
     * @return Si puede realizar la inserción devuelve verdadero en caso
     * contrario devuelve falso.
     */
    public boolean insertarVertice(Comparable nuevoVertice) {
        boolean exito = false;
        NodoVertice aux = this.ubicarVertice(nuevoVertice);
        if (aux == null) {
            this.inicio = new NodoVertice(nuevoVertice, this.inicio);
            exito = true;
        }
        return exito;
    }

    private NodoVertice ubicarVertice(Comparable buscado) {
        NodoVertice aux = this.inicio;
        while (aux != null && !aux.getElem().equals(buscado)) {
            aux = aux.getSigVertice();
        }
        return aux;
    }

    /**
     *
     * @param vertice
     * @return
     */
    public boolean eliminarVertice(Comparable vertice) {
        boolean exito = false;

        return exito;
    }

    /**
     *
     * @param origen
     * @param destino
     * @return
     */
    public boolean eliminarArco(Comparable origen, Comparable destino) {
        boolean exito = false;

        NodoVertice auxO = ubicarVertice(origen);
        NodoVertice auxD = ubicarVertice(destino);

        NodoAdyEti siguiente;

        if (auxO != null && auxD != null) {
            //Verifica si ambos vertices existen
            NodoAdyEti ady = auxO.getPrimerAdy();
            if (ady != null) {
                siguiente = ady.getSigAdyacente();
                if (ady.getVertice().getElem().equals(destino)) {
                    auxO.setPrimerAdy(siguiente);
                } else {
                    while (siguiente != null && !exito) {
                        if (siguiente.getVertice().getElem().equals(destino)) {
                            ady.setSigAdy(siguiente.getSigAdyacente());
                            exito = true;
                        } else {
                            ady = ady.getSigAdyacente();
                            siguiente = siguiente.getSigAdyacente();
                        }
                    }
                }
            }
        }
        return exito;
    }

    /**
     *
     * @param origen
     * @param destino
     * @return
     */
    public boolean eliminarArcoDoble(Comparable origen, Comparable destino) {
        boolean exito = false;
        NodoVertice auxO = ubicarVertice(origen);
        NodoVertice auxD = ubicarVertice(destino);
        NodoAdyEti siguiente;
        if (auxO != null && auxD != null) {
            //Verifica si ambos vertices existen
            NodoAdyEti ady = auxO.getPrimerAdy();
            if (ady != null) {
                siguiente = ady.getSigAdyacente();
                if (ady.getVertice().getElem().equals(destino)) {
                    auxO.setPrimerAdy(siguiente);
                } else {
                    while (siguiente != null && !exito) {
                        if (siguiente.getVertice().getElem().equals(destino)) {
                            ady.setSigAdy(siguiente.getSigAdyacente());
                            exito = true;
                        } else {
                            ady = ady.getSigAdyacente();
                            siguiente = siguiente.getSigAdyacente();
                        }
                    }
                }
            }
            exito = false;
            ady = auxD.getPrimerAdy();
            if (ady != null) {
                siguiente = ady.getSigAdyacente();
                if (ady.getVertice().getElem().equals(origen)) {
                    auxD.setPrimerAdy(siguiente);
                } else {
                    while (siguiente != null && !exito) {
                        if (siguiente.getVertice().getElem().equals(origen)) {
                            ady.setSigAdy(siguiente.getSigAdyacente());
                            exito = true;
                        } else {
                            ady = ady.getSigAdyacente();
                            siguiente = siguiente.getSigAdyacente();
                        }
                    }
                }
            }

        }
        return exito;
    }

    /**
     *
     * @param origen: vertice del cual se genera un nuevo arco
     * @param destino: vertice destino del arco
     * @param etiqueta: etiqueta del arco
     * @return exito Si creo un nuevo arco(True) si no(False)
     */
    public boolean insertarArco(Comparable origen, Comparable destino, String etiqueta) {
        boolean exito = false;
        NodoVertice auxO = ubicarVertice(origen);
        NodoVertice auxD = ubicarVertice(destino);
        if (auxO != null && auxD != null) {
            //Verifica si ambos vertices existen
            NodoAdyEti ady = auxO.getPrimerAdy();
            if (!existeArco(auxO, destino)) {
                NodoAdyEti nuevo = new NodoAdyEti(auxD, etiqueta);
                nuevo.setSigAdy(ady);
                auxO.setPrimerAdy(nuevo);
                exito = true;
            }
        }
        return exito;
    }

    /**
     *
     * @param origen
     * @param destino
     * @param etiqueta
     * @return
     */
    public boolean insertarArcoDoble(Comparable origen, Comparable destino, String etiqueta) {
        boolean exito = false;
        NodoVertice auxO = ubicarVertice(origen);
        NodoVertice auxD = ubicarVertice(destino);
        if (auxO != null && auxD != null) {

            NodoAdyEti ady = auxO.getPrimerAdy();
            if (!existeArco(auxO, destino)) {
                NodoAdyEti nuevo = new NodoAdyEti(auxD, etiqueta);
                nuevo.setSigAdy(ady);
                auxO.setPrimerAdy(nuevo);
                exito = true;

                ady = auxD.getPrimerAdy();
                if (!existeArco(auxD, origen)) {
                    nuevo = new NodoAdyEti(auxO, etiqueta);
                    nuevo.setSigAdy(ady);
                    auxD.setPrimerAdy(nuevo);
                }
            }
        }
        return exito;
    }

    private boolean existeArco(NodoVertice nodo, Comparable elem) {
        boolean existe = false;
        NodoAdyEti ady = nodo.getPrimerAdy();
        while (ady != null && !existe) {
            if (ady.getVertice().getElem().equals(elem)) {
                existe = true;
            } else {
                ady = ady.getSigAdyacente();
            }
        }
        return existe;
    }

    /**
     *
     * @return
     */
    public Lista listarEnProfundidad() {
        Lista visitados = new Lista();
        //define un vertice donde comenzar a recorrer
        NodoVertice aux = this.inicio;
        while (aux != null) {
            if (visitados.localizar(aux.getElem()) < 0) {
                //Si el vertice no fue visitado aun, avanza en profundidad
                listarEnProfundidadAux(aux, visitados);
            }
            aux = aux.getSigVertice();
        }
        return visitados;
    }

    private void listarEnProfundidadAux(NodoVertice n, Lista vis) {
        if (n != null) {
            //marca al vertice n como visitado
            vis.insertar(n.getElem());
            NodoAdyEti ady = n.getPrimerAdy();
            while (ady != null) {
                // visita en profundidad los adyacentes de n aun no visitados   
                if (vis.localizar(ady.getVertice().getElem()) < 0) {
                    listarEnProfundidadAux(ady.getVertice(), vis);
                }
                ady = ady.getSigAdyacente();
            }
        }
    }

    public Comparable buscarEnProfundidad(Comparable buscado) {
        Lista visitados = new Lista();
        NodoVertice aux = this.inicio;
        Comparable retorno = null;
        while (aux != null && retorno == null) {
            if (visitados.localizar(aux.getElem()) < 0) {
                retorno = buscarEnProfundidad(buscado, aux, visitados);
            }
            aux = aux.getSigVertice();
        }
        return retorno;
    }

    private Comparable buscarEnProfundidad(Comparable buscado, NodoVertice nodo, Lista vis) {
        Comparable retorno = null;
        if (nodo != null) {
            if (nodo.getElem().equals(buscado)) {
                retorno = nodo.getElem();
            } else {
                vis.insertar(nodo.getElem());
                NodoAdyEti ady = nodo.getPrimerAdy();
                while (ady != null && retorno == null) {
                    if (vis.localizar(ady.getVertice().getElem()) < 0) {
                        retorno = buscarEnProfundidad(buscado, ady.getVertice(), vis);
                    }
                    ady = ady.getSigAdyacente();
                }
            }
        }
        return retorno;
    }

    public Lista listarVertices() {
        Lista retorno = new Lista();
        NodoVertice nodo = this.inicio;
        while (nodo != null) {
            retorno.insertarInicio(nodo.getElem());
            nodo = nodo.getSigVertice();
        }
        return retorno;
    }

    /**
     *
     * @param origen
     * @param destino
     * @return
     */
    public boolean existeCamino(Comparable origen, Comparable destino) {
        boolean exito = false;
        //Verifica si ambos vertices existen;
        NodoVertice auxO = ubicarVertice(origen);
        NodoVertice auxD = ubicarVertice(destino);
        if (auxO != null && auxD != null) {
            //Si ambos vertices existen busca si existe un camino entre ambos
            Lista visitados = new Lista();
            exito = existeCaminoAux(auxO, destino, visitados);
        }
        return exito;
    }

    private boolean existeCaminoAux(NodoVertice n, Comparable dest, Lista vis) {
        boolean exito = false;
        if (n != null) {
            //si vertice n es el destino: HAY CAMINO!
            if (n.getElem().equals(dest)) {
                exito = true;
            } else {
                //si no es el destino verifica si hay camino entre n y destino
                vis.insertar(n.getElem());
                NodoAdyEti ady = n.getPrimerAdy();
                while (!exito && ady != null) {
                    if (vis.localizar(ady.getVertice().getElem()) < 0) {
                        exito = existeCaminoAux(ady.getVertice(), dest, vis);
                    }
                    ady = ady.getSigAdyacente();
                }
            }
        }
        return exito;
    }

    public Lista caminoMasCorto(Comparable origen, Comparable destino) {
        Lista lista = new Lista();
        Lista exito = new Lista();
        NodoVertice auxO = ubicarVertice(origen);
        NodoVertice auxD = ubicarVertice(destino);
        if (auxO != null && auxD != null) {
            exito = caminoMasCorto(auxO, destino, lista, exito);
        }
        return exito;
    }

    private Lista caminoMasCorto(NodoVertice n, Comparable dest, Lista vis, Lista exito) {
        vis.insertar(n.getElem());
        if (exito.esVacia() || vis.longitud() < exito.longitud()) {

            //System.out.println(vis.toString());
            if (n.getElem().equals(dest)) {
                exito = vis.clonar();
                //System.out.println("<<<<<ENCONTRADO " + exito.toString());
//                if (exito.esVacia()) {
//                    exito = vis.clonar();
//                    System.out.println("PRIMER EXITO:" + exito.toString());
//                } else {
//                    exito = vis.clonar();
//                    System.out.println("OTRO EXITO:" + exito.toString());
//                }
            } else {
                //si no es el destino verifica si hay camino entre n y destino
                NodoAdyEti ady = n.getPrimerAdy();
                while (ady != null) {
                    if (vis.localizar(ady.getVertice().getElem()) < 0) {
                        exito = caminoMasCorto(ady.getVertice(), dest, vis, exito);
                    }
                    ady = ady.getSigAdyacente();
                }
            }

        }
        vis.eliminar(vis.longitud());
        return exito;
    }

    public Lista listarArcos(Comparable elem) {
        Lista lista = new Lista();
        NodoVertice nodo = ubicarVertice(elem);
        if (nodo != null) {
            NodoAdyEti ady = nodo.getPrimerAdy();
            while (ady != null) {
                lista.insertar(ady.getVertice().getElem());
                ady = ady.getSigAdyacente();
            }
        }
        return lista;
    }

    public boolean esVacio() {
        return this.inicio == null;
    }

    public void vaciar() {
        this.inicio = null;
    }

    @Override
    public String toString() {
        String s = "";
        NodoVertice vertice = this.inicio;
        while (vertice != null) {
            s += "VERTICE: " + vertice.getElem().toString() + "\n";
            NodoAdyEti ady = vertice.getPrimerAdy();
            while (ady != null) {
                s += "ARCO A: ";
                s += ady.getVertice().getElem().toString() + "  -  ";
                s += "ETIQUETA: " + ady.getEtiqueta() + "\n";
                ady = ady.getSigAdyacente();
            }
            s += "\n";
            vertice = vertice.getSigVertice();
        }
        return s;
    }

}
