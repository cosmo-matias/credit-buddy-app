package br.com.ducosmo.creditbuddy.service;

import br.com.ducosmo.creditbuddy.model.Pessoa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {

    public void salvar(Pessoa pessoa) {
        String sql = "INSERT INTO pessoas(nome, celular) VALUES(?, ?)";

        try (Connection conn = DatabaseService.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, pessoa.getNome());
            pstmt.setString(2, pessoa.getCelular());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void atualizar(Pessoa pessoa) {
        String sql = "UPDATE pessoas SET nome = ? , celular = ? WHERE id = ?";

        try (Connection conn = DatabaseService.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, pessoa.getNome());
            pstmt.setString(2, pessoa.getCelular());
            pstmt.setInt(3, pessoa.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM pessoas WHERE id = ?";

        try (Connection conn = DatabaseService.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Pessoa> listarTodos() {
        List<Pessoa> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM pessoas ORDER BY nome";

        try (Connection conn = DatabaseService.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Pessoa pessoa = new Pessoa(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("celular"));
                pessoas.add(pessoa);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return pessoas;
    }
}