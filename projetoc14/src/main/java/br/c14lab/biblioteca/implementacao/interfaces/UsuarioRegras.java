package br.c14lab.biblioteca.implementacao.interfaces;

import br.c14lab.biblioteca.model.Usuario;

import java.util.List;

public interface UsuarioRegras {
    void guardarUsuario(Usuario usuario) ;
    Usuario buscarPorId(int id);
    List<Usuario> mostrarTodosUsuarios();
    void atualizarUsuario(Usuario usuario);
    void removerUsuario(int id);
    Usuario buscarPorNome(String nome);
}
