package grafos;

public class NodoAdyEti {

    private NodoVertice vertice;
    private NodoAdyEti sigAdy;
    private String etiqueta;

    public NodoAdyEti() {
        this.etiqueta = null;
        this.sigAdy = null;
        this.vertice = null;
    }

    public NodoAdyEti(NodoVertice vertice, String etiqueta) {
        this.vertice = vertice;
        this.etiqueta = etiqueta;
        this.sigAdy = null;
    }

    public NodoAdyEti(NodoVertice vertice, NodoAdyEti sigAdy, String etiqueta) {
        this.vertice = vertice;
        this.sigAdy = sigAdy;
        this.etiqueta = etiqueta;
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
