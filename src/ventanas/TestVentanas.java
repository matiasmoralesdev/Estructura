package ventanas;


public class TestVentanas {

    static String[] pedidos = new String[10];
    static int cant = 0;
        
    public static void main (String[] arg){
     
        int op;

        do {
            op = mostrarMenu();
            switch (op) {
            case 0: cargarItem();
                    break;
            case 1: existeItem();
                    break;
            case 2: mostrarTodos();
                    break;
            }
        }while (op != 3);
    }

    /**
     * Muestra todos los elementos del vector
     * @param p es el vector de los pedidos
     */
    private static void mostrarTodos(){
        String mens = "";
        for (int i = 0; i < cant; i++){
            mens += pedidos[i] + "\n";
        }
        Ventanas.mostrarTexto("Mi compra", mens, 20, 10);
    }


    /**
     * Lee una cantidad numerica positiva
     * @return el valor leido y validado
     */
    private static int leerCantidadPedido(){
        int cantidad = -1;
        String cantLeida;

        boolean exito = false;
        do {
            // mostrar el menu y leer la opcion
             cantLeida= Ventanas.leerUnDato("Lista de compra", "¿Cuanto hay que comprar?");

            if (cantLeida != null) {
                // verificar si es una opcion valida del menu
                try{
                    cantidad = Integer.valueOf(cantLeida);
                    if (cantidad > 0)
                        exito = true;
                    else
                        Ventanas.mostrarError("Debe ser una cantidad positiva.\n Intente de nuevo.");
                }
                catch (Exception e){
                    Ventanas.mostrarError("Debe ingresar un numero.\n Intente de nuevo.");
                }
            }
            else {
                Ventanas.mostrarError("Debe ingresar un numero.\n Intente de nuevo.");
            }
        } while (!exito);

        return cantidad;
    }


    /** Lee un concepto. Si no esta cargado lo carga
     * Si esta repetido modifica la cantidad
     * @param p: es el vector con pedidos actuales
     */
    private static void cargarItem(){
        String concepto = Ventanas.leerUnDato("Lista de compra", "¿Que hay que comprar?");
        if (concepto != null){
            int cantidad = leerCantidadPedido();
            pedidos[cant] = concepto + ": " + cantidad;
            Ventanas.mostrarMensaje("Exito", "Item cargado con exito");
            cant++;
        }
    }



    /** Lee un concepto. Si no esta cargado lo carga
     * Si esta repetido modifica la cantidad
     * @param p: es el vector con pedidos actuales
     */
    private static void existeItem(){
        String concepto = Ventanas.leerUnDato("Lista de compra", "¿Que item quiere consultar?");
        if (concepto != null){
            int i=0;
            boolean encontrado=false;
            while (i < cant && !encontrado){
                if (pedidos[i].indexOf(concepto) >= 0){
                    encontrado = true;
                }
                else 
                    i++;
            }
            if (encontrado)
                Ventanas.mostrarMensaje("Exito", "El item existe");
            else {
                Ventanas.mostrarError("No se encontró el item");
            }
        }
    }



    /**
     * Muestra el menu de opciones y devuelve un valor validado
     * @return un valor entero que es una opcion valida del menu
     */
    private static int mostrarMenu(){
        int opcionElegida = -1;
        String[] opciones = {"Cargar pedido nuevo", "Ver pedido", "Mostrar todos los items cargados", "Salir"};
        String opcion;

        // mostrar el menu y leer la opcion
        opcion = Ventanas.pedirUnaOpcion("Menu", "Elija una opcion", opciones);
        if (opcion!=null) {
            int i = 0;
            boolean encontrado = false;

            while (!encontrado && i < opciones.length){
                if (opcion.equals(opciones[i]))
                    encontrado = true;
                else
                    i++;
            }
            opcionElegida = i; // opcion segun orden en el arreglo
        }
        else
            opcionElegida = 3; // si eligio cancelar, significa que quiere salir

        return opcionElegida;
    }
}
