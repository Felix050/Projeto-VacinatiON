package model.bean;

import java.util.Date;

/**
 *
 * @author vinic
 */
public class Pacientes implements Comparable<Pacientes> {
    private int id;
    private String nome;
    public int idade;
    public boolean profissao;
    private String endereco;
    private Date dataa;
    public String vacinacao;
    public int prioridade;
    private String dataini;
    private String datafin;
    
    @Override
    public String toString() {
        return "Pacientes{" + "id=" + id + ", nome=" + nome + ", idade=" + idade + ", profissao=" + profissao + ", endereco=" + endereco + ", dataa=" + dataa + ", vacinacao=" + vacinacao + ", prioridade=" + prioridade + ", dataini=" + dataini + ", datafin=" + datafin + '}';
    }

    public int getPrioridade() {
          
    return prioridade;
    } 


    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }
    
    

    public String getDataini() {
        return dataini;
    }

    public void setDataini(String dataini) {
        this.dataini = dataini;
    }

    public String getDatafin() {
        return datafin;
    }

    public void setDatafin(String datafin) {
        this.datafin = datafin;
    }

    
    public String getVacinacao() {
        return vacinacao;
    }

    public void setVacinacao(String vacinacao) {
        this.vacinacao = vacinacao;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pacientes() {
    }


    public boolean isProfissao() {
        return profissao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
    
    public void setProfissao(boolean profissao) {
        this.profissao = profissao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Date getDataa() {
        return dataa;
    }

    public void setDataa(Date data) {
        this.dataa = data;
    }    

    @Override
    public int compareTo(Pacientes arg0) {
        return this.dataa.compareTo(arg0.dataa);
    }

}
