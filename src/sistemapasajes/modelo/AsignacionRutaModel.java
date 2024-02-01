/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes.modelo;

/**
 *
 * @author edson
 */
public class AsignacionRutaModel {
    private int idAsigRuta;
    private String fechaAsigRuta;
    private String horaAsigRuta;
    private int vehiculoAsigRuta;
    private int rutaAsigRuta;
    private String origenAsigRuta;
    private String destinoAsigRuta;
    
    public AsignacionRutaModel(){
        
    }
    public AsignacionRutaModel( int idAsigRuta, String fechaAsigRuta, String horaAsigRuta, int vehiculoAsigRuta, int rutaAsigRuta,
            String origenAsigRuta, String destinoAsigRuta ){
        this.idAsigRuta=idAsigRuta;
        this.fechaAsigRuta = fechaAsigRuta;
        this.horaAsigRuta=horaAsigRuta;
        this.vehiculoAsigRuta=vehiculoAsigRuta;
        this.rutaAsigRuta=rutaAsigRuta;        
    }

    public int getIdAsigRuta() {
        return idAsigRuta;
    }

    public void setIdAsigRuta(int idAsigRuta) {
        this.idAsigRuta = idAsigRuta;
    }

    public String getFechaAsigRuta() {
        return fechaAsigRuta;
    }

    public void setFechaAsigRuta(String fechaAsigRuta) {
        this.fechaAsigRuta = fechaAsigRuta;
    }

    public String getHoraAsigRuta() {
        return horaAsigRuta;
    }

    public void setHoraAsigRuta(String horaAsigRuta) {
        this.horaAsigRuta = horaAsigRuta;
    }

    public int getVehiculoAsigRuta() {
        return vehiculoAsigRuta;
    }

    public void setVehiculoAsigRuta(int vehiculoAsigRuta) {
        this.vehiculoAsigRuta = vehiculoAsigRuta;
    }

    public int getRutaAsigRuta() {
        return rutaAsigRuta;
    }

    public void setRutaAsigRuta(int rutaAsigRuta) {
        this.rutaAsigRuta = rutaAsigRuta;
    }

    public String getOrigenAsigRuta() {
        return origenAsigRuta;
    }

    public void setOrigenAsigRuta(String origenAsigRuta) {
        this.origenAsigRuta = origenAsigRuta;
    }

    public String getDestinoAsigRuta() {
        return destinoAsigRuta;
    }

    public void setDestinoAsigRuta(String destinoAsigRuta) {
        this.destinoAsigRuta = destinoAsigRuta;
    }
    
}
