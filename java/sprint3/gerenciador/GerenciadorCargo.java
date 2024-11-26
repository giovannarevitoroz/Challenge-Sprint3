package sprint3.gerenciador;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sprint3.model.dao.cargo.CargoDaoImpl;
import sprint3.model.vo.Cargo;
import sprint3.view.CargoView;


public class GerenciadorCargo {

    private CargoView cargoView;
    private CargoDaoImpl cargoDao;
	
    public GerenciadorCargo(CargoView cargoView, CargoDaoImpl cargoDao) {
		this.cargoView = cargoView;
		this.cargoDao = cargoDao;
	}
    
    public void gerenciarCargo() {
		while (true) {
			cargoView.mostrarMenuCargo();
			String optCargoString = cargoView.obterOpcao();
			try {
				int optCargo = Integer.parseInt(optCargoString);
				if (optCargo >= 0 && optCargo <= 4) {
					if(optCargo == 0) {
						break;
					}
					switch (optCargo) {
					case 1:
						criarCargo();
						break;	
					case 2:
						visualizarCargos();
						break;
					case 3:
						atualizarCargo();
						break;
					case 4:
						deletarCargo();
						break;
				} 
			} else {
				System.out.println("Escolha uma opção entre 0 e 4.");
			}
		} catch (NumberFormatException e) {
			System.out.println("Opção inválida.");
	}}
	}
    
    public void criarCargo() {
    	Pattern patternIdCargo = Pattern.compile("CG\\d{2}");
    	String idCargo;
        String nomeCargo;
        String areaCargo;
        String descricaoCargo;
        double salarioCargo;
        while (true) {
        	idCargo = cargoView.solicitaIdCargo();
            Matcher matcher = patternIdCargo.matcher(idCargo);
            if (!matcher.matches()) {
            	cargoView.exibirMensagem("ID com formato inválido.");
            	continue;
            } 
            if (cargoDao.cargoExiste(idCargo)) {
            	cargoView.exibirMensagem("Cargo com ID já cadastrado.");
            	continue;
            }
            break;
            }
        while (true) {
             	nomeCargo = cargoView.solicitaNomeCargo();
             	if (nomeCargo.isEmpty()) {
             		cargoView.exibirMensagem("Digite algum nome de cargo.");
             		continue;
             	} else {
             		break;
             	}
             }
        while (true) {
         	areaCargo = cargoView.solicitaAreaCargo();
         	if (areaCargo.isEmpty()) {
         		cargoView.exibirMensagem("Digite alguma área de cargo.");
         		continue;
         	} else {
         		break;
         	}
         }
        while (true) {
         	descricaoCargo = cargoView.solicitaDescricaoCargo();
         	if (descricaoCargo.isEmpty()) {
         		cargoView.exibirMensagem("Digite alguma descrição para o cargo.");
         		continue;
         	} else {
         		break;
         	}
         }
        while (true) {
            String salarioS = cargoView.solicitaSalarioCargo();
            try {
                salarioCargo = Double.parseDouble(salarioS);
                if (salarioCargo <= 0) {
                    cargoView.exibirMensagem("Salário deve ser maior que 0.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                cargoView.exibirMensagem("Digite um valor numérico válido para o salário.");
                continue;
            }
        }    
        // Criação do cargo
        Cargo cargo = new Cargo(idCargo, nomeCargo, descricaoCargo, areaCargo, salarioCargo);
        cargoDao.inserir(cargo);
        cargoView.exibirMensagem("Cargo criado com sucesso!");         
        }
    
    
    
    public void atualizarCargo() {
    	String cargo = cargoView.cargoAAtualizar();
    	while (true) {
    		 if (!cargo.matches("CG\\d{2}")) {
                 cargoView.exibirMensagem("ID com formato inválido.");
                 break;
              }
    		 if (cargoDao.cargoExiste(cargo) == false) {
                 cargoView.exibirMensagem("Nenhum cargo encontrado com o ID inserido.");
                 break;
              }
    		cargoView.mostrarMenuCargoAtualizacao();
    		String optAtString = cargoView.obterOpcao();
			try {
				int optAt = Integer.parseInt(optAtString);
				if (optAt >= 0 && optAt <= 4) {
					if(optAt == 0) {
						break;
					}
					switch (optAt) {
						case 1:
							String novoNomeCargo = cargoView.solicitaNomeCargo();
			             	if (novoNomeCargo.isEmpty()) {
			             		cargoView.exibirMensagem("Digite algum nome de cargo.");
			             		continue;
			             	}
			             	cargoDao.atualizarNomeCargo(cargo, novoNomeCargo);
							break;
						case 2:
							String novaAreaCargo = cargoView.solicitaAreaCargo();
				         	if (novaAreaCargo.isEmpty()) {
				         		cargoView.exibirMensagem("Digite alguma área de cargo.");
				         		continue;
				         	}
							cargoDao.atualizarAreaCargo(cargo, novaAreaCargo);
							break;
						case 3:
							String novaDescricaoCargo = cargoView.solicitaDescricaoCargo();
				         	if (novaDescricaoCargo.isEmpty()) {
				         		cargoView.exibirMensagem("Digite alguma descrição para o cargo.");
				         		continue;
				         	}
				            cargoDao.atualizarDescricaoCargo(cargo, novaDescricaoCargo);
				            break;
						case 4:
							double novoSalarioCargo = 0;
							String novoSalarioS = cargoView.solicitaSalarioCargo();
				            try {
				                novoSalarioCargo = Double.parseDouble(novoSalarioS);
				                if (novoSalarioCargo <= 0) {
				                    cargoView.exibirMensagem("Salário deve ser maior que 0.");
				                    continue;
				                }
				            } catch (NumberFormatException e) {
				                cargoView.exibirMensagem("Digite um valor numérico válido para o salário.");
				            }
						         cargoDao.atualizarSalarioCargo(cargo, novoSalarioCargo);
						         break;
					}
				} else {
					System.out.println("Digite uma opção entre 0 e 4.");
				}
			}catch (NumberFormatException e) {
				System.out.println("Opção inválida.");			
    }
  }
}
			
	
    
    
    public void visualizarCargos()  {
    	ArrayList<Cargo> listaCargos = cargoDao.listar();
    	if (listaCargos.isEmpty()) {
    		cargoView.exibirMensagem("\nNenhum cargo cadastrado.\n");
    	}
    	for (Cargo c : listaCargos) {
    		cargoView.imprimirCargo(c);
    	}
    }
    
    public void deletarCargo()  {
    	String cargo = cargoView.cargoARemover();
        if (cargo.matches("CG\\d{2}")) {
        	cargoDao.deletar(cargo);   	
        } else {
        	System.out.println("ID com formato inválido.");
        }	
    }
    
}
