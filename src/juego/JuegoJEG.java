package juego;

import conjuntistas.ArbolAVL;
import grafos.GrafoEtiquetado;
import java.util.HashMap;
import ventanas.Ventanas;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Random;
import lineales.dinamicas.Lista;

/**
 *
 * @author Matthew
 */
public class JuegoJEG {

    //Declaracion de Variables
    static GrafoEtiquetado mundoTEG = new GrafoEtiquetado();
    static Jugador[] jugadores = new Jugador[3];
    static HashMap relacion = new HashMap();
    static Lista listaPaises = new Lista();
    static String[] arrayStringPaises;
    static String errorMapaVacio = "NO HAY UN MAPA CREADO";
    static PrintWriter salida;

    public static void main(String[] args) throws FileNotFoundException {

        salida = new PrintWriter(new FileOutputStream("log.txt"));

        //Mecanismo del menu
        int opcion;
        boolean salir = false;
        do {
            opcion = mostrarMenu();
            switch (opcion) {
                case 0: //1. crearMapa()
                    iniciarMapa();
                    break;
                case 1: //3. atacar(pais1,pais2)
                    atacar();
                    break;
                case 2: //4. obtenerCantPaises(jugador)
                    obtenerCantPaises();
                    break;
                case 3: //5. obtenerVecinos(pais)
                    obtenerVecinos();
                    break;
                case 4: //6. crearPactor(pais1,pais2)
                    crearPacto();
                    break;
                case 5: //agregaFichas(pais,cantFichas)
                    agregarFichas();
                    break;
                case 6: //quitaFichas (pais,cantFichas)
                    quitarFichas();
                    break;
                case 7: //cantJugadasMinimas(pais1,pais2)
                    cantJugadasMinimasView();
                    break;
                case 8: //esPosibleLlegar(pais1,pais2,k)
                    esPosibleLlegarView();
                    break;
                case 9: //obtenerAtaquesConvenientes(jugador)
                    obtenerAtqConvenientesView();
                    break;
                case 10: //vaGanando()
                    vaGanandoView();
                    break;
                case 11: //cumplioObjetivo()
                    cumplioObjetivoView();
                    break;
                case 12: //mostrarEstado()
                    mostrarEstado();
                    break;
                case 13: //salir()
                    if (!mundoTEG.esVacio()) {
                        salir();
                    }
                    salir = true;
                    break;
                //default:
            }
        } while (!salir);

    }

    private static void salir() {
        Jugador actual;
        Lista listaPaises;
        String[] arreglo;
        int contador;
        Pais p;
        for (int i = 0; i < 3; i++) {
            contador = 0;
            actual = jugadores[i];

            salida.println();
            salida.println("> JUGADOR " + i + " : " + actual.getNombre());

            listaPaises = actual.getPaisesObtenidos().listar();

            salida.println("> Distribucion de paises: " + listaPaises.toString());

            arreglo = listaPaises.toArrayString();
            for (String pais : arreglo) {
                p = (Pais) mundoTEG.buscarEnProfundidad(pais);
                contador += p.cantEjercitos;
            }
            salida.println("> Cantidad total de ejercitos: " + contador);
            salida.println();
        }

        salida.close();
    }

