package sprint3.model.dao.veiculo;

import sprint3.model.vo.Veiculo;

import java.sql.SQLException;
import java.util.ArrayList;


public interface VeiculoDAO {
    public boolean inserir(Veiculo veiculo);
    public ArrayList<Veiculo> listar();
    public Veiculo buscarPorPlaca(String placa) ;
    public boolean veiculoExiste(String placa);
    public void atualizarMarca(String placa, String marca) ;
    public void atualizarModelo(String placa, String modelo) ;
    public void atualizarAno(String placa, int ano);
    public void atualizarQuilometragem(String placa, double quilometragem);
    public void atualizarCpfProprietario(String placa, String cpf) throws SQLException;
    public void deletar(String placa) ;
}
