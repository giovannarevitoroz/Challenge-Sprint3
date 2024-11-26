package sprint3.gerenciador;
 
import java.util.ArrayList;
import java.util.UUID;
import sprint3.model.dao.diagnostico.DiagnosticoDaoImpl;
import sprint3.model.dao.orcamento.OrcamentoDaoImpl;
import sprint3.model.dao.servico.ServicoDaoImpl;
import sprint3.model.dao.veiculo.VeiculoDaoImpl;
import sprint3.model.vo.Diagnostico;
import sprint3.model.vo.Servico;
import sprint3.model.vo.Veiculo;
import sprint3.view.DiagnosticoView;

public class GerenciadorDiagnostico {
	
	private DiagnosticoView diagnosticoView;
	private DiagnosticoDaoImpl diagnosticoDao; 
	private OrcamentoDaoImpl orcamentoDao;
	private VeiculoDaoImpl veiculoDao;
	private ServicoDaoImpl servicoDao;
	
	public GerenciadorDiagnostico(DiagnosticoView diagnosticoView, DiagnosticoDaoImpl diagnosticoDao,
			OrcamentoDaoImpl orcamentoDao, VeiculoDaoImpl veiculoDao, ServicoDaoImpl servicoDao) {
		this.diagnosticoView = diagnosticoView;
		this.diagnosticoDao = diagnosticoDao;
		this.orcamentoDao = orcamentoDao;
		this.veiculoDao = veiculoDao;
		this.servicoDao = servicoDao;
	}

	public void gerenciarDiagnostico() {
		while (true) {
			diagnosticoView.mostrarMenuDiagnostico();
			String optDiagnosticoString = diagnosticoView.obterOpcao();
			try {
				int optDiagnostico = Integer.parseInt(optDiagnosticoString);
				if (optDiagnostico >= 0 && optDiagnostico <= 4) {
					if(optDiagnostico == 0) {
						break;
					}
					switch (optDiagnostico) {
					case 1:
						criarDiagnostico();
						break;	
					case 2:
						visualizarDiagnosticos();
						break;
					case 3:
						atualizarStatusDiagnostico();
						break;
					case 4:
						deletarDiagnostico();
						break;
				} 
			} else {
				System.out.println("Escolha uma opção entre 0 e 4.");
			}
		} catch (NumberFormatException e) {
			System.out.println("Opção inválida.");
	}}
	}
	
