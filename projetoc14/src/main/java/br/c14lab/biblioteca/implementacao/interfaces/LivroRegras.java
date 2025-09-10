package br.c14lab.biblioteca.implementacao.interfaces;

import br.c14lab.biblioteca.model.Livro;

import java.util.List;

public interface LivroRegras {
    void adicionarLivro(Livro livro);
    Livro buscarPorIsbn(String isbn);
    List<Livro> buscarTodosOsLivros();
    void atualizarLivro(Livro livro);
    void removerLivro(Livro livro);
    List<Livro> buscarPorTituloOuAutor(String titulo, String autor);
}
