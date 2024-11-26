package sprint3.model.dao.peca;

import oracle.jdbc.pool.OracleDataSource;
import sprint3.model.dao.credenciais.Credenciais;
import sprint3.model.vo.Peca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PecaDaoImpl implements PecaDAO {

    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";

    public Connection conectar() throws SQLException {
        OracleDataSource ods = new OracleDataSource();
        ods.setURL(URL);
        ods.setUser(Credenciais.user);
        ods.setPassword(Credenciais.pwd);
        return ods.getConnection();
    }

    // Insere uma nova peça no banco de dados
    @Override
    public boolean inserir(Peca peca) {
        String sql = "INSERT INTO peca (id_peca, disponibilidade_peca, nome_peca, preco_peca) " +
                "VALUES (?, ?, ?, ?)";
        try (Connection conn = conectar();
        		PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, peca.getIdPeca());
            ps.setInt(2, peca.getDisponibilidadePeca());
            ps.setString(3, peca.getNomePeca());
            ps.setDouble(4, peca.getPrecoPeca());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao inserir a peça:");
            e.printStackTrace();
            return false;
        } 
    }

    // Lista todas as peças no banco de dados
    @Override
    public ArrayList<Peca> listar() {
        ArrayList<Peca> pecas = new ArrayList<Peca>();
        String sql = "SELECT * FROM peca";
        try (Connection conn = conectar();
        		PreparedStatement ps = conn.prepareStatement(sql);        
        		ResultSet resultSet = ps.executeQuery()){       	
        		while (resultSet.next()) {
	                String idPeca = resultSet.getString("id_peca");
	                int disponibilidadePeca = resultSet.getInt("disponibilidade_peca");
	                String nomePeca = resultSet.getString("nome_peca");
	                double precoPeca = resultSet.getDouble("preco_peca");

                Peca peca = new Peca(idPeca, disponibilidadePeca, nomePeca, precoPeca);
                pecas.add(peca);
            }
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao listas as peças:");
            e.printStackTrace();
        } 
        return pecas;
    }

    @Override
    public Peca buscarPorId(String id) {
        String sql = "SELECT * FROM peca WHERE id_peca = ?";
        Peca peca = null;

        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String idPeca = rs.getString("id_peca");
                    int disponibilidadePeca = rs.getInt("disponibilidade_peca");
                    String nomePeca = rs.getString("nome_peca");
                    double precoPeca = rs.getDouble("preco_peca");

                    peca = new Peca(idPeca, disponibilidadePeca, nomePeca, precoPeca);
                }
            }
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao buscar peça:");
            e.printStackTrace();
        } 
        return peca;
    }
    
    // verifica se a peça existe no sistema. Caso sim, retorna true. Caso não, retorna false. 
    // sem isso, quando tentamos dar fazer update, delete ou read sem que a peça exista, podemos acabar tendo algum erro ou nem sequer um erro.
    @Override
    public boolean pecaExiste(String idPeca) {
    	String sqlExiste = "SELECT COUNT(*) FROM peca WHERE id_peca = ?";
    	try(Connection conn = conectar();
    			PreparedStatement ps = conn.prepareStatement(sqlExiste)) {
    		ps.setString(1, idPeca);
    		try (ResultSet resultSet = ps.executeQuery()) {
    			if (resultSet.next()) {
    				return resultSet.getInt(1) > 0;
    			} else {
    				return false;
    			}
    		}
    	} catch (SQLException e) {
            System.err.println("Ocorreu um erro ao listas as peças:");
            e.printStackTrace();
            return false;
        } 
    }
    
    @Override
    public void atualizarDisponibilidade(String idPeca, int disponibilidade) {
    	if (pecaExiste(idPeca)) {
    		String sql = "UPDATE peca SET disponibilidade_peca = ? where id_peca = ?";
        	try(Connection conn = conectar();
        			PreparedStatement ps = conn.prepareStatement(sql)) {
        		ps.setInt(1, disponibilidade);
        		ps.setString(2, idPeca);
        		ps.executeUpdate();
        		System.out.println("Disponibilidade da peça atualizado com sucesso!");
        	} catch (SQLException e) {
                System.err.println("Ocorreu um erro ao atualizar a peça:");
                e.printStackTrace();
            } 
    	} else {
    		System.out.println("ID da peça não registrado no banco de dados.");
        }
}
    	
    
    
    @Override
    public void atualizarNomePeca(String idPeca, String nome) {
    	if (pecaExiste(idPeca)) {
    		String sql = "UPDATE peca SET nome_peca = ? where id_peca = ?";
    		try(Connection conn = conectar();
    				PreparedStatement ps = conn.prepareStatement(sql)) {
        		ps.setString(1, nome);
        		ps.setString(2, idPeca);
        		ps.executeUpdate();
        		System.out.println("Nome da peça atualizado com sucesso!");
    		} catch (SQLException e) {
                System.err.println("Ocorreu um erro ao atualizar a peça:");
                e.printStackTrace();
            } 
    } else {
    	System.out.println("ID da peça não registrado no banco de dados.");
    }
  }
    
    @Override
    public void atualizarPrecoPeca(String idPeca, double preco)  {
    	if (pecaExiste(idPeca)) {
    		String sql = "UPDATE peca SET preco_peca = ? where id_peca = ?";
        	try(Connection conn = conectar();
        			PreparedStatement ps = conn.prepareStatement(sql)) {
        		ps.setDouble(1, preco);
        		ps.setString(2, idPeca);
        		ps.executeUpdate();
        		System.out.println("Preço da peça atualizado com sucesso!");
        	} catch (SQLException e) {
                System.err.println("Ocorreu um erro ao atualizar a peça:");
                e.printStackTrace();
            } 
    	} else {
    		System.out.println("ID da peça não registrado no banco de dados.");
    	}
    	
    } 

    @Override
    public void deletar(String id)  {
    	if (pecaExiste(id)) {
    		String sql = "DELETE FROM peca WHERE id_peca = ?";
            try (Connection conn = conectar();
            		PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, id);
                ps.executeUpdate();
            System.out.println("Peça removida com sucesso!");       
            } catch (SQLException e) {
                System.err.println("Ocorreu um erro ao deletar a peça:");
                e.printStackTrace();
            } 
        } else {
        	System.out.println("ID da peça não registrado no banco de dados.");
        }
    }
}
