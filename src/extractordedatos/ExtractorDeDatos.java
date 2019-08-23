/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extractordedatos;

import edu.cecar.ControladorCVLAC;
import edu.cecar.ControladorExcel;
import edu.cecar.modelo.Investigador;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jhonnys
 */
public class ExtractorDeDatos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /**
         * Requerimiento funcional uno.
         * cargar a memoria principal los distintos enlaces de los cvlac
         */
        ArrayList<Investigador> investigadores;
        BufferedReader br = null;
        ArrayList<String> urls= new ArrayList();
        try {
            System.out.println("Cargando URLs");
            String li;
            br = new BufferedReader(new FileReader("urls.txt"));
            while((li = br.readLine()) != null){
                urls.add(li);
            }
             System.out.println("Urls de Cvlac caragadas exitosamente:");
                for (String url : urls) {
                    System.out.println(url);                    
                }
        } catch (FileNotFoundException ex) {
            System.err.println("La ruta especificada no se encontro");
            Logger.getLogger(ExtractorDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExtractorDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (br != null) {
                     br.close();
                }        
            } catch (IOException ex) {
                Logger.getLogger(ExtractorDeDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (!urls.isEmpty()) {
            System.out.println("Extrayendo información de las urls..");
            ControladorCVLAC coCvlac= new ControladorCVLAC(urls);
            try {
               investigadores = coCvlac.ExtraerDatos();
               System.out.println("Finalizo extraccion");
               System.out.println("Guardando datos a excel");
                ControladorExcel ce = new ControladorExcel("tmps/");
                for (Investigador investigador : investigadores) {
                        ce.guardar(investigador);       
                }
                System.out.println("Todos los Ivestigadores guardados con exito. Revise la carpeta tmps");
            } catch (IOException ex) {
                ex.printStackTrace();
                Logger.getLogger(ExtractorDeDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }else{
            System.out.println("No hay urls");
        }
  

    }
    
}
