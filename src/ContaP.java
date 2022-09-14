public class ContaP extends Conta{

    @Override
    public PessoaFisica getPessoa() {
        return super.getPessoa();
    }

    @Override
    public void setPessoa(PessoaFisica pessoa) {
        super.setPessoa(pessoa);
    }

    @Override
    public int getCodigoAgencia() {
        return super.getCodigoAgencia();
    }

    @Override
    public void setCodigoAgencia(int codigoAgencia) {
        super.setCodigoAgencia(codigoAgencia);
    }

    @Override
    public int getNumeroConta() {
        return super.getNumeroConta();
    }

    @Override
    public void setNumeroConta(int numeroConta) {
        super.setNumeroConta(numeroConta);
    }

    @Override
    public String getSenha() {
        return super.getSenha();
    }

    @Override
    public void setSenha(String senha) {
        super.setSenha(senha);
    }

    public void deposito(Double valor) {
        if (valor < 0){
            throw new RuntimeException("Não é aceito valor negativo!");
        }

        this.saldo += valor;
    }

    public void saque(Double valor) {
        if(valor > this.saldo){
            throw new RuntimeException("Valor do saque superior ao seu saldo atual!");
        }

        this.saldo -= valor;
    }

    public void transferencia(Double valor) {
        if(valor > this.saldo){
            throw new RuntimeException("!Valor de transferência superior ao seu saldo atual!");
        }

        this.saldo -= valor;
    }

    @Override
    Double getSaldo() {
        return this.saldo;
    }
}
