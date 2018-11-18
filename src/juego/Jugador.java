package juego;

import conjuntistas.ArbolAVL;

public class Jugador {

    private String nombre;
    private ArbolAVL paises;

    public Jugador() {
        
    }

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.paises = new ArbolAVL();
    }

}
