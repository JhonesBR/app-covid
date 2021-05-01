/**
 * @author Grupo E
 */
public abstract class Conta {
    protected String nome;
    protected String cpf;
    protected String senha;
    protected int id;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public int getId() { return id; }


    public Conta(String nome, String cpf, String senha, int id) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.id = id;
    }

}