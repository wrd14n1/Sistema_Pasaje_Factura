/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes.dao;

import java.util.List;
import sistemapasajes.modelo.SerieModel;

/**
 *
 * @author edson
 */
public interface SerieDAO {
    SerieModel obtenerSerieporId (int idSerie);
     SerieModel obtenerTipoSerie (String TipoSerie, String CodSerie);
      SerieModel obtenerNumSerie (int idSerie);
    List<SerieModel> obtenerTodasSeries();
    void agregarSerie (SerieModel serie);
    void actualizarSerie (SerieModel serie);
    void actualizarNumSerie(SerieModel serie);
    void eliminarSerie (int idSerie);
}
