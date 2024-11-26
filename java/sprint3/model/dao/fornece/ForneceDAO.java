package sprint3.model.dao.fornece;

import java.util.ArrayList;


public interface ForneceDAO {
	public boolean adicionarAssociacao(String id_peca, String id_servico);
	public boolean deletarAssociacao(String id_peca, String id_servico);
	public ArrayList<String> listarServicosPorPeca(String id_peca);
	public ArrayList<String> listarPecasPorServico(String id_servico);
	
}
