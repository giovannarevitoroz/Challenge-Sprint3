package sprint3.gerenciador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sprint3.model.dao.agendamento.AgendamentoDaoImpl;
import sprint3.model.dao.centroAutomotivo.CentroDaoImpl;
import sprint3.model.dao.servico.ServicoDaoImpl;
import sprint3.model.dao.veiculo.VeiculoDaoImpl;
import sprint3.model.vo.Agendamento;
import sprint3.model.vo.CentroAutomotivo;
import sprint3.model.vo.Servico;
import sprint3.model.vo.Veiculo;
import sprint3.view.AgendamentoView;

public class GerenciadorAgendamento {
    private AgendamentoDaoImpl agendamentoDao;
    private ServicoDaoImpl servicoDao;
    private CentroDaoImpl centroDao;
    private VeiculoDaoImpl veiculoDao;
    private AgendamentoView agendamentoView;
    
    
    
    public GerenciadorAgendamento(AgendamentoDaoImpl agendamentoDao, ServicoDaoImpl servicoDao, CentroDaoImpl centroDao,
			VeiculoDaoImpl veiculoDao, AgendamentoView agendamentoView) {
		this.agendamentoDao = agendamentoDao;
		this.servicoDao = servicoDao;
		this.centroDao = centroDao;
		this.veiculoDao = veiculoDao;
		this.agendamentoView = agendamentoView;
	}

	public void gerenciarAgendamento() {
		while (true) {
			agendamentoView.mostrarMenuAgendamento();
			String optAgendamentoString = agendamentoView.obterOpcao();
			try {
				int optAgendamento = Integer.parseInt(optAgendamentoString);
				if (optAgendamento >= 0 && optAgendamento <= 4) {
					if(optAgendamento == 0) {
						break;
					}
					switch (optAgendamento) {
					case 1:
						criarAgendamento();
						break;	
					case 2:
						visualizarAgendamentos();
						break;
					case 3:
						atualizarAgendamento();
						break;
					case 4:
						deletarAgendamento();
						break;
				} 
			} else {
				System.out.println("Escolha uma opção entre 0 e 4.");
			}
		} catch (NumberFormatException e) {
			System.out.println("Opção inválida.");
	}}
	}

