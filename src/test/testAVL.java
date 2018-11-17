package test;

import conjuntistas.ArbolAVL;

public class testAVL {

    public static void main(String[] args) {
        
        ArbolAVL avl = new ArbolAVL();
        
        avl.insertar("helado");
        avl.insertar("avion");
        avl.insertar("perro");
        avl.insertar(5);
                
        
        
        
        System.out.println(avl.toString());
        
        
        
        
        
        
        
    }
}
