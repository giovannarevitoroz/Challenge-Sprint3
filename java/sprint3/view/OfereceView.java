package sprint3.view;

import java.util.Scanner;

import sprint3.model.vo.CentroAutomotivo;
import sprint3.model.vo.Servico;

public class OfereceView implements IView {

	Scanner scanner = new Scanner(System.in);
	
	public void mostrarMenuOferece() {
		System.out.println("\n========== { MENU DE RELAÇÃO ENTRE CENTRO E SERVIÇO } ==========\n");
		System.out.println("1.  Cadastrar Relação entre centro e serviço");
		System.out.println("2.  Listar Serviços relacionados a um centro");
		System.out.println("3.  Listar Centros relacionados a um serviço");
		System.out.println("4.  Deletar Relação entre centro e serviço");
		System.out.println("0.  Sair");
		System.out.println("\nEscolha uma opção: ");
	}
	
	public void imprimirCentroOferece(CentroAutomotivo ca) {
		exibirMensagem("\n====== { INFORMAÇÕES DO CENTRO AUTOMOTIVO DE ID " + ca.getIdCentro() + " } ======");
		exibirMensagem("ID: " + ca.getIdCentro());
		exibirMensagem("Nome do centro: " + ca.getNomeCentro());
		exibirMensagem("Endereço: " + ca.getEnderecoCentro());
		exibirMensagem("Telefone: " + ca.getTelefoneCentro());
		exibirMensagem("Horário de Funcionamento: " + ca.getHorarioFuncionamento());
	}
	
	public void imprimirServicoOferece(Servico s) {
		exibirMensagem("\n====== { INFORMAÇÕES DO SERVIÇO DE ID " + s.getIdServico() + " } ======");
		exibirMensagem("ID: " + s.getIdServico());
		exibirMensagem("Tipo: " + s.getTipoServico());
		exibirMensagem("Descrição: " + s.getDescricaoServico());
		exibirMensagem("Preço: " + s.getPrecoServico());
		exibirMensagem("Duração (em minutos): " + s.getDuracaoServico());
	}
	
	public String solicitaCentroOferece() {
		System.out.println("Qual o ID do centro na relação? (Ex: C123): ");
		return scanner.nextLine();
	}
	
	public String solicitaServicoOferece() {
		System.out.println("Qual o ID do serviço na relação? (Ex: S12345): ");
		return scanner.nextLine();
	}
	
	public String centroOfereceARemover() {
    	System.out.println("Qual o ID do centro que deseja remover? (Ex: C123): ");
		return scanner.nextLine();
    }
	
	public String servicoOfereceARemover() {
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
