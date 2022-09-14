import java.util.Scanner;

import java.util.*;

public class Main {


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random random = new Random();

        Hashtable<String, PessoaFisica> hashtablePessoa = new Hashtable<String, PessoaFisica>();
        Hashtable<Integer, ContaC> hashtableContaCorrente = new Hashtable<Integer, ContaC>();
        Hashtable<Integer, ContaP> hashtableContaPoupanca = new Hashtable<Integer, ContaP>();
        ValidacaoDados validarDados = new ValidacaoDados();

        ArrayList<Hashtable> arrayContas = new ArrayList<Hashtable>();
        arrayContas.add(hashtableContaCorrente);
        arrayContas.add(hashtableContaPoupanca);

        int opcao = 1;

        while (opcao != 0) {
            PessoaFisica pessoa = new PessoaFisica();
            ContaC contaCorrente = new ContaC();
            ContaP contaPoupanca = new ContaP();

            String cpf;
            int agencia;
            int numeroConta;
            Double valor;
            String senha;

            System.out.println("1 - Cadastrar Pessoa Física.");
            System.out.println("2 - Cadastrar Conta Corrente.");
            System.out.println("3 - Cadastrar Conta Poupança.");
            System.out.println("4 - Efetuar Deposito.");
            System.out.println("5 - Efetuar Saque.");
            System.out.println("6 - Efetuar Transferência.");
            System.out.println("0 - Sair do sistema.");
            opcao = scan.nextInt();

            switch (opcao) {
                case 0:
                    System.out.println("Saindo...");
                    break;
                case 1:
                    System.out.println("");
                    System.out.println("- Opção selecionada: Cadastrar Pessoa Física -");

                    System.out.println("Informe o nome da pessoa: ");
                    pessoa.setNome(scan.next());

                    System.out.println("Informe o CPF da pessoa: ");
                    pessoa.setCPF(scan.next());

                    hashtablePessoa.put(pessoa.getCPF(), pessoa);

                    System.out.println("");
                    System.out.println("Pessoa cadastrada com sucesso.");
                    break;
                case 2:
                    System.out.println("");
                    System.out.println("- Opção selecionada: Cadastrar Conta Corrente -");

                    System.out.println("Informe o CPF da Pessoa: ");
                    cpf = scan.next();

                    if (hashtablePessoa.containsKey(cpf)) {
                        try {
                            for (Map.Entry<Integer, ContaC> conta : hashtableContaCorrente.entrySet()) {
                                if (conta.getValue().getPessoa().getCPF().equals(cpf)) {
                                    throw new RuntimeException("Esta pessoa já está vinculado a uma conta corrente!");
                                }
                            }

                            contaCorrente.setPessoa(hashtablePessoa.get(cpf));
                            contaCorrente.setNumeroConta(random.nextInt(1000));
                            contaCorrente.setCodigoAgencia(123);

                            System.out.println("Informe a senha para abertura da conta: ");
                            contaCorrente.setSenha(scan.next());

                            hashtableContaCorrente.put(contaCorrente.getNumeroConta(), contaCorrente);
                            System.out.println("Conta cadastrada com sucesso. Número da conta: " + "\n" + contaCorrente.getNumeroConta());
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("Esse CPF não existe!");
                    }

                    break;
                case 3:
                    System.out.println("");
                    System.out.println("- Opção selecionada: Cadastrar Conta Poupança -");

                    System.out.println("Informe o CPF da Pessoa: ");
                    cpf = scan.next();

                    if (hashtablePessoa.containsKey(cpf)) {
                        try {
                            for (Map.Entry<Integer, ContaP> conta : hashtableContaPoupanca.entrySet()) {
                                if (conta.getValue().getPessoa().getCPF() == cpf) {
                                    throw new RuntimeException("Esta pessoa já está vinculado a uma conta poupança!");
                                }
                            }

                            contaPoupanca.setPessoa(hashtablePessoa.get(cpf));
                            contaPoupanca.setNumeroConta(random.nextInt(1000));
                            contaPoupanca.setCodigoAgencia(123);

                            System.out.println("Informe a senha para abertura da conta: ");
                            contaPoupanca.setSenha(scan.next());

                            hashtableContaPoupanca.put(contaPoupanca.getNumeroConta(), contaPoupanca);
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("Esse CPF não existe!");
                    }

                    break;
                case 4:
                    try {
                        System.out.println("Informe seu CPF: ");
                        cpf = scan.next();

                        for (Map.Entry<String, PessoaFisica> pessoas : hashtablePessoa.entrySet()) {
                            validarDados.validarCPF(pessoas.getKey(), cpf);
                        }

                        System.out.println("Informe a agência de origem: ");
                        agencia = scan.nextInt();

                        while (agencia != 123) {
                            System.out.println("Agência inválida!");

                            System.out.println("Informe novamente a agência de origem: ");
                            agencia = scan.nextInt();
                        }

                        System.out.println("Informe o número da sua conta: ");
                        numeroConta = scan.nextInt();

                        validarDados.validarNumeroConta(hashtableContaCorrente, numeroConta);
                        for (Map.Entry<Integer, ContaC> conta : hashtableContaCorrente.entrySet()) {
                            validarDados.validarCPF(conta.getValue().getPessoa().getCPF(), cpf);
                        }

                        System.out.println("Informe o valor de deposito: ");
                        valor = scan.nextDouble();

                        System.out.println("Informe a senha da conta: ");
                        senha = scan.next();

                        validarDados.validarSenha(hashtableContaCorrente.get(numeroConta).getSenha(), senha);

                        System.out.println("Valor de saque: " + valor);
                        System.out.println("Confirmar valor?");
                        System.out.println("(1) - SIM.");
                        System.out.println("(0) - NÃO.");
                        int confirmacao = scan.nextInt();

                        switch (confirmacao) {
                            case 1:
                                hashtableContaCorrente.get(numeroConta).deposito(valor);
                                System.out.println("Depósito realizado com sucesso." + contaCorrente.saldo);
                                break;
                            case 0:
                                System.out.println("Operação cancelada!");
                                break;
                        }
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    try {
                        System.out.println("Informe seu CPF: ");
                        cpf = scan.next();

                        for (Map.Entry<String, PessoaFisica> pessoas : hashtablePessoa.entrySet()) {
                            validarDados.validarCPF(pessoas.getKey(), cpf);
                        }

                        System.out.println("Agência de origem: ");
                        agencia = scan.nextInt();

                        while (agencia != 123) {
                            System.out.println("Agência inválida!");

                            System.out.println("Informe novamente a agência de origem: ");
                            agencia = scan.nextInt();
                        }

                        System.out.println("Número da sua conta: ");
                        numeroConta = scan.nextInt();

                        validarDados.validarNumeroConta(hashtableContaCorrente, numeroConta);
                        for (Map.Entry<Integer, ContaC> conta : hashtableContaCorrente.entrySet()) {
                            validarDados.validarCPF(conta.getValue().getPessoa().getCPF(), cpf);
                        }

                        System.out.println("Valor de saque: ");
                        valor = scan.nextDouble();

                        System.out.println("Senha da conta: ");
                        senha = scan.next();

                        validarDados.validarSenha(hashtableContaCorrente.get(numeroConta).getSenha(), senha);

                        System.out.println("Valor de saque: " + valor);
                        System.out.println("Confirmar valor?");
                        System.out.println("1 - SIM.");
                        System.out.println("0 - NÃO.");
                        int confirmacao = scan.nextInt();

                        switch (confirmacao) {
                            case 1:
                                hashtableContaCorrente.get(numeroConta).saque(valor);
                                System.out.println("Saque realizado com sucesso.");
                                break;
                            case 0:
                                System.out.println("Operação cancelada!");
                                break;
                        }
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 6:
                    break;
            }
        }
    }
}