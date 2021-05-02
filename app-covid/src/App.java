import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Grupo E
 */
public class App {
    // Lista das contas, consultas e chats
    private static final List<Paciente> pacientes = new ArrayList<>();
    private static final List<Equipe> funcionarios = new ArrayList<>();
    private static final List<Consulta> consultas = new ArrayList<>();
    private static final List<Chat> chats = new ArrayList<>();

    // Scanner para leitura do teclado
    private static final Scanner sc = new Scanner(System.in);
    private static final Scanner scInt = new Scanner(System.in);

    // Diretório de execução do programa
    private static final String dir = System.getProperty("user.dir");

    public static void main(String[] args) {
        loadData();

        /*
            Conta da equipe
            Nome: GrupoE
            CPF: 99020705075
            Senha: admin
         */

        /*
            Conta do Paciente 1
            Nome: Clara Eloá
            CPF: 48187139455
            Senha: paciente1
            Cidade: Paulista
            Estado: PE
            Sintomas: Tosse
            Telefone: 81989391439
         */

        /*
            Conta do Paciente 2
            Nome: Elias Bryan
            CPF: 51146611242
            Senha: paciente2
            Cidade: Manaus
            Estado: AM
            Sintomas: Dor no pulmao
            Telefone: 92989293051
         */

        /*
            Conta do Paciente 3
            Nome: Nelson Assis
            CPF: 10739045202
            Senha: paciente3
            Cidade: Rio de Janeiro
            Estado: RJ
            Sintomas: Dor de garganta
            Telefone: 21986010326
         */

        // Mostra as informações sobre o covid
        showInfos();

        // Instanciação de variaveis
        int login = 0;
        int index = -1;
        String escolhaSintomas;
        String cpf = "";

        String confirmaCPF = "N";
        while(!"S".equalsIgnoreCase(confirmaCPF)){
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
            if ("S".equalsIgnoreCase(escolhaSintomas)) {
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

                Paciente p = new Paciente(telefone, cidade, estado, sintomas, nome, cpf, senha);
                pacientes.add(p);
                persistNewPaciente(p);
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
            System.out.println("\n\nBem vindo(a) " + (pacientes.get(index)).getNome());
            while (opc != 0) {
                System.out.println("\nMenu:");
                System.out.println("\t1) Dados cadastrados");
                System.out.println("\t2) Informações sobre o Covid-19");
                System.out.println("\t3) Consultas marcadas");
                System.out.println("\t4) Abrir chat");
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
                    consultaPaciente(pacientes.get(index));
                } else if (opc == 4){
                    // Opção de abrir chat
                    int i = iniciarChatPaciente(pacientes.get(index));
                } else {
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
            int opc2;
            System.out.println("Bem vindo(a) " + (funcionarios.get(index)).getNome());
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
                    decisaoEquipe(funcionarios.get(index));
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
                    decisaoEquipe(funcionarios.get(index));
                } else if (opc == 3) {
                    // Opção de visualizar consultas marcadas
                    listarConsultas();
                } else{
                    opc = 0;
                }
            }
        }
    }

