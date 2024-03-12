/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes.dao;

import java.util.List;
import sistemapasajes.modelo.EncomiendaModel;

/**
 *
 * @author Edson Cusicuna
 */
public interface EncomiendaDAO {
    EncomiendaModel obtenerEncomiendaporid (int idEnco);
    List<EncomiendaModel> obtenerTodasEnciomiendas();
    void agregarEncomienda (EncomiendaModel encomienda);
    void actualizarEncomienda (EncomiendaModel encomienda);
    void eliminarEncomienda (int idEnco);
}
