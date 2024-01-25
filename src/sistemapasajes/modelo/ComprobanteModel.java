/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes.modelo;

import java.util.Date;

/**
 *
 * @author edson
 */
public class ComprobanteModel {
    private int idComp;
    private String tipoComp;
    private String serieComp;
    private String docclienteComp;
    private String clienteComp;
    private String fechaComp;
    private String horaComp;
    private String monedaComp;
    private String mediopagoComp;
    private Double totalventgravComp;
    private Double igvComp;
    private Double imptotalComp;
    private String hashComp;
    private String fechaxmlComp;
    private String fechaenvioComp;
    private String estadoComp;
    
    public ComprobanteModel(){
        
    }
    
    public ComprobanteModel(int idComp, String tipoComp, String serieComp, String docclienteComp, String clienteComp, String fechaComp,
            String horaComp,String monedaComp, String mediopagoComp, Double totalventagravComp, Double igvComp, Double imptotalComp,
            String hashComp,String fechaxmlComp, String fechaenvioComp, String estadoComp){
        this.idComp=idComp;
        this.tipoComp=tipoComp;
        this.serieComp=serieComp;
        this.docclienteComp=docclienteComp;
        this.clienteComp=clienteComp;
        this.fechaComp=fechaComp;
        this.horaComp=horaComp;
        this.monedaComp=monedaComp;
        this.mediopagoComp=mediopagoComp;
        this.totalventgravComp=totalventagravComp;
        this.igvComp=igvComp;
        this.imptotalComp=imptotalComp;
        this.hashComp=hashComp;
        this.fechaxmlComp=fechaxmlComp;
        this.fechaenvioComp=fechaenvioComp;
        this.estadoComp = estadoComp;
        
    }

    public int getIdComp() {
        return idComp;
    }

    public void setIdComp(int idComp) {
        this.idComp = idComp;
    }

    public String getTipoComp() {
        return tipoComp;
    }

    public void setTipoComp(String tipoComp) {
        this.tipoComp = tipoComp;
    }

    public String getSerieComp() {
        return serieComp;
    }

    public void setSerieComp(String serieComp) {
        this.serieComp = serieComp;
    }

    public String getClienteComp() {
        return clienteComp;
    }

    public void setClienteComp(String clienteComp) {
        this.clienteComp = clienteComp;
    }

    public String getFechaComp() {
        return fechaComp;
    }

    public void setFechaComp(String fechaComp) {
        this.fechaComp = fechaComp;
    }

    public String getMonedaComp() {
        return monedaComp;
    }

    public void setMonedaComp(String monedaComp) {
        this.monedaComp = monedaComp;
    }

    public String getMediopagoComp() {
        return mediopagoComp;
    }

    public void setMediopagoComp(String mediopagoComp) {
        this.mediopagoComp = mediopagoComp;
    }

    public Double getTotalventgravComp() {
        return totalventgravComp;
    }

    public void setTotalventgravComp(Double totalventgravComp) {
        this.totalventgravComp = totalventgravComp;
    }

    public Double getIgvComp() {
        return igvComp;
    }

    public void setIgvComp(Double igvComp) {
        this.igvComp = igvComp;
    }

    public Double getImptotalComp() {
        return imptotalComp;
    }

    public void setImptotalComp(Double imptotalComp) {
        this.imptotalComp = imptotalComp;
    }

    public String getFechaxmlComp() {
        return fechaxmlComp;
    }

    public void setFechaxmlComp(String fechaxmlComp) {
        this.fechaxmlComp = fechaxmlComp;
    }

    public String getFechaenvioComp() {
        return fechaenvioComp;
    }

    public void setFechaenvioComp(String fechaenvioComp) {
        this.fechaenvioComp = fechaenvioComp;
    }

    public String getEstadoComp() {
        return estadoComp;
    }

    public void setEstadoComp(String estadoComp) {
        this.estadoComp = estadoComp;
    }

    public String getDocclienteComp() {
        return docclienteComp;
    }

    public void setDocclienteComp(String docclienteComp) {
        this.docclienteComp = docclienteComp;
    }

    public String getHoraComp() {
        return horaComp;
    }

    public void setHoraComp(String horaComp) {
        this.horaComp = horaComp;
    }

    public String getHashComp() {
        return hashComp;
    }

    public void setHashComp(String hashComp) {
        this.hashComp = hashComp;
    }
    
    
}
