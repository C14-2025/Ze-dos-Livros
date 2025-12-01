package br.c14lab.biblioteca.controller;

import br.c14lab.biblioteca.exceptions.RegistroDuplicadoException;
import br.c14lab.biblioteca.implementacao.UsuarioIMPL;
import br.c14lab.biblioteca.model.Usuario;
import br.c14lab.biblioteca.exceptions.NaoEncontradoException;
import java.util.List;
import java.util.Scanner;

public class ControladoraUsuarios {

    //Atributos
    //----------------------------------------------------------------------------------------------
    Scanner sc = new Scanner(System.in);
    private UsuarioIMPL usuarioIMPL = new UsuarioIMPL();
    //----------------------------------------------------------------------------------------------



    //Construtor
    //----------------------------------------------------------------------------------------------
    public ControladoraUsuarios(UsuarioIMPL usuarioIMPL, Scanner scanner) {
        this.usuarioIMPL = usuarioIMPL;
        this.sc = scanner;
    }
    //----------------------------------------------------------------------------------------------



    //Métodos
    //----------------------------------------------------------------------------------------------
    // MENU DE USUÁRIOS
    public void telaInicial() {
        int option = 99;
        do {
            System.out.println("                Pressione ENTER para continuar:");
            sc.nextLine();


            System.out.println("====================================================");
            System.out.println("                Gerenciar Usuários");
            System.out.println("====================================================");
            System.out.println("[1 - Adicionar Usuário]");
            System.out.println("[2 - Buscar por ID]");
            System.out.println("[3 - Buscar por Nome]");
            System.out.println("[4 - Mostrar todos]");
            System.out.println("[5 - Atualizar Usuário]");
            System.out.println("[6 - Remover Usuário]");
            System.out.println("[0 - Voltar]");
            System.out.println("====================================================");

            option = sc.nextInt();
            sc.nextLine(); //n bugar o switch

            switch (option) {

                case 1:
                    try {
                        adicionarUsuario();
                    } catch (RegistroDuplicadoException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case 2:
                    buscarPorId();
                    break;

                case 3:
                    buscarPorNome();
                    break;

                case 4:
                    mostrarTodos();
                    break;

                case 5:
                    atualizarUsuario();
                    break;

                case 6:
                    removerUsuario();
                    break;

                case 0:
                    System.out.println("Voltando...");
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }

        } while (option != 0);
    }
    //----------------------------------------------------------------------------------------------



    // ADICIONAR -----------------------------------------------------------------------------------
    public void adicionarUsuario() {
        System.out.println("[Para Cadastrar o novo usuário digite:]");

        System.out.println("[ID:]");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.println("[Nome:]");
        String nome = sc.nextLine();

        System.out.println("[Email:]");
        String email = sc.nextLine();

        System.out.println("[Telefone:]");
        String telefone = sc.nextLine();

        System.out.println("[Endereco:]");
        String endereco = sc.nextLine();

        usuarioIMPL.guardarUsuario(new Usuario(id,nome,email,telefone,endereco));
    }
    //----------------------------------------------------------------------------------------------



    // BUSCAR POR ID --------------------------------------------------------------------------------
    public void buscarPorId() {
        System.out.println("[Para buscar um usuário digite:]");
        System.out.println("[ID:]");
        int id = sc.nextInt();
        sc.nextLine();

        try {
            Usuario u = usuarioIMPL.buscarPorId(id);
            System.out.println("[Usuário encontrado:] " + u.getNome());
            System.out.println("[Email:] " + u.getEmail());
            System.out.println("[Telefone:] " + u.getTelefone());
            System.out.println("[Endereco:] " + u.getEndereco());
        } catch (NaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }
    //----------------------------------------------------------------------------------------------



    // BUSCAR POR NOME ------------------------------------------------------------------------------
    public void buscarPorNome() {
        System.out.println("[Digite o nome:]");
        String nome = sc.nextLine();

        try {
            Usuario u = usuarioIMPL.buscarPorNome(nome);
            System.out.println("[Usuário encontrado: ID = " + u.getId() + "]");
            System.out.println("[Email:] " + u.getEmail());
            System.out.println("[Telefone:] " + u.getTelefone());
            System.out.println("[Endereco:] " + u.getEndereco());
        } catch (NaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }
    //----------------------------------------------------------------------------------------------



    // MOSTRAR TODOS --------------------------------------------------------------------------------
    public void mostrarTodos() {
        List<Usuario> lista = usuarioIMPL.mostrarTodosUsuarios();
        if (lista.isEmpty()) {
            System.out.println("[Nenhum usuário cadastrado!]");
            return;
        }

        System.out.println("[----- Lista de Usuários -----]");
        for (Usuario u : lista) {
            System.out.println("[ID: " + u.getId() + "] | [Nome: " + u.getNome() +"]");
        }
    }
    //----------------------------------------------------------------------------------------------



    // ATUALIZAR ------------------------------------------------------------------------------------
    public void atualizarUsuario() {
        System.out.println("[Digite o ID do usuário a ser atualizado:]");
        int id = sc.nextInt();
        sc.nextLine();

        Usuario usuarioAtualizado;

        //Buscar o usuário existente
        try {
            usuarioAtualizado = usuarioIMPL.buscarPorId(id);
        } catch (NaoEncontradoException e) {
            System.out.println(e.getMessage());
            return;
        }

        // 2. Atualizar os campos
        System.out.println("[Digite o novo nome:]");
        String novoNome = sc.nextLine();
        usuarioAtualizado.setNome(novoNome);

        System.out.println("[Digite o novo email:]");
        String novoEmail = sc.nextLine();
        usuarioAtualizado.setEmail(novoEmail);

        System.out.println("[Digite o novo telefone:]");
        String novoTelefone = sc.nextLine();
        usuarioAtualizado.setTelefone(novoTelefone);

        System.out.println("[Digite o novo endereço:]");
        String novoEndereco = sc.nextLine();
        usuarioAtualizado.setEndereco(novoEndereco);

        usuarioIMPL.atualizarUsuario(usuarioAtualizado);
    }

    //----------------------------------------------------------------------------------------------

    // REMOVER --------------------------------------------------------------------------------------
    public void removerUsuario() {
        System.out.println("[Digite o ID do usuário a remover:]");
        int id = sc.nextInt();
        sc.nextLine();

        try {
            usuarioIMPL.buscarPorId(id); //garante que o usuario existe
            //se ñ existir lança exceção
            usuarioIMPL.removerUsuario(id);

        } catch (NaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }
    //----------------------------------------------------------------------------------------------
}

