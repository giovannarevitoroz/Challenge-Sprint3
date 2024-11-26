package sprint3.model.dao.diagnostico;

import sprint3.model.vo.Diagnostico;

import java.util.List;

public interface DiagnosticoDAO {
    public boolean inserir(Diagnostico diagnostico) ;
    public List<Diagnostico> listar() ;
    public Diagnostico buscarPorId(String id) ;
    public boolean diagnosticoExiste(String idDiagnostico) ;
    public void atualizarStatusDiagnostico(String idDiagnostico, String status) ;
    public void deletar(String id) ;
    
}
