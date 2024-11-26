package sprint3.view;

import java.util.Scanner;

import sprint3.model.vo.Diagnostico;

public class DiagnosticoView implements IView {
	Scanner scanner = new Scanner(System.in);
	
	public void mostrarMenuDiagnostico() {
		System.out.println("\n========== { MENU DE AUTO-DIAGNÓSTICO } ==========\n");
		System.out.println("1.  Cadastrar Auto-diagnóstico");
		System.out.println("2.  Listar Auto-diagnósticos");
		System.out.println("3.  Atualizar Status do Auto-diagnóstico");
		System.out.println("4.  Deletar Auto-diagnóstico");
		System.out.println("0.  Sair");
		System.out.println("\nEscolha uma opção: ");
	}
	
	public void imprimirDiagnostico(Diagnostico d) {
		exibirMensagem("\n====== { INFORMAÇÕES DO DIAGNÓSTICO DE ID " + d.getIdDiagnostico() + " } ======");
		System.out.println("ID Diagnóstico: " + d.getIdDiagnostico());
		System.out.println("Usuário: " + d.getVeiculo().getUsuario().getCpfUsuario());
		System.out.println("Veículo: " + d.getVeiculo().getPlaca());
		System.out.println("Sintomas: " + d.getDescricaoSintomas());
		System.out.println("Solução: " + d.getSolucao());
		System.out.println("ID Serviço: " + d.getServico().getIdServico());
		System.out.println("Categoria: " + d.getCategoria());
		System.out.println("Status: " + d.getStatus());
		System.out.println("ID Orçamento: " + (d.getOrcamento() == null || d.getOrcamento().getIdOrcamento() == null ? "Não realizado" : d.getOrcamento().getIdOrcamento()));

	}
	
	public String solicitaIdDiagnostico() {
        System.out.print("Digite o ID do diagnóstico (Informado na criação do diagnóstico): ");
        return scanner.nextLine();
    }

    public String solicitaDescricaoSintomas() {
        System.out.print("Digite a descrição dos sintomas: ");
        return scanner.nextLine();
    }

    public String solicitaCategoria() {
        System.out.print("Digite a categoria do problema: ");
        return scanner.nextLine();
    }

    public String solicitaStatusDiagnostico() {
        System.out.print("Digite o status do diagnóstico ('Em Análise' ou 'Analisado'): ");
        return scanner.nextLine();
    }
    
    public String solicitaSolucao() {
        System.out.print("Digite a solução encontrada pelo diagnóstico: ");
        return scanner.nextLine();
    }
    
    public String solicitaPlaca() {
        System.out.print("Digite a placa do veículo analisado no auto-diagnóstico: (Ex: ABC1D23): ");
        return scanner.nextLine();
    }
    
    public String solicitaServico() {
        System.out.print("Digite o ID do serviço indicado pelo diagnóstico: (Ex: S12345): ");
        return scanner.nextLine();
    }
    
    public String diagnosticoAAtualizar() {
    	System.out.println("Qual o ID do diagnóstico que deseja atualizar o status? (Informado na criação do diagnóstico): ");
		return scanner.nextLine();
    }
    
    public String diagnosticoARemover() {
    	System.out.println("Qual o ID do diagnóstico que deseja remover? (Informado na criação do diagnóstico): ");
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
