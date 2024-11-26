package sprint3.model.dao.oferece;

import java.util.ArrayList;


public interface OfereceDAO {
	public boolean adicionarAssociacao(String id_servico, String id_centro);
	public boolean deletarAssociacao(String id_servico, String id_centro);
	public ArrayList<String> listarServicosPorCentro(String id_centro);
	public ArrayList<String> listarCentrosPorServico(String id_servico);
}
