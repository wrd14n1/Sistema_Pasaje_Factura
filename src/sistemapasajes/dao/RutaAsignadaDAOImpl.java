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
import sistemapasajes.modelo.AsignacionRutaModel;

/**
 *
 * @author edson
 */
public class RutaAsignadaDAOImpl implements RutaAsignadaDAO {

    private Connection conexion;

    public RutaAsignadaDAOImpl() {
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
    public AsignacionRutaModel obtenerAsignacionRutaporId(int idAsignacionRuta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AsignacionRutaModel obtenerRutaporVehiculo(int vehiculoAsignacionRuta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<AsignacionRutaModel> obtenerTodasAsignacionRutas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void agregarRutaAsignada(AsignacionRutaModel asignacionruta) {
        String query = "INSERT INTO asignacionruta (fecha_asig,hora_asig,vehiculo_asig, ruta_asig, origen_asig,destino_asig) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, asignacionruta.getFechaAsigRuta());
            stmt.setString(2, asignacionruta.getHoraAsigRuta());
            stmt.setInt(3, asignacionruta.getVehiculoAsigRuta());
            stmt.setInt(4, asignacionruta.getRutaAsigRuta());
            stmt.setString(5, asignacionruta.getOrigenAsigRuta());
            stmt.setString(6, asignacionruta.getDestinoAsigRuta());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Ruta Asignada agregada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al registrar ruta asignada: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
    public void actualizarRuta(AsignacionRutaModel asignacionruta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarAsignacionRuta(int idAsignacionRuta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<AsignacionRutaModel> obtenerAsignacionRutasporFecha(String fechaAsignacionRuta, int rutaAsignacionRuta) {
        List<AsignacionRutaModel> rutasasignadas = new ArrayList<>();
        String query = "SELECT * FROM asignacionruta"
                + " INNER JOIN vehiculo ON id_vehi=vehiculo_asig"
                + " WHERE fecha_asig = ? AND ruta_asig=?";

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, fechaAsignacionRuta);
            stmt.setInt(2, rutaAsignacionRuta);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    AsignacionRutaModel rutaasignada = obtenerRutaAsignadaDesdeResultSet(rs);
                    rutasasignadas.add(rutaasignada);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener lista de vehiculos asignados: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("error:" + ex.getMessage());
        } finally {
            Conexion desconectar = new Conexion();
            try {
                desconectar.desconectar();
                System.out.println("Desconexión exitosa.");
            } catch (SQLException ex) {
                System.out.println("Error al desconectar" + ex.getMessage());
            }
        }

        return rutasasignadas;
    }

    private AsignacionRutaModel obtenerRutaAsignadaDesdeResultSet(ResultSet rs) throws SQLException {
        int idRutaAsignada = rs.getInt("id_asig");
        String fechaAsigRuta = rs.getString("fecha_asig");
        String horaAsigRuta = rs.getString("hora_asig");
        int vehiculoAsigRuta = rs.getInt("vehiculo_asig");
        int rutaAsigRuta = rs.getInt("ruta_asig");
        String origenAsigRuta = rs.getString("origen_asig");
        String destinoAsigRuta = ("destino_asig");
        String descVehiculoAsigRuta = rs.getString("desc_vehi");
        String placavehiAsigRuta = rs.getString("placa_vehi");

        return new AsignacionRutaModel(idRutaAsignada, fechaAsigRuta, horaAsigRuta, vehiculoAsigRuta, rutaAsigRuta, origenAsigRuta, destinoAsigRuta, descVehiculoAsigRuta, placavehiAsigRuta);
    }

}
