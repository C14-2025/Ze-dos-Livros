package br.c14lab.biblioteca.implementacao;

import br.c14lab.biblioteca.implementacao.interfaces.LivroRegras;
import br.c14lab.biblioteca.exceptions.LivroNaoEncontradoException;
import br.c14lab.biblioteca.model.Livro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LivroIMPL implements LivroRegras {

    //Atributos  ------------------------------------------------------------------------------------
    private final Map<String, Livro> livros = new HashMap<>();
    //-------------------------------------------------------------------------------------------------


    //Métodos ------------------------------------------------------------------------------------
    @Override
    public void adicionarLivro(Livro livro){
        livros.put(livro.getIsbn(), livro);
    }



    @Override
    public Livro buscarPorIsbn(String isbn){
        return livros.get(isbn);
    }



    @Override
    public List<Livro> buscarTodosOsLivros(){
        return new ArrayList<>(livros.values());
    }


    @Override
    public void atualizarLivro(Livro livroAtualizado) {
        String isbn = livroAtualizado.getIsbn();
        if(!livros.containsKey(isbn)){
            throw new LivroNaoEncontradoException("Livro com ISBN " +
            isbn + " não encontrado.");
        }

        livros.put(isbn, livroAtualizado);
    }



    @Override
    public void removerLivro(Livro livroASerRemovido) {
        String isbn = livroASerRemovido.getIsbn();
        if(!livros.containsKey(isbn)){
            throw new LivroNaoEncontradoException("Livro com ISBN " +
                    isbn + " não encontrado para remoção.");
        }

        livros.remove(isbn);
        System.out.println("Livro " + isbn + " removido com sucesso.");
    }



    @Override
    public List<Livro> buscarPorTituloOuAutor(String titulo, String autor) {
        return List.of();
    }
    //-------------------------------------------------------------------------------------------------

}
