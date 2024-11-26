package sprint3.model.dao.veiculo;

import oracle.jdbc.pool.OracleDataSource;
import sprint3.model.dao.credenciais.Credenciais;
import sprint3.model.dao.usuario.UsuarioDaoImpl;
import sprint3.model.vo.Usuario;
import sprint3.model.vo.Veiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VeiculoDaoImpl implements VeiculoDAO {

    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
    UsuarioDaoImpl daoUsuario = new UsuarioDaoImpl();
    
    public VeiculoDaoImpl() {
    	
    }

    private Connection conectar() throws SQLException {
        OracleDataSource ods = new OracleDataSource();
        ods.setURL(URL);
        ods.setUser(Credenciais.user);
        ods.setPassword(Credenciais.pwd);
        return ods.getConnection();
    }

    // Método para inserir um novo veículo
    @Override
    public boolean inserir(Veiculo veiculo)  {
        String sql = "INSERT INTO veiculo (marca, modelo, placa, ano, quilometragem, usuario_cpf_usuario) VALUES (?,?,?,?,?,?)";
        try (Connection conn = conectar();
        	PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, veiculo.getMarca());
            ps.setString(2, veiculo.getModelo());
            ps.setString(3, veiculo.getPlaca());
            ps.setInt(4, veiculo.getAno());
            ps.setDouble(5, veiculo.getQuilometragem());
            ps.setString(6, veiculo.getUsuario().getCpfUsuario());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao inserir veículo:");
            e.printStackTrace();
            return false;
        } 
    }

    // Método para listar todos os veículos
    @Override
    public ArrayList<Veiculo> listar() {
        ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();
        String sql = "SELECT * FROM veiculo";
        try (Connection conn = conectar();
        	PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet resultSet = ps.executeQuery()) {

            while (resultSet.next()) {
                String marca = resultSet.getString("marca");
                String modelo = resultSet.getString("modelo");
                String placa = resultSet.getString("placa");
                int ano = resultSet.getInt("ano");
                double quilometragem = resultSet.getDouble("quilometragem");
                Usuario usuario = daoUsuario.buscarPorCPF(resultSet.getString("usuario_cpf_usuario"));

                Veiculo veiculo = new Veiculo(marca, modelo, ano, placa, quilometragem, usuario);
                veiculos.add(veiculo);
            }
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao listar veículos:");
            e.printStackTrace();
        } 
        return veiculos;
    }

    @Override
    public Veiculo buscarPorPlaca(String placa) {
        String sql = "SELECT * FROM veiculo WHERE placa = ?";
        Veiculo veiculo = null; 
        try (Connection conn = conectar();
        		PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, placa);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    String marca = resultSet.getString("marca");
                    String modelo = resultSet.getString("modelo");
                    int ano = resultSet.getInt("ano");
                    double quilometragem = resultSet.getDouble("quilometragem");
                    Usuario usuario = daoUsuario.buscarPorCPF(resultSet.getString("usuario_cpf_usuario"));
                    veiculo = new Veiculo(marca, modelo, ano, placa, quilometragem, usuario);
                }
            }
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao retornar veículo:");
            e.printStackTrace();
        } 
        return veiculo; 
    }
    
    @Override
    public boolean veiculoExiste(String placa) {
    	String sqlExiste = "SELECT COUNT(*) FROM veiculo WHERE placa = ?";
    	try(Connection conn = conectar();
    			PreparedStatement ps = conn.prepareStatement(sqlExiste)) {
    		ps.setString(1, placa);
    		try (ResultSet resultSet = ps.executeQuery()) {
    			if (resultSet.next()) {
    				return resultSet.getInt(1) > 0;
    			} else {
    				return false;
    			}
    		}
    	} catch (SQLException e) {
            System.err.println("Ocorreu um erro ao retornar veículo:");
            e.printStackTrace();
            return false;
        } 
    }
    
    @Override
    public void atualizarMarca(String placa, String marca) {
    	if (veiculoExiste(placa)) {
    		String sql = "UPDATE veiculo SET marca = ? where placa = ?";
        	try(Connection conn = conectar();
        			PreparedStatement ps = conn.prepareStatement(sql)) {
        		ps.setString(1, marca);
        		ps.setString(2, placa);
        		ps.executeUpdate();
        		System.out.println("Marca atualizada com sucesso!");
        	} catch (SQLException e) {
        		System.err.println("Ocorreu um erro ao atualizar veículo:");
                e.printStackTrace();
            } 
    	} else {
    		System.out.println("Placa do veículo não registrada no banco de dados.");
        }
    	
    }
    
    @Override
    public void atualizarModelo(String placa, String modelo) {
    	if (veiculoExiste(placa)) {
    		String sql = "UPDATE veiculo SET modelo = ? where placa = ?";
        	try(Connection conn = conectar();
        			PreparedStatement ps = conn.prepareStatement(sql)) {
        		ps.setString(1, modelo);
        		ps.setString(2, placa);
        		ps.executeUpdate();
        		System.out.println("Modelo atualizado com sucesso!");
        	} catch (SQLException e) {
        		System.err.println("Ocorreu um erro ao atualizar veículo:");
                e.printStackTrace();
            } 
    	} else {
    		System.out.println("Placa do veículo não registrada no banco de dados.");
    	}
    	
    }
    
    @Override
    public void atualizarAno(String placa, int ano) {
    	if (veiculoExiste(placa)) {
    		String sql = "UPDATE veiculo SET ano = ? where placa = ?";
        	try(Connection conn = conectar();
        			PreparedStatement ps = conn.prepareStatement(sql)) {
        		ps.setInt(1, ano);
        		ps.setString(2, placa);
        		ps.executeUpdate();
        		System.out.println("Ano atualizado com sucesso!");
        	} catch (SQLException e) {
        		System.err.println("Ocorreu um erro ao atualizar veículo:");
                e.printStackTrace();
            } 
    	} else {
    		System.out.println("Placa do veículo não registrada no banco de dados.");
    	}
    	
    }
    
    @Override
    public void atualizarQuilometragem(String placa, double quilometragem) {
    	if(veiculoExiste(placa)) {
    		String sql = "UPDATE veiculo SET quilometragem = ? where placa = ?";
        	try(Connection conn = conectar();
        			PreparedStatement ps = conn.prepareStatement(sql)) {
        		ps.setDouble(1, quilometragem);
        		ps.setString(2, placa);
        		ps.executeUpdate();
        		System.out.println("Quilometragem atualizada com sucesso!");
        	} catch (SQLException e) {
        		System.err.println("Ocorreu um erro ao atualizar veículo:");
                e.printStackTrace();
            } 
    	} else {
    		System.out.println("Placa do veículo não registrada no banco de dados.");
    	}	
    }
    
    @Override
    public void atualizarCpfProprietario(String placa, String cpf) throws SQLException {
    	if(veiculoExiste(placa) && daoUsuario.usuarioExiste(cpf)) {
    		String sql = "UPDATE veiculo SET usuario_cpf_usuario = ? where placa = ?";
        	try(Connection conn = conectar();
        			PreparedStatement ps = conn.prepareStatement(sql)) {
        		ps.setString(1, cpf);
        		ps.setString(2, placa);
        		ps.executeUpdate();
        		System.out.println("CPF do proprietário atualizado com sucesso!");
        	} catch (SQLException e) {
        		System.err.println("Ocorreu um erro ao atualizar veículo:");
                e.printStackTrace();
            } 
    	} else {
    		System.out.println("Placa do veículo ou CPF do usuário não registrados no banco de dados.");
    	}
    	
    }

    // Método para deletar um veículo com base na placa
    @Override
    public void deletar(String placa)  {
    	if(veiculoExiste(placa)) {
    		String sql = "DELETE FROM veiculo WHERE placa = ?";
            try (Connection conn = conectar();
            		PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, placa);
                ps.executeUpdate();
                System.out.println("Veículo removido com sucesso!");
            } catch (SQLException e) {
                System.err.println("Ocorreu um erro ao deletar veículo:");
                e.printStackTrace();
            } 
    	} else {
    		System.out.println("Placa do veículo não registrada no banco de dados.");
    	}  
    }
}
