package sprint3.model.dao.agendamento;

import oracle.jdbc.pool.OracleDataSource;
import sprint3.model.vo.Agendamento;
import sprint3.model.vo.CentroAutomotivo;
import sprint3.model.vo.Servico;
import sprint3.model.vo.Veiculo;
import sprint3.model.dao.centroAutomotivo.CentroDaoImpl;
import sprint3.model.dao.credenciais.Credenciais;
import sprint3.model.dao.servico.ServicoDaoImpl;
import sprint3.model.dao.veiculo.VeiculoDaoImpl;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AgendamentoDaoImpl implements AgendamentoDAO {
    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
    private CentroDaoImpl daoCentro = new CentroDaoImpl();
    private ServicoDaoImpl daoServico = new ServicoDaoImpl();
    private VeiculoDaoImpl daoVeiculo = new VeiculoDaoImpl();
    
    public AgendamentoDaoImpl() {
    	
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
    public boolean inserir(Agendamento a) {
        String sql = "INSERT INTO agendamento (id_agendamento, data_agendamento, horario_agendamento, descricao_agendamento, servico_id_servico, centro_automotivo_id_centro, veiculo_placa) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = conectar();
        		PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, a.getIdAgendamento());
            ps.setDate(2, a.getData());
            ps.setString(3, a.getHora());
            ps.setString(4, a.getDescricao());
            ps.setString(5, a.getServico().getIdServico());
            ps.setString(6, a.getCentro().getIdCentro());
            ps.setString(7, a.getVeiculo().getPlaca());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir agendamento:");
            e.printStackTrace();
            return false;
        }
    }

    // Lista todos os agendamentos
    @Override
    public ArrayList<Agendamento> listar() {
        ArrayList<Agendamento> agendamentos = new ArrayList<Agendamento>();
        String sql = "SELECT * FROM agendamento"; 

        try (Connection conn = conectar();
        	 PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet resultSet = ps.executeQuery()) {
            while (resultSet.next()) {
                String idAgendamento = resultSet.getString("id_agendamento");
                Date data = resultSet.getDate("data_agendamento");
                String hora = resultSet.getString("horario_agendamento");
                String descricao = resultSet.getString("descricao_agendamento");
                String idCentro = resultSet.getString("centro_automotivo_id_centro");
                String idServico = resultSet.getString("servico_id_servico");
                String placaVeiculo = resultSet.getString("veiculo_placa");
                
                CentroAutomotivo centro = daoCentro.buscarPorId(idCentro);
                Servico servico = daoServico.buscarPorId(idServico);
                Veiculo veiculo = daoVeiculo.buscarPorPlaca(placaVeiculo);
                
                Agendamento agendamento = new Agendamento(idAgendamento, data, hora, descricao, centro, servico, veiculo);
                agendamentos.add(agendamento);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar agendamentos:");
            e.printStackTrace();
        }
        return agendamentos;
    }

    // Busca agendamento por ID
    @Override
    public Agendamento buscarPorId(String id) {
        String sql = "SELECT * FROM agendamento WHERE id_agendamento = ?";
        Agendamento agendamento = null;

        try (Connection conn = conectar();
        		PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
            if (resultSet.next()) {
            	String idAgendamento = resultSet.getString("id_agendamento");
                Date data = resultSet.getDate("data_agendamento");
                String hora = resultSet.getString("horario_agendamento");
                String descricao = resultSet.getString("descricao_agendamento");
                String idCentro = resultSet.getString("centro_automotivo_id_centro");
                String idServico = resultSet.getString("servico_id_servico");
                String placaVeiculo = resultSet.getString("veiculo_placa");
                
                CentroAutomotivo centro = daoCentro.buscarPorId(idCentro);
                Servico servico = daoServico.buscarPorId(idServico);
                Veiculo veiculo = daoVeiculo.buscarPorPlaca(placaVeiculo);

                agendamento = new Agendamento(idAgendamento, data, hora, descricao, centro, servico, veiculo);
            }
          }
        }
        catch (SQLException e) {
            System.err.println("Erro ao buscar agendamento:");
            e.printStackTrace();
        }
        return agendamento;
    }
    
    // verifica se agendamento existe no banco de dados
    @Override
    public boolean agendamentoExiste(String idAgendamento) {
    	String sqlExiste = "SELECT COUNT(*) FROM agendamento WHERE id_agendamento = ?";
    	try(Connection conn = conectar();
    			PreparedStatement ps = conn.prepareStatement(sqlExiste)) {
    		ps.setString(1, idAgendamento);
    		try (ResultSet resultSet = ps.executeQuery()) {
    			if (resultSet.next()) {
    				return resultSet.getInt(1) > 0;
    			}
    		}
    	} catch (SQLException e) {
            System.err.println("Erro ao buscar agendamento:");
            e.printStackTrace();
            return false;
        }
    	return false;
    }
    
    // atualiza data de agendamento
    @Override
    public void atualizarDataAgendamento(String idAgendamento, Date data) {
    	if (agendamentoExiste(idAgendamento)) {
    		String sql = "UPDATE agendamento SET data_agendamento = ? where id_agendamento = ?";
        	try(Connection conn = conectar();
        			PreparedStatement ps = conn.prepareStatement(sql)) {
        		ps.setDate(1, data);
        		ps.setString(2, idAgendamento);
        		ps.executeUpdate();
        		System.out.println("Data do agendamento atualizada com sucesso!");
        	} catch (SQLException e) {
                System.err.println("Erro ao atualizar agendamento:");
                e.printStackTrace();
            }
    	} else {
    		System.out.println("ID do agendamento não registrado no banco de dados.");
    	}
    	
    }
    
    // atualiza o horario do agendamento
    @Override
    public void atualizarHorarioAgendamento(String idAgendamento, String hora) {
    	if (agendamentoExiste(idAgendamento)) {
    		String sql = "UPDATE agendamento SET horario_agendamento = ? where id_agendamento = ?";
        	try(Connection conn = conectar();
        			PreparedStatement ps = conn.prepareStatement(sql)) {
        		ps.setString(1, hora);
        		ps.setString(2, idAgendamento);
        		ps.executeUpdate();
        		System.out.println("Horário do agendamento atualizado com sucesso!");
        	} catch (SQLException e) {
                System.err.println("Erro ao atualizar agendamento:");
                e.printStackTrace();
            }
    	} else {
    		System.out.println("ID do agendamento não registrado no banco de dados.");
    	}
    	
    }
    
    // atualiza a descrição
    @Override
    public void atualizarDescricaoAgendamento(String idAgendamento, String descricao) {
    	if (agendamentoExiste(idAgendamento)) {
    		String sql = "UPDATE agendamento SET descricao_agendamento = ? where id_agendamento = ?";
        	try(Connection conn = conectar();
        			PreparedStatement ps = conn.prepareStatement(sql)) {
        		ps.setString(1, descricao);
        		ps.setString(2, idAgendamento);
        		ps.executeUpdate();
        		System.out.println("Descrição do agendamento atualizada com sucesso!");
        	} catch (SQLException e) {
                System.err.println("Erro ao atualizar agendamento:");
                e.printStackTrace();
            }
    	} else {
    		System.out.println("ID do agendamento não registrado no banco de dados.");
    	}
    	
    }
    
    // atualizar centro automotivo do agendamento
    @Override
    public void atualizarCentro(String idAgendamento, String idCentro) {
    	if(agendamentoExiste(idAgendamento) && daoCentro.centroExiste(idCentro)) {
    		String sql = "UPDATE agendamento SET centro_automotivo_id_centro = ? where id_agendamento = ?";
        	try(Connection conn = conectar();
        			PreparedStatement ps = conn.prepareStatement(sql)) {
        		ps.setString(1, idCentro);
        		ps.setString(2, idAgendamento);
        		ps.executeUpdate();
        		System.out.println("Centro do agendamento atualizado com sucesso!");
        	} catch (SQLException e) {
                System.err.println("Erro ao atualizar agendamento:");
                e.printStackTrace();
            }
    	} else {
    		System.out.println("ID do agendamento não registrado no banco de dados.");
    	}
    	
    }
    
    // atualiza o serviço 
    @Override
    public void atualizarServico(String idAgendamento, String idServico)  {
    	if(agendamentoExiste(idAgendamento) && daoServico.servicoExiste(idServico)) {
    		String sql = "UPDATE agendamento SET servico_id_servico = ? where id_agendamento = ?";
        	try(Connection conn = conectar();
        			PreparedStatement ps = conn.prepareStatement(sql)) {
        		ps.setString(1, idServico);
        		ps.setString(2, idAgendamento);
        		ps.executeUpdate();
        		System.out.println("Serviço do agendamento atualizado com sucesso!");
        	} catch (SQLException e) {
                System.err.println("Erro ao atualizar agendamento:");
                e.printStackTrace();
            }
    	} else {
    		System.out.println("ID do agendamento não registrado no banco de dados.");
    	}
    	
    }
    
    // atualiza o veiculo
    @Override
    public void atualizarVeiculo(String idAgendamento, String placa)  {
    	if(agendamentoExiste(idAgendamento) && daoVeiculo.veiculoExiste(placa)) {
    		String sql = "UPDATE agendamento SET veiculo_placa = ? where id_agendamento = ?";
        	try(Connection conn = conectar();
        			PreparedStatement ps = conn.prepareStatement(sql)) {
        		ps.setString(1, placa);
        		ps.setString(2, idAgendamento);
        		ps.executeUpdate();
        		System.out.println("Veículo do agendamento atualizado com sucesso!");
        	} catch (SQLException e) {
                System.err.println("Erro ao atualizar agendamento:");
                e.printStackTrace();
            }
    	} else {
    		System.out.println("ID do agendamento não registrado no banco de dados.");
    	}
    }

    // Deleta agendamento
    @Override
    public void deletar(String id) {
    	if (agendamentoExiste(id)) {
    		String sql = "DELETE FROM agendamento WHERE id_agendamento = ?";
            try (Connection conn = conectar();
            		PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, id);
                ps.executeUpdate();
                System.out.println("Agendamento removido com sucesso!");
            } 
            catch (SQLException e) {
                System.err.println("Erro ao deletar agendamento:");
                e.printStackTrace();
            }
    	} else {
    		System.out.println("ID do agendamento não registrado no banco de dados.");
    	}
    }
}
