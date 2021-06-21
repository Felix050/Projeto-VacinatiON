package model.dao;

import Connection.ConnectionFactory;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.bean.Administradores;

public class DAOAdm { 
    public boolean existe (Administradores admin) throws Exception{
        String sql = "SELECT * FROM tb_administrador WHERE nome = ? AND senha = ?";
        
        
        try (Connection conn = ConnectionFactory.obterConexao();
                PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setString(1, admin.getNome());
            ps.setString(2, admin.getSenha());
            try (ResultSet rs = ps.executeQuery()){
                return rs.next();
            }
        }
    }
    public void inserirAdministradores (Administradores adms) {
        String sql="INSERT INTO tb_administrador (nome, senha, idade, endereco, cpf) VALUES (?, ?, ?, ?, ?)";
        //abrir a conexao com o banco
        ConnectionFactory factory = new ConnectionFactory();
        
        try (Connection c = factory.obterConexao()){
            //pre-compilar o comando sql
            PreparedStatement ps = c.prepareStatement(sql);
            //4.Preencher os dados faltantes, substituindo os ? place holders
            ps.setString(1, adms.getNome());
            ps.setString(2, adms.getSenha());
            ps.setInt(3, adms.getIdade());
            ps.setString(4, adms.getEndereco());
            ps.setString(5, adms.getCpf());
            //Executar o comando
            ps.execute();
            JOptionPane.showMessageDialog(null, "Administrador cadastrado com sucesso!");
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Sistema indisponivel, tente novamente mais tarde.");
            e.printStackTrace();
        }                                
    }
    
    public void atualizarAdministradores (Administradores adms){
        String sql = "UPDATE tb_administrador SET nome = ?, senha= ?, idade = ?, endereco = ?, cpf = ? WHERE id = ?";
        
        ConnectionFactory factory = new ConnectionFactory();
        
        try(Connection conexao = factory.obterConexao();
                PreparedStatement ps = conexao.prepareStatement(sql)){
            ps.setString(1, adms.getNome());
            ps.setString(2, adms.getSenha());
            ps.setInt(3, adms.getIdade());
            ps.setString(4, adms.getEndereco());
            ps.setString(5, adms.getCpf());
            ps.setInt(6, adms.getId());
            ps.execute();
            
            JOptionPane.showMessageDialog(null, "Administrador atualizado com sucesso!");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Sistema indisponivel, tente novamente mais tarde.");
            e.printStackTrace();
        }
    }
    public void excluirAdm(Administradores administradores) {       
        String sql = "DELETE FROM tb_administrador WHERE id=?";       
        ConnectionFactory fabricaDeConexoes = new ConnectionFactory();
        try (Connection conexao = fabricaDeConexoes.obterConexao()) {            
            PreparedStatement ps = conexao.prepareStatement(sql);                     
            ps.setInt(1, administradores.getId());
            ps.execute();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public List<Administradores> listar() /*throws Exception*/{
        Connection conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<Administradores> admins = new ArrayList<>();
        
        try {
            ps = conexao.prepareStatement("SELECT * FROM tb_administrador");
            rs = ps.executeQuery();
            
            while (rs.next()){
                
                Administradores admin = new Administradores();
                
                admin.setId(rs.getInt("id"));
                admin.setNome(rs.getString("nome"));
                admin.setSenha(rs.getString("senha"));
                admin.setIdade(rs.getInt("idade"));
                admin.setEndereco(rs.getString("endereco"));
                admin.setCpf(rs.getString("cpf"));
                admins.add(admin);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sistema Indisponivel, tente novamente mais tarde");//Logger.getLogger(DAOAdm.class.getName()).log(Level.SEVERE, null, ex);
        }                 
     return admins;   
    }       
}


