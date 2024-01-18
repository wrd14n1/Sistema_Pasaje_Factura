/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes.dao;

import java.util.List;
import sistemapasajes.modelo.PasajeroModel;

/**
 *
 * @author edson
 */
public interface PasajeroDAO {
    PasajeroModel obtenerPasajeroPorId (int idPsje);
    PasajeroModel obtenerPasajeroPorDNI (String numdocPsje);
    List<PasajeroModel> obtenerTodosPasajeros();
    void agregarPasajero(PasajeroModel pasajero);
    void actualizarPasajero (PasajeroModel pasajero);
    void eliminarPasajero(int idPsje);
}
