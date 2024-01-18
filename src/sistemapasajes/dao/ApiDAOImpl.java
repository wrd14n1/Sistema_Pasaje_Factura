/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import sistemapasajes.modelo.ApiModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sistemapasajes.Conexion;
/**
 *
 * @author edson
 */
public class ApiDAOImpl implements ApiDAO{
    
    private  Connection conexion;
    
    public ApiDAOImpl(){
         
        Conexion conexionInstance = new Conexion();
        try {
              this.conexion = conexionInstance.conectar();
        } catch (SQLException ex) {
            System.out.println("Error al obtener la conexión: " + ex.getMessage());
        }
    }
    
    @Override
    public ApiModel obtenerApiPorId(int idApi) {
        ApiModel api =null;
        String query="SELECT * FROM api WHERE id_api = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(query)){
            stmt.setInt(1, idApi);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    api = obtenerApiDesdeResultSet(rs);
                }
 
            } 
        } catch (SQLException ex) {
            System.out.println("Error al obtener configuración por ID: " + ex.getMessage());
        } finally{
            Conexion desconectar = new Conexion();
            try {
                desconectar.desconectar();
                System.out.println("Desconexión exitosa.");
            } catch (SQLException ex) {
               System.out.println("Error al desconectar" + ex.getMessage());
            }
        }
        return api;
    }

    @Override
    public List<ApiModel> obtenerTodasApis() {
        List<ApiModel> apis = new ArrayList<>();
        String query ="SELECT * FROM api";
        try (PreparedStatement stmt = conexion.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
             while (rs.next()) {                
                ApiModel api = obtenerApiDesdeResultSet(rs);
                apis.add(api);
            }
             
        } catch (SQLException ex) {
            System.out.println("Error al obtener todas las configuraciones: " + ex.getMessage());
        }finally{
            Conexion desconectar = new Conexion();
            try {
                desconectar.desconectar();
                System.out.println("Desconexión exitosa.");
            } catch (SQLException ex) {
               System.out.println("Error al desconectar" + ex.getMessage());
            }
        }
        return apis;
    }

    @Override
    public void agregarApi(ApiModel api) {
        String query = "INSERT INTO api(url_api, desc_api) VALUES (?,?)";
        
        try (PreparedStatement stmt = conexion.prepareStatement(query)){
            stmt.setString(1, api.getUrlApi());
            stmt.setString(2, api.getDescApi());
            stmt.executeUpdate();
             JOptionPane.showMessageDialog(null, "Registro agregado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            //System.out.println("Error al agregar configuración: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al agregar registro: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }finally{
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
    public void actualizarApi(ApiModel api) {
         String query = "UPDATE api SET url_api = ?, desc_api = ? WHERE id_api=? ";
         try (PreparedStatement stmt = conexion.prepareStatement(query)){
            stmt.setString(1, api.getUrlApi());
            stmt.setString(2, api.getDescApi());
            stmt.setInt(3, api.getIdApi());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error al actualizar registro: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }finally{
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
    public void eliminarApi(int idApi) {
        String query = "DELETE FROM api WHERE id_api =?";
        try (PreparedStatement stmt = conexion.prepareStatement(query)){
            stmt.setInt(1, idApi);
            stmt.executeUpdate();
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error al actualizar registro: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
      }finally{
            Conexion desconectar = new Conexion();
            try {
                desconectar.desconectar();
                System.out.println("Desconexión exitosa.");
            } catch (SQLException ex) {
               System.out.println("Error al desconectar" + ex.getMessage());
            }
        }
    }
    
    private ApiModel obtenerApiDesdeResultSet( ResultSet rs) throws SQLException{
        int idApi = rs.getInt("id_api");
        String urlApi = rs.getString("url_api");
        String descApi = rs.getString("desc_api");
        return new ApiModel(idApi,urlApi,descApi);
    }
    
       
}
