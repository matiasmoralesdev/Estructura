package grafos;

import lineales.dinamicas.Lista;

public class GrafoEtiquetado {
    
    private NodoVertice inicio;
    
    public GrafoEtiquetado() {
        this.inicio = null;
    }
    
    public GrafoEtiquetado(NodoVertice inicio) {
        this.inicio = inicio;
    }
    
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
    
    public boolean insertarArco(Comparable origen, Comparable destino, String etiqueta) {
        boolean exito = false;
        
        NodoVertice auxO = ubicarVertice(origen);
        NodoVertice auxD = ubicarVertice(destino);
        
        if (auxO != null && auxD != null) {
            
            if (auxO.getPrimerAdy() == null) {
                auxO.setPrimerAdy(new NodoAdyEti(auxD, etiqueta));
            }

            //Verifica si ambos vertices existen
            if (!this.existeCamino(origen, destino)) {
                
            }
            
        }
        
        return exito;
    }
    
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
            vis.insertar(n.getElem(), vis.longitud() + 1);
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
                vis.insertar(n.getElem(), vis.longitud() + 1);
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
    
}
