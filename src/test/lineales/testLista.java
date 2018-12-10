package test.lineales;

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
        lista.insertar("Cacota");
        
        System.out.println(lista.toString());
        System.out.println(lista.clonar().toString());
        
        
        lista.eliminar(lista.longitud());
        System.out.println(lista.toString());        
    }

}
