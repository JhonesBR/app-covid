import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author Grupo E
 */
public class App {
    // Lista das contas e consultas
    private static List<Paciente> pacientes = new ArrayList<Paciente>();
    private static List<Equipe> funcionarios = new ArrayList<Equipe>();
    private static List<Consulta> consultas = new ArrayList<Consulta>();

    // Scanner para leitura do teclado
    private static Scanner sc = new Scanner(System.in);
    private static Scanner scInt = new Scanner(System.in);

    public static void main(String[] args) {
        // Conta da equipe
        // CPF: 987
        // Senha: admin
        funcionarios.add(new Equipe("GrupoE", "987", "admin"));

        // Conta de paciente1
        // CPF: 10987654321
        // Senha: admin
        // Conta de paciente2
        // CPF: 123
        // Senha: admin
        pacientes.add(new Paciente("19994958816", "Limeira", "SP", "Perda de apetite", "Plínio", "10987654321", "admin"));
        pacientes.add(new Paciente("19994958817", "Limeira", "SP", "Tosse", "Varese", "123", "admin"));

        // Mostra as informações sobre o covid
        showInfos();

        // Instanciação de variaveis
        int login = 0;
        int index = -1;
        String escolhaSintomas;
        String cpf = "";

        String confirmaCPF = "N";
        while("S".equals(confirmaCPF.toUpperCase()) == false){
            // Insere o cpf
            System.out.print("\n\nCPF: ");
            cpf = sc.nextLine();
            System.out.print("Seu CPF é " + cpf + "? (S/N): ");
            confirmaCPF = sc.nextLine();
        }

        // Verifica se ja existe cadastro
        int cadastroFeito = verificarCadastro(cpf);
        if (cadastroFeito == 0){
            System.out.print("Você apresenta algum dos sintomas listados? (S/N): ");
            escolhaSintomas = sc.nextLine();
            if ((escolhaSintomas.toUpperCase()).equals("S")) {
                // Realiza cadastro de paciente
                System.out.print("Nome: ");
                String nome = sc.nextLine();
                System.out.print("Telefone: ");
                String telefone = sc.nextLine();
                System.out.print("Estado: ");
                String estado = sc.nextLine();
                System.out.print("Cidade: ");
                String cidade = sc.nextLine();
                System.out.print("Senha: ");
                String senha = sc.nextLine();
                System.out.print("Descrição dos sintomas: ");
                String sintomas = sc.nextLine();

                pacientes.add(new Paciente(telefone, cidade, estado, sintomas, nome, cpf, senha));
                login = 1;
            } else{
                // Não apresenta sintomas
                System.out.println("\n\nObrigado por se manter seguro!!\n\n");
            }
        } else{
            // Login
            login = login(cpf);
        }

        if (login == 1){
            // Login Paciente
            for (int i=0; i<pacientes.size(); i++)
                if ((pacientes.get(i).cpf).equals(cpf))
                    index = i;
            int opc = -1;
            System.out.println("\n\nBem vindo " + (pacientes.get(index)).getNome());
            while (opc != 0) {
                System.out.println("\nMenu:");
                System.out.println("\t1) Dados cadastrados");
                System.out.println("\t2) Informações sobre o Covid-19");
                System.out.println("\t3) Consultas marcadas");
                System.out.println("\t0) Sair");
                opc = scInt.nextInt();

                if (opc == 1) {
                    // Opção de visualizar dados cadastrados
                    (pacientes.get(index)).visualizarInformacoes();
                } else if (opc == 2) {
                    // Opção de visualizar informações sobre o covid
                    showInfos();
                } else if (opc == 3) {
                    // Opção de visualizar consultas marcadas
                } else{
                    opc = 0;
                }
            }
        }
        if (login == 2){
            // Login funcionário
            for (int i=0; i<funcionarios.size(); i++)
                if ((funcionarios.get(i).cpf).equals(cpf))
                    index = i;
            int opc = -1;
            int opc2 = -1;
            System.out.println("Bem vindo " + (funcionarios.get(index)).getNome());
            while (opc != 0) {
                System.out.println("\nMenu:");
                System.out.println("\t1) Listar Pacientes");
                System.out.println("\t2) Procurar Paciente");
                System.out.println("\t3) Consultas marcadas");
                System.out.println("\t0) Sair");
                opc = scInt.nextInt();

                if (opc == 1) {
                    // Opção de listar Pacientes
                    listarPacientes();
                    marcarConsulta();
                } else if (opc == 2) {
                    // Opção de procurar Paciente
                    System.out.println("\nMenu:");
                    System.out.println("\t1) Procurar por CPF");
                    System.out.println("\t2) Procurar por Estado");
                    System.out.println("\t3) Procurar por Cidade");
                    opc2 = scInt.nextInt();

                    String busca;
                    if (opc2 == 1) {
                        // Procurar por CPF
                        System.out.print("\nInsira o CPF: ");
                        busca = sc.nextLine();
                        procurarPaciente(1, busca, "", "");
                    } else if (opc2 == 2) {
                        // Procurar por Estado
                        System.out.print("\nInsira o Estado: ");
                        busca = sc.nextLine();
                        procurarPaciente(2, "", "", busca);
                    } else if (opc2 == 3) {
                        // Procurar por Cidade
                        System.out.print("\nInsira a Cidade: ");
                        busca = sc.nextLine();
                        procurarPaciente(3, "", busca, "");
                    }
                    marcarConsulta();
                } else if (opc == 3) {
                    // Opção de visualizar consultas marcadas
                    listarConsultas();
                } else{
                    opc = 0;
                }
            }
        }
    }


