package model.dao;

import Connection.ConnectionFactory;
import com.mysql.cj.util.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Pacientes;
import model.bean.Utils;

public class DAOPct {
    public void inserirPacientes (Pacientes pct) {
        String sql="INSERT INTO tb_paciente (nome, idade, profissao, endereco, dataa, prioridade) VALUES (?, ?, ?, ?, ?, ?)";
        //abrir a conexao com o banco
        ConnectionFactory factory = new ConnectionFactory();
        
        try (Connection c = factory.obterConexao()){
            //pre-compilar o comando sql
            PreparedStatement ps = c.prepareStatement(sql);
            //4.Preencher os dados faltantes, substituindo os ? place holders
            ps.setString(1, pct.getNome());
            ps.setInt(2, pct.getIdade());
            ps.setBoolean(3, pct.isProfissao());
            ps.setString (4, pct.getEndereco());
            ps.setDate(5, new java.sql.Date(pct.getDataa().getTime()));
            ps.setInt(6, pct.getPrioridade());
//                if(pct.idade >= 70){
//                pct.prioridade = 1;
//                }
//                else if (pct.profissao == true){
//                pct.prioridade = 2;
//                }
//                else{
//                pct.prioridade = 3;     
//                }                   
            //Executar o comando
            ps.execute();
            JOptionPane.showMessageDialog(null, "Paciente cadastrado com sucesso!");
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Sistema indisponivel, tente novamente mais tarde.");
            e.printStackTrace();
        }                                
    }
    
    public void atualizarPacientes (Pacientes pct){
        String sql = "UPDATE tb_paciente SET nome = ?, idade= ?, profissao= ?, endereco= ?, prioridade= ? WHERE id = ?";
        
        ConnectionFactory factory = new ConnectionFactory();
        
        try(Connection conexao = factory.obterConexao();
            PreparedStatement ps = conexao.prepareStatement(sql)){
            ps.setString(1, pct.getNome());
            ps.setInt(2, pct.getIdade());
            ps.setBoolean(3, pct.isProfissao());
            ps.setString (4, pct.getEndereco());
            ps.setInt(5, pct.getPrioridade());
            ps.setInt(6, pct.getId());
            
            ps.execute();
            
            JOptionPane.showMessageDialog(null, "Paciente atualizado com sucesso!");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Sistema indisponivel, tente novamente mais tarde.");
            e.printStackTrace();
        }
    }
    public void excluirPacientes (Pacientes pct) {       
        String sql = "DELETE FROM tb_paciente WHERE id=?";       
        ConnectionFactory fabricaDeConexoes = new ConnectionFactory();
        try (Connection conexao = fabricaDeConexoes.obterConexao()) {            
            PreparedStatement ps = conexao.prepareStatement(sql);                     
            ps.setInt(1, pct.getId());
            ps.execute();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Sistema indisponivel, tente novamente mais tarde.");
        }
    }
    
