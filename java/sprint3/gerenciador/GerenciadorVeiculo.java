package sprint3.gerenciador;

import java.sql.SQLException;
import java.util.ArrayList;
import sprint3.model.dao.usuario.UsuarioDaoImpl;
import sprint3.model.dao.veiculo.VeiculoDaoImpl;
import sprint3.model.vo.Usuario;
import sprint3.model.vo.Veiculo;
import sprint3.view.VeiculoView;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GerenciadorVeiculo  {
	private VeiculoDaoImpl daoVeiculo;
	private UsuarioDaoImpl daoUsuario;
	private VeiculoView veiculoView;
    
    public GerenciadorVeiculo(VeiculoDaoImpl daoVeiculo, UsuarioDaoImpl daoUsuario, VeiculoView veiculoView) {
    	this.daoVeiculo = daoVeiculo;
    	this.daoUsuario = daoUsuario;
    	this.veiculoView = veiculoView;
    }
    
    public void gerenciarVeiculo() throws SQLException {
		while (true) {
			veiculoView.mostrarMenuVeiculo();
			String optVeiculoString = veiculoView.obterOpcao();
			try {
				int optVeiculo = Integer.parseInt(optVeiculoString);
				if (optVeiculo >= 0 && optVeiculo <= 4) {
					if(optVeiculo == 0) {
						break;
					}
					switch (optVeiculo) {
					case 1:
						criarVeiculo();
						break;	
					case 2:
						visualizarVeiculos();
						break;
					case 3:
						atualizarVeiculo();
						break;
					case 4:
						deletarVeiculo();
						break;
				} 
			} else {
				System.out.println("Escolha uma opção entre 0 e 4.");
			}
		} catch (NumberFormatException e) {
			System.out.println("Opção inválida.");
	}}
}
    
    public void criarVeiculo() {
    	String marca;
        String modelo;
        int ano;
        String placa;
        Usuario usuario;
        while (true) {
        	if (daoUsuario.listar().isEmpty()) {
        		veiculoView.exibirMensagem("\nNenhum usuário cadastrado. Não é possível cadastrar um veículo.\n");
        		break;
        	}
        	 while (true) {
             	marca = veiculoView.solicitaMarca();
             	if (marca.isEmpty()) {
             		veiculoView.exibirMensagem("Digite alguma marca.");
             		continue;
             	} else {
             		break;
             	}
             }
             while (true) {
             	modelo = veiculoView.solicitaModelo();
             	if (modelo.isEmpty()) {
             		veiculoView.exibirMensagem("Digite algum modelo.");
             		continue;
             	} else {
             		break;
             	}
             }
             while (true) {
             	String anoString = veiculoView.solicitaAno();
                 if (!anoString.matches("^(19[8-9][0-9]|20[0-1][0-9]|202[0-4])$")) {
                     veiculoView.exibirMensagem("Ano inválido.");
                     continue;
                 }
                 ano = Integer.parseInt(anoString);
                 break;
             }
             while (true) {
             	placa = veiculoView.solicitaPlaca();
                 if (!placa.matches("^[A-Z]{3}\\d[A-Z]\\d{2}$")) {
                     veiculoView.exibirMensagem("Placa com formato inválido.");
                     continue;
                 }
                 if (daoVeiculo.veiculoExiste(placa)) {
                     veiculoView.exibirMensagem("A placa inserida já existe no sistema.");
                     continue;
                 }
                 break;
             }
             double quilometragem;
             while (true) {
                 String quilometragemStr = veiculoView.solicitaQuilometragem();
                 try {
                     quilometragem = Double.parseDouble(quilometragemStr);
                     if (quilometragem <= 0) {
                         veiculoView.exibirMensagem("Quilometragem deve ser maior que 0.");
                         continue;
                     }
                     break;
                 } catch (NumberFormatException e) {
                     veiculoView.exibirMensagem("Digite um valor numérico válido para a quilometragem.");
                 }
             }
             while (true) {
             	String cpf = veiculoView.solicitaCpfProprietario();
                 if (!cpf.matches("^\\d{11}$")) {
                     veiculoView.exibirMensagem("CPF inválido.");
                     continue;
                 }
                 usuario = daoUsuario.buscarPorCPF(cpf);
                 if (usuario == null) {
                     veiculoView.exibirMensagem("O CPF inserido não existe no sistema.");
                     continue;
                 }
                 break;
             }        
             // Criação do veículo
             Veiculo veiculo = new Veiculo(marca, modelo, ano, placa, quilometragem, usuario);
             daoVeiculo.inserir(veiculo);
             veiculoView.exibirMensagem("Veículo criado com sucesso!");
             break;
        }
    }
    
    
    public void atualizarVeiculo() throws SQLException {
    	String placa = veiculoView.veiculoAAtualizar();
    	while (true) {
    		 if (!placa.matches("^[A-Z]{3}\\d[A-Z]\\d{2}$")) {
                 veiculoView.exibirMensagem("Placa com formato inválido.");
                 break;
              }
    		 if (daoVeiculo.veiculoExiste(placa) == false) {
                 veiculoView.exibirMensagem("Nenhum veículo encontrado com a placa inserida.");
                 break;
              }
    		veiculoView.mostrarMenuVeiculoAtualizacao();
    		String optAtString = veiculoView.obterOpcao();
			try {
				int optAt = Integer.parseInt(optAtString);
				if (optAt >= 0 && optAt <= 5) {
					if(optAt == 0) {
						break;
					}
					switch (optAt) {
						case 1:
							String novaMarca = veiculoView.solicitaMarca();
			             	if (novaMarca.isEmpty()) {
			             		veiculoView.exibirMensagem("Digite alguma marca.");
			             		continue;
			             	} 
							daoVeiculo.atualizarMarca(placa, novaMarca);
							break;
						case 2:
							String novoModelo = veiculoView.solicitaModelo();
			             	if (novoModelo.isEmpty()) {
			             		veiculoView.exibirMensagem("Digite algum modelo.");
			             		continue;
			             	}
							daoVeiculo.atualizarModelo(placa, novoModelo);
							break;
						case 3:
							String novoAnoS = veiculoView.solicitaAno();
							if (!novoAnoS.matches("^(19[8-9][0-9]|20[0-1][0-9]|202[0-4])$")) {
				                veiculoView.exibirMensagem("Ano inválido.");
				            }
				            int novoAno = Integer.parseInt(novoAnoS);
				            daoVeiculo.atualizarAno(placa, novoAno);
				            break;
						case 4:
							double novaQuilometragem = 0;
							String novaQuilometragemS = veiculoView.solicitaQuilometragem();
						         try {
						             novaQuilometragem = Double.parseDouble(novaQuilometragemS);
						             if (novaQuilometragem <= 0) {
						                 veiculoView.exibirMensagem("Quilometragem deve ser maior que 0.");
						                }
						            } catch (NumberFormatException e) {
						                veiculoView.exibirMensagem("Digite um valor numérico válido para a quilometragem.");
						            }
						         daoVeiculo.atualizarQuilometragem(placa, novaQuilometragem);
						         break;
						case 5:
							String novoProprietario = veiculoView.solicitaCpfProprietario();
							if (!novoProprietario.matches("^\\d{11}$")) {
				                veiculoView.exibirMensagem("CPF inválido.");
				            } else {
				            	daoVeiculo.atualizarCpfProprietario(placa, novoProprietario);
				            }  
							break;
					}
				} else {
					System.out.println("Digite uma opção entre 0 e 5.");
				}
			}catch (NumberFormatException e) {
				System.out.println("Opção inválida.");			
    }
  }
}
			
    public void visualizarVeiculos() {
    	ArrayList<Veiculo> listaVeiculos = daoVeiculo.listar();
    	if (listaVeiculos.isEmpty()) {
    		veiculoView.exibirMensagem("\nNenhum veículo cadastrado.\n");
    	}
    	for (Veiculo v : listaVeiculos) {
    		veiculoView.imprimirVeiculo(v);
    	}
    }
    
    public void deletarVeiculo() {
    	String placa = veiculoView.veiculoARemover();
    	Pattern patternPlaca = Pattern.compile("^[A-Z]{3}\\d[A-Z]\\d{2}$");
    	Matcher matcher = patternPlaca.matcher(placa);
        if (matcher.matches()) {
        	daoVeiculo.deletar(placa);   	
        } else {
        	System.out.println("Placa com formato inválido.");
        }	
    }
    
}
