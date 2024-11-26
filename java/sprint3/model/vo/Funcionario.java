package sprint3.model.vo;

public class Funcionario {
    private String matriculaFuncionario;
    private String nomeFuncionario;
    private Cargo cargo;
    private CentroAutomotivo centroAutomotivo;
    private boolean disponibilidade;
    private String horarioTrabalho;

    public Funcionario(String matriculaFuncionario, String nomeFuncionario, Cargo cargo, CentroAutomotivo centroAutomotivo, boolean disponibilidade, String horarioTrabalho) {
        this.matriculaFuncionario = matriculaFuncionario;
        this.nomeFuncionario = nomeFuncionario;
        this.cargo = cargo;
        this.centroAutomotivo = centroAutomotivo;
        this.disponibilidade = disponibilidade;
        this.horarioTrabalho = horarioTrabalho;
    }


    public String getMatriculaFuncionario() {
		return matriculaFuncionario;
	}

	public void setMatriculaFuncionario(String matriculaFuncionario) {
		this.matriculaFuncionario = matriculaFuncionario;
	}

	public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public CentroAutomotivo getCentroAutomotivo() {
        return centroAutomotivo;
    }

    public void setCentroAutomotivo(CentroAutomotivo centroAutomotivo) {
        this.centroAutomotivo = centroAutomotivo;
    }


	public boolean isDisponibilidade() {
		return disponibilidade;
	}


	public void setDisponibilidade(boolean disponibilidade) {
		this.disponibilidade = disponibilidade;
	}


	public String getHorarioTrabalho() {
		return horarioTrabalho;
	}


	public void setHorarioTrabalho(String horarioTrabalho) {
		this.horarioTrabalho = horarioTrabalho;
	}
    
}