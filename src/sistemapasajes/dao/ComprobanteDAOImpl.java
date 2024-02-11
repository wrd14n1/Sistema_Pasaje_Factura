/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes.dao;

import java.util.List;
import sistemapasajes.modelo.ComprobanteModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import sistemapasajes.Conexion;

/**
 *
 * @author edson
 */
public class ComprobanteDAOImpl implements ComprobanteDAO {

    private Connection conexion;

    public ComprobanteDAOImpl() {
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
    public ComprobanteModel obtenerComprobanteporID(int idComp) {
        ComprobanteModel comprobante = null;
        String query = "SELECT * FROM comprobante WHERE id_comp = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setInt(1, idComp);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    comprobante = obtenerComprobanteDesdeResultSet(rs);
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

        return comprobante;
    }

    @Override
    public ComprobanteModel obtenerComprobanteporNum(String numdocComp) {
        ComprobanteModel comprobante = null;
        String query = "SELECT * FROM comprobante WHERE serie_comp = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, numdocComp);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    comprobante = obtenerComprobanteDesdeResultSet(rs);
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

        return comprobante;
    }

    @Override
    public List<ComprobanteModel> obtenerTodosComprobantes() {
        List<ComprobanteModel> comprobantes = new ArrayList<>();
        String query = "SELECT * FROM comprobante";
        try (PreparedStatement stmt = conexion.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ComprobanteModel comprobante = obtenerComprobanteDesdeResultSet(rs);
                comprobantes.add(comprobante);
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

        return comprobantes;

    }

    @Override
    public void agregarComprobante(ComprobanteModel comprobante) {
        String query = "INSERT INTO comprobante (tipo_comp, serie_comp, doccliente_comp,cliente_comp, fecha_comp,"
                + "moneda_comp, mediopago_comp, totalventgrav_comp, igv_comp, imptotal_comp,"
                + "fechaxml_comp, fechaenvio_comp, estado_comp, hora_comp, afec_comp, vehiculo_comp) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, comprobante.getTipoComp());
            stmt.setString(2, comprobante.getSerieComp());
            stmt.setString(3, comprobante.getDocclienteComp());
            stmt.setString(4, comprobante.getClienteComp());
            stmt.setString(5, comprobante.getFechaComp());
            stmt.setString(6, comprobante.getMonedaComp());
            stmt.setString(7, comprobante.getMediopagoComp());
            stmt.setDouble(8, comprobante.getTotalventgravComp());
            stmt.setDouble(9, comprobante.getIgvComp());
            stmt.setDouble(10, comprobante.getImptotalComp());
            stmt.setString(11, comprobante.getFechaxmlComp());
            stmt.setString(12, comprobante.getFechaenvioComp());
            stmt.setString(13, comprobante.getEstadoComp());
            stmt.setString(14, comprobante.getHoraComp());
            stmt.setString(15, comprobante.getAfecComp());
            stmt.setInt(16, comprobante.getVehiculoComp());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Comprobante agregado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
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
    public void actualizarComprobante(ComprobanteModel comprobante) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarComprobante(int idComp) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private ComprobanteModel obtenerComprobanteDesdeResultSet(ResultSet rs) throws SQLException {
        int idComp = rs.getInt("id_comp");
        String tipoComp = rs.getString("tipo_comp");
        String serieComp = rs.getString("serie_comp");
        String docclienteComp = rs.getString("doccliente_comp");
        String clienteComp = rs.getString("cliente_comp");
        String fechaComp = rs.getString("fecha_comp");
        String horaComp = rs.getString("hora_comp");
        String monedaComp = rs.getString("moneda_comp");
        String mediopagoComp = rs.getString("mediopago_comp");
        Double totalventgravComp = rs.getDouble("totalventgrav_comp");
        Double igvComp = rs.getDouble("igv_comp");
        Double imptotalComp = rs.getDouble("imptotal_comp");
        String hashComp = rs.getString("hash_comp");
        String fechaxmlComp = rs.getString("fechaxml_comp");
        String fechaenvioComp = rs.getString("fechaenvio_comp");
        String estadoComp = rs.getString("estado_comp");
        String afecComp = rs.getString("afec_comp");
        int vehiComp = rs.getInt("vehiculo_comp");
        return new ComprobanteModel(idComp, tipoComp, serieComp, docclienteComp, clienteComp, fechaComp, horaComp, monedaComp,
                mediopagoComp, totalventgravComp, igvComp, imptotalComp, hashComp, fechaxmlComp, fechaenvioComp, estadoComp, afecComp, vehiComp);
    }

    @Override
    public void actualizarComprobanteHash(ComprobanteModel comprobante) {
        String query = "UPDATE comprobante SET hash_comp = ? , estado_comp=? WHERE serie_comp = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, comprobante.getHashComp());
            stmt.setString(2, "XML GENERADO");
            stmt.setString(3, comprobante.getSerieComp());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error DAO - Actualizar Hash Comprobante: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
    public List<ComprobanteModel> obtenerComprobantesporEstado(String estadoComp) {
        List<ComprobanteModel> comprobantes = new ArrayList<>();
        String query = "SELECT * FROM comprobante WHERE estado_comp=?";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, estadoComp); // Establecer el valor del parámetro
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ComprobanteModel comprobante = obtenerComprobanteDesdeResultSet(rs);
                    
                    comprobantes.add(comprobante);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos de los Comprobantes: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexion desconectar = new Conexion();
            try {
                desconectar.desconectar();
                System.out.println("Desconexión exitosa.");
            } catch (SQLException ex) {
                System.out.println("Error al desconectar" + ex.getMessage());
            }
        }

        return comprobantes;
    }

    @Override
    public void actualizarPorEstadoComprobante(ComprobanteModel comprobante) {
        String query = "UPDATE comprobante SET estado_comp = ? WHERE serie_comp = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, comprobante.getEstadoComp());
            stmt.setString(2, comprobante.getSerieComp());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error DAO - Actualizar Hash Comprobante: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

}
