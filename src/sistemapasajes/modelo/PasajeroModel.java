/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes.modelo;

/**
 *
 * @author edson
 */
public class PasajeroModel {
    private int idPsje;
    private String numdocPsje;
    private String apePsje;
    private String nomPsje;
    
    public PasajeroModel(){
        
    } 
    
    public PasajeroModel(int idPasaje, String numdocPsje, String apePsje, String nomPsje){
        this.idPsje=idPasaje;
        this.numdocPsje=numdocPsje;
        this.nomPsje = nomPsje;
        this.apePsje=apePsje;        
    }

    public int getIdPsje() {
        return idPsje;
    }

    public void setIdPsje(int idPsje) {
        this.idPsje = idPsje;
    }

    public String getNumdocPsje() {
        return numdocPsje;
    }

    public void setNumdocPsje(String numdocPsje) {
        this.numdocPsje = numdocPsje;
    }

    public String getApePsje() {
        return apePsje;
    }

    public void setApePsje(String apePsje) {
        this.apePsje = apePsje;
    }

    public String getNomPsje() {
        return nomPsje;
    }

    public void setNomPsje(String nomPsje) {
        this.nomPsje = nomPsje;
    }
    
    
}
