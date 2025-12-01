package br.c14lab.biblioteca.implementacao;

import br.c14lab.biblioteca.exceptions.NaoEncontradoException;
import br.c14lab.biblioteca.exceptions.RegistroDuplicadoException;
import br.c14lab.biblioteca.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioIMPLTest {

    private UsuarioIMPL usuarioIMPL;

    @BeforeEach
    void setUp() {
        usuarioIMPL = new UsuarioIMPL();
    }


    // Testes de adição
    //----------------------------------------------------------------------------------------------
    @Test
    void deveAdicionarUsuarioComSucesso() {
        usuarioIMPL.guardarUsuario(new Usuario(1, "Carlos"));
        assertEquals(1, usuarioIMPL.mostrarTodosUsuarios().size());
    }

    @Test
    void deveAdicionarMultiplosUsuariosComSucesso() {
        usuarioIMPL.guardarUsuario(new Usuario(1, "Ana"));
        usuarioIMPL.guardarUsuario(new Usuario(2, "Bia"));
        usuarioIMPL.guardarUsuario(new Usuario(3, "Caio"));
        assertEquals(3, usuarioIMPL.mostrarTodosUsuarios().size());
    }

    @Test
    void deveLancarExcecaoQuandoAdicionarUsuarioComIdDuplicado() {
        usuarioIMPL.guardarUsuario(new Usuario(1, "João"));
        try {
            usuarioIMPL.guardarUsuario(new Usuario(1, "Maria"));
            fail();
        } catch (RegistroDuplicadoException e) {
            assertTrue(true);
        }
    }

    @Test
    void naoDevePermitirDoisUsuariosComMesmoIdMesmoNomeDiferente() {
        usuarioIMPL.guardarUsuario(new Usuario(7, "A"));
        try {
            usuarioIMPL.guardarUsuario(new Usuario(7, "B"));
            fail();
        } catch (RegistroDuplicadoException e) {
            assertTrue(true);
        }
    }

    @Test
    void deveAdicionarUsuarioComNomeGrande() {
        usuarioIMPL.guardarUsuario(new Usuario(50, "NOME MUITO GRANDE TESTE"));
        assertEquals(1, usuarioIMPL.mostrarTodosUsuarios().size());
    }
    //----------------------------------------------------------------------------------------------



    // Testes de retorno nulo
    //----------------------------------------------------------------------------------------------
    @Test
    void mostrarTodosUsuariosRetornaListaVaziaInicialmente() {
        assertTrue(usuarioIMPL.mostrarTodosUsuarios().isEmpty());
    }

    @Test
    void listaUsuariosNaoEhNula() {
        assertNotNull(usuarioIMPL.mostrarTodosUsuarios());
    }

    @Test
    void listaRefleteInsercoesCorretamente() {
        usuarioIMPL.guardarUsuario(new Usuario(10, "X"));
        usuarioIMPL.guardarUsuario(new Usuario(20, "Y"));
        assertEquals(2, usuarioIMPL.mostrarTodosUsuarios().size());
    }

    @Test
    void listaNaoModificaQuandoNenhumUsuarioInserido() {
        assertEquals(0, usuarioIMPL.mostrarTodosUsuarios().size());
    }
    //----------------------------------------------------------------------------------------------



    // Teste de exceção
    //----------------------------------------------------------------------------------------------
    @Test
    void deveLancarExcecaoAoBuscarPorIdInexistente() {
        try {
            usuarioIMPL.buscarPorId(99);
            fail();
        } catch (NaoEncontradoException e) {
            assertTrue(true);
        }
    }

    @Test
    void deveLancarExcecaoAoBuscarPorNomeInexistente() {
        try {
            usuarioIMPL.buscarPorNome("Fulano");
            fail();
        } catch (NaoEncontradoException e) {
            assertTrue(true);
        }
    }

    @Test
    void buscarComStringVaziaLancaExcecao() {
        try {
            usuarioIMPL.buscarPorNome("");
            fail();
        } catch (NaoEncontradoException e) {
            assertTrue(true);
        }
    }

    @Test
    void buscarComNomeNuloLancaExcecao() {
        try {
            usuarioIMPL.buscarPorNome(null);
            fail();
        } catch (NaoEncontradoException e) {
            assertTrue(true);
        }
    }

    @Test
    void buscarComIdNegativoLancaExcecao() {
        try {
            usuarioIMPL.buscarPorId(-1);
            fail();
        } catch (NaoEncontradoException e) {
            assertTrue(true);
        }
    }
    //----------------------------------------------------------------------------------------------



    // Testes de Exceção
    //----------------------------------------------------------------------------------------------
    @Test
    void deveBuscarUsuarioPorIdExistente() {
        usuarioIMPL.guardarUsuario(new Usuario(10, "Roberto"));
        Usuario encontrado = usuarioIMPL.buscarPorId(10);
        assertEquals("Roberto", encontrado.getNome());
    }

    @Test
    void deveBuscarUsuarioPorNomeExistente() {
        usuarioIMPL.guardarUsuario(new Usuario(5, "Ana"));
        Usuario encontrado = usuarioIMPL.buscarPorNome("ana");
        assertEquals(5, encontrado.getId());
    }

    @Test
    void deveEncontrarUsuarioIndependenteDeMaiusculasOuMinusculas() {
        usuarioIMPL.guardarUsuario(new Usuario(12, "Pedro"));
        Usuario usuario = usuarioIMPL.buscarPorNome("PEDRO");
        assertEquals(12, usuario.getId());
    }
    //----------------------------------------------------------------------------------------------



    // Testes Negativos
    //----------------------------------------------------------------------------------------------
    @Test
    void atualizarUsuarioNaoModificaNadaQuandoIdNaoExiste() {
        usuarioIMPL.guardarUsuario(new Usuario(1, "Pedro"));
        usuarioIMPL.atualizarUsuario(new Usuario(2, "Novo Nome"));
        assertEquals("Pedro", usuarioIMPL.buscarPorId(1).getNome());
    }

    @Test
    void atualizarUsuarioModificaCorretamenteQuandoExiste() {
        usuarioIMPL.guardarUsuario(new Usuario(1, "Pedro"));
        usuarioIMPL.atualizarUsuario(new Usuario(1, "Pedro Atualizado"));
        assertEquals("Pedro Atualizado", usuarioIMPL.buscarPorId(1).getNome());
    }

    @Test
    void removerUsuarioNaoRemoveNadaQuandoIdNaoExiste() {
        usuarioIMPL.guardarUsuario(new Usuario(3, "Lucas"));
        usuarioIMPL.removerUsuario(999);
        assertEquals(1, usuarioIMPL.mostrarTodosUsuarios().size());
    }

    @Test
    void removerUsuarioExistenteRemoveComSucesso() {
        usuarioIMPL.guardarUsuario(new Usuario(11, "Clara"));
        usuarioIMPL.removerUsuario(11);
        assertTrue(usuarioIMPL.mostrarTodosUsuarios().isEmpty());
    }

    @Test
    void removerVariosUsuariosSeguidamenteFunciona() {
        usuarioIMPL.guardarUsuario(new Usuario(1, "A"));
        usuarioIMPL.guardarUsuario(new Usuario(2, "B"));
        usuarioIMPL.guardarUsuario(new Usuario(3, "C"));
        usuarioIMPL.removerUsuario(1);
        usuarioIMPL.removerUsuario(2);
        assertEquals(1, usuarioIMPL.mostrarTodosUsuarios().size());
    }

    @Test
    void atualizarSemInserirNenhumUsuarioNaoGeraErro() {
        usuarioIMPL.atualizarUsuario(new Usuario(1, "Ninguém"));
        assertTrue(usuarioIMPL.mostrarTodosUsuarios().isEmpty());
    }

    @Test
    void removerUsuarioComIdNegativoNaoRemoveNada() {
        usuarioIMPL.guardarUsuario(new Usuario(15, "Jose"));
        usuarioIMPL.removerUsuario(-5);
        assertEquals(1, usuarioIMPL.mostrarTodosUsuarios().size());
    }
    //----------------------------------------------------------------------------------------------
}