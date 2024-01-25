/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes.dao;

/**
 *
 * @author edson
 */
import sistemapasajes.modelo.ConfiguracionModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import sistemapasajes.Conexion;

public class ConfiguracionDAOImpl implements ConfiguracionDAO {

    private Connection conexion;

    public ConfiguracionDAOImpl() {
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
    public ConfiguracionModel obtenerConfiguracionPorId(int idConf) {
        ConfiguracionModel configuracion = null;
        String query = "SELECT * FROM configuracion WHERE id_conf = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setInt(1, idConf);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    configuracion = obtenerConfiguracionDesdeResultSet(rs);
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

        return configuracion;
    }

    @Override
    public ConfiguracionModel obtenerConfiguracionPorRuc(String rucConf) {
        ConfiguracionModel configuracion = null;
        String query = "SELECT * FROM configuracion WHERE ruc_conf = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, rucConf);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    configuracion = obtenerConfiguracionDesdeResultSet(rs);
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

        return configuracion;
    }

    @Override
    public List<ConfiguracionModel> obtenerTodasConfiguraciones() {
        List<ConfiguracionModel> configuraciones = new ArrayList<>();
        String query = "SELECT * FROM configuracion";

        try (PreparedStatement stmt = conexion.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ConfiguracionModel configuracion = obtenerConfiguracionDesdeResultSet(rs);
                configuraciones.add(configuracion);
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

        return configuraciones;
    }

    @Override
    public void agregarConfiguracion(ConfiguracionModel configuracion) {
        String query = "INSERT INTO configuracion (ruc_conf, razon_conf, nomcom_conf, direc_conf, rsunat_conf, rarch_conf, logo_conf, text1_conf, text2_conf, text3_conf) VALUES (?,?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, configuracion.getRucConf());
            stmt.setString(2, configuracion.getRazonConf());
            stmt.setString(3, configuracion.getNomcomConf());
            stmt.setString(4, configuracion.getDirecConf());
            stmt.setString(5, configuracion.getRutaSunat());
            stmt.setString(6, configuracion.getRutaArchivo());
            stmt.setString(7, configuracion.getLogoConf());
            stmt.setString(8, configuracion.getTxt1Conf());
            stmt.setString(9, configuracion.getTxt2Conf());
            stmt.setString(10, configuracion.getTxt3Conf());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Configuración agregada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
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
    public void actualizarConfiguracion(ConfiguracionModel configuracion) {
        String query = "UPDATE configuracion SET ruc_conf = ?, razon_conf = ?, nomcom_conf = ?, direc_conf = ?, rsunat_conf=? , rarch_conf = ?, logo_conf =?, text1_conf=?, text2_conf=?, text3_conf=? WHERE id_conf = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, configuracion.getRucConf());
            stmt.setString(2, configuracion.getRazonConf());
            stmt.setString(3, configuracion.getNomcomConf());
            stmt.setString(4, configuracion.getDirecConf());
            stmt.setString(5, configuracion.getRutaSunat());
            stmt.setString(6, configuracion.getRutaArchivo());
            stmt.setString(7, configuracion.getLogoConf());
            stmt.setString(8, configuracion.getTxt1Conf());
            stmt.setString(9, configuracion.getTxt2Conf());
            stmt.setString(10, configuracion.getTxt3Conf());
            stmt.setInt(11, configuracion.getIdConf());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error DAO - Actualizar Configuración: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
    public void eliminarConfiguracion(int idConf) {
        String query = "DELETE FROM configuracion WHERE id_conf = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setInt(1, idConf);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al eliminar configuración: " + ex.getMessage());
        }
    }

    private ConfiguracionModel obtenerConfiguracionDesdeResultSet(ResultSet rs) throws SQLException {
        int idConf = rs.getInt("id_conf");
        String rucConf = rs.getString("ruc_conf");
        String razonConf = rs.getString("razon_conf");
        String nomcomConf = rs.getString("nomcom_conf");
        String direcConf = rs.getString("direc_conf");
        String rutaSunat = rs.getString("rsunat_conf");
        String rutaArchivo = rs.getString("rarch_conf");
        String logo = rs.getString("logo_conf");
        String text1 = rs.getString("text1_conf");
        String text2 = rs.getString("text2_conf");
        String text3 = rs.getString("text3_conf");
        

        return new ConfiguracionModel(idConf, rucConf, razonConf, nomcomConf, direcConf, rutaSunat, rutaArchivo, logo, text1, text2, text3);
    }
}
