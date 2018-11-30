/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import grafos.GrafoEtiquetado;
import java.util.HashMap;
import lineales.dinamicas.Lista;

/**
 *
 * @author Matthew
 */
public class JEG {

    //Declaracion de Variables
    static GrafoEtiquetado mundoTEG = new GrafoEtiquetado();
    static Jugador jugador1 = new Jugador("Alfa");
    static Jugador jugador2 = new Jugador("Beta");
    static Jugador jugador3 = new Jugador("Gama");
    static Jugador[] jugadores = {jugador1, jugador2, jugador3};
    static HashMap relacion = new HashMap();

    
    
    
    
    
    public static void quitarFichass(Pais pais, int cantFichas) {
        pais.quitarFicha(cantFichas);
    }

    public static Lista obtenerAtqConvenientes(Jugador j1) {
        Lista atqConv = new Lista();
        Lista lista = j1.getPaisesObtenidos().listar();
        for (int i = 1; i <= lista.longitud(); i++) {

            Pais p = (Pais) lista.recuperar(i);
            p = (Pais) mundoTEG.buscarEnProfundidad(p.getNombre());

            int fichas = p.getFichas();
            Lista adyacentes = mundoTEG.listarArcos(p);
            for (int j = 1; j <= adyacentes.longitud(); j++) {

                Pais ady = (Pais) adyacentes.recuperar(j);
                ady = (Pais) mundoTEG.buscarEnProfundidad(ady.getNombre());

                if (relacion.containsKey(ady)) {
                    Jugador dueño = (Jugador) relacion.get(ady);
                    if (!dueño.getNombre().equals(j1.getNombre())) {
                        if (fichas > ady.getFichas()) {
                            atqConv.insertar(p.getNombre() + " >> " + ady.getNombre());
                        }
                    }
                }
            }
        }
        return atqConv;
    }

    public static Jugador vaGanando() {
        int mayor = 0, longActual;
        Jugador ganador = null;
        for (Jugador jugador : jugadores) {
            longActual = jugador.getPaisesObtenidos().listar().longitud();
            if (longActual > mayor) {
                mayor = longActual;
                ganador = jugador;
            }
        }
        return ganador;
    }

    public static Jugador cumplioObjetivo() {
        Jugador ganador = null;
        for (Jugador jugador : jugadores) {
            if (jugador.getPaisesObtenidos().listar().longitud() >= 25) {
                ganador = jugador;
            }
        }
        return ganador;
    }

}
