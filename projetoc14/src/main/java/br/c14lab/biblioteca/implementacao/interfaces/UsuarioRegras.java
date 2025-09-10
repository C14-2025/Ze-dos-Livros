package br.c14lab.biblioteca.implementacao.interfaces;

import br.c14lab.biblioteca.model.Usuario;

import java.util.List;

public interface UsuarioRegras {
    void adicionarUsuario(Usuario usuario) ;
    Usuario buscarPorId(String id);
    void mostrarTodosUsuarios();
    void atualizarUsuario(Usuario usuario);
    void removerUsuario(String id);
    Usuario buscarPorNome(String nome);
}
