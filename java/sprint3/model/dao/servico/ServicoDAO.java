package sprint3.model.dao.servico;

import sprint3.model.vo.Servico;

import java.util.ArrayList;

public interface ServicoDAO {
    boolean inserir(Servico s) ;
    public ArrayList<Servico> listar() ;
    Servico buscarPorId(String id) ;
    public boolean servicoExiste(String idServico) ;
    public void atualizarTipo(String idServico, String tipo) ;
    public void atualizarDescricao(String idServico, String descricao) ;
    public void atualizarPreco(String idServico, double preco) ;
    public void atualizarDuracao(String idServico, int duracao);
    void deletar(String id);
}
