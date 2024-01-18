/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes.dao;

/**
 *
 * @author edson
 */

import sistemapasajes.modelo.ConfiguracionModel;
import java.util.List;

public interface ConfiguracionDAO {
    ConfiguracionModel obtenerConfiguracionPorId(int idConf);
    ConfiguracionModel obtenerConfiguracionPorRuc(String rucConf);
    List<ConfiguracionModel> obtenerTodasConfiguraciones();
    void agregarConfiguracion(ConfiguracionModel configuracion);
    void actualizarConfiguracion(ConfiguracionModel configuracion);
    void eliminarConfiguracion(int idConf);
}