public class Data {

    public int dia;
    public int mes;
    public int ano;

    public Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    @Override
    public String toString() {
        return String.format("%02d" , dia) + "/" + String.format("%02d", mes) + "/" + String.format("%04d" , ano);
    }

    public String getDia() {
        return Integer.toString(this.dia);
    }

    public String getMes() {
        return Integer.toString(this.mes);
    }

    public String getAno() {
        return Integer.toString(this.ano);
    }
}
