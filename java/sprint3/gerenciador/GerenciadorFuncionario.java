package sprint3.gerenciador;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sprint3.model.dao.cargo.CargoDaoImpl;
import sprint3.model.dao.centroAutomotivo.CentroDaoImpl;
import sprint3.model.dao.funcionario.FuncionarioDaoImpl;
import sprint3.model.vo.Cargo;
import sprint3.model.vo.CentroAutomotivo;
import sprint3.model.vo.Funcionario;
import sprint3.view.FuncionarioView;

public class GerenciadorFuncionario {

	private FuncionarioView funcionarioView;
	private FuncionarioDaoImpl funcionarioDao;
	private CentroDaoImpl centroDao;
	private CargoDaoImpl cargoDao;
	
    
    
    public GerenciadorFuncionario(FuncionarioView funcionarioView, FuncionarioDaoImpl funcionarioDao,
			CentroDaoImpl centroDao, CargoDaoImpl cargoDao) {
		this.funcionarioView = funcionarioView;
		this.funcionarioDao = funcionarioDao;
		this.centroDao = centroDao;
		this.cargoDao = cargoDao;
	}

	public void gerenciarFuncionario() {
		while (true) {
			funcionarioView.mostrarMenuFuncionario();
			String optFuncionarioString = funcionarioView.obterOpcao();
			try {
				int optFuncionario = Integer.parseInt(optFuncionarioString);
				if (optFuncionario >= 0 && optFuncionario <= 4) {
					if(optFuncionario == 0) {
						break;
					}
					switch (optFuncionario) {
					case 1:
						criarFuncionario();
						break;	
					case 2:
						visualizarFuncionarios();
						break;
					case 3:
						atualizarFuncionario();
						break;
					case 4:
						deletarFuncionario();
						break;
				} 
			} else {
				System.out.println("Escolha uma opção entre 0 e 4.");
			}
		} catch (NumberFormatException e) {
			System.out.println("Opção inválida.");
	}}
	}
    
    public void criarFuncionario()  {
    	Pattern patternMatricula = Pattern.compile("M\\d{5}");
    	Pattern patternHorario = Pattern.compile("^(?:[01]\\d|2[0-3]):[0-5]\\d\\s*-\\s*(?:[01]\\d|2[0-3]):[0-5]\\d$");
    	String matricula;
        String nomeFunc;
        String horarioTrabalho;
        boolean disponibilidade;
        CentroAutomotivo centro;
        Cargo cargo;
        while (true) {
        	if (cargoDao.listar().isEmpty() || centroDao.listar().isEmpty()) {
        		funcionarioView.exibirMensagem("\nErro. É necessário que haja ao menos um centro e um cargo para criar um funcionário.\n");
        		break;
        	}
        while (true) {
        	matricula = funcionarioView.solicitaMatricula();
            Matcher matcher = patternMatricula.matcher(matricula);
            if (!matcher.matches()) {
            	funcionarioView.exibirMensagem("Matrícula com formato inválido.");
            	continue;
            } 
            if (funcionarioDao.funcionarioExiste(matricula)) {
            	funcionarioView.exibirMensagem("Funcionário com matrícula já cadastrado.");
            	continue;
            }
            break;
            }
        while (true) {
             	nomeFunc = funcionarioView.solicitaNomeFuncionario();
             	if (nomeFunc.isEmpty()) {
             		funcionarioView.exibirMensagem("Digite algum nome para o funcionário.");
             		continue;
             	} else {
             		break;
             	}
             }
        while (true) {
        	horarioTrabalho = funcionarioView.solicitaHorarioTrabalho();
       	 	Matcher matcher = patternHorario.matcher(horarioTrabalho);
           if (!matcher.matches()) {
           	funcionarioView.exibirMensagem("Horário com formato inválido.");
           	continue;
           } else {
          	 break;
           }
         }
        while (true) {
        	String disponibilidadeS = funcionarioView.solicitaDisponibilidadeFuncionario();
        	if ("S".equals(disponibilidadeS)) {
        		disponibilidade = true;
                break;
            } else if ("N".equals(disponibilidadeS)) {
                disponibilidade = false;
                break;
            } else {
                System.out.println("Por favor, digite 'S' ou 'N'.");
                continue;
            	}
        	}   
        while (true) {
        	String idCentro = funcionarioView.solicitaCentroFuncionario();
            if (!idCentro.matches("C\\d{3}")) {
            	funcionarioView.exibirMensagem("ID com formato inválido.");
                continue;
            }
            centro = centroDao.buscarPorId(idCentro);
            if (centro == null) {
            	funcionarioView.exibirMensagem("O ID inserido não existe no sistema.");
                continue;
            }
            break;
         }
        while (true) {
        	String idCargo = funcionarioView.solicitaCargoFuncionario();
            if (!idCargo.matches("CG\\d{2}")) {
            	funcionarioView.exibirMensagem("ID com formato inválido.");
                continue;
            }
            cargo = cargoDao.buscarPorId(idCargo);
            if (cargo == null) {
            	funcionarioView.exibirMensagem("O ID inserido não existe no sistema.");
                continue;
            }
            break;
        }    
        // Criação do funcionário
        Funcionario funcionario = new Funcionario(matricula, nomeFunc, cargo, centro, disponibilidade, horarioTrabalho);
        funcionarioDao.inserir(funcionario);
        funcionarioView.exibirMensagem("Funcionário criado com sucesso!"); 
        break;
        }
    }
    
    
    
