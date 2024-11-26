package sprint3.model.vo;

public class Servico {
    private String idServico;
    private String tipoServico;
    private String descricaoServico;
    private double precoServico;
    private int duracaoServico;

    public Servico(String idServico, String tipoServico, String descricaoServico, double precoServico, int duracaoServico) {
        this.idServico = idServico;
        this.tipoServico = tipoServico;
        this.descricaoServico = descricaoServico;
        this.precoServico = precoServico;
        this.duracaoServico = duracaoServico;
    }

    // Getters e setters
    public String getIdServico() {
        return idServico;
    }

    public void setIdServico(String idServico) {
        this.idServico = idServico;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public String getDescricaoServico() {
        return descricaoServico;
    }

    public void setDescricaoServico(String descricaoServico) {
        this.descricaoServico = descricaoServico;
    }

    public double getPrecoServico() {
        return precoServico;
    }

    public void setPrecoServico(double precoServico) {
        this.precoServico = precoServico;
    }

    public int getDuracaoServico() {
        return duracaoServico;
    }

    public void setDuracaoServico(int duracaoServico) {
        this.duracaoServico = duracaoServico;
    }
}