    //MENUS DE SELECCION
    private static int mostrarMenu() {
        int opcionElegida = -1;
        String[] opciones = {"1. Crear Mapa", "2. Atacar", "3. Obtener Cantidad de Paises",
            "4. Obtener Vecinos", "5. Crear Pacto", "6. Agregar Fichas", "7. Quitar Fichas",
            "8. Cantidad de Jugadas Minimas", "9. ¿Es posible Llegar?", "10. Obtener Ataques Convenientes",
            "11. Va Ganando", "12. Cumplio Objetivo", "13. Mostrar Estado",
            "14. Salir"};
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
            opcionElegida = 13; // si eligio cancelar, significa que quiere salir
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
        opcion = Ventanas.pedirUnaOpcion("Jugadores", "Elija un jugador", opciones);
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

    private static String mostrarPaises() {
        String[] opciones = arrayStringPaises;
        String opcion;
        //mostrar el menu y leer la opcion
        opcion = Ventanas.pedirUnaOpcion("Lista de Paises", "Elija un Pais", opciones);
        if (opcion == null) {
            opcion = "";
        }
        return opcion;
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
        opcion = Ventanas.pedirUnaOpcion("Paises Limitrofes de" + pais, "Elija una opcion", opciones);
        if (opcion == null) {
            opcion = "";
        }
        return opcion;
    }

    private static int leerCantidadFichas(boolean tipo, int fichasActuales) {
        int cantidad = -1;
        String cantLeida;
        String mensaje;
        boolean exito = false;
        if (tipo) {
            mensaje = "¿Cuantas fichas desea agregar?(Actuales: " + fichasActuales + ")";
        } else {
            mensaje = "¿Cuantas fichas desea quitar?(Actuales: " + fichasActuales + ")";
        }
        do {
            // mostrar el menu y leer la opcion
            cantLeida = Ventanas.leerUnDato("Añadir Fichas", mensaje);
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

    private static int leerCantidadPasos() {
        int cantidad = -1;
        String cantLeida;
        boolean exito = false;
        do {
            // mostrar el menu y leer la opcion
            cantLeida = Ventanas.leerUnDato("Cantidad de Pasos", "Ingrese la cantidad de pasos:");
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

    //CREAR MAPA
    private static void iniciarMapa() {
        if (mundoTEG.esVacio()) {
            mundoTEG = crearMapa();
            crearJugadores();
            listaPaises = mundoTEG.listarVertices();
            asignarJugadores();
            Ventanas.mostrarMensaje("Mapa Creado", "El mapa se ha creado correctamente! ");
        } else {
            Ventanas.mostrarError("Ya existe un mapa creado!");
        }
    }

    private static String ingresarNombre(String porDefecto) {
        String retorno = "";
        do {
            retorno = Ventanas.leerUnDato("Nombre", "Ingrese su nombre: (Por Defecto: " + porDefecto + ")");
        } while (retorno == null);
        if (retorno.isEmpty()) {
            retorno = porDefecto;
        }
        return retorno;
    }

    private static void crearJugadores() {
        String j1, j2, j3;
        int dado1, dado2, dado3, pos1, pos2, pos3;
        j1 = ingresarNombre("Alfa");
        j2 = ingresarNombre("Beta");
        j3 = ingresarNombre("Gama");

        dado1 = new Random().nextInt(6) + 1;
        Ventanas.mostrarMensaje("Dados tirados", j1 + " ha sacado un " + dado1);

        dado2 = new Random().nextInt(6) + 1;
        while (dado2 == dado1) {
            Ventanas.mostrarError(j2 + " ha sacado un " + dado2 + " VUELVE A TIRAR");
            dado2 = new Random().nextInt(6) + 1;
        }
        Ventanas.mostrarMensaje("Dados tirados", j2 + " ha sacado un " + dado2);

        dado3 = new Random().nextInt(6) + 1;
        while (dado2 == dado1 || dado2 == dado1) {
            Ventanas.mostrarError(j3 + " ha sacado un " + dado3 + " VUELVE A TIRAR");
            dado2 = new Random().nextInt(6) + 1;
        }
        Ventanas.mostrarMensaje("Dados tirados", j3 + " ha sacado un " + dado3);

        if (dado1 > dado2 && dado1 > dado3) {
            pos1 = 0;

            if (dado2 > dado3) {
                pos2 = 1;
                pos3 = 2;
            } else {
                pos2 = 2;
                pos3 = 1;
            }

        } else if (((dado1 > dado2) && (dado1 < dado3)) || ((dado1 < dado2) && (dado1 > dado3))) {
            pos1 = 1;
            if (dado2 > dado3) {
                pos2 = 0;
                pos3 = 2;
            } else {
                pos3 = 0;
                pos2 = 2;
            }
        } else {
            pos1 = 2;
            if (dado2 > dado3) {
                pos2 = 0;
                pos3 = 1;
            } else {
                pos2 = 1;
                pos3 = 0;
            }

        }

        jugadores[pos1] = new Jugador(j1);
        jugadores[pos2] = new Jugador(j2);
        jugadores[pos3] = new Jugador(j3);
        String cadena = "";
        for (int i = 0; i < jugadores.length; i++) {
            cadena += (i + 1) + "° Jugador: " + jugadores[i].getNombre() + "\n";
        }
        Ventanas.mostrarMensaje("Orden de jugadas", "El orden de jugadores es: \n" + cadena);
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

    private static void asignarJugadores() {
        int jug = 0;
        for (int i = 1; i <= listaPaises.longitud(); i++) {
            asignarPais(jug, (Pais) listaPaises.recuperar(i));
            jug++;
            if (jug >= 3) {
                jug = 0;
            }
        }
    }

    public static boolean asignarPais(int jugador, Pais pais) {
        boolean exito = false;
        if (!relacion.containsKey(pais)) {
            pais.setFichas(3);
            jugadores[jugador].getPaisesObtenidos().insertar(pais);
            relacion.put(pais.getNombre(), jugadores[jugador]);
            exito = true;
        }
        return exito;
    }

    //ATACAR
    private static void atacar() {
        String p1, p2;
        Pais pais1, pais2;
        int dado1, dado2;
        Jugador j1, j2;
        if (!mundoTEG.esVacio()) {
            p1 = mostrarPaises();
            pais1 = (Pais) mundoTEG.buscarEnProfundidad(p1);
            j1 = (Jugador) relacion.get(pais1);
            if (!p1.isEmpty()) {
                if (pais1.getFichas() > 1) {
                    p2 = mostrarPaisesLimitrofes(p1);
                    pais2 = (Pais) mundoTEG.buscarEnProfundidad(p2);
                    j2 = (Jugador) relacion.get(pais2);
                    if (!p2.isEmpty()) {
                        if (!j1.getNombre().equals(j2.getNombre())) {
                            dado1 = new Random().nextInt(6) + 1;
                            dado2 = new Random().nextInt(6) + 1;
                            Ventanas.mostrarMensaje("DADOS", "JUGADOR " + j1.getNombre() + " - DADO: " + dado1
                                    + "\nJUGADOR " + j2.getNombre() + " - DADO: " + dado2);
                            if (dado1 > dado2) {
                                pais2.quitarFicha(1);
                                Ventanas.mostrarMensaje("ATAQUE", "El pais" + p1 + " ataco con exito a " + p2);

                                //Salida a archivo
                                salida.println("# El Pais " + pais1.getNombre() + " perteneciente a " + j1.getNombre() + " ATACO A"
                                        + pais2.getNombre() + " perteneciente a " + j2.getNombre() + "con resultado EXITOSO");

                                if (pais2.getFichas() == 0) {
                                    relacion.replace(pais2.getNombre(), j1);
                                    pais2.sumarFicha(1);
                                    j1.getPaisesObtenidos().insertar(pais2);

                                    //Salida a archivo
                                    salida.println("% El jugador" + j1.getNombre() + " Conquisto " + pais2.getNombre());
                                }
                            } else {
                                pais1.quitarFicha(1);
                                Ventanas.mostrarMensaje("ATAQUE", "El pais" + p1 + " fallo el ataque a " + p2);
                                //Salida a archivo
                                salida.println("# El Pais " + pais1.getNombre() + " perteneciente a " + j1.getNombre() + " ATACO A"
                                        + pais2.getNombre() + " perteneciente a " + j2.getNombre() + "con resultado FALIIDO");

                            }
                        } else {
                            Ventanas.mostrarError("No se puede atacar un pais propio");
                        }
                    }
                } else {
                    Ventanas.mostrarError("El pais no tiene fichas suficientes para atacar");
                }
            }
        } else {
            Ventanas.mostrarError(errorMapaVacio);
        }

    }

    //OBTENER CANTIDAD DE PAISES
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

    //OBTENER VECINOS
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

    //CREAR PACTO
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

    //AGREGAR FICHAS
    private static void agregarFichas() {
        String pais;
        Pais p;
        int cantidad;
        if (!mundoTEG.esVacio()) {
            pais = mostrarPaises();
            if (!pais.isEmpty()) {
                p = (Pais) mundoTEG.buscarEnProfundidad(pais);
                cantidad = leerCantidadFichas(true, p.getFichas());
                p.sumarFicha(cantidad);
                Ventanas.mostrarMensaje("EXITO", "Se han añadido las fichas correctamente\n"
                        + p.toString() + " tiene ahora " + p.getFichas() + " fichas");
            }
        } else {
            Ventanas.mostrarError(errorMapaVacio);
        }
    }

    // QUITAR FICHAS
    private static void quitarFichas() {
        String pais;
        Pais p;
        int cantidad;
        if (!mundoTEG.esVacio()) {
            pais = mostrarPaises();
            if (!pais.isEmpty()) {
                p = (Pais) mundoTEG.buscarEnProfundidad(pais);
                cantidad = leerCantidadFichas(false, p.getFichas());
                p.quitarFicha(cantidad);
                Ventanas.mostrarMensaje("EXITO", "Se han eliminado las fichas correctamente\n"
                        + p.toString() + " tiene ahora " + p.getFichas() + " fichas");
            }
        } else {
            Ventanas.mostrarError(errorMapaVacio);
        }
    }

    //CANTIDAD DE JUGADAS MINIMAS
    private static void cantJugadasMinimasView() {

        String p1, p2;
        Pais pais1, pais2;
        if (!mundoTEG.esVacio()) {
            p1 = mostrarPaises();
            if (!p1.isEmpty()) {
                p2 = mostrarPaises();
                if (!p2.isEmpty()) {
                    //pais1 = (Pais) mundoTEG.
                    System.out.println("Entro aca");
                    int respuesta = cantJugadasMinimas(p1, p2);
                    Ventanas.mostrarMensaje("Minimas Jugadas", "La cantidad minimas de jugadas necesarias es: " + respuesta);
                }
            }
        } else {
            Ventanas.mostrarError(errorMapaVacio);
        }
    }

    public static int cantJugadasMinimas(String pais1, String pais2) {
        Lista camino;
        int respuesta;
        camino = mundoTEG.caminoMasCorto(pais1, pais2);
        respuesta = camino.longitud();
        return respuesta;
    }

    //ES POSIBLE LLEGAR
    private static void esPosibleLlegarView() {
        String p1, p2;
        int k, respuesta;
        Pais pais1, pais2;
        if (!mundoTEG.esVacio()) {
            p1 = mostrarPaises();
            if (!p1.isEmpty()) {
                p2 = mostrarPaises();
                if (!p2.isEmpty()) {
                    k = leerCantidadPasos();
                    respuesta = cantJugadasMinimas(p1, p2);
                    if (respuesta > k) {
                        Ventanas.mostrarError("No es posible llegar en " + k + " pasos");
                    } else {
                        Ventanas.mostrarMensaje("Exito", "Es posible llegar en " + k + " pasos");
                    }
                }
            }

        } else {
            Ventanas.mostrarError(errorMapaVacio);
        }
    }

    //OBTENER ATAQUES CONVENIENTES
    private static void obtenerAtqConvenientesView() {
        if (!mundoTEG.esVacio()) {
            int jugador = mostrarJugadores();
            if (jugador != -1) {
                Jugador j1 = jugadores[jugador];
                Lista atq = obtenerAtqConvenientes(j1);
                String s = "";
                if (!atq.esVacia()) {
                    for (int i = 1; i <= atq.longitud(); i++) {
                        s += atq.recuperar(i) + "\n";
                    }
                    Ventanas.mostrarMensaje("Ataques Posibles", "Los ataques convenientes de " + j1.getNombre() + " son: \n" + s);

                } else {
                    Ventanas.mostrarError("El jugador " + j1.getNombre() + " no le conviene atacar a ningun pais");
                }
            }
        } else {
            Ventanas.mostrarError(errorMapaVacio);
        }
    }

    public static Lista obtenerAtqConvenientes(Jugador j1) {
        Lista atqConv = new Lista();
        Lista lista = j1.getPaisesObtenidos().listar();
        for (int i = 1; i <= lista.longitud(); i++) {

            Pais p = (Pais) lista.recuperar(i);
            p = (Pais) mundoTEG.buscarEnProfundidad(p.getNombre());

            int fichas = p.getFichas();
            Lista adyacentes = mundoTEG.listarArcos(p);
            for (int j = 1; j <= adyacentes.longitud(); j++) {

                Pais ady = (Pais) adyacentes.recuperar(j);
                ady = (Pais) mundoTEG.buscarEnProfundidad(ady.getNombre());

                if (relacion.containsKey(ady)) {
                    Jugador dueño = (Jugador) relacion.get(ady);
                    if (!dueño.getNombre().equals(j1.getNombre())) {
                        if (fichas > ady.getFichas()) {
                            atqConv.insertar(p.getNombre() + " >> " + ady.getNombre());
                        }
                    }
                }
            }
        }
        return atqConv;
    }

    //VA GANANDO
    private static void vaGanandoView() {
        if (!mundoTEG.esVacio()) {
            Ventanas.mostrarMensaje("Va Ganando", "Va ganando el jugador: " + vaGanando().getNombre());
        } else {
            Ventanas.mostrarError(errorMapaVacio);
        }
    }

    public static Jugador vaGanando() {
        int mayor = 0, longActual;
        Jugador ganador = null;
        for (Jugador jugador : jugadores) {
            longActual = jugador.getPaisesObtenidos().size();
            if (longActual > mayor) {
                mayor = longActual;
                ganador = jugador;
            }
        }
        return ganador;
    }

    //CUMPLIO OBJETIVO
    private static void cumplioObjetivoView() {
        Jugador ganador;
        if (!mundoTEG.esVacio()) {
            ganador = cumplioObjetivo();
            if (ganador != null) {
                Ventanas.mostrarMensaje("GANADOR", "El jugador " + ganador + " ha ganado");
            } else {
                Ventanas.mostrarMensaje("GANADOR", "Aun no hay ganador");
            }
        } else {
            Ventanas.mostrarError(errorMapaVacio);
        }
    }

    public static Jugador cumplioObjetivo() {
        Jugador ganador = null;
        for (Jugador jugador : jugadores) {
            if (jugador.getPaisesObtenidos().size() >= 25) {
                ganador = jugador;
            }
        }
        return ganador;
    }

    //MOSTRAR ESTADO
    private static void mostrarEstado() {
        if (!mundoTEG.esVacio()) {
            Ventanas.mostrarTexto("MUNDO TEG", "Distribucion del Mundo:\n" + mundoTEG.toString(), 50, 25);
            for (int i = 0; i < jugadores.length; i++) {
                Ventanas.mostrarTexto("AVL DE JUGADOR " + (i + 1) + ": " + jugadores[i].getNombre(), jugadores[i].getPaisesObtenidos().toString(), 50, 25);
            }
            Ventanas.mostrarTexto("MAPEO ", relacion.toString(), 50, 25);
        } else {
            Ventanas.mostrarError(errorMapaVacio);
        }
    }

}
