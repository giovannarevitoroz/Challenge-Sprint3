package sprint3.view;

import java.util.Scanner;

import sprint3.model.vo.Orcamento;

public class OrcamentoView implements IView {
	
	Scanner scanner = new Scanner(System.in);
	
	public void mostrarMenuOrcamento() {
		System.out.println("\n========== { MENU DE AUTO-ORÇAMENTO } ==========\n");
		System.out.println("1.  Criar Auto-orçamento a partir de Diagnóstico");
		System.out.println("2.  Listar Auto-orçamentos");
		System.out.println("3.  Atualizar status Auto-orçamento");
		System.out.println("4.  Deletar Auto-orçamento");
		System.out.println("0.  Sair");
		System.out.println("\nEscolha uma opção: ");
	}
	
	public void imprimirOrcamento(Orcamento o) {
		exibirMensagem("\n====== { INFORMAÇÕES DO ORÇAMENTO DE ID " + o.getIdOrcamento() + " } ======");
		System.out.println("ID Orçamento: " + o.getIdOrcamento());
		System.out.println("Descrição: " + o.getDescricaoOrcamento());
		System.out.println("Valor Total: " + o.getValorTotal());
		System.out.println("Status: " + o.getStatusOrcamento());
	}
		
		public String solicitaDiagnostico() {
			System.out.print("Digite o ID do diagnóstico que deseja obter o orçamento (Fornecido na criação do diagnóstico): ");
	        return scanner.nextLine();
		}

	    public String solicitaDescricaoOrcamento() {
	        System.out.print("Digite a descrição do orçamento: ");
	        return scanner.nextLine();
	    }

	    public String solicitaStatusOrcamento() {
	        System.out.print("Digite o Status do orçamento ('Pendente', 'Aprovado' ou 'Rejeitado'): ");
	        return scanner.nextLine();
	    }

	    public String solicitaValorTotal() {
	        System.out.print("Digite o valor total do orçamento: ");
	        return scanner.nextLine();
	    }

	    public String solicitaIdOrcamento() {
	        System.out.print("Digite o ID do orçamento (Fornecido na criação do orçamento): ");
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
		
		 public String orcamentoAAtualizar() {
		    	System.out.println("Qual o ID do orçamento que deseja atualizar o status? (Fornecido na criação do orçamento): ");
				return scanner.nextLine();
		    }
		    
		public String orcamentoARemover() {
		    	System.out.println("Qual o ID do orçamento que deseja remover? (Fornecido na criação do orçamento): ");
				return scanner.nextLine();
		    }
	
}
