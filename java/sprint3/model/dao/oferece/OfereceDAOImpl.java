package sprint3.model.dao.oferece;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.pool.OracleDataSource;
import sprint3.model.dao.credenciais.Credenciais;

public class OfereceDAOImpl implements OfereceDAO {
	private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";

    public Connection conectar() throws SQLException {
        OracleDataSource ods = new OracleDataSource();
        ods.setURL(URL);
        ods.setUser(Credenciais.user);
        ods.setPassword(Credenciais.pwd);
        return ods.getConnection();
    }

	@Override
	public boolean adicionarAssociacao(String id_servico, String id_centro) {
		String sql = "INSERT INTO oferece (servico_id_servico, centro_automotivo_id_centro) VALUES (?, ?)";
		try (Connection conn = conectar();
				PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id_servico);
            ps.setString(2, id_centro);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
        	System.err.println("Ocorreu um erro ao inserir associação Oferece: o centro e o serviço já estão associados.");
            return false;
        } 
	}

	@Override
	public boolean deletarAssociacao(String id_servico, String id_centro)  {
		String sql = "DELETE FROM oferece WHERE centro_automotivo_id_centro = ? AND servico_id_servico = ?";

        try (Connection conn = conectar();
        		PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id_servico);
            ps.setString(2, id_centro);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao deletar associação Oferece:");
            e.printStackTrace();
            return false;
        } 
	}

	@Override
	public ArrayList<String> listarServicosPorCentro(String id_centro) {
		ArrayList<String> servicos = new ArrayList<String>();
        String sql = "SELECT servico_id_servico FROM oferece WHERE centro_automotivo_id_centro = ?";
        try (Connection conn = conectar();
        		PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id_centro);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                	String idServico = rs.getString("servico_id_servico");
                    servicos.add(idServico);
                }
            }
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao listar serviços associados a centro:");
            e.printStackTrace();
        } 
        return servicos;
	}

	@Override
	public ArrayList<String> listarCentrosPorServico(String id_servico) {
		ArrayList<String> centros = new ArrayList<String>();
        String sql = "SELECT centro_automotivo_id_centro FROM oferece WHERE servico_id_servico = ?";
        try (Connection conn = conectar();
        		PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id_servico);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                	 String idCentro = rs.getString("centro_automotivo_id_centro");
                     centros.add(idCentro);
                }
            }
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao listar centros associados a serviço:");
            e.printStackTrace();
        } 
        return centros;
	}

}
