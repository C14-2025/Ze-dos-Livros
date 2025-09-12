package br.c14lab.biblioteca;
import br.c14lab.biblioteca.exceptions.UsuarioNaoEncontradoException;
import br.c14lab.biblioteca.implementacao.UsuarioIMPL;
import br.c14lab.biblioteca.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestUsuarioIMPL {
    UsuarioIMPL usuarioService;
    Usuario user1;
    Usuario user2;
    @BeforeEach
    public void setup(){
        this.usuarioService = new UsuarioIMPL();
        this.user1 = new Usuario("AG", "carlos@gmail.com", "carlos",
                "123456789", "endereco");
        this.user2 = new Usuario("BH", "mariano@hotmail.com", "Mariano",
                "921325356", "Avenida Central");
        usuarioService.adicionarUsuario(user1);
        usuarioService.adicionarUsuario(user2);
    }
    @Test
    public void testBuscaPorNome(){
        String nomeEsperado = "carlos";
        Usuario result = usuarioService.buscarPorNome(nomeEsperado);
        assertEquals(nomeEsperado, result.getNome());
    }

    @Test
    public void testBuscaPorNome_UsuarioNaoEncontrado(){
        assertThrows(UsuarioNaoEncontradoException.class, () -> {
            usuarioService.buscarPorNome("josé");
        });
    }

    @Test
    public void testRemoverUsuario(){
        assertEquals(2, usuarioService.quantidadeUsuarios());
        usuarioService.removerUsuario("BH");
        assertEquals(1, usuarioService.quantidadeUsuarios());

    }

    @Test
    public void testRemoverUsuario_inexistente(){
        assertEquals(2, usuarioService.quantidadeUsuarios());
        assertThrows(UsuarioNaoEncontradoException.class,
                ()-> usuarioService.removerUsuario("MM"));
        assertEquals(2, usuarioService.quantidadeUsuarios());

    }

    @Test
    public void testRemoverUsuario_null(){
        assertEquals(2, usuarioService.quantidadeUsuarios());
        assertThrows(UsuarioNaoEncontradoException.class,
                () -> usuarioService.removerUsuario(null));

    }

    @Test
    public void testRemoverUsuario_todos(){
        assertEquals(2,usuarioService.quantidadeUsuarios());
        usuarioService.removerUsuario("AG");
        usuarioService.removerUsuario("BH");
        assertEquals(0, usuarioService.quantidadeUsuarios());
    }

    @Test
    public void testRemoverUsuario_novamente(){
        assertEquals(2, usuarioService.quantidadeUsuarios());
        usuarioService.removerUsuario("BH");
        assertThrows(UsuarioNaoEncontradoException.class,
                () -> usuarioService.removerUsuario("BH"));

    }
}

