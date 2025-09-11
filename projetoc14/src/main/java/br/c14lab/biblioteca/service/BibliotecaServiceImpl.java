package br.c14lab.biblioteca.service;

import br.c14lab.biblioteca.model.Emprestimo;
import br.c14lab.biblioteca.model.Livro;
import br.c14lab.biblioteca.model.Usuario;

import java.util.List;

public class BibliotecaServiceImpl implements BibliotecaService{


    @Override
    public void cadastrarLivros(Livro livro) {

    }

    @Override
    public Livro ConsultarLivro(int id) {
        return null;
    }

    @Override
    public List<Livro> listarLivros() {
        return List.of();
    }

    @Override
    public void atualizarLivro(Livro livro) {

    }

    @Override
    public void removerLivro(int id) {

    }

    @Override
    public void cadastrarUsuario(Usuario usuario) {

    }

    @Override
    public void realizarEmprestimo(Emprestimo emprestimo) {

    }

    @Override
    public void devolverLivro(Livro livro) {

    }

    @Override
    public List<Emprestimo> listarEmprestimos() {
        return List.of();
    }
}