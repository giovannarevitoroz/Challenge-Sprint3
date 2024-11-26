package sprint3.model.dao.orcamento;

import sprint3.model.vo.Orcamento;

import java.sql.SQLException;
import java.util.List;

public interface OrcamentoDAO {
    public boolean inserir(Orcamento orcamento) throws SQLException;
    public List<Orcamento> listar() throws SQLException;
    public Orcamento buscarPorId(String id) throws SQLException;
    public boolean orcamentoExiste(String idOrcamento) throws SQLException;
    public void atualizarStatus(String idOrcamento, String status) throws SQLException;
    public void deletar(String id) throws SQLException;
}
