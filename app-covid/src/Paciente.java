/**
 * @author Grupo E
 */
public class Paciente extends Conta {
    private String telefone;
    private String cidade;
    private String estado;
    private String sintomas;
    private boolean pacienteInfectado;

    public boolean getPacienteInfectado() { return pacienteInfectado; }
    public void setPacienteInfectado(boolean pacienteInfectado) { this.pacienteInfectado = pacienteInfectado; }

    public String getTelefone() { return telefone; }

    public String getCidade() { return cidade; }

    public String getEstado() { return estado; }

    public String getSintomas() { return sintomas; }

    public Paciente(String telefone, String cidade, String estado, String sintomas, String nome, String cpf, String senha) {
        super(nome, cpf, senha, 0);
        this.telefone = telefone;
        this.cidade = cidade;
        this.estado = estado;
        this.sintomas = sintomas;
    }

    public void visualizarInformacoes() {
        System.out.println("Informações sobre " + this.nome);
        System.out.println("CPF: " + this.cpf);
        System.out.println("Telefone: " + this.telefone);
        System.out.println("Estado: " + this.estado);
        System.out.println("Cidade: " + this.cpf);
        System.out.println("Sintomas: " + this.sintomas);
    }

}
