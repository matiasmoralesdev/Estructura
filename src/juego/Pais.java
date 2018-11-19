package juego;

public class Pais implements Comparable {

    String nombre;
    int cantEjercitos;

    public Pais(String name, int fichas) {
        this.nombre = name;
        this.cantEjercitos = fichas;
    }

    public Pais(String name) {
        this.nombre = name;
        this.cantEjercitos = 0;
    }

    @Override
    public boolean equals(Object o) {
        return this.nombre.equals(o.toString());
    }

    public boolean equals(Pais p) {
        return this.nombre.equals(p.nombre);
    }

    public boolean equals(String s) {
        return this.nombre.equals(s);
    }

    @Override
    public int compareTo(Object o) {
        return this.nombre.compareTo(o.toString());
    }

    public int compareTo(Pais p) {
        return this.nombre.compareTo(p.nombre);
    }

    public int compareTo(String s) {
        return this.nombre.compareTo(s);
    }

    @Override
    public String toString() {
        return this.nombre;
    }

}
