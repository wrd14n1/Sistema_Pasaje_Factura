/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes.modelo;

/**
 *
 * @author edson
 */
public class RutaModel {
   private int idRuta;
   private String tramoRuta;
   private double precioRuta;
   private String punto1Ruta;
   private String punto2Ruta;
   
   public RutaModel(){
       
   }
   public RutaModel(int idRuta, String tramoRuta, double precioRuta, String punto1Ruta, String punto2Ruta){
       this.idRuta=idRuta;
       this.tramoRuta = tramoRuta;
       this.precioRuta = precioRuta;
       this.punto1Ruta=punto1Ruta;
       this.punto2Ruta=punto2Ruta;
   }

    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

    public String getTramoRuta() {
        return tramoRuta;
    }

    public void setTramoRuta(String tramoRuta) {
        this.tramoRuta = tramoRuta;
    }

    public double getPrecioRuta() {
        return precioRuta;
    }

    public void setPrecioRuta(double precioRuta) {
        this.precioRuta = precioRuta;
    }

    public String getPunto1Ruta() {
        return punto1Ruta;
    }

    public void setPunto1Ruta(String punto1Ruta) {
        this.punto1Ruta = punto1Ruta;
    }

    public String getPunto2Ruta() {
        return punto2Ruta;
    }

    public void setPunto2Ruta(String punto2Ruta) {
        this.punto2Ruta = punto2Ruta;
    }


   
}
