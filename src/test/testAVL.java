package test;

import conjuntistas.ArbolAVL;
import juego.Pais;

public class testAVL {
    
    public static void main(String[] args) {
        
        ArbolAVL avl = new ArbolAVL();
        
        Pais p1 = new Pais("Gato", 0);
        Pais p2 = new Pais("Arca", 0);
        Pais p3 = new Pais("Zona", 0);
        Pais p5 = new Pais("Zone", 0);
        Pais p6 = new Pais("Zoni", 0);
        Pais p7 = new Pais("Zono", 0);
        Pais p8 = new Pais("Zonu", 0);
        Pais p9 = new Pais("Zonus", 0);
        Pais p10 = new Pais("Zonux", 0);
        Pais p11 = new Pais("Zonuz", 0);
        
        avl.insertar(10);
        avl.insertar(9);
        avl.insertar(11);
        avl.insertar(12);
        avl.insertar(13);
        avl.insertar(14);
       // avl.insertar(12);
   //     avl.insertar(13);
  //      avl.insertar(p6);
//        avl.insertar(p7);
//        avl.insertar(p8);
//        avl.insertar(p9);
//        avl.insertar(p10);
//        avl.insertar(p11);
        
        System.out.println(avl.toString());
        System.out.println(avl.listar().toString());
        
    }
}
