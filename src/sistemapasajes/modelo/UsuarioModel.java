/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes.modelo;

/**
 *
 * @author edson
 */
public class UsuarioModel {
    private int idUsua;
    private String datosUsua;
    private String usuarioUsua;
    private String claveUsua;
    
    public UsuarioModel(){
        
    }
    public UsuarioModel(int idUsua, String datosUsua, String usuarioUsua, String claveUsua){
        this.idUsua=idUsua;
        this.datosUsua=datosUsua;
        this.usuarioUsua=usuarioUsua;
        this.claveUsua=claveUsua;
        
    }

    public int getIdUsua() {
        return idUsua;
    }

    public void setIdUsua(int idUsua) {
        this.idUsua = idUsua;
    }

    public String getDatosUsua() {
        return datosUsua;
    }

    public void setDatosUsua(String datosUsua) {
        this.datosUsua = datosUsua;
    }

    public String getUsuarioUsua() {
        return usuarioUsua;
    }

    public void setUsuarioUsua(String usuarioUsua) {
        this.usuarioUsua = usuarioUsua;
    }

    public String getClaveUsua() {
        return claveUsua;
    }

    public void setClaveUsua(String claveUsua) {
        this.claveUsua = claveUsua;
    }
    
}
