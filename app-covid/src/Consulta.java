/**
 * @author Grupo E
 */
public class Consulta {

    private Paciente paciente;
    private Data dataConsulta;
    private boolean statusConsulta;

    public Paciente getPaciente() { return paciente; }

    public Data getDataConsulta() { return dataConsulta; }

    public boolean isStatusConsulta() { return statusConsulta; }

    public Consulta(Paciente paciente, Data dataConsulta) {
        this.paciente = paciente;
        this.dataConsulta = dataConsulta;
        this.statusConsulta = false;
    }

    public void marcarConcluida(boolean pacienteInfectado) {
        this.paciente.setPacienteInfectado(pacienteInfectado);
        this.statusConsulta = true;
    }
}
