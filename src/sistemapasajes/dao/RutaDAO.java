/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes.dao;

/**
 *
 * @author edson
 */
import sistemapasajes.modelo.RutaModel;
import java.util.List;

public interface RutaDAO {
    
    RutaModel obtenerRutaporId (int idRuta);
    List<RutaModel> obtenerTodasRutas();
    void agregarRuta(RutaModel ruta);
    void actualizarRuta(RutaModel ruta);
    void eliminarRuta (int idRuta);
}
