/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes.modelo;

/**
 *
 * @author edson
 */
public class VehiculoModel {

    private int idVehi;
    private String descVehi;
    private String placaVehi;
    private int numVehi;
    private String condVehi;

    public VehiculoModel() {

    }

    public VehiculoModel(int idVehi, String descVehi, String placaVehi, int numVehi, String condVehi) {
        this.idVehi = idVehi;
        this.descVehi = descVehi;
        this.placaVehi = placaVehi;
        this.numVehi = numVehi;
        this.condVehi = condVehi;

    }

    public int getIdVehi() {
        return idVehi;
    }

    public void setIdVehi(int idVehi) {
        this.idVehi = idVehi;
    }

    public String getDescVehi() {
        return descVehi;
    }

    public void setDescVehi(String descVehi) {
        this.descVehi = descVehi;
    }

    public String getPlacaVehi() {
        return placaVehi;
    }

    public void setPlacaVehi(String placaVehi) {
        this.placaVehi = placaVehi;
    }

    public int getNumVehi() {
        return numVehi;
    }

    public void setNumVehi(int numVehi) {
        this.numVehi = numVehi;
    }

    public String getCondVehi() {
        return condVehi;
    }

    public void setCondVehi(String condVehi) {
        this.condVehi = condVehi;
    }
    
}
