package ventanas;

/*
 * Libreria con funciones para mostrar mensajes y otras ventanas
 */

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class Ventanas {


    /** Muestra un mensaje y pide una respuesta SI o NO
     *  @param pregunta: texto que representa la pregunta
     *  @return boton SI = true, boton NO = false
     */
    public static boolean preguntarSiNo(String pregunta){
        int res = JOptionPane.showConfirmDialog(null, pregunta, "Su opcion", 
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return res == JOptionPane.YES_OPTION;
    }


    /** Muestra un mensaje y una lista de opciones
     *  @param mensaje: mensaje que pide seleccionar una opcion
     *  @param opciones: array de String (cada elem es una opcion)
     *  @param titulo: titulo de la ventana
     */
    public static String pedirUnaOpcion(String titulo, String mensaje, String[] opciones){
        return (String) JOptionPane.showInputDialog(null, mensaje, titulo, 
                JOptionPane.PLAIN_MESSAGE,null, opciones, opciones[0]);
    }


    /** Pide que se ingrese un valor por teclado
     *  @param mensaje: debe mencionar el dato esperado
     *  @param titulo: titulo de la ventana
     * Tiene con dos botones (ACEPTAR-CANCELAR)
     * La opcion ACEPTAR devuelve el valor ingresado en formato String
     * La opcion CANCELAR devuelve un null
     */
    public static String leerUnDato(String titulo, String mensaje){
        return JOptionPane.showInputDialog(null, mensaje, titulo,
                JOptionPane.OK_CANCEL_OPTION);
    }


    /** Muestra un mensaje de texto largo (varias lineas)
     *  @param mensaje: texto del mensaje a mostrar
     *  @param titulo: titulo de la ventana
     *  @param ancho y alto: tamanio de la ventana
     */
    public static void mostrarTexto(String titulo, String texto, int ancho, int alto){
     // crea y setea el area de texto donde se muestra el mensaje
      JTextArea areaTexto = new JTextArea(alto, ancho);
      areaTexto.setText(texto);
      areaTexto.setEditable(false);

      // prepara las barras de scrolling
      JScrollPane scroll = new JScrollPane(areaTexto);

      // muestra el mensaje en un JOptionPane
      JOptionPane.showMessageDialog(null, scroll, titulo,
              JOptionPane.PLAIN_MESSAGE);
    }


    /** Muestra un mensaje con formato de ADVERTENCIA de error
     *  @param mensajeError: texto de error o advertencia
     */
    public static void mostrarError(String mensajeError){
        JOptionPane.showMessageDialog(null, mensajeError, "ERROR",
                JOptionPane.ERROR_MESSAGE);
    }


    /** Muestra un mensaje con formato de INFORMACION
     *  @param mensaje: texto del mensaje informativo
     *  @param titulo: titulo de la ventana
     */
    public static void mostrarMensaje(String titulo, String mensaje){
        JOptionPane.showMessageDialog(null, mensaje, titulo,
                JOptionPane.PLAIN_MESSAGE);
    }

}
