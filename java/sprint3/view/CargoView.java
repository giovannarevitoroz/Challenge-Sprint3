package sprint3.view;

import java.util.Scanner;

import sprint3.model.vo.Cargo;

public class CargoView implements IView {
	private Scanner scanner = new Scanner(System.in);
	
	public void mostrarMenuCargo() {
		System.out.println("\n========== { MENU DE CARGO } ==========\n");
		System.out.println("1.  Cadastrar Cargo");
		System.out.println("2.  Listar Cargos");
		System.out.println("3.  Atualizar Cargo");
		System.out.println("4.  Deletar Cargo");
		System.out.println("0.  Sair");
		System.out.println("\nEscolha uma opção: ");
	}
	
	public void mostrarMenuCargoAtualizacao() {
		System.out.println("\n========== { MENU DE ATUALIZAÇÃO DO CARGO } ==========\n");
		System.out.println("1.  Atualizar Nome do cargo");
		System.out.println("2.  Atualizar Área do cargo");
		System.out.println("3.  Atualizar Descrição do cargo");
		System.out.println("4.  Atualizar Salário do cargo");
		System.out.println("0.  Sair");
		System.out.println("\nEscolha uma opção: ");
	}
	
	public void imprimirCargo(Cargo c) {
		exibirMensagem("\n====== { INFORMAÇÕES DO CARGO DE ID " + c.getIdCargo() + " } ======");
		exibirMensagem("ID: " + c.getIdCargo());
		exibirMensagem("Nome do cargo: " + c.getNomeCargo());
		exibirMensagem("Área do cargo: " + c.getAreaCargo());
		exibirMensagem("Descrição: " + c.getDescricaoCargo());
		exibirMensagem("Salário: R$" + c.getSalarioCargo());
	}
	
	 public String solicitaNomeCargo() {
	        System.out.print("Digite o nome do cargo: ");
	        return scanner.nextLine();
	    }

	    public String solicitaDescricaoCargo() {
	        System.out.print("Digite a descrição do cargo: ");
	        return scanner.nextLine();
	    }

	    public String solicitaAreaCargo() {
	        System.out.print("Digite a área do cargo: ");
	        return scanner.nextLine();
	    }

	    public String solicitaSalarioCargo() {
	        System.out.print("Digite o salário do cargo: ");
	        return scanner.nextLine();
	    }

	    public String solicitaIdCargo() {
	        System.out.print("Digite o ID do cargo (Ex: CG12): ");
	        return scanner.nextLine();
	    }
	    
	    @Override
	    public void exibirMensagem(String mensagem) {
	        System.out.println(mensagem);
	    }

		@Override
		public String obterOpcao() {
			return scanner.nextLine();
		}
		
		 public String cargoAAtualizar() {
		    	System.out.println("Qual o ID do cargo que deseja atualizar? (Ex: CG12): ");
				return scanner.nextLine();
		    }
		    
		public String cargoARemover() {
		    	System.out.println("Qual o ID do cargo que deseja remover? (Ex: CG12): ");
				return scanner.nextLine();
		    }
}
