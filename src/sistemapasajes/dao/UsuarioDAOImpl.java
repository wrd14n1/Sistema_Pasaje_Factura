/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import sistemapasajes.Conexion;
import sistemapasajes.modelo.UsuarioModel;

/**
 *
 * @author edson
 */
public class UsuarioDAOImpl implements UsuarioDAO{

        private Connection conexion;

    public UsuarioDAOImpl() {
        // Obtener la conexión a la base de datos
        // Crea una instancia de Conexion y úsala para obtener una conexión
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
    public void agregarUsuario(UsuarioModel usuario) {
        String query = "INSERT INTO usuario (datos_usua, usuario_usua, clave_usua) VALUES (?,?,?)";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, usuario.getDatosUsua());
            stmt.setString(2, usuario.getUsuarioUsua());
            stmt.setString(3, usuario.getClaveUsua());
         
     
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario agregado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
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
    public UsuarioModel loginUsuario(String usuarioUsua, String claveUsua) {
       UsuarioModel usuario = null;
       String query = "SELECT * FROM usuario WHERE usuario_usua= ? AND clave_usua =?";
         try (PreparedStatement stmt = conexion.prepareStatement(query)) {
             stmt.setString(1, usuarioUsua);
             stmt.setString(2, claveUsua);
             try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = obtenerUsuarioDesdeResultSet(rs);
                          
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
       return usuario;
    }
    
    private UsuarioModel obtenerUsuarioDesdeResultSet ( ResultSet rs) throws SQLException{
        int idUsua = rs.getInt("id_usua");
        String datosUsua = rs.getString("datos_usua");
        String usuarioUsua = rs.getString("usuario_usua");
        String claveUsua = rs.getString("clave_usua");
        
        return new UsuarioModel (idUsua,datosUsua,usuarioUsua, claveUsua);
    }
    
}
