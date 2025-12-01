package br.c14lab.biblioteca.implementacao;

import br.c14lab.biblioteca.exceptions.NaoEncontradoException;
import br.c14lab.biblioteca.exceptions.RegistroDuplicadoException;
import br.c14lab.biblioteca.model.Livro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LivroIMPLTest {

    private LivroIMPL livroIMPL;

    @BeforeEach
    void setUp() {
        livroIMPL = new LivroIMPL();
    }


    // Testes de adição
    //----------------------------------------------------------------------------------------------
    @Test
    void deveAdicionarLivroComSucesso() {
        livroIMPL.guardarLivro(new Livro("1", "A", "B", 1));
        assertEquals(1, livroIMPL.buscarTodosOsLivros().size());
    }

    @Test
    void deveAdicionarVariosLivros() {
        livroIMPL.guardarLivro(new Livro("1", "A", "B", 1));
        livroIMPL.guardarLivro(new Livro("2", "C", "D", 1));
        livroIMPL.guardarLivro(new Livro("3", "E", "F", 1));
        assertEquals(3, livroIMPL.buscarTodosOsLivros().size());
    }

    @Test
    void naoPodeAdicionarIsbnDuplicado() {
        livroIMPL.guardarLivro(new Livro("X", "A", "B", 1));
        try {
            livroIMPL.guardarLivro(new Livro("X", "C", "D", 1));
            fail();
        } catch (RegistroDuplicadoException e) {
            assertTrue(true);
        }
    }

    @Test
    void isbnDuplicadoMesmoComAutoresDiferentes() {
        livroIMPL.guardarLivro(new Livro("10", "A", "Autor1", 1));
        try {
            livroIMPL.guardarLivro(new Livro("10", "B", "Autor2", 1));
            fail();
        } catch (RegistroDuplicadoException e) {
            assertTrue(true);
        }
    }
    //----------------------------------------------------------------------------------------------



    // Testes de retorno nulo
    //----------------------------------------------------------------------------------------------
    @Test
    void listaInicialmenteVazia() {
        assertTrue(livroIMPL.buscarTodosOsLivros().isEmpty());
    }

    @Test
    void buscarTodosNaoRetornaListaNula() {
        assertNotNull(livroIMPL.buscarTodosOsLivros());
    }

    @Test
    void listaRetornaCopiaIndependente() {
        livroIMPL.guardarLivro(new Livro("1", "A", "B", 1));
        List<Livro> lista = livroIMPL.buscarTodosOsLivros();
        lista.clear();
        assertEquals(1, livroIMPL.buscarTodosOsLivros().size());
    }
    //----------------------------------------------------------------------------------------------



    // Testes de exceção
    //----------------------------------------------------------------------------------------------
    @Test
    void buscarPorIsbnInexistenteGeraExcecao() {
        try {
            livroIMPL.buscarPorIsbn("999");
            fail();
        } catch (NaoEncontradoException e) {
            assertTrue(true);
        }
    }

    @Test
    void buscarPorIsbnNuloGeraExcecao() {
        try {
            livroIMPL.buscarPorIsbn(null);
            fail();
        } catch (NaoEncontradoException e) {
            assertTrue(true);
        }
    }

    @Test
    void buscarPorIsbnVazioGeraExcecao() {
        try {
            livroIMPL.buscarPorIsbn("");
            fail();
        } catch (NaoEncontradoException e) {
            assertTrue(true);
        }
    }

    @Test
    void atualizarLivroInexistenteGeraExcecao() {
        try {
            livroIMPL.atualizarLivro(new Livro("X", "A", "B", 1));
            fail();
        } catch (NaoEncontradoException e) {
            assertTrue(true);
        }
    }

    @Test
    void removerLivroInexistenteGeraExcecao() {
        try {
            livroIMPL.removerLivro(new Livro("123", "T", "A", 1));
            fail();
        } catch (NaoEncontradoException e) {
            assertTrue(true);
        }
    }
    //----------------------------------------------------------------------------------------------



    // Testes de Exceção
    //----------------------------------------------------------------------------------------------
    @Test
    void deveBuscarLivroExistentePorIsbn() {
        livroIMPL.guardarLivro(new Livro("7", "Titulo1", "Autor1", 1));
        Livro l = livroIMPL.buscarPorIsbn("7");
        assertEquals("Titulo1", l.getTitulo());
    }

    @Test
    void buscarPorIsbnFuncionaMesmoComLetras() {
        livroIMPL.guardarLivro(new Livro("ABC-123", "X", "Y", 1));
        assertEquals("X", livroIMPL.buscarPorIsbn("ABC-123").getTitulo());
    }

    @Test
    void deveAtualizarLivroComSucesso() {
        livroIMPL.guardarLivro(new Livro("9", "Velho", "A", 1));
        livroIMPL.atualizarLivro(new Livro("9", "Novo", "A", 1));
        assertEquals("Novo", livroIMPL.buscarPorIsbn("9").getTitulo());
    }

    @Test
    void deveRemoverLivroComSucesso() {
        livroIMPL.guardarLivro(new Livro("50", "Y", "A", 1));
        livroIMPL.removerLivro(new Livro("50", "Y", "A", 1));
        assertTrue(livroIMPL.buscarTodosOsLivros().isEmpty());
    }
    //----------------------------------------------------------------------------------------------



    // Testes Negativos
    //----------------------------------------------------------------------------------------------
    @Test
    void atualizarComIsbnErradoNaoAfetaLista() {
        livroIMPL.guardarLivro(new Livro("1", "A", "B", 1));
        try {
            livroIMPL.atualizarLivro(new Livro("2", "C", "D", 1));
            fail();
        } catch (NaoEncontradoException e) {
            assertTrue(true);
        }
    }

    @Test
    void removerComIsbnErradoNaoRemoveNada() {
        livroIMPL.guardarLivro(new Livro("1", "A", "B", 1));
        try {
            livroIMPL.removerLivro(new Livro("2", "X", "Y", 1));
            fail();
        } catch (NaoEncontradoException e) {
            assertTrue(true);
        }
    }

    @Test
    void buscarPorTituloOuAutorSemFiltrosRetornaVazio() {
        livroIMPL.guardarLivro(new Livro("1", "A", "B", 1));
        List<Livro> r = livroIMPL.buscarPorTituloOuAutor("", "");
        assertTrue(r.isEmpty());
    }

    @Test
    void buscarPorTituloOuAutorComTudoNuloRetornaVazio() {
        livroIMPL.guardarLivro(new Livro("1", "A", "B", 1));
        List<Livro> r = livroIMPL.buscarPorTituloOuAutor(null, null);
        assertTrue(r.isEmpty());
    }

    @Test
    void buscarPorTituloOuAutorTituloNaoCoincideMasAutorCoincide() {
        livroIMPL.guardarLivro(new Livro("1", "Livro XPTO", "Autor Bom", 1));
        List<Livro> r = livroIMPL.buscarPorTituloOuAutor("nada a ver", "autor");
        assertEquals(1, r.size());
    }

    @Test
    void buscarPorTituloOuAutorTituloCoincideMasAutorNao() {
        livroIMPL.guardarLivro(new Livro("1", "Titulo Incrivel", "AAA", 1));
        List<Livro> r = livroIMPL.buscarPorTituloOuAutor("incrivel", "xyz");
        assertEquals(1, r.size());
    }

    @Test
    void buscarPorTituloOuAutorCoincideAmbos() {
        livroIMPL.guardarLivro(new Livro("1", "Java", "Oracle", 1));
        List<Livro> r = livroIMPL.buscarPorTituloOuAutor("java", "oracl");
        assertEquals(1, r.size());
    }

    @Test
    void buscarPorTituloOuAutorSemMatchRetornaVazio() {
        livroIMPL.guardarLivro(new Livro("1", "Livro XPTO", "Autor XPTO", 1));
        List<Livro> r = livroIMPL.buscarPorTituloOuAutor("nada", "nada");
        assertTrue(r.isEmpty());
    }

    @Test
    void buscarPorTituloOuAutorIgnoreCase() {
        livroIMPL.guardarLivro(new Livro("1", "Java Avançado", "Oracle", 1));
        List<Livro> r = livroIMPL.buscarPorTituloOuAutor("JAVA", "oracle");
        assertEquals(1, r.size());
    }
    //----------------------------------------------------------------------------------------------
}
