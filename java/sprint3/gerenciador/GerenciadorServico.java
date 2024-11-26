package sprint3.gerenciador;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sprint3.model.dao.servico.ServicoDaoImpl;
import sprint3.model.vo.Servico;
import sprint3.view.ServicoView;

public class GerenciadorServico {
   
	private ServicoView servicoView;
	private ServicoDaoImpl servicoDao;
	
	public GerenciadorServico(ServicoView servicoView, ServicoDaoImpl servicoDao) {
		this.servicoView = servicoView;
		this.servicoDao = servicoDao;
	}
	
	public void gerenciarServico() {
		while (true) {
			servicoView.mostrarMenuServico();
			String optServicoString = servicoView.obterOpcao();
			try {
				int optServico = Integer.parseInt(optServicoString);
				if (optServico >= 0 && optServico <= 4) {
					if(optServico == 0) {
						break;
					}
					switch (optServico) {
					case 1:
						criarServico();
						break;	
					case 2:
						visualizarServicos();
						break;
					case 3:
						atualizarServico();
						break;
					case 4:
						deletarServico();
						break;
				} 
			} else {
				System.out.println("Escolha uma opção entre 0 e 4.");
			}
		} catch (NumberFormatException e) {
			System.out.println("Opção inválida.");
	}}
	}
	
    public void criarServico() {
    	Pattern patternIdPeca = Pattern.compile("S\\d{5}");
    	String idServico;
    	String tipoServico;
    	String descricaoServico;
    	double precoServico;
    	int duracao;
    	System.out.println("\n*-* CADASTRO SERVIÇO *-*");
        while (true) {
        	idServico = servicoView.solicitaIdServico();
            Matcher matcher = patternIdPeca.matcher(idServico);
            if (!matcher.matches()) {
            	servicoView.exibirMensagem("ID com formato inválido.");
            	continue;
            } 
            if (servicoDao.servicoExiste(idServico)) {
            	servicoView.exibirMensagem("Serviço com ID já cadastrado.");
            	continue;
            }
            break;
            }
        while (true) {
         	tipoServico = servicoView.solicitaTipo();
         	if (tipoServico.isEmpty()) {
         		servicoView.exibirMensagem("Digite algum tipo.");
         		continue;
         	} else {
         		break;
         	}
         }
        while (true) {
         	descricaoServico = servicoView.solicitaDescricaoServico();
         	if (descricaoServico.isEmpty()) {
         		servicoView.exibirMensagem("Digite alguma descrição.");
         		continue;
         	} else {
         		break;
         	}
         }
        while (true) {
            String precoS = servicoView.solicitaPrecoServico();
            try {
            	precoServico = Double.parseDouble(precoS);
            	if (precoServico >= 0) {
            		break;
            	} else {
            		servicoView.exibirMensagem("O valor não pode ser menor que zero.");
            		continue;
            	}
            } catch (NumberFormatException e) {
            	servicoView.exibirMensagem("Valor não numérico inválido.");
            	continue;
            }
        }
        while (true) {
            String duracaoS = servicoView.solicitaDuracao();
            try {
            	duracao = Integer.parseInt(duracaoS);
            	if (duracao > 0) {
            		break;
            	} else {
            		servicoView.exibirMensagem("O valor não pode ser menor que zero.");
            		continue;
            	}
            } catch (NumberFormatException e) {
            	servicoView.exibirMensagem("Valor não numérico inválido.");
            	continue;
            }
        }
        Servico servico = new Servico(idServico, tipoServico, descricaoServico, precoServico, duracao);
        servicoDao.inserir(servico);
        servicoView.exibirMensagem("\nServiço cadastrado com sucesso!");
        } 
    
    
    public void atualizarServico() {
    	String servico = servicoView.servicoAAtualizar();
    	while (true) {
    		if (!servico.matches("S\\d{5}")) {
                servicoView.exibirMensagem("ID com formato inválido.");
                break;
             }
    		if (servicoDao.servicoExiste(servico) == false) {
    			servicoView.exibirMensagem("Nenhum serviço encontrado com o ID inserido.");
    			break;
             }
    		servicoView.mostrarMenuServicoAtualizacao();
    		String optAtString = servicoView.obterOpcao();
			try {
				int optAt = Integer.parseInt(optAtString);
				if (optAt >= 0 && optAt <= 4) {
					if(optAt == 0) {
						break;
					}
					switch (optAt) {
						case 1:
							String novoTipoServico = servicoView.solicitaTipo();
							if (novoTipoServico.isEmpty()) {
				         		servicoView.exibirMensagem("Digite algum tipo.");
				         		continue;
				         	} 
							servicoDao.atualizarTipo(servico, novoTipoServico);
							break;
						case 2:
							String novaDescricaoServico = servicoView.solicitaDescricaoServico();
				         	if (novaDescricaoServico.isEmpty()) {
				         		servicoView.exibirMensagem("Digite alguma descrição.");
				         		continue;
				         	}
				         	servicoDao.atualizarDescricao(servico, novaDescricaoServico);
				         	break;
						case 3:
							 double preco;
							 String precoS = servicoView.solicitaPrecoServico();
					            try {
					            	preco = Double.parseDouble(precoS);
					            	if (preco < 0) {
					            		servicoView.exibirMensagem("O valor não pode ser menor que zero.");
					            		continue;
					            	} 
					            } catch (NumberFormatException e) {
					            	servicoView.exibirMensagem("Valor não numérico inválido.");
					            	continue;
					            }
				            servicoDao.atualizarPreco(servico, preco);
				            break;
						case 4:
							int duracaoNova;
							String duracaoS = servicoView.solicitaDuracao();
				            try {
				            	duracaoNova = Integer.parseInt(duracaoS);
				            	if (duracaoNova < 0) {
				            		servicoView.exibirMensagem("O valor não pode ser menor que zero.");
				            		continue;
				            	} 
				            } catch (NumberFormatException e) {
				            	servicoView.exibirMensagem("Valor não numérico inválido.");
				            	continue;
				            }
							servicoDao.atualizarDuracao(servico,duracaoNova);
							break;
					}
				} else {
					servicoView.exibirMensagem("Selecione uma opção entre 0 e 4.");
				}
			}catch (NumberFormatException e) {
				servicoView.exibirMensagem("Opção inválida.");			
    }
  }
}
			    
    public void visualizarServicos() {
    	ArrayList<Servico> listaServicos = servicoDao.listar();
    	if (listaServicos.isEmpty()) {
    		servicoView.exibirMensagem("\nNenhuma peça cadastrada.\n");
    	} else {
    		for (Servico s : listaServicos) {
        		servicoView.imprimirServico(s);
        	}
    	}
    	
    }
    
    public void deletarServico() {
    	String servico = servicoView.servicoARemover();
    	if (!servico.matches("S\\d{5}")) {
            servicoView.exibirMensagem("ID com formato inválido.");
         } else {
        	servicoDao.deletar(servico); 
         }
    }

   
}
