package sprint3.gerenciador;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sprint3.model.dao.centroAutomotivo.CentroDaoImpl;
import sprint3.model.vo.CentroAutomotivo;
import sprint3.view.CentroView;

public class GerenciadorCentro {

	private CentroView centroView;
    private CentroDaoImpl centroDao;
	  
    public GerenciadorCentro(CentroView centroView, CentroDaoImpl centroDao) {
		this.centroView = centroView;
		this.centroDao = centroDao;
	}

	public void gerenciarCentro() {
		while (true) {
			centroView.mostrarMenuCentro();
			String optCentroString = centroView.obterOpcao();;
			try {
				int optCentro = Integer.parseInt(optCentroString);
				if (optCentro >= 0 && optCentro <= 4) {
					if(optCentro == 0) {
						break;
					}
					switch (optCentro) {
					case 1:
						criarCentro();
						break;	
					case 2:
						visualizarCentros();
						break;
					case 3:
						atualizarCentro();
						break;
					case 4:
						deletarCentro();
						break;
				} 
			} else {
				System.out.println("Escolha uma opção entre 0 e 4.");
			}
		} catch (NumberFormatException e) {
			System.out.println("Opção inválida.");
	}}
	}
    
    public void criarCentro()  {
    	Pattern patternIdCentro = Pattern.compile("C\\d{3}");
    	Pattern patternHorario = Pattern.compile("^(?:[01]\\d|2[0-3]):[0-5]\\d\\s*-\\s*(?:[01]\\d|2[0-3]):[0-5]\\d$");
    	Pattern patternTelefone = Pattern.compile("^\\d{11}$");
    	String idCentro;
        String nomeCentro;
        String enderecoCentro;
        String telefoneCentro;
        String horarioFuncionamento;
        while (true) {
        	idCentro = centroView.solicitaIdCentro();
            Matcher matcher = patternIdCentro.matcher(idCentro);
            if (!matcher.matches()) {
            	centroView.exibirMensagem("ID com formato inválido.");
            	continue;
            } 
            if (centroDao.centroExiste(idCentro)) {
            	centroView.exibirMensagem("Centro Automotivo com ID já cadastrado.");
            	continue;
            }
            break;
            }
        while (true) {
             	nomeCentro = centroView.solicitaNomeCentro();
             	if (nomeCentro.isEmpty()) {
             		centroView.exibirMensagem("Digite algum nome para o centro.");
             		continue;
             	} else {
             		break;
             	}
             }
        while (true) {
         	enderecoCentro = centroView.solicitaEndereco();
         	if (enderecoCentro.isEmpty()) {
         		centroView.exibirMensagem("Digite algum endereço.");
         		continue;
         	}
         	if (enderecoCentro.length() < 20) {
         		centroView.exibirMensagem("O endereço do centro deve ter no mínimo 20 caracteres.");
         		continue;
         	}
         	 else {
         		break;
         	}
         }
        while (true) {
         	telefoneCentro = centroView.solicitaTelefone();
         	 Matcher matcher = patternTelefone.matcher(telefoneCentro);
             if (!matcher.matches()) {
             	centroView.exibirMensagem("Telefone com formato inválido.");
             	continue;
             } else {
            	 break;
             }
         }
        while (true) {
        	horarioFuncionamento = centroView.solicitaHorario();
        	 Matcher matcher = patternHorario.matcher(horarioFuncionamento);
            if (!matcher.matches()) {
            	centroView.exibirMensagem("Horário com formato inválido.");
            	continue;
            } else {
           	 break;
            }
        }    
        // Criação do centro
        CentroAutomotivo centro = new CentroAutomotivo(idCentro, nomeCentro, enderecoCentro, telefoneCentro, horarioFuncionamento);
        centroDao.inserir(centro);
        centroView.exibirMensagem("Centro Automotivo criado com sucesso!");         
        }
    
    
    
    public void atualizarCentro() {
    	String centro = centroView.centroAAtualizar();
    	while (true) {
    		 if (!centro.matches("C\\d{3}")) {
                 centroView.exibirMensagem("ID com formato inválido.");
                 break;
              }
    		 if (centroDao.centroExiste(centro) == false) {
                 centroView.exibirMensagem("Nenhum centro automotivo encontrado com o ID inserido.");
                 break;
              }
    		centroView.mostrarMenuCentroAtualizacao();
    		String optAtString = centroView.obterOpcao();
			try {
				int optAt = Integer.parseInt(optAtString);
				if (optAt >= 0 && optAt <= 4) {
					if(optAt == 0) {
						break;
					}
					switch (optAt) {
						case 1:
							String novoNomeCentro = centroView.solicitaNomeCentro();
			             	if (novoNomeCentro.isEmpty()) {
			             		centroView.exibirMensagem("Digite algum nome para o centro.");
			             		continue;
			             	}
			             	centroDao.atualizarNomeCentro(centro, novoNomeCentro);
							break;
						case 2:
							String novoEnderecoCentro = centroView.solicitaEndereco();
				         	if (novoEnderecoCentro.isEmpty()) {
				         		centroView.exibirMensagem("Digite algum endereço.");
				         		continue;
				         	}
				         	if (novoEnderecoCentro.length() < 20) {
				         		centroView.exibirMensagem("O endereço do centro deve ter no mínimo 20 caracteres.");
				         		continue;
				         	}
							centroDao.atualizarEndereco(centro, novoEnderecoCentro);
							break;
						case 3:
							String novoTelefoneCentro = centroView.solicitaTelefone();
				             if (!novoTelefoneCentro.matches("^\\d{11}$")) {
				             	centroView.exibirMensagem("Telefone com formato inválido.");
				             	continue;
				             }
				            centroDao.atualizarTelefoneCentro(centro, novoTelefoneCentro);
				            break;
						case 4:
							String novoHorarioFuncionamento = centroView.solicitaHorario();
				            if (!novoHorarioFuncionamento.matches("^(?:[01]\\d|2[0-3]):[0-5]\\d\\s*-\\s*(?:[01]\\d|2[0-3]):[0-5]\\d$")) {
				            	centroView.exibirMensagem("Horário com formato inválido.");
				            	continue;
				            }
						         centroDao.atualizarHorarioFuncionamento(centro, novoHorarioFuncionamento);
						         break;
					}
				} else {
					centroView.exibirMensagem("Digite uma opção entre 0 e 4.");
				}
			}catch (NumberFormatException e) {
				centroView.exibirMensagem("Opção inválida.");			
    }
  }
}
			
    public void visualizarCentros() {
    	ArrayList<CentroAutomotivo> listaCentros = centroDao.listar();
    	if (listaCentros.isEmpty()) {
			centroView.exibirMensagem("\nNenhum centro automotivo cadastrado.\n");
    	}
    	for (CentroAutomotivo ca : listaCentros) {
    		centroView.imprimirCentro(ca);
    	}
    }
    
    public void deletarCentro() {
    	String centro = centroView.centroARemover();
        if (centro.matches("C\\d{3}")) {
        	centroDao.deletar(centro);   	
        } else {
        	centroView.exibirMensagem("ID com formato inválido.");
        }	
    }

}
