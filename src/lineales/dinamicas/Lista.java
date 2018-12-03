package lineales.dinamicas;

import juego.Pais;

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

    /**
     * Inserta siempre al final de la lista, como no es posible que haya un
     * error en la posicion, este metodo no devuelve un valor booleano
     *
     * @param elem Es el elemento comparable que tendra el nuevo nodo creado
     */
    public void insertar(Comparable elem) {
        if (esVacia()) {
            this.cabecera = new Nodo(elem);

        } else {
            Nodo aux = this.cabecera;
            while (aux.getEnlace() != null) {
                aux = aux.getEnlace();
            }
            aux.setEnlace(new Nodo(elem));
        }
        this.longitud++;
    }

    /**
     * Inserta u nuevo elemento al principio de la lista, como no es posible que
     * ocurra algun error ya que no entra por parametro ninguna posicion
     * entonces no es necesario que retorne un valor booleano
     *
     * @param elem Es el elemento comparable que tendra el nuevo nodo creado
     */
    public void insertarInicio(Comparable elem) {
        Nodo inicio = this.cabecera;
        this.cabecera = new Nodo(elem, inicio);
        this.longitud++;
    }

    public int localizar(Comparable elem) {
        int posicion = 1;
        Nodo aux = this.cabecera;
        boolean encontrado = false;
        while (aux != null && !encontrado) {
            if (aux.getElem().compareTo(elem) == 0) {
                encontrado = true;
            } else {
                aux = aux.getEnlace();
                posicion++;
            }
        }
        if (!encontrado) {
            posicion = -1;
        }

        return posicion;
    }

    public boolean eliminar(int pos) {
        boolean exito = true;
        if (pos < 1 || pos > this.longitud) {
            exito = false;
        } else if (this.cabecera == null) {
            exito = false;
        } else if (pos == 1) {
            this.cabecera = this.cabecera.getEnlace();
            this.longitud--;
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

    public boolean eliminarInicio() {
        boolean exito = false;
        if (this.cabecera != null) {
            this.cabecera = this.cabecera.getEnlace();
            exito = true;
            this.longitud--;
        }
        return exito;
    }

    public Comparable recuperar(int pos) {
        Comparable respuesta = null;
        Nodo aux = this.cabecera;
        int i = 1;
        while (i <= pos && aux != null) {
            if (i == pos) {
                respuesta = aux.getElem();
            }
            aux = aux.getEnlace();
            i++;
        }
        return respuesta;
    }

    public boolean esVacia() {
        return this.cabecera == null;
    }

    public int longitud() {
        return this.longitud;
    }

    public Lista clonar() {
        Lista clon = new Lista();
        if (!esVacia()) {
            Nodo aux = this.cabecera;
            clon.cabecera = new Nodo(this.cabecera.getElem());
            Nodo auxClon = clon.cabecera;
            aux = aux.getEnlace();
            while (aux != null) {
                auxClon.setEnlace(new Nodo(aux.getElem()));
                aux = aux.getEnlace();
                auxClon = auxClon.getEnlace();
            }
            clon.longitud = this.longitud;
        }

        return clon;
    }

    public String[] toArrayString() {
        String[] arreglo = new String[this.longitud];
        Nodo aux = this.cabecera;
        int i = 0;
        while (aux != null) {
            Comparable elem = aux.getElem();
            if (elem.getClass() == Pais.class) {
                Pais p = (Pais) elem;
                arreglo[i] = p.getNombre();
            } else {
                arreglo[i] = elem.toString();

            }
            aux = aux.getEnlace();
            i++;
        }
        return arreglo;
    }

    public Comparable[] toArray() {
        Comparable[] arreglo = new Comparable[this.longitud];

        Nodo aux = this.cabecera;
        int i = 0;
        while (aux != null) {
            arreglo[i] = aux.getElem();
            aux = aux.getEnlace();
            i++;
        }
        return arreglo;
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
