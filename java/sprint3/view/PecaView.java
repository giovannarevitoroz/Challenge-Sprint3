package sprint3.view;

import java.util.Scanner;

import sprint3.model.vo.Peca;

public class PecaView implements IView {
	
	private Scanner scanner = new Scanner(System.in);
	
	public void mostrarMenuPeca() {
		System.out.println("\n========== { MENU DE PEÇA } ==========\n");
		System.out.println("1.  Cadastrar Peça");
		System.out.println("2.  Listar Peças");
		System.out.println("3.  Atualizar Peça");
		System.out.println("4.  Deletar Peça");
		System.out.println("0.  Sair");
		System.out.println("\nEscolha uma opção: ");
	}
	
	public void mostrarMenuPecaAtualizacao() {
			System.out.println("\n========== { MENU DE ATUALIZAÇÃO DA PEÇA } ==========\n");
			System.out.println("1.  Atualizar Disponibilidade da Peça");
			System.out.println("2.  Atualizar Nome da Peça");
			System.out.println("3.  Atualizar Preço da Peça");
			System.out.println("0.  Sair");
			System.out.println("\nEscolha uma opção: ");
		}
	
	public void imprimirPeca(Peca p) {
		exibirMensagem("\n====== { INFORMAÇÕES DA PEÇA DE ID " + p.getIdPeca() + " } ======");
		exibirMensagem("ID: " + p.getIdPeca());
		exibirMensagem("Nome: " + p.getNomePeca());
		exibirMensagem("Disponibilidade: " + p.getDisponibilidadePeca());
		exibirMensagem("Preço: R$" + p.getPrecoPeca());
	}
	
	 public String solicitaIdPeca() {
	        System.out.print("Digite o ID da peça (Ex: P12345): ");
	        return scanner.nextLine();
	    }

	    public String solicitaDisponibilidade() {
	        System.out.print("Digite a quantidade disponível: ");
	        return scanner.nextLine();
	    }

	    public String solicitaNomePeca() {
	        System.out.print("Digite o nome da peça: ");
	        return scanner.nextLine();
	    }

	    public String solicitaPreco() {
	        System.out.print("Digite o preço da peça: ");
	        return scanner.nextLine();
	    }
	    
	    public String pecaAAtualizar() {
	    	System.out.println("Qual o ID da peça que deseja atualizar? (Ex: P12345): ");
			return scanner.nextLine();
	    }
	    
	public String pecaARemover() {
	    	System.out.println("Qual o ID da peça que deseja remover? (Ex: P12345): ");
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

}