    public void atualizarFuncionario() {
    	String funcionario = funcionarioView.funcionarioAAtualizar();
    	while (true) {
    		 if (!funcionario.matches("M\\d{5}")) {
                 funcionarioView.exibirMensagem("ID com formato inválido.");
                 break;
              }
    		 if (funcionarioDao.funcionarioExiste(funcionario) == false) {
                 funcionarioView.exibirMensagem("Nenhum funcionário encontrado com o ID inserido.");
                 break;
              }
    		funcionarioView.mostrarMenuFuncionarioAtualizacao();
    		String optAtString = funcionarioView.obterOpcao();
			try {
				int optAt = Integer.parseInt(optAtString);
				if (optAt >= 0 && optAt <= 5) {
					if(optAt == 0) {
						break;
					}
					switch (optAt) {
						case 1:
							String novoNomeFunc = funcionarioView.solicitaNomeFuncionario();
			             	if (novoNomeFunc.isEmpty()) {
			             		funcionarioView.exibirMensagem("Digite algum nome para o funcionário.");
			             		continue;
			             	}
			             	funcionarioDao.atualizarNomeFuncionario(funcionario, novoNomeFunc);
							break;
						case 2:
							String novoHorarioTrabalho = funcionarioView.solicitaHorarioTrabalho();
							if (!novoHorarioTrabalho.matches("^(?:[01]\\d|2[0-3]):[0-5]\\d\\s*-\\s*(?:[01]\\d|2[0-3]):[0-5]\\d$")) {
				            	funcionarioView.exibirMensagem("Horário com formato inválido.");
				            	continue;
				            }
						         funcionarioDao.atualizarHorarioTrabalho(funcionario, novoHorarioTrabalho);
						         break;
						case 3:
							boolean novaDisponibilidade;
							String novaDisponibilidadeS = funcionarioView.solicitaDisponibilidadeFuncionario();
							if ("S".equals(novaDisponibilidadeS)) {
				        		novaDisponibilidade = true;
				            } else if ("N".equals(novaDisponibilidadeS)) {
				            	novaDisponibilidade = false;
				            } else {
				                System.out.println("Por favor, digite 'S' ou 'N'.");
				                continue;
				            	}
				            funcionarioDao.atualizarDisponibilidadeFuncionario(funcionario, novaDisponibilidade);
				            break;
						case 4:
							String novoCentro = funcionarioView.solicitaCentroFuncionario();
							if (!novoCentro.matches("C\\d{3}")) {
								funcionarioView.exibirMensagem("ID com formato inválido.");
							}
							if (centroDao.centroExiste(novoCentro) == false) {
				                 funcionarioView.exibirMensagem("Nenhum centro automotivo encontrado com o ID inserido.");
				              } else {
				            	  funcionarioDao.atualizarCentro(funcionario, novoCentro);
				              }
							break;
						case 5:
							String novoCargo = funcionarioView.solicitaCargoFuncionario();
							if (!novoCargo.matches("CG\\d{2}")) {
								funcionarioView.exibirMensagem("ID com formato inválido.");
							}
							if (cargoDao.cargoExiste(novoCargo) == false) {
				                 funcionarioView.exibirMensagem("Nenhum cargo encontrado com o ID inserido.");
				              } else {
				            	  funcionarioDao.atualizarCargo(funcionario, novoCargo);
				              }
							break;	
					}     
					}
				 else {
					funcionarioView.exibirMensagem("Digite uma opção entre 0 e 5.");
				}
			}catch (NumberFormatException e) {
				funcionarioView.exibirMensagem("Opção inválida.");			
    }
  }
}
			
    public void visualizarFuncionarios() {
    	ArrayList<Funcionario> listaFuncionarios = funcionarioDao.listar();
    	if (listaFuncionarios.isEmpty()) {
			funcionarioView.exibirMensagem("\nNenhum funcionário cadastrado.\n");
    	}
    	for (Funcionario f : listaFuncionarios) {
    		funcionarioView.imprimirFuncionario(f);
    	}
    }
    
    public void deletarFuncionario() {
    	String funcionario = funcionarioView.funcionarioARemover();
        if (funcionario.matches("M\\d{5}")) {
        	funcionarioDao.deletar(funcionario);   	
        } else {
        	funcionarioView.exibirMensagem("ID com formato inválido.");
        }	
    }
}
