package juego;

public class Pais implements Comparable {

    private String nombre;
    private int cantEjercitos;

    public Pais(String name, int fichas) {
        this.nombre = name;
        this.cantEjercitos = fichas;
    }

    public Pais(String name) {
        this.nombre = name;
        this.cantEjercitos = 0;
    }

    public int getFichas() {
        return this.cantEjercitos;
    }

    public void setFichas(int ejercitos) {
        this.cantEjercitos = ejercitos;
    }

    public void sumarFicha(int n) {
        this.cantEjercitos += Math.abs(n);
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String s) {
        this.nombre = s;
    }

    public void quitarFicha(int n) {
        if (this.cantEjercitos < n) {
            this.cantEjercitos = 0;
        } else {
            this.cantEjercitos -= Math.abs(n);
        }
    }

    @Override
    public boolean equals(Object o) {
        boolean valor;
        Pais p;
        if (o.getClass() == this.getClass()) {
            p = (Pais) o;
            valor = this.nombre.equals(p.nombre);
        } else {
            valor = this.nombre.equals(o.toString());
        }
        return valor;
    }

    @Override
    public int compareTo(Object o) {
        int valor;
        Pais p;
        if (o.getClass() == this.getClass()) {
            p = (Pais) o;
            valor = this.nombre.compareTo(p.nombre);
        } else {
            valor = this.nombre.compareTo(o.toString());
        }
        return valor;
    }

    @Override
    public String toString() {
        return this.nombre + "(" + this.cantEjercitos + ")";
    }

    public String aCadena() {
        return this.nombre + "(" + this.cantEjercitos + ")";
    }

    @Override
    public int hashCode() {
        return nombre.hashCode(); //To change body of generated methods, choose Tools | Templates.

    }

}
