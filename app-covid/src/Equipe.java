/**
 * @author Grupo E
 */
public class Equipe extends Conta {

    public Equipe(String nome, String cpf, String senha) {
        super(nome, cpf, senha, 1);
    }

    public void marcarConsulta(Data data, Paciente paciente){
        Consulta consulta = new Consulta(paciente, data);
    }

}
