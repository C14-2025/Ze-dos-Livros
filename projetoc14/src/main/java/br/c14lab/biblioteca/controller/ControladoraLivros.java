package br.c14lab.biblioteca.controller;

import br.c14lab.biblioteca.exceptions.NaoEncontradoException;
import br.c14lab.biblioteca.exceptions.RegistroDuplicadoException;
import br.c14lab.biblioteca.implementacao.LivroIMPL;
import br.c14lab.biblioteca.model.Livro;

import java.util.List;
import java.util.Scanner;

public class ControladoraLivros {

    //Atributos
    //----------------------------------------------------------------------------------------------
    Scanner sc = new Scanner(System.in);
    private LivroIMPL livroIMPL = new LivroIMPL();
    //----------------------------------------------------------------------------------------------



    //Construtor
    //----------------------------------------------------------------------------------------------
    public ControladoraLivros(LivroIMPL livroIMPL) {
        this.livroIMPL = livroIMPL;
    }
    //----------------------------------------------------------------------------------------------



    //Métodos
    //----------------------------------------------------------------------------------------------
    // MENU PRINCIPAL
    public void telaInicial() {

        int option = 99;
        do {
            System.out.println("                Pressione ENTER para continuar:");
            sc.nextLine();

            System.out.println("====================================================");
            System.out.println("                  Gerenciar Livros");
            System.out.println("====================================================");
            System.out.println("[1 - Adicionar Livro]");
            System.out.println("[2 - Buscar por ISBN]");
            System.out.println("[3 - Buscar por Título ou Autor]");
            System.out.println("[4 - Mostrar todos os livros]");
            System.out.println("[5 - Atualizar Livro]");
            System.out.println("[6 - Remover Livro]");
            System.out.println("[0 - Voltar]");
            System.out.println("====================================================");

            option = sc.nextInt();
            sc.nextLine(); //n bugar o switch

            switch (option) {

                case 1:
                    adicionarLivro();
                    break;

                case 2:
                    buscarPorIsbn();
                    break;

                case 3:
                    buscarPorTituloOuAutor();
                    break;

                case 4:
                    mostrarTodosLivros();
                    break;

                case 5:
                    atualizarLivro();
                    break;

                case 6:
                    removerLivro();
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
    private void adicionarLivro() {
        int quantidade;

        System.out.println("[Digite o ISBN:]");
        String isbn = sc.nextLine();

        System.out.println("[Digite o Título:]");
        String titulo = sc.nextLine();

        System.out.println("[Digite o Autor:]");
        String autor = sc.nextLine();

        System.out.println("[Digite a Editora:]");
        String editora = sc.nextLine();

        System.out.println("[Digite o Ano de Publicação:]");
        int anoPublicacao = sc.nextInt();
        sc.nextLine();

        System.out.println("[Digite a Quantidade Disponível:]");
        do{
            quantidade = sc.nextInt();
        }while(quantidade < 1);
        sc.nextLine();

        System.out.println("[Digite a Categoria:]");
        String categoria = sc.nextLine();

        Livro novo = new Livro(isbn, titulo, autor, editora, anoPublicacao, quantidade, categoria);

        try {
            livroIMPL.guardarLivro(novo);
        } catch (RegistroDuplicadoException e) {
            System.out.println(e.getMessage());
        }
    }
    //----------------------------------------------------------------------------------------------



    // BUSCAR POR ISBN -----------------------------------------------------------------------------
    private void buscarPorIsbn() {

        System.out.println("[Digite o ISBN:]");
        String isbn = sc.nextLine();

        try {
            Livro livro = livroIMPL.buscarPorIsbn(isbn);
            exibirLivro(livro);

        } catch (NaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }
    //----------------------------------------------------------------------------------------------



    // BUSCAR POR TÍTULO OU AUTOR -------------------------------------------------------------------
    private void buscarPorTituloOuAutor() {

        System.out.println("[Digite o título (ou deixe vazio)]:");
        String titulo = sc.nextLine();

        System.out.println("[Digite o autor (ou deixe vazio):]");
        String autor = sc.nextLine();

        List<Livro> encontrados = livroIMPL.buscarPorTituloOuAutor(titulo, autor);

        if (encontrados.isEmpty()) {
            System.out.println("[Nenhum livro encontrado]");
            return;
        }

        for (Livro livro : encontrados) {
            exibirLivro(livro);
        }
    }
    //----------------------------------------------------------------------------------------------



    // MOSTRAR TODOS --------------------------------------------------------------------------------
    private void mostrarTodosLivros() {

        List<Livro> lista = livroIMPL.buscarTodosOsLivros();

        if (lista.isEmpty()) {
            System.out.println("[Nenhum livro cadastrado]");
            return;
        }

        for (Livro livro : lista) {
            exibirLivro(livro);
        }
    }
    //----------------------------------------------------------------------------------------------



    // ATUALIZAR ------------------------------------------------------------------------------------
    private void atualizarLivro() {

        System.out.println("[Digite o ISBN do livro a ser atualizado:]");
        String isbn = sc.nextLine();

        System.out.println("[Digite o novo título:]");
        String novoTitulo = sc.nextLine();

        System.out.println("[Digite o novo autor:]");
        String novoAutor = sc.nextLine();

        System.out.println("[Digite a nova editora:]");
        String novaEditora = sc.nextLine();

        System.out.println("[Digite o novo ano de publicação:]");
        int novoAno = sc.nextInt();
        sc.nextLine();

        System.out.println("[Digite a nova quantidade disponível:]");
        int novaQuantidade = sc.nextInt();
        sc.nextLine();

        System.out.println("[Digite a nova categoria:]");
        String novaCategoria = sc.nextLine();

        Livro atualizado = new Livro(isbn, novoTitulo, novoAutor, novaEditora, novoAno, novaQuantidade, novaCategoria);

        try {
            livroIMPL.atualizarLivro(atualizado);
        } catch (NaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }
    //----------------------------------------------------------------------------------------------



    // REMOVER --------------------------------------------------------------------------------------
    private void removerLivro() {

        System.out.println("[Digite o ISBN do livro a remover:]");
        String isbn = sc.nextLine();

        // só o ISBN é usado
        Livro remover = new Livro(isbn, "", "", "", 0, 0, "");

        try {
            livroIMPL.removerLivro(remover);
        } catch (NaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }
    //----------------------------------------------------------------------------------------------



    // EXIBIR LIVRO ---------------------------------------------------------------------------------
    private void exibirLivro(Livro livro) {

        System.out.println("--------------------------------------");
        System.out.println("ISBN: " + livro.getIsbn());
        System.out.println("Título: " + livro.getTitulo());
        System.out.println("Autor: " + livro.getAutor());
        System.out.println("Editora: " + livro.getEditora());
        System.out.println("Ano de Publicação: " + livro.getAnoPublicacao());
        System.out.println("Quantidade Disponível: " + livro.getQuantidadeDisponivel());
        System.out.println("Categoria: " + livro.getCategoria());
    }
    //----------------------------------------------------------------------------------------------
}
