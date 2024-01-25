/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes.modelo;

/**
 *
 * @author edson
 */
public class RutaArchivoModel {

    private int idRutaArchivo;
    private String descRutaArchivo;
    private String tituloRutaArchivo;
    private String empRutaArchivo;

    public RutaArchivoModel() {

    }

    public RutaArchivoModel(int idRutaArchivo, String descRutaArchivo, String tituloRutaArchivo, String empRutaArchivo) {
        this.idRutaArchivo = idRutaArchivo;
        this.descRutaArchivo = descRutaArchivo;
        this.tituloRutaArchivo = tituloRutaArchivo;
        this.empRutaArchivo = empRutaArchivo;
    }

    public int getIdRutaArchivo() {
        return idRutaArchivo;
    }

    public void setIdRutaArchivo(int idRutaArchivo) {
        this.idRutaArchivo = idRutaArchivo;
    }

    public String getDescRutaArchivo() {
        return descRutaArchivo;
    }

    public void setDescRutaArchivo(String descRutaArchivo) {
        this.descRutaArchivo = descRutaArchivo;
    }

    public String getTituloRutaArchivo() {
        return tituloRutaArchivo;
    }

    public void setTituloRutaArchivo(String tituloRutaArchivo) {
        this.tituloRutaArchivo = tituloRutaArchivo;
    }

    public String getEmpRutaArchivo() {
        return empRutaArchivo;
    }

    public void setEmpRutaArchivo(String empRutaArchivo) {
        this.empRutaArchivo = empRutaArchivo;
    }

}
