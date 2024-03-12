/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes.dao;

import java.util.List;
import sistemapasajes.modelo.AsignacionRutaModel;

/**
 *
 * @author edson
 */
public interface RutaAsignadaDAO {
      AsignacionRutaModel obtenerAsignacionRutaporId (int idAsignacionRuta);
    AsignacionRutaModel obtenerRutaporVehiculo (int vehiculoAsignacionRuta);
    List<AsignacionRutaModel> obtenerTodasAsignacionRutas();
    List<AsignacionRutaModel> obtenerAsignacionRutasporFecha(String fechaAsignacionRuta, int rutaAsignacionruta);
    List<AsignacionRutaModel> obtenerAsignacionRutasporFechaTotal(String fechaAsignacionRuta);
    void agregarRutaAsignada(AsignacionRutaModel asignacionruta);
    void actualizarRuta(AsignacionRutaModel asignacionruta);
    void eliminarAsignacionRuta (int idAsignacionRuta);
}
