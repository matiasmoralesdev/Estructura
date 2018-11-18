package test;

import lineales.dinamicas.Lista;

public class testLista {

    public static void main(String[] args) {
        Lista lista = new Lista();
        System.out.println(lista.esVacia());
        
        
        lista.insertar("Hola", 1);
        lista.insertar("Chau", 2);
        lista.insertar("jaja", 2);
        lista.insertar("asdasd", 4);
        lista.eliminar(5);
        
        System.out.println(lista.toString());
    }

}
