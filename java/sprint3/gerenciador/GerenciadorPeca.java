package sprint3.gerenciador;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sprint3.model.dao.peca.PecaDaoImpl;
import sprint3.model.vo.Peca;
import sprint3.view.PecaView;

public class GerenciadorPeca {
	private PecaView pecaView;
	private PecaDaoImpl pecaDao;
	
	
	public GerenciadorPeca(PecaView pecaView, PecaDaoImpl pecaDao) {
		this.pecaView = pecaView;
		this.pecaDao = pecaDao;
	}

	
	public void gerenciarPeca() {	
		while (true) {
			pecaView.mostrarMenuPeca();
			String optPecaString = pecaView.obterOpcao();
			try {
				int optPeca = Integer.parseInt(optPecaString);
				if (optPeca >= 0 && optPeca <= 4) {
					if(optPeca == 0) {
						break;
					}
					switch (optPeca) {
					case 1:
						criarPeca();
						break;	
					case 2:
						visualizarPecas();
						break;
					case 3:
						atualizarPeca();
						break;
					case 4:
						deletarPeca();
						break;
				} 
			} else {
				System.out.println("Escolha uma opção entre 0 e 4.");
			}
		} catch (NumberFormatException e) {
			System.out.println("Opção inválida.");
	}}
}
	
    public void criarPeca() {
    	Pattern patternIdPeca = Pattern.compile("P\\d{5}");
    	String idPeca;
    	int disponibilidade;
    	String nomePeca;
    	double preco;
    	System.out.println("\n*-* CADASTRO PEÇA *-*");
        while (true) {
        	idPeca = pecaView.solicitaIdPeca();
            Matcher matcher = patternIdPeca.matcher(idPeca);
            if (!matcher.matches()) {
            	pecaView.exibirMensagem("ID com formato inválido.");
            	continue;
            } 
            if (pecaDao.pecaExiste(idPeca)) {
            	pecaView.exibirMensagem("Peça com ID já cadastrada.");
            	continue;
            }
            break;
            }
        while (true) {
            String disponibilidadeS = pecaView.solicitaDisponibilidade();
            try {
            	disponibilidade = Integer.parseInt(disponibilidadeS);
            	if (disponibilidade >= 0) {
            		break;
            	} else {
            		pecaView.exibirMensagem("O valor não pode ser menor que zero.");
            		continue;
            	}
            } catch (NumberFormatException e) {
            	pecaView.exibirMensagem("Valor não numérico inválido.");
            	continue;
            }
        }
        while (true) {
         	nomePeca = pecaView.solicitaNomePeca();
         	if (nomePeca.isEmpty()) {
         		pecaView.exibirMensagem("Digite algum nome.");
         		continue;
         	} else {
         		break;
         	}
         }
        while (true) {
            String precoS = pecaView.solicitaPreco();
            try {
            	preco = Double.parseDouble(precoS);
            	if (preco >= 0) {
            		break;
            	} else {
            		pecaView.exibirMensagem("O valor não pode ser menor que zero.");
            		continue;
            	}
            } catch (NumberFormatException e) {
            	pecaView.exibirMensagem("Valor não numérico inválido.");
            	continue;
            }
        }
        Peca peca = new Peca(idPeca, disponibilidade, nomePeca, preco);
        pecaDao.inserir(peca);
        pecaView.exibirMensagem("\nPeça cadastrada com sucesso!");
        } 
    
    
    public void atualizarPeca() {
    	String peca = pecaView.pecaAAtualizar();
    	while (true) {
    		if (!peca.matches("P\\d{5}")) {
                pecaView.exibirMensagem("ID com formato inválido.");
                break;
             }
    		if (pecaDao.pecaExiste(peca) == false) {
    			pecaView.exibirMensagem("Nenhuma peça encontrada com o ID inserido.");
    			break;
             }
    		pecaView.mostrarMenuPecaAtualizacao();
    		String optAtString = pecaView.obterOpcao();
			try {
				int optAt = Integer.parseInt(optAtString);
				if (optAt >= 0 && optAt <= 3) {
					if(optAt == 0) {
						break;
					}
					switch (optAt) {
						case 1:
							int disponibilidade;
							String disponibilidadeS = pecaView.solicitaDisponibilidade();
				            try {
				            	disponibilidade = Integer.parseInt(disponibilidadeS);
				            	if (disponibilidade < 0) {
				            		pecaView.exibirMensagem("O valor não pode ser menor que zero.");
				            		continue;
				            	}
				            } catch (NumberFormatException e) {
				            	pecaView.exibirMensagem("Valor não numérico inválido.");
				            	continue;
				            }
							pecaDao.atualizarDisponibilidade(peca,disponibilidade);
							break;
						case 2:
							String novoNomePeca = pecaView.solicitaNomePeca();
				         	if (novoNomePeca.isEmpty()) {
				         		pecaView.exibirMensagem("Digite algum nome.");
				         		continue;
				         	} 
				         	pecaDao.atualizarNomePeca(peca, novoNomePeca);
				         	break;
						case 3:
							 double preco;
							 String precoS = pecaView.solicitaPreco();
					            try {
					            	preco = Double.parseDouble(precoS);
					            	if (preco < 0) {
					            		pecaView.exibirMensagem("O valor não pode ser menor que zero.");
					            		continue;
					            	} 
					            } catch (NumberFormatException e) {
					            	pecaView.exibirMensagem("Valor não numérico inválido.");
					            	continue;
					            }
				            pecaDao.atualizarPrecoPeca(peca, preco);
				            break;
					}
				} else {
					pecaView.exibirMensagem("Selecione uma opção entre 0 e 3.");
				}
			}catch (NumberFormatException e) {
				pecaView.exibirMensagem("Opção inválida.");			
    }
  }
}
			    
    public void visualizarPecas()  {
    	ArrayList<Peca> listaPecas = pecaDao.listar();
    	if (listaPecas.isEmpty()) {
    		pecaView.exibirMensagem("\nNenhuma peça cadastrada.\n");
    	} else {
    		for (Peca p : listaPecas) {
        		pecaView.imprimirPeca(p);
        	}
    	}
    	
    }
    
    public void deletarPeca() {
    	String peca = pecaView.pecaARemover();
    	if (!peca.matches("P\\d{5}")) {
            pecaView.exibirMensagem("ID com formato inválido.");
         } else {
        	pecaDao.deletar(peca); 
         }
    }
	
}
