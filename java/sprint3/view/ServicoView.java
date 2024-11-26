package sprint3.view;

import java.util.Scanner;

import sprint3.model.vo.Servico;

public class ServicoView implements IView {
	
	private Scanner scanner = new Scanner(System.in);
	
	public void mostrarMenuServico() {
		System.out.println("\n========== { MENU DE SERVIÇO } ==========\n");
		System.out.println("1.  Cadastrar Serviço");
		System.out.println("2.  Listar Serviços");
		System.out.println("3.  Atualizar Serviço");
		System.out.println("4.  Deletar Serviço");
		System.out.println("0.  Sair");
		System.out.println("\nEscolha uma opção: ");
	}
	
	public void mostrarMenuServicoAtualizacao() {
		System.out.println("\n========== { MENU DE ATUALIZAÇÃO DO SERVIÇO } ==========\n");
		System.out.println("1.  Atualizar Tipo");
		System.out.println("2.  Atualizar Descrição");
		System.out.println("3.  Atualizar Preço");
		System.out.println("4.  Atualizar Duração");
		System.out.println("0.  Sair");
		System.out.println("\nEscolha uma opção: ");
	}
	
	public void imprimirServico(Servico s) {
		exibirMensagem("\n====== { INFORMAÇÕES DO SERVIÇO DE ID " + s.getIdServico() + " } ======");
		exibirMensagem("ID: " + s.getIdServico());
		exibirMensagem("Tipo: " + s.getTipoServico());
		exibirMensagem("Descrição: " + s.getDescricaoServico());
		exibirMensagem("Preço: " + s.getPrecoServico());
		exibirMensagem("Duração (em minutos): " + s.getDuracaoServico());
	}

	
	 public String solicitaTipo() {
	        System.out.print("Digite o tipo do serviço: ");
	        return scanner.nextLine();
	    }

	    public String solicitaDescricaoServico() {
	        System.out.print("Digite a descrição do serviço: ");
	        return scanner.nextLine();
	    }

	    public String solicitaPrecoServico() {
	        System.out.print("Digite o preço do serviço: ");
	        return scanner.nextLine();
	    }

	    public String solicitaDuracao() {
	        System.out.print("Digite a duração do serviço (em minutos): ");
	        return scanner.nextLine();
	    }

	    public String solicitaIdServico() {
	        System.out.print("Digite o ID do serviço (Ex: S12345): ");
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
		
		 public String servicoAAtualizar() {
		    	System.out.println("Qual o ID do serviço que deseja atualizar? (Ex: S12345): ");
				return scanner.nextLine();
		    }
		    
		public String servicoARemover() {
		    	System.out.println("Qual o ID do serviço que deseja remover? (Ex: S12345. OBS: todos os agendamentos e diagnósticos relacionados serão removidos): ");
				return scanner.nextLine();
		    }
}