    public static void showInfos(){
        System.out.println("\n\nSintomas do Coronavirus (COVID-19): ");
        System.out.println("Sintomas mais comuns: Febre, Tosse seca, Fadiga\n");
        System.out.println("Sintomas menos comuns: Perda de paladar e olfato, Congestão nasal, Conjuntivite, Dor de Garaganta, Dor de cabeça, ");
        System.out.println("                       Dores musculares, Erupções cutâneas, Náusea e Vômito\n");
        System.out.println("Sintomas Graves: Falta de Ar, Perda de apetite, Confusão, Dor persistente, pressão no peito e alta temperatura");
    }


    public static int verificarCadastro(String cpf){
        // Retorna 1 para CPF de Paciente
        for (int i=0; i<pacientes.size(); i++) {
            if ((pacientes.get(i)).cpf.equals(cpf)){
                return 1;
            }
        }
        // Retorna 2 para CPF de Funcionário
        for (int i=0; i<funcionarios.size(); i++) {
            if ((funcionarios.get(i)).cpf.equals(cpf)){
                return 2;
            }
        }
        // Retorna 0 para cadastrar
        return 0;
    }


    public static int login(String cpf){
        int count = 0;
        while (count <= 5){
            System.out.print("Senha: ");
            String senha = sc.nextLine();
            for (int i=0; i<pacientes.size(); i++) {
                if ((pacientes.get(i).cpf).equals(cpf) && (pacientes.get(i).senha).equals(senha)) {
                    // Login realizado como paciente
                    return 1;
                }
            }

            for (int i=0; i<funcionarios.size(); i++) {
                if ((funcionarios.get(i).cpf).equals(cpf) && (funcionarios.get(i).senha).equals(senha)) {
                    // Login realizado como paciente
                    return 2;
                }
            }
            count++;
            System.out.println("Usuário/Senha invalidos\n\n");
        }
        // Falha no login
        System.out.println("\n\nLimite de tentativas excedido\n\n");
        return 0;
    }


    public static void listarPacientes() {
        System.out.println("\n\nLista de pacientes: ");
        for (int i=0; i<pacientes.size(); i++) {
            System.out.print("- Nome: " + (pacientes.get(i).getNome()));
            System.out.println("   CPF: " + (pacientes.get(i).getCpf()));
        }
        System.out.print("\n");
    }


