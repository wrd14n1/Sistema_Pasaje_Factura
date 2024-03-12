/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes.modelo;

/**
 *
 * @author Edson Cusicuna
 */
public class EncomiendaModel {
    private int idEnco;
    private String fechaEnco;
    private String descEnco;
    private String unidadEnco;
    private int cantidadEnco;
    private String origenEnco;
    private String destinoEnco;
    private double precioEnco;
    private int comprobanteEnco;
    private String docremiEnco;
    private String remiEnco;
    private String docdestEnco;
    private String destiEnco;
    
    public EncomiendaModel(){
        
    }
    public EncomiendaModel( int idEnco, String fechaEnco, String descEnco, String unidadEnco,
            int cantidadEnco, String origenEnco, String destinoEnco, double precioEnco, int comprobanteEnco,
            String docremiEnco, String remiEnco, String docdestEnco, String destiEnco ){
        this.idEnco=idEnco;
        this.fechaEnco=fechaEnco;
        this.descEnco=descEnco;
        this.unidadEnco = unidadEnco;
        this.cantidadEnco=cantidadEnco;
        this.origenEnco = origenEnco;
        this.destinoEnco=destinoEnco;
        this.precioEnco=precioEnco;
        this.comprobanteEnco=comprobanteEnco;
        this.docremiEnco = docremiEnco;
        this.remiEnco=remiEnco;
        this.docdestEnco=docdestEnco;
        this.destiEnco=destiEnco;
    }

    public int getIdEnco() {
        return idEnco;
    }

    public void setIdEnco(int idEnco) {
        this.idEnco = idEnco;
    }

    public String getFechaEnco() {
        return fechaEnco;
    }

    public void setFechaEnco(String fechaEnco) {
        this.fechaEnco = fechaEnco;
    }

    public String getDescEnco() {
        return descEnco;
    }

    public void setDescEnco(String descEnco) {
        this.descEnco = descEnco;
    }

    public String getUnidadEnco() {
        return unidadEnco;
    }

    public void setUnidadEnco(String unidadEnco) {
        this.unidadEnco = unidadEnco;
    }

    public int getCantidadEnco() {
        return cantidadEnco;
    }

    public void setCantidadEnco(int cantidadEnco) {
        this.cantidadEnco = cantidadEnco;
    }

    public String getOrigenEnco() {
        return origenEnco;
    }

    public void setOrigenEnco(String origenEnco) {
        this.origenEnco = origenEnco;
    }

    public String getDestinoEnco() {
        return destinoEnco;
    }

    public void setDestinoEnco(String destinoEnco) {
        this.destinoEnco = destinoEnco;
    }

    public double getPrecioEnco() {
        return precioEnco;
    }

    public void setPrecioEnco(double precioEnco) {
        this.precioEnco = precioEnco;
    }

    public int getComprobanteEnco() {
        return comprobanteEnco;
    }

    public void setComprobanteEnco(int comprobanteEnco) {
        this.comprobanteEnco = comprobanteEnco;
    }

    public String getDocremiEnco() {
        return docremiEnco;
    }

    public void setDocremiEnco(String docremiEnco) {
        this.docremiEnco = docremiEnco;
    }

    public String getRemiEnco() {
        return remiEnco;
    }

    public void setRemiEnco(String remiEnco) {
        this.remiEnco = remiEnco;
    }

    public String getDocdestEnco() {
        return docdestEnco;
    }

    public void setDocdestEnco(String docdestEnco) {
        this.docdestEnco = docdestEnco;
    }

    public String getDestiEnco() {
        return destiEnco;
    }

    public void setDestiEnco(String destiEnco) {
        this.destiEnco = destiEnco;
    }
    
    
}
