/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public boolean cadastrarProduto (ProdutosDTO produto){
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

        conn = new conectaDAO().connectDB();
        if (conn == null) {
            return false;
        }

        try (Connection conexao = conn;
             PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setString(1, produto.getNome());
            comando.setInt(2, produto.getValor());
            comando.setString(3, produto.getStatus());
            return comando.executeUpdate() == 1;
        } catch (SQLException erro) {
            System.err.println("Erro ao cadastrar produto: " + erro.getMessage());
            return false;
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        ArrayList<ProdutosDTO> produtos = new ArrayList<>();
        String sql = "SELECT id, nome, valor, status FROM produtos ORDER BY id";

        conn = new conectaDAO().connectDB();
        if (conn == null) {
            return produtos;
        }

        try (Connection conexao = conn;
             PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet resultado = comando.executeQuery()) {
            while (resultado.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultado.getInt("id"));
                produto.setNome(resultado.getString("nome"));
                produto.setValor(resultado.getInt("valor"));
                produto.setStatus(resultado.getString("status"));
                produtos.add(produto);
            }
        } catch (SQLException erro) {
            System.err.println("Erro ao listar produtos: " + erro.getMessage());
        }

        return produtos;
    }

    public boolean venderProduto(int id) {
        String sql = "UPDATE produtos SET status = ? WHERE id = ?";

        conn = new conectaDAO().connectDB();
        if (conn == null) {
            return false;
        }

        try (Connection conexao = conn;
             PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setString(1, "Vendido");
            comando.setInt(2, id);
            return comando.executeUpdate() == 1;
        } catch (SQLException erro) {
            System.err.println("Erro ao vender produto: " + erro.getMessage());
            return false;
        }
    }

    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
        ArrayList<ProdutosDTO> produtos = new ArrayList<>();
        String sql = "SELECT id, nome, valor, status FROM produtos "
                + "WHERE status = ? ORDER BY id";

        conn = new conectaDAO().connectDB();
        if (conn == null) {
            return produtos;
        }

        try (Connection conexao = conn;
             PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setString(1, "Vendido");
            try (ResultSet resultado = comando.executeQuery()) {
                while (resultado.next()) {
                    ProdutosDTO produto = new ProdutosDTO();
                    produto.setId(resultado.getInt("id"));
                    produto.setNome(resultado.getString("nome"));
                    produto.setValor(resultado.getInt("valor"));
                    produto.setStatus(resultado.getString("status"));
                    produtos.add(produto);
                }
            }
        } catch (SQLException erro) {
            System.err.println("Erro ao listar produtos vendidos: " + erro.getMessage());
        }

        return produtos;
    }
    
    
    
        
}

