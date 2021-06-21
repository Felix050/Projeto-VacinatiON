package model.bean;

public class Administradores  { 
    private int id;
    private String nome;
    private String senha;
    private int idade;
    private String endereco;
    private String cpf;

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    

    public Administradores() {
    }
    

    public Administradores(int id, String nome, String senha) {
        this(nome, senha);
        this.id = id;        
        //this.nome = nome;
        //this.senha = senha;
    }

    public Administradores(String nome, String senha) {
        setNome(nome);
        setSenha(senha);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String sernha) {
        this.senha = sernha;
    }
        
}
