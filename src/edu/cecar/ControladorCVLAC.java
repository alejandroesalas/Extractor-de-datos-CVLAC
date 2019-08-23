
package edu.cecar;

import edu.cecar.modelo.Investigador;
import edu.cecar.modelo.LineaInvestigacion;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;


/**
 *
 * @author Jhonnys
 * Clase que se encarga de extraer, por medio de la libreria Jsoup, la informacion requerida
 */
public final class ControladorCVLAC {
    private final ArrayList<Investigador> investigadores;
    private final ArrayList<String> urls;
    private List<HashMap<String,String>>formacionesAcademicas;

    public ControladorCVLAC(ArrayList<String> urls) {
        this.urls = urls;
        investigadores = new ArrayList<>();
    }
    
    public ArrayList<Investigador> ExtraerDatos() throws IOException{
        
        String nombre;String nacionalidad;String sexo;
        Investigador investigador;
        for (String url : urls) {
             ArrayList<String> expLaboral = new ArrayList<>();
         ArrayList<LineaInvestigacion> lineasInvestigacion = new ArrayList<>();
             formacionesAcademicas = new ArrayList<>();
            //investigador = new Investigador();
            Document documentoHTML = Jsoup.connect(url).ignoreHttpErrors(true).validateTLSCertificates(false).get();
             Elements tables = documentoHTML.select("table");
             System.out.println("tamaño"+tables.size());
             Element tableDatosGenerales = tables.get(1);
            Elements tableRowsDatosGenerales = tableDatosGenerales.select("tr");
            
            nombre = tableRowsDatosGenerales.get(0).
                    select("td").get(1).text();
             nacionalidad = tableRowsDatosGenerales.
                    get(2).select("td").get(1).text();
             sexo = tableRowsDatosGenerales.
                    get(3).select("td").get(1).text();
             investigador = new Investigador(nombre, nacionalidad, sexo);
             System.out.println("Información Personal");
              System.out.println("Nombre:".concat(nombre));
              System.out.println("Nacionalidad:".concat(nacionalidad));
              System.out.println("Sexo:".concat(sexo));
              System.out.println("-------------------------------");
             //Recorremos las tablas.
             for (int i = 2; i < tables.size(); i++) {
                  Element table =  tables.get(i);
                  Element primeraFila = table.select("tr").get(0);//tomamos el primer tr
//                  System.out.println(primeraFila.text());
                  if (primeraFila != null) {
                      if (primeraFila.text().equalsIgnoreCase("Formación Académica")) {
                           System.out.println("Extrayendo Formacion academica..");
                           Elements filas = table.select("tr");
                             for (int j = 1; j < filas.size(); j++) {
                                  Element columna = filas.get(j).select("td").get(1);//Se btiene el segundo td(1), ya que el primero(0) esta vacio
                                  System.out.println(columna.text());
                                  List<TextNode> nodos = columna.textNodes();
                                  HashMap<String,String>formacion = new HashMap<>();
                                  formacion.put("tipoFormacion",columna.select("b").first().text());//tipo de formacion
                                  formacion.put("lugarFormacion", nodos.get(0).text());
                                  formacion.put("tituloFormacion", nodos.get(1).text());
                                  formacion.put("fechaRealizacion", nodos.get(2).text());
                                  formacion.put("descripcion", nodos.get(3).text());
                                  formacionesAcademicas.add(formacion);
                             }
                           System.out.println("-------------------------------");
                          
                      }else if(primeraFila.text().equalsIgnoreCase("Experiencia profesional")){
                             System.out.println("Extrayendo xperiencia profesional");
                              Elements filas = table.select("tr");//filas
                               for (int k = 1; k < filas.size(); k++) {
                                   Element columna = filas.get(k).select("td").get(1);
                                   expLaboral.add(columna.text());
                                   System.out.println(columna.text()); 
                               }
                              System.out.println("-------------------------------");
                      }else if(primeraFila.text().equalsIgnoreCase("Líneas de investigación")){
                           System.out.println("Extrayendo Lineas de investigacion");
                           Elements listas = table.select("li");
                           for (Element lista : listas) {
                               System.out.println(lista.text()); 
                               List<TextNode> nodos = lista.textNodes();
                               boolean isActivo = nodos.get(1).text().equalsIgnoreCase("Si");
                               lineasInvestigacion.add(new LineaInvestigacion(nodos.get(0).text(), isActivo));
                          }
                              System.out.println("-------------------------------");
                      }
                      investigador.setFormacionesAcademicas(formacionesAcademicas);
                      investigador.setExpLaboral(expLaboral);
                      investigador.setLineasDeInvestigaciones(lineasInvestigacion);
                   } 
            }
             investigadores.add(investigador);
        }
        return investigadores;
    }
    
    
    
}
