package sprint3.model.vo;

public class Orcamento {
	private String idOrcamento;
	private String descricaoOrcamento;
	private double valorTotal;
	private String statusOrcamento;
	
	public Orcamento(String idOrcamento, String descricaoOrcamento, double valorTotal, String statusOrcamento) {
		this.idOrcamento = idOrcamento;
		this.descricaoOrcamento = descricaoOrcamento;
		this.valorTotal = valorTotal;
		this.statusOrcamento = statusOrcamento;
	}

	public String getIdOrcamento() {
		return idOrcamento;
	}

	public void setIdOrcamento(String idOrcamento) {
		this.idOrcamento = idOrcamento;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getDescricaoOrcamento() {
		return descricaoOrcamento;
	}

	public void setDescricaoOrcamento(String descricaoOrcamento) {
		this.descricaoOrcamento = descricaoOrcamento;
	}
	
	public String getStatusOrcamento() {
		return statusOrcamento;
	}

	public void setStatusOrcamento(String statusOrcamento) {
		this.statusOrcamento = statusOrcamento;
	}

}
