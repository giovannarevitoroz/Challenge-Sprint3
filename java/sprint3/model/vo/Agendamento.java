package sprint3.model.vo;

import java.sql.Date;
import java.util.UUID;

public class Agendamento {
	private String idAgendamento;
    private Date data;
    private String hora;
    private String descricao;
    private CentroAutomotivo centro;
    private Servico servico;
    private Veiculo veiculo;

	public Agendamento() {
		this.idAgendamento = UUID.randomUUID().toString();
	}

	public Agendamento(String idAgendamento, Date data, String hora, String descricao, CentroAutomotivo centro, Servico servico, Veiculo veiculo) {
		this.idAgendamento = idAgendamento;
		this.data = data;
        this.hora = hora;
        this.descricao = descricao;
        this.centro = centro;
        this.servico = servico;
        this.veiculo = veiculo;
	}

    public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public CentroAutomotivo getCentro() {
		return centro;
	}

	public void setCentro(CentroAutomotivo centro) {
		this.centro = centro;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	

	public String getIdAgendamento() {
		return idAgendamento;
	}

	public void setIdAgendamento(String idAgendamento) {
		this.idAgendamento = idAgendamento;
	}
	
}
