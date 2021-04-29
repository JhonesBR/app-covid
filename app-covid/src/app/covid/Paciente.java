package app.covid;

/**
 * @author João Vitor Oliveira de Melo
 */
public class Paciente extends Conta {
    private String telefone;
    private String cidade;
    private String estado;
    private String sintomas;

    public Paciente(String telefone, String cidade, String estado, String sintomas, String nome, String cpf, String senha) {
        super(nome, cpf, senha, 0);
        this.telefone = telefone;
        this.cidade = cidade;
        this.estado = estado;
        this.sintomas = sintomas;
    }
    
}
