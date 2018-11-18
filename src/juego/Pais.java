package juego;

public class Pais implements Comparable {

    String nombre;
    int cantEjercitos;

    public Pais(String name, int fichas) {
        this.nombre = name;
        this.cantEjercitos = fichas;
    }

    @Override
    public int compareTo(Object o) {
        Pais p2 = (Pais) o;
        return this.nombre.compareTo(p2.nombre);
    }

    @Override
    public String toString(){
        return this.nombre;
    }
    
    
}
