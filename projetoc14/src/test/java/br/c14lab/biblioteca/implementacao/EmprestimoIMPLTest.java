package br.c14lab.biblioteca.implementacao;

import br.c14lab.biblioteca.exceptions.NaoEncontradoException;
import br.c14lab.biblioteca.exceptions.RegistroDuplicadoException;
import br.c14lab.biblioteca.model.Emprestimo;
import br.c14lab.biblioteca.model.Livro;
import br.c14lab.biblioteca.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmprestimoIMPLTest {

    private UsuarioIMPL usuarioIMPL;
    private LivroIMPL livroIMPL;
    private EmprestimoIMPL emprestimoIMPL;

    @BeforeEach
    void setUp() {
        usuarioIMPL = new UsuarioIMPL();
        livroIMPL = new LivroIMPL();
        emprestimoIMPL = new EmprestimoIMPL(usuarioIMPL, livroIMPL);
    }

    // Testes de adição
    //----------------------------------------------------------------------------------------------
    @Test
    void adicionarEmprestimoSimples() {
        usuarioIMPL.guardarUsuario(new Usuario(1, "Joao"));
        livroIMPL.guardarLivro(new Livro("ISBN1", "Livro", "Autor", 3));
        Emprestimo emp = new Emprestimo(1, 1, "ISBN1");
        emprestimoIMPL.guardarEmprestimo(emp);
        assertEquals(1, emprestimoIMPL.buscarTodosOsEmprestimos().size());
    }

    @Test
    void adicionarEmprestimoComData() {
        usuarioIMPL.guardarUsuario(new Usuario(2, "Maria"));
        livroIMPL.guardarLivro(new Livro("ISBN2", "Livro2", "Autor", 5));
        Emprestimo emp = new Emprestimo(2, 2, "ISBN2");
        emp.setDataEmprestimo(LocalDate.now());
        emprestimoIMPL.guardarEmprestimo(emp);
        assertNotNull(emprestimoIMPL.buscarEmprestimoPorID(2).getDataEmprestimo());
    }

    @Test
    void adicionarVariosEmprestimos() {
        usuarioIMPL.guardarUsuario(new Usuario(3, "Carlos"));
        usuarioIMPL.guardarUsuario(new Usuario(4, "Ana"));
        livroIMPL.guardarLivro(new Livro("ISBN3", "Livro3", "Autor", 5));
        emprestimoIMPL.guardarEmprestimo(new Emprestimo(3, 3, "ISBN3"));
        emprestimoIMPL.guardarEmprestimo(new Emprestimo(4, 4, "ISBN3"));
        assertEquals(2, emprestimoIMPL.buscarTodosOsEmprestimos().size());
    }

    @Test
    void adicionarEmprestimoQuantidadeAlta() {
        usuarioIMPL.guardarUsuario(new Usuario(5, "Rita"));
        livroIMPL.guardarLivro(new Livro("ISBN4", "Livro4", "Autor", 100));
        Emprestimo emp = new Emprestimo(5, 5, "ISBN4");
        emprestimoIMPL.guardarEmprestimo(emp);
        assertEquals(1, emprestimoIMPL.buscarTodosOsEmprestimos().size());
    }
    //----------------------------------------------------------------------------------------------



    // Testes de retorno nulo
    //----------------------------------------------------------------------------------------------
    @Test
    void listaEmprestimosVaziaInicialmente() {
        assertTrue(emprestimoIMPL.buscarTodosOsEmprestimos().isEmpty());
    }

    @Test
    void listaEmprestimosAtivosVazia() {
        assertTrue(emprestimoIMPL.buscarEmprestimosAtivos().isEmpty());
    }

    @Test
    void buscarPorUsuarioSemEmprestimosRetornaNulo() {
        try {
            emprestimoIMPL.buscarPorUsuario(999);
            fail();
        } catch (NaoEncontradoException e) {
            assertTrue(true);
        }
    }
    //----------------------------------------------------------------------------------------------



    // Teste de exceção
    //----------------------------------------------------------------------------------------------
    @Test
    void erroAoBuscarEmprestimoInexistente() {
        try {
            emprestimoIMPL.buscarEmprestimoPorID(9999);
            fail();
        } catch (NaoEncontradoException e) {
            assertTrue(true);
        }
    }

    @Test
    void erroAoRegistrarDuplicado() {
        usuarioIMPL.guardarUsuario(new Usuario(6, "Paulo"));
        livroIMPL.guardarLivro(new Livro("ISBN5", "Livro5", "Autor", 4));
        emprestimoIMPL.guardarEmprestimo(new Emprestimo(6, 6, "ISBN5"));
        try {
            emprestimoIMPL.guardarEmprestimo(new Emprestimo(6, 6, "ISBN5"));
            fail();
        } catch (RegistroDuplicadoException e) {
            assertTrue(true);
        }
    }
    //----------------------------------------------------------------------------------------------



    // Testes de Exceção
    //----------------------------------------------------------------------------------------------
    @Test
    void impedirEmprestimoComUsuarioInexistente() {
        livroIMPL.guardarLivro(new Livro("ISBN6", "Livro6", "Autor", 3));
        Emprestimo emp = new Emprestimo(7, 999, "ISBN6");
        emprestimoIMPL.guardarEmprestimo(emp);
        assertTrue(emprestimoIMPL.buscarTodosOsEmprestimos().isEmpty());
    }

    @Test
    void impedirEmprestimoComLivroInexistente() {
        usuarioIMPL.guardarUsuario(new Usuario(7, "Luiz"));
        Emprestimo emp = new Emprestimo(8, 7, "XNAOEXISTE");
        emprestimoIMPL.guardarEmprestimo(emp);
        assertTrue(emprestimoIMPL.buscarTodosOsEmprestimos().isEmpty());
    }

    @Test
    void impedirEmprestimoSemEstoque() {
        usuarioIMPL.guardarUsuario(new Usuario(8, "Julia"));
        livroIMPL.guardarLivro(new Livro("ISBN7", "Livro7", "Autor", 0));
        Emprestimo emp = new Emprestimo(9, 8, "ISBN7");
        emprestimoIMPL.guardarEmprestimo(emp);
        assertTrue(emprestimoIMPL.buscarTodosOsEmprestimos().isEmpty());
    }
    //----------------------------------------------------------------------------------------------



    // Testes Negativos
    //----------------------------------------------------------------------------------------------
    @Test
    void atualizarEmprestimoInexistenteNaoFalha() {
        Emprestimo emp = new Emprestimo(999, 1, "ISBNX");
        try {
            emprestimoIMPL.atualizarEmprestimo(emp);
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void devolverLivroInexistenteNaoFalha() {
        Emprestimo emp = new Emprestimo(888, 2, "FANTASMA");
        try {
            emprestimoIMPL.devolverExemplar(emp);
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void devolverRestauraQuantidadeCorretamente() {
        usuarioIMPL.guardarUsuario(new Usuario(9, "Rogério"));
        livroIMPL.guardarLivro(new Livro("ISBN8", "Livro8", "Autor", 1));
        Emprestimo emp = new Emprestimo(10, 9, "ISBN8");
        emprestimoIMPL.guardarEmprestimo(emp);
        emprestimoIMPL.devolverExemplar(emp);
        assertEquals(1, livroIMPL.buscarPorIsbn("ISBN8").getQuantidadeDisponivel());
    }

    @Test
    void buscarSomenteEmprestimosAtivos() {
        usuarioIMPL.guardarUsuario(new Usuario(10, "Marcos"));
        livroIMPL.guardarLivro(new Livro("ISBN9", "Livro9", "Autor", 3));
        Emprestimo e1 = new Emprestimo(11, 10, "ISBN9");
        Emprestimo e2 = new Emprestimo(12, 10, "ISBN9");
        emprestimoIMPL.guardarEmprestimo(e1);
        emprestimoIMPL.guardarEmprestimo(e2);
        e1.setDevolvido(true);
        List<Emprestimo> ativos = emprestimoIMPL.buscarEmprestimosAtivos();
        assertEquals(1, ativos.size());
    }

    @Test
    void atualizarEmprestimoDefineDevolucao() {
        usuarioIMPL.guardarUsuario(new Usuario(11, "Helena"));
        livroIMPL.guardarLivro(new Livro("ISBN10", "Livro10", "Autor", 5));
        Emprestimo emp = new Emprestimo(13, 11, "ISBN10");
        emprestimoIMPL.guardarEmprestimo(emp);
        Emprestimo novo = new Emprestimo(13, 11, "ISBN10");
        novo.setDevolvido(true);
        emprestimoIMPL.atualizarEmprestimo(novo);
        assertTrue(emprestimoIMPL.buscarEmprestimoPorID(13).isDevolvido());
    }

    @Test
    void naoPermiteEmprestarQuandoZerado() {
        usuarioIMPL.guardarUsuario(new Usuario(12, "Sofia"));
        usuarioIMPL.guardarUsuario(new Usuario(13, "Igor"));
        livroIMPL.guardarLivro(new Livro("ISBN11", "Livro11", "Autor", 1));
        emprestimoIMPL.guardarEmprestimo(new Emprestimo(14, 12, "ISBN11"));
        emprestimoIMPL.guardarEmprestimo(new Emprestimo(15, 13, "ISBN11"));
        assertEquals(1, emprestimoIMPL.buscarTodosOsEmprestimos().size());
    }

    @Test
    void registrarDataEmprestimoCorretamente() {
        usuarioIMPL.guardarUsuario(new Usuario(14, "Juliano"));
        livroIMPL.guardarLivro(new Livro("ISBN12", "Livro12", "Autor", 5));
        Emprestimo emp = new Emprestimo(16, 14, "ISBN12");
        emp.setDataEmprestimo(LocalDate.now());
        emprestimoIMPL.guardarEmprestimo(emp);
        assertNotNull(emprestimoIMPL.buscarEmprestimoPorID(16).getDataEmprestimo());
    }

    @Test
    void buscarEmprestimoExistentePorId() {
        usuarioIMPL.guardarUsuario(new Usuario(15, "Diego"));
        livroIMPL.guardarLivro(new Livro("ISBN13", "Livro13", "Autor", 2));
        Emprestimo emp = new Emprestimo(17, 15, "ISBN13");
        emprestimoIMPL.guardarEmprestimo(emp);
        assertEquals(17, emprestimoIMPL.buscarEmprestimoPorID(17).getId());
    }

    @Test
    void buscarEmprestimoPorUsuarioExistente() {
        usuarioIMPL.guardarUsuario(new Usuario(16, "Tania"));
        livroIMPL.guardarLivro(new Livro("ISBN14", "Livro14", "Autor", 2));
        Emprestimo emp = new Emprestimo(18, 16, "ISBN14");
        emprestimoIMPL.guardarEmprestimo(emp);
        assertEquals("ISBN14", emprestimoIMPL.buscarPorUsuario(16).getLivroIsbn());
    }

    @Test
    void devolverSemAlterarQuandoDevolvidoAntes() {
        usuarioIMPL.guardarUsuario(new Usuario(17, "Elaine"));
        livroIMPL.guardarLivro(new Livro("ISBN15", "Livro15", "Autor", 3));
        Emprestimo emp = new Emprestimo(19, 17, "ISBN15");
        emp.setDevolvido(true);
        emprestimoIMPL.guardarEmprestimo(emp);
        emprestimoIMPL.devolverExemplar(emp);
        assertEquals(3, livroIMPL.buscarPorIsbn("ISBN15").getQuantidadeDisponivel());
    }

    //----------------------------------------------------------------------------------------------
}
