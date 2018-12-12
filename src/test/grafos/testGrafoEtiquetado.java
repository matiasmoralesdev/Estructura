package test.grafos;

import grafos.GrafoEtiquetado;
import juego.Pais;

public class testGrafoEtiquetado {

    public static void main(String[] args) {
        GrafoEtiquetado grafo = new GrafoEtiquetado();
        grafo.insertarVertice(new Pais("Argentina"));
        grafo.insertarVertice(new Pais("Chile"));
        grafo.insertarVertice(new Pais("Peru"));
        grafo.insertarVertice(new Pais("Bolivia"));
        grafo.insertarVertice(new Pais("Paraguay"));
        grafo.insertarVertice(new Pais("Canada"));
        grafo.insertarVertice(new Pais("China"));
        grafo.insertarVertice(new Pais("Japon"));
        grafo.insertarArco("Argentina", "Chile", "jajas");
        grafo.insertarArco("Argentina", "Peru", "jajas");
        grafo.insertarArco("Argentina", "Canada", "jajas");
        grafo.insertarArco("Peru", "Chile", "jajas");
        grafo.insertarArco("Canada", "Bolivia", "jajas");
        grafo.insertarArco("Paraguay", "Chile", "jajas");
        grafo.insertarArco("Japon", "Chile", "jajas");
        grafo.insertarArco("China", "Chile", "jajas");

        grafo.insertarArco("China", "Japon", "jajas");

        System.out.println(grafo.toString());
        System.out.println(grafo.listarEnProfundidad().toString());

        grafo.eliminarArco("Peru", "Argentina");

        System.out.println(grafo.toString());
        System.out.println(grafo.listarEnProfundidad().toString());
        System.out.println("-----------------------------");
        Pais p = (Pais) grafo.buscarEnProfundidad("Paraguay");
        System.out.println(p.toString());
        System.out.println(grafo.existeCamino("Argentina", "Chile"));
        System.out.println("CAMINO MAS CORTO: " + grafo.caminoMasCorto("Argentina", "Japon").toString());
        System.out.println("-----------------------------");
        System.out.println("-----------------------------");
        System.out.println("-----------------------------");
        System.out.println("-----------------------------");
        System.out.println("-----------------------------");
        grafo.eliminarVertice("Japon");
        System.out.println(grafo.toString());
        System.out.println(grafo.listarEnProfundidad().toString());

    }
}
