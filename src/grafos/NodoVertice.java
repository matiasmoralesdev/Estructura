package grafos;

public class NodoVertice {

    private Comparable elem;
    private NodoVertice sigVertice;
    private NodoAdyEti primerAdy;

    public NodoVertice(Comparable item) {
        this.elem = item;
        this.sigVertice = null;
        this.primerAdy = null;
    }

    public NodoVertice(Comparable item, NodoAdyEti ady, NodoVertice n) {
        this.elem = item;
        this.sigVertice = n;
        this.primerAdy = ady;
    }

    public Comparable getElem() {
        return this.elem;
    }

    public void setElem(Comparable item) {
        this.elem = item;
    }

    public NodoVertice getSigVertice() {
        return this.sigVertice;
    }

    public void setSigVertice(NodoVertice nodo) {
        this.sigVertice = nodo;
    }

    public NodoAdyEti getPrimerAdy() {
        return this.primerAdy;
    }

    public void setPrimerAdy(NodoAdyEti nodo) {
        this.primerAdy = nodo;
    }

}
