package test;

import grafos.GrafoEtiquetado;
import juego.Pais;

public class testGrafoEtiquetado {

    public static void main(String[] args) {
        GrafoEtiquetado grafo = new GrafoEtiquetado();
        grafo.insertarVertice(new Pais("Argentina"));
        grafo.insertarVertice(new Pais("Chile"));
        grafo.insertarArcoDoble("Argentina", "Chile", "jajas");

        System.out.println(grafo.toString());

    }
}
