/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes.modelo;

/**
 *
 * @author edson
 */
public class SerieModel {
    private int idSerie;
    private String tipoSerie;
    private String codsunatSerie;
    private String codSerie;
    private int numSerie;
    
    public SerieModel(){
        
    }
    public SerieModel(int idSerie, String tipoSerie, String codsunatSerie, String codSerie, int numSerie ){
        this.idSerie = idSerie;
        this.tipoSerie = tipoSerie;
        this.codsunatSerie= codsunatSerie;
        this.numSerie = numSerie;
        this.codSerie=codSerie;
        
    } 

    public int getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(int idSerie) {
        this.idSerie = idSerie;
    }

    public String getTipoSerie() {
        return tipoSerie;
    }

    public void setTipoSerie(String tipoSerie) {
        this.tipoSerie = tipoSerie;
    }

    public String getCodsunatSerie() {
        return codsunatSerie;
    }

    public void setCodsunatSerie(String codsunatSerie) {
        this.codsunatSerie = codsunatSerie;
    }

    public int getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(int numSerie) {
        this.numSerie = numSerie;
    }

    public String getCodSerie() {
        return codSerie;
    }

    public void setCodSerie(String codSerie) {
        this.codSerie = codSerie;
    }
    
}
