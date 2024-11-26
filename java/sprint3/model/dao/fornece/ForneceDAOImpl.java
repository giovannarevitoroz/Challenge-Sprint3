package sprint3.model.dao.fornece;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.pool.OracleDataSource;
import sprint3.model.dao.credenciais.Credenciais;

public class ForneceDAOImpl implements ForneceDAO {
	 private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";

	    public Connection conectar() throws SQLException {
	        OracleDataSource ods = new OracleDataSource();
	        ods.setURL(URL);
	        ods.setUser(Credenciais.user);
	        ods.setPassword(Credenciais.pwd);
	        return ods.getConnection();
	    }

		@Override
		public boolean adicionarAssociacao(String id_peca, String id_servico) {
			String sql = "INSERT INTO fornece (peca_id_peca, servico_id_servico) VALUES (?, ?)";
			try (Connection conn = conectar();
					PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setString(1, id_peca);
	            ps.setString(2, id_servico);
	            int linhasAfetadas = ps.executeUpdate();
	            return linhasAfetadas > 0;
	        } catch (SQLException e) {
	            System.err.println("Ocorreu um erro ao inserir associação Fornece: a peça e o serviço já estão associados.");
	            return false;
	        } 
		}

		@Override
		public boolean deletarAssociacao(String id_peca, String id_servico)  {
			       	String sql = "DELETE FROM fornece WHERE peca_id_peca = ? AND servico_id_servico = ?";
			        try (Connection conn = conectar();
			        		PreparedStatement ps = conn.prepareStatement(sql)) {
			        	ps.setString(1, id_peca);
			            ps.setString(2, id_servico);
			            int linhasAfetadas = ps.executeUpdate();
			            return linhasAfetadas > 0;
			        } catch (SQLException e) {
			            System.err.println("Ocorreu um erro ao deletar associação Fornece:");
			            e.printStackTrace();
			            return false;
			        } 
			    }

		@Override
		public ArrayList<String> listarServicosPorPeca(String id_peca)  {
			ArrayList<String> servicos = new ArrayList<String>();
	        String sql = "SELECT servico_id_servico FROM fornece WHERE peca_id_peca = ?";
	        try (Connection conn = conectar();
	        		PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setString(1, id_peca);
	            try (ResultSet rs = ps.executeQuery()) {
	                while (rs.next()) {
	                	String idServico = rs.getString("servico_id_servico");
	                    servicos.add(idServico);
	                }
	            }
	        } catch (SQLException e) {
	            System.err.println("Ocorreu um erro ao listar associação serviços associados a peça:");
	            e.printStackTrace();
	        } 
	        return servicos;
		}

		@Override
		public ArrayList<String> listarPecasPorServico(String id_servico)  {
			ArrayList<String> pecas = new ArrayList<String>();
	        String sql = "SELECT peca_id_peca FROM fornece WHERE servico_id_servico = ?";
	        try (Connection conn = conectar();
	        		PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setString(1, id_servico);
	            try (ResultSet rs = ps.executeQuery()) {
	                while (rs.next()) {
	                	 String idPeca = rs.getString("peca_id_peca");
	                     pecas.add(idPeca);
	                }
	            }
	        } catch (SQLException e) {
	            System.err.println("Ocorreu um erro ao listar associação peças associadas a serviço:");
	            e.printStackTrace();
	        } 
	        return pecas;
	    }
	    
	
}
