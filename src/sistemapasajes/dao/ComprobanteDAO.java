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
    List<ComprobanteModel> obtenerComprobantesporEstado(String estadoComp);
    void agregarComprobante (ComprobanteModel comprobante);
    void actualizarComprobante  (ComprobanteModel comprobante);
    void actualizarPorEstadoComprobante  (ComprobanteModel comprobante);
    void eliminarComprobante (int idComp);    
    void actualizarComprobanteHash(ComprobanteModel comprobante);
    List<ComprobanteModel> reporteComprobante (String fechaIni, String fechafin);
}
