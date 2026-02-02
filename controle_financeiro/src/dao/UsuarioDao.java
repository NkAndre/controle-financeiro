package dao;

import java.sql.*;


public class UsuarioDao {
    public boolean validarLogin(String user, String pass) {
        // Agora comparamos o usuário com o hash da senha
        String sql = "SELECT id FROM usuarios WHERE usuario = ? AND senha = ? LIMIT 1";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, user);
            stmt.setString(2, pass); // 'pass' já virá hasheado do Service
            
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void cadastrar(String user, String pass) {
        String sql = "INSERT INTO usuarios (usuario, senha) VALUES (?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user);
            stmt.setString(2, pass);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}