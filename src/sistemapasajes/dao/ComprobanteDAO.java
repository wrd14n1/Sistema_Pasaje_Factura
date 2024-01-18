/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes.dao;

import java.util.List;
import sistemapasajes.modelo.ComprobanteModel;

/**
 *
 * @author edson
 */
public interface ComprobanteDAO {
    
    ComprobanteModel obtenerComprobanteporID (int idComp);
    ComprobanteModel obtenerComprobanteporNum(String numdocComp);
    List<ComprobanteModel> obtenerTodosComprobantes();
    void agregarComprobante (ComprobanteModel comprobante);
    void actualizarComprobante  (ComprobanteModel comprobante);
    void eliminarComprobante (int idComp);    
    
}
