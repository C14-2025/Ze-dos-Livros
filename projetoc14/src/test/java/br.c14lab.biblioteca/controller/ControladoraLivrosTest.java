package br.c14lab.biblioteca.controller;

import br.c14lab.biblioteca.exceptions.NaoEncontradoException;
import br.c14lab.biblioteca.exceptions.RegistroDuplicadoException;
import br.c14lab.biblioteca.implementacao.LivroIMPL;
import br.c14lab.biblioteca.model.Livro;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ControladoraLivrosTest {


    //----------------------------------------------------------------------------------------------

    @Test
    public void deveAdicionarLivroComSucesso() throws Exception {
        // Arrange
        LivroIMPL mockImpl = mock(LivroIMPL.class);
        Scanner sc = mock(Scanner.class);

        when(sc.nextLine()).thenReturn(
                "123-ABC",         // ISBN
                "O Hobbit",        // Título
                "J.R.R.Tolkien",   // Autor
                "Allen & Unwin",   // Editora
                "Fantasia"         // Categoria
        );

        when(sc.nextInt()).thenReturn(
                1937,  // Ano de publicação
                3      // Quantidade
        );

        ControladoraLivros ctrl = new ControladoraLivros(mockImpl, sc);

        //verificar criação correta do objeto
        ArgumentCaptor<Livro> captor = ArgumentCaptor.forClass(Livro.class);

        // Act
        ctrl.adicionarLivro();

        // Assert
        verify(mockImpl).guardarLivro(captor.capture());
        Livro novo = captor.getValue();

        assertEquals("123-ABC", novo.getIsbn());
        assertEquals("O Hobbit", novo.getTitulo());
        assertEquals("J.R.R.Tolkien", novo.getAutor());
        assertEquals("Allen & Unwin", novo.getEditora());
        assertEquals(1937, novo.getAnoPublicacao());
        assertEquals(3, novo.getQuantidadeDisponivel());
        assertEquals("Fantasia", novo.getCategoria());
    }

    //----------------------------------------------------------------------------------------------

    @Test
    void deveTratarLivroDuplicado() throws RegistroDuplicadoException {
        LivroIMPL mockImpl = mock(LivroIMPL.class);
        Scanner sc = mock(Scanner.class);

        when(sc.nextLine()).thenReturn("123", "Tit", "Aut", "Ed", "Cat");
        when(sc.nextInt()).thenReturn(2020, 1);

        doThrow(new RegistroDuplicadoException("Duplicado"))
                .when(mockImpl)
                .guardarLivro(any(Livro.class));

        ControladoraLivros ctrl = new ControladoraLivros(mockImpl, sc);

        ctrl.adicionarLivro();

        verify(mockImpl).guardarLivro(any(Livro.class));
    }

    //----------------------------------------------------------------------------------------------


    @Test
    void deveBuscarPorIsbn() throws Exception {
        LivroIMPL mockImpl = mock(LivroIMPL.class);
        Scanner sc = mock(Scanner.class);

        when(sc.nextLine()).thenReturn("123");

        Livro livro = new Livro("123", "T", "A", "E", 2020, 1, "Cat");

        when(mockImpl.buscarPorIsbn("123")).thenReturn(livro);

        ControladoraLivros ctrl = new ControladoraLivros(mockImpl, sc);

        ctrl.buscarPorIsbn();

        verify(mockImpl, times(1)).buscarPorIsbn("123");
    }

    //----------------------------------------------------------------------------------------------


    @Test
    void deveTratarBuscarIsbnNaoEncontrado() throws Exception {
        LivroIMPL mockImpl = mock(LivroIMPL.class);
        Scanner mockScanner = mock(Scanner.class);

        when(mockScanner.nextLine()).thenReturn("000");

        when(mockImpl.buscarPorIsbn("000"))
                .thenThrow(new NaoEncontradoException("ISBN não encontrado"));

        ControladoraLivros ctrl = new ControladoraLivros(mockImpl, mockScanner);

        ctrl.buscarPorIsbn();

        verify(mockImpl).buscarPorIsbn("000");
    }

    @Test
    void deveBuscarPorTituloOuAutor() {
        LivroIMPL mockImpl = mock(LivroIMPL.class);
        Scanner mockScanner = mock(Scanner.class);

        when(mockScanner.nextLine())
                .thenReturn("Titulo")
                .thenReturn("Autor");

        Livro livro = new Livro("1", "T", "A", "E", 2020, 1, "Cat");
        when(mockImpl.buscarPorTituloOuAutor("Titulo", "Autor"))
                .thenReturn(List.of(livro));

        ControladoraLivros ctrl = new ControladoraLivros(mockImpl, mockScanner);

        ctrl.buscarPorTituloOuAutor();

        verify(mockImpl).buscarPorTituloOuAutor("Titulo", "Autor");
    }

    @Test
    void deveMostrarTodosLivros() {
        LivroIMPL mockImpl = mock(LivroIMPL.class);
        Scanner mockScanner = mock(Scanner.class);
        List<Livro> livros = new ArrayList<Livro>();

        Livro livro1 = new Livro("1", "T", "A", "E", 2020, 1, "Cat");
        Livro livro2 = new Livro("2", "T2", "A2", "E", 2021, 1, "Cat");
        livros.add(livro1);
        livros.add(livro2);

        when(mockImpl.buscarTodosOsLivros()).thenReturn(livros);

        ControladoraLivros ctrl = new ControladoraLivros(mockImpl, mockScanner);

        ctrl.mostrarTodosLivros();

        verify(mockImpl).buscarTodosOsLivros();
    }

    @Test
    void deveAtualizarLivro() throws Exception {
        LivroIMPL mockImpl = mock(LivroIMPL.class);
        Scanner mockScanner = mock(Scanner.class);

        when(mockScanner.nextLine())
                .thenReturn("123")     // isbn
                .thenReturn("Novo T")
                .thenReturn("Novo A")
                .thenReturn("Nova E")
                .thenReturn("Nova Cat");

        when(mockScanner.nextInt())
                .thenReturn(2021) // ano
                .thenReturn(5);   // quantidade

        ControladoraLivros ctrl = new ControladoraLivros(mockImpl, mockScanner);

        ArgumentCaptor<Livro> livroCaptor = ArgumentCaptor.forClass(Livro.class);

        ctrl.atualizarLivro();

        verify(mockImpl).atualizarLivro(livroCaptor.capture());
        Livro atualizado = livroCaptor.getValue();

        // valida campos
        assertEquals("123", atualizado.getIsbn());
        assertEquals("Novo T", atualizado.getTitulo());
        assertEquals("Novo A", atualizado.getAutor());
        assertEquals("Nova E", atualizado.getEditora());
        assertEquals(2021, atualizado.getAnoPublicacao());
        assertEquals(5, atualizado.getQuantidadeDisponivel());
        assertEquals("Nova Cat", atualizado.getCategoria());

    }

    //=========================================================================

    @Test
    void deveRemoverLivro() throws Exception {
        LivroIMPL mockImpl = mock(LivroIMPL.class);
        Scanner mockScanner = mock(Scanner.class);

        when(mockScanner.nextLine()).thenReturn("999");

        ControladoraLivros ctrl = new ControladoraLivros(mockImpl, mockScanner);

        ArgumentCaptor<Livro> captor = ArgumentCaptor.forClass(Livro.class);

        ctrl.removerLivro();

        verify(mockImpl).removerLivro(captor.capture());
        Livro remover = captor.getValue();

        assertEquals("999", remover.getIsbn());
        assertEquals("", remover.getTitulo());
        assertEquals("", remover.getAutor());
        assertEquals("", remover.getEditora());
        assertEquals(0, remover.getAnoPublicacao());
        assertEquals(0, remover.getQuantidadeDisponivel());
        assertEquals("", remover.getCategoria());
    }


}
