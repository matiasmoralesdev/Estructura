package test;

import lineales.dinamicas.Lista;

public class testLista {

    public static void main(String[] args) {
        Lista lista = new Lista();
        System.out.println(lista.esVacia());
        
        
        lista.insertar("Hola");
        lista.insertar("Chau");
        lista.insertar("Perro");
        lista.insertar("Gato");
        lista.insertar("Caca");
        
        System.out.println(lista.toString());
        System.out.println(lista.clonar().toString());
    }

}