    public static void loadData() {
        String dataPacientes = dir + "\\src\\pacientes.data";
        String dataEquipe = dir + "\\src\\equipe.data";
        String dataConsultas = dir + "\\src\\consultas.data";
        String dataChat = dir + "\\src\\chat.data";

        try {
            // Pacientes
            FileReader arq = new FileReader(dataPacientes);
            BufferedReader lerArq = new BufferedReader(arq);
            String telefone, cidade, estado, sintomas, nome, cpf, senha, statusPaciente;
            telefone = lerArq.readLine();
            while (telefone != null) {
                cidade = lerArq.readLine();
                estado = lerArq.readLine();
                sintomas = lerArq.readLine();
                nome = lerArq.readLine();
                cpf = lerArq.readLine();
                senha = lerArq.readLine();
                statusPaciente = lerArq.readLine();
                pacientes.add(new Paciente(telefone, cidade, estado, sintomas, nome, cpf, senha));
                if (statusPaciente.equalsIgnoreCase("true")) {
                    pacientes.get(pacientes.size()-1).setPacienteInfectado(true);
                } else {
                    pacientes.get(pacientes.size()-1).setPacienteInfectado(false);
                }
                telefone = lerArq.readLine();
            }

            // Funcionários
            arq = new FileReader(dataEquipe);
            lerArq = new BufferedReader(arq);
            nome = lerArq.readLine();
            while (nome != null) {
                cpf = lerArq.readLine();
                senha = lerArq.readLine();
                funcionarios.add(new Equipe(nome, cpf, senha));
                nome = lerArq.readLine();
            }

            // Consultas
            arq = new FileReader(dataConsultas);
            lerArq = new BufferedReader(arq);
            String tmpStatus;
            int dia, mes, ano;
            telefone = lerArq.readLine();
            while (telefone != null) {
                cidade = lerArq.readLine();
                estado = lerArq.readLine();
                sintomas = lerArq.readLine();
                nome = lerArq.readLine();
                cpf = lerArq.readLine();
                senha = lerArq.readLine();
                dia = Integer.parseInt(lerArq.readLine());
                mes = Integer.parseInt(lerArq.readLine());
                ano = Integer.parseInt(lerArq.readLine());
                tmpStatus = lerArq.readLine();

                Data data = new Data(dia, mes, ano);
                Paciente p = new Paciente(telefone, cidade, estado, sintomas, nome, cpf, senha);
                consultas.add(new Consulta(p, data));
                if (tmpStatus.equalsIgnoreCase("True")){
                    (consultas.get(consultas.size()-1)).setStatusConsulta(true);
                }
                telefone = lerArq.readLine();
            }

            // Chats
            arq = new FileReader(dataChat);
            lerArq = new BufferedReader(arq);
            String nome2, cpf2, data, msg, id1;
            nome = lerArq.readLine();
            while (nome != null) {
                cpf = lerArq.readLine();
                id1 = lerArq.readLine();
                nome2 = lerArq.readLine();
                cpf2 = lerArq.readLine();
                data = lerArq.readLine();
                msg = lerArq.readLine();

                if (id1.equals("1")){
                    Equipe s = new Equipe(nome, cpf, "");
                    Paciente r = new Paciente("", "", "", "", nome2, cpf2, "");
                    chats.add(new Chat(s, r, data, msg));
                } else {
                    Paciente s = new Paciente("", "", "", "", nome, cpf, "");
                    Equipe r = new Equipe(nome2, cpf2, "");
                    chats.add(new Chat(s, r, data, msg));
                }

                nome = lerArq.readLine();
            }
            lerArq.close();


        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
    }

    public static void showInfos() {
        System.out.println("\n\nSintomas do Coronavirus (COVID-19): ");
        System.out.println("Sintomas mais comuns: Febre, Tosse seca, Fadiga\n");
        System.out.println("Sintomas menos comuns: Perda de paladar e olfato, Congestão nasal, Conjuntivite, Dor de Garaganta, Dor de cabeça, ");
        System.out.println("                       Dores musculares, Erupções cutâneas, Náusea e Vômito\n");
        System.out.println("Sintomas Graves: Falta de Ar, Perda de apetite, Confusão, Dor persistente, pressão no peito e alta temperatura");
    }

    public static int verificarCadastro(String cpf) {
        // Retorna 1 para CPF de Paciente
        for (Paciente paciente : pacientes) {
            if (paciente.cpf.equals(cpf)) {
                return 1;
            }
        }
        // Retorna 2 para CPF de Funcionário
        for (Equipe funcionario : funcionarios) {
            if (funcionario.cpf.equals(cpf)) {
                return 2;
            }
        }
        // Retorna 0 para cadastrar
        return 0;
    }

    public static int login(String cpf) {
        int count = 0;
        while (count <= 5){
            System.out.print("Senha: ");
            String senha = sc.nextLine();
            for (Paciente paciente : pacientes) {
                if ((paciente.cpf).equals(cpf) && (paciente.senha).equals(senha)) {
                    // Login realizado como paciente
                    return 1;
                }
            }

            for (Equipe funcionario : funcionarios) {
                if ((funcionario.cpf).equals(cpf) && (funcionario.senha).equals(senha)) {
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
        for (Paciente paciente : pacientes) {
            System.out.print("- Nome: " + (paciente.getNome()));
            System.out.println("   CPF: " + (paciente.getCpf()));
        }
        System.out.print("\n");
    }

    public static void procurarPaciente(int mode, String cpf, String cidade, String estado) {
        if (mode == 1) {
            // Procura por CPF
            for (Paciente paciente : pacientes) {
                if (((paciente.getCpf())).equalsIgnoreCase(cpf)) {
                    System.out.println("\nPaciente CPF: " + paciente.getCpf());
                    System.out.print("\tNome: " + (paciente.getNome()));
                    System.out.println("\tTelefone: " + (paciente.getTelefone()));
                    System.out.println("\tEstado: " + (paciente.getEstado()));
                    System.out.println("\tCidade: " + (paciente.getCidade()));
                    System.out.println("\tSintomas: " + (paciente.getSintomas()));
                }

            }
        } else if (mode == 2){
            // Procura por Estado
            for (Paciente paciente : pacientes) {
                if (((paciente.getEstado())).equalsIgnoreCase(estado)) {
                    System.out.println("\nPaciente CPF: " + paciente.getCpf());
                    System.out.print("\tNome: " + (paciente.getNome()));
                    System.out.println("\tTelefone: " + (paciente.getTelefone()));
                    System.out.println("\tEstado: " + (paciente.getEstado()));
                    System.out.println("\tCidade: " + (paciente.getCidade()));
                    System.out.println("\tSintomas: " + (paciente.getSintomas()));
                }
            }

        } else if (mode == 3){
            // Procura por Cidade
            for (Paciente paciente : pacientes) {
                if (((paciente.getCidade())).equalsIgnoreCase(cidade)) {
                    System.out.println("\nPaciente CPF: " + paciente.getCpf());
                    System.out.print("\tNome: " + (paciente.getNome()));
                    System.out.println("\tTelefone: " + (paciente.getTelefone()));
                    System.out.println("\tEstado: " + (paciente.getEstado()));
                    System.out.println("\tCidade: " + (paciente.getCidade()));
                    System.out.println("\tSintomas: " + (paciente.getSintomas()));
                }
            }
        }
        System.out.print("\n");
    }

    public static void marcarConsulta() {
        String cpf;
        int count = 0;
        int index = 0;

        while (count <= 5){
            System.out.print("Insira o CPF do paciente: ");
            cpf = sc.nextLine();
            if (verificarCadastro(cpf) == 1) {
                // Encontrou CPF
                for (int i = 0; i < pacientes.size(); i++) {
                    // Obtem o index do paciente
                    if ((((pacientes.get(i)).getCpf())).equalsIgnoreCase(cpf)){
                        index = i;
                    }
                }
                System.out.print("Data da consulta (DD/MM/AAAA): ");
                String data = sc.nextLine();
                Data dataConsulta = new Data(Integer.parseInt(data.substring(0, 2)), Integer.parseInt(data.substring(3, 5)), Integer.parseInt(data.substring(6, 10)));
                Consulta c = new Consulta(pacientes.get(index), dataConsulta);
                consultas.add(c);
                persistNewConsulta(c);
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

    public static void iniciarChatEquipe(Equipe atendente) {
        String cpf;
        int count = 0;
        int index = 0;

        while (count <= 5){
            System.out.print("Insira o CPF do paciente: ");
            cpf = sc.nextLine();
            if (verificarCadastro(cpf) == 1) {
                // Encontrou CPF
                for (int i = 0; i < pacientes.size(); i++) {
                    // Obtem o index do paciente
                    if ((((pacientes.get(i)).getCpf())).equalsIgnoreCase(cpf)){
                        index = i;
                    }
                }

                showMessages(atendente, pacientes.get(index));
                sendMessage(atendente, pacientes.get(index));

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

    public static int iniciarChatPaciente(Paciente paciente) {
        for (Chat chat: chats) {
            if ((chat.getReceiver()).getCpf().equals(paciente.getCpf())) {
                Conta s = chat.getSender();
                showMessages(paciente, s);
                sendMessage(paciente, s);
                return 1;
            }
        }
        System.out.println("\nVocê não tem mensagens :(\n");
        return 0;
    }

    public static void decisaoEquipe(Equipe equipe) {
        int opc;
        System.out.println("\nMenu:");
        System.out.println("\t1) Marcar Consulta");
        System.out.println("\t2) Iniciar Chat");
        System.out.println("\t0) Sair");

        opc = scInt.nextInt();
        if (opc == 1){
            marcarConsulta();
        } else if (opc == 2){
            iniciarChatEquipe(equipe);
        }
    }

    public static void listarConsultas() {
        int count = 0;
        for (Consulta consulta : consultas) {
            System.out.println("Consulta " + (count+1) + ":");
            System.out.println("\tData: " + consulta.getDataConsulta());
            System.out.println("\tNome do paciente: " + (consulta.getPaciente()).getNome());
            System.out.println("\tSintomas do paciente: " + (consulta.getPaciente()).getSintomas());
            System.out.println("\tCidade da consulta: " + (consulta.getPaciente()).getCidade());
            if (!consulta.getStatusConsulta()){
                System.out.println("\tStatus da consulta: Em aberto");
            } else {
                System.out.println("\tStatus da consulta: Realizada");
                if(consulta.getPaciente().getPacienteInfectado()) {
                    System.out.println("\tStatus do paciente: Infectado");
                } else {
                    System.out.println("\tStatus do paciente: Não infectado");
                }
            }
            System.out.print("\n");
            count++;
        }

        if (count >= 1) {
            System.out.print("Deseja marcar uma consulta como concluida? (S/N): ");
            String opc = sc.nextLine();
            boolean found = false;
            if (opc.equalsIgnoreCase("s")) {
                System.out.print("Digite o nome do paciente: ");
                String nomePaciente = sc.nextLine();
                for (Consulta consulta: consultas) {
                    if (nomePaciente.equalsIgnoreCase((consulta.getPaciente()).getNome()) && !consulta.getStatusConsulta()) {
                        System.out.print("O paciente estava infectado? (S/N): ");
                        String opc2 = sc.nextLine();
                        if(opc2.equalsIgnoreCase("s")) {
                            consulta.marcarConcluida(true);
                        } else {
                            consulta.marcarConcluida(false);
                        }
                        persistConsultasGeral();
                        persistPacientesGeral();
                        found = true;
                    }
                }
                if(!found){
                    System.out.println("Paciente não existente/Consulta já realizada");
                }
            }
        }
    }

    public static void consultaPaciente(Paciente paciente) {
        int count = 0;
        for (Consulta consulta : consultas) {
            if (((consulta.getPaciente()).getCpf()).equals(paciente.getCpf())) {
                System.out.println("Consulta agendada para " + consulta.getDataConsulta());
                System.out.println("\tEstado: " + (consulta.getPaciente()).getEstado());
                System.out.println("\tCidade: " + (consulta.getPaciente()).getCidade());
                count++;
            }
        }
        if (count == 0){
            System.out.println("\nVocê não possui consultas marcadas");
        }
    }

    public static void showMessages(Conta voce, Conta outro) {
        System.out.println("\n\nChat iniciado\nDigite \"sair\" para finalizar o chat\n");
        for (Chat chat : chats) {
            if ((chat.getReceiver()).getCpf().equals(voce.getCpf()) && (chat.getSender()).getCpf().equals(outro.getCpf())) {
                // Mensagem "Outro"
                System.out.println(chat.getData() + " | " + getPrimeiraPalavra((chat.getSender()).getNome()) + ": " + chat.getMsg());
            }

            if ((chat.getSender()).getCpf().equals(voce.getCpf()) && (chat.getReceiver()).getCpf().equals(outro.getCpf())) {
                // Mensagem "Você"
                System.out.println(chat.getData() + " | " + "Voce: " + chat.getMsg());
            }
        }
    }

    public static String getPrimeiraPalavra(String s) {
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (" ".equals(Character.toString(c))) {
                return s.substring(0, i+1);
            }
        }
        return s;
    }

    public static void sendMessage(Conta sender, Conta receiver) {
        boolean p = false;
        if (sender.getId() == 0) {
            for (Chat chat : chats) {
                if ((chat.getReceiver()).getCpf().equals(sender.getCpf())) {
                    p = true;
                    break;
                }
            }
        } else { p = true; }

        if (!p) {
            System.out.println("Você não possui mensagens");
        }

        if (p) {
            // Liberado para mandar mensagem
            String mensagem = "";
            while (!mensagem.equalsIgnoreCase("sair")){
                mensagem = sc.nextLine();
                if (!mensagem.equalsIgnoreCase("sair")) {
                    Chat c = new Chat(sender, receiver, mensagem);
                    chats.add(c);
                    //salvar mensagem no txt
                    persistNewChat(c);
                }
            }
        }
    }

    public static void persistNewPaciente(Paciente paciente) {
        String data = dir + "\\src\\pacientes.data";

        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(data, true));
            output.newLine();output.append(paciente.getTelefone());
            output.newLine();output.append(paciente.getCidade());
            output.newLine();output.append(paciente.getEstado());
            output.newLine();output.append(paciente.getSintomas());
            output.newLine();output.append(paciente.getNome());
            output.newLine();output.append(paciente.getCpf());
            output.newLine();output.append(paciente.getSenha());
            output.newLine();output.append(Boolean.toString(paciente.getPacienteInfectado()));
            output.close();

        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
    }

    public static void persistNewFuncionario(Equipe funcionarios) {

    }

    public static void persistNewConsulta(Consulta consulta) {
        String data = dir + "\\src\\consultas.data";

        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(data, true));
            output.newLine();output.append((consulta.getPaciente()).getTelefone());
            output.newLine();output.append((consulta.getPaciente()).getCidade());
            output.newLine();output.append((consulta.getPaciente()).getEstado());
            output.newLine();output.append((consulta.getPaciente()).getSintomas());
            output.newLine();output.append((consulta.getPaciente()).getNome());
            output.newLine();output.append((consulta.getPaciente()).getCpf());
            output.newLine();output.append((consulta.getPaciente()).getSenha());
            output.newLine();output.append(consulta.getDiaConsulta());
            output.newLine();output.append(consulta.getMesConsulta());
            output.newLine();output.append(consulta.getAnoConsulta());
            output.newLine();output.append(Boolean.toString(consulta.getStatusConsulta()));
            output.close();

        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
    }

    public static void persistConsultasGeral() {
        String data = dir + "\\src\\consultas.data";

        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(data));
            for (Consulta consulta: consultas) {
                output.append((consulta.getPaciente()).getTelefone());
                output.newLine();output.append((consulta.getPaciente()).getCidade());
                output.newLine();output.append((consulta.getPaciente()).getEstado());
                output.newLine();output.append((consulta.getPaciente()).getSintomas());
                output.newLine();output.append((consulta.getPaciente()).getNome());
                output.newLine();output.append((consulta.getPaciente()).getCpf());
                output.newLine();output.append((consulta.getPaciente()).getSenha());
                output.newLine();output.append(consulta.getDiaConsulta());
                output.newLine();output.append(consulta.getMesConsulta());
                output.newLine();output.append(consulta.getAnoConsulta());
                output.newLine();output.append(Boolean.toString(consulta.getStatusConsulta()));
                output.newLine();
            }
            output.close();

        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
    } // Para atualizar consultas realizadas

    public static void persistPacientesGeral() {
        String data = dir + "\\src\\pacientes.data";
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(data));
            for (Paciente paciente : pacientes) {
                output.append(paciente.getTelefone());
                output.newLine();output.append(paciente.getCidade());
                output.newLine();output.append(paciente.getEstado());
                output.newLine();output.append(paciente.getSintomas());
                output.newLine();output.append(paciente.getNome());
                output.newLine();output.append(paciente.getCpf());
                output.newLine();output.append(paciente.getSenha());
                output.newLine();output.append(Boolean.toString(paciente.getPacienteInfectado()));
                output.newLine();
            }
            output.close();

        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

    } // Para atualizar pacientes atendidos

    public static void persistNewChat(Chat chat) {
        String data = dir + "\\src\\chat.data";

        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(data, true));
            output.newLine();output.append((chat.getSender()).getNome());
            output.newLine();output.append((chat.getSender()).getCpf());
            output.newLine();output.append(Integer.toString(chat.getSender().getId()));
            output.newLine();output.append((chat.getReceiver()).getNome());
            output.newLine();output.append((chat.getReceiver()).getCpf());
            output.newLine();output.append(chat.getData());
            output.newLine();output.append(chat.getMsg());
            output.close();

        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
    }
}