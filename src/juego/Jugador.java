package juego;

import conjuntistas.ArbolAVL;

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

}
