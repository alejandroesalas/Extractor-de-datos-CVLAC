/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cecar.modelo;

/**
 *
 * @author Jhonnys
 */
public class LineaInvestigacion {
    private String titulo;
    private boolean activa;

    public LineaInvestigacion(String titulo, boolean activa) {
        this.titulo = titulo;
        this.activa = activa;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
    
}
