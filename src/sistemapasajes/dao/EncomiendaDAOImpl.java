/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import sistemapasajes.Conexion;
import sistemapasajes.modelo.EncomiendaModel;

/**
 *
 * @author Edson Cusicuna
 */
public class EncomiendaDAOImpl implements EncomiendaDAO {

    private Connection conexion;

    public EncomiendaDAOImpl() {
        Conexion conexionInstance = new Conexion();
        try {
            this.conexion = conexionInstance.conectar();
        } catch (SQLException ex) {
            System.out.println("Error al obtener la conexión: " + ex.getMessage());
        }
    }

    @Override
    public EncomiendaModel obtenerEncomiendaporid(int idEnco) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<EncomiendaModel> obtenerTodasEnciomiendas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void agregarEncomienda(EncomiendaModel encomienda) {
        String query = "INSERT INTO encomienda (fecha_enco, desc_enco, unidad_enco, cantidad_enco, "
                + "origen_enco, destino_enco, precio_enco, comprobante_enco, docremi_enco, remi_enco, docdest_enco, desti_enco)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, encomienda.getFechaEnco());
            stmt.setString(2, encomienda.getDescEnco());
            stmt.setString(3, encomienda.getUnidadEnco());
            stmt.setInt(4, encomienda.getCantidadEnco());
            stmt.setString(5, encomienda.getOrigenEnco());
            stmt.setString(6, encomienda.getDestinoEnco());
            stmt.setDouble(7, encomienda.getPrecioEnco());
            stmt.setInt(8, encomienda.getComprobanteEnco());
            stmt.setString(9, encomienda.getDocremiEnco());
            stmt.setString(10, encomienda.getRemiEnco());
            stmt.setString(11, encomienda.getDocdestEnco());
            stmt.setString(12, encomienda.getDestiEnco());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Encomienda agregado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al agregar Encomienda: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
    public void actualizarEncomienda(EncomiendaModel encomienda) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarEncomienda(int idEnco) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
