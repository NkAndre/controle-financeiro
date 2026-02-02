package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Lancamento;
import model.TipoLancamento;
import model.Sessao; // Importante para pegar quem está logado

public class LancamentoDao {

    public void salvar(Lancamento lancamento) {
        // : Aqui é inserido também o usuario_logado (6 campos)
        String sql = "INSERT INTO lancamentos (descricao, valor, tipo, categoria, data_lancamento, usuario_logado) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, lancamento.getDescricao());
            stmt.setDouble(2, lancamento.getValor());
            stmt.setString(3, lancamento.getTipo().toString());
            stmt.setString(4, lancamento.getCategoria() != null ? lancamento.getCategoria().getNome() : null);
            stmt.setDate(5, Date.valueOf(lancamento.getData()));
            
            // 6º Campo: Pega o nome do usuário que está na Sessão atual
            stmt.setString(6, Sessao.usuarioLogado);
            
            stmt.execute();
            System.out.println("✅ Gravado no MySQL por: " + Sessao.usuarioLogado);
            
        } catch (Exception e) {
            System.err.println("❌ Erro ao salvar: Verifique se a coluna 'usuario_logado' existe na tabela lancamentos!");
            e.printStackTrace();
        }
    }

    public List<Lancamento> listarTodos() {
        List<Lancamento> lista = new ArrayList<>();
        // O SELECT * já vai trazer a coluna nova automaticamente certin
        String sql = "SELECT * FROM lancamentos ORDER BY id DESC";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Lancamento l = new Lancamento();
                l.setId(rs.getInt("id"));
                l.setDescricao(rs.getString("descricao"));
                l.setValor(rs.getDouble("valor"));
                l.setTipo(TipoLancamento.valueOf(rs.getString("tipo")));
                l.setData(rs.getDate("data_lancamento").toLocalDate());
                
                model.Categoria cat = new model.Categoria();
                cat.setNome(rs.getString("categoria"));
                l.setCategoria(cat);

                // Opcional: Se quiser mostrar o usuário na tabela, 
                // você pode ler rs.getString("usuario_logado") aqui.
                
                lista.add(l);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void excluir(int id) {
        String sql = "DELETE FROM lancamentos WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
            System.out.println("🗑️ Lançamento removido do banco!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}