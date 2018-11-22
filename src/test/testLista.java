package test;

import lineales.dinamicas.Lista;

public class testLista {

    public static void main(String[] args) {
        Lista lista = new Lista();
        System.out.println(lista.esVacia());
        
        
        lista.insertarAlFinal("Hola");
        lista.insertarAlFinal("Chau");
        lista.insertarAlFinal("Perro");
        lista.insertarAlFinal("Gato");
        lista.insertarAlFinal("Caca");
        
        System.out.println(lista.toString());
    }

}
