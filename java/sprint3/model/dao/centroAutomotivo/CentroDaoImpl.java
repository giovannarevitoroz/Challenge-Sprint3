package sprint3.model.dao.centroAutomotivo;

import oracle.jdbc.pool.OracleDataSource;
import sprint3.model.dao.credenciais.Credenciais;
import sprint3.model.vo.CentroAutomotivo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CentroDaoImpl implements CentroDAO {
    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";

    public Connection conectar() throws SQLException {
        OracleDataSource ods = new OracleDataSource();
        ods.setURL(URL);
        ods.setUser(Credenciais.user);
        ods.setPassword(Credenciais.pwd);
        return ods.getConnection();
    }

    @Override
    public boolean inserir(CentroAutomotivo ca) {
        String sql = "INSERT INTO centro_automotivo (id_centro, nome_centro, endereco_centro, telefone_centro, horario_funcionamento) VALUES (?,?,?,?,?)";

        try (Connection conn = conectar();
        		PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ca.getIdCentro());
            ps.setString(2, ca.getNomeCentro());
            ps.setString(3, ca.getEnderecoCentro());
            ps.setString(4, ca.getTelefoneCentro());
            ps.setString(5, ca.getHorarioFuncionamento());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao inserir centro:");
            e.printStackTrace();
            return false;
        } 
    }

    @Override
    public ArrayList<CentroAutomotivo> listar() {
        ArrayList<CentroAutomotivo> centros = new ArrayList<CentroAutomotivo>();
        String sql = "SELECT * FROM centro_automotivo"; 
        try (Connection conn = conectar();
        	 PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet resultSet = ps.executeQuery()) {
        	    while (resultSet.next()) {
                String idCentro = resultSet.getString("id_centro");
                String nomeCentro = resultSet.getString("nome_centro");
                String enderecoCentro = resultSet.getString("endereco_centro");
                String telefoneCentro = resultSet.getString("telefone_centro");
                String horarioFuncionamento = resultSet.getString("horario_funcionamento");

                CentroAutomotivo centro = new CentroAutomotivo(idCentro, nomeCentro, enderecoCentro, telefoneCentro, horarioFuncionamento);
                centros.add(centro);
            }
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao listar centros:");
            e.printStackTrace();
        } 
        return centros;
    }

    @Override
    public CentroAutomotivo buscarPorId(String idCentro) {
        String sql = "SELECT * FROM centro_automotivo WHERE id_centro = ?";
        CentroAutomotivo centro = null;
        try (Connection conn = conectar();
        	 PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, idCentro);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    idCentro = resultSet.getString("id_centro");
                    String nomeCentro = resultSet.getString("nome_centro");
                    String enderecoCentro = resultSet.getString("endereco_centro");
                    String telefoneCentro = resultSet.getString("telefone_centro");
                    String horarioFuncionamento = resultSet.getString("horario_funcionamento");

                    centro = new CentroAutomotivo(idCentro, nomeCentro, enderecoCentro, telefoneCentro,horarioFuncionamento);
                } 
            }
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao buscar centro:");
            e.printStackTrace();
        } 
        return centro;
    }
    
    @Override
    public boolean centroExiste(String idCentro) {
    	String sqlExiste = "SELECT COUNT(*) FROM centro_automotivo WHERE id_centro = ?";
    	try(Connection conn = conectar();
    			PreparedStatement ps = conn.prepareStatement(sqlExiste)) {
    		ps.setString(1, idCentro);
    		try (ResultSet resultSet = ps.executeQuery()) {
    			if (resultSet.next()) {
    				return resultSet.getInt(1) > 0;
    			} else {
    				return false;
    			}
    		}
    	} catch (SQLException e) {
            System.err.println("Ocorreu um erro ao buscar centro:");
            e.printStackTrace();
            return false;
        } 
    }
    
    @Override
    public void atualizarNomeCentro(String idCentro, String nome) {
    	if (centroExiste(idCentro)) {
    		String sql = "UPDATE centro_automotivo SET nome_centro = ? where id_centro = ?";
        	try(Connection conn = conectar();
        			PreparedStatement ps = conn.prepareStatement(sql)) {
        		ps.setString(1, nome);
        		ps.setString(2, idCentro);
        		ps.executeUpdate();
        		System.out.println("Nome do centro atualizado com sucesso!");
        	} catch (SQLException e) {
                System.err.println("Ocorreu um erro ao atualizar centro:");
                e.printStackTrace();
            } 
    	} else {
    		System.out.println("ID do centro não registrado no banco de dados.");
    	}
    	
    }
    
    @Override
    public void atualizarEndereco(String idCentro, String endereco) {
    	if (centroExiste(idCentro)) {
    		String sql = "UPDATE centro_automotivo SET endereco_centro = ? where id_centro = ?";
        	try(Connection conn = conectar();
        			PreparedStatement ps = conn.prepareStatement(sql)) {
        		ps.setString(1, endereco);
        		ps.setString(2, idCentro);
        		ps.executeUpdate();
        		System.out.println("Endereço do centro atualizado com sucesso!");
        	} catch (SQLException e) {
                System.err.println("Ocorreu um erro ao atualizar centro:");
                e.printStackTrace();
            } 
    	} else {
    		System.out.println("ID do centro não registrado no banco de dados.");
    	}
    	
    }
    
    @Override
    public void atualizarTelefoneCentro(String idCentro, String telefone) {
    	if(centroExiste(idCentro)) {
    		String sql = "UPDATE centro_automotivo SET telefone_centro = ? where id_centro = ?";
        	try(Connection conn = conectar();
        			PreparedStatement ps = conn.prepareStatement(sql)) {
        		ps.setString(1, telefone);
        		ps.setString(2, idCentro);
        		ps.executeUpdate();
        		System.out.println("Telefone do centro atualizado com sucesso!");
        	} catch (SQLException e) {
                System.err.println("Ocorreu um erro ao atualizar centro:");
                e.printStackTrace();
            } 
    	} else {
    		System.out.println("ID do centro não registrado no banco de dados.");
    	}
    	
    }
    
    @Override
    public void atualizarHorarioFuncionamento(String idCentro, String horario) {
    	if (centroExiste(idCentro)) {
    		String sql = "UPDATE centro_automotivo SET horario_funcionamento = ? where id_centro = ?";
        	try(Connection conn = conectar();
        			PreparedStatement ps = conn.prepareStatement(sql)) {
        		ps.setString(1, horario);
        		ps.setString(2, idCentro);
        		ps.executeUpdate();
        		System.out.println("Horário de funcionamento do centro atualizado com sucesso!");
        	} catch (SQLException e) {
                System.err.println("Ocorreu um erro ao atualizar centro:");
                e.printStackTrace();
            } 
    	} else {
    		System.out.println("ID do centro não registrado no banco de dados.");
    	}
    	
    }

    @Override
    public void deletar(String id) {
    	if(centroExiste(id)) {
    		String sql = "DELETE FROM centro_automotivo WHERE id_centro = ?";
            try (Connection conn = conectar();
            		PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, id);
                ps.executeUpdate();
                System.out.println("Centro removido com sucesso!");
            } catch (SQLException e) {
                System.err.println("Ocorreu um erro ao deletar centro:");
                e.printStackTrace();
            } 
    	} else {
    		System.out.println("ID do centro não registrado no banco de dados.");
    	}
        
    }
}
