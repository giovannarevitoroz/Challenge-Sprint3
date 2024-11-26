package sprint3.view;

import java.util.Scanner;

import sprint3.model.vo.Veiculo;

public class VeiculoView implements IView {
	
	private Scanner scanner = new Scanner(System.in);
	
	public void mostrarMenuVeiculo() {
		System.out.println("\n========== { MENU DO VEÍCULO } ==========\n");
		System.out.println("1.  Cadastrar Veículo");
		System.out.println("2.  Listar Veículos");
		System.out.println("3.  Atualizar Veículo");
		System.out.println("4.  Deletar Veículo");
		System.out.println("0.  Sair");
		System.out.println("\nEscolha uma opção: ");
	}
	
	public void mostrarMenuVeiculoAtualizacao() {
		System.out.println("\n========== { MENU DE ATUALIZAÇÃO DO VEÍCULO } ==========\n");
		System.out.println("1.  Atualizar Marca");
		System.out.println("2.  Atualizar Modelo");
		System.out.println("3.  Atualizar Ano");
		System.out.println("4.  Atualizar Quilometragem");
		System.out.println("5.  Atualizar CPF do Proprietário");
		System.out.println("0.  Sair");
		System.out.println("\nEscolha uma opção: ");
	}
	
	public void imprimirVeiculo(Veiculo v) {
		exibirMensagem("\n====== { INFORMAÇÕES DO VEÍCULO DE PLACA " + v.getPlaca() + " } ======");
		exibirMensagem("Placa: " + v.getPlaca());
		exibirMensagem("Marca: " + v.getMarca());
		exibirMensagem("Modelo: " + v.getModelo());
		exibirMensagem("Ano: " + v.getAno());
		exibirMensagem("Quilometragem: " + v.getQuilometragem() + "km");
		exibirMensagem("CPF do Proprietário: " + v.getUsuario().getCpfUsuario() + "\n");
	}
	
	 public String solicitaMarca() {
	        System.out.print("Digite a marca do veículo: ");
	        return scanner.nextLine();
	    }

	    public String solicitaModelo() {
	        System.out.print("Digite o modelo do veículo: ");
	        return scanner.nextLine();
	    }

	    public String solicitaPlaca() {
	        System.out.print("Digite a placa do veículo (ex: ABC1D23): ");
	        return scanner.nextLine();
	    }

	    public String solicitaAno() {
	        System.out.print("Digite o ano do veículo (entre 1980 e 2024): ");
	        return scanner.nextLine();
	    }

	    public String solicitaCpfProprietario() {
	        System.out.print("Digite o CPF do proprietário do veículo (Ex: 11122233344): ");
	        return scanner.nextLine();
	    }

	    public String solicitaQuilometragem() {
	        System.out.print("Digite a quilometragem do veículo: ");
	        return scanner.nextLine();
	    }

	    @Override
	    public void exibirMensagem(String mensagem) {
	        System.out.println(mensagem);
	    }
	    
	    public String veiculoAAtualizar() {
	    	System.out.println("Qual a placa do veículo que deseja atualizar? (Ex: ABC1D23): ");
			return scanner.nextLine();
	    }
	    
	    public String veiculoARemover() {
	    	System.out.println("Qual a placa do veículo que deseja remover? (Ex: ABC1D23): ");
			return scanner.nextLine();
	    }
	    
	    @Override
	    public String obterOpcao() {
	        return scanner.nextLine();
	    }

}
