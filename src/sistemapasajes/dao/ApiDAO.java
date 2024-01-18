/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes.dao;

import java.util.List;
import sistemapasajes.modelo.ApiModel;

/**
 *
 * @author edson
 */
public interface ApiDAO {
    ApiModel obtenerApiPorId(int idApi);
    List<ApiModel> obtenerTodasApis();
    void agregarApi(ApiModel api);
    void actualizarApi(ApiModel api);
    void eliminarApi(int idApi);
   
}
