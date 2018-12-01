package juego;

import conjuntistas.ArbolAVL;
import java.util.Objects;

public class Jugador {

    private String nombre;
    private ArbolAVL paisesObtenidos;
    //private boolean iniciado;

    public Jugador() {

    }

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.paisesObtenidos = new ArbolAVL();
        //   this.iniciado = false;
    }

    /*
    public void inicializar() {
        this.iniciado = true;
    }

    public boolean iniciado() {
        return this.iniciado;
    }
     */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArbolAVL getPaisesObtenidos() {
        return paisesObtenidos;
    }

    public void setPaisesObtenidos(ArbolAVL paisesObtenidos) {
        this.paisesObtenidos = paisesObtenidos;
    }

    @Override
    public int hashCode() {
        return nombre.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Jugador other = (Jugador) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }

    public boolean equals(Jugador j) {
        return this.nombre.equals(j.nombre);
    }

    @Override
    public String toString() {
        return this.nombre;
    }

}
