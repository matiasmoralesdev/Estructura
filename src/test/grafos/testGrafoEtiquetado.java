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
        grafo.insertarArcoDoble("Argentina", "Chile", "jajas");
        grafo.insertarArcoDoble("Argentina", "Peru", "jajas");
        grafo.insertarArcoDoble("Argentina", "Canada", "jajas");
        grafo.insertarArcoDoble("Peru", "Chile", "jajas");
        grafo.insertarArcoDoble("Canada", "Bolivia", "jajas");
        grafo.insertarArcoDoble("Paraguay", "Chile", "jajas");
        grafo.insertarArcoDoble("Japon", "Chile", "jajas");
        grafo.insertarArcoDoble("China", "Chile", "jajas");

        grafo.insertarArcoDoble("China", "Japon", "jajas");

        System.out.println(grafo.toString());
        System.out.println(grafo.listarEnProfundidad().toString());

        grafo.eliminarArcoDoble("Peru", "Argentina");

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
