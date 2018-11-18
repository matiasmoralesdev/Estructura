package lineales.dinamicas;

public class Lista {

    private Nodo cabecera;
    private int longitud;

    public Lista() {
        this.cabecera = null;
        this.longitud = 0;
    }

    public boolean insertar(Comparable elem, int pos) {
        boolean exito = true;
        if (pos < 1 || pos > this.longitud + 1) {
            exito = false;
        } else {
            if (pos == 1) {

                this.cabecera = new Nodo(elem, this.cabecera);

            } else {

                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                Nodo nuevo = new Nodo(elem, aux.getEnlace());
                aux.setEnlace(nuevo);

            }
            this.longitud++;
        }

        return exito;
    }

    public int localizar(Comparable elem) {
        return 3;
    }

    public boolean eliminar(int pos) {
        boolean exito = true;
        if (pos < 1 || pos > this.longitud) {
            exito = false;
        } else if (this.cabecera == null) {
            exito = false;
        } else if (pos == 1) {
            this.cabecera = this.cabecera.getEnlace();
        } else {
            Nodo aux = this.cabecera;
            int i = 1;
            while (i < pos - 1) {
                aux = aux.getEnlace();
                i++;
            }
            aux.setEnlace(aux.getEnlace().getEnlace());
            this.longitud--;
        }
        return exito;
    }

    public boolean esVacia() {
        return this.cabecera == null;
    }

    public int longitud() {
        return this.longitud;
    }

    @Override
    public String toString() {
        String s = "";

        if (this.cabecera != null) {

            Nodo aux = this.cabecera;
            while (aux != null) {
                s += aux.getElem().toString() + " ";
                aux = aux.getEnlace();
            }

        } else {
            s = "LISTA VACIA!!!!";
        }
        return s;
    }

}
