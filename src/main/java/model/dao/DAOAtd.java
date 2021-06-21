package model.dao;

import Connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Atendentes;

public class DAOAtd {
    public boolean existe (Atendentes atendentes) throws Exception{
        String sql = "SELECT * FROM tb_atendente WHERE nome = ? AND senha = ?";
        
        
        try (Connection conn = ConnectionFactory.obterConexao();
                PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setString(1, atendentes.getNome());
            ps.setString(2, atendentes.getSenha());
            try (ResultSet rs = ps.executeQuery()){
                return rs.next();
            }
        }
    }
    public void inserirAtendentes (Atendentes atd) {
        String sql="INSERT INTO tb_atendente (nome, senha, idade, endereco, cpf) VALUES (?, ?, ?, ?, ?)";
        //abrir a conexao com o banco
        ConnectionFactory factory = new ConnectionFactory();
        
        try (Connection c = factory.obterConexao()){
            //pre-compilar o comando sql
            PreparedStatement ps = c.prepareStatement(sql);
            //4.Preencher os dados faltantes, substituindo os ? place holders
            ps.setString(1, atd.getNome());
            ps.setString(2, atd.getSenha());
            ps.setInt(3, atd.getIdade());
            ps.setString(4, atd.getEndereco());
            ps.setString(5, atd.getCpf());
            //Executar o comando
            ps.execute();
            JOptionPane.showMessageDialog(null, "Atendente cadastrado com sucesso!");
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Sistema indisponivel, tente novamente mais tarde.");
        }                                
    }
    
    public void atualizarAtendentes (Atendentes atd){
        String sql = "UPDATE tb_atendente SET nome = ?, senha= ?, idade = ?, endereco = ?, cpf = ? WHERE id = ?";
        
        ConnectionFactory factory = new ConnectionFactory();
        
        try(Connection conexao = factory.obterConexao();
                PreparedStatement ps = conexao.prepareStatement(sql)){
            ps.setString(1, atd.getNome());
            ps.setString(2, atd.getSenha());
            ps.setInt(3, atd.getIdade());
            ps.setString(4, atd.getEndereco());
            ps.setString(5, atd.getCpf());
            ps.setInt(6, atd.getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Atendente atualizado com sucesso!");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Sistema indisponivel, tente novamente mais tarde.");
        }
    }
    public void excluirAtendentes (Atendentes atd) {       
        String sql = "DELETE FROM tb_atendente WHERE id=?";       
        ConnectionFactory fabricaDeConexoes = new ConnectionFactory();
        try (Connection conexao = fabricaDeConexoes.obterConexao()) {            
            PreparedStatement ps = conexao.prepareStatement(sql);                     
            ps.setInt(1, atd.getId());
            ps.execute();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public List<Atendentes> listar() /*throws Exception*/{
        Connection conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<Atendentes> atendentes = new ArrayList<>();
        
        try {
            ps = conexao.prepareStatement("SELECT * FROM tb_atendente");
            rs = ps.executeQuery();
            
            while (rs.next()){
                
                Atendentes atd = new Atendentes();
                
                atd.setId(rs.getInt("id"));
                atd.setNome(rs.getString("nome"));
                atd.setSenha(rs.getString("senha"));
                atd.setIdade(rs.getInt("idade"));
                atd.setEndereco(rs.getString("endereco"));
                atd.setCpf(rs.getString("cpf"));
                atendentes.add(atd);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sistema Indisponivel, tente novamente mais tarde");//Logger.getLogger(DAOAdm.class.getName()).log(Level.SEVERE, null, ex);
        }                 
     return atendentes;   
    }       
    
}
