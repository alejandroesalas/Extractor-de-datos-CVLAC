/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cecar.modelo;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Jhonnys
 */
public final class Investigador {
    private String nombre;
    private String nacionalidad;
    private String sexo;   
    private List<HashMap<String,String>>formacionesAcademicas;
    private List<String> expLaboral;
    private List<LineaInvestigacion> lineasDeInvestigaciones;

    public Investigador() {
    }

    public Investigador(String nombre, String nacionalidad, String sexo) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.sexo = sexo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public List<HashMap<String, String>> getFormacionesAcademicas() {
        return formacionesAcademicas;
    }

    public void setFormacionesAcademicas(List<HashMap<String, String>> formacionesAcademicas) {
        this.formacionesAcademicas = formacionesAcademicas;
    }

    public List<String> getExpLaboral() {
        return expLaboral;
    }

    public void setExpLaboral(List<String> expLaboral) {
        this.expLaboral = expLaboral;
    }

    public List<LineaInvestigacion> getLineasDeInvestigaciones() {
        return lineasDeInvestigaciones;
    }

    public void setLineasDeInvestigaciones(List<LineaInvestigacion> lineasDeInvestigaciones) {
        this.lineasDeInvestigaciones = lineasDeInvestigaciones;
    }
    
}