    public static void procurarPaciente(int mode, String cpf, String cidade, String estado) {
        if (mode == 1) {
            // Procura por CPF
            for (int i = 0; i < pacientes.size(); i++) {
                if ((((pacientes.get(i)).getCpf()).toLowerCase()).equals(cpf.toLowerCase())){
                    System.out.println("\nPaciente CPF: " + (pacientes.get(i)).getCpf());
                    System.out.print("\tNome: " + (pacientes.get(i).getNome()));
                    System.out.println("\tTelefone: " + (pacientes.get(i).getTelefone()));
                    System.out.println("\tEstado: " + (pacientes.get(i).getEstado()));
                    System.out.println("\tCidade: " + (pacientes.get(i).getCidade()));
                    System.out.println("\tSintomas: " + (pacientes.get(i).getSintomas()));
                }

            }
        } else if (mode == 2){
            // Procura por Estado
            for (int i = 0; i < pacientes.size(); i++) {
                if ((((pacientes.get(i)).getEstado()).toLowerCase()).equals(estado.toLowerCase())){
                    System.out.println("\nPaciente CPF: " + (pacientes.get(i)).getCpf());
                    System.out.print("\tNome: " + (pacientes.get(i).getNome()));
                    System.out.println("\tTelefone: " + (pacientes.get(i).getTelefone()));
                    System.out.println("\tEstado: " + (pacientes.get(i).getEstado()));
                    System.out.println("\tCidade: " + (pacientes.get(i).getCidade()));
                    System.out.println("\tSintomas: " + (pacientes.get(i).getSintomas()));
                }
            }

        } else if (mode == 3){
            // Procura por Cidade
            for (int i = 0; i < pacientes.size(); i++) {
                if ((((pacientes.get(i)).getCidade()).toLowerCase()).equals(cidade.toLowerCase())){
                    System.out.println("\nPaciente CPF: " + (pacientes.get(i)).getCpf());
                    System.out.print("\tNome: " + (pacientes.get(i).getNome()));
                    System.out.println("\tTelefone: " + (pacientes.get(i).getTelefone()));
                    System.out.println("\tEstado: " + (pacientes.get(i).getEstado()));
                    System.out.println("\tCidade: " + (pacientes.get(i).getCidade()));
                    System.out.println("\tSintomas: " + (pacientes.get(i).getSintomas()));
                }
            }
        }
        System.out.print("\n");
    }

    public static void marcarConsulta(){
        String cons;
        String cpf = "";
        int count = 0;
        int index = 0;
        System.out.print("Deseja marcar uma consulta? (S/N): ");
        cons = sc.nextLine();
        if ((cons.toUpperCase()).equals("S")) {
            // Deseja marcar consulta
            while (count <= 5){
                System.out.print("Insira o CPF do paciente: ");
                cpf = sc.nextLine();
                if (verificarCadastro(cpf) == 1) {
                    // Encontrou CPF
                    for (int i = 0; i < pacientes.size(); i++) {
                        // Obtem o index do paciente
                        if ((((pacientes.get(i)).getCpf()).toLowerCase()).equals(cpf.toLowerCase())){
                            index = i;
                        }
                    }
                    System.out.print("Data da consulta (DD/MM/AAAA): ");
                    String data = sc.nextLine();
                    Data dataConsulta = new Data(Integer.parseInt(data.substring(0, 2)), Integer.parseInt(data.substring(3, 5)), Integer.parseInt(data.substring(6, 10)));
                    consultas.add(new Consulta(pacientes.get(index), dataConsulta));
                    Consulta consultaAtual = consultas.get(consultas.size()-1);
                    System.out.println("\nConsulta com " + (consultaAtual.getPaciente()).getNome() + " marcada com sucesso" +
                                        " para o dia " + (consultaAtual.getDataConsulta()) + "!\n");
                    break;
                } else {
                    // Não encontrou CPF
                    System.out.println("CPF não encontrado");
                }
                count++;
            }
            if (count > 5) {
                System.out.println("\nOperação cancelada\n");
            }
        }
    }

    public static void listarConsultas() {
        for (int i=0; i< consultas.size(); i++){
            System.out.println("Consulta " + i+1 + ":");
            System.out.println("\tData: " + (consultas.get(i)).getDataConsulta());
            System.out.println("\tNome do paciente: " + (consultas.get(i).getPaciente()).getNome());
            System.out.println("\tSintomas do paciente: " + (consultas.get(i).getPaciente()).getSintomas());
            System.out.println("\tCidade da consulta: " + (consultas.get(i).getPaciente()).getCidade());
            System.out.print("\n");
        }
    }
}