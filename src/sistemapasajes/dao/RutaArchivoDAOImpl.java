/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import sistemapasajes.Conexion;
import sistemapasajes.modelo.RutaArchivoModel;

/**
 *
 * @author edson
 */
public class RutaArchivoDAOImpl implements RutaArchivoDAO {
      private Connection conexion;
      
    public RutaArchivoDAOImpl (){
                Conexion conexionInstance = new Conexion();

        try {
            this.conexion = conexionInstance.conectar();
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
    }

    @Override
    public RutaArchivoModel obtenerRutaporId(int idRutaArchivo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<RutaArchivoModel> obtenerTodasRutasArchivo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void agregarRutaArchivo(RutaArchivoModel rutaarchivo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizarRuta(RutaArchivoModel rutaarchivo) {
       String query = "UPDATE ruta_archivo SET descripcion_rutaarch=?  WHERE empresa_rutaarch =?  AND titulo_rutaarch=? "; 
               try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, rutaarchivo.getDescRutaArchivo());
            stmt.setString(2, rutaarchivo.getEmpRutaArchivo());
            stmt.setString(3, rutaarchivo.getTituloRutaArchivo());
            stmt.executeUpdate();
                   System.out.println(rutaarchivo.getDescRutaArchivo());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error DAO - Actualizar Ruta de Archivos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
    public void eliminarRuta(int idRutaArchivo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public RutaArchivoModel obtenerRutaporEmpresa(String empRutaArchivo, String tituloRutaArchivo) {
        RutaArchivoModel rutaarchivo = null;
        String query = "SELECT * FROM ruta_archivo WHERE empresa_rutaarch = ? AND titulo_rutaarch=?";
                try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, empRutaArchivo);
            stmt.setString(2, tituloRutaArchivo);
               try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    rutaarchivo = ObtenerRutaArchivoDesdeResulSet(rs);
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
        return rutaarchivo;
    }
    private RutaArchivoModel ObtenerRutaArchivoDesdeResulSet (ResultSet rs) throws SQLException{
        int idRutaArchivo = rs.getInt("id_rutaarch");
        String descRutaArchivo = rs.getString("descripcion_rutaarch");
        String tituloRutaArchivo = rs.getString("titulo_rutaarch");
        String empRutaArchivo = rs.getString("empresa_rutaarch");
        return  new RutaArchivoModel(idRutaArchivo, descRutaArchivo, tituloRutaArchivo,empRutaArchivo);
    } 
    
}
