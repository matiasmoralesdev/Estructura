package utiles;

public class ErrorTDA extends Exception{

/**
 * Autor: Catedra EP y EDyA
 * Fecha creacion: Abril 2011
 * Fecha ult. modificacion: 13/04/2011
 *
 * Propósito de la clase: Ser usada como medio de comunicación entre los
 * métodos de los TDA implementados en dichas materias
 * y los métodos que invoquen a los mismos
 *
 */

// ATRIBUTOS ------------------------------------------------------------------
	 int codigo;

// METODO CONSTRUCTOR ---------------------------------------------------------

         /**
          * El metodo constructor necesita un codigo int que defina el tipo
          * de error descubierto. Al crearse inicializa el codigo con ese valor.
          *
          * En caso de ser necesario considerar mas tipos de errores
	  * se puede agregar el codigo y su mensaje correspondiente
	  * en el metodo getMensaje()
          */

         public ErrorTDA(int codigo){
             this.codigo = codigo;
         }


// METODOS OBSERVADORES ------------------------------------------------------

        /**
         * Devuelve el valor numerico, si el programa llamador desea mostrarlo
         */
	public int getCodigo () {
            return codigo;
	 }


        /**
         * Devuelve el mensaje asociado al valor numerico,
         * en caso que el programa llamador desee mostrarlo
         */
	public String getMensaje () {
            String mensaje;
            switch (this.getCodigo()){
               // Mensajes generales para TDAs
                case 1:   mensaje = "Datos de entrada invalidos";
                          break;
                case 2:   mensaje = "Objeto inmutable, no se puede modificar";
                          break;
                case 3:   mensaje = "La operacion no termino exitosamente";
                          break;

               // Mensajes para manejo de estructuras dinamicas
                case 4:   mensaje = "Posicion no valida";
                          break;
                case 5:   mensaje = "Elemento no encontrado";
                          break;
                case 6:   mensaje = "Elemento o clave repetido";
                          break;
                case 7:   mensaje = "Estructura llena";
                          break;
                case 8:   mensaje = "Estructura vacia";
                          break;

               // Mensajes para manejo de archivos
                case 100: mensaje = "Error abriendo el archivo";
                          break;
                case 101: mensaje = "Error de escritura en archivo";
                          break;
                case 102: mensaje = "Error de lectura de archivo";
                          break;
                case 103: mensaje = "Error cerrando Archivo";
                          break;
                case 104: mensaje = "Archivo inexistente";
                          break;

		// Mensaje con codigo invalido
                default:  mensaje = "Error indeterminado";
             }

            return mensaje;
	 }

}
