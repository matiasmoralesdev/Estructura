package test;

import conjuntistas.ArbolAVL;

public class testAVL {

    public static void main(String[] args) {
        
        ArbolAVL avl = new ArbolAVL();
        
        avl.insertar(12);
        avl.insertar(5);
        avl.insertar(23);
        avl.insertar(3);
        avl.insertar(8);
        avl.insertar(10);
        
        System.out.println(avl.toString());
        
        
        
        
        
        
        
    }
}
