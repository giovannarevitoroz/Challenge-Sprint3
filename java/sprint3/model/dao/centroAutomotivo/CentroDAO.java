package sprint3.model.dao.centroAutomotivo;

import sprint3.model.vo.CentroAutomotivo;
import java.util.ArrayList;


public interface CentroDAO {
    public boolean inserir(CentroAutomotivo centroAutomotivo) ;
    public ArrayList<CentroAutomotivo> listar() ;
    public CentroAutomotivo buscarPorId(String id) ;
    public boolean centroExiste(String idCentro) ;
    public void atualizarNomeCentro(String idCentro, String nome);
    public void atualizarEndereco(String idCentro, String endereco);
    public void atualizarTelefoneCentro(String idCentro, String telefone);
    public void atualizarHorarioFuncionamento(String idCentro, String horario);
    public void deletar(String id);
}
