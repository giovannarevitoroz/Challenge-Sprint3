package sprint3.view;

import java.util.Scanner;

import sprint3.model.vo.Agendamento;

public class AgendamentoView implements IView {
	
	private Scanner scanner = new Scanner(System.in);

	public void mostrarMenuAgendamento() {
		exibirMensagem("\n========== { MENU DE AGENDAMENTO } ==========\n");
		exibirMensagem("1.  Cadastrar Agendamento");
		exibirMensagem("2.  Listar Agendamentos");
		exibirMensagem("3.  Atualizar Agendamento");
		exibirMensagem("4.  Deletar Agendamento");
		exibirMensagem("0.  Sair");
		exibirMensagem("\nEscolha uma opção: ");
	}
	
	public void mostrarMenuAgendamentoAtualizacao() {
		exibirMensagem("\n========== { MENU DE ATUALIZAÇÃO DO AGENDAMENTO } ==========\n");
		exibirMensagem("1.  Atualizar Data do agendamento");
		exibirMensagem("2.  Atualizar Horário do agendamento");
		exibirMensagem("3.  Atualizar Descrição do agendamento");
		exibirMensagem("4.  Atualizar Serviço do agendamento");
		exibirMensagem("5.  Atualizar Centro Automotivo do agendamento");
		exibirMensagem("6.  Atualizar Veículo do agendamento");
		exibirMensagem("0.  Sair");
		exibirMensagem("\nEscolha uma opção: ");
	}
	
	public void imprimirAgendamento(Agendamento a) {
			exibirMensagem("\n====== { INFORMAÇÕES DO AGENDAMENTO DE ID " + a.getIdAgendamento() + " } ======");
			exibirMensagem("ID Agendamento....: " + a.getIdAgendamento());
			exibirMensagem("Serviço...........: " + a.getServico().getDescricaoServico());
			exibirMensagem("Oficina...........: " + a.getCentro().getNomeCentro());
			exibirMensagem("Data..............: " + a.getData());
			exibirMensagem("Horário...........: " + a.getHora());
			exibirMensagem("Usuário CPF.......: " + a.getVeiculo().getUsuario().getCpfUsuario());
			exibirMensagem("Veículo...........: " + a.getVeiculo().getPlaca());
	}
	
	
	public String solicitaIdAgendamento() {
        System.out.print("Digite o ID do agendamento (Fornecido na criação do agendamento): ");
        return scanner.nextLine();
    }

    public String solicitaDataAgendamento() {
        System.out.print("Digite a Data do agendamento (Ex: DD-MON-YYYY. OBS.: Mês no formato americano): ");
        return scanner.nextLine();
    }

    public String solicitaHorarioAgendamento() {
        System.out.print("Digite o Horário do agendamento (Ex: 13:00): ");
        return scanner.nextLine();
    }

    public String solicitaDescricaoAgendamento() {
        System.out.print("Digite a Descrição do agendamento: ");
        return scanner.nextLine();
    }
    
    public String solicitaServico() {
        System.out.print("Digite o ID do serviço que será feito no agendamento (Ex: S12345): ");
        return scanner.nextLine();
    }
    
    public String solicitaCentro() {
        System.out.print("Digite o ID do centro automotivo que será feito o agendamento (Ex: C123): ");
        return scanner.nextLine();
    }
    
    public String solicitaPlaca() {
        System.out.print("Digite a placa do veículo do agendamento (Ex: ABC1D23): ");
        return scanner.nextLine();
    }
    
    public String agendamentoAAtualizar() {
    	exibirMensagem("Qual o ID do agendamento que deseja atualizar? (Fornecido na criação do agendamento): ");
		return scanner.nextLine();
    }
    
    public String agendamentoARemover() {
    	exibirMensagem("Qual o ID do agendamento que deseja remover? (Fornecido na criação do agendamento): ");
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
