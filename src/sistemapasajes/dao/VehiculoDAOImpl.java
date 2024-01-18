/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes.dao;

/**
 *
 * @author edson
 */
import sistemapasajes.modelo.VehiculoModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sistemapasajes.Conexion;

public class VehiculoDAOImpl implements VehiculoDAO {

    private Connection conexion;

    // Constructor que recibe la conexión o puedes tener un método para establecerla
    public VehiculoDAOImpl() {
        // Obtener la conexión a la base de datos
               // Crea una instancia de Conexion y úsala para obtener una conexión
        Conexion conexionInstance = new Conexion();
        
        try {
            this.conexion = conexionInstance.conectar();
        } catch (SQLException ex) {
            System.out.println("Error al obtener la conexión: " + ex.getMessage());
        }
    }
    @Override
    public VehiculoModel obtenerVehiculoPorId(int idVehiculo) {
        VehiculoModel vehiculo = null;
        String query = "SELECT * FROM vehiculos WHERE id_vehi = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setInt(1, idVehiculo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    vehiculo = obtenerVehiculoDesdeResultSet(rs);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener vehículo por ID: " + ex.getMessage());
        }

        return vehiculo;
    }

    @Override
    public List<VehiculoModel> obtenerTodosVehiculos() {
        List<VehiculoModel> vehiculos = new ArrayList<>();
        String query = "SELECT * FROM vehiculo";

        try (PreparedStatement stmt = conexion.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                VehiculoModel vehiculo = obtenerVehiculoDesdeResultSet(rs);
                vehiculos.add(vehiculo);
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener todos los vehículos: " + ex.getMessage());
        }

        return vehiculos;
    }

    @Override
    public void agregarVehiculo(VehiculoModel vehiculo) {
        String query = "INSERT INTO vehiculo (desc_vehi, placa_vehi, numasie_vehi, conductor_vehi) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, vehiculo.getDescVehi());
            stmt.setString(2, vehiculo.getPlacaVehi());
            stmt.setInt(3, vehiculo.getNumVehi());
            stmt.setString(4, vehiculo.getCondVehi());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al agregar vehículo: " + ex.getMessage());
        }
    }

    @Override
    public void actualizarVehiculo(VehiculoModel vehiculo) {
        String query = "UPDATE vehiculo SET desc_vehi = ?, placa_vehi = ?, numasie_vehi = ?, cond_vehi = ? WHERE id_vehi = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, vehiculo.getDescVehi());
            stmt.setString(2, vehiculo.getPlacaVehi());
            stmt.setInt(3, vehiculo.getNumVehi());
            stmt.setString(4, vehiculo.getCondVehi());
            stmt.setInt(5, vehiculo.getIdVehi());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al actualizar vehículo: " + ex.getMessage());
        }
    }

    @Override
    public void eliminarVehiculo(int idVehiculo) {
        String query = "DELETE FROM vehiculo WHERE id_vehi = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setInt(1, idVehiculo);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al eliminar vehículo: " + ex.getMessage());
        }
    }

    private VehiculoModel obtenerVehiculoDesdeResultSet(ResultSet rs) throws SQLException {
        int idVehi = rs.getInt("id_vehi");
        String descVehi = rs.getString("desc_vehi");
        String placaVehi = rs.getString("placa_vehi");
        int numVehi = rs.getInt("numasie_vehi");
        String condVehi = rs.getString("conductor_vehi");

        return new VehiculoModel(idVehi, descVehi, placaVehi, numVehi, condVehi);
    }
}