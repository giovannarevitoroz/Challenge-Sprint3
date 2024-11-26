package sprint3.model.dao.peca;

import sprint3.model.vo.Peca;
import java.util.ArrayList;

public interface PecaDAO {

    public boolean inserir(Peca peca) ;
    public ArrayList<Peca> listar() ;
    public Peca buscarPorId(String id) ;
    public boolean pecaExiste(String idPeca) ;
    public void atualizarDisponibilidade(String idPeca, int disponibilidade) ;
    public void atualizarNomePeca(String idPeca, String nome);
    public void atualizarPrecoPeca(String idPeca, double preco) ;
    void deletar(String id) ;
}
