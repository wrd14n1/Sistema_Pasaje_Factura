/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes.dao;

import java.util.List;
import sistemapasajes.modelo.RutaArchivoModel;


/**
 *
 * @author edson
 */
public interface RutaArchivoDAO {
    
    RutaArchivoModel obtenerRutaporId (int idRutaArchivo);
    RutaArchivoModel obtenerRutaporEmpresa (String empRutaArchivo, String hash);
    List<RutaArchivoModel> obtenerTodasRutasArchivo();
    void agregarRutaArchivo(RutaArchivoModel rutaarchivo);
    void actualizarRuta(RutaArchivoModel rutaarchivo);
    void eliminarRuta (int idRutaArchivo);
}
