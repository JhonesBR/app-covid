package app.covid;

/**
 * @author João Vitor Oliveira de Melo
 */
public abstract class Conta {
    protected String nome;
    protected String cpf;
    protected String senha;
    protected int id;

    public Conta(String nome, String cpf, String senha, int id) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.id = id;
    }
    
    public int getId() {
        return this.id;
    }

    public String getNome() {
        return nome;
    }
    
}
