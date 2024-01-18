/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import sistemapasajes.Conexion;
import sistemapasajes.modelo.EmpresaModel;

/**
 *
 * @author edson
 */
public class EmpresaDAOImpl implements EmpresaDAO{
    
    private Connection conexion;
    
    public EmpresaDAOImpl (){
        Conexion conexionInstance = new Conexion();
               try {
            this.conexion = conexionInstance.conectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al conectar con la Base de Datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexion desconectar = new Conexion();
            try {
                desconectar.desconectar();
                System.out.println("Desconexión exitosa.");
            } catch (SQLException ex) {
                System.out.println("Error al desconectar" + ex.getMessage());
            }
        }
    }
    
    @Override
    public EmpresaModel obtenerEmpresaporId(int idEmp) {
        EmpresaModel empresa = null;
        String query = "SELECT * FROM empresa WHERE id_emp = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setInt(1, idEmp);
               try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    empresa = obtenerEmpresaDesdeResultSet(rs);
                }
            }
                } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar registro: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexion desconectar = new Conexion();
            try {
                desconectar.desconectar();
                System.out.println("Desconexión exitosa.");
            } catch (SQLException ex) {
                System.out.println("Error al desconectar" + ex.getMessage());
            }
        }
        return empresa;
    }

    @Override
    public List<EmpresaModel> obtenerTodasEmpresas() {
        List<EmpresaModel> empresas = new ArrayList<>();
        String query = "SELECT * FROM empresa";
                try (PreparedStatement stmt = conexion.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                EmpresaModel empresa = obtenerEmpresaDesdeResultSet(rs);
                empresas.add(empresa);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener registros: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexion desconectar = new Conexion();
            try {
                desconectar.desconectar();
                System.out.println("Desconexión exitosa.");
            } catch (SQLException ex) {
                System.out.println("Error al desconectar" + ex.getMessage());
            }
        }
        return empresas;
    }

    @Override
    public void agregarEmpresa(EmpresaModel empresa) {
        String query = "INSERT INTO empresa (tipodoc_emp, numdoc_emp, razon_emp, direccion_emp,"
                + "ubigeo_emp, distrito_emp, provincia_emp, departamento_emp)"
                + "VALUES (?,?,?,?,?,?,?,?)";
             try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, empresa.getTipodocEmp());
            stmt.setString(2, empresa.getNumdocEmp());
            stmt.setString(3, empresa.getRazonEmp());
            stmt.setString(4, empresa.getDireccionEmp());
            stmt.setString(5, empresa.getUbigeoEmp());
            stmt.setString(6, empresa.getDistritoEmp());
            stmt.setString(7, empresa.getProvinciaEmp());
            stmt.setString(8, empresa.getDepartamentoEmp());
                     

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Empresa agregada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al agregar registro: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexion desconectar = new Conexion();
            try {
                desconectar.desconectar();
                System.out.println("Desconexión exitosa.");
            } catch (SQLException ex) {
                System.out.println("Error al desconectar" + ex.getMessage());
            }
        }
        
    }

    @Override
    public void actualizarEmpresa(EmpresaModel empresa) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarEmpresa(int idEmp) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private EmpresaModel obtenerEmpresaDesdeResultSet(ResultSet rs) throws SQLException{
        int idEmp = rs.getInt("id_emp");
        String tipodocEmp = rs.getString("tipodoc_emp");
        String numdocEmp = rs.getString("numdoc_emp");
        String razonEmp = rs.getString("razon_emp");
        String direccionEmp = rs.getString("direccion_emp");
        String ubigeoEmp = rs.getString("ubigeo_emp");
        String distritoEmp = rs.getString("distrito_emp");
        String provinciaEmp = rs.getString("provincia_emp");
        String departamentoEmp = rs.getString("departamento_emp");
        
        
        return new EmpresaModel(idEmp, tipodocEmp, numdocEmp, razonEmp, 
                direccionEmp, ubigeoEmp, distritoEmp, provinciaEmp, departamentoEmp);
    }
    
        @Override
    public EmpresaModel obtenerEmpresaporRuc(String numdocEmp) {
        EmpresaModel empresa = null;
        String query = "SELECT * FROM empresa WHERE numdoc_emp = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, numdocEmp);
               try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    empresa = obtenerEmpresaDesdeResultSet(rs);
                }
            }
                } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener registro: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexion desconectar = new Conexion();
            try {
                desconectar.desconectar();
                System.out.println("Desconexión exitosa.");
            } catch (SQLException ex) {
                System.out.println("Error al desconectar" + ex.getMessage());
            }
        }
        return empresa;
    }
    
}
