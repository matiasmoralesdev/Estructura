package juego;

import ventanas.Ventanas;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import lineales.dinamicas.Lista;

/**
 *
 * @author Matthew
 */
public class JuegoJEG {

    //Declaracion de Variables
    static JEG juego = new JEG();
    static String errorMapaVacio = "NO HAY UN MAPA CREADO";
    static PrintWriter salida;

    public static void main(String[] args) throws FileNotFoundException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        salida = new PrintWriter(new FileOutputStream("logs/log" + timeStamp + ".txt"));

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
                    obtenerCantPaisesView();
                    break;
                case 3: //5. obtenerVecinos(pais)
                    obtenerVecinosView();
                    break;
                case 4: //6. crearPactor(pais1,pais2)
                    crearPactoView();
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
                    if (juego.mapaCreado()) {
                        guardarInfo();
                    }
                    salir = true;
                    salida.close();
                    break;
                //default:
            }
        } while (!salir);

    }

    private static void guardarInfo() {
        Jugador actual;
        Lista listaPaises;
        int contador;
        Pais p;
        for (int i = 0; i < juego.cantidadDeJugadores(); i++) {
            contador = 0;
            actual = juego.getJugador(i);
            salida.println();
            salida.println("> JUGADOR " + (i + 1) + " : " + actual.getNombre());
            listaPaises = actual.getPaisesObtenidos().listar();
            salida.print("> Distribucion de paises: ");
            for (int j = 1; j <= listaPaises.longitud(); j++) {
                p = (Pais) listaPaises.recuperar(j);
                salida.print(p.getNombre() + "(" + p.getFichas() + ") - ");
                contador += p.getFichas();
            }
            salida.println();
            salida.println("> Cantidad total de ejercitos: " + contador);
            salida.println();
        }
    }

    //MENUS DE SELECCION
    private static int mostrarMenu() {
        int opcionElegida;
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
        int opcionElegida;
        int cantidadJugadores = juego.cantidadDeJugadores();
        String[] opciones = new String[cantidadJugadores];
        for (int i = 0; i < cantidadJugadores; i++) {
            opciones[i] = "JUGADOR " + (i + 1) + " : " + juego.getJugador(i).toString();
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
            opcionElegida = 100; // si eligio cancelar, significa que quiere salir
        }
        return opcionElegida;

    }

    private static String mostrarPaises() {
        String opcion;
        //mostrar el menu y leer la opcion
        opcion = Ventanas.pedirUnaOpcion("Lista de Paises", "Elija un Pais", juego.listadoPaises());
        if (opcion == null) {
            opcion = "";
        }
        return opcion;
    }

    private static String mostrarPaisesLimitrofes(String pais) {
        String[] opciones = juego.obtenerVecinos(pais).toArrayString();
        String opcion;
        //mostrar el menu y leer la opcion
        opcion = Ventanas.pedirUnaOpcion("Paises Limitrofes de " + pais, "Elija una opcion", opciones);
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
                } catch (NumberFormatException e) {
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
                } catch (NumberFormatException e) {
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
        if (!juego.mapaCreado()) {
            crearJugadores();
            juego.crearMapa();
            salida.println("#################################");
            salida.println("ESTADO INICIAL");
            salida.println("#################################");
            guardarInfo();
            salida.println("#################################");
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
        while (dado3 == dado1 || dado3 == dado2) {
            Ventanas.mostrarError(j3 + " ha sacado un " + dado3 + " VUELVE A TIRAR");
            dado3 = new Random().nextInt(6) + 1;
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
        juego.crearJugador(pos1, new Jugador(j1));
        juego.crearJugador(pos2, new Jugador(j2));
        juego.crearJugador(pos3, new Jugador(j3));

        Ventanas.mostrarMensaje("Orden de jugadas", "El orden de jugadores es: \n" + juego.listadoJugadores());
    }

    //ATACAR
    private static void atacar() {
        String p1, p2, informe;
        if (juego.mapaCreado()) {
            p1 = mostrarPaises();
            if (!p1.isEmpty()) {
                //Si elige la opcion CANCELAR vuelve al menu principal
                p2 = mostrarPaisesLimitrofes(p1);
                if (!p2.isEmpty()) {
                    //Si elige la opcion CANCELAR vuelve al menu principal
                    informe = juego.atacar(p1, p2);
                    Ventanas.mostrarMensaje("RESULTADO DEL ATAQUE", informe);
                    salida.println("---------------------------------------------------------------------------");
                    salida.println(informe);
                    salida.println("---------------------------------------------------------------------------");
                }
            }
        } else {
            Ventanas.mostrarError(errorMapaVacio);
        }
    }
    //OBTENER CANTIDAD DE PAISES

    private static void obtenerCantPaisesView() {
        int respuesta;
        if (juego.mapaCreado()) {
            int jugadorElegido = mostrarJugadores();
            if (jugadorElegido > -1 && jugadorElegido < 3) {
                respuesta = juego.obtenerCantPaises(jugadorElegido);
                Ventanas.mostrarMensaje("Cantidad de paises",
                        "El jugador " + juego.getJugador(jugadorElegido).getNombre() + " tiene " + respuesta + " paises");
            }
        } else {
            Ventanas.mostrarError(errorMapaVacio);
        }
    }

    //OBTENER VECINOS
    private static void obtenerVecinosView() {
        if (juego.mapaCreado()) {
            String pais = mostrarPaises();
            if (!pais.isEmpty()) {
                Ventanas.mostrarMensaje("Paises Vecinos", juego.obtenerVecinos(pais).toString());
            }
        } else {
            Ventanas.mostrarError(errorMapaVacio);
        }
    }

    //CREAR PACTO
    private static void crearPactoView() {
        String pais1, pais2;
        if (juego.mapaCreado()) {
            pais1 = mostrarPaises();
            if (!pais1.isEmpty()) {
                pais2 = mostrarPaisesLimitrofes(pais1);
                if (!pais2.isEmpty()) {
                    if (juego.crearPacto(pais1, pais2)) {
                        Ventanas.mostrarMensaje("EXITO", "Se ha eliminado la conexion entre " + pais1 + " y " + pais2);
                    }
                }
            }
        } else {
            Ventanas.mostrarError(errorMapaVacio);
        }
    }

    //AGREGAR Y ELIMINAR FICHAS
    private static void modificarFichas(boolean valor) {
        //Si valor es "true" el metodo agregara fichas
        //Si valor es "false" el metodo quitara fichas
        String pais, palabra;
        Pais p;
        int cantidad;
        if (juego.mapaCreado()) {
            pais = mostrarPaises();
            if (!pais.isEmpty()) {
                //Si elige la opcion CANCELAR vuelve al menu principal
                p = juego.obtenerPais(pais);
                cantidad = leerCantidadFichas(valor, p.getFichas());
                if (valor) {
                    p.sumarFicha(cantidad);
                    palabra = "añadido";
                } else {
                    p.quitarFicha(cantidad);
                    palabra = "eliminado";
                }
                Ventanas.mostrarMensaje("EXITO", "Se han " + palabra + " las fichas correctamente\n"
                        + p.getNombre() + " tiene ahora " + p.getFichas() + " fichas");
            }
        } else {
            Ventanas.mostrarError(errorMapaVacio);
        }
    }

    //CANTIDAD DE JUGADAS MINIMAS
    private static void cantJugadasMinimasView() {
        String p1, p2;
        int respuesta;
        if (juego.mapaCreado()) {
            p1 = mostrarPaises();
            if (!p1.isEmpty()) {
                //Si elige la opcion cancelar vuelve al menu principal
                p2 = mostrarPaises();
                if (!p2.isEmpty()) {
                    //Si elige la opcion cancelar vuelve al menu principal
                    if (p2.equals(p1)) {
                        Ventanas.mostrarError("Debe elegir un pais diferente");
                    } else {
                        respuesta = juego.cantJugadasMinimas(p1, p2);
                        Ventanas.mostrarMensaje("Jugadas Minimas", "La cantidad minimas de jugadas necesarias es: " + respuesta);
                    }
                }
            }
        } else {
            Ventanas.mostrarError(errorMapaVacio);
        }
    }

    //ES POSIBLE LLEGAR
    private static void esPosibleLlegarView() {
        String p1, p2;
        int k;
        if (juego.mapaCreado()) {
            p1 = mostrarPaises();
            if (!p1.isEmpty()) {
                //Si elige la opcion CANCELAR vuelve al menu principal
                p2 = mostrarPaises();
                if (!p2.isEmpty()) {
                    //Si elige la opcion CANCELAR vuelve al menu principal
                    if (p2.equals(p1)) {
                        Ventanas.mostrarError("Debe elegir un pais diferente");
                    } else {
                        k = leerCantidadPasos();
                        if (juego.sePuedeLlegarEn(p1, p2, k)) {
                            Ventanas.mostrarMensaje("Exito", "Es posible llegar en " + k + " pasos");
                        } else {
                            Ventanas.mostrarError("No es posible llegar en " + k + " pasos");
                        }
                    }
                }
            }

        } else {
            Ventanas.mostrarError(errorMapaVacio);
        }
    }

    //OBTENER ATAQUES CONVENIENTES
    private static void obtenerAtqConvenientesView() {
        if (juego.mapaCreado()) {
            int jugador = mostrarJugadores();
            if (jugador != -1) {
                //Si elige la opcion CANCELAR vuelve al menu principal
                Jugador j1 = juego.getJugador(jugador);
                Lista atq = juego.obtenerAtqConvenientes(j1);
                if (!atq.esVacia()) {
                    Ventanas.mostrarMensaje("Ataques Posibles", "Los ataques convenientes de " + j1.getNombre() + " son: \n" + atq.aCadena());
                } else {
                    Ventanas.mostrarError("El jugador " + j1.getNombre() + " no le conviene atacar a ningun pais");
                }
            }
        } else {
            Ventanas.mostrarError(errorMapaVacio);
        }
    }

    //VA GANANDO
    private static void vaGanandoView() {
        if (juego.mapaCreado()) {
            Ventanas.mostrarMensaje("Va Ganando", "Va ganando el jugador: " + juego.vaGanando().getNombre());
        } else {
            Ventanas.mostrarError(errorMapaVacio);
        }
    }

    //CUMPLIO OBJETIVO
    private static void cumplioObjetivoView() {
        Jugador ganador;
        if (juego.mapaCreado()) {
            ganador = juego.cumplioObjetivo();
            if (ganador != null) {
                Ventanas.mostrarMensaje("GANADOR", "El jugador " + ganador.getNombre() + " ha ganado");
            } else {
                Ventanas.mostrarMensaje("GANADOR", "Aun no hay ganador");
            }
        } else {
            Ventanas.mostrarError(errorMapaVacio);
        }
    }

    //MOSTRAR ESTADO
    private static void mostrarEstado() {
        if (juego.mapaCreado()) {
            Ventanas.mostrarTexto("MUNDO TEG", "Distribucion del Mundo:\n\n" + juego.mostrarMundo(), 50, 25);
            for (int i = 0; i < juego.cantidadDeJugadores(); i++) {
                Ventanas.mostrarTexto("AVL DE JUGADOR " + (i + 1),
                        juego.datosDeJugador(i), 50, 25);
            }
            Ventanas.mostrarTexto("MAPEO ", juego.obtenerRelacion(), 50, 10);
        } else {
            Ventanas.mostrarError(errorMapaVacio);
        }
    }

}
