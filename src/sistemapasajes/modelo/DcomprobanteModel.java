/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes.modelo;

/**
 *
 * @author edson
 */
public class DcomprobanteModel {
    private int idDcomp;
    private String numserieDcomp;
    private int itemDcomp;
    private int cantidadDcomp;
    private String tipunidDcomp;
    private String productoDcomp;
    private String descripcionDcomp;
    private Double valorunitarioDcomp;
    private Double preciounitarioDcomp;
    
    public DcomprobanteModel(){
        
    }
    public DcomprobanteModel( int idDcomp, String numserieDcomp, int itemDcomp, int cantidadDcomp, String tipunidDcomp,
            String productoDcomp, String descripcionDcomp, Double valorunitarioDcomp, Double preciounitarioDcomp){
        this.idDcomp=idDcomp;
        this.numserieDcomp=numserieDcomp;
        this.itemDcomp =itemDcomp; 
        this.cantidadDcomp= cantidadDcomp;
        this.tipunidDcomp=tipunidDcomp;
        this.productoDcomp=productoDcomp;
        this.descripcionDcomp=descripcionDcomp;
        this.valorunitarioDcomp= valorunitarioDcomp;
        this.preciounitarioDcomp=preciounitarioDcomp;
    }

    public int getIdDcomp() {
        return idDcomp;
    }

    public void setIdDcomp(int idDcomp) {
        this.idDcomp = idDcomp;
    }

    public String getNumserieDcomp() {
        return numserieDcomp;
    }

    public void setNumserieDcomp(String numserieDcomp) {
        this.numserieDcomp = numserieDcomp;
    }

    public int getItemDcomp() {
        return itemDcomp;
    }

    public void setItemDcomp(int itemDcomp) {
        this.itemDcomp = itemDcomp;
    }

    public int getCantidadDcomp() {
        return cantidadDcomp;
    }

    public void setCantidadDcomp(int cantidadDcomp) {
        this.cantidadDcomp = cantidadDcomp;
    }

    public String getTipunidDcomp() {
        return tipunidDcomp;
    }

    public void setTipunidDcomp(String tipunidDcomp) {
        this.tipunidDcomp = tipunidDcomp;
    }

    public String getProductoDcomp() {
        return productoDcomp;
    }

    public void setProductoDcomp(String productoDcomp) {
        this.productoDcomp = productoDcomp;
    }

    public String getDescripcionDcomp() {
        return descripcionDcomp;
    }

    public void setDescripcionDcomp(String descripcionDcomp) {
        this.descripcionDcomp = descripcionDcomp;
    }

    public Double getValorunitarioDcomp() {
        return valorunitarioDcomp;
    }

    public void setValorunitarioDcomp(Double valorunitarioDcomp) {
        this.valorunitarioDcomp = valorunitarioDcomp;
    }

    public Double getPreciounitarioDcomp() {
        return preciounitarioDcomp;
    }

    public void setPreciounitarioDcomp(Double preciounitarioDcomp) {
        this.preciounitarioDcomp = preciounitarioDcomp;
    }
    
    
}
