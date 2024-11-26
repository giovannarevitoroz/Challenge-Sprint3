package sprint3.view;

import java.util.Scanner;

import sprint3.model.vo.Peca;
import sprint3.model.vo.Servico;

public class ForneceView implements IView{
	
	Scanner scanner = new Scanner(System.in);
	
	public void mostrarMenuFornece() {
		System.out.println("\n========== { MENU DE RELAÇÃO ENTRE PEÇA E SERVIÇO } ==========\n");
		System.out.println("1.  Cadastrar Relação entre peça e serviço");
		System.out.println("2.  Listar Serviços relacionados a uma peça");
		System.out.println("3.  Listar Peças relacionadas a um serviço");
		System.out.println("4.  Deletar Relação entre peça e serviço");
		System.out.println("0.  Sair");
		System.out.println("\nEscolha uma opção: ");
	}
	
	public void imprimirPecaFornece(Peca p) {
		exibirMensagem("\n====== { INFORMAÇÕES DA PEÇA DE ID " + p.getIdPeca() + " } ======");
		exibirMensagem("ID: " + p.getIdPeca());
		exibirMensagem("Nome: " + p.getNomePeca());
		exibirMensagem("Disponibilidade: " + p.getDisponibilidadePeca());
		exibirMensagem("Preço: " + p.getPrecoPeca());
	}
	
	public void imprimirServicoFornece(Servico s) {
		exibirMensagem("\n====== { INFORMAÇÕES DO SERVIÇO DE ID " + s.getIdServico() + " } ======");
		exibirMensagem("ID: " + s.getIdServico());
		exibirMensagem("Tipo: " + s.getTipoServico());
		exibirMensagem("Descrição: " + s.getDescricaoServico());
		exibirMensagem("Preço: " + s.getPrecoServico());
		exibirMensagem("Duração (em minutos): " + s.getDuracaoServico());
	}
	
	public String solicitaPecaFornece() {
		System.out.println("Qual o ID da peça na relação? (Ex: P12345): ");
		return scanner.nextLine();
	}
	
	public String solicitaServicoFornece() {
		System.out.println("Qual o ID do serviço na relação? (Ex: S12345): ");
		return scanner.nextLine();
	}
	
	public String pecaForneceARemover() {
    	System.out.println("Qual o ID da peça que deseja remover? (Ex: P12345): ");
		return scanner.nextLine();
    }
	
	public String servicoForneceARemover() {
    	System.out.println("Qual o ID do serviço que deseja remover? (Ex: S12345): ");
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
