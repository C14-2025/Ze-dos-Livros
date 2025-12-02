package br.c14lab.biblioteca.implementacao;

import br.c14lab.biblioteca.exceptions.NaoEncontradoException;
import br.c14lab.biblioteca.exceptions.RegistroDuplicadoException;
import br.c14lab.biblioteca.implementacao.interfaces.UsuarioRegras;
import br.c14lab.biblioteca.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioIMPL implements UsuarioRegras {


    //Atributos
    //----------------------------------------------------------------------------------------------
    private List<Usuario> usuarios = new ArrayList<>();
    //----------------------------------------------------------------------------------------------

    public UsuarioIMPL(){
        usuarios.add(new Usuario(1, "Ana Silva", "ana.silva@example.com", "11987654321", "Rua das Flores, 123"));
        usuarios.add(new Usuario(2, "Bruno Oliveira", "bruno.oliveira@example.com", "11999887766", "Avenida Central, 987"));
        usuarios.add(new Usuario(3, "Carla Souza", "carla.souza@example.com", "21966554433", "Rua do Sol, 55"));
    }


    // Métodos
    //----------------------------------------------------------------------------------------------
    // Adiciona um usuario
    @Override
    public void guardarUsuario(Usuario usuario) {
        // Verifica se o ID já existe
        for (Usuario u : usuarios) {
            if (u.getId() == usuario.getId()) {
                throw new RegistroDuplicadoException("[Já existe um usuário com este ID!]");
            }
        }
        usuarios.add(usuario);
        System.out.println("Usuário adicionado com sucesso!");
    }
    //----------------------------------------------------------------------------------------------



    //----------------------------------------------------------------------------------------------
    // Busca um usuario por ID
    @Override
    public Usuario buscarPorId(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        throw new NaoEncontradoException("Usuário com ID " + id + " não encontrado!");
    }
    //----------------------------------------------------------------------------------------------



    //----------------------------------------------------------------------------------------------
    // Busca um usuario por NOME
    @Override
    public Usuario buscarPorNome(String nome) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNome().equalsIgnoreCase(nome)) {
                return usuario;
            }
        }
        throw new NaoEncontradoException("Usuário com nome \"" + nome + "\" não encontrado!");
    }
    //----------------------------------------------------------------------------------------------



    //----------------------------------------------------------------------------------------------
    // Lista todos os usuários
    @Override
    public List<Usuario> mostrarTodosUsuarios() {
        return usuarios;
    }
    //----------------------------------------------------------------------------------------------



    //----------------------------------------------------------------------------------------------
    //Atualiza o usuário por ID
    @Override
    public void atualizarUsuario(Usuario usuarioAtualizado) {
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            if (usuario.getId() == usuarioAtualizado.getId()) {
                usuarios.set(i, usuarioAtualizado);
                System.out.println("Usuário atualizado com sucesso!");
                return;
            }
        }
    }
    //----------------------------------------------------------------------------------------------



    //----------------------------------------------------------------------------------------------
    //Remove um usuário por ID
    @Override
    public void removerUsuario(int id) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId() == id) {
                usuarios.remove(i);
                System.out.println("Usuário removido com sucesso!");
                return;
            }
        }
    }
    //----------------------------------------------------------------------------------------------

}
