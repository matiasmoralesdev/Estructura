package juego;

import grafos.GrafoEtiquetado;
import java.util.HashMap;
import ventanas.Ventanas;
import conjuntistas.ArbolAVL;

public class JuegoJEG {

    public static void main(String[] args) {

        //Declaracion de Variables
        GrafoEtiquetado mundoTEG = new GrafoEtiquetado();
        ArbolAVL[] jugadores = new ArbolAVL[3];
        int turno = 0;
        
        
        
        //Mecanismo del menu
        int opcion;
        do {
            opcion = mostrarMenu();
            switch (opcion) {
                case 0:
                    if (mundoTEG.esVacio()) {
                        mundoTEG = crearMapa();

                    } else {
                        Ventanas.mostrarError("Ya existe un mapa creado!");
                    }
                    break;
                case 1:
                    if (!mundoTEG.esVacio()) {

                    } else {
                        Ventanas.mostrarError("No hay mapa creado");
                    }
                    break;
                //case 2: mostrarTodos();
                // break;
            }

        } while (opcion != 14);

    }

    private static int mostrarMenu() {
        int opcionElegida = -1;
        String[] opciones = {"1. Crear Mapa", "2. Asignar Pais", "3. Atacar", "4. Obtener Cantidad de Paises",
            "5. Obtener Vecinos","6. Crear Pacto", "7. Agregar Fichas", "8. Quitar Fichas",
            "9. Cantidad de Jugadas Minimas", "10. Es posible Llegar?", "11. Obtener Ataques Convenientes", 
            "12. Va Ganando", "13. Cumplio Objetivo", "14. Mostrar Estado",
            "15. Salir"};
        String opcion;
        //mostrar el menu y leer la opcion
        opcion = Ventanas.pedirUnaOpcion("Menu Principal", "Elija una opcion", opciones);
        if (opcion != null) {
            int i = 0;
            boolean encontrado = false;

            while (!encontrado && i < opciones.length) {
                if (opcion.equals(opciones[i])) {
                    encontrado = true;
                } else {
                    i++;
                }
            }
            opcionElegida = i; // opcion segun orden en el arreglo
        } else {
            opcionElegida = 14; // si eligio cancelar, significa que quiere salir
        }
        return opcionElegida;

    }

