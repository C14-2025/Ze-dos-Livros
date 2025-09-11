package br.c14lab.biblioteca.model;

import java.util.List;

public interface BibliotecaService {

    void cadastrarLivros (Livro livro);
    Livro ConsultarLivro (int id);
    List<Livro> listarLivros();
    void atualizarLivro (Livro livro);
    void removerLivro (int id);
    void cadastrarUsuario (Usuario usuario);
    void realizarEmprestimo (Emprestimo emprestimo);
    void devolverLivro (Livro livro);
    List<Emprestimo> listarEmprestimos();



}