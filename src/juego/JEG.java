package juego;

import conjuntistas.ArbolAVL;
import grafos.GrafoEtiquetado;
import java.util.HashMap;
import java.util.Random;
import lineales.dinamicas.Lista;

public class JEG {

    private final int CANTJUGADORES = 3;

    private GrafoEtiquetado mundoTEG = new GrafoEtiquetado();
    private final Jugador[] jugadores;
    private HashMap relacion = new HashMap();
    private String[] arrayStringPaises;
    private boolean mapaCreado;

    public JEG() {
        mundoTEG = new GrafoEtiquetado();
        jugadores = new Jugador[CANTJUGADORES];
        relacion = new HashMap();
        mapaCreado = false;
    }

    public void crearMapa() {
        ArbolAVL arbolOrden = new ArbolAVL();

        Pais argentina = new Pais("ARGENTINA");
        Pais chile = new Pais("CHILE");
        Pais uruguay = new Pais("URUGUAY");
        Pais brasil = new Pais("BRASIL");
        Pais peru = new Pais("PERU");
        Pais colombia = new Pais("COLOMBIA");
        Pais mexico = new Pais("MEXICO");
        Pais california = new Pais("CALIFORNIA");
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

        Lista listaPaises = arbolOrden.listar();
        asignarJugadores(listaPaises);
        arrayStringPaises = new String[listaPaises.longitud()];

        for (int i = 1; i <= listaPaises.longitud(); i++) {
            Pais p = (Pais) listaPaises.recuperar(i);
            arrayStringPaises[i - 1] = p.getNombre();
        }

        mapaCreado = true;
    }

    private void asignarJugadores(Lista listaPaises) {
        int jug = 0;
        for (int i = 1; i <= listaPaises.longitud(); i++) {
            asignarPais(jug, (Pais) listaPaises.recuperar(i));
            jug++;
            if (jug >= 3) {
                jug = 0;
            }
        }
    }

    public boolean asignarPais(int jugador, Pais pais) {
        boolean exito = false;
        if (!relacion.containsKey(pais.getNombre())) {
            pais.setFichas(3);
            jugadores[jugador].getPaisesObtenidos().insertar(pais);
            relacion.put(pais.getNombre(), jugadores[jugador]);
            exito = true;
        }
        return exito;
    }

    public void crearJugador(int posicion, Jugador jugador) {
        jugadores[posicion] = jugador;
    }

    public String listadoJugadores() {
        String cadena = "";
        for (int i = 0; i < jugadores.length; i++) {
            cadena += (i + 1) + "° Jugador: " + jugadores[i].getNombre() + "\n";
        }
        return cadena;
    }

    public Jugador obtenerDueñoDe(String pais) {
        return (Jugador) relacion.get(pais);
    }

    public Jugador getJugador(int turno) {
        return jugadores[turno];
    }

    public boolean mapaCreado() {
        return this.mapaCreado;
    }

    public int tirarDados() {
        return (new Random().nextInt(6) + 1);
    }

    public String obtenerRelacion() {
        return relacion.toString();
    }

    public String datosDeJugador(int pos) {

        String cadena = "";
        Jugador jugador = jugadores[pos];
        cadena += "--------------------------------------------------\n";
        cadena += "JUGADOR " + (pos + 1) + " : " + jugador.getNombre() + "\n";
        cadena += "--------------------------------------------------\n";
        cadena += "\n";
        cadena += "AVL DEL JUGADOR:  \n";
        cadena += jugador.getPaisesObtenidos().toString();

        return cadena;
    }

    public String mostrarMundo() {
        return mundoTEG.toString();
    }

    public int cantidadDeJugadores() {
        return this.CANTJUGADORES;
    }

