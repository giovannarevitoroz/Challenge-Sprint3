package sprint3.model.dao.funcionario;

import sprint3.model.vo.Funcionario;

import java.util.ArrayList;

public interface FuncionarioDAO {

    public boolean inserir(Funcionario funcionario) ;
    public ArrayList<Funcionario> listar() ;
    public Funcionario buscarPorId(String id);
    public boolean funcionarioExiste(String idFuncionario);
    public void atualizarNomeFuncionario(String matricula, String nome) ;
    public void atualizarHorarioTrabalho(String matricula, String horario) ;
    public void atualizarDisponibilidadeFuncionario(String matricula, boolean disponibilidade) ;
    public void atualizarCentro(String matricula, String idCentro);
    public void atualizarCargo(String matricula, String idCargo) ;
    public void deletar(String matricula) ;
}
