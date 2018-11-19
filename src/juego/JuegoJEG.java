package juego;

import grafos.GrafoEtiquetado;

public class JuegoJEG {

    public static void main(String[] args) {

        //GrafoEtiquetado MundoTEG = new GrafoEtiquetado();
        crearMapa();
    }

    public static void crearMapa() {
        GrafoEtiquetado MundoTEG = new GrafoEtiquetado();

        MundoTEG.insertarVertice(new Pais("ARGENTINA"));
        MundoTEG.insertarVertice(new Pais("CHILE"));
        MundoTEG.insertarVertice(new Pais("URUGUAY"));
        MundoTEG.insertarVertice(new Pais("BRASIL"));
        MundoTEG.insertarVertice(new Pais("PERU"));
        MundoTEG.insertarVertice(new Pais("COLOMBIA"));

        MundoTEG.insertarVertice(new Pais("MEXICO"));
        MundoTEG.insertarVertice(new Pais("CALIFORNIA"));
        MundoTEG.insertarVertice(new Pais("CANADA"));
        MundoTEG.insertarVertice(new Pais("OREGON"));
        MundoTEG.insertarVertice(new Pais("YUKON"));
        MundoTEG.insertarVertice(new Pais("ALASKA"));
        MundoTEG.insertarVertice(new Pais("NUEVA YORK"));
        MundoTEG.insertarVertice(new Pais("TERRANOVA"));
        MundoTEG.insertarVertice(new Pais("LABRADOR"));
        MundoTEG.insertarVertice(new Pais("GROENLANDIA"));

        MundoTEG.insertarVertice(new Pais("ISLANDIA"));
        MundoTEG.insertarVertice(new Pais("GRAN BRETAÑA"));
        MundoTEG.insertarVertice(new Pais("ESPAÑA"));
        MundoTEG.insertarVertice(new Pais("FRANCIA"));
        MundoTEG.insertarVertice(new Pais("ALEMANIA"));
        MundoTEG.insertarVertice(new Pais("ITALIA"));
        MundoTEG.insertarVertice(new Pais("POLONIA"));
        MundoTEG.insertarVertice(new Pais("RUSIA"));
        MundoTEG.insertarVertice(new Pais("SUECIA"));

        MundoTEG.insertarVertice(new Pais("IRAN"));
        MundoTEG.insertarVertice(new Pais("TURQUIA"));
        MundoTEG.insertarVertice(new Pais("ISRAEL"));
        MundoTEG.insertarVertice(new Pais("ARABIA"));
        MundoTEG.insertarVertice(new Pais("ARAL"));
        MundoTEG.insertarVertice(new Pais("TARTARIA"));
        MundoTEG.insertarVertice(new Pais("TAMIR"));
        MundoTEG.insertarVertice(new Pais("SIBERIA"));
        MundoTEG.insertarVertice(new Pais("MONGOLIA"));
        MundoTEG.insertarVertice(new Pais("GOBI"));
        MundoTEG.insertarVertice(new Pais("CHINA"));
        MundoTEG.insertarVertice(new Pais("MALASIA"));
        MundoTEG.insertarVertice(new Pais("JAPON"));
        MundoTEG.insertarVertice(new Pais("KAMCHATKA"));
        MundoTEG.insertarVertice(new Pais("INDIA"));

        MundoTEG.insertarVertice(new Pais("SUMATRA"));
        MundoTEG.insertarVertice(new Pais("BORNEO"));
        MundoTEG.insertarVertice(new Pais("JAVA"));
        MundoTEG.insertarVertice(new Pais("AUSTRALIA"));

        MundoTEG.insertarVertice(new Pais("SAHARA"));
        MundoTEG.insertarVertice(new Pais("EGIPTO"));
        MundoTEG.insertarVertice(new Pais("ETIOPIA"));
        MundoTEG.insertarVertice(new Pais("ZAIRE"));
        MundoTEG.insertarVertice(new Pais("SUDAFRICA"));
        MundoTEG.insertarVertice(new Pais("MADAGASCAR"));

        //MundoTEG.insertarArcoDoble("", "", "PUENTE");
        // MundoTEG.insertarArcoDoble("", "", "FRONTERA");
        MundoTEG.insertarArcoDoble("ARGENTINA", "CHILE", "FRONTERA");
        MundoTEG.insertarArcoDoble("ARGENTINA", "URUGUAY", "FRONTERA");
        MundoTEG.insertarArcoDoble("ARGENTINA", "BRASIL", "FRONTERA");
        MundoTEG.insertarArcoDoble("ARGENTINA", "PERU", "FRONTERA");
        MundoTEG.insertarArcoDoble("CHILE", "PERU", "FRONTERA");
        MundoTEG.insertarArcoDoble("CHILE", "AUSTRALIA", "PUENTE");
        MundoTEG.insertarArcoDoble("PERU", "COLOMBIA", "FRONTERA");
        MundoTEG.insertarArcoDoble("PERU", "BRASIL", "FRONTERA");
        MundoTEG.insertarArcoDoble("URUGUAY", "BRASIL", "FRONTERA");
        MundoTEG.insertarArcoDoble("BRASIL", "COLOMBIA", "FRONTERA");
        MundoTEG.insertarArcoDoble("BRASIL", "SAHARA", "PUENTE");
        MundoTEG.insertarArcoDoble("COLOMBIA", "MEXICO", "FRONTERA");
        MundoTEG.insertarArcoDoble("MEXICO", "CALIFORNIA", "FRONTERA");
        MundoTEG.insertarArcoDoble("CALIFORNIA", "OREGON", "FRONTERA");
        MundoTEG.insertarArcoDoble("CALIFORNIA", "NUEVA YORK", "FRONTERA");
        MundoTEG.insertarArcoDoble("OREGON", "ALASKA", "FRONTERA");
        MundoTEG.insertarArcoDoble("OREGON", "YUKON", "FRONTERA");
        MundoTEG.insertarArcoDoble("OREGON", "CANADA", "FRONTERA");
        MundoTEG.insertarArcoDoble("OREGON", "NUEVA YORK", "FRONTERA");
        MundoTEG.insertarArcoDoble("ALASKA", "YUKON", "FRONTERA");
        MundoTEG.insertarArcoDoble("ALASKA", "KAMCHATKA", "PUENTE");
        MundoTEG.insertarArcoDoble("YUKON", "CANADA", "FRONTERA");
        MundoTEG.insertarArcoDoble("CANADA", "NUEVA YORK", "FRONTERA");
        MundoTEG.insertarArcoDoble("NUEVA YORK", "TERRANOVA", "FRONTERA");
        MundoTEG.insertarArcoDoble("NUEVA YORK", "GROENLANDIA", "PUENTE");
        MundoTEG.insertarArcoDoble("TERRANOVA", "LABRADOR", "FRONTERA");
        MundoTEG.insertarArcoDoble("LABRADOR", "GROENLANDIA", "PUENTE");
        MundoTEG.insertarArcoDoble("GROENLANDIA", "ISLANDIA", "PUENTE");
        MundoTEG.insertarArcoDoble("ISLANDIA", "SUECIA", "PUENTE");
        MundoTEG.insertarArcoDoble("ISLANDIA", "GRAN BRETAÑA", "PUENTE");
        MundoTEG.insertarArcoDoble("GRAN BRETAÑA", "ESPAÑA", "PUENTE");
        MundoTEG.insertarArcoDoble("GRAN BRETAÑA", "ALEMANIA", "PUENTE");
        MundoTEG.insertarArcoDoble("ESPAÑA", "SAHARA", "PUENTE");
        MundoTEG.insertarArcoDoble("ESPAÑA", "FRANCIA", "FRONTERA");
        MundoTEG.insertarArcoDoble("FRANCIA", "ITALIA", "FRONTERA");
        MundoTEG.insertarArcoDoble("FRANCIA", "ALEMANIA", "FRONTERA");
        MundoTEG.insertarArcoDoble("ALEMANIA", "POLONIA", "FRONTERA");
        MundoTEG.insertarArcoDoble("POLONIA", "RUSIA", "FRONTERA");
        MundoTEG.insertarArcoDoble("POLONIA", "EGIPTO", "PUENTE");
        MundoTEG.insertarArcoDoble("RUSIA", "SUECIA", "FRONTERA");
        MundoTEG.insertarArcoDoble("RUSIA", "ARAL", "FRONTERA");
        MundoTEG.insertarArcoDoble("RUSIA", "IRAN", "FRONTERA");
        MundoTEG.insertarArcoDoble("RUSIA", "TURQUIA", "FRONTERA");
        MundoTEG.insertarArcoDoble("ARAL", "TARTARIA", "FRONTERA");
        MundoTEG.insertarArcoDoble("ARAL", "SIBERIA", "FRONTERA");
        MundoTEG.insertarArcoDoble("ARAL", "IRAN", "FRONTERA");
        MundoTEG.insertarArcoDoble("ARAL", "MONGOLIA", "FRONTERA");
        MundoTEG.insertarArcoDoble("TARTARIA", "TAMIR", "FRONTERA");
        MundoTEG.insertarArcoDoble("TARTARIA", "SIBERIA", "FRONTERA");
        MundoTEG.insertarArcoDoble("TAMIR", "SIBERIA", "FRONTERA");
        MundoTEG.insertarArcoDoble("SIBERIA", "KAMCHATKA", "FRONTERA");
        MundoTEG.insertarArcoDoble("SIBERIA", "MONGOLIA", "FRONTERA");
        MundoTEG.insertarArcoDoble("SIBERIA", "CHINA", "FRONTERA");
        MundoTEG.insertarArcoDoble("KAMCHATKA", "CHINA", "FRONTERA");
        MundoTEG.insertarArcoDoble("KAMCHATKA", "JAPON", "PUENTE");
        MundoTEG.insertarArcoDoble("CHINA", "JAPON", "PUENTE");
        MundoTEG.insertarArcoDoble("CHINA", "GOBI", "FRONTERA");
        MundoTEG.insertarArcoDoble("CHINA", "MALASIA", "FRONTERA");
        MundoTEG.insertarArcoDoble("CHINA", "INDIA", "FRONTERA");
        MundoTEG.insertarArcoDoble("CHINA", "MONGOLIA", "FRONTERA");
        MundoTEG.insertarArcoDoble("CHINA", "IRAN", "FRONTERA");
        MundoTEG.insertarArcoDoble("MONGOLIA", "GOBI", "FRONTERA");
        MundoTEG.insertarArcoDoble("IRAN", "GOBI", "FRONTERA");
        MundoTEG.insertarArcoDoble("IRAN", "TURQUIA", "FRONTERA");
        MundoTEG.insertarArcoDoble("TURQUIA", "ISRAEL", "FRONTERA");
        MundoTEG.insertarArcoDoble("TURQUIA", "ARABIA", "FRONTERA");
        MundoTEG.insertarArcoDoble("TURQUIA", "EGIPTO", "PUENTE");
        MundoTEG.insertarArcoDoble("ISRAEL", "EGIPTO", "PUENTE");
        MundoTEG.insertarArcoDoble("ISRAEL", "ARABIA", "FRONTERA");
        MundoTEG.insertarArcoDoble("INDIA", "SUMATRA", "PUENTE");
        MundoTEG.insertarArcoDoble("MALASIA", "BORNEO", "PUENTE");
        MundoTEG.insertarArcoDoble("SUMATRA", "AUSTRALIA", "PUENTE");
        MundoTEG.insertarArcoDoble("BORNEO", "AUSTRALIA", "PUENTE");
        MundoTEG.insertarArcoDoble("JAVA", "AUSTRALIA", "PUENTE");
        MundoTEG.insertarArcoDoble("EGIPTO", "ETIOPIA", "FRONTERA");
        MundoTEG.insertarArcoDoble("EGIPTO", "SAHARA", "FRONTERA");
        MundoTEG.insertarArcoDoble("EGIPTO", "MADAGASCAR", "PUENTE");
        MundoTEG.insertarArcoDoble("ETIOPIA", "SAHARA", "FRONTERA");
        MundoTEG.insertarArcoDoble("ETIOPIA", "ZAIRE", "FRONTERA");
        MundoTEG.insertarArcoDoble("ETIOPIA", "SUDAFRICA", "FRONTERA");
        MundoTEG.insertarArcoDoble("ZAIRE", "SAHARA", "FRONTERA");
        MundoTEG.insertarArcoDoble("ZAIRE", "SUDAFRICA", "FRONTERA");
        MundoTEG.insertarArcoDoble("ZAIRE", "MADAGASCAR", "PUENTE");

        //MundoTEG.insertarArcoDoble("", "", "PUENTE");
        MundoTEG.insertarArcoDoble("", "", "FRONTERA");

        System.out.println(MundoTEG.toString());

    }

}
