package juego;

import conjuntistas.ArbolAVL;
import grafos.GrafoEtiquetado;
import java.util.HashMap;
import ventanas.Ventanas;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 *
 * @author Matthew
 */
public class JuegoJEG {
    //Declaracion de Variables

    static GrafoEtiquetado mundoTEG = new GrafoEtiquetado();

    static Jugador jugador1 = new Jugador("Alfa");
    static Jugador jugador2 = new Jugador("Beta");
    static Jugador jugador3 = new Jugador("Gama");
    static Jugador[] jugadores = {jugador1, jugador2, jugador3};

    static HashMap relacion = new HashMap();

    static String errorMapaVacio = "NO HAY UN MAPA CREADO";

    public static void main(String[] args) throws FileNotFoundException {

        PrintWriter salida = new PrintWriter(new FileOutputStream("log.txt"));
        salida.print("jaja");
        salida.close();

        String datoEntrada = "";

        //Mecanismo del menu
        int opcion;
        do {
            opcion = mostrarMenu();
            switch (opcion) {
                case 0: //1. crearMapa()
                    iniciarMapa();
                    break;
                case 1: //2. asignarPais(jugador,pais)
                    asignarPais();
                    break;
                case 2: //3. atacar(pais1,pais2)
                    atacar();
                case 3: //4. obtenerCantPaises(jugador)
                    obtenerCantPaises();
                    break;
                case 4: //5. obtenerVecinos(pais)
                    obtenerVecinos();
                    break;
                case 5: //6. crearPactor(pais1,pais2)
                    crearPacto();
                    break;
                case 6: //agregaFichas(pais,cantFichas)
                    agregarFichas();
                    break;
                case 7: //quitaFichas (pais,cantFichas)
                    quitarFichas();
                    break;
                case 8: //cantJugadasMinimas(pais1,pais2)
                    cantJugadasMinimas();
                    break;
                case 9: //esPosibleLlegar(pais1,pais2,k)
                    esPosibleLlegar();
                    break;
                case 10: //obtenerAtaquesConvenientes(jugador)
                    obtenerAtqConvenientes();
                    break;
                case 11: //vaGanando()
                    vaGanando();
                    break;
                case 12: //cumplioObjetivo()
                    cumplioObjetivo();
                    break;
                case 13: //mostrarEstado()
                    mostrarEstado();
                    break;
            }

        } while (opcion != 14);

    }

