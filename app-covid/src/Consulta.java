/**
 * @author Grupo E
 */
public class Consulta {

    private Paciente paciente;
    private Data dataConsulta;
    private boolean statusConsulta;

    public Paciente getPaciente() { return paciente; }

    public Data getDataConsulta() { return dataConsulta; }

    public boolean getStatusConsulta() { return statusConsulta; }

    public Consulta(Paciente paciente, Data dataConsulta) {
        this.paciente = paciente;
        this.dataConsulta = dataConsulta;
        this.statusConsulta = false;
    }

    public void marcarConcluida(boolean pacienteInfectado) {
        this.paciente.setPacienteInfectado(pacienteInfectado);
        this.statusConsulta = true;
    }

    public void setStatusConsulta(boolean statusConsulta) {
        this.statusConsulta = statusConsulta;
    }

    public String getDiaConsulta(){
        return (this.dataConsulta).getDia();
    }

    public String getMesConsulta(){
        return (this.dataConsulta).getMes();
    }

    public String getAnoConsulta(){
        return (this.dataConsulta).getAno();
    }
}
