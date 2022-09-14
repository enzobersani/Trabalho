import java.util.Hashtable;

public class ValidacaoDados {
    public void validarCPF(String cpfConta, String cpf) {
        if(!cpfConta.equals(cpf)){
            throw new RuntimeException("CPF inválido!");
        }
    }

    public void validarNumeroConta(Hashtable conta, int numeroConta){
        if (!conta.containsKey(numeroConta)){
            throw new RuntimeException("Número de conta inválido!");
        }
    }

    public void validarSenha(String senhaConta, String senha){
        if(!senhaConta.equals(senha)){
            throw new RuntimeException("Senha incorreta!");
        }
    }
}
