package sprint3.main;

import sprint3.gerenciador.*;
import sprint3.model.dao.agendamento.AgendamentoDaoImpl;
import sprint3.model.dao.cargo.CargoDaoImpl;
import sprint3.model.dao.centroAutomotivo.CentroDaoImpl;
import sprint3.model.dao.diagnostico.DiagnosticoDaoImpl;
import sprint3.model.dao.fornece.ForneceDAOImpl;
import sprint3.model.dao.funcionario.FuncionarioDaoImpl;
import sprint3.model.dao.oferece.OfereceDAOImpl;
import sprint3.model.dao.orcamento.OrcamentoDaoImpl;
import sprint3.model.dao.peca.PecaDaoImpl;
import sprint3.model.dao.servico.ServicoDaoImpl;
import sprint3.model.dao.usuario.UsuarioDaoImpl;
import sprint3.model.dao.veiculo.VeiculoDaoImpl;
import sprint3.view.AgendamentoView;
import sprint3.view.CargoView;
import sprint3.view.CentroView;
import sprint3.view.MenuPrincipalView;
import sprint3.view.OfereceView;
import sprint3.view.DiagnosticoView;
import sprint3.view.ForneceView;
import sprint3.view.FuncionarioView;
import sprint3.view.OrcamentoView;
import sprint3.view.PecaView;
import sprint3.view.ServicoView;
import sprint3.view.UsuarioView;
import sprint3.view.VeiculoView;


public class Main {
    public static void main(String[] args)  {

    	// criando Dao
    	VeiculoDaoImpl daoVeiculo = new VeiculoDaoImpl();
    	UsuarioDaoImpl daoUsuario = new UsuarioDaoImpl();
    	PecaDaoImpl pecaDao = new PecaDaoImpl();
    	ServicoDaoImpl servicoDao = new ServicoDaoImpl();
    	CargoDaoImpl cargoDao = new CargoDaoImpl();
    	CentroDaoImpl centroDao = new CentroDaoImpl();
    	AgendamentoDaoImpl agendamentoDao = new AgendamentoDaoImpl();
    	FuncionarioDaoImpl funcionarioDao = new FuncionarioDaoImpl();
    	DiagnosticoDaoImpl diagnosticoDao = new DiagnosticoDaoImpl(); 
    	OrcamentoDaoImpl orcamentoDao = new OrcamentoDaoImpl();
    	ForneceDAOImpl forneceDao = new ForneceDAOImpl();
    	OfereceDAOImpl ofereceDao = new OfereceDAOImpl();
    	
    	// criando View
    	VeiculoView veiculoView = new VeiculoView();
    	UsuarioView usuarioView = new UsuarioView();
    	PecaView pecaView = new PecaView();
    	ServicoView servicoView = new ServicoView();
    	CargoView cargoView = new CargoView();
    	CentroView centroView = new CentroView();
    	AgendamentoView agendamentoView = new AgendamentoView();
    	FuncionarioView funcionarioView = new FuncionarioView();
    	DiagnosticoView diagnosticoView = new DiagnosticoView();
    	OrcamentoView orcamentoView = new OrcamentoView();
    	ForneceView forneceView = new ForneceView();
    	OfereceView ofereceView = new OfereceView();
        
    	//criando Gerenciadores/Controllers
    	GerenciadorUsuario gu = new GerenciadorUsuario(usuarioView, daoUsuario);
    	GerenciadorVeiculo gv = new GerenciadorVeiculo(daoVeiculo, daoUsuario, veiculoView);
    	GerenciadorPeca gp = new GerenciadorPeca(pecaView, pecaDao);
    	GerenciadorServico gs = new GerenciadorServico(servicoView, servicoDao);
    	GerenciadorCargo gc = new GerenciadorCargo(cargoView, cargoDao);
    	GerenciadorCentro gac = new GerenciadorCentro(centroView, centroDao);
    	GerenciadorAgendamento gaa = new GerenciadorAgendamento(agendamentoDao, servicoDao, centroDao, daoVeiculo, agendamentoView);
    	GerenciadorFuncionario gf = new GerenciadorFuncionario(funcionarioView, funcionarioDao, centroDao, cargoDao);
    	GerenciadorDiagnostico gd = new GerenciadorDiagnostico(diagnosticoView, diagnosticoDao, orcamentoDao, daoVeiculo, servicoDao);
    	GerenciadorOrcamento go = new GerenciadorOrcamento(diagnosticoDao, orcamentoDao, orcamentoView, gd);
    	GerenciadorFornece gfo = new GerenciadorFornece(forneceView, pecaDao, servicoDao, forneceDao);
    	GerenciadorOferece goo = new GerenciadorOferece(ofereceView, centroDao, servicoDao, ofereceDao);
    	// Menu principal
    	MenuPrincipalView cm = new MenuPrincipalView(gu, gv, gp, gs, gc, gac, gaa, gf, gd, go, gfo, goo);
    	
    	// todos os métodos estão nos menus e submenus, com seus respectivos gerenciadores.
    	cm.start();

        

    }

}