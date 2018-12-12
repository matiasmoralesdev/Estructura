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

    public String[] listadoPaises() {
        return this.arrayStringPaises;
    }

    /**
     * Se ingresan al sistema todos los paises y sus conexiones. Ademas guarda
     * en el arreglo un arreglo de paises existentes que se usara para
     * propositos graficos
     */
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

        mundoTEG.insertarArco("ARGENTINA", "CHILE", "FRONTERA");
        mundoTEG.insertarArco("ARGENTINA", "URUGUAY", "FRONTERA");
        mundoTEG.insertarArco("ARGENTINA", "BRASIL", "FRONTERA");
        mundoTEG.insertarArco("ARGENTINA", "PERU", "FRONTERA");
        mundoTEG.insertarArco("CHILE", "PERU", "FRONTERA");
        mundoTEG.insertarArco("CHILE", "AUSTRALIA", "PUENTE");
        mundoTEG.insertarArco("PERU", "COLOMBIA", "FRONTERA");
        mundoTEG.insertarArco("PERU", "BRASIL", "FRONTERA");
        mundoTEG.insertarArco("URUGUAY", "BRASIL", "FRONTERA");
        mundoTEG.insertarArco("BRASIL", "COLOMBIA", "FRONTERA");
        mundoTEG.insertarArco("BRASIL", "SAHARA", "PUENTE");
        mundoTEG.insertarArco("COLOMBIA", "MEXICO", "FRONTERA");
        mundoTEG.insertarArco("MEXICO", "CALIFORNIA", "FRONTERA");
        mundoTEG.insertarArco("CALIFORNIA", "OREGON", "FRONTERA");
        mundoTEG.insertarArco("CALIFORNIA", "NUEVA YORK", "FRONTERA");
        mundoTEG.insertarArco("OREGON", "ALASKA", "FRONTERA");
        mundoTEG.insertarArco("OREGON", "YUKON", "FRONTERA");
        mundoTEG.insertarArco("OREGON", "CANADA", "FRONTERA");
        mundoTEG.insertarArco("OREGON", "NUEVA YORK", "FRONTERA");
        mundoTEG.insertarArco("ALASKA", "YUKON", "FRONTERA");
        mundoTEG.insertarArco("ALASKA", "KAMCHATKA", "PUENTE");
        mundoTEG.insertarArco("YUKON", "CANADA", "FRONTERA");
        mundoTEG.insertarArco("CANADA", "NUEVA YORK", "FRONTERA");
        mundoTEG.insertarArco("NUEVA YORK", "TERRANOVA", "FRONTERA");
        mundoTEG.insertarArco("NUEVA YORK", "GROENLANDIA", "PUENTE");
        mundoTEG.insertarArco("TERRANOVA", "LABRADOR", "FRONTERA");
        mundoTEG.insertarArco("LABRADOR", "GROENLANDIA", "PUENTE");
        mundoTEG.insertarArco("GROENLANDIA", "ISLANDIA", "PUENTE");
        mundoTEG.insertarArco("ISLANDIA", "SUECIA", "PUENTE");
        mundoTEG.insertarArco("ISLANDIA", "GRAN BRETAÑA", "PUENTE");
        mundoTEG.insertarArco("GRAN BRETAÑA", "ESPAÑA", "PUENTE");
        mundoTEG.insertarArco("GRAN BRETAÑA", "ALEMANIA", "PUENTE");
        mundoTEG.insertarArco("ESPAÑA", "SAHARA", "PUENTE");
        mundoTEG.insertarArco("ESPAÑA", "FRANCIA", "FRONTERA");
        mundoTEG.insertarArco("FRANCIA", "ITALIA", "FRONTERA");
        mundoTEG.insertarArco("FRANCIA", "ALEMANIA", "FRONTERA");
        mundoTEG.insertarArco("ITALIA", "ALEMANIA", "FRONTERA");
        mundoTEG.insertarArco("ALEMANIA", "POLONIA", "FRONTERA");
        mundoTEG.insertarArco("POLONIA", "RUSIA", "FRONTERA");
        mundoTEG.insertarArco("POLONIA", "EGIPTO", "PUENTE");
        mundoTEG.insertarArco("RUSIA", "SUECIA", "FRONTERA");
        mundoTEG.insertarArco("RUSIA", "ARAL", "FRONTERA");
        mundoTEG.insertarArco("RUSIA", "IRAN", "FRONTERA");
        mundoTEG.insertarArco("RUSIA", "TURQUIA", "FRONTERA");
        mundoTEG.insertarArco("ARAL", "TARTARIA", "FRONTERA");
        mundoTEG.insertarArco("ARAL", "SIBERIA", "FRONTERA");
        mundoTEG.insertarArco("ARAL", "IRAN", "FRONTERA");
        mundoTEG.insertarArco("ARAL", "MONGOLIA", "FRONTERA");
        mundoTEG.insertarArco("TARTARIA", "TAMIR", "FRONTERA");
        mundoTEG.insertarArco("TARTARIA", "SIBERIA", "FRONTERA");
        mundoTEG.insertarArco("TAMIR", "SIBERIA", "FRONTERA");
        mundoTEG.insertarArco("SIBERIA", "KAMCHATKA", "FRONTERA");
        mundoTEG.insertarArco("SIBERIA", "MONGOLIA", "FRONTERA");
        mundoTEG.insertarArco("SIBERIA", "CHINA", "FRONTERA");
        mundoTEG.insertarArco("KAMCHATKA", "CHINA", "FRONTERA");
        mundoTEG.insertarArco("KAMCHATKA", "JAPON", "PUENTE");
        mundoTEG.insertarArco("CHINA", "JAPON", "PUENTE");
        mundoTEG.insertarArco("CHINA", "GOBI", "FRONTERA");
        mundoTEG.insertarArco("CHINA", "MALASIA", "FRONTERA");
        mundoTEG.insertarArco("CHINA", "INDIA", "FRONTERA");
        mundoTEG.insertarArco("CHINA", "MONGOLIA", "FRONTERA");
        mundoTEG.insertarArco("CHINA", "IRAN", "FRONTERA");
        mundoTEG.insertarArco("MONGOLIA", "GOBI", "FRONTERA");
        mundoTEG.insertarArco("IRAN", "GOBI", "FRONTERA");
        mundoTEG.insertarArco("IRAN", "TURQUIA", "FRONTERA");
        mundoTEG.insertarArco("TURQUIA", "ISRAEL", "FRONTERA");
        mundoTEG.insertarArco("TURQUIA", "ARABIA", "FRONTERA");
        mundoTEG.insertarArco("TURQUIA", "EGIPTO", "PUENTE");
        mundoTEG.insertarArco("ISRAEL", "EGIPTO", "PUENTE");
        mundoTEG.insertarArco("ISRAEL", "ARABIA", "FRONTERA");
        mundoTEG.insertarArco("INDIA", "SUMATRA", "PUENTE");
        mundoTEG.insertarArco("MALASIA", "BORNEO", "PUENTE");
        mundoTEG.insertarArco("SUMATRA", "AUSTRALIA", "PUENTE");
        mundoTEG.insertarArco("BORNEO", "AUSTRALIA", "PUENTE");
        mundoTEG.insertarArco("JAVA", "AUSTRALIA", "PUENTE");
        mundoTEG.insertarArco("EGIPTO", "ETIOPIA", "FRONTERA");
        mundoTEG.insertarArco("EGIPTO", "SAHARA", "FRONTERA");
        mundoTEG.insertarArco("EGIPTO", "MADAGASCAR", "PUENTE");
        mundoTEG.insertarArco("ETIOPIA", "SAHARA", "FRONTERA");
        mundoTEG.insertarArco("ETIOPIA", "ZAIRE", "FRONTERA");
        mundoTEG.insertarArco("ETIOPIA", "SUDAFRICA", "FRONTERA");
        mundoTEG.insertarArco("ZAIRE", "SAHARA", "FRONTERA");
        mundoTEG.insertarArco("ZAIRE", "SUDAFRICA", "FRONTERA");
        mundoTEG.insertarArco("ZAIRE", "MADAGASCAR", "PUENTE");

        arrayStringPaises = asignarJugadores(arbolOrden.listar());
        mapaCreado = true;
    }

    private String[] asignarJugadores(Lista listaPaises) {

        int jug = 0;
        int longitud = listaPaises.longitud();
        String[] arreglo = new String[longitud];
        for (int i = 1; i <= longitud; i++) {
            Pais p = (Pais) listaPaises.recuperar(1);
            asignarPais(jug, p);
            arreglo[i - 1] = p.getNombre();
            jug++;
            if (jug >= 3) {
                jug = 0;
            }
            listaPaises.eliminarInicio();
        }
        return arreglo;
    }

    /**
     * Asigna el país al jugador y coloca las tres fichas iniciales
     *
     * @param jugador
     * @param pais
     * @return
     */
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

    /**
     * Intenta realizar el ataque según lo indicado en las reglas (verificando
     * las condiciones necesarias), e informar el resultado del mismo
     *
     * @param pais1
     * @param pais2
     * @return
     */
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

                dado1 = 6;
                dado2 = 1;
                // dado1 = tirarDados();
                //dado2 = tirarDados();

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

    /**
     * Intenta realizar el ataque según lo indicado en las reglas (verificando
     * las condiciones necesarias), e informar el resultado del mismo
     *
     * @param jugador
     * @return
     */
    public int obtenerCantPaises(int jugador) {
        return jugadores[jugador].getPaisesObtenidos().size();
    }

    /**
     * Obtiene y muestra la lista de países que son limítrofes o tienen un
     * puente que lo une con país
     *
     * @param pais
     * @return
     */
    public Lista obtenerVecinos(String pais) {
        return mundoTEG.listarArcos(pais);
    }

    public String[] getVecinos(String pais) {

        Lista lista = mundoTEG.listarArcos(pais);
        int longitud = lista.longitud();
        String[] arreglo = new String[longitud];
        for (int i = 1; i <= longitud; i++) {
            Pais p = (Pais) lista.recuperar(1);
            arreglo[i - 1] = p.getNombre();
            lista.eliminarInicio();
        }
        return arreglo;
    }

    /**
     * Crea un pacto de no-agresión entre ambos países
     *
     * @param pais1
     * @param pais2
     * @return
     */
    public boolean crearPacto(String pais1, String pais2) {
        return mundoTEG.eliminarArco(pais1, pais2);
    }

    /**
     * Crea un pacto de no-agresión entre ambos países
     *
     * @param pais
     * @param cantFichas
     */
    public void agregarFichas(String pais, int cantFichas) {
        Pais p = obtenerPais(pais);
        p.sumarFicha(cantFichas);
    }

    /**
     * Crea un pacto de no-agresión entre ambos países
     *
     * @param pais
     * @param cantFichas
     */
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

    /**
     * Devuelve la cantidad mínima de “ataques” con los que un jugador que es
     * dueño del país1 podría llegar al país2, considerando que ganara todos los
     * ataques
     *
     * @param pais1
     * @param pais2
     * @return
     */
    public int cantJugadasMinimas(String pais1, String pais2) {
        Lista camino;
        camino = mundoTEG.caminoMasCorto(pais1, pais2);
        return camino.longitud() - 1;
    }

    /**
     * Devuelve la cantidad mínima de “ataques” con los que un jugador que es
     * dueño del país1 podría llegar al país2, considerando que ganara todos los
     * ataques
     *
     * @param pais1
     * @param pais2
     * @param k
     * @return
     */
    public boolean sePuedeLlegarEn(String pais1, String pais2, int k) {
        return mundoTEG.existeMasCorto(pais1, pais2, k);
    }

    /**
     * Para un jugador dado, listar qué ataques podría ser más conveniente hacer
     * (aquellos que el país atacante tenga mayor o igual cantidad de ejércitos
     * que el país atacado)
     *
     * @param j1
     * @return
     */
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

    /**
     * Obtener y mostrar el jugador que posee más países en ese momento
     *
     * @return
     */
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

    /**
     * Devuelve al jugador que cumplió el objetivo de conquistar como mínimo 25
     * países. Este método debería evaluarse al final de cada ronda. En caso de
     * que ningún jugador haya alcanzado el objetivo, debe devolver un valor
     * acorde
     *
     * @return
     */
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
