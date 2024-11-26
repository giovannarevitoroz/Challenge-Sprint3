package sprint3.model.dao.cargo;

import oracle.jdbc.pool.OracleDataSource;
import sprint3.model.dao.credenciais.Credenciais;
import sprint3.model.vo.Cargo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CargoDaoImpl implements CargoDAO {
    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";

    public Connection conectar() throws SQLException {
        OracleDataSource ods = new OracleDataSource();
        ods.setURL(URL);
        ods.setUser(Credenciais.user);
        ods.setPassword(Credenciais.pwd);
        return ods.getConnection();
    }

    @Override
    public boolean inserir(Cargo c) {
        String sql = "INSERT INTO cargo (id_cargo, nome_cargo, descricao_cargo, area_cargo, salario_cargo) VALUES (?,?,?,?,?)";

        try (Connection conn = conectar();
        		PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getIdCargo());
            ps.setString(2, c.getNomeCargo());
            ps.setString(3, c.getDescricaoCargo());
            ps.setString(4, c.getAreaCargo());
            ps.setDouble(5, c.getSalarioCargo());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }  catch (SQLException e) {
            System.err.println("Ocorreu um erro ao inserir cargo:");
            e.printStackTrace();
            return false;
        } 
    }

    @Override
    public ArrayList<Cargo> listar() {
        ArrayList<Cargo> cargos = new ArrayList<Cargo>();
        String sql = "SELECT * FROM cargo"; 
        try (Connection conn = conectar();
        	 PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet resultSet = ps.executeQuery()){      	
            while (resultSet.next()) {
                String idCargo = resultSet.getString("id_cargo");
                String nomeCargo = resultSet.getString("nome_cargo");
                String descricaoCargo = resultSet.getString("descricao_cargo");
                String areaCargo = resultSet.getString("area_cargo");
                Double salarioCargo = resultSet.getDouble("salario_cargo");

                Cargo cargo = new Cargo(idCargo, nomeCargo, descricaoCargo, areaCargo, salarioCargo);
                cargos.add(cargo);
            }
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao listar cargos:");
            e.printStackTrace();
        } 
        return cargos;
    }

    @Override 
    public Cargo buscarPorId(String idCargo) {
        String sql = "SELECT * FROM cargo WHERE id_cargo = ?";
        Cargo cargo = null;
        try (Connection conn = conectar();
        		PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, idCargo);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    String id = resultSet.getString("id_cargo");
                    String nome = resultSet.getString("nome_cargo");
                    String descricao = resultSet.getString("descricao_cargo");
                    String area = resultSet.getString("area_cargo");
                    double salario = resultSet.getDouble("salario_cargo");

                    cargo = new Cargo(id, nome, descricao, area, salario);
                } 
            }
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao obter cargo:");
            e.printStackTrace();
        } 
        return cargo;
    }
    
    @Override
    public boolean cargoExiste(String idCargo) {
    	String sqlExiste = "SELECT COUNT(*) FROM cargo WHERE id_cargo = ?";
    	try(Connection conn = conectar();
    			PreparedStatement ps = conn.prepareStatement(sqlExiste)) {
    		ps.setString(1, idCargo);
    		try (ResultSet resultSet = ps.executeQuery()) {
    			if (resultSet.next()) {
    				return resultSet.getInt(1) > 0;
    			} else {
    				return false;
    			}
    		}
    	} catch (SQLException e) {
            System.err.println("Ocorreu um erro ao obter cargo:");
            e.printStackTrace();
            return false;
        } 
    }
    
    @Override
    public void atualizarNomeCargo(String idCargo, String nome) {
    	if(cargoExiste(idCargo)) {
    		String sql = "UPDATE cargo SET nome_cargo = ? where id_cargo = ?";
        	try(Connection conn = conectar();
        			PreparedStatement ps = conn.prepareStatement(sql)) {
        		ps.setString(1, nome);
        		ps.setString(2, idCargo);
        		ps.executeUpdate();
        		System.out.println("Nome do cargo atualizado com sucesso!");
        	} catch (SQLException e) {
                System.err.println("Ocorreu um erro ao atualizar cargo:");
                e.printStackTrace();
            } 
    	} else {
    		System.out.println("ID do cargo não registrado no banco de dados.");
    	}
    	
    }
    
    @Override
    public void atualizarDescricaoCargo(String idCargo, String descricao) {
    	if(cargoExiste(idCargo)) {
    		String sql = "UPDATE cargo SET descricao_cargo = ? where id_cargo = ?";
        	try(Connection conn = conectar();
        			PreparedStatement ps = conn.prepareStatement(sql)) {
        		ps.setString(1, descricao);
        		ps.setString(2, idCargo);
        		ps.executeUpdate();
        		System.out.println("Descrição do cargo atualizada com sucesso!");
        	} catch (SQLException e) {
                System.err.println("Ocorreu um erro ao atualizar cargo:");
                e.printStackTrace();
            } 
    	} else {
    		System.out.println("ID do cargo não registrado no banco de dados.");
    	}
    	
    }
    
    @Override
    public void atualizarAreaCargo(String idCargo, String area) {
    	if (cargoExiste(idCargo)) {
    		String sql = "UPDATE cargo SET area_cargo = ? where id_cargo = ?";
        	try(Connection conn = conectar();
        			PreparedStatement ps = conn.prepareStatement(sql)) {
        		ps.setString(1, area);
        		ps.setString(2, idCargo);
        		ps.executeUpdate();
        		System.out.println("Área do cargo atualizada com sucesso!");
        	} catch (SQLException e) {
                System.err.println("Ocorreu um erro ao atualizar cargo:");
                e.printStackTrace();
            } 
    	} else {
    		System.out.println("ID do cargo não registrado no banco de dados.");
    	}
    	
    }
    
    @Override
    public void atualizarSalarioCargo(String idCargo, double salario) {
    	if (cargoExiste(idCargo)) {
    		String sql = "UPDATE cargo SET salario_cargo = ? where id_cargo = ?";
        	try(Connection conn = conectar();
        			PreparedStatement ps = conn.prepareStatement(sql)) {
        		ps.setDouble(1, salario);
        		ps.setString(2, idCargo);
        		ps.executeUpdate();
        		System.out.println("Salário do cargo atualizado com sucesso!");
        	} catch (SQLException e) {
                System.err.println("Ocorreu um erro ao atualizar cargo:");
                e.printStackTrace();
            } 
    	} else {
    		System.out.println("ID do cargo não registrado no banco de dados.");
    	}
    	
    }

    @Override
    public void deletar(String id) {
    	if (cargoExiste(id)) {
    		String sql = "DELETE FROM cargo WHERE id_cargo = ?";
            try (Connection conn = conectar();
            		PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, id);
                ps.executeUpdate();
                System.out.println("Cargo removido com sucesso!");
            } catch (SQLException e) {
                System.err.println("Ocorreu um erro ao deletar cargo:");
                e.printStackTrace();
            } 
    	} else {
    		System.out.println("ID do cargo não registrado no banco de dados.");
    	}
        
    }
}
