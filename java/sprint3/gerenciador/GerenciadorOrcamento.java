package sprint3.gerenciador;

import java.util.ArrayList;
import java.util.UUID;

import sprint3.model.dao.diagnostico.DiagnosticoDaoImpl;
import sprint3.model.dao.orcamento.OrcamentoDaoImpl;
import sprint3.model.vo.Diagnostico;
import sprint3.model.vo.Orcamento;
import sprint3.view.OrcamentoView;

public class GerenciadorOrcamento {
	
	private DiagnosticoDaoImpl diagnosticoDao;
	private OrcamentoDaoImpl orcamentoDao;
	private OrcamentoView orcamentoView;
	private GerenciadorDiagnostico gDiagnostico;

	
	

	public GerenciadorOrcamento(DiagnosticoDaoImpl diagnosticoDao, OrcamentoDaoImpl orcamentoDao,
			OrcamentoView orcamentoView, GerenciadorDiagnostico gDiagnostico) {
		this.diagnosticoDao = diagnosticoDao;
		this.orcamentoDao = orcamentoDao;
		this.orcamentoView = orcamentoView;
		this.gDiagnostico = gDiagnostico;
	}

	public void gerenciarOrcamento() {
		while (true) {
			orcamentoView.mostrarMenuOrcamento();
			String optOrcamentoString = orcamentoView.obterOpcao();
			try {
				int optOrcamento = Integer.parseInt(optOrcamentoString);
				if (optOrcamento >= 0 && optOrcamento <= 4) {
					if(optOrcamento == 0) {
						break;
					}
					switch (optOrcamento) {
					case 1:
						criarOrcamento();
						break;	
					case 2:
						visualizarOrcamentos();
						break;
					case 3:
						atualizarStatusOrcamento();
						break;
					case 4:
						deletarOrcamento();
						break;
				} 
			} else {
				System.out.println("Escolha uma opção entre 0 e 4.");
			}
		} catch (NumberFormatException e) {
			System.out.println("Opção inválida.");
	}}
	}
	
	public void criarOrcamento() {
		String idDiagnostico = orcamentoView.solicitaDiagnostico();
		double valorTotal;
		String idOrcamento;
		String descricaoOrcamento;
		String statusOrcamento;
		while (true) {
			if (diagnosticoDao.diagnosticoExiste(idDiagnostico) == false) {
				orcamentoView.exibirMensagem("Nenhum diagnóstico existente com o ID informado.");
				break;
			}
			idOrcamento = UUID.randomUUID().toString();
			Diagnostico d = diagnosticoDao.buscarPorId(idDiagnostico);
			valorTotal = d.getServico().getPrecoServico();
			while (true) {
             	descricaoOrcamento = orcamentoView.solicitaDescricaoOrcamento();
             	if (descricaoOrcamento.isEmpty()) {
             		orcamentoView.exibirMensagem("Digite a descrição para o orçamento.");
             		continue;
             	} else {
             		break;
             	}
        	}
			while (true) {
        		statusOrcamento = orcamentoView.solicitaStatusOrcamento();
            	if ("Pendente".equals(statusOrcamento)) {
            		statusOrcamento = statusOrcamento.toUpperCase();
                    break;
                } else if ("Aprovado".equals(statusOrcamento)) {
                	statusOrcamento = statusOrcamento.toUpperCase();
                    break;
                }
                   else if ("Rejeitado".equals(statusOrcamento)) {
                    statusOrcamento = statusOrcamento.toUpperCase();
                    break;
                } else {
                	orcamentoView.exibirMensagem("Por favor, digite uma das opções informadas.");
                    continue;
                	}
            	}
			Orcamento orcamento = new Orcamento(idOrcamento, descricaoOrcamento, valorTotal, statusOrcamento);
			orcamentoDao.inserir(orcamento);
			gDiagnostico.adicionarOrcamentoNoDiagnostico(idDiagnostico, idOrcamento);
			orcamentoView.exibirMensagem("Orçamento criado com sucesso! O ID do orçamento é " + idOrcamento + ".");
			break;
		}	
	}
	
	 public void atualizarStatusOrcamento() {
	    	String idOrcamento = orcamentoView.orcamentoAAtualizar();
	    	while (true) {
	    		 if (orcamentoDao.orcamentoExiste(idOrcamento) == false) {
	                 orcamentoView.exibirMensagem("Nenhum orçamento encontrado com o ID inserido.");
	                 break;
	              }
	    		 String statusOrcamento;
	    		 while (true) {
	    			 statusOrcamento = orcamentoView.solicitaStatusOrcamento();
	             	if ("Pendente".equals(statusOrcamento)) {
	             		statusOrcamento = statusOrcamento.toUpperCase();
	                 } else if ("Aprovado".equals(statusOrcamento)) {
	                 	statusOrcamento = statusOrcamento.toUpperCase();
	                 }
	                    else if ("Rejeitado".equals(statusOrcamento)) {
	                     statusOrcamento = statusOrcamento.toUpperCase();
	                 } else {
	                 	orcamentoView.exibirMensagem("Por favor, digite uma das opções informadas.");
	                     continue;
	                 	}
	             	 diagnosticoDao.atualizarStatusDiagnostico(idOrcamento, statusOrcamento);
		    		 break;
	             	}
	    		
	    	}
	}
				
	    public void visualizarOrcamentos() {
	    	ArrayList<Orcamento> listaOrcamentos = orcamentoDao.listar();
	    	if (listaOrcamentos.isEmpty()) {
	    		orcamentoView.exibirMensagem("\nNenhum orçamento registrado.\n");
	    	}
	    	for (Orcamento o : listaOrcamentos) {
	    		orcamentoView.imprimirOrcamento(o);
	    	}
	    }
	    
	    public void deletarOrcamento() {
	    	String idOrcamento = orcamentoView.orcamentoARemover();
	        orcamentoDao.deletar(idOrcamento);   	
	    }

}
