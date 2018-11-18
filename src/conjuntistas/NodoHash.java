package conjuntistas;

public class NodoHash {

    private Comparable elem;
    private NodoHash enlace;

    public NodoHash() {
        this.elem = null;
        this.enlace = null;
    }

    public NodoHash(Comparable elem, NodoHash enlace) {
        this.elem = elem;
        this.enlace = enlace;
    }

    public NodoHash(Comparable elem) {
        this.elem = elem;
        this.enlace = null;
    }
    
    
    

}
