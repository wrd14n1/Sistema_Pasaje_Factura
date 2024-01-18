/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes.dao;

import sistemapasajes.modelo.UsuarioModel;

/**
 *
 * @author edson
 */
public interface UsuarioDAO {
    void agregarUsuario (UsuarioModel usuario);
    UsuarioModel loginUsuario (String usuarioUsua, String claveUsua);
}
