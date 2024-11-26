package sprint3.model.dao.diagnostico;

import oracle.jdbc.pool.OracleDataSource;
import sprint3.model.vo.Diagnostico;
import sprint3.model.vo.Orcamento;
import sprint3.model.vo.Servico;
import sprint3.model.vo.Veiculo;
import sprint3.model.dao.credenciais.Credenciais;
import sprint3.model.dao.orcamento.OrcamentoDaoImpl;
import sprint3.model.dao.servico.ServicoDaoImpl;
import sprint3.model.dao.veiculo.VeiculoDaoImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DiagnosticoDaoImpl implements DiagnosticoDAO {
    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
    private VeiculoDaoImpl daoVeiculo = new VeiculoDaoImpl();
    private ServicoDaoImpl daoServico = new ServicoDaoImpl();
    private OrcamentoDaoImpl daoOrcamento = new OrcamentoDaoImpl();

    public DiagnosticoDaoImpl() {
    	
    }
    
    public Connection conectar() throws SQLException {
        OracleDataSource ods = new OracleDataSource();
        ods.setURL(URL);
        ods.setUser(Credenciais.user);
        ods.setPassword(Credenciais.pwd);
        return ods.getConnection();
    }

    // Insere agendamento
    @Override
    public boolean inserir(Diagnostico d) {
        String sql = "INSERT INTO diagnostico (id_diagnostico, descricao_sintomas, categoria_problema, solucao, status_diagnostico, veiculo_placa, servico_id_servico) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = conectar();
        		PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, d.getIdDiagnostico());
            ps.setString(2, d.getDescricaoSintomas());
            ps.setString(3, d.getCategoria());
            ps.setString(4, d.getSolucao());
            ps.setString(5, d.getStatus());
            ps.setString(6, String.valueOf(d.getVeiculo().getPlaca()));
            ps.setString(7, String.valueOf(d.getServico().getIdServico()));

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao inserir diagnóstico:");
            e.printStackTrace();
            return false;
        } 
    }

    // Lista todos os agendamentos
    @Override
    public ArrayList<Diagnostico> listar() {
        ArrayList<Diagnostico> diagnosticos = new ArrayList<Diagnostico>();
        String sql = "SELECT * FROM diagnostico"; 
        try (Connection conn = conectar();
        		PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet resultSet = ps.executeQuery()) {
            while (resultSet.next()) {
                String idDiagnostico = resultSet.getString("id_diagnostico");
                String descricao = resultSet.getString("descricao_sintomas");
                String categoria = resultSet.getString("categoria_problema");
                String status = resultSet.getString("status_diagnostico");
                String idOrcamento = resultSet.getString("orcamento_id_orcamento");
                String idServico = resultSet.getString("servico_id_servico");
                String veiculoPlaca = resultSet.getString("veiculo_placa");  
                String solucao = resultSet.getString("solucao");
                Veiculo veiculo = daoVeiculo.buscarPorPlaca(veiculoPlaca);
                Servico servico = daoServico.buscarPorId(idServico);
                Orcamento orcamento = daoOrcamento.buscarPorId(idOrcamento); 
                Diagnostico diagnostico = new Diagnostico(idDiagnostico, veiculo, descricao, servico, categoria, status, orcamento, solucao);
                diagnosticos.add(diagnostico);
            }
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao listar diagnósticos:");
            e.printStackTrace();
        } 
        return diagnosticos;
    }

    @Override
    public Diagnostico buscarPorId(String id) {
        String sql = "SELECT * FROM diagnostico WHERE id_diagnostico = ?";
        Diagnostico diagnostico = null;

        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    String idDiagnostico = resultSet.getString("id_diagnostico");
                    String descricao = resultSet.getString("descricao_sintomas");
                    String categoria = resultSet.getString("categoria_problema");
                    String status = resultSet.getString("status_diagnostico");
                    String idOrcamento = resultSet.getString("orcamento_id_orcamento");
                    String idServico = resultSet.getString("servico_id_servico");
                    String veiculoPlaca = resultSet.getString("veiculo_placa");
                    String solucao = resultSet.getString("solucao");

                    Veiculo veiculo = daoVeiculo.buscarPorPlaca(veiculoPlaca);
                    Servico servico = daoServico.buscarPorId(idServico);
                    Orcamento orcamento = daoOrcamento.buscarPorId(idOrcamento);

                    diagnostico = new Diagnostico(idDiagnostico, veiculo, descricao, servico, categoria, status, orcamento, solucao);
                }
            }
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao buscar diagnóstico:");
            e.printStackTrace();
        }

        return diagnostico;
    }
    
    @Override
    public boolean diagnosticoExiste(String idDiagnostico) {
    	String sqlExiste = "SELECT COUNT(*) FROM diagnostico WHERE id_diagnostico = ?";
    	try(Connection conn = conectar();
    			PreparedStatement ps = conn.prepareStatement(sqlExiste)) {
    		ps.setString(1, idDiagnostico);
    		try (ResultSet resultSet = ps.executeQuery()) {
    			if (resultSet.next()) {
    				return resultSet.getInt(1) > 0;
    			} else {
    				return false;
    			}
    		}
    	} catch (SQLException e) {
            System.err.println("Ocorreu um erro ao buscar diagnóstico:");
            e.printStackTrace();
            return false;
        } 
    }
    
    @Override
    public void atualizarStatusDiagnostico(String idDiagnostico, String status) {
    	if (diagnosticoExiste(idDiagnostico)) {
    		String sql = "UPDATE diagnostico SET status_diagnostico = ? where id_diagnostico = ?";
        	try(Connection conn = conectar();
        			PreparedStatement ps = conn.prepareStatement(sql)) {
        		ps.setString(1, status);
        		ps.setString(2, idDiagnostico);
        		ps.executeUpdate();
        		System.out.println("Status do diagnóstico atualizado com sucesso!");
        	} catch (SQLException e) {
                System.err.println("Ocorreu um erro ao atualizar diagnóstico:");
                e.printStackTrace();
            } 
    	} else {
    		System.out.println("ID do diagnóstico não registrado no banco de dados.");
    	}
    	
    }
    
    public void inserirOrcamentoNoDiagnostico(String idDiagnostico, String idOrcamento) {
    	 
    		String sql = "UPDATE diagnostico SET orcamento_id_orcamento = ? where id_diagnostico = ?";
        	try(Connection conn = conectar();
        			PreparedStatement ps = conn.prepareStatement(sql)) {
        		ps.setString(1, idOrcamento);
        		ps.setString(2, idDiagnostico);
        		ps.executeUpdate();
        		System.out.println("Orçamento adicionado com sucesso!");
        	} catch (SQLException e) {
                System.err.println("Ocorreu um erro ao atualizar diagnóstico:");
                e.printStackTrace();
            } 
    	} 

	@Override
	public void deletar(String id)  {
		if (diagnosticoExiste(id)) {
			String sql = "DELETE FROM diagnostico WHERE id_diagnostico = ?";
	        try (Connection conn = conectar();
	        		PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setString(1, id);
	            ps.executeUpdate();
	            System.out.println("Diagnóstico removido com sucesso!");
	        } catch (SQLException e) {
	            System.err.println("Ocorreu um erro ao deletar diagnóstico:");
	            e.printStackTrace();
	        } 
		} else {
			System.out.println("ID do diagnóstico não registrado no banco de dados.");
		}
		
	}

}