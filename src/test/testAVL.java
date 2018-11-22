package test;

import conjuntistas.ArbolAVL;
import juego.Pais;

public class testAVL {
    
    public static void main(String[] args) {
        
        ArbolAVL avl = new ArbolAVL();
        
        Pais p1 = new Pais("Gato", 0);
        Pais p2 = new Pais("Arca", 0);
        Pais p3 = new Pais("Zona", 0);
        
        avl.insertar(p1);
        avl.insertar(p2);
        avl.insertar(p3);
        
        System.out.println(avl.toString());
        System.out.println(avl.listar().toString());
        
    }
}
