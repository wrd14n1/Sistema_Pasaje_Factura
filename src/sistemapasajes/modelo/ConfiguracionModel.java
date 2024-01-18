/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes.modelo;

/**
 *
 * @author edson
 */
public class ConfiguracionModel {
    private int idConf;
    private String rucConf;
    private String razonConf;
    private String nomcomConf;
    private String direcConf;
    private String rutaSunat;
    private String rutaArchivo;
    private String logoConf;
    private String txt1Conf;
    private String txt2Conf;
    private String txt3Conf;
    

    public ConfiguracionModel() {
        // Constructor vacío necesario para algunos frameworks y operaciones de base de datos
    }

    public ConfiguracionModel(int idConf, String rucConf, String razonConf, String nomcomConf, String direcConf, String rutaSunat, String rutaArchivo,
            String logoConf, String txt1Conf, String txt2Conf, String txt3Conf) {
        /*public ConfiguracionModel(int idConf, String rucConf, String razonConf, String nomcomConf, String direcConf, String rutaSunat, String rutaArchivo) {*/
        this.idConf = idConf;
        this.rucConf = rucConf;
        this.razonConf = razonConf;
        this.nomcomConf = nomcomConf;
        this.direcConf = direcConf;
        this.rutaSunat = rutaSunat;
        this.rutaArchivo = rutaArchivo;
        this.logoConf = logoConf;
        this.txt1Conf = txt1Conf;
        this.txt2Conf = txt2Conf;
        this.txt3Conf = txt3Conf;
    }

    // Getters y setters

    public int getIdConf() {
        return idConf;
    }

    public void setIdConf(int idConf) {
        this.idConf = idConf;
    }

    public String getRucConf() {
        return rucConf;
    }

    public void setRucConf(String rucConf) {
        this.rucConf = rucConf;
    }

    public String getRazonConf() {
        return razonConf;
    }

    public void setRazonConf(String razonConf) {
        this.razonConf = razonConf;
    }

    public String getNomcomConf() {
        return nomcomConf;
    }

    public void setNomcomConf(String nomcomConf) {
        this.nomcomConf = nomcomConf;
    }

    public String getDirecConf() {
        return direcConf;
    }

    public void setDirecConf(String direcConf) {
        this.direcConf = direcConf;
    }
    public String getRutaSunat(){
        return rutaSunat;
    }
    public void setRutaSunat(String rutaSunat){
        this.rutaSunat = rutaSunat;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public String getLogoConf() {
        return logoConf;
    }

    public void setLogoConf(String logoConf) {
        this.logoConf = logoConf;
    }

    public String getTxt1Conf() {
        return txt1Conf;
    }

    public void setTxt1Conf(String txt1Conf) {
        this.txt1Conf = txt1Conf;
    }

    public String getTxt2Conf() {
        return txt2Conf;
    }

    public void setTxt2Conf(String txt2Conf) {
        this.txt2Conf = txt2Conf;
    }

    public String getTxt3Conf() {
        return txt3Conf;
    }

    public void setTxt3Conf(String txt3Conf) {
        this.txt3Conf = txt3Conf;
    }
    
}