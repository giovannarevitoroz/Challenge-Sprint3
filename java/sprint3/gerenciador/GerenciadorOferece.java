package sprint3.gerenciador;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sprint3.model.dao.centroAutomotivo.CentroDaoImpl;
import sprint3.model.dao.oferece.OfereceDAOImpl;
import sprint3.model.dao.servico.ServicoDaoImpl;
import sprint3.model.vo.CentroAutomotivo;
import sprint3.model.vo.Servico;
import sprint3.view.OfereceView;

public class GerenciadorOferece {
	private OfereceView ofereceView;
	private CentroDaoImpl centroDao;
	private ServicoDaoImpl servicoDao;
	private OfereceDAOImpl ofereceDao;
	
	public GerenciadorOferece(OfereceView ofereceView, CentroDaoImpl centroDao, ServicoDaoImpl servicoDao, OfereceDAOImpl ofereceDao) {
		this.ofereceView = ofereceView;
		this.centroDao = centroDao;
		this.servicoDao = servicoDao;
		this.ofereceDao = ofereceDao;
	}

	public void gerenciarOferece() {
		while (true) {
			ofereceView.mostrarMenuOferece();
			String optOfereceString = ofereceView.obterOpcao();
			try {
				int optOferece = Integer.parseInt(optOfereceString);
				if (optOferece >= 0 && optOferece <= 4) {
					if(optOferece == 0) {
						break;
					}
					switch (optOferece) {
					case 1:
						criarAssociacaoOferece();
						break;	
					case 2:
						visualizarServicosDoCentro();;
						break;
					case 3:
						visualizarCentrosDoServico();
						break;
					case 4:
						deletarAssociacaoOferece();
						break;
				} 
			} else {
				System.out.println("Escolha uma opção entre 0 e 4.");
			}
		} catch (NumberFormatException e) {
			System.out.println("Opção inválida.");
	}}
	}
	
	 public void criarAssociacaoOferece() {
	    	Pattern patternIdCentro = Pattern.compile("C\\d{3}");
	    	Pattern patternIdServico = Pattern.compile("S\\d{5}");
	    	String idCentro;
	    	String idServico;
	    	while (true) {
	    		if (centroDao.listar().isEmpty() || servicoDao.listar().isEmpty()) {
	    			ofereceView.exibirMensagem("Erro. É necessário ao menos um centro automotivo e um serviço para estabelecer uma relação.");
	    			break;
	    		}
	    	System.out.println("\n*-* CADASTRO ASSOCIAÇÃO *-*");
	        while (true) {
	        	idCentro = ofereceView.solicitaCentroOferece();
	            Matcher matcher = patternIdCentro.matcher(idCentro);
	            if (!matcher.matches()) {
	            	ofereceView.exibirMensagem("ID com formato inválido.");
	            	continue;
	            } 
	            if (!centroDao.centroExiste(idCentro)) {
	            	ofereceView.exibirMensagem("Centro automotivo não cadastrado.");
	            	continue;
	            }
	            break;
	            }
	        while (true) {
	        	idServico = ofereceView.solicitaServicoOferece();
	            Matcher matcher = patternIdServico.matcher(idServico);
	            if (!matcher.matches()) {
	            	ofereceView.exibirMensagem("ID com formato inválido.");
	            	continue;
	            } 
	            if (!servicoDao.servicoExiste(idServico)) {
	            	ofereceView.exibirMensagem("Serviço não cadastrado.");
	            	continue;
	            }
	            break;
	            }
	        boolean inseriu = ofereceDao.adicionarAssociacao(idServico, idCentro);
	        if (inseriu) {
	        	ofereceView.exibirMensagem("\nAssociação cadastrada com sucesso!");
	        }
	        break;
	        } }
	
				    
	    public void visualizarCentrosDoServico()  {
	    	String idServico = ofereceView.solicitaServicoOferece();
	    	ArrayList<String> listaIdsCentros = ofereceDao.listarCentrosPorServico(idServico);
	    	if (listaIdsCentros.isEmpty()) {
	    		ofereceView.exibirMensagem("\nNenhuma associação cadastrada.\n");
	    	} else {
	    		ofereceView.exibirMensagem("\n====== { CENTROS AUTOMOTIVOS ASSOCIADOS AO SERVIÇO DE ID " + idServico + " } ======");
	    		for (String id : listaIdsCentros) {
	    			CentroAutomotivo ca = centroDao.buscarPorId(id);
	        		ofereceView.imprimirCentroOferece(ca);
	        	}
	    	}	
	    }
	    
	    public void visualizarServicosDoCentro()  {
	    	String idCentro = ofereceView.solicitaCentroOferece();
	    	ArrayList<String> listaIdsServicos = ofereceDao.listarServicosPorCentro(idCentro);
	    	if (listaIdsServicos.isEmpty()) {
	    		ofereceView.exibirMensagem("\nNenhuma associação cadastrada.\n");
	    	} else {
	    		ofereceView.exibirMensagem("\n====== { SERVIÇOS ASSOCIADOS AO CENTRO AUTOMOTIVO DE ID " + idCentro + " } ======");
	    		for (String id : listaIdsServicos) {
	    			Servico s = servicoDao.buscarPorId(id);
	        		ofereceView.imprimirServicoOferece(s);
	        	}
	    	}	
	    }
	    
	    public void deletarAssociacaoOferece() {
	    	String centro = ofereceView.centroOfereceARemover();
	    	String servico = ofereceView.servicoOfereceARemover();
	    	if (!centro.matches("C\\d{3}") && !servico.matches("S\\d{5}")) {
	            ofereceView.exibirMensagem("ID(s) com formato(s) inválido(s).");
	         } else {
	        	boolean deletou = ofereceDao.deletarAssociacao(centro, servico); 
	        	if (deletou) {
	        		ofereceView.exibirMensagem("Associação removida com sucesso!");
	        	} else {
	        		ofereceView.exibirMensagem("Associação inexistente.");
	        	}
	         }
	    }
}
