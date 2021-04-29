package app.covid;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author João Vitor Oliveira de Melo
 */
public class AppCovid {
    // Lista das contas
    private static List<Paciente> pacientes = new ArrayList<Paciente>();
    private static List<Equipe> funcionarios = new ArrayList<Equipe>();
    
    // Scanner para leitura do teclado
    private static Scanner sc = new Scanner(System.in);
    private static Scanner scInt = new Scanner(System.in);
    
    public static void main(String[] args) {
        // Conta da equipe
        // CPF: 12345678910
        // Senha: admin              
        funcionarios.add(new Equipe("GrupoE", "12345678910", "admin"));
        
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
            int opc = 0;
            System.out.println("Bem vindo " + (pacientes.get(index)).getNome());
            System.out.println("Menu:");
            System.out.println("\t1) Dados cadastrados");
            System.out.println("\t2) Informações sobre o Covid-19");
            System.out.println("\t3) Consultas marcadas");
            System.out.println("\t0) Sair");
            opc = scInt.nextInt();
        }
        if (login == 2){
            // Login funcionário
            for (int i=0; i<funcionarios.size(); i++)
                if ((funcionarios.get(i).cpf).equals(cpf))
                    index = i;
            int opc = 0;
            System.out.println("Bem vindo " + (funcionarios.get(index)).getNome());
            System.out.println("Menu:");
            System.out.println("\t1) Listar Pacientes");
            System.out.println("\t2) Procurar Paciente");
            System.out.println("\t3) Consultas marcadas");
            System.out.println("\t0) Sair");
            opc = scInt.nextInt();
        }
    }
    
    
    public static void showInfos(){
        
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
    
}