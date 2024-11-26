package sprint3.view;

import java.sql.SQLException;
import java.util.Scanner;

import sprint3.gerenciador.GerenciadorAgendamento;
import sprint3.gerenciador.GerenciadorCargo;
import sprint3.gerenciador.GerenciadorCentro;
import sprint3.gerenciador.GerenciadorDiagnostico;
import sprint3.gerenciador.GerenciadorFornece;
import sprint3.gerenciador.GerenciadorFuncionario;
import sprint3.gerenciador.GerenciadorOferece;
import sprint3.gerenciador.GerenciadorOrcamento;
import sprint3.gerenciador.GerenciadorPeca;
import sprint3.gerenciador.GerenciadorServico;
import sprint3.gerenciador.GerenciadorUsuario;
import sprint3.gerenciador.GerenciadorVeiculo;

public class MenuPrincipalView implements IView {
	private Scanner input = new Scanner(System.in);
	private GerenciadorUsuario gUsuario;
	private GerenciadorVeiculo gVeiculo;
	private GerenciadorPeca gPeca;
	private GerenciadorServico gServico;
	private GerenciadorCargo gCargo;
	private GerenciadorCentro gCentro;
	private GerenciadorAgendamento gAgendamento;
	private GerenciadorFuncionario gFuncionario;
	private GerenciadorDiagnostico gDiagnostico;
	private GerenciadorOrcamento gOrcamento;
	private GerenciadorFornece gFornece;
	private GerenciadorOferece gOferece;

	
	public MenuPrincipalView(GerenciadorUsuario gUsuario, GerenciadorVeiculo gVeiculo, 
			GerenciadorPeca gPeca, GerenciadorServico gServico,  GerenciadorCargo gCargo, GerenciadorCentro gCentro, 
			GerenciadorAgendamento gAgendamento, GerenciadorFuncionario gFuncionario,  GerenciadorDiagnostico gDiagnostico, GerenciadorOrcamento gOrcamento, GerenciadorFornece gFornece, GerenciadorOferece gOferece) {
		this.gUsuario = gUsuario;
		this.gVeiculo = gVeiculo;
		this.gPeca = gPeca;
		this.gServico = gServico;
		this.gCargo = gCargo;
		this.gCentro = gCentro;
		this.gAgendamento = gAgendamento;
		this.gFuncionario = gFuncionario;
		this.gDiagnostico = gDiagnostico;
		this.gOrcamento = gOrcamento;
		this.gFornece = gFornece;
		this.gOferece = gOferece;

	}
	
	public void mostrarMenuPrincipal() {
		exibirMensagem("========== { MENU DO SISTEMA } ==========\n");
		exibirMensagem("1.  Gerenciar Usuário");
		exibirMensagem("2.  Gerenciar Veículo");
		exibirMensagem("3.  Gerenciar Peça");
		exibirMensagem("4.  Gerenciar Cargo");
		exibirMensagem("5.  Gerenciar Centro Automotivo");
		exibirMensagem("6.  Gerenciar Serviço");
		exibirMensagem("7.  Gerenciar Funcionário");
		exibirMensagem("8.  Gerenciar Agendamento");
		exibirMensagem("9.  Gerenciar Auto-diagnóstico");
		exibirMensagem("10. Gerenciar Auto-orçamento");
		exibirMensagem("11. Gerenciar Associação entre peça e serviço");
		exibirMensagem("12. Gerenciar Associação entre centro e serviço");
		exibirMensagem("0.  Sair do Menu");
		exibirMensagem("\nEscolha uma opção: ");				
}
	
	public void start() {
		while (true) {
			mostrarMenuPrincipal();
			String optString = obterOpcao();
			try  {
				int opt = Integer.parseInt(optString);
				if (opt >= 0 && opt <= 12) {
					if (opt == 0) {
						exibirMensagem("\nSaindo do sistema...");
						break;
					}
					switch (opt) {
					case 1:
						gUsuario.gerenciarUsuario(); // ok
						break;
					case 2:
						gVeiculo.gerenciarVeiculo(); // ok
						break;
					case 3:
						gPeca.gerenciarPeca(); // ok
						break;
					case 4:
						gCargo.gerenciarCargo(); // ok
						break;
					case 5:
						gCentro.gerenciarCentro(); // ok
						break;
					case 6:
						gServico.gerenciarServico(); // ok
						break;
					case 7:
						gFuncionario.gerenciarFuncionario(); // ok
						break;
					case 8:
						gAgendamento.gerenciarAgendamento(); // ok
						break;
					case 9:
						gDiagnostico.gerenciarDiagnostico(); // ok
						break;
					case 10:
						gOrcamento.gerenciarOrcamento(); // ok
						break;
					case 11:
						gFornece.gerenciarFornece(); // ok
						break;
					case 12:
						gOferece.gerenciarOferece(); // ok
						break;
					} 	
				} else {
				exibirMensagem("Escolha uma opção entre 0 e 12.");
				continue;
			}
		} catch (NumberFormatException e) {
			exibirMensagem("Escolha uma opção válida.");
			continue;
		} catch (SQLException e) {
            exibirMensagem("Erro:");
            e.printStackTrace();
        }
	}
}
	

	@Override
	public void exibirMensagem(String mensagem) {
		System.out.println(mensagem);
		
	}

	@Override
	public String obterOpcao() {
		return input.nextLine();
	}
}
