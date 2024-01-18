/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes.dao;

import sistemapasajes.modelo.PasajeroModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import sistemapasajes.Conexion;

/**
 *
 * @author edson
 */
public class PasajeroDAOimpl implements PasajeroDAO {

    private Connection conexion;

    public PasajeroDAOimpl() {
        Conexion conexionInstance = new Conexion();
        try {
            this.conexion = conexionInstance.conectar();
        } catch (SQLException ex) {
            System.out.println("Error al obtener la conexión: " + ex.getMessage());
        }

    }

    @Override
    public PasajeroModel obtenerPasajeroPorId(int idPsje) {
        PasajeroModel pasajero = null;
        String query = "SELECT * FROM pasajero WHERE id_psje = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setInt(1, idPsje);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    pasajero = obtenerPasajeroDesdeResultSet(rs);
                }
            } catch (Exception e) {
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

        return pasajero;
    }

    @Override
    public List<PasajeroModel> obtenerTodosPasajeros() {
        List<PasajeroModel> pasajeros = new ArrayList<>();
        String query = "SELECT * FROM pasajero";
        try (PreparedStatement stmt = conexion.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {                
                PasajeroModel psje = obtenerPasajeroDesdeResultSet(rs);
                pasajeros.add(psje);
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

        return pasajeros;
    }

    @Override
    public void agregarPasajero(PasajeroModel pasajero) {
        String query = "INSERT INTO pasajero(numdoc_psje,ape_psje,nom_psje) VALUES (?,?,?)";
        try (PreparedStatement stmt = conexion.prepareStatement(query)){
            stmt.setString(1, pasajero.getNumdocPsje());
            stmt.setString(2, pasajero.getApePsje());
            stmt.setString(3, pasajero.getNomPsje());
             stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Pasajero agregado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al agregar pasajero: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
    public void actualizarPasajero(PasajeroModel pasajero) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarPasajero(int idPsje) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private PasajeroModel obtenerPasajeroDesdeResultSet(ResultSet rs) throws SQLException {
        int idPsje = rs.getInt("id_psje");
        String numdocPsje = rs.getString("numdoc_psje");
        String ape_psje = rs.getString("ape_psje");
        String nom_psje = rs.getString("nom_psje");
        return new PasajeroModel(idPsje, numdocPsje, ape_psje, nom_psje);
    }

    public PasajeroModel obtenerPasajeroPorDNI(String numdocPsje) {
                PasajeroModel pasajero = null;
        String query = "SELECT * FROM pasajero WHERE numdoc_psje = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, numdocPsje);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    pasajero = obtenerPasajeroDesdeResultSet(rs);
                }
            } catch (Exception e) {
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

        return pasajero;
    }
}
