/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes.dao;

import java.util.List;
import sistemapasajes.modelo.RutaModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import sistemapasajes.Conexion;

/**
 *
 * @author edson
 */
public class RutaDAOImpl implements RutaDAO {
    
    private Connection conexion;
     
    public RutaDAOImpl(){
         Conexion conexionInstance = new Conexion();
         try {
            this.conexion = conexionInstance.conectar();
        } catch (SQLException ex) {
             System.out.println("Error al obtener la conexión: " + ex.getMessage());
        }
    }

    @Override
    public RutaModel obtenerRutaporId(int idRuta) {
        RutaModel ruta = null;
        String query= "SELECT * FROM ruta WHERE id_ruta = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(query))  {
            stmt.setInt(1, idRuta);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ruta = obtenerRutaDesdeResultSet(rs);
                }
            } 
        } catch (SQLException ex) {
              System.out.println("Error al obtener vehículo por ID: " + ex.getMessage());
        }
        return ruta;
    }

    @Override
    public List<RutaModel> obtenerTodasRutas() {
       List<RutaModel> rutas = new ArrayList<>();
       String query = "SELECT * FROM ruta" ;
        try (PreparedStatement stmt = conexion.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
             while (rs.next()) {                
                RutaModel ruta = obtenerRutaDesdeResultSet(rs);
                rutas.add(ruta);
            }
        } catch (Exception e) {
        }
       return rutas;
    }

    @Override
    public void agregarRuta(RutaModel ruta) {
        String query ="INSERT INTO ruta (tramo_ruta, precio_ruta, punto1_ruta, punto2_ruta) VALUES (?,?,?,?)";
        try(PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, ruta.getTramoRuta());
            stmt.setDouble(2, ruta.getPrecioRuta());
            stmt.setString(3, ruta.getPunto1Ruta());
            stmt.setString(4, ruta.getPunto2Ruta());
            stmt.executeUpdate();
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error al registrar la ruta: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    @Override
    public void actualizarRuta(RutaModel ruta) {
        String query ="UPDATE ruta SET tramo_ruta = ?, precio_ruta =?, punto1_ruta=?, punto2_ruta=? WHERE id_ruta= ?";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, ruta.getTramoRuta());
            stmt.setDouble(2, ruta.getPrecioRuta());
            stmt.setString(3, ruta.getPunto1Ruta());
            stmt.setString(4, ruta.getPunto2Ruta());
            stmt.setInt(5, ruta.getIdRuta());
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar ruta: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    @Override
    public void eliminarRuta(int idRuta) {
        String query = "DELETE FROM ruta WHERE id_ruta=?";
        try (PreparedStatement stmt = conexion.prepareStatement(query)){
            stmt.setInt(1, idRuta);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al eliminar vehículo: " + ex.getMessage());
        }
    }
    
    private RutaModel obtenerRutaDesdeResultSet(ResultSet rs) throws SQLException{
        int idRuta = rs.getInt("id_ruta");
        String tramoRuta = rs.getString("tramo_ruta");
        double precioRuta = rs.getDouble("precio_ruta");
        String punto1Ruta = rs.getString("punto1_ruta");
        String punto2Ruta = rs.getString("punto2_ruta");
        return new RutaModel(idRuta, tramoRuta, precioRuta, punto1Ruta, punto2Ruta);
    }
}
