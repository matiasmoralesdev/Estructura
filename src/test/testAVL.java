package test;

import conjuntistas.ArbolAVL;

public class testAVL {
    
    public static void main(String[] args) {
        
        ArbolAVL avl = new ArbolAVL();
        
        avl.insertar(10);
        avl.insertar(5);
        avl.insertar(15);
        avl.insertar(12);
        avl.insertar(17);
        avl.insertar(6);
        avl.insertar(13);
        avl.eliminar(6);
        
        System.out.println(avl.toString());
        
    }
}