    private static GrafoEtiquetado crearMapa() {
        GrafoEtiquetado mundo = new GrafoEtiquetado();

        mundo.insertarVertice(new Pais("ARGENTINA"));
        mundo.insertarVertice(new Pais("CHILE"));
        mundo.insertarVertice(new Pais("URUGUAY"));
        mundo.insertarVertice(new Pais("BRASIL"));
        mundo.insertarVertice(new Pais("PERU"));
        mundo.insertarVertice(new Pais("COLOMBIA"));
        mundo.insertarVertice(new Pais("MEXICO"));
        mundo.insertarVertice(new Pais("CALIFORNIA"));
        mundo.insertarVertice(new Pais("CANADA"));
        mundo.insertarVertice(new Pais("OREGON"));
        mundo.insertarVertice(new Pais("YUKON"));
        mundo.insertarVertice(new Pais("ALASKA"));
        mundo.insertarVertice(new Pais("NUEVA YORK"));
        mundo.insertarVertice(new Pais("TERRANOVA"));
        mundo.insertarVertice(new Pais("LABRADOR"));
        mundo.insertarVertice(new Pais("GROENLANDIA"));
        mundo.insertarVertice(new Pais("ISLANDIA"));
        mundo.insertarVertice(new Pais("GRAN BRETAÑA"));
        mundo.insertarVertice(new Pais("ESPAÑA"));
        mundo.insertarVertice(new Pais("FRANCIA"));
        mundo.insertarVertice(new Pais("ALEMANIA"));
        mundo.insertarVertice(new Pais("ITALIA"));
        mundo.insertarVertice(new Pais("POLONIA"));
        mundo.insertarVertice(new Pais("RUSIA"));
        mundo.insertarVertice(new Pais("SUECIA"));
        mundo.insertarVertice(new Pais("IRAN"));
        mundo.insertarVertice(new Pais("TURQUIA"));
        mundo.insertarVertice(new Pais("ISRAEL"));
        mundo.insertarVertice(new Pais("ARABIA"));
        mundo.insertarVertice(new Pais("ARAL"));
        mundo.insertarVertice(new Pais("TARTARIA"));
        mundo.insertarVertice(new Pais("TAMIR"));
        mundo.insertarVertice(new Pais("SIBERIA"));
        mundo.insertarVertice(new Pais("MONGOLIA"));
        mundo.insertarVertice(new Pais("GOBI"));
        mundo.insertarVertice(new Pais("CHINA"));
        mundo.insertarVertice(new Pais("MALASIA"));
        mundo.insertarVertice(new Pais("JAPON"));
        mundo.insertarVertice(new Pais("KAMCHATKA"));
        mundo.insertarVertice(new Pais("INDIA"));
        mundo.insertarVertice(new Pais("SUMATRA"));
        mundo.insertarVertice(new Pais("BORNEO"));
        mundo.insertarVertice(new Pais("JAVA"));
        mundo.insertarVertice(new Pais("AUSTRALIA"));
        mundo.insertarVertice(new Pais("SAHARA"));
        mundo.insertarVertice(new Pais("EGIPTO"));
        mundo.insertarVertice(new Pais("ETIOPIA"));
        mundo.insertarVertice(new Pais("ZAIRE"));
        mundo.insertarVertice(new Pais("SUDAFRICA"));
        mundo.insertarVertice(new Pais("MADAGASCAR"));

        mundo.insertarArcoDoble("ARGENTINA", "CHILE", "FRONTERA");
        mundo.insertarArcoDoble("ARGENTINA", "URUGUAY", "FRONTERA");
        mundo.insertarArcoDoble("ARGENTINA", "BRASIL", "FRONTERA");
        mundo.insertarArcoDoble("ARGENTINA", "PERU", "FRONTERA");
        mundo.insertarArcoDoble("CHILE", "PERU", "FRONTERA");
        mundo.insertarArcoDoble("CHILE", "AUSTRALIA", "PUENTE");
        mundo.insertarArcoDoble("PERU", "COLOMBIA", "FRONTERA");
        mundo.insertarArcoDoble("PERU", "BRASIL", "FRONTERA");
        mundo.insertarArcoDoble("URUGUAY", "BRASIL", "FRONTERA");
        mundo.insertarArcoDoble("BRASIL", "COLOMBIA", "FRONTERA");
        mundo.insertarArcoDoble("BRASIL", "SAHARA", "PUENTE");
        mundo.insertarArcoDoble("COLOMBIA", "MEXICO", "FRONTERA");
        mundo.insertarArcoDoble("MEXICO", "CALIFORNIA", "FRONTERA");
        mundo.insertarArcoDoble("CALIFORNIA", "OREGON", "FRONTERA");
        mundo.insertarArcoDoble("CALIFORNIA", "NUEVA YORK", "FRONTERA");
        mundo.insertarArcoDoble("OREGON", "ALASKA", "FRONTERA");
        mundo.insertarArcoDoble("OREGON", "YUKON", "FRONTERA");
        mundo.insertarArcoDoble("OREGON", "CANADA", "FRONTERA");
        mundo.insertarArcoDoble("OREGON", "NUEVA YORK", "FRONTERA");
        mundo.insertarArcoDoble("ALASKA", "YUKON", "FRONTERA");
        mundo.insertarArcoDoble("ALASKA", "KAMCHATKA", "PUENTE");
        mundo.insertarArcoDoble("YUKON", "CANADA", "FRONTERA");
        mundo.insertarArcoDoble("CANADA", "NUEVA YORK", "FRONTERA");
        mundo.insertarArcoDoble("NUEVA YORK", "TERRANOVA", "FRONTERA");
        mundo.insertarArcoDoble("NUEVA YORK", "GROENLANDIA", "PUENTE");
        mundo.insertarArcoDoble("TERRANOVA", "LABRADOR", "FRONTERA");
        mundo.insertarArcoDoble("LABRADOR", "GROENLANDIA", "PUENTE");
        mundo.insertarArcoDoble("GROENLANDIA", "ISLANDIA", "PUENTE");
        mundo.insertarArcoDoble("ISLANDIA", "SUECIA", "PUENTE");
        mundo.insertarArcoDoble("ISLANDIA", "GRAN BRETAÑA", "PUENTE");
        mundo.insertarArcoDoble("GRAN BRETAÑA", "ESPAÑA", "PUENTE");
        mundo.insertarArcoDoble("GRAN BRETAÑA", "ALEMANIA", "PUENTE");
        mundo.insertarArcoDoble("ESPAÑA", "SAHARA", "PUENTE");
        mundo.insertarArcoDoble("ESPAÑA", "FRANCIA", "FRONTERA");
        mundo.insertarArcoDoble("FRANCIA", "ITALIA", "FRONTERA");
        mundo.insertarArcoDoble("FRANCIA", "ALEMANIA", "FRONTERA");
        mundo.insertarArcoDoble("ALEMANIA", "POLONIA", "FRONTERA");
        mundo.insertarArcoDoble("POLONIA", "RUSIA", "FRONTERA");
        mundo.insertarArcoDoble("POLONIA", "EGIPTO", "PUENTE");
        mundo.insertarArcoDoble("RUSIA", "SUECIA", "FRONTERA");
        mundo.insertarArcoDoble("RUSIA", "ARAL", "FRONTERA");
        mundo.insertarArcoDoble("RUSIA", "IRAN", "FRONTERA");
        mundo.insertarArcoDoble("RUSIA", "TURQUIA", "FRONTERA");
        mundo.insertarArcoDoble("ARAL", "TARTARIA", "FRONTERA");
        mundo.insertarArcoDoble("ARAL", "SIBERIA", "FRONTERA");
        mundo.insertarArcoDoble("ARAL", "IRAN", "FRONTERA");
        mundo.insertarArcoDoble("ARAL", "MONGOLIA", "FRONTERA");
        mundo.insertarArcoDoble("TARTARIA", "TAMIR", "FRONTERA");
        mundo.insertarArcoDoble("TARTARIA", "SIBERIA", "FRONTERA");
        mundo.insertarArcoDoble("TAMIR", "SIBERIA", "FRONTERA");
        mundo.insertarArcoDoble("SIBERIA", "KAMCHATKA", "FRONTERA");
        mundo.insertarArcoDoble("SIBERIA", "MONGOLIA", "FRONTERA");
        mundo.insertarArcoDoble("SIBERIA", "CHINA", "FRONTERA");
        mundo.insertarArcoDoble("KAMCHATKA", "CHINA", "FRONTERA");
        mundo.insertarArcoDoble("KAMCHATKA", "JAPON", "PUENTE");
        mundo.insertarArcoDoble("CHINA", "JAPON", "PUENTE");
        mundo.insertarArcoDoble("CHINA", "GOBI", "FRONTERA");
        mundo.insertarArcoDoble("CHINA", "MALASIA", "FRONTERA");
        mundo.insertarArcoDoble("CHINA", "INDIA", "FRONTERA");
        mundo.insertarArcoDoble("CHINA", "MONGOLIA", "FRONTERA");
        mundo.insertarArcoDoble("CHINA", "IRAN", "FRONTERA");
        mundo.insertarArcoDoble("MONGOLIA", "GOBI", "FRONTERA");
        mundo.insertarArcoDoble("IRAN", "GOBI", "FRONTERA");
        mundo.insertarArcoDoble("IRAN", "TURQUIA", "FRONTERA");
        mundo.insertarArcoDoble("TURQUIA", "ISRAEL", "FRONTERA");
        mundo.insertarArcoDoble("TURQUIA", "ARABIA", "FRONTERA");
        mundo.insertarArcoDoble("TURQUIA", "EGIPTO", "PUENTE");
        mundo.insertarArcoDoble("ISRAEL", "EGIPTO", "PUENTE");
        mundo.insertarArcoDoble("ISRAEL", "ARABIA", "FRONTERA");
        mundo.insertarArcoDoble("INDIA", "SUMATRA", "PUENTE");
        mundo.insertarArcoDoble("MALASIA", "BORNEO", "PUENTE");
        mundo.insertarArcoDoble("SUMATRA", "AUSTRALIA", "PUENTE");
        mundo.insertarArcoDoble("BORNEO", "AUSTRALIA", "PUENTE");
        mundo.insertarArcoDoble("JAVA", "AUSTRALIA", "PUENTE");
        mundo.insertarArcoDoble("EGIPTO", "ETIOPIA", "FRONTERA");
        mundo.insertarArcoDoble("EGIPTO", "SAHARA", "FRONTERA");
        mundo.insertarArcoDoble("EGIPTO", "MADAGASCAR", "PUENTE");
        mundo.insertarArcoDoble("ETIOPIA", "SAHARA", "FRONTERA");
        mundo.insertarArcoDoble("ETIOPIA", "ZAIRE", "FRONTERA");
        mundo.insertarArcoDoble("ETIOPIA", "SUDAFRICA", "FRONTERA");
        mundo.insertarArcoDoble("ZAIRE", "SAHARA", "FRONTERA");
        mundo.insertarArcoDoble("ZAIRE", "SUDAFRICA", "FRONTERA");
        mundo.insertarArcoDoble("ZAIRE", "MADAGASCAR", "PUENTE");

        return mundo;
    }

}