    public void criarAgendamento() {
    	System.out.println("\n*-* INICIANDO AGENDAMENTO *-*\n");
    	Pattern regexHora = Pattern.compile("^(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9])(:([0-5][0-9]))?$");
    	Pattern regexData = Pattern.compile("^(0[1-9]|[12][0-9]|3[01])-(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)-\\d{4}$");
    	String idAgendamento = UUID.randomUUID().toString(); // cria ID aleatório
    	java.sql.Date dataSql = null;
    	String horario;
    	String descricaoAgendamento;
    	Servico servico;
    	CentroAutomotivo centro;
    	Veiculo veiculo;
    	while (true) {
        	if (veiculoDao.listar().isEmpty() || centroDao.listar().isEmpty() || servicoDao.listar().isEmpty()) {
        		agendamentoView.exibirMensagem("\nErro. É necessário que haja ao menos um usuário, centro automotivo e serviço para criar um agendamento.\n");
        		break;
        	}
    	while (true) {
         	String dataString = agendamentoView.solicitaDataAgendamento();
             Matcher matcher = regexData.matcher(dataString);
             if (!matcher.matches()) {
             	agendamentoView.exibirMensagem("Data com formato inválido.");
             	continue;
             } else {
            	 SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
            	 try {
					Date dataUtil = sdf.parse(dataString);
					dataSql = new java.sql.Date(dataUtil.getTime());
				} catch (ParseException e) {
					e.printStackTrace();
				}
                 break; 
             }
         }
    	while (true) {
         	horario = agendamentoView.solicitaHorarioAgendamento();
             Matcher matcher = regexHora.matcher(horario);
             if (!matcher.matches()) {
             	agendamentoView.exibirMensagem("Horário com formato inválido.");
             	continue;
             } else {
                 break; 
             }
         }
    	while (true) {
         	descricaoAgendamento = agendamentoView.solicitaDescricaoAgendamento();
         	if (descricaoAgendamento.isEmpty()) {
         		agendamentoView.exibirMensagem("Digite alguma descrição para o agendamento.");
         		continue;
         	} else {
         		break;
         	}
    	}
    	 while (true) {
          	String idServico = agendamentoView.solicitaServico();
              if (!idServico.matches("S\\d{5}")) {
                  agendamentoView.exibirMensagem("ID com formato inválido.");
                  continue;
              }
              servico = servicoDao.buscarPorId(idServico);
              if (servico == null) {
                  agendamentoView.exibirMensagem("O ID inserido não existe no sistema.");
                  continue;
              }
              break;
          }
    	 while (true) {
           	String idCentro = agendamentoView.solicitaCentro();
               if (!idCentro.matches("C\\d{3}")) {
                   agendamentoView.exibirMensagem("ID com formato inválido.");
                   continue;
               }
               centro = centroDao.buscarPorId(idCentro);
               if (centro == null) {
                   agendamentoView.exibirMensagem("O ID inserido não existe no sistema.");
                   continue;
               }
               break;
           }
    	 while (true) {
           	String placaVeiculo = agendamentoView.solicitaPlaca();
               if (!placaVeiculo.matches("^[A-Z]{3}\\d[A-Z]\\d{2}$")) {
                   agendamentoView.exibirMensagem("Placa com formato inválido.");
                   continue;
               }
               veiculo = veiculoDao.buscarPorPlaca(placaVeiculo);
               if (veiculo == null) {
                   agendamentoView.exibirMensagem("A placa inserida não existe no sistema.");
                   continue;
               }
               break;
           }
    	 Agendamento agendamento = new Agendamento(idAgendamento, dataSql, horario, descricaoAgendamento, centro , servico, veiculo);
    	 agendamentoDao.inserir(agendamento);
    	 agendamentoView.exibirMensagem("Agendamento criado com sucesso! O ID do agendamento é " + idAgendamento + ".");
    	 break;
    }}
    	
   
    public void atualizarAgendamento() {
    	String idAgendamento = agendamentoView.agendamentoAAtualizar();
    	while (true) {
    		 if (agendamentoDao.agendamentoExiste(idAgendamento) == false) {
                 agendamentoView.exibirMensagem("Nenhum agendamento encontrado com o ID inserido.");
                 break;
              }
    		agendamentoView.mostrarMenuAgendamentoAtualizacao();
    		String optAtString = agendamentoView.obterOpcao();
			try {
				int optAt = Integer.parseInt(optAtString);
				if (optAt >= 0 && optAt <= 6) {
					if(optAt == 0) {
						break;
					}
					switch (optAt) {
						case 1:
							String novaData = agendamentoView.solicitaDataAgendamento();
							java.sql.Date dataSqlNova = null;
							if (!novaData.matches("^(0[1-9]|[12][0-9]|3[01])-(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)-\\d{4}$")) {
				             	agendamentoView.exibirMensagem("Data com formato inválido.");
				             	continue;
				             } else {
				            	 SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
				            	 try {
									Date dataUtil = sdf.parse(novaData);
									dataSqlNova = new java.sql.Date(dataUtil.getTime());
								} catch (ParseException e) {
									e.printStackTrace();
							}
				          }
							agendamentoDao.atualizarDataAgendamento(idAgendamento, dataSqlNova);
							break;
						case 2:
							String novoHorario = agendamentoView.solicitaHorarioAgendamento();
							if (!novoHorario.matches("^(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9])(:([0-5][0-9]))?$")) {
				             	agendamentoView.exibirMensagem("Horário com formato inválido.");
				             	continue;
				             }
							agendamentoDao.atualizarHorarioAgendamento(idAgendamento, novoHorario);
							break;
						case 3:
							String novaDescricao = agendamentoView.solicitaDescricaoAgendamento();
							if (novaDescricao.isEmpty()) {
			             		agendamentoView.exibirMensagem("Digite alguma descrição para o agendamento.");
			             		continue;
			             	}
				            agendamentoDao.atualizarDescricaoAgendamento(idAgendamento, novaDescricao);
				            break;
						case 4:
							String novoServico = agendamentoView.solicitaServico();
							if (!novoServico.matches("S\\d{5}")) {
								agendamentoView.exibirMensagem("ID com formato inválido.");
							}
							if (servicoDao.servicoExiste(novoServico) == false) {
				                 agendamentoView.exibirMensagem("Nenhum serviço encontrado com o ID inserido.");
				              } else {
				            	  agendamentoDao.atualizarServico(idAgendamento, novoServico);
				              }
							break;
						case 5:
							String novoCentro = agendamentoView.solicitaCentro();
							if (!novoCentro.matches("C\\d{3}")) {
								agendamentoView.exibirMensagem("ID com formato inválido.");
							}
							if (centroDao.centroExiste(novoCentro) == false) {
				                 agendamentoView.exibirMensagem("Nenhum centro automotivo encontrado com o ID inserido.");
				              } else {
				            	  agendamentoDao.atualizarCentro(idAgendamento, novoCentro);
				              }
							break;
						case 6:
							String novoVeiculo = agendamentoView.solicitaPlaca();
							if (!novoVeiculo.matches("^[A-Z]{3}\\d[A-Z]\\d{2}$")) {
								agendamentoView.exibirMensagem("Placa com formato inválido.");
							}
							 else {
				            	  agendamentoDao.atualizarVeiculo(idAgendamento, novoVeiculo);
				              }
							
					}
				} else {
					System.out.println("Digite uma opção entre 0 e 6.");
				}
			}catch (NumberFormatException e) {
				System.out.println("Opção inválida.");			
    }
  }
}
			
    public void visualizarAgendamentos() {
    	ArrayList<Agendamento> listaAgendamentos = agendamentoDao.listar();
    	if (listaAgendamentos.isEmpty()) {
    		agendamentoView.exibirMensagem("\nNenhum agendamento registrado.\n");
    	}
    	for (Agendamento a : listaAgendamentos) {
    		agendamentoView.imprimirAgendamento(a);
    	}
    }
    
    public void deletarAgendamento() {
    	String idAgendamento = agendamentoView.agendamentoARemover();
        agendamentoDao.deletar(idAgendamento);   	
    }
    
    
}
