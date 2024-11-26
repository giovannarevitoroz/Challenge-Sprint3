package sprint3.model.dao.agendamento;

import sprint3.model.vo.Agendamento;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;


public interface AgendamentoDAO {

    public boolean inserir(Agendamento Agendamento) throws SQLException;
    public ArrayList<Agendamento> listar() throws SQLException;
    public Agendamento buscarPorId(String id) throws SQLException;
    public boolean agendamentoExiste(String idAgendamento) throws SQLException;
    public void atualizarDataAgendamento(String idAgendamento, Date data) ;
    public void atualizarHorarioAgendamento(String idAgendamento, String hora) ;
    public void atualizarDescricaoAgendamento(String idAgendamento, String descricao) ;
    public void atualizarCentro(String idAgendamento, String idCentro) ;
    public void atualizarServico(String idAgendamento, String idServico) ;
    public void atualizarVeiculo(String idAgendamento, String placa);
    public void deletar(String id);


}
