package br.c14lab.biblioteca.controller;

import br.c14lab.biblioteca.exceptions.NaoEncontradoException;
import br.c14lab.biblioteca.exceptions.RegistroDuplicadoException;
import br.c14lab.biblioteca.implementacao.EmprestimoIMPL;
import br.c14lab.biblioteca.model.Emprestimo;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ControladoraEmprestimos {

    //Atributos
    //----------------------------------------------------------------------------------------------
    private EmprestimoIMPL emprestimoIMPL;
    Emprestimo emprestimo  = new Emprestimo();
    Scanner sc = new Scanner(System.in);
    //----------------------------------------------------------------------------------------------



    //Atributos
    //----------------------------------------------------------------------------------------------
    public ControladoraEmprestimos(EmprestimoIMPL emprestimoIMPL, Scanner scanner) {
        this.emprestimoIMPL = emprestimoIMPL;
        this.sc = scanner;
    }
    //----------------------------------------------------------------------------------------------



    //Métodos
    // -------------------------------------------------------------------------
    public void telaInicial() {

        int option = 99;
        do {
            System.out.println("                Pressione ENTER para continuar:");
            sc.nextLine();

            System.out.println("====================================================");
            System.out.println("               Gerenciar Empréstimos");
            System.out.println("====================================================");
            System.out.println("[1 - Registrar Empréstimo]");
            System.out.println("[2 - Buscar por ID]");
            System.out.println("[3 - Buscar por Usuário]");
            System.out.println("[4 - Listar todos]");
            System.out.println("[5 - Listar apenas ativos]");
            System.out.println("[6 - Devolver Livro]");
            System.out.println("[0 - Voltar]");
            System.out.println("====================================================");
            System.out.print("Opção: ");

            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    registrarEmprestimo();
                    break;

                case 2:
                    buscarPorID();
                    break;

                case 3:
                    buscarPorUsuario();
                    break;

                case 4:
                    listarTodos();
                    break;

                case 5:
                    listarAtivos();
                    break;

                case 6:
                    marcarComoDevolvido();
                    break;

                case 0:
                    System.out.println("Voltando...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (option != 0);
    }
    // -------------------------------------------------------------------------



    // -------------------------------------------------------------------------
    public void registrarEmprestimo() {
        Emprestimo aux = new Emprestimo();

        System.out.print("[ID do Empréstimo:] ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("[ID do Usuário:] ");
        int usuarioId = sc.nextInt();
        sc.nextLine();

        System.out.print("[ISBN do Livro:] ");
        String isbn = sc.nextLine();

        aux.setId(id);
        aux.setLivroIsbn(isbn);
        aux.setUsuarioId(usuarioId);
        aux.setDataEmprestimo(LocalDate.now());
        aux.setDataDevolucao(LocalDate.now().plusDays(30));
        aux.setDevolvido(false);

        try{
            emprestimoIMPL.guardarEmprestimo(aux);
        }
        catch (RegistroDuplicadoException | NaoEncontradoException e){
            System.out.println(e.getMessage());
        }
    }
    // -------------------------------------------------------------------------



    // -------------------------------------------------------------------------
    public void buscarPorID() {
        System.out.print("[Digite o ID do empréstimo:] ");
        int id = sc.nextInt();
        sc.nextLine();

        Emprestimo emprestimo = null;

        try {
            emprestimo = emprestimoIMPL.buscarEmprestimoPorID(id);
        }
        catch (NaoEncontradoException e){
            System.out.println(e.getMessage());
        }

        if (emprestimo != null) {
            mostrarEmprestimo(emprestimo);
        }
    }

    // -------------------------------------------------------------------------



    // -------------------------------------------------------------------------
    public void buscarPorUsuario() {
        System.out.print("[ID do usuário: ]");
        int usuarioId = sc.nextInt();
        sc.nextLine();

        Emprestimo emprestimo = null;

        try {
            emprestimo = emprestimoIMPL.buscarPorUsuario(usuarioId);
        }
        catch (NaoEncontradoException e){
            System.out.println(e.getMessage());
        }

        if (emprestimo != null) {
            mostrarEmprestimo(emprestimo);
        }
    }
    // -------------------------------------------------------------------------



    // -------------------------------------------------------------------------
    public void listarTodos() {
        List<Emprestimo> lista = emprestimoIMPL.buscarTodosOsEmprestimos();


        if (lista == null || lista.isEmpty()) {
            System.out.println("[Nenhum empréstimo registrado]");
            return;
        }

        for (Emprestimo e : lista) {
            mostrarEmprestimo(e);
        }
    }

    // -------------------------------------------------------------------------



    // -------------------------------------------------------------------------
    public void listarAtivos() {
        List<Emprestimo> ativos = emprestimoIMPL.buscarEmprestimosAtivos();

        if (ativos.isEmpty()) {
            System.out.println("[Nenhum empréstimo ativo]");
            return;
        }

        for (Emprestimo e : ativos) {
            mostrarEmprestimo(e);
        }
    }
    // -------------------------------------------------------------------------



    //----------------------------------------------------------------------------------------------
    public void marcarComoDevolvido() {
        System.out.print("[ID do empréstimo para devolver:] ");
        int id = sc.nextInt();
        sc.nextLine();

        try {
            Emprestimo e = emprestimoIMPL.buscarEmprestimoPorID(id);

            e.setDevolvido(true);
            e.setDataDevolucao(LocalDate.now());
            emprestimoIMPL.atualizarEmprestimo(e);

            emprestimoIMPL.devolverExemplar(e);
            System.out.println("Empréstimo devolvido com sucesso!");

        } catch (NaoEncontradoException ex) {
            System.out.println(ex.getMessage());
        }
    }
    //----------------------------------------------------------------------------------------------



    // -------------------------------------------------------------------------
    public void mostrarEmprestimo(Emprestimo e) {
        System.out.println("----------------------------------------");
        System.out.println("ID: " + e.getId());
        System.out.println("Usuário ID: " + e.getUsuarioId());
        System.out.println("Livro ISBN: " + e.getLivroIsbn());
        System.out.println("Data Empréstimo: " + e.getDataEmprestimo());
        System.out.println("Data Devolução: " + e.getDataDevolucao());
        System.out.println("Devolvido: " + e.isDevolvido());
    }
    // -------------------------------------------------------------------------

}
