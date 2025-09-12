package br.c14lab.biblioteca.implementacao;

import br.c14lab.biblioteca.exceptions.LivroNaoEncontradoException;
import br.c14lab.biblioteca.exceptions.UsuarioNaoEncontradoException;
import br.c14lab.biblioteca.implementacao.interfaces.UsuarioRegras;
import br.c14lab.biblioteca.model.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsuarioIMPL implements UsuarioRegras {

    //Atributos  ------------------------------------------------------------------------------------
    List<Usuario> usuarios = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    //-------------------------------------------------------------------------------------------------


    //Métodos ------------------------------------------------------------------------------------
    @Override
    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }



    @Override
    public Usuario buscarPorId(String id) {
        for (Usuario u : usuarios) {
            if (id != null && id.equals(u.getId())) {
                return u;
            }
        }
        throw new UsuarioNaoEncontradoException("Usuário não encontrado!");
    }

    @Override
    public void mostrarTodosUsuarios() {
        for(Usuario u : usuarios )
        {
            System.out.println("[Usuário " + u.getId() + ": " + u.getNome() + "]");
            System.out.println("-------------------------");
        }
    }

    // Método para auxilio em testes
    public int quantidadeUsuarios() {
        return usuarios.size();
    }

    @Override
    public void atualizarUsuario(Usuario usuario) {
        int atualizar;

        System.out.println("[Qual informação deseja atualizar do usuario " + usuario.getNome() + " :]");
        System.out.println("[1 - Nome       ]");
        System.out.println("[2 - Email      ]");
        System.out.println("[3 - Telefone   ]");
        System.out.println("[4 - Endereco   ]");
        System.out.println("-------------------------");

        atualizar = sc.nextInt();

        if (atualizar == 1)
        {
            String nome;
            System.out.println("[Digite o novo nome: ]");
            nome = sc.nextLine();

            usuario.setNome(nome);
            System.out.println("[Nome atualizado com sucesso!]");
            System.out.println("-------------------------");
        }
        else if (atualizar == 2)
        {
            String email;
            System.out.println("[Digite o novo email: ]");
            email = sc.nextLine();
            usuario.setEmail(email);
            System.out.println("[Email atualizado com sucesso!]");
            System.out.println("-------------------------");
        }
        else if (atualizar == 3)
        {
            String telefone;
            System.out.println("[Digite o novo telefone: ]");
            telefone = sc.nextLine();
            usuario.setTelefone(telefone);
            System.out.println("[Telefone atualizado com sucesso!]");
            System.out.println("-------------------------");
        }
        else if (atualizar == 4)
        {
            String endereco;
            System.out.println("[Digite o novo endereco: ]");
            endereco = sc.nextLine();
            usuario.setEndereco(endereco);
            System.out.println("[Endereco atualizado com sucesso!]");
            System.out.println("-------------------------");
        }

        sc.close(); //Fecha o scanner
    }


    @Override
    public void removerUsuario(String id) {
        Usuario usuarioParaRemover = null;

        for(Usuario u : usuarios )
        {
            if(id != null && id.equals(u.getId()))
            {
                usuarioParaRemover = u;
                break;
            }
        }

        if (usuarioParaRemover != null)
        {
            usuarios.remove(usuarioParaRemover);
            System.out.println("[Usuário com ID " + id + " removido com sucesso.]");
            System.out.println("-------------------------");
        }
        else
        {
            throw new UsuarioNaoEncontradoException("Usuário não encontrado para remoção!");
        }
    }


    /*  testeUsuarioNaoEncontradoException
    *
    * tembém testar o lançamento de exceção, e a busca do usuário*/
    @Override
    public Usuario buscarPorNome(String nome) {
        for(Usuario u : usuarios )
        {
            if(nome != null && nome.equals(u.getNome())) {
                return u;
            }
        }
        throw new UsuarioNaoEncontradoException("Usuario com nome '" + nome + "'não encontrado.");
    }
    //-------------------------------------------------------------------------------------------------



}
