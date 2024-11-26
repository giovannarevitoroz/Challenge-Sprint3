package sprint3.view;

import java.util.Scanner;

import sprint3.model.vo.CentroAutomotivo;

public class CentroView implements IView{
	private Scanner scanner = new Scanner(System.in);
	
	public void mostrarMenuCentro() {
		System.out.println("\n========== { MENU DE CENTRO AUTOMOTIVO } ==========\n");
		System.out.println("1.  Cadastrar Centro Automotivo");
		System.out.println("2.  Listar Centro Automotivos");
		System.out.println("3.  Atualizar Centro Automotivo");
		System.out.println("4.  Deletar Centro Automotivo");
		System.out.println("0.  Sair");
		System.out.println("\nEscolha uma opção: ");
	}
	
	public void mostrarMenuCentroAtualizacao() {
		System.out.println("\n========== { MENU DE ATUALIZAÇÃO DO CENTRO } ==========\n");
		System.out.println("1.  Atualizar Nome do centro");
		System.out.println("2.  Atualizar Endereço do centro");
		System.out.println("3.  Atualizar Telefone do centro");
		System.out.println("4.  Atualizar Horário de funcionamento");
		System.out.println("0.  Sair");
		System.out.println("\nEscolha uma opção: ");
	}
	
	public void imprimirCentro(CentroAutomotivo ca) {
		exibirMensagem("\n====== { INFORMAÇÕES DO CENTRO AUTOMOTIVO DE ID " + ca.getIdCentro() + " } ======");
		exibirMensagem("ID: " + ca.getIdCentro());
		exibirMensagem("Nome do centro: " + ca.getNomeCentro());
		exibirMensagem("Endereço: " + ca.getEnderecoCentro());
		exibirMensagem("Telefone: " + ca.getTelefoneCentro());
		exibirMensagem("Horário de Funcionamento: " + ca.getHorarioFuncionamento());
	}

	public String solicitaIdCentro() {
        System.out.print("Digite o ID do centro (Ex: C123): ");
        return scanner.nextLine();
    }

    public String solicitaNomeCentro() {
        System.out.print("Digite o Nome do centro: ");
        return scanner.nextLine();
    }

    public String solicitaEndereco() {
        System.out.print("Digite o Endereço do centro: ");
        return scanner.nextLine();
    }

    public String solicitaTelefone() {
        System.out.print("Digite o Telefone do centro (Ex: 11928932893): ");
        return scanner.nextLine();
    }
    
    public String solicitaHorario() {
        System.out.print("Digite o horário de funcionamento (Ex: 07:30 - 18:30): ");
        return scanner.nextLine();
    }
    
    public String centroAAtualizar() {
    	System.out.println("Qual o ID do centro que deseja atualizar? (Ex: C123): ");
		return scanner.nextLine();
    }
    
    public String centroARemover() {
    	System.out.println("Qual o ID do centro que deseja remover? (Ex: C123): ");
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
