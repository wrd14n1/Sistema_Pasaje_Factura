/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes.dao;

import sistemapasajes.modelo.SerieModel;
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
public class SerieDAOImpl implements SerieDAO {

    private Connection conexion;

    public SerieDAOImpl() {

        Conexion conexionInstance = new Conexion();
        try {
            this.conexion = conexionInstance.conectar();
        } catch (SQLException ex) {
            System.out.println("Error al obtener la conexión: " + ex.getMessage());
        }
    }

    @Override
    public SerieModel obtenerSerieporId(int idSerie) {
        SerieModel serie = null;
        String query = "SELECT * FROM serie WHERE  id_serie=?";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setInt(1, idSerie);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    serie = obtenerSerieDesdeResultSet(rs);
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
        return serie;
    }

    @Override
    public List<SerieModel> obtenerTodasSeries() {
        List<SerieModel> series = new ArrayList<>();
        String query = "SELECT * FROM serie";
        try (PreparedStatement stmt = conexion.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                SerieModel serie = obtenerSerieDesdeResultSet(rs);
                series.add(serie);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener registros: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexion desconectar = new Conexion();
            try {
                desconectar.desconectar();
                System.out.println("Desconexión exitosa.");
            } catch (SQLException ex) {
                System.out.println("Error al desconectar" + ex.getMessage());
            }
        }
        return series;
    }

    @Override
    public void agregarSerie(SerieModel serie) {
        String query = "INSERT INTO serie (tipo_serie, codsunat_serie, cod_serie, num_serie ) VALUES (?,?,?,?)";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, serie.getTipoSerie());
            stmt.setString(2, serie.getCodsunatSerie());
            stmt.setString(3, serie.getCodSerie());
            stmt.setInt(4, serie.getNumSerie());

        } catch (SQLException ex) {
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
    public void actualizarSerie(SerieModel serie) {
        String query = "UPDATE serie SET tipo_serie=?, codsunat=?, cod_serie=?, num_serie=? WHERE id_serie= ? ";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, serie.getTipoSerie());
            stmt.setString(2, serie.getCodsunatSerie());
            stmt.setString(3, serie.getCodSerie());
            stmt.setInt(4, serie.getNumSerie());
            stmt.setInt(5, serie.getIdSerie());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error al actualizar registro: " + ex.getMessage());
             JOptionPane.showMessageDialog(null, "Error al actualizar registro: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    @Override
    public void eliminarSerie(int idSerie) {
        String query = " DELETE FROM serie WHERE  id_serie=?";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setInt(1, idSerie);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al eliminar serie: " + ex.getMessage());
        }
    }

    private SerieModel obtenerSerieDesdeResultSet(ResultSet rs) throws SQLException {
        int idSerie = rs.getInt("id_serie");
        String tipoSerie = rs.getString("tipo_serie");
        String codsunatSerie = rs.getString("codsunat_serie");
        String codSerie = rs.getString("cod_serie");
        int numSerie = rs.getInt("num_serie");
        return new SerieModel(idSerie, tipoSerie, codsunatSerie, codSerie, numSerie);
    }

    @Override
    public SerieModel obtenerTipoSerie(String TipoSerie, String CodSerie) {
                SerieModel serie = null;
        String query = "SELECT * FROM serie WHERE  tipo_serie=? AND cod_serie=?";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, TipoSerie);
            stmt.setString(2, CodSerie);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    serie = obtenerSerieDesdeResultSet(rs);
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
        return serie;
    }

    @Override
    public SerieModel obtenerNumSerie(int idSerie) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizarNumSerie(SerieModel serie) {
                String query = "UPDATE serie SET num_serie=? WHERE cod_serie= ? ";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setInt(1, serie.getNumSerie());
            stmt.setString(2, serie.getCodSerie());
                      
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error al actualizar registro: " + ex.getMessage());
        }
    }

}
