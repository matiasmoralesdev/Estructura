package grafos;

public class NodoAdyEti {

    private NodoVertice vertice;
    private NodoAdyEti sigAdy;
    private String etiqueta;

    public NodoAdyEti() {

    }

    public NodoVertice getVertice() {
        return this.vertice;
    }

    public NodoAdyEti getSigAdyacente() {
        return this.sigAdy;
    }

    public String getEtiqueta() {
        return this.etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public void setSigAdy(NodoAdyEti sigAdy) {
        this.sigAdy = sigAdy;
    }

    public void setVertice(NodoVertice vertice) {
        this.vertice = vertice;
    }

}