    public String atacar(String pais1, String pais2) {
        String informe = "";
        int dado1, dado2;
        Jugador jugador1 = (Jugador) relacion.get(pais1);
        Jugador jugador2 = (Jugador) relacion.get(pais2);
        Pais p1 = (Pais) jugador1.getPaisesObtenidos().recuperar(pais1);
        Pais p2;
        if (p1.getFichas() > 1) {
            if (!jugador1.getNombre().equals(jugador2.getNombre())) {
                p2 = (Pais) jugador2.getPaisesObtenidos().recuperar(pais2);
                //Se tiran 2 dados
                dado1 = tirarDados();
                dado2 = tirarDados();
                informe += "DADO DE " + jugador1.getNombre() + " : " + dado1 + " - ";
                informe += "DADO DE " + jugador2.getNombre() + " : " + dado2 + "\n";
                if (dado1 > dado2) {
                    p2.quitarFicha(1);
                    informe += "El Pais " + p1.getNombre() + " perteneciente a " + jugador1.getNombre() + " ATACO A "
                            + p2.getNombre() + " perteneciente a " + jugador2.getNombre() + " con resultado EXITOSO\n";

                    if (p2.getFichas() == 0) {
                        //Si el pais atacado se queda sin fichas, el atacante lo conquista
                        relacion.replace(p2.getNombre(), jugador1);
                        p2.sumarFicha(1);
                        jugador1.getPaisesObtenidos().insertar(p2);
                        jugador2.getPaisesObtenidos().eliminar(p2);
                        informe += "EL JUGADOR " + jugador1.getNombre() + " CONQUISTO " + p2.getNombre();
                    }
                } else {
                    p1.quitarFicha(1);
                    informe += "El Pais " + p1.getNombre() + " perteneciente a " + jugador1.getNombre() + " ATACO A "
                            + p2.getNombre() + " perteneciente a " + jugador2.getNombre() + " con resultado FALLIDO\n";
                }

            } else {
                informe += "Ataque Fallido: \nNo se puede atacar a un pais propio";
            }
        } else {
            informe += "Ataque Fallido: \nEl pais " + pais1 + " no tiene suficiente fichas para atacar a " + pais2;
        }
        return informe;
    }

    public String[] listadoPaises() {
        return this.arrayStringPaises;
    }

    public int obtenerCantPaises(int jugador) {
        return jugadores[jugador].getPaisesObtenidos().size();
    }

    public Lista obtenerVecinos(String pais) {
        return mundoTEG.listarArcos(pais);
    }

    public boolean crearPacto(String pais1, String pais2) {
        return mundoTEG.eliminarArcoDoble(pais1, pais2);
    }

    public void agregarFichas(String pais, int cantFichas) {
        Pais p = obtenerPais(pais);
        p.sumarFicha(cantFichas);
    }

    public void quitarFichas(String pais, int cantFichas) {
        Pais p = obtenerPais(pais);
        p.quitarFicha(cantFichas);
    }

    public Pais obtenerPais(String p) {
        int i = 0;
        boolean encontrado = false;
        Pais pais = null;
        while (i < jugadores.length && !encontrado) {
            pais = (Pais) jugadores[i].getPaisesObtenidos().recuperar(p);
            if (pais != null) {
                encontrado = true;
            }
            i++;
        }
        return pais;
    }

    public int cantJugadasMinimas(String pais1, String pais2) {
        Lista camino;
        camino = mundoTEG.caminoMasCorto(pais1, pais2);
        return camino.longitud() - 1;
    }

    public boolean sePuedeLlegarEn(String pais1, String pais2, int k) {
        return mundoTEG.existeMasCorto(pais1, pais2, k);
    }

    public Lista obtenerAtqConvenientes(Jugador j1) {
        Lista atqConv = new Lista();
        int fichas;
        Pais ady;
        Jugador dueño;
        Lista lista = j1.getPaisesObtenidos().listar();
        for (int i = 1; i <= lista.longitud(); i++) {
            Pais p = (Pais) lista.recuperar(i);
            fichas = p.getFichas();
            Lista adyacentes = mundoTEG.listarArcos(p);
            for (int j = 1; j <= adyacentes.longitud(); j++) {
                ady = (Pais) adyacentes.recuperar(j);
                if (relacion.containsKey(ady.getNombre())) {
                    dueño = (Jugador) relacion.get(ady.getNombre());
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

    public Jugador vaGanando() {
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

    public Jugador cumplioObjetivo() {
        Jugador ganador = null;
        for (Jugador jugador : jugadores) {
            if (jugador.getPaisesObtenidos().size() >= 25) {
                ganador = jugador;
            }
        }
        return ganador;
    }

}
