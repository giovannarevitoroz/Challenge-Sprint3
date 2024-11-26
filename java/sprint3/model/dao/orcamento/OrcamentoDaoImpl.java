package sprint3.model.dao.orcamento;

import oracle.jdbc.pool.OracleDataSource;
import sprint3.model.dao.credenciais.Credenciais;
import sprint3.model.vo.Orcamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrcamentoDaoImpl implements OrcamentoDAO {
    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
    
    public OrcamentoDaoImpl() {
    	
    }

    public Connection conectar() throws SQLException {
        OracleDataSource ods = new OracleDataSource();
        ods.setURL(URL);
        ods.setUser(Credenciais.user);
        ods.setPassword(Credenciais.pwd);
        return ods.getConnection();
    }

    @Override
    public boolean inserir(Orcamento orcamento) {
        String sql = "INSERT INTO orcamento (id_orcamento, descricao_orcamento, valor_total, status_orcamento) VALUES (?, ?, ?, ?)";

        try (Connection conn = conectar();
        		PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, orcamento.getIdOrcamento());
            ps.setString(2, orcamento.getDescricaoOrcamento());
            ps.setDouble(3, orcamento.getValorTotal());
            ps.setString(4, orcamento.getStatusOrcamento());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao inserir orçamento:");
            e.printStackTrace();
            return false;
        } 
    }

    @Override
    public ArrayList<Orcamento> listar() {
        ArrayList<Orcamento> orcamentos = new ArrayList<Orcamento>();
        String sql = "SELECT * FROM orcamento";

        try (Connection conn = conectar();
        		PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet resultSet = ps.executeQuery()){
            while (resultSet.next()) {
                String idOrcamento = resultSet.getString("id_orcamento");
                String descricao = resultSet.getString("descricao_orcamento");
                double valorTotal = resultSet.getDouble("valor_total");
                String status = resultSet.getString("status_orcamento");

                Orcamento orcamento = new Orcamento(idOrcamento, descricao, valorTotal, status);
                orcamentos.add(orcamento);
            }
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao listar orçamentos:");
            e.printStackTrace();
        } 
        return orcamentos;
    }

    @Override
    public Orcamento buscarPorId(String id)  {
        String sql = "SELECT * FROM orcamento WHERE id_orcamento = ?";
        Orcamento orcamento = null;
        try (Connection conn = conectar();
        		PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                String idOrcamento = rs.getString("id_orcamento");
                String descricao = rs.getString("descricao_orcamento");
                double valorTotal = rs.getDouble("valor_total");
                String status = rs.getString("status_orcamento");

                orcamento = new Orcamento(idOrcamento, descricao, valorTotal, status);
            }
          }
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao buscar orçamento:");
            e.printStackTrace();
        } 
        return orcamento;
    }
    
    @Override
    public boolean orcamentoExiste(String idOrcamento)  {
    	String sqlExiste = "SELECT COUNT(*) FROM orcamento WHERE id_orcamento = ?";
    	try(Connection conn = conectar();
    			PreparedStatement ps = conn.prepareStatement(sqlExiste)) {
    		ps.setString(1, idOrcamento);
    		try (ResultSet resultSet = ps.executeQuery()) {
    			if (resultSet.next()) {
    				return resultSet.getInt(1) > 0;
    			} else {
    				return false;
    			}
    		}
    	} catch (SQLException e) {
            System.err.println("Ocorreu um erro ao buscar orçamento:");
            e.printStackTrace();
            return false;
        } 
    }

    @Override
    public void atualizarStatus(String idOrcamento, String status) {
    	if (orcamentoExiste(idOrcamento)) {
    		String sql = "UPDATE orcamento SET status_orcamento = ? WHERE id_orcamento = ?";
            try (Connection conn = conectar();
            		PreparedStatement ps = conn.prepareStatement(sql)) {
            	ps.setString(1, status);
                ps.setString(2, idOrcamento);
                ps.executeUpdate();
                System.out.println("Status do orçamento atualizado com sucesso!");
            } catch (SQLException e) {
                System.err.println("Ocorreu um erro ao atualizar orçamento:");
                e.printStackTrace();
            } 
    	} else {
    		System.out.println("ID do orçamento não registrado no banco de dados.");
    	}
        
    }

    @Override
    public void deletar(String id) {
    	if (orcamentoExiste(id)) {
    		String sql = "DELETE FROM orcamento WHERE id_orcamento = ?";
            try (Connection conn = conectar();
            		PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, id);
                ps.executeUpdate();
                System.out.println("Orçamento removido com sucesso!");
            } catch (SQLException e) {
                System.err.println("Ocorreu um erro ao deletar orçamento:");
                e.printStackTrace();
            } 
    	} else {
    		System.out.println("ID do orçamento não registrado no banco de dados.");
    	}
        
    }
}
