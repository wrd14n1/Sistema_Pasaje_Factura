/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes.modelo;

/**
 *
 * @author edson
 */
public class ApiModel {
    private int idApi;
    private String urlApi;
    private String descApi;
    
    public ApiModel(){
        
    }
    
    public ApiModel(int idApi, String urlApi, String descApi){
        this.idApi=idApi;
        this.urlApi=urlApi;
        this.descApi=descApi;
    }

    public int getIdApi() {
        return idApi;
    }

    public void setIdApi(int idApi) {
        this.idApi = idApi;
    }

    public String getUrlApi() {
        return urlApi;
    }

    public void setUrlApi(String urlApi) {
        this.urlApi = urlApi;
    }

    public String getDescApi() {
        return descApi;
    }

    public void setDescApi(String descApi) {
        this.descApi = descApi;
    }
    
}
