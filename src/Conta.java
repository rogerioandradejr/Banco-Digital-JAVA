import java.util.Scanner;

public abstract class Conta implements IConta{

    private static final int agencia_padrao = 1;
    private static int SEQUENCIAL = 1;

    protected int agencia;
    protected int numero;
    protected double saldo;
    private Cliente cliente;

    public Conta(Cliente cliente) {
        this.agencia = Conta.agencia_padrao;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
    }

    @Override
    public void sacar(Double valor) {
       if(valor > saldo){
           System.out.println("Saldo Insuficiente, saldo atual: R$"+saldo);
       }
       else
        saldo -= valor;
    }

    @Override
    public void depositar(Double valor) {
        saldo += valor;
    }

    @Override
    public void transferir(Double valor, Conta contaDestino, double saldo) {
       if(valor > saldo){
           System.out.println("Opera��o n�o realizada.");
           this.saldo = saldo;
       }
       else{
            sacar(valor);
            contaDestino.depositar(valor);
           }
    }

    //Getter
    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    protected void imprimirInfoComuns() {
        System.out.println(String.format("Titular: %s", this.cliente.getNome()));
        System.out.println(String.format("Agencia: %d", this.agencia));
        System.out.println(String.format("Numero: %d", this.numero));
        System.out.println(String.format("Saldo: %.2f", this.saldo));
    }

    /**
     * M�todo criado para iniciar a escolha da opera��o que ser� realizada
     * @param sc
     * @return
     */
    protected static int getOp(Scanner sc) {
        System.out.println("======================================");
        System.out.println("Informe qual opera��o deseja realizar:\n1- Deposito\n2- Sacar\n3- Transferir");
        System.out.println("======================================\nDigite aqui:");
        int op = sc.nextInt();
            if (op == 1 || op == 2 || op == 3) {
                return op;
            } else {
                System.out.println("Op��o incorreta, op��o selecionada foi " + op);
                return getOp(sc);
            }
    }

    /**
     * Metodo Criado para identificar a op��o da Conta (Corrente ou Poupan�a)
     * @param sc
     * @return
     */
    protected static int getTipoConta(Scanner sc) {
        System.out.println("======================================");
        System.out.println("Informe o tipo da conta:\n1- Conta Corrente\n2- Conta Poupan�a");
        System.out.println("======================================\nDigite aqui:");
        int tipoConta = sc.nextInt();
        if (tipoConta == 1 || tipoConta == 2 || tipoConta == 3) {
            return tipoConta;
        }
        else{
            System.out.println("Op��o incorreta, op��o selecionada foi " +tipoConta);
            return getTipoConta(sc);
        }
    }

    /**
     * M�todo criado para a realiza��o do Switch dentro da opera��o
     * @param sc
     * @param cc
     * @param cp
     * @param op
     * @param tipoConta
     */
    protected static void OpEscolhida(Scanner sc, Conta cc, Conta cp, int op, int tipoConta) {
        switch (op){
            case 1:
                if (tipoConta == 1){
                    System.out.println("Informe o valor que ser� depositado na Conta Corrente:");
                    cc.depositar(sc.nextDouble());
                    break;
                }
                else
                    System.out.println("Informe o valor que ser� depositado na Conta Poupan�a:");
                cp.depositar(sc.nextDouble());
                break;

            case 2:
                if (tipoConta == 1){
                    System.out.println("Informe o valor que ser� sacado na Conta Corrente:");
                    cc.sacar(sc.nextDouble());
                    break;
                }
                else
                    System.out.println("Informe o valor que ser� sacado na Conta Poupan�a:");
                cp.sacar(sc.nextDouble());
                break;

            case 3:
                if (tipoConta == 1){
                    System.out.println("Informe o valor que ser� transferido da Conta Corrente para Conta Poupan�a:");
                    cc.transferir(sc.nextDouble(), cp);
                    break;
                }
                else
                    System.out.println("Informe o valor que ser� transferido da Conta Poupan�a para Conta Corrente:");
                cp.transferir(sc.nextDouble(), cc);
                break;
            default:
                System.out.println("Valor inv�lido");

        }
    }

}