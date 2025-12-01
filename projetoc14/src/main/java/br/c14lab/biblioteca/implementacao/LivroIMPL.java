package br.c14lab.biblioteca.implementacao;

import br.c14lab.biblioteca.exceptions.NaoEncontradoException;
import br.c14lab.biblioteca.exceptions.RegistroDuplicadoException;
import br.c14lab.biblioteca.implementacao.interfaces.LivroRegras;
import br.c14lab.biblioteca.model.Livro;

import java.util.ArrayList;
import java.util.List;


public class LivroIMPL implements LivroRegras {

    //Atributos
    //----------------------------------------------------------------------------------------------
    private List<Livro> livros = new ArrayList<>();
    //----------------------------------------------------------------------------------------------



    // MÉTODOS
    //----------------------------------------------------------------------------------------------
    //Adiciona um livro
    @Override
    public void guardarLivro(Livro livro) {
        for (Livro l : livros) {
            if (l.getIsbn().equals(livro.getIsbn())) {
                throw new RegistroDuplicadoException("Já existe um livro com o ISBN " + livro.getIsbn());
            }
        }
        livros.add(livro);
        System.out.println("[Livro adicionado com sucesso!]");
    }
    //----------------------------------------------------------------------------------------------



    //----------------------------------------------------------------------------------------------
    //Busca um livro pelo seu ID (isbn)
    @Override
    public Livro buscarPorIsbn(String isbn) {
        for (Livro livro : livros) {
            if (livro.getIsbn().equals(isbn)) {
                return livro;
            }
        }
        throw new NaoEncontradoException("Livro com ISBN " + isbn + " não encontrado!");
    }
    //----------------------------------------------------------------------------------------------



    //----------------------------------------------------------------------------------------------
    //Busca um livro por autor e titulo
    @Override
    public List<Livro> buscarPorTituloOuAutor(String titulo, String autor) {

        // Se nenhum filtro foi enviado retorna lista vazia
        boolean tituloVazio = (titulo == null || titulo.isBlank());
        boolean autorVazio  = (autor == null  || autor.isBlank());

        if (tituloVazio && autorVazio) {
            return new ArrayList<>();
        }

        List<Livro> encontrados = new ArrayList<>();

        for (Livro livro : livros) {

            boolean combinaTitulo = false;
            boolean combinaAutor  = false;

            if (!tituloVazio) {
                combinaTitulo = livro.getTitulo().toLowerCase()
                        .contains(titulo.toLowerCase());
            }

            if (!autorVazio) {
                combinaAutor = livro.getAutor().toLowerCase()
                        .contains(autor.toLowerCase());
            }

            if (combinaTitulo || combinaAutor) {
                encontrados.add(livro);
            }
        }

        return encontrados;
    }
    //----------------------------------------------------------------------------------------------



    //----------------------------------------------------------------------------------------------
    //Busca por todos os livros
    @Override
    public List<Livro> buscarTodosOsLivros() {
        return new ArrayList<>(livros);
    }
    //----------------------------------------------------------------------------------------------



    //----------------------------------------------------------------------------------------------
    //Atualiza um livro usando ID
    @Override
    public void atualizarLivro(Livro livroAtualizado) {
        for (int i = 0; i < livros.size(); i++) {
            if (livros.get(i).getIsbn().equals(livroAtualizado.getIsbn())) {
                livros.set(i, livroAtualizado);
                System.out.println("Livro com ISBN " + livroAtualizado.getIsbn() + " atualizado com sucesso!");
                return;
            }
        }
        throw new NaoEncontradoException("Livro com ISBN " + livroAtualizado.getIsbn() + " não encontrado para atualização!");
    }
    //----------------------------------------------------------------------------------------------



    //----------------------------------------------------------------------------------------------
    //Remove um livro por ID
    @Override
    public void removerLivro(Livro livroASerRemovido) {

        String isbn = livroASerRemovido.getIsbn();

        for (int i = 0; i < livros.size(); i++) {
            if (livros.get(i).getIsbn().equals(isbn)) {
                livros.remove(i);
                System.out.println("Livro com ISBN " + isbn + " removido com sucesso!");
                return;
            }
        }

        throw new NaoEncontradoException("Livro com ISBN " + isbn + " não encontrado para remoção!");
    }
    //----------------------------------------------------------------------------------------------

}