    private static int mostrarMenu() {
        int opcionElegida = -1;
        String[] opciones = {"1. Crear Mapa", "2. Asignar Pais", "3. Atacar", "4. Obtener Cantidad de Paises",
            "5. Obtener Vecinos", "6. Crear Pacto", "7. Agregar Fichas", "8. Quitar Fichas",
            "9. Cantidad de Jugadas Minimas", "10. ¿Es posible Llegar?", "11. Obtener Ataques Convenientes",
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

    private static int mostrarJugadores() {
        int opcionElegida = -1;
        String[] opciones = new String[jugadores.length];
        for (int i = 0; i < jugadores.length; i++) {
            opciones[i] = "JUGADOR " + (i + 1) + " : " + jugadores[i].getNombre().toString();
        }

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
            opcionElegida = 3; // si eligio cancelar, significa que quiere salir
        }
        return opcionElegida;

    }

    private static String mostrarPaises2() {
        String[] opciones = mundoTEG.listarEnProfundidad().toArrayString();
        String opcion;
        //mostrar el menu y leer la opcion
        opcion = Ventanas.pedirUnaOpcion("Menu Principal", "Elija una opcion", opciones);
        if (opcion == null) {
            opcion = "";
        }
        return opcion;
    }

    private static String mostrarPaises() {
        String[] opciones = mundoTEG.listarEnProfundidad().toArrayString();
        ArbolAVL orden = new ArbolAVL();

        for (String option : opciones) {
            orden.insertar(option);
        }

        opciones = orden.listar().toArrayString();
        String opcion;
        //mostrar el menu y leer la opcion
        opcion = Ventanas.pedirUnaOpcion("Menu Principal", "Elija una opcion", opciones);
        if (opcion == null) {
            opcion = "";
        }
        return opcion;
    }

    private static void asignarPais() {
        int jugadorSeleccionado;
        String paisSeleccionado;
        Pais p;
        if (!mundoTEG.esVacio()) {
            jugadorSeleccionado = mostrarJugadores();
            if (jugadorSeleccionado != -1) {
                paisSeleccionado = mostrarPaises();
                if (!paisSeleccionado.isEmpty()) {

                    p = (Pais) mundoTEG.recuperarElem(paisSeleccionado);

                    if (!relacion.containsKey(p)) {
                        p.setFichas(3);
                        jugadores[jugadorSeleccionado].getPaisesObtenidos().insertar(p);
                        relacion.put(p, jugadores[jugadorSeleccionado].getNombre());
                        Ventanas.mostrarMensaje("EXITO",
                                "El jugador " + jugadores[jugadorSeleccionado].getNombre()
                                + " ha seleccionado el pais " + p.getNombre());
                    } else {
                        Ventanas.mostrarError("Un jugador ya posee este pais");
                    }

                }
            }

        } else {
            Ventanas.mostrarError(errorMapaVacio);
        }
    }

    public static void asignarPais(Jugador jugador, Pais pais) {
        pais.sumarFicha(3);
        jugador.getPaisesObtenidos().insertar(pais);
        System.out.println("");
    }

    private static void iniciarMapa() {
        if (mundoTEG.esVacio()) {
            mundoTEG = crearMapa();
            Ventanas.mostrarMensaje("Mapa Creado", "El mapa se ha creado correctamente! ");
        } else {
            Ventanas.mostrarError("Ya existe un mapa creado!");
        }
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
        mundo.insertarArcoDoble("ITALIA", "ALEMANIA", "FRONTERA");
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

    private static void obtenerVecinos() {
        if (!mundoTEG.esVacio()) {
            String paisesVecinos = mostrarPaises();
            if (!paisesVecinos.isEmpty()) {
                Ventanas.mostrarMensaje("Paises Vecinos", mundoTEG.listarArcos(paisesVecinos).toString());
            }
        } else {
            Ventanas.mostrarError(errorMapaVacio);
        }
    }

    private static void obtenerCantPaises() {
        int respuesta;
        if (!mundoTEG.esVacio()) {
            int jugadorElegido = mostrarJugadores();
            if (jugadorElegido > -1 && jugadorElegido < 3) {
                respuesta = jugadores[jugadorElegido].getPaisesObtenidos().listar().longitud();
                Ventanas.mostrarMensaje("Cantidad de paises",
                        "El jugador " + jugadores[jugadorElegido].getNombre() + " tiene " + respuesta + " paises");
            }
        } else {
            Ventanas.mostrarError(errorMapaVacio);
        }
    }

    private static void agregarFichas() {
        String pais;
        Pais p;
        int cantidad;
        if (!mundoTEG.esVacio()) {
            pais = mostrarPaises();
            if (!pais.isEmpty()) {
                p = (Pais) mundoTEG.recuperarElem(pais);
                cantidad = leerCantidadFichas(p.getFichas());
                p.sumarFicha(cantidad);
                Ventanas.mostrarMensaje("EXITO", "Se han añadido las fichas correctamente\n"
                        + p.toString() + " tiene ahora " + p.getFichas() + " fichas");
            }

        } else {
            Ventanas.mostrarError(errorMapaVacio);
        }
    }

    private static int leerCantidadFichas(int fichasActuales) {
        int cantidad = -1;
        String cantLeida;

        boolean exito = false;
        do {
            // mostrar el menu y leer la opcion
            cantLeida = Ventanas.leerUnDato("Añadir Fichas", "¿Cuantas fichas desea agregar?(Actuales: " + fichasActuales + ")");

            if (cantLeida != null) {
                // verificar si es una opcion valida del menu
                try {
                    cantidad = Integer.valueOf(cantLeida);
                    if (cantidad > 0) {
                        exito = true;
                    } else {
                        Ventanas.mostrarError("Debe ser una cantidad positiva.\n Intente de nuevo.");
                    }
                } catch (Exception e) {
                    Ventanas.mostrarError("Debe ingresar un numero.\n Intente de nuevo.");
                }
            } else {
                Ventanas.mostrarError("Debe ingresar un numero.\n Intente de nuevo.");
            }
        } while (!exito);

        return cantidad;
    }

    private static void quitarFichas() {
        String pais;
        Pais p;
        int cantidad;
        if (!mundoTEG.esVacio()) {
            pais = mostrarPaises();
            if (!pais.isEmpty()) {
                p = (Pais) mundoTEG.recuperarElem(pais);
                cantidad = leerCantidadFichas(p.getFichas());
                p.quitarFicha(cantidad);
                Ventanas.mostrarMensaje("EXITO", "Se han eliminado las fichas correctamente\n"
                        + p.toString() + " tiene ahora " + p.getFichas() + " fichas");
            }

        } else {
            Ventanas.mostrarError(errorMapaVacio);
        }
    }

    private static void mostrarEstado() {
        if (!mundoTEG.esVacio()) {
            Ventanas.mostrarTexto("MUNDO TEG", mundoTEG.toString(), 50, 25);
            for (int i = 0; i < jugadores.length; i++) {
                Ventanas.mostrarTexto("AVL DE JUGADOR " + (i + 1), jugadores[i].getPaisesObtenidos().toString(), 50, 25);
            }
            Ventanas.mostrarTexto("MAPEO ", relacion.toString(), 50, 25);

        } else {
            Ventanas.mostrarError(errorMapaVacio);
        }
    }

    private static void crearPacto() {
        String pais1, pais2;
        if (!mundoTEG.esVacio()) {
            pais1 = mostrarPaises();
            if (!pais1.isEmpty()) {
                pais2 = mostrarPaisesLimitrofes(pais1);
                if (!pais2.isEmpty()) {
                    mundoTEG.eliminarArcoDoble(pais1, pais2);
                    Ventanas.mostrarMensaje("EXITO", "Se ha eliminado la conexion entre " + pais1 + " y " + pais2);
                }
            }
        } else {
            Ventanas.mostrarError(errorMapaVacio);
        }
    }

    private static String mostrarPaisesLimitrofes(String pais) {
        String[] opciones = mundoTEG.listarArcos(pais).toArrayString();

        //
        ArbolAVL orden = new ArbolAVL();
        for (String option : opciones) {
            orden.insertar(option);
        }
        opciones = orden.listar().toArrayString();
        //

        String opcion;
        //mostrar el menu y leer la opcion
        opcion = Ventanas.pedirUnaOpcion("Menu Principal", "Elija una opcion", opciones);
        if (opcion == null) {
            opcion = "";
        }
        return opcion;
    }

    private static void cumplioObjetivo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void vaGanando() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void obtenerAtqConvenientes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void cantJugadasMinimas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void esPosibleLlegar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void atacar() {
        String p1, p2;
        Pais pais1, pais2;

        if (!mundoTEG.esVacio()) {
            p1 = mostrarPaises();
            if (!p1.isEmpty()) {

                p2 = mostrarPaises();
                if (!p2.isEmpty()) {

                    pais1 = (Pais) mundoTEG.recuperarElem(p1);
                    pais2 = (Pais) mundoTEG.recuperarElem(p2);

                }

            }

        } else {
            Ventanas.mostrarError(errorMapaVacio);
        }

    }

}
