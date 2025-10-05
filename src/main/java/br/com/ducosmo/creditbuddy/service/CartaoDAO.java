package br.com.ducosmo.creditbuddy.service;

import br.com.ducosmo.creditbuddy.model.Cartao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartaoDAO {

    public void salvar(Cartao cartao) {
        String sql = "INSERT INTO cartoes(nome_cartao, bandeira, dia_vencimento, dia_fechamento) VALUES(?, ?, ?, ?)";
        try (Connection conn = DatabaseService.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cartao.getNomeCartao());
            pstmt.setString(2, cartao.getBandeira());
            pstmt.setInt(3, cartao.getDiaVencimento());
            pstmt.setInt(4, cartao.getDiaFechamento());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void atualizar(Cartao cartao) {
        String sql = "UPDATE cartoes SET nome_cartao = ?, bandeira = ?, dia_vencimento = ?, dia_fechamento = ? WHERE id = ?";
        try (Connection conn = DatabaseService.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cartao.getNomeCartao());
            pstmt.setString(2, cartao.getBandeira());
            pstmt.setInt(3, cartao.getDiaVencimento());
            pstmt.setInt(4, cartao.getDiaFechamento());
            pstmt.setInt(5, cartao.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM cartoes WHERE id = ?";
        try (Connection conn = DatabaseService.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Cartao> listarTodos() {
        List<Cartao> cartoes = new ArrayList<>();
        String sql = "SELECT * FROM cartoes ORDER BY nome_cartao";
        try (Connection conn = DatabaseService.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cartao cartao = new Cartao();
                cartao.setId(rs.getInt("id"));
                cartao.setNomeCartao(rs.getString("nome_cartao"));
                cartao.setBandeira(rs.getString("bandeira"));
                cartao.setDiaVencimento(rs.getInt("dia_vencimento"));
                cartao.setDiaFechamento(rs.getInt("dia_fechamento"));
                cartoes.add(cartao);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cartoes;
    }
}