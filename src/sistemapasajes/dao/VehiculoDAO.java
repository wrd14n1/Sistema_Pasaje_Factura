/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes.dao;

/**
 *
 * @author edson
 */

import sistemapasajes.modelo.VehiculoModel;

import java.util.List;

public interface VehiculoDAO {
    VehiculoModel obtenerVehiculoPorId(int idVehi);
    List<VehiculoModel> obtenerTodosVehiculos();
    void agregarVehiculo(VehiculoModel vehiculo);
    void actualizarVehiculo(VehiculoModel vehiculo);
    void eliminarVehiculo(int idVehi);
}