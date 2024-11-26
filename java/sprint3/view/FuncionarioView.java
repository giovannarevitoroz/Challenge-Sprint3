package sprint3.view;

import java.util.Scanner;

import sprint3.model.vo.Funcionario;

public class FuncionarioView implements IView {
	
	Scanner scanner = new Scanner(System.in);
	
	public void mostrarMenuFuncionario() {
		System.out.println("\n========== { MENU DE FUNCIONÁRIO } ==========\n");
		System.out.println("1.  Cadastrar Funcionário");
		System.out.println("2.  Listar Funcionários");
		System.out.println("3.  Atualizar Funcionário");
		System.out.println("4.  Deletar Funcionário");
		System.out.println("0.  Sair");
		System.out.println("\nEscolha uma opção: ");
	}
	
	public void mostrarMenuFuncionarioAtualizacao() {
		System.out.println("\n========== { MENU DE ATUALIZAÇÃO DO FUNCIONÁRIO } ==========\n");
		System.out.println("1.  Atualizar Nome do funcionário");
		System.out.println("2.  Atualizar Horário de trabalho");
		System.out.println("3.  Atualizar Disponibilidade do funcionário");
		System.out.println("4.  Atualizar Centro do funcionário");
		System.out.println("5.  Atualizar Cargo do funcionário");
		System.out.println("0.  Sair");
		System.out.println("\nEscolha uma opção: ");
	}
	
	public void imprimirFuncionario(Funcionario f) {
			exibirMensagem("\n====== { INFORMAÇÕES DO FUNCIONÁRIO DE MATRÍCULA " + f.getMatriculaFuncionario() + " } ======");
			exibirMensagem("Matrícula: " + f.getMatriculaFuncionario());
			exibirMensagem("Nome: " + f.getNomeFuncionario());
		    exibirMensagem("Cargo: " + (f.getCargo() != null ? f.getCargo().getNomeCargo() : "Sem cargo (inativo)"));
		    exibirMensagem("Área do Cargo: " + (f.getCargo() != null ? f.getCargo().getAreaCargo() : "Sem área (inativo)"));
		    exibirMensagem("Oficina: " + (f.getCentroAutomotivo() != null ? f.getCentroAutomotivo().getNomeCentro() : "Sem oficina (inativo)"));
		    exibirMensagem("Disponibilidade: " + (f.isDisponibilidade() ? "Disponível" : "Indisponível"));		    
		    exibirMensagem("Horário de trabalho: " + f.getHorarioTrabalho() + "\n");
	}
	
	public String solicitaMatricula() {
        System.out.print("Digite a Matrícula do funcionário (Ex: M12345): ");
        return scanner.nextLine();
    }

    public String solicitaNomeFuncionario() {
        System.out.print("Digite o Nome do funcionário: ");
        return scanner.nextLine();
    }

    public String solicitaHorarioTrabalho() {
        System.out.print("Digite o horário de trabalho (Ex: 07:30 - 17:30): ");
        return scanner.nextLine();
    }

    public String solicitaDisponibilidadeFuncionario() {
        System.out.print("Digite a disponibilidade do funcionário ('S' ou 'N'): ");
        return scanner.nextLine();
    }
    
    public String solicitaCentroFuncionario() {
        System.out.print("Digite o ID do Centro do funcionário (Ex: C123): ");
        return scanner.nextLine();
    }
    
    public String solicitaCargoFuncionario() {
        System.out.print("Digite o ID do Cargo do funcionário (Ex: CG12): ");
        return scanner.nextLine();
    }
    
    public String funcionarioAAtualizar() {
    	System.out.println("Qual o ID do funcionário que deseja atualizar? (Ex: M12345): ");
		return scanner.nextLine();
    }
    
    public String funcionarioARemover() {
    	System.out.println("Qual o ID do funcionário que deseja remover? (Ex: M12345): ");
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
