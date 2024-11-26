package sprint3.model.dao.servico;

import oracle.jdbc.pool.OracleDataSource;
import sprint3.model.dao.credenciais.Credenciais;
import sprint3.model.vo.Servico;

import java.sql.*;
import java.util.ArrayList;
public class ServicoDaoImpl implements ServicoDAO {

    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";

    public Connection conectar() throws SQLException {
        OracleDataSource ods = new OracleDataSource();
        ods.setURL(URL);
        ods.setUser(Credenciais.user);
        ods.setPassword(Credenciais.pwd);
        return ods.getConnection();
       
    }

    @Override
    public boolean inserir(Servico s)  {
        String sql = "INSERT INTO servico (id_servico, tipo_servico, descricao_servico, preco_servico, duracao) VALUES (?,?,?,?,?)";

        try (Connection conn = conectar();
        		PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getIdServico());
            ps.setString(2, s.getTipoServico());
            ps.setString(3, s.getDescricaoServico());
            ps.setDouble(4, s.getPrecoServico());
            ps.setInt(5, s.getDuracaoServico());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao inserir serviço:");
            e.printStackTrace();
            return false;
        } 
    }
    

    @Override
    public ArrayList<Servico> listar()  {
        ArrayList<Servico> servicos = new ArrayList<>();
        String sql = "SELECT * FROM servico";
        try(Connection conn = conectar();
        	PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery()) { 
            while (resultSet.next()) {
                String idServico = resultSet.getString("id_servico");
                String tipoServico = resultSet.getString("tipo_servico");
                String descricaoServico = resultSet.getString("descricao_servico");
                Double precoServico = resultSet.getDouble("preco_servico");
                int duracao = resultSet.getInt("duracao");

                Servico servico = new Servico(idServico, tipoServico, descricaoServico, precoServico, duracao);
                servicos.add(servico);
            }
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao listar serviços:");
            e.printStackTrace();
        }
        return servicos;
    }

    @Override
    public Servico buscarPorId(String id) {
        String sql = "SELECT * FROM servico WHERE id_servico = ?";
        Servico servico = null;
        try (Connection conn = conectar();
        		PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);  
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    String idServico = resultSet.getString("id_servico");
                    String tipoServico = resultSet.getString("tipo_servico");
                    String descricaoServico = resultSet.getString("descricao_servico");
                    double precoServico = resultSet.getDouble("preco_servico");
                    int duracao = resultSet.getInt("duracao");

                    servico = new Servico(idServico, tipoServico, descricaoServico, precoServico, duracao);
                } 
            }
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao obter serviço:");
            e.printStackTrace();
        } 
        return servico;
    }
    
    @Override
    public boolean servicoExiste(String idServico) {
    	String sqlExiste = "SELECT COUNT(*) FROM servico WHERE id_servico = ?";
    	try(Connection conn = conectar();
    			PreparedStatement ps = conn.prepareStatement(sqlExiste)) {
    		ps.setString(1, idServico);
    		try (ResultSet resultSet = ps.executeQuery()) {
    			if (resultSet.next()) {
    				return resultSet.getInt(1) > 0;
    			} else {
    				return false;
    			}
    		}
    	} catch (SQLException e) {
            System.err.println("Ocorreu um erro ao obter serviço:");
            e.printStackTrace();
            return false;
        } 
    }
    
    @Override
    public void atualizarTipo(String idServico, String tipo) {
    	if (servicoExiste(idServico)) {
    		String sql = "UPDATE servico SET tipo_servico = ? where id_servico = ?";
        	try(Connection conn = conectar();
        			PreparedStatement ps = conn.prepareStatement(sql)) {
        		ps.setString(1, tipo);
        		ps.setString(2, idServico);
        		ps.executeUpdate();
        		System.out.println("Tipo do serviço atualizado com sucesso!");
        	} catch (SQLException e) {
                System.err.println("Ocorreu um erro ao atualizar serviço:");
                e.printStackTrace();
            } 
    	} else {
    		System.out.println("ID do serviço não registrado no banco de dados.");
    	}
    	
    }
    
    @Override
    public void atualizarDescricao(String idServico, String descricao) {
    	if (servicoExiste(idServico)) {
    		String sql = "UPDATE servico SET descricao_servico = ? where id_servico = ?";
        	try(Connection conn = conectar();
        			PreparedStatement ps = conn.prepareStatement(sql)) {
        		ps.setString(1, descricao);
        		ps.setString(2, idServico);
        		ps.executeUpdate();
        		System.out.println("Descrição do serviço atualizada com sucesso!");
        	}  catch (SQLException e) {
                System.err.println("Ocorreu um erro ao atualizar serviço:");
                e.printStackTrace();
            } 
    	} else {
    		System.out.println("ID do serviço não registrado no banco de dados.");
    	}
    	
    }
    
    @Override
    public void atualizarPreco(String idServico, double preco) {
    	if (servicoExiste(idServico)) {
    		String sql = "UPDATE servico SET preco_servico = ? where id_servico = ?";
        	try(Connection conn = conectar();
        			PreparedStatement ps = conn.prepareStatement(sql)) {
        		ps.setDouble(1, preco);
        		ps.setString(2, idServico);
        		ps.executeUpdate();
        		System.out.println("Preço do serviço atualizado com sucesso!");
        	}  catch (SQLException e) {
                System.err.println("Ocorreu um erro ao atualizar serviço:");
                e.printStackTrace();
            } 
    	} else {
    		System.out.println("ID do serviço não registrado no banco de dados.");
    	}
    	
    }
    
    @Override
    public void atualizarDuracao(String idServico, int duracao) {
    	if (servicoExiste(idServico)) {
    		String sql = "UPDATE servico SET duracao = ? where id_servico = ?";
        	try(Connection conn = conectar();
        			PreparedStatement ps = conn.prepareStatement(sql)) {
        		ps.setInt(1, duracao);
        		ps.setString(2, idServico);
        		ps.executeUpdate();
        		System.out.println("Duração do serviço atualizada com sucesso!");
        	}  catch (SQLException e) {
                System.err.println("Ocorreu um erro ao atualizar serviço:");
                e.printStackTrace();
            } 
    	} else {
    		System.out.println("ID do serviço não registrado no banco de dados.");
    	}
    	
    }

    @Override
    public void deletar(String id) {
    	if (servicoExiste(id)) {
    		String sql = "DELETE FROM servico WHERE id_servico = ?";
            try (Connection conn = conectar();
            		PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, id);
                ps.executeUpdate();
                System.out.println("Serviço removido com sucesso!");
            }  catch (SQLException e) {
                System.err.println("Ocorreu um erro ao deletar serviço:");
                e.printStackTrace();
            } 
    	} else {
    		System.out.println("ID do serviço não registrado no banco de dados.");
    	}
        
    }

}
