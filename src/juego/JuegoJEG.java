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
    static boolean mapaCreado = false;

    public static void main(String[] args) throws FileNotFoundException {

        salida = new PrintWriter(new FileOutputStream("log.txt"));

        //Mecanismo del menu
        int opcion;
        boolean salir = false;
        do {
            opcion = mostrarMenu();
            switch (opcion) {
                case 0: //1. crearMapa() //2.AsignarPais(jugador,pais)
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
                    modificarFichas(true);
                    break;
                case 6: //quitaFichas (pais,cantFichas)
                    modificarFichas(false);
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
                    if (mapaCreado) {
                        salir();
                    }
                    salir = true;
                    salida.close();
                    break;
                //default:
            }
        } while (!salir);

    }

    private static void salir() {
        Jugador actual;
        Lista listaPaises;
        int contador;
        Pais p;
        for (int i = 0; i < jugadores.length; i++) {
            contador = 0;
            actual = jugadores[i];
            salida.println();
            salida.println("> JUGADOR " + i + " : " + actual.getNombre());
            listaPaises = actual.getPaisesObtenidos().listar();
            salida.println("> Distribucion de paises: " + listaPaises.toString());
            for (int j = 1; j < listaPaises.longitud(); j++) {
                p = (Pais) listaPaises.recuperar(j);
                contador += p.getFichas();
            }
            salida.println("> Cantidad total de ejercitos: " + contador);
            salida.println();
        }

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
        if (!mapaCreado) {
            crearMapa();
            crearJugadores();
            asignarJugadores();
            mapaCreado = true;
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

    private static void crearMapa() {
        ArbolAVL arbolOrden = new ArbolAVL();

        Pais argentina = new Pais("ARGENTINA");
        Pais chile = new Pais("CHILE");
        Pais uruguay = new Pais("URUGUAY");
        Pais brasil = new Pais("BRASIL");
        Pais peru = new Pais("PERU");
        Pais colombia = new Pais("COLOMBIA");
        Pais mexico = new Pais("MEXICO");
        Pais california = new Pais("CALIFORNA");
        Pais oregon = new Pais("OREGON");
        Pais yukon = new Pais("YUKON");
        Pais alaska = new Pais("ALASKA");
        Pais nuevaYork = new Pais("NUEVA YORK");
        Pais terranova = new Pais("TERRANOVA");
        Pais labrador = new Pais("LABRADOR");
        Pais groenlandia = new Pais("GROENLANDIA");
        Pais islandia = new Pais("ISLANDIA");
        Pais granBretania = new Pais("GRAN BRETAÑA");
        Pais espania = new Pais("ESPAÑA");
        Pais francia = new Pais("FRANCIA");
        Pais alemania = new Pais("ALEMANIA");
        Pais italia = new Pais("ITALIA");
        Pais polonia = new Pais("POLONIA");
        Pais rusia = new Pais("RUSIA");
        Pais suecia = new Pais("SUECIA");
        Pais iran = new Pais("IRAN");
        Pais turquia = new Pais("TURQUIA");
        Pais israel = new Pais("ISRAEL");
        Pais arabia = new Pais("ARABIA");
        Pais aral = new Pais("ARAL");
        Pais tartaria = new Pais("TARTARIA");
        Pais tamir = new Pais("TAMIR");
        Pais siberia = new Pais("SIBERIA");
        Pais mongolia = new Pais("MONGOLIA");
        Pais gobi = new Pais("GOBI");
        Pais china = new Pais("CHINA");
        Pais malasia = new Pais("MALASIA");
        Pais japon = new Pais("JAPON");
        Pais kamchatka = new Pais("KAMCHATKA");
        Pais india = new Pais("INDIA");
        Pais sumatra = new Pais("SUMATRA");
        Pais borneo = new Pais("BORNEO");
        Pais java = new Pais("JAVA");
        Pais australia = new Pais("AUSTRALIA");
        Pais sahara = new Pais("SAHARA");
        Pais egipto = new Pais("EGIPTO");
        Pais etiopia = new Pais("ETIOPIA");
        Pais zaire = new Pais("ZAIRE");
        Pais sudafrica = new Pais("SUDAFRICA");
        Pais madagascar = new Pais("MADAGASCAR");
        Pais canada = new Pais("CANADA");

        mundoTEG.insertarVertice(argentina);
        mundoTEG.insertarVertice(chile);
        mundoTEG.insertarVertice(uruguay);
        mundoTEG.insertarVertice(brasil);
        mundoTEG.insertarVertice(peru);
        mundoTEG.insertarVertice(colombia);
        mundoTEG.insertarVertice(mexico);
        mundoTEG.insertarVertice(california);
        mundoTEG.insertarVertice(canada);
        mundoTEG.insertarVertice(oregon);
        mundoTEG.insertarVertice(yukon);
        mundoTEG.insertarVertice(alaska);
        mundoTEG.insertarVertice(nuevaYork);
        mundoTEG.insertarVertice(terranova);
        mundoTEG.insertarVertice(labrador);
        mundoTEG.insertarVertice(groenlandia);
        mundoTEG.insertarVertice(islandia);
        mundoTEG.insertarVertice(granBretania);
        mundoTEG.insertarVertice(espania);
        mundoTEG.insertarVertice(francia);
        mundoTEG.insertarVertice(alemania);
        mundoTEG.insertarVertice(italia);
        mundoTEG.insertarVertice(polonia);
        mundoTEG.insertarVertice(rusia);
        mundoTEG.insertarVertice(suecia);
        mundoTEG.insertarVertice(iran);
        mundoTEG.insertarVertice(turquia);
        mundoTEG.insertarVertice(israel);
        mundoTEG.insertarVertice(arabia);
        mundoTEG.insertarVertice(aral);
        mundoTEG.insertarVertice(tartaria);
        mundoTEG.insertarVertice(tamir);
        mundoTEG.insertarVertice(siberia);
        mundoTEG.insertarVertice(mongolia);
        mundoTEG.insertarVertice(gobi);
        mundoTEG.insertarVertice(china);
        mundoTEG.insertarVertice(malasia);
        mundoTEG.insertarVertice(japon);
        mundoTEG.insertarVertice(kamchatka);
        mundoTEG.insertarVertice(india);
        mundoTEG.insertarVertice(sumatra);
        mundoTEG.insertarVertice(borneo);
        mundoTEG.insertarVertice(java);
        mundoTEG.insertarVertice(australia);
        mundoTEG.insertarVertice(sahara);
        mundoTEG.insertarVertice(egipto);
        mundoTEG.insertarVertice(etiopia);
        mundoTEG.insertarVertice(zaire);
        mundoTEG.insertarVertice(sudafrica);
        mundoTEG.insertarVertice(madagascar);

        arbolOrden.insertar(argentina);
        arbolOrden.insertar(chile);
        arbolOrden.insertar(uruguay);
        arbolOrden.insertar(brasil);
        arbolOrden.insertar(peru);
        arbolOrden.insertar(colombia);
        arbolOrden.insertar(mexico);
        arbolOrden.insertar(california);
        arbolOrden.insertar(canada);
        arbolOrden.insertar(oregon);
        arbolOrden.insertar(yukon);
        arbolOrden.insertar(alaska);
        arbolOrden.insertar(nuevaYork);
        arbolOrden.insertar(terranova);
        arbolOrden.insertar(labrador);
        arbolOrden.insertar(groenlandia);
        arbolOrden.insertar(islandia);
        arbolOrden.insertar(granBretania);
        arbolOrden.insertar(espania);
        arbolOrden.insertar(francia);
        arbolOrden.insertar(alemania);
        arbolOrden.insertar(italia);
        arbolOrden.insertar(polonia);
        arbolOrden.insertar(rusia);
        arbolOrden.insertar(suecia);
        arbolOrden.insertar(iran);
        arbolOrden.insertar(turquia);
        arbolOrden.insertar(israel);
        arbolOrden.insertar(arabia);
        arbolOrden.insertar(aral);
        arbolOrden.insertar(tartaria);
        arbolOrden.insertar(tamir);
        arbolOrden.insertar(siberia);
        arbolOrden.insertar(mongolia);
        arbolOrden.insertar(gobi);
        arbolOrden.insertar(china);
        arbolOrden.insertar(malasia);
        arbolOrden.insertar(japon);
        arbolOrden.insertar(kamchatka);
        arbolOrden.insertar(india);
        arbolOrden.insertar(sumatra);
        arbolOrden.insertar(borneo);
        arbolOrden.insertar(java);
        arbolOrden.insertar(australia);
        arbolOrden.insertar(sahara);
        arbolOrden.insertar(egipto);
        arbolOrden.insertar(etiopia);
        arbolOrden.insertar(zaire);
        arbolOrden.insertar(sudafrica);
        arbolOrden.insertar(madagascar);

        mundoTEG.insertarArcoDoble("ARGENTINA", "CHILE", "FRONTERA");
        mundoTEG.insertarArcoDoble("ARGENTINA", "URUGUAY", "FRONTERA");
        mundoTEG.insertarArcoDoble("ARGENTINA", "BRASIL", "FRONTERA");
        mundoTEG.insertarArcoDoble("ARGENTINA", "PERU", "FRONTERA");
        mundoTEG.insertarArcoDoble("CHILE", "PERU", "FRONTERA");
        mundoTEG.insertarArcoDoble("CHILE", "AUSTRALIA", "PUENTE");
        mundoTEG.insertarArcoDoble("PERU", "COLOMBIA", "FRONTERA");
        mundoTEG.insertarArcoDoble("PERU", "BRASIL", "FRONTERA");
        mundoTEG.insertarArcoDoble("URUGUAY", "BRASIL", "FRONTERA");
        mundoTEG.insertarArcoDoble("BRASIL", "COLOMBIA", "FRONTERA");
        mundoTEG.insertarArcoDoble("BRASIL", "SAHARA", "PUENTE");
        mundoTEG.insertarArcoDoble("COLOMBIA", "MEXICO", "FRONTERA");
        mundoTEG.insertarArcoDoble("MEXICO", "CALIFORNIA", "FRONTERA");
        mundoTEG.insertarArcoDoble("CALIFORNIA", "OREGON", "FRONTERA");
        mundoTEG.insertarArcoDoble("CALIFORNIA", "NUEVA YORK", "FRONTERA");
        mundoTEG.insertarArcoDoble("OREGON", "ALASKA", "FRONTERA");
        mundoTEG.insertarArcoDoble("OREGON", "YUKON", "FRONTERA");
        mundoTEG.insertarArcoDoble("OREGON", "CANADA", "FRONTERA");
        mundoTEG.insertarArcoDoble("OREGON", "NUEVA YORK", "FRONTERA");
        mundoTEG.insertarArcoDoble("ALASKA", "YUKON", "FRONTERA");
        mundoTEG.insertarArcoDoble("ALASKA", "KAMCHATKA", "PUENTE");
        mundoTEG.insertarArcoDoble("YUKON", "CANADA", "FRONTERA");
        mundoTEG.insertarArcoDoble("CANADA", "NUEVA YORK", "FRONTERA");
        mundoTEG.insertarArcoDoble("NUEVA YORK", "TERRANOVA", "FRONTERA");
        mundoTEG.insertarArcoDoble("NUEVA YORK", "GROENLANDIA", "PUENTE");
        mundoTEG.insertarArcoDoble("TERRANOVA", "LABRADOR", "FRONTERA");
        mundoTEG.insertarArcoDoble("LABRADOR", "GROENLANDIA", "PUENTE");
        mundoTEG.insertarArcoDoble("GROENLANDIA", "ISLANDIA", "PUENTE");
        mundoTEG.insertarArcoDoble("ISLANDIA", "SUECIA", "PUENTE");
        mundoTEG.insertarArcoDoble("ISLANDIA", "GRAN BRETAÑA", "PUENTE");
        mundoTEG.insertarArcoDoble("GRAN BRETAÑA", "ESPAÑA", "PUENTE");
        mundoTEG.insertarArcoDoble("GRAN BRETAÑA", "ALEMANIA", "PUENTE");
        mundoTEG.insertarArcoDoble("ESPAÑA", "SAHARA", "PUENTE");
        mundoTEG.insertarArcoDoble("ESPAÑA", "FRANCIA", "FRONTERA");
        mundoTEG.insertarArcoDoble("FRANCIA", "ITALIA", "FRONTERA");
        mundoTEG.insertarArcoDoble("FRANCIA", "ALEMANIA", "FRONTERA");
        mundoTEG.insertarArcoDoble("ITALIA", "ALEMANIA", "FRONTERA");
        mundoTEG.insertarArcoDoble("ALEMANIA", "POLONIA", "FRONTERA");
        mundoTEG.insertarArcoDoble("POLONIA", "RUSIA", "FRONTERA");
        mundoTEG.insertarArcoDoble("POLONIA", "EGIPTO", "PUENTE");
        mundoTEG.insertarArcoDoble("RUSIA", "SUECIA", "FRONTERA");
        mundoTEG.insertarArcoDoble("RUSIA", "ARAL", "FRONTERA");
        mundoTEG.insertarArcoDoble("RUSIA", "IRAN", "FRONTERA");
        mundoTEG.insertarArcoDoble("RUSIA", "TURQUIA", "FRONTERA");
        mundoTEG.insertarArcoDoble("ARAL", "TARTARIA", "FRONTERA");
        mundoTEG.insertarArcoDoble("ARAL", "SIBERIA", "FRONTERA");
        mundoTEG.insertarArcoDoble("ARAL", "IRAN", "FRONTERA");
        mundoTEG.insertarArcoDoble("ARAL", "MONGOLIA", "FRONTERA");
        mundoTEG.insertarArcoDoble("TARTARIA", "TAMIR", "FRONTERA");
        mundoTEG.insertarArcoDoble("TARTARIA", "SIBERIA", "FRONTERA");
        mundoTEG.insertarArcoDoble("TAMIR", "SIBERIA", "FRONTERA");
        mundoTEG.insertarArcoDoble("SIBERIA", "KAMCHATKA", "FRONTERA");
        mundoTEG.insertarArcoDoble("SIBERIA", "MONGOLIA", "FRONTERA");
        mundoTEG.insertarArcoDoble("SIBERIA", "CHINA", "FRONTERA");
        mundoTEG.insertarArcoDoble("KAMCHATKA", "CHINA", "FRONTERA");
        mundoTEG.insertarArcoDoble("KAMCHATKA", "JAPON", "PUENTE");
        mundoTEG.insertarArcoDoble("CHINA", "JAPON", "PUENTE");
        mundoTEG.insertarArcoDoble("CHINA", "GOBI", "FRONTERA");
        mundoTEG.insertarArcoDoble("CHINA", "MALASIA", "FRONTERA");
        mundoTEG.insertarArcoDoble("CHINA", "INDIA", "FRONTERA");
        mundoTEG.insertarArcoDoble("CHINA", "MONGOLIA", "FRONTERA");
        mundoTEG.insertarArcoDoble("CHINA", "IRAN", "FRONTERA");
        mundoTEG.insertarArcoDoble("MONGOLIA", "GOBI", "FRONTERA");
        mundoTEG.insertarArcoDoble("IRAN", "GOBI", "FRONTERA");
        mundoTEG.insertarArcoDoble("IRAN", "TURQUIA", "FRONTERA");
        mundoTEG.insertarArcoDoble("TURQUIA", "ISRAEL", "FRONTERA");
        mundoTEG.insertarArcoDoble("TURQUIA", "ARABIA", "FRONTERA");
        mundoTEG.insertarArcoDoble("TURQUIA", "EGIPTO", "PUENTE");
        mundoTEG.insertarArcoDoble("ISRAEL", "EGIPTO", "PUENTE");
        mundoTEG.insertarArcoDoble("ISRAEL", "ARABIA", "FRONTERA");
        mundoTEG.insertarArcoDoble("INDIA", "SUMATRA", "PUENTE");
        mundoTEG.insertarArcoDoble("MALASIA", "BORNEO", "PUENTE");
        mundoTEG.insertarArcoDoble("SUMATRA", "AUSTRALIA", "PUENTE");
        mundoTEG.insertarArcoDoble("BORNEO", "AUSTRALIA", "PUENTE");
        mundoTEG.insertarArcoDoble("JAVA", "AUSTRALIA", "PUENTE");
        mundoTEG.insertarArcoDoble("EGIPTO", "ETIOPIA", "FRONTERA");
        mundoTEG.insertarArcoDoble("EGIPTO", "SAHARA", "FRONTERA");
        mundoTEG.insertarArcoDoble("EGIPTO", "MADAGASCAR", "PUENTE");
        mundoTEG.insertarArcoDoble("ETIOPIA", "SAHARA", "FRONTERA");
        mundoTEG.insertarArcoDoble("ETIOPIA", "ZAIRE", "FRONTERA");
        mundoTEG.insertarArcoDoble("ETIOPIA", "SUDAFRICA", "FRONTERA");
        mundoTEG.insertarArcoDoble("ZAIRE", "SAHARA", "FRONTERA");
        mundoTEG.insertarArcoDoble("ZAIRE", "SUDAFRICA", "FRONTERA");
        mundoTEG.insertarArcoDoble("ZAIRE", "MADAGASCAR", "PUENTE");

        listaPaises = arbolOrden.listar();
        arrayStringPaises = listaPaises.toArrayString();

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
        if (!relacion.containsKey(pais.getNombre())) {
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
        Jugador jugador1, jugador2;
        if (mapaCreado) {
            p1 = mostrarPaises();
            if (!p1.isEmpty()) {
                jugador1 = (Jugador) relacion.get(p1);
                pais1 = (Pais) jugador1.getPaisesObtenidos().recuperar(p1);
                if (pais1.getFichas() > 1) {
                    p2 = mostrarPaisesLimitrofes(p1);

                    if (!p2.isEmpty()) {
                        jugador2 = (Jugador) relacion.get(p2);
                        pais2 = (Pais) jugador2.getPaisesObtenidos().recuperar(p2);

                        if (!jugador1.getNombre().equals(jugador2.getNombre())) {
                            dado1 = new Random().nextInt(6) + 1;
                            dado2 = new Random().nextInt(6) + 1;
                            Ventanas.mostrarMensaje("DADOS", "JUGADOR " + jugador1.getNombre() + " - DADO: " + dado1
                                    + "\nJUGADOR " + jugador2.getNombre() + " - DADO: " + dado2);
                            if (dado1 > dado2) {
                                pais2.quitarFicha(1);
                                Ventanas.mostrarMensaje("ATAQUE", "El pais" + p1 + " ataco con exito a " + p2);

                                //Salida a archivo
                                salida.println("# El Pais " + pais1.getNombre() + " perteneciente a " + jugador1.getNombre() + " ATACO A"
                                        + pais2.getNombre() + " perteneciente a " + jugador2.getNombre() + "con resultado EXITOSO");
                                //

                                if (pais2.getFichas() == 0) {
                                    relacion.replace(pais2.getNombre(), jugador1);
                                    pais2.sumarFicha(1);
                                    jugador1.getPaisesObtenidos().insertar(pais2);
                                    jugador2.getPaisesObtenidos().eliminar(pais2);
                                    Ventanas.mostrarMensaje("CONQUISTA", "EL JUGADOR " + jugador1.getNombre() + " CONQUISTO " + pais2.getNombre());

                                    //Salida a archivo
                                    salida.println("% El jugador" + jugador1.getNombre() + " Conquisto " + pais2.getNombre());
                                    //

                                }
                            } else {
                                pais1.quitarFicha(1);
                                Ventanas.mostrarMensaje("ATAQUE", "El pais" + p1 + " fallo el ataque a " + p2);
                                //Salida a archivo
                                salida.println("# El Pais " + pais1.getNombre() + " perteneciente a " + jugador1.getNombre() + " ATACO A " 
                                        + pais2.getNombre() + " perteneciente a " + jugador2.getNombre() + " con resultado FALIIDO");

                                if (pais1.getFichas() == 0) {
                                    relacion.replace(pais1.getNombre(), jugador2);
                                    pais2.sumarFicha(1);
                                    jugador2.getPaisesObtenidos().insertar(pais1);
                                    jugador1.getPaisesObtenidos().eliminar(pais1);

                                    Ventanas.mostrarMensaje("PERDIDA", "EL JUGADOR " + jugador1.getNombre() + " PERDIO " + pais1.getNombre());

                                    //Salida a archivo
                                    salida.println("% El jugador" + jugador1.getNombre() + " Conquisto " + pais2.getNombre());
                                    //
                                }
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
        if (mapaCreado) {
            int jugadorElegido = mostrarJugadores();
            if (jugadorElegido > -1 && jugadorElegido < 3) {
                respuesta = jugadores[jugadorElegido].getPaisesObtenidos().size();
                Ventanas.mostrarMensaje("Cantidad de paises",
                        "El jugador " + jugadores[jugadorElegido].getNombre() + " tiene " + respuesta + " paises");
            }
        } else {
            Ventanas.mostrarError(errorMapaVacio);
        }
    }

    //OBTENER VECINOS
    private static void obtenerVecinos() {
        if (mapaCreado) {
            String pais = mostrarPaises();
            if (!pais.isEmpty()) {
                Ventanas.mostrarMensaje("Paises Vecinos", mundoTEG.listarArcos(pais).toString());
            }
        } else {
            Ventanas.mostrarError(errorMapaVacio);
        }
    }

    //CREAR PACTO
    private static void crearPacto() {
        String pais1, pais2;
        if (mapaCreado) {
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

    //AGREGAR Y ELIMINAR FICHAS
    private static void modificarFichas(boolean valor) {
        String pais, palabra;
        Pais p;
        int cantidad;
        if (mapaCreado) {
            pais = mostrarPaises();
            if (!pais.isEmpty()) {
                p = obtenerPais(pais);
                cantidad = leerCantidadFichas(valor, p.getFichas());
                if (valor) {
                    p.sumarFicha(cantidad);
                    palabra = "añadido";
                } else {
                    p.quitarFicha(cantidad);
                    palabra = "eliminado";
                }
                Ventanas.mostrarMensaje("EXITO", "Se han " + palabra + " las fichas correctamente\n"
                        + p.toString() + " tiene ahora " + p.getFichas() + " fichas");
            }
        } else {
            Ventanas.mostrarError(errorMapaVacio);
        }
    }

    private static Pais obtenerPais(String p) {
        int i = 0;
        boolean encontrado = false;
        Pais pais = null;
        while (i < jugadores.length && !encontrado) {
            pais = (Pais) jugadores[i].getPaisesObtenidos().recuperar(pais);
            if (pais != null) {
                encontrado = true;
            }
            i++;
        }
        return pais;
    }

    //CANTIDAD DE JUGADAS MINIMAS
    private static void cantJugadasMinimasView() {
        String p1, p2;
        Pais pais1, pais2;
        if (mapaCreado) {
            p1 = mostrarPaises();
            if (!p1.isEmpty()) {
                p2 = mostrarPaises();
                if (!p2.isEmpty()) {
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
        camino = mundoTEG.caminoMasCorto(pais1, pais2);
        return camino.longitud() - 1;
    }

    //ES POSIBLE LLEGAR
    private static void esPosibleLlegarView() {
        String p1, p2;
        int k, respuesta;
        Pais pais1, pais2;
        if (mapaCreado) {
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
        if (mapaCreado) {
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
            int fichas = p.getFichas();
            Lista adyacentes = mundoTEG.listarArcos(p);
            for (int j = 1; j <= adyacentes.longitud(); j++) {
                Pais ady = (Pais) adyacentes.recuperar(j);
                if (relacion.containsKey(ady.getNombre())) {
                    Jugador dueño = (Jugador) relacion.get(ady.getNombre());
                    if (!dueño.getNombre().equals(j1.getNombre())) {
                        if (fichas > ady.getFichas()) {
                            atqConv.insertarInicio(p.getNombre() + " >> " + ady.getNombre());
                        }
                    }
                }
            }
        }
        return atqConv;
    }

    //VA GANANDO
    private static void vaGanandoView() {
        if (mapaCreado) {
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
        if (mapaCreado) {
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
        if (mapaCreado) {
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
