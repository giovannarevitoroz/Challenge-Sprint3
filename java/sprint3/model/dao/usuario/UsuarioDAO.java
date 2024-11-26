package sprint3.model.dao.usuario;

import sprint3.model.vo.Usuario;

import java.util.ArrayList;

public interface UsuarioDAO {
    public boolean inserir(Usuario usuario) ;
    public ArrayList<Usuario> listar() ;
    public Usuario buscarPorCPF(String cpf) ;
    public boolean usuarioExiste(String cpf) ;
    public void atualizarNome(String cpf, String nome) ;
    public void atualizarEmail(String cpf, String email) ;
    public void atualizarTelefone(String cpf, String telefone) ;
    public void atualizarSenha(String cpf, String senha) ;
    public void deletar(String cpf) ;
}
