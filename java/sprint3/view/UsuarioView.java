package sprint3.view;

import java.util.Scanner;

import sprint3.model.vo.Usuario;

public class UsuarioView implements IView {
	
	Scanner scanner = new Scanner(System.in);
	
	public void mostrarMenuUsuario() {
		System.out.println("\n========== { MENU DO USUÁRIO } ==========\n");
		System.out.println("1.  Cadastrar Usuário");
		System.out.println("2.  Listar Usuários");
		System.out.println("3.  Atualizar Usuário");
		System.out.println("4.  Deletar Usuário");
		System.out.println("0.  Sair");
		System.out.println("\nEscolha uma opção: ");
	}
	
	public void mostrarMenuUsuarioAtualizacao() {
		System.out.println("\n========== { MENU DE ATUALIZAÇÃO DO USUÁRIO } ==========\n");
		System.out.println("1.  Atualizar Nome do Usuário");
		System.out.println("2.  Atualizar E-mail");
		System.out.println("3.  Atualizar Telefone");
		System.out.println("4.  Atualizar Senha");
		System.out.println("0.  Sair");
		System.out.println("\nEscolha uma opção: ");
	}
	
	public void imprimirUsuario(Usuario u) {
		exibirMensagem("\n====== { INFORMAÇÕES DO USUÁRIO DE CPF " + u.getCpfUsuario() + " } ======");
		exibirMensagem("CPF: " + u.getCpfUsuario());
		exibirMensagem("Nome: " + u.getNomeUsuario());
		exibirMensagem("E-mail: " + u.getEmail());
		exibirMensagem("Senha: " + u.getSenha());
		exibirMensagem("Telefone: " + u.getTelefone());
	}
	
	 public String solicitaCPF() {
	        System.out.print("Digite o CPF do usuário: ");
	        return scanner.nextLine();
	    }

	    public String solicitaEmail() {
	        System.out.print("Digite o E-mail do usuário: ");
	        return scanner.nextLine();
	    }

	    public String solicitaTelefone() {
	        System.out.print("Digite o Telefone do usuário (ex: 11989328374): ");
	        return scanner.nextLine();
	    }

	    public String solicitaNome() {
	        System.out.print("Digite o Nome do usuário: ");
	        return scanner.nextLine();
	    }

	    public String solicitaSenha() {
	        System.out.print("Digite a Senha do usuário: ");
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
		
		 public String usuarioAAtualizar() {
		    	System.out.println("Qual o CPF do usuário que deseja atualizar? (Ex: 11122233344): ");
				return scanner.nextLine();
		    }
		    
		public String usuarioARemover() {
		    	System.out.println("Qual o CPF do usuário que deseja remover? (Ex: 11122233344. OBS: todos os veículos, agendamentos e diagnósticos no usuário serão removidos): ");
				return scanner.nextLine();
		    }
}
