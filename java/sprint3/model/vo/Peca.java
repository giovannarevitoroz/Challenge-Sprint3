package sprint3.model.vo;

public class Peca {
	private String idPeca;
	private int disponibilidadePeca;
	private String nomePeca;
	private double precoPeca;
	
	public Peca(String idPeca, int disponibilidadePeca, String nomePeca, double precoPeca) {
		this.idPeca = idPeca;
		this.disponibilidadePeca = disponibilidadePeca;
		this.nomePeca = nomePeca;
		this.precoPeca = precoPeca;
	}

	public String getIdPeca() {

		return idPeca;
	}

	public void setIdPeca(String idPeca) {
		this.idPeca = idPeca;
	}

	public int getDisponibilidadePeca() {
		return disponibilidadePeca;
	}

	public void setDisponibilidadePeca(int disponibilidadePeca) {
		this.disponibilidadePeca = disponibilidadePeca;
	}

	public String getNomePeca() {
		return nomePeca;
	}

	public void setNomePeca(String nomePeca) {
		this.nomePeca = nomePeca;
	}

	public double getPrecoPeca() {
		return precoPeca;
	}

	public void setPrecoPeca(double precoPeca) {
		this.precoPeca = precoPeca;
	}

}