	public void criarDiagnostico() {
    	System.out.println("\n*-* INICIANDO DIAGNÓSTICO *-*\n");
    	String idDiagnostico = UUID.randomUUID().toString(); // cria ID aleatório
    	String categoria;
    	String descricaoSintomas;
    	String solucao;
    	String statusDiagnostico;
    	Servico servico;
    	Veiculo veiculo;
    	while (true) {
        	if (veiculoDao.listar().isEmpty() || servicoDao.listar().isEmpty()) {
        		diagnosticoView.exibirMensagem("\nErro. É necessário que haja ao menos um usuário e um serviço para criar um diagnóstico.\n");
        		break;
        	}
        	while (true) {
             	descricaoSintomas = diagnosticoView.solicitaDescricaoSintomas();
             	if (descricaoSintomas.isEmpty()) {
             		diagnosticoView.exibirMensagem("Digite alguma descrição para os sintomas.");
             		continue;
             	} else {
             		break;
             	}
        	}
        	while (true) {
             	categoria = diagnosticoView.solicitaCategoria();
             	if (categoria.isEmpty()) {
             		diagnosticoView.exibirMensagem("Digite alguma categoria para o problema.");
             		continue;
             	} else {
             		break;
             	}
        	}
        	while (true) {
             	solucao = diagnosticoView.solicitaSolucao();
             	if (solucao.isEmpty()) {
             		diagnosticoView.exibirMensagem("Digite a solução para o problema identificado.");
             		continue;
             	} else {
             		break;
             	}
        	}
        	while (true) {
        		statusDiagnostico = diagnosticoView.solicitaStatusDiagnostico();
            	if ("Em Análise".equals(statusDiagnostico)) {
            		statusDiagnostico = statusDiagnostico.toUpperCase();
                    break;
                } else if ("Analisado".equals(statusDiagnostico)) {
                	statusDiagnostico = statusDiagnostico.toUpperCase();
                    break;
                } else {
                    System.out.println("Por favor, digite uma das opções informadas.");
                    continue;
                	}
            	}  
    	while (true) {
    		String placaVeiculo = diagnosticoView.solicitaPlaca();
            if (!placaVeiculo.matches("^[A-Z]{3}\\d[A-Z]\\d{2}$")) {
                diagnosticoView.exibirMensagem("Placa com formato inválido.");
                continue;
            }
            veiculo = veiculoDao.buscarPorPlaca(placaVeiculo);
            if (veiculo == null) {
                diagnosticoView.exibirMensagem("A placa inserida não existe no sistema.");
                continue;
            }
            break;
         }
    	while (true) {
    		String idServico = diagnosticoView.solicitaServico();
            if (!idServico.matches("S\\d{5}")) {
                diagnosticoView.exibirMensagem("ID com formato inválido.");
                continue;
            }
            servico = servicoDao.buscarPorId(idServico);
            if (servico == null) {
                diagnosticoView.exibirMensagem("O ID inserido não existe no sistema.");
                continue;
            }
            break;
         }
    	 Diagnostico diagnostico = new Diagnostico(idDiagnostico, veiculo, descricaoSintomas, servico, categoria , statusDiagnostico, solucao);
    	 diagnosticoDao.inserir(diagnostico);
    	 diagnosticoView.exibirMensagem("Diagnóstico criado com sucesso! O ID do diagnóstico é " + idDiagnostico + ".");
    	 break;
    }}
    	
   
    public void atualizarStatusDiagnostico() {
    	String idAgendamento = diagnosticoView.diagnosticoAAtualizar();
    	while (true) {
    		 if (diagnosticoDao.diagnosticoExiste(idAgendamento) == false) {
                 diagnosticoView.exibirMensagem("Nenhum diagnóstico encontrado com o ID inserido.");
                 break;
              }
    		 String statusDiagnosticoNovo;
    		 while (true) {
         		 statusDiagnosticoNovo = diagnosticoView.solicitaStatusDiagnostico();
             	if ("Em Análise".equals(statusDiagnosticoNovo)) {
             		statusDiagnosticoNovo = statusDiagnosticoNovo.toUpperCase();
                     break;
                 } else if ("Analisado".equals(statusDiagnosticoNovo)) {
                	 statusDiagnosticoNovo = statusDiagnosticoNovo.toUpperCase();
                     break;
                 } else {
                     System.out.println("Por favor, digite uma das opções informadas.");
                     continue;
                 	}
             	}
    		 diagnosticoDao.atualizarStatusDiagnostico(idAgendamento, statusDiagnosticoNovo);
    		 break;
    	}

    }
    
    public void adicionarOrcamentoNoDiagnostico(String idDiagnostico, String idOrcamento) {
    	if (diagnosticoDao.diagnosticoExiste(idDiagnostico) && orcamentoDao.orcamentoExiste(idOrcamento)) {
    		diagnosticoDao.inserirOrcamentoNoDiagnostico(idDiagnostico, idOrcamento);
    	} else {
    		diagnosticoView.exibirMensagem("Ocorreu um erro ao inserir o orçamento no diagnóstico.");
    	}
    }
			
    public void visualizarDiagnosticos() {
    	ArrayList<Diagnostico> listaDiagnosticos = diagnosticoDao.listar();
    	if (listaDiagnosticos.isEmpty()) {
    		diagnosticoView.exibirMensagem("\nNenhum diagnóstico registrado.\n");
    	}
    	for (Diagnostico d : listaDiagnosticos) {
    		diagnosticoView.imprimirDiagnostico(d);
    	}
    }
    
    public void deletarDiagnostico() {
    	String idDiagnostico = diagnosticoView.diagnosticoARemover();
        diagnosticoDao.deletar(idDiagnostico);   	
    }
}
