/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes.dao;

import java.util.List;
import sistemapasajes.modelo.DcomprobanteModel;

/**
 *
 * @author edson
 */
public interface DcomprobanteDAO {
        DcomprobanteModel obtenerDComprobanteporID (int idComp);
    DcomprobanteModel obtenerDcomprobanteporNum(String numserieDComp);
    List<DcomprobanteModel> obtenerTodosDcomprobantes();
    void agregarDcomprobante (DcomprobanteModel dcomprobante);
    void actualizarComprobante  (DcomprobanteModel dcomprobante);
    void eliminarDcomprobante (int idDcomp);  
}
