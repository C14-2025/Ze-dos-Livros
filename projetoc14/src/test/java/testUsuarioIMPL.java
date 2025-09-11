import br.c14lab.biblioteca.exceptions.UsuarioNaoEncontradoException;
import br.c14lab.biblioteca.implementacao.UsuarioIMPL;
import br.c14lab.biblioteca.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class testUsuarioIMPL {
    UsuarioIMPL usuarioService;
    Usuario user;

    @BeforeEach
    public void setup(){
        this.usuarioService = new UsuarioIMPL();
        this.user = new Usuario("AG", "carlos@gmail.com", "carlos",
                "123456789", "endereco");
        usuarioService.adicionarUsuario(user);
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
}

