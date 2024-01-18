/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes.dao;

import java.util.List;
import sistemapasajes.modelo.EmpresaModel;

/**
 *
 * @author edson
 */
public interface EmpresaDAO {
    EmpresaModel obtenerEmpresaporId (int idEmp);
    List<EmpresaModel> obtenerTodasEmpresas();
    void agregarEmpresa (EmpresaModel empresa);
    void actualizarEmpresa (EmpresaModel empresa);
    void eliminarEmpresa(int idEmp);  
    EmpresaModel obtenerEmpresaporRuc(String numdocEmp);
    
}
