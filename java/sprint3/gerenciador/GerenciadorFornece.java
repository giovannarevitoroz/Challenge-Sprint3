package sprint3.gerenciador;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sprint3.model.dao.fornece.ForneceDAOImpl;
import sprint3.model.dao.peca.PecaDaoImpl;
import sprint3.model.dao.servico.ServicoDaoImpl;
import sprint3.model.vo.Peca;
import sprint3.model.vo.Servico;
import sprint3.view.ForneceView;

public class GerenciadorFornece {
	
	private ForneceView forneceView;
	private PecaDaoImpl pecaDao;
	private ServicoDaoImpl servicoDao;
	private ForneceDAOImpl forneceDao;
	
	public GerenciadorFornece(ForneceView forneceView, PecaDaoImpl pecaDao, ServicoDaoImpl servicoDao, ForneceDAOImpl forneceDao) {
		this.forneceView = forneceView;
		this.pecaDao = pecaDao;
		this.servicoDao = servicoDao;
		this.forneceDao = forneceDao;
	}

	public void gerenciarFornece() {
		while (true) {
			forneceView.mostrarMenuFornece();
			String optForneceString = forneceView.obterOpcao();
			try {
				int optFornece = Integer.parseInt(optForneceString);
				if (optFornece >= 0 && optFornece <= 4) {
					if(optFornece == 0) {
						break;
					}
					switch (optFornece) {
					case 1:
						criarAssociacaoFornece();
						break;	
					case 2:
						visualizarPecasDoServico();;
						break;
					case 3:
						visualizarServicosDaPeca();
						break;
					case 4:
						deletarAssociacaoFornece();
						break;
				} 
			} else {
				System.out.println("Escolha uma opção entre 0 e 4.");
			}
		} catch (NumberFormatException e) {
			System.out.println("Opção inválida.");
	}}
	}
	
	 public void criarAssociacaoFornece() {
	    	Pattern patternIdPeca = Pattern.compile("P\\d{5}");
	    	Pattern patternIdServico = Pattern.compile("S\\d{5}");
	    	String idPeca;
	    	String idServico;
	    	while (true) {
	    		if (pecaDao.listar().isEmpty() || servicoDao.listar().isEmpty()) {
	    			forneceView.exibirMensagem("Erro. É necessário ao menos uma peça e um serviço para estabelecer uma relação.");
	    			break;
	    		}
	    	System.out.println("\n*-* CADASTRO ASSOCIAÇÃO *-*");
	        while (true) {
	        	idPeca = forneceView.solicitaPecaFornece();
	            Matcher matcher = patternIdPeca.matcher(idPeca);
	            if (!matcher.matches()) {
	            	forneceView.exibirMensagem("ID com formato inválido.");
	            	continue;
	            } 
	            if (!pecaDao.pecaExiste(idPeca)) {
	            	forneceView.exibirMensagem("Peça não cadastrada.");
	            	continue;
	            }
	            break;
	            }
	        while (true) {
	        	idServico = forneceView.solicitaServicoFornece();
	            Matcher matcher = patternIdServico.matcher(idServico);
	            if (!matcher.matches()) {
	            	forneceView.exibirMensagem("ID com formato inválido.");
	            	continue;
	            } 
	            if (!servicoDao.servicoExiste(idServico)) {
	            	forneceView.exibirMensagem("Serviço não cadastrado.");
	            	continue;
	            }
	            break;
	            }
	        boolean inseriu = forneceDao.adicionarAssociacao(idPeca, idServico);
	        if (inseriu) {
	        	forneceView.exibirMensagem("\nAssociação cadastrada com sucesso!");
	        }
	        break;
	        } }
	
				    
	    public void visualizarPecasDoServico()  {
	    	String idServico = forneceView.solicitaServicoFornece();
	    	ArrayList<String> listaIdsPecas = forneceDao.listarPecasPorServico(idServico);
	    	if (listaIdsPecas.isEmpty()) {
	    		forneceView.exibirMensagem("\nNenhuma associação cadastrada.\n");
	    	} else {
	    		forneceView.exibirMensagem("\n====== { PEÇAS ASSOCIADAS AO SERVIÇO DE ID " + idServico + " } ======");
	    		for (String id : listaIdsPecas) {
	    			Peca p = pecaDao.buscarPorId(id);
	        		forneceView.imprimirPecaFornece(p);
	        	}
	    	}	
	    }
	    
	    public void visualizarServicosDaPeca()  {
	    	String idPeca = forneceView.solicitaPecaFornece();
	    	ArrayList<String> listaIdsServicos = forneceDao.listarServicosPorPeca(idPeca);
	    	if (listaIdsServicos.isEmpty()) {
	    		forneceView.exibirMensagem("\nNenhuma associação cadastrada.\n");
	    	} else {
	    		forneceView.exibirMensagem("\n====== { SERVIÇOS ASSOCIADOS A PEÇA DE ID " + idPeca + " } ======");
	    		for (String id : listaIdsServicos) {
	    			Servico s = servicoDao.buscarPorId(id);
	        		forneceView.imprimirServicoFornece(s);
	        	}
	    	}	
	    }
	    
	    public void deletarAssociacaoFornece() {
	    	String peca = forneceView.pecaForneceARemover();
	    	String servico = forneceView.servicoForneceARemover();
	    	if (!peca.matches("P\\d{5}") && !servico.matches("S\\d{5}")) {
	            forneceView.exibirMensagem("ID(s) com formato(s) inválido(s).");
	         } else {
	        	boolean deletou = forneceDao.deletarAssociacao(peca, servico); 
	        	if (deletou) {
	        		forneceView.exibirMensagem("Associação removida com sucesso!");
	        	} else {
	        		forneceView.exibirMensagem("Associação inexistente.");
	        	}
	         }
	    }
}
