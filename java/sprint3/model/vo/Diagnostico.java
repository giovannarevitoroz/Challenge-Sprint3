package sprint3.model.vo;


public class Diagnostico {
	private String idDiagnostico; // Alterado para String
	private Veiculo veiculo;
	private String descricaoSintomas;
	private String solucao;
	private Orcamento orcamento;
	private Servico servico;
	private String categoria;
	private String status;

	public Diagnostico(String idDiagnostico, Veiculo veiculo, String descricaoSintomas, Servico servico, String categoria, String status, String solucao) {
		this.idDiagnostico = idDiagnostico;
		this.veiculo = veiculo;
		this.descricaoSintomas = descricaoSintomas;
		this.solucao = solucao;
		this.categoria = categoria;
		this.status = status;
		this.servico = servico;
	}
	
	public Diagnostico(String idDiagnostico, Veiculo veiculo, String descricaoSintomas, Servico servico, String categoria, String status, Orcamento orcamento, String solucao) {
		this.idDiagnostico = idDiagnostico;
		this.veiculo = veiculo;
		this.descricaoSintomas = descricaoSintomas;
		this.solucao = solucao;
		this.categoria = categoria;
		this.status = status;
		this.orcamento = orcamento;
		this.servico = servico;
	}

	

	public String getSolucao() {
		return solucao;
	}

	public void setSolucao(String solucao) {
		this.solucao = solucao;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public String getIdDiagnostico() {
		return idDiagnostico; // Retorna String
	}

	public void setIdDiagnostico(String idDiagnostico) { // Aceita String
		this.idDiagnostico = idDiagnostico;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public String getDescricaoSintomas() {
		return descricaoSintomas;
	}

	public void setDescricaoSintomas(String descricaoSintomas) {
		this.descricaoSintomas = descricaoSintomas;
	}

	public Orcamento getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
