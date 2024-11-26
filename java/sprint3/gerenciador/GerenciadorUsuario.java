package sprint3.gerenciador;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sprint3.model.dao.usuario.UsuarioDaoImpl;
import sprint3.model.vo.Usuario;
import sprint3.view.UsuarioView;

public class GerenciadorUsuario {
	private UsuarioView usuarioView;
	private UsuarioDaoImpl usuarioDao;
	
    public GerenciadorUsuario(UsuarioView usuarioView, UsuarioDaoImpl usuarioDao) {
		this.usuarioView = usuarioView;
		this.usuarioDao = usuarioDao;
	}
    
    public void gerenciarUsuario() {
		while (true) {
			usuarioView.mostrarMenuUsuario();
			String optUsuarioString = usuarioView.obterOpcao();
			try {
				int optUsuario = Integer.parseInt(optUsuarioString);
				if (optUsuario >= 0 && optUsuario <= 4) {
					if (optUsuario == 0) {
						break;
					}
					switch (optUsuario) {
					case 1:
						criarUsuario();
						break;	
					case 2:
						visualizarUsuarios();
						break;
					case 3:
						atualizarUsuario();
						break;
					case 4:
						deletarUsuario();
						break;
				} 
			} else {
				System.out.println("Escolha uma opção entre 0 e 4.");
			}
		} catch (NumberFormatException e) {
			
	}}
}

    public void criarUsuario() {
    	Pattern patternEmail = Pattern.compile("^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    	Pattern patternTel = Pattern.compile("^\\d{11}$");
    	Pattern patternCpf = Pattern.compile("^\\d{11}$");
    	String email;
    	String senha;
    	String telefone;
    	String cpf;
    	String nome;
    	System.out.println("\n*-* CADASTRO USUÁRIO *-*");
    	while (true) {
         	nome = usuarioView.solicitaNome();
         	if (nome.isEmpty()) {
         		usuarioView.exibirMensagem("Digite alguma nome.");
         		continue;
         	} else {
         		break;
         	}
         }
        while (true) {
        	email = usuarioView.solicitaEmail();
            Matcher matcher = patternEmail.matcher(email);
            if (!matcher.matches()) {
            	usuarioView.exibirMensagem("E-mail inválido.");
            	continue;
            } else {
            	break;
            	}	
            }
        while (true) {
            telefone = usuarioView.solicitaTelefone();
            Matcher matcher = patternTel.matcher(telefone);
            if (matcher.matches()) {
            	break;
            } else {
            	usuarioView.exibirMensagem("Telefone inválido.");
            	continue;
            }
        }
		while (true) {
			senha = usuarioView.solicitaSenha();
			if (senha.length() < 6) {
				usuarioView.exibirMensagem("A senha possui menos de 6 caracteres.");
				continue;
			} else {
				break;
			}
		}
		
        while (true) {
            cpf = usuarioView.solicitaCPF();
            Matcher matcher = patternCpf.matcher(cpf);
            if (!matcher.matches()) {
            	usuarioView.exibirMensagem("CPF inválido.");
            	continue;
            }
            if (usuarioDao.usuarioExiste(cpf)) {
            	usuarioView.exibirMensagem("CPF informado já está cadastrado.");
            	continue;
            }
            break;
        }
        Usuario usuario = new Usuario(cpf, nome, senha, email, telefone);
        usuarioDao.inserir(usuario);
        usuarioView.exibirMensagem("\nUsuário cadastrado com sucesso!");
        } 
    
    
    public void atualizarUsuario() {
    	String cpf = usuarioView.usuarioAAtualizar();
    	while (true) {
    		if (!cpf.matches("^\\d{11}$")) {
                usuarioView.exibirMensagem("\nCPF com formato inválido.\n");
                break;
             }
    		if (usuarioDao.usuarioExiste(cpf) == false) {
    			usuarioView.exibirMensagem("\nNenhum usuário encontrado com o CPF inserido.\n");
    			break;
             }
    		usuarioView.mostrarMenuUsuarioAtualizacao();
    		String optAtString = usuarioView.obterOpcao();
			try {
				int optAt = Integer.parseInt(optAtString);
				if (optAt >= 0 && optAt <= 4) {
					if(optAt == 0) {
						break;
					}
					switch (optAt) {
						case 1:
							String novoNome = usuarioView.solicitaNome();
							usuarioDao.atualizarNome(cpf, novoNome);
							break;
						case 2:
							String novoEmail = usuarioView.solicitaEmail();
				            if (!novoEmail.matches("^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
				            	usuarioView.exibirMensagem("E-mail inválido.");
				            	continue;
				            } 
							usuarioDao.atualizarEmail(cpf, novoEmail);
							break;
						case 3:
							String novoTelefone = usuarioView.solicitaTelefone();
							if (!novoTelefone.matches("^\\d{11}$")) {
								usuarioView.exibirMensagem("Telefone inválido.");
								continue;
				            }
				            usuarioDao.atualizarTelefone(cpf, novoTelefone);
				            break;
						case 4:
							String novaSenha = usuarioView.solicitaSenha();
							if (novaSenha.length() < 6) {
								System.out.println("A senha possui menos de 6 caracteres.");
								continue;
							} else {
								usuarioDao.atualizarSenha(cpf, novaSenha);
								break;
							}
							
					}
				} else {
					usuarioView.exibirMensagem("Selecione uma opção entre 0 e 4.");
				}
			}catch (NumberFormatException e) {
				usuarioView.exibirMensagem("Opção inválida.");			
    }
  }
}
			    
    public void visualizarUsuarios() {
    	ArrayList<Usuario> listaUsuarios = usuarioDao.listar();
    	if (listaUsuarios.isEmpty()) {
    		usuarioView.exibirMensagem("\nNenhum usuário cadastrado.\n");
    	} else {
    		for (Usuario u : listaUsuarios) {
        		usuarioView.imprimirUsuario(u);;
        	}
    	}
    }
    
    public void deletarUsuario() {
    	String cpf = usuarioView.usuarioARemover();
    	if (!cpf.matches("^\\d{11}$")) {
            usuarioView.exibirMensagem("CPF com formato inválido.");
         } else {
        	usuarioDao.deletar(cpf); 
         }
    }
}
    

    
    