    public List<Pacientes> listar() /*throws Exception*/{
        Connection conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<Pacientes> pacientes = new ArrayList<>();
        
        try {
            ps = conexao.prepareStatement("SELECT * FROM tb_paciente");
            rs = ps.executeQuery();
            
            while (rs.next()){
                
                Pacientes pct = new Pacientes();
                
                pct.setId(rs.getInt("id"));
                pct.setNome(rs.getString("nome"));
                pct.setIdade(rs.getInt("idade"));
                pct.setProfissao(rs.getBoolean("profissao"));
                pct.setEndereco(rs.getString("endereco"));
                pct.setDataa(rs.getDate("dataa"));
                pct.setVacinacao(rs.getString("vacinacao"));
                pct.setPrioridade(rs.getInt("prioridade"));
                pacientes.add(pct);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sistema Indisponivel, tente novamente mais tarde.");//Logger.getLogger(DAOAdm.class.getName()).log(Level.SEVERE, null, ex);
        }                 
     return pacientes;   
    }
    
    public List<Pacientes> exibirFila(){
        
        Connection conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<Pacientes> pacientes = new ArrayList<>();
        
        try {
            ps = conexao.prepareStatement("SELECT * FROM tb_paciente WHERE  vacinacao LIKE 'Sim' ORDER BY prioridade ASC"); 
            rs = ps.executeQuery();
            
            while (rs.next()){
                
                Pacientes pct = new Pacientes();
                
                pct.setId(rs.getInt("id"));
                pct.setNome(rs.getString("nome"));
                pct.setIdade(rs.getInt("idade"));
                pct.setProfissao(rs.getBoolean("profissao"));
                pct.setPrioridade(rs.getInt("prioridade"));                                               
                pct.setDataa(rs.getDate("dataa"));
                pct.setVacinacao(rs.getString("vacinacao"));
                
                
                pacientes.add(pct);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao exibir fila.");
        }
        return pacientes; 
        }
    public List<Pacientes> exibirFilaNaoVacinados(){
        
        Connection conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<Pacientes> pacientes = new ArrayList<>();
        
        try {
            ps = conexao.prepareStatement("SELECT * FROM tb_paciente WHERE  vacinacao LIKE ' ' ORDER BY prioridade ASC"); 
            rs = ps.executeQuery();
            
            while (rs.next()){
                
                Pacientes pct = new Pacientes();
                
                pct.setId(rs.getInt("id"));
                pct.setNome(rs.getString("nome"));
                pct.setIdade(rs.getInt("idade"));
                pct.setProfissao(rs.getBoolean("profissao"));
                pct.setPrioridade(rs.getInt("prioridade"));                                               
                pct.setDataa(rs.getDate("dataa"));
                pct.setVacinacao(rs.getString("vacinacao"));
                
                
                pacientes.add(pct);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao exibir fila.");
        }
        return pacientes; 
        }
        
    public List<Pacientes> gerarRelatorio(String dataini, String datafin)throws Exception{
        
        Connection conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<Pacientes> pacientes = new ArrayList<>();
        System.out.println(dataini);
        System.out.println(datafin);
        try {
            ps = conexao.prepareStatement("SELECT * FROM tb_paciente WHERE dataa >= ? AND dataa <= ?");
//            ps = conexao.prepareStatement("SELECT * FROM tb_paciente WHERE dataa BETWEEN ? AND ?");
            ps.setDate(1,new java.sql.Date(Utils.converter(dataini).getTime()));
            ps.setDate(2, new java.sql.Date(Utils.converter(datafin).getTime()));
    
            rs = ps.executeQuery();
            
            while (rs.next()){
                
                Pacientes pct = new Pacientes();
                               
                pct.setDataa(rs.getDate("dataa"));                
                pct.setIdade(rs.getInt("idade"));
               
                System.out.println(pct);
                pacientes.add(pct);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sistema Indisponivel, tente novamente mais tarde.");//Logger.getLogger(DAOAdm.class.getName()).log(Level.SEVERE, null, ex);
        }                 
     return pacientes;   

    }
    public List<Pacientes> gerarRelatorio90(String dataini, String datafin)throws Exception{
        
        Connection conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<Pacientes> pacientes = new ArrayList<>();
        System.out.println(dataini);
        System.out.println(datafin);
        try {
            ps = conexao.prepareStatement("SELECT * FROM tb_paciente WHERE idade >= 90 AND dataa >= ? AND dataa <= ?");
            //SELECT * FROM tb_paciente WHERE idade >= 70 AND idade <90 AND dataa >= ? AND dataa <= ?;
            ps.setDate(1,new java.sql.Date(Utils.converter(dataini).getTime()));
            ps.setDate(2, new java.sql.Date(Utils.converter(datafin).getTime()));
    
            rs = ps.executeQuery();
            
            while (rs.next()){
                
                Pacientes pct = new Pacientes();
                               
                pct.setDataa(rs.getDate("dataa"));                
                pct.setIdade(rs.getInt("idade"));
               
                System.out.println(pct);
                pacientes.add(pct);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sistema Indisponivel, tente novamente mais tarde.");//Logger.getLogger(DAOAdm.class.getName()).log(Level.SEVERE, null, ex);
        }                 
     return pacientes;   

    }
    public List<Pacientes> gerarRelatorioMaior70(String dataini, String datafin)throws Exception{
        
        Connection conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<Pacientes> pacientes = new ArrayList<>();
        System.out.println(dataini);
        System.out.println(datafin);
        try {
            ps = conexao.prepareStatement("SELECT * FROM tb_paciente WHERE idade >= 70 AND idade <90 AND dataa >= ? AND dataa <= ?");
            //SELECT * FROM tb_paciente WHERE idade >= 70 AND idade <90 AND dataa >= ? AND dataa <= ?;
            ps.setDate(1,new java.sql.Date(Utils.converter(dataini).getTime()));
            ps.setDate(2, new java.sql.Date(Utils.converter(datafin).getTime()));
    
            rs = ps.executeQuery();
            
            while (rs.next()){
                
                Pacientes pct = new Pacientes();
                               
                pct.setDataa(rs.getDate("dataa"));                
                pct.setIdade(rs.getInt("idade"));
               
                System.out.println(pct);
                pacientes.add(pct);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sistema Indisponivel, tente novamente mais tarde.");//Logger.getLogger(DAOAdm.class.getName()).log(Level.SEVERE, null, ex);
        }                 
     return pacientes;   

    }
    public List<Pacientes> gerarRelatorioMaior50(String dataini, String datafin)throws Exception{
        
        Connection conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<Pacientes> pacientes = new ArrayList<>();
        System.out.println(dataini);
        System.out.println(datafin);
        try {
            ps = conexao.prepareStatement("SELECT * FROM tb_paciente WHERE idade >= 50 AND idade <70 AND dataa >= ? AND dataa <= ?");
            //SELECT * FROM tb_paciente WHERE idade >= 70 AND idade <90 AND dataa >= ? AND dataa <= ?;
            ps.setDate(1,new java.sql.Date(Utils.converter(dataini).getTime()));
            ps.setDate(2, new java.sql.Date(Utils.converter(datafin).getTime()));
    
            rs = ps.executeQuery();
            
            while (rs.next()){
                
                Pacientes pct = new Pacientes();
                               
                pct.setDataa(rs.getDate("dataa"));                
                pct.setIdade(rs.getInt("idade"));
               
                System.out.println(pct);
                pacientes.add(pct);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sistema Indisponivel, tente novamente mais tarde.");//Logger.getLogger(DAOAdm.class.getName()).log(Level.SEVERE, null, ex);
        }                 
     return pacientes;   

    }
    public List<Pacientes> gerarRelatorioMenor50(String dataini, String datafin)throws Exception{
        
        Connection conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<Pacientes> pacientes = new ArrayList<>();
        System.out.println(dataini);
        System.out.println(datafin);
        try {
            ps = conexao.prepareStatement("SELECT * FROM tb_paciente WHERE idade <50  AND dataa >= ? AND dataa <= ?");
            //SELECT * FROM tb_paciente WHERE idade >= 70 AND idade <90 AND dataa >= ? AND dataa <= ?;
            ps.setDate(1,new java.sql.Date(Utils.converter(dataini).getTime()));
            ps.setDate(2, new java.sql.Date(Utils.converter(datafin).getTime()));
    
            rs = ps.executeQuery();
            
            while (rs.next()){
                
                Pacientes pct = new Pacientes();
                               
                pct.setDataa(rs.getDate("dataa"));                
                pct.setIdade(rs.getInt("idade"));
               
                System.out.println(pct);
                pacientes.add(pct);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sistema Indisponivel, tente novamente mais tarde.");//Logger.getLogger(DAOAdm.class.getName()).log(Level.SEVERE, null, ex);
        }                 
     return pacientes;   

    }
    
    
     public void adicionarVacina (Pacientes pct){
        String sql = "UPDATE tb_paciente SET dataa = ?, vacinacao = ? WHERE id = ?";
        
        ConnectionFactory factory = new ConnectionFactory();
        
        try(Connection conexao = factory.obterConexao();
            PreparedStatement ps = conexao.prepareStatement(sql)){
            
                                
            ps.setDate(1,new java.sql.Date(pct.getDataa().getTime()) );
            ps.setString(2, pct.getVacinacao());
            ps.setInt(3, pct.getId());
            
            ps.execute();
            
            
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Marque a vacina!.");
        }
    }
}