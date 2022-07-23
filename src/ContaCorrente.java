public class ContaCorrente extends Conta {

    public ContaCorrente(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("=== Extrato conta Corrente ===");
        super.imprimirInfoComuns();

    }

	@Override
	public void transferir(Double valor, Conta contaDestino) {
		// TODO Auto-generated method stub
		
	}
}