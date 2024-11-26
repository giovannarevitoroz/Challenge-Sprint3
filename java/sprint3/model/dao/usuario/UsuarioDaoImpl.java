package sprint3.model.dao.usuario;

import oracle.jdbc.pool.OracleDataSource;
import sprint3.model.dao.credenciais.Credenciais;
import sprint3.model.vo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDaoImpl implements UsuarioDAO {

    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";

    public Connection conectar() throws SQLException {
        OracleDataSource ods = new OracleDataSource();
        ods.setURL(URL);
        ods.setUser(Credenciais.user);
        ods.setPassword(Credenciais.pwd);
        return ods.getConnection();
    }

    @Override
    public boolean inserir(Usuario usuario) {
        String sql = "INSERT INTO usuario (cpf_usuario, nome_usuario, email, telefone, senha) VALUES (?,?,?,?,?)";
        try (Connection conn = conectar();
        		PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usuario.getCpfUsuario());
            ps.setString(2, usuario.getNomeUsuario());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getTelefone());
            ps.setString(5, usuario.getSenha());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao inserir usuário:");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Usuario> listar() {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        String sql = "SELECT * FROM usuario";
        try (Connection conn = conectar();
        		PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet resultSet = ps.executeQuery();){
            while (resultSet.next()) {
                String cpfUsuario = resultSet.getString("cpf_usuario");
                String nomeUsuario = resultSet.getString("nome_usuario");
                String email = resultSet.getString("email");
                String telefone = resultSet.getString("telefone");
                String senha = resultSet.getString("senha");

                Usuario usuario = new Usuario(cpfUsuario, nomeUsuario, senha, email, telefone);
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao listar usuários: ");
            e.printStackTrace();
        } 
        return usuarios;
    }

    @Override
    public Usuario buscarPorCPF(String cpf) {
        String sql = "SELECT * FROM usuario WHERE cpf_usuario = ?";
        Usuario usuario = null;
        try (Connection conn = conectar();
        		PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cpf);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    String cpfUsuario = resultSet.getString("cpf_usuario");
                    String nomeUsuario = resultSet.getString("nome_usuario");
                    String email = resultSet.getString("email");
                    String telefone = resultSet.getString("telefone");
                    String senha = resultSet.getString("senha");
                    usuario =  new Usuario(cpfUsuario, nomeUsuario, senha, email, telefone);
                } 
            }  
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao retornar veículo:");
            e.printStackTrace();
        } 
        return usuario;  
    }
    
    @Override
    public boolean usuarioExiste(String cpf) {
    	String sqlExiste = "SELECT COUNT(*) FROM usuario WHERE cpf_usuario = ?";
    	try(Connection conn = conectar();
    			PreparedStatement ps = conn.prepareStatement(sqlExiste)) {
    		ps.setString(1, cpf);
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
    public void atualizarNome(String cpf, String nome)  {
    	if (usuarioExiste(cpf)) {
    		String sql = "UPDATE usuario SET nome_usuario = ? where cpf_usuario = ?";
        	try(Connection conn = conectar();
        			PreparedStatement ps = conn.prepareStatement(sql)) {
        		ps.setString(1, nome);
        		ps.setString(2, cpf);
        		ps.executeUpdate();
        		System.out.println("Nome do usuário atualizado com sucesso!");
        	} 
        	catch (SQLException e) {
                System.err.println("Ocorreu um erro ao atualizar usuário: ");
                e.printStackTrace();
            } 
    	} else {
    		System.out.println("CPF do usuário não registrado no banco de dados.");
    	}	
    }
    
    @Override
    public void atualizarEmail(String cpf, String email) {
    	if (usuarioExiste(cpf)) {
    		String sql = "UPDATE usuario SET email = ? where cpf_usuario = ?";
        	try(Connection conn = conectar();
        			PreparedStatement ps = conn.prepareStatement(sql)) {
        		ps.setString(1, email);
        		ps.setString(2, cpf);
        		ps.executeUpdate();
        		System.out.println("E-mail do usuário atualizado com sucesso!");
        	} 
        	catch (SQLException e) {
                System.err.println("Ocorreu um erro ao atualizar usuário: ");
                e.printStackTrace();
            } 
    	} else {
    		System.out.println("CPF do usuário não registrado no banco de dados.");
    	}
    	
    }
    
    @Override
    public void atualizarTelefone(String cpf, String telefone) {
    	if (usuarioExiste(cpf)) {
    		String sql = "UPDATE usuario SET telefone = ? where cpf_usuario = ?";
        	try(Connection conn = conectar();
        			PreparedStatement ps = conn.prepareStatement(sql)) {
        		ps.setString(1, telefone);
        		ps.setString(2, cpf);
        		ps.executeUpdate();
        		System.out.println("Telefone do usuário atualizado com sucesso!");
        	} 
        	catch (SQLException e) {
                System.err.println("Ocorreu um erro ao atualizar usuário: ");
                e.printStackTrace();
            }
    	} else {
    		System.out.println("CPF do usuário não registrado no banco de dados.");
    	}
    	
    }
    
    @Override
    public void atualizarSenha(String cpf, String senha) {
    	if (usuarioExiste(cpf)) {
    		String sql = "UPDATE usuario SET senha = ? where cpf_usuario = ?";
        	try(Connection conn = conectar();
        			PreparedStatement ps = conn.prepareStatement(sql)) {
        		ps.setString(1, senha);
        		ps.setString(2, cpf);
        		ps.executeUpdate();
        		System.out.println("Senha do usuário atualizada com sucesso!");
        	} 
        	catch (SQLException e) {
                System.err.println("Ocorreu um erro ao atualizar usuário: ");
                e.printStackTrace();
            } 
    	} else {
    		System.out.println("CPF do usuário não registrado no banco de dados.");
    	}
    	
    }
    
    @Override
    public void deletar(String cpf) {
    	if (usuarioExiste(cpf)) {
    		String sql = "DELETE FROM usuario WHERE cpf_usuario = ?";
            try (Connection conn = conectar();
            		PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, cpf);
                ps.executeUpdate();
                System.out.println("Usuário removido com sucesso!");
            } 
            catch (SQLException e) {
                System.err.println("Ocorreu um erro ao deletar usuário: ");
                e.printStackTrace();
            } 
    	} else {
    		System.out.println("CPF do usuário não registrado no banco de dados.");
    	}
        
    }

}
