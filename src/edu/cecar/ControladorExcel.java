/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cecar;

import edu.cecar.modelo.Investigador;
import edu.cecar.modelo.LineaInvestigacion;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;

/**
 *
 * @author Jhonnys
 */
public final class ControladorExcel {
    private final String ruta;
    private  HSSFWorkbook workbook;
    public ControladorExcel(String ruta) {
        this.ruta = ruta;
    }

    public void guardar(Investigador investigador) throws FileNotFoundException, IOException {
////        XSSFWorkbook workbook = XSSFWorkbookFactory.createWorkbook()  
        workbook = new HSSFWorkbook();
        System.out.println("Guardando Informacion Personal..");
        guardarDatosPersonales(workbook, investigador.getNombre(), investigador.getNacionalidad(), investigador.getSexo());
         System.out.println("Guardando Formación academica..");
         guardarFormacionAcademica(investigador.getFormacionesAcademicas(), workbook); 
         System.out.println("Guardando Experiencia Profesional..");
         guardarExpProf(investigador.getExpLaboral(), workbook); 
         System.out.println("Guardando Lineas de investigación..");
         guardarInvestigacion(investigador.getLineasDeInvestigaciones(), workbook); 
         System.out.println("Persistiendo datos..");
         persistir(new FileOutputStream(ruta.concat(investigador.getNombre()).concat(".xls")));      
    }
    public void guardarDatosPersonales( Workbook wb,String nombre,String nacionalidad,String sexo){
        Sheet hoja = wb.createSheet(WorkbookUtil.createSafeSheetName("Datos Personales"));
         Row fila =  hoja.createRow(0);
         fila.createCell(0).setCellValue("Nombre");
         fila.createCell(1).setCellValue("Nacionalidad");
         fila.createCell(2).setCellValue("Sexo");
         Row otrafila = hoja.createRow(1);
         otrafila.createCell(0).setCellValue(nombre);
         otrafila.createCell(1).setCellValue(nacionalidad);
         otrafila.createCell(2).setCellValue(sexo);
    }
    public void guardarFormacionAcademica(List<HashMap<String,String>>formaciones,Workbook wb){
        Sheet hoja = wb.createSheet(WorkbookUtil.createSafeSheetName("Formación academica"));
         Row fila =  hoja.createRow(0);
         fila.createCell(0).setCellValue("Tipo");
         fila.createCell(1).setCellValue("Lugar");
         fila.createCell(2).setCellValue("Titulo");
         fila.createCell(3).setCellValue("Fecha");
         fila.createCell(4).setCellValue("Descripción");
//         System.out.println(formaciones.get(0).get("tipoFormacion"));
//         System.out.println(formaciones.get(0).get("lugarFormacion"));
//         System.out.println(formaciones.get(1).get("tipoFormacion"));
//         System.out.println(formaciones.get(1).get("lugarFormacion"));
//         System.out.println(formaciones.get(2).get("tipoFormacion"));
//         System.out.println(formaciones.get(2).get("lugarFormacion"));
         for (int i = 0; i < formaciones.size(); i++) {
         Row otraFila =  hoja.createRow(i+1);
//         System.out.println(formaciones.get(i).get("tipoFormacion"));
         otraFila.createCell(0).setCellValue(formaciones.get(i).get("tipoFormacion"));
          otraFila.createCell(1).setCellValue(formaciones.get(i).get("lugarFormacion"));
           otraFila.createCell(2).setCellValue(formaciones.get(i).get("tituloFormacion"));
            otraFila.createCell(3).setCellValue(formaciones.get(i).get("fechaRealizacion"));
             otraFila.createCell(4).setCellValue(formaciones.get(i).get("descripcion"));
        }
    }
    private void guardarExpProf(List<String> expLaboral, Workbook workbook) {
        Sheet hoja = workbook.createSheet(WorkbookUtil.createSafeSheetName("Experiencia profesional"));
        hoja.createRow(0).createCell(0).setCellValue("Experiencia");
        for (int i = 0; i < expLaboral.size(); i++) {
              Row fila = hoja.createRow(i+1);
              fila.createCell(0).setCellValue(expLaboral.get(i));
        }    
    }
    private void guardarInvestigacion(List<LineaInvestigacion> lineasDeInvestigaciones, Workbook workbook) {
        Sheet hoja = workbook.createSheet(WorkbookUtil.createSafeSheetName("Lineas de Investigación"));
        Row fila = hoja.createRow(0);
        fila.createCell(0).setCellValue("Titulo");
        fila.createCell(1).setCellValue("Activo");
        for (int i = 0; i < lineasDeInvestigaciones.size(); i++) {
              Row otrafila = hoja.createRow(i+1);
              otrafila.createCell(0).setCellValue(lineasDeInvestigaciones.get(i).getTitulo());
              otrafila.createCell(1).setCellValue(lineasDeInvestigaciones.get(i).isActiva());
        }  
    }
    public void persistir(OutputStream stream) throws IOException{
        workbook.write(stream);
    }
    
    
    
}
