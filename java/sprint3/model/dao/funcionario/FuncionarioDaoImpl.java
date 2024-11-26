package sprint3.model.dao.funcionario;

import oracle.jdbc.pool.OracleDataSource;
import sprint3.model.dao.cargo.CargoDaoImpl;
import sprint3.model.dao.centroAutomotivo.CentroDaoImpl;
import sprint3.model.dao.credenciais.Credenciais;
import sprint3.model.vo.Funcionario;
import sprint3.model.vo.Cargo;
import sprint3.model.vo.CentroAutomotivo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FuncionarioDaoImpl implements FuncionarioDAO {
    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
    private CentroDaoImpl daoCentro = new CentroDaoImpl();
    private CargoDaoImpl daoCargo = new CargoDaoImpl();

    public Connection conectar() throws SQLException {
        OracleDataSource ods = new OracleDataSource();
        ods.setURL(URL);
        ods.setUser(Credenciais.user);
        ods.setPassword(Credenciais.pwd);
        return ods.getConnection();
    }

    @Override
    public boolean inserir(Funcionario f) {
        String sql = "INSERT INTO funcionario (matricula_func, nome_func, horario_trabalho, disponibilidade_func, centro_automotivo_id_centro, cargo_id_cargo) VALUES (?,?,?,?,?,?)";

        try (Connection conn = conectar();
        		PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, f.getMatriculaFuncionario());
            ps.setString(2, f.getNomeFuncionario());
            ps.setString(3, f.getHorarioTrabalho());
            ps.setString(4, f.isDisponibilidade() ? "S" : "N");
            ps.setString(5, f.getCentroAutomotivo().getIdCentro());
            ps.setString(6, f.getCargo().getIdCargo());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao inserir funcionário:");
            e.printStackTrace();
            return false;
        } 
    }

    @Override
    public ArrayList<Funcionario> listar() {
        ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
        String sql = "SELECT * FROM funcionario";

        try  (Connection conn = conectar();
        		PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet resultSet = ps.executeQuery()) {
            while (resultSet.next()) {
                String matricula = resultSet.getString("matricula_func");
                String nome = resultSet.getString("nome_func");
                String horario = resultSet.getString("horario_trabalho");
                boolean disponibilidade = resultSet.getString("disponibilidade_func").equals("S");
                String idCargo = resultSet.getString("cargo_id_cargo");
                String idCentro = resultSet.getString("centro_automotivo_id_centro");

                Cargo cargo = daoCargo.buscarPorId(idCargo);
                CentroAutomotivo centro = daoCentro.buscarPorId(idCentro);

                Funcionario funcionario = new Funcionario(matricula, nome, cargo, centro, disponibilidade, horario);
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao listar funcionários:");
            e.printStackTrace();
        } 
        return funcionarios;
    }

    @Override
    public Funcionario buscarPorId(String id)  {
        String sql = "SELECT * FROM funcionario WHERE matricula_func = ?";
        Funcionario funcionario = null;
        try (Connection conn = conectar();
        		PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    String matricula = resultSet.getString("matricula_func");
                    String nome = resultSet.getString("nome_func");
                    String horario = resultSet.getString("horario_trabalho");
                    boolean disponibilidade = resultSet.getString("disponibilidade_func").equals("S");
                    String idCargo = resultSet.getString("cargo_id_cargo");
                    String idCentro = resultSet.getString("centro_automotivo_id_centro");
                    Cargo cargo = daoCargo.buscarPorId(idCargo);
                    CentroAutomotivo centro = daoCentro.buscarPorId(idCentro);
                    funcionario = new Funcionario(matricula, nome, cargo, centro, disponibilidade, horario);
                } 
            }
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao buscar funcionário:");
            e.printStackTrace();
        } 
        return funcionario;
    }
    
    @Override
    public boolean funcionarioExiste(String matricula)  {
    	String sqlExiste = "SELECT COUNT(*) FROM funcionario WHERE matricula_func = ?";
    	try(Connection conn = conectar();
    			PreparedStatement ps = conn.prepareStatement(sqlExiste)) {
    		ps.setString(1, matricula);
    		try (ResultSet resultSet = ps.executeQuery()) {
    			if (resultSet.next()) {
    				return resultSet.getInt(1) > 0;
    			} else {
    				return false;
    			}
    		}
    	} catch (SQLException e) {
            System.err.println("Ocorreu um erro ao buscar funcionário:");
            e.printStackTrace();
            return false;
        } 
    }
    
    @Override
    public void atualizarNomeFuncionario(String matricula, String nome)  {
    	if (funcionarioExiste(matricula)) {
    		String sql = "UPDATE funcionario SET nome_func = ? where matricula_func = ?";
        	try(Connection conn = conectar();
        			PreparedStatement ps = conn.prepareStatement(sql)) {
        		ps.setString(1, nome);
        		ps.setString(2, matricula);
        		ps.executeUpdate();
        		System.out.println("Nome do funcionário atualizado com sucesso!");
        	} catch (SQLException e) {
                System.err.println("Ocorreu um erro ao atualizar funcionário:");
                e.printStackTrace();
            } 
    	} else {
    		System.out.println("Matrícula do funcionário não registrada no banco de dados.");
    	}
    	
    }
    
    @Override
    public void atualizarHorarioTrabalho(String matricula, String horario)  {
    	if (funcionarioExiste(matricula)) {
    		String sql = "UPDATE funcionario SET horario_trabalho = ? where matricula_func = ?";
        	try(Connection conn = conectar();
        			PreparedStatement ps = conn.prepareStatement(sql)) {
        		ps.setString(1, horario);
        		ps.setString(2, matricula);
        		ps.executeUpdate();
        		System.out.println("Horário de trabalho atualizado com sucesso!");
        	} catch (SQLException e) {
                System.err.println("Ocorreu um erro ao atualizar funcionário:");
                e.printStackTrace();
            } 
    	} else {
    		System.out.println("Matrícula do funcionário não registrada no banco de dados.");
    	}
    	
    }
    
    @Override
    public void atualizarDisponibilidadeFuncionario(String matricula, boolean disponibilidade)  {
    	if (funcionarioExiste(matricula)) {
    		String sql = "UPDATE funcionario SET disponibilidade_func = ? where matricula_func = ?";
        	try(Connection conn = conectar();
        			PreparedStatement ps = conn.prepareStatement(sql)) {
        		ps.setString(1, disponibilidade ? "S" : "N");
        		ps.setString(2, matricula);
        		ps.executeUpdate();
        		System.out.println("Disponibilidade atualizada com sucesso!");
        	} catch (SQLException e) {
                System.err.println("Ocorreu um erro ao atualizar funcionário:");
                e.printStackTrace();
            } 
    	} else {
    		System.out.println("Matrícula do funcionário não registrada no banco de dados.");
    	}
    	
    }
    
    @Override
    public void atualizarCentro(String matricula, String idCentro)  {
    	if (funcionarioExiste(matricula) && daoCentro.centroExiste(idCentro)) {
    		String sql = "UPDATE funcionario SET centro_automotivo_id_centro = ? where matricula_func = ?";
        	try(Connection conn = conectar();
        			PreparedStatement ps = conn.prepareStatement(sql)) {
        		ps.setString(1, idCentro);
        		ps.setString(2, matricula);
        		ps.executeUpdate();
        		System.out.println("Centro do funcionário atualizado com sucesso!");
        	} catch (SQLException e) {
                System.err.println("Ocorreu um erro ao atualizar funcionário:");
                e.printStackTrace();
            } 
    	} else {
    		System.out.println("Matrícula do funcionário não registrada no banco de dados.");
    	}	
    }
    
    @Override
    public void atualizarCargo(String matricula, String idCargo) {
    	if (funcionarioExiste(matricula) && daoCargo.cargoExiste(idCargo)) {
    		String sql = "UPDATE funcionario SET cargo_id_cargo = ? where matricula_func = ?";
        	try(Connection conn = conectar();
        			PreparedStatement ps = conn.prepareStatement(sql)) {
        		ps.setString(1, idCargo);
        		ps.setString(2, matricula);
        		ps.executeUpdate();
        		System.out.println("Cargo do funcionário atualizado com sucesso!");
        	} catch (SQLException e) {
                System.err.println("Ocorreu um erro ao atualizar funcionário:");
                e.printStackTrace();
            } 
    	} else {
    		System.out.println("Matrícula do funcionário não registrada no banco de dados.");
    	}	
    }

    @Override
    public void deletar(String matricula) {
    	if (funcionarioExiste(matricula)) {
    		String sql = "DELETE FROM funcionario WHERE matricula_func = ?";
            try (Connection conn = conectar();
            		PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, matricula);
                ps.executeUpdate();
                System.out.println("Funcionário removido com sucesso!");
            } catch (SQLException e) {
                System.err.println("Ocorreu um erro ao deletar funcionário:");
                e.printStackTrace();
            } 
    	} else {
    		System.out.println("Matrícula do funcionário não registrada no banco de dados.");
    	}
    }

}
