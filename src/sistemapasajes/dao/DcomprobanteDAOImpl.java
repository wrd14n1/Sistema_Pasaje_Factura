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
import sistemapasajes.modelo.DcomprobanteModel;

/**
 *
 * @author edson
 */
public class DcomprobanteDAOImpl implements DcomprobanteDAO {

    private Connection conexion;

    public DcomprobanteDAOImpl() {
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
    public DcomprobanteModel obtenerDComprobanteporID(int idComp) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public DcomprobanteModel obtenerDcomprobanteporNum(String numserieDComp) {
        DcomprobanteModel dcomprobante = null;
        String query= "SELECT * FROM dcomprobante WHERE numserie_dcomp =?";
                try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, numserieDComp);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    dcomprobante = obtenerDcomprobanteDesdeResultSet(rs);
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
        return dcomprobante;
    }

    @Override
    public List<DcomprobanteModel> obtenerTodosDcomprobantes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void agregarDcomprobante(DcomprobanteModel dcomprobante) {
        String query = "INSERT INTO dcomprobante(numserie_dcomp, item_dcomp, cantidad_dcomp,"
                + "tipunid_dcomp, producto_dcomp, descripcion_dcomp, valorunitario_dcomp, preciounitario_dcomp) "
                + "VALUES (?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, dcomprobante.getNumserieDcomp());
            stmt.setInt(2, dcomprobante.getItemDcomp());
            stmt.setInt(3, dcomprobante.getCantidadDcomp());
            stmt.setString(4, dcomprobante.getTipunidDcomp());
            stmt.setString(5, dcomprobante.getProductoDcomp());
            stmt.setString(6, dcomprobante.getDescripcionDcomp());
            stmt.setDouble(7, dcomprobante.getValorunitarioDcomp());
            stmt.setDouble(8, dcomprobante.getPreciounitarioDcomp());
     
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Detalle de Comprobante agregado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
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
    public void actualizarComprobante(DcomprobanteModel dcomprobante) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarDcomprobante(int idDcomp) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

        private DcomprobanteModel obtenerDcomprobanteDesdeResultSet(ResultSet rs) throws SQLException {
        int idDcomp = rs.getInt("id_dcomp");
        String numserieDcomp = rs.getString("numserie_dcomp");
        int itemDcomp = rs.getInt("item_dcomp");
        int cantidadDcomp = rs.getInt("cantidad_dcomp");
        String tipunidDcomp = rs.getString("tipunid_dcomp");
        String productoDcomp = rs.getString("producto_dcomp");
        String descripcionDcomp = rs.getString("descripcion_dcomp");
        Double valorunitarioDcomp = rs.getDouble("valorunitario_dcomp");
        Double preciounitarioDcomp = rs.getDouble("preciounitario_dcomp");
        

        return new DcomprobanteModel(idDcomp, numserieDcomp, itemDcomp, cantidadDcomp,tipunidDcomp, productoDcomp, descripcionDcomp,
                valorunitarioDcomp, preciounitarioDcomp);
    }
}
