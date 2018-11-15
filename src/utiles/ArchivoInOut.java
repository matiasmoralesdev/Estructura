package utiles;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class ArchivoInOut {

/**
 * Autor: Paola - Catedra EDyA
 * Fecha creacion: Marzo 2008
 * Fecha ult. modificacion: 16/08/2011
 *
 * Propósito de la clase: Permite ejecutar manejar
 * operaciones de carga y recuperacion desde Archivos a otras
 * estructuras en memoria primaria
 *
 */

        /**
         * Recibe un nombre de archivo y una estructura almacenada en un Object
         * El Object se guarda en un archivo con el nombre dado
         */
	public static void grabarEnArchivo(String nombre, Object o) throws ErrorTDA{
		try{
			ObjectOutputStream  flujoDatos = new ObjectOutputStream(new FileOutputStream(nombre));

                        try
			{
				flujoDatos.writeObject(o);
			}
			catch (IOException e)
			{
				//Error de escritura en archivo
				throw new ErrorTDA(101);
			}

                        try{
				flujoDatos.close();
			}
			catch(IOException e){
				//Error Cerrando Archivo
				throw new ErrorTDA(103);
			}
		}
		catch(IOException e){
			//Error abriendo el archivo
			throw new ErrorTDA(100);
		}

	}


        /**
         * Recibe un nombre de archivo.
         * Devuelve un Object como estaba almacenado en el archivo
         * Precondicion: que el archivo exista 
         */
	public static Object cargarDesdeArchivo(String nombre) throws ErrorTDA {
		File archivoDisco = new File(nombre);
                Object o = null;
		if (archivoDisco.exists()){
			ObjectInputStream flujoDeEntrada = null;

			try
		   	{
			   flujoDeEntrada = new ObjectInputStream(new FileInputStream(nombre));
			}
			catch (IOException e)
			{
				//Error de lectura de archivo
				throw new ErrorTDA(102);
			}
			try
			{
				try
				{
        				o = flujoDeEntrada.readObject( );
				}
				catch(EOFException e)
				{
				}
				flujoDeEntrada.close( );
			}
		   	catch(Exception e)
		   	{
		   		//Error de lectura de archivo
				throw new ErrorTDA(102);
		   	}
		}
		else
			throw new ErrorTDA(104);

		return o;


	}
}
