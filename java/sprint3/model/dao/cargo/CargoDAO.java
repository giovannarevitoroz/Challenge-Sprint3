package sprint3.model.dao.cargo;

import sprint3.model.vo.Cargo;
import java.util.ArrayList;

public interface CargoDAO {
    public boolean inserir(Cargo cargo);
    public ArrayList<Cargo> listar();
    public Cargo buscarPorId(String id);
    public boolean cargoExiste(String idCargo);
    public void atualizarNomeCargo(String idCargo, String nome);
    public void atualizarDescricaoCargo(String idCargo, String descricao);
    public void atualizarAreaCargo(String idCargo, String area);
    public void atualizarSalarioCargo(String idCargo, double salario);
    public void deletar(String id);
}
