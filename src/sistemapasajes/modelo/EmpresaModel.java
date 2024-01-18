/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes.modelo;

/**
 *
 * @author edson
 */
public class EmpresaModel {
    private int idEmp;
    private String tipodocEmp;
    private String numdocEmp;
    private String razonEmp;
    private String direccionEmp;
    private String ubigeoEmp;
    private String distritoEmp;
    private String provinciaEmp;
    private String departamentoEmp;
    
    public EmpresaModel(){
        
    }
    
    public EmpresaModel (int idEmp, String tipodocEmp, String numdocEmp, String razonEmp,
            String direccionEmp, String ubigeoEmp, String distritoEmp, String provinciaEmp,
            String departamentoEmp){
        this.idEmp = idEmp;
        this.tipodocEmp=tipodocEmp;
        this.numdocEmp=numdocEmp;
        this.razonEmp = razonEmp;
        this.direccionEmp=direccionEmp;
        this.ubigeoEmp = ubigeoEmp;
        this.distritoEmp = distritoEmp;
        this.provinciaEmp = provinciaEmp;
        this.departamentoEmp = departamentoEmp;
        
    }

    public int getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(int idEmp) {
        this.idEmp = idEmp;
    }

    public String getTipodocEmp() {
        return tipodocEmp;
    }

    public void setTipodocEmp(String tipodocEmp) {
        this.tipodocEmp = tipodocEmp;
    }

    public String getNumdocEmp() {
        return numdocEmp;
    }

    public void setNumdocEmp(String numdocEmp) {
        this.numdocEmp = numdocEmp;
    }

    public String getRazonEmp() {
        return razonEmp;
    }

    public void setRazonEmp(String razonEmp) {
        this.razonEmp = razonEmp;
    }

    public String getDireccionEmp() {
        return direccionEmp;
    }

    public void setDireccionEmp(String direccionEmp) {
        this.direccionEmp = direccionEmp;
    }

    public String getUbigeoEmp() {
        return ubigeoEmp;
    }

    public void setUbigeoEmp(String ubigeoEmp) {
        this.ubigeoEmp = ubigeoEmp;
    }

    public String getDistritoEmp() {
        return distritoEmp;
    }

    public void setDistritoEmp(String distritoEmp) {
        this.distritoEmp = distritoEmp;
    }

    public String getProvinciaEmp() {
        return provinciaEmp;
    }

    public void setProvinciaEmp(String provinciaEmp) {
        this.provinciaEmp = provinciaEmp;
    }

    public String getDepartamentoEmp() {
        return departamentoEmp;
    }

    public void setDepartamentoEmp(String departamentoEmp) {
        this.departamentoEmp = departamentoEmp;
    }
    
}
