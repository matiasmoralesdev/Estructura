package lineales.dinamicas;

public class Nodo {

    private Comparable elem;
    private Nodo enlace;

    public Nodo() {
        this.elem = null;
        this.enlace = null;
    }

    public Nodo(Comparable elem) {
        this.elem = elem;
        this.enlace = null;
    }

    public Nodo(Comparable elem, Nodo enlace) {
        this.elem = elem;
        this.enlace = enlace;
    }

    public void setElem(Comparable elem) {
        this.elem = elem;
    }

    public void setEnlace(Nodo enlace) {
        this.enlace = enlace;
    }

    public Comparable getElem() {
        return elem;
    }

    public Nodo getEnlace() {
        return enlace;
    }

}
