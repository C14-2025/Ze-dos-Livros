package br.c14lab.biblioteca;

import br.c14lab.biblioteca.exceptions.UsuarioNaoEncontradoException;
import br.c14lab.biblioteca.implementacao.UsuarioIMPL;
import br.c14lab.biblioteca.model.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioIMPLIT {

    @Mock
    private DataSource mockDataSource;
    @Mock
    private Connection mockConnection;
    @Mock
    private PreparedStatement mockStatement;
    @Mock
    private ResultSet mockResultSet;

    @InjectMocks
    private UsuarioIMPL usuarioService;

    @Test
    void testMockAdicionarUsuarioComSucesso() throws SQLException {
        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);

        Usuario novoUsuario = new Usuario("1", "carlos",
                "carlos@gmail.com", "123456789", "endereco");

        usuarioService.adicionarUsuario(novoUsuario);

        verify(mockStatement, times(1)).executeUpdate();
        verify(mockStatement).setString(1, "1");
        verify(mockStatement).setString(2, "carlos");
    }

    @Test
    void testRemoverUsuarioComSucesso() throws SQLException {
        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);

        when(mockStatement.executeUpdate()).thenReturn(1);

        usuarioService.removerUsuario("ID_EXISTENTE");

        verify(mockStatement, times(1)).executeUpdate();
    }

    @Test
    void testBuscarPorIdNaoEncontrado() throws SQLException {

        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);

        assertThrows(UsuarioNaoEncontradoException.class, () -> {
            usuarioService.buscarPorId("ID_INEXISTENTE");
        });
    }

    @Test
    void testAtualizarUsuarioComSucesso() throws SQLException {

        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(1);

        Usuario usuario = new Usuario("ID_EXISTENTE","Novo Nome", "novoemail@test.com", "1100000000", "Rua Test");
        usuarioService.atualizarUsuario(usuario);
        verify(mockStatement, times(1)).executeUpdate();
    }

    @Test
    void testAtualizarUsuarioNaoEncontrado() throws SQLException {

        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(0);

        Usuario usuario = new Usuario("ID_INEXISTENTE", "Outro_nome", "emailincorreto@test.com", "1111111111", "Av Wrong");
        assertThrows(UsuarioNaoEncontradoException.class, () -> usuarioService.atualizarUsuario(usuario));
    }

    @Test
    void testBuscarPorIdComSucesso() throws SQLException {
        Usuario usuarioEsperado = new Usuario("2","Mariano", "mariano@hotmail.com", "921325356", "Avenida Central");

        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);

        // Resposta dinâmica para cada coluna
        when(mockResultSet.getString(anyString())).thenAnswer(invocation -> {
            String coluna = invocation.getArgument(0, String.class);
            switch (coluna) {
                case "id": return usuarioEsperado.getId();
                case "nome": return usuarioEsperado.getNome();
                case "email": return usuarioEsperado.getEmail();
                case "telefone": return usuarioEsperado.getTelefone();
                case "endereco": return usuarioEsperado.getEndereco();
                default: return null;
            }
        });

        Usuario usuarioEncontrado = usuarioService.buscarPorId("2");

        assertNotNull(usuarioEncontrado);
        assertEquals("Mariano", usuarioEncontrado.getNome());
        assertEquals("mariano@hotmail.com", usuarioEncontrado.getEmail());
        assertEquals("921325356", usuarioEncontrado.getTelefone());
        assertEquals("Avenida Central", usuarioEncontrado.getEndereco());
    }



    @Test
            void testRemoverUsuarioNaoEncontrado() throws SQLException {
        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);

        when(mockStatement.executeUpdate()).thenReturn(0);

        assertThrows(UsuarioNaoEncontradoException.class,
                () -> usuarioService.removerUsuario("ID_INEXISTENTE"));

        verify(mockStatement, times(1)).executeUpdate();
    }

    @Test
    void testBuscarPorNomeComSucesso() throws SQLException {
        Usuario usuarioEsperado = new Usuario("2","Mariano", "mariano@hotmail.com", "921325356", "Avenida Central");

        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);

        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getString("id")).thenReturn(usuarioEsperado.getId());
        when(mockResultSet.getString("nome")).thenReturn(usuarioEsperado.getNome());
        when(mockResultSet.getString("email")).thenReturn(usuarioEsperado.getEmail());
        when(mockResultSet.getString("telefone")).thenReturn(usuarioEsperado.getTelefone());
        when(mockResultSet.getString("endereco")).thenReturn(usuarioEsperado.getEndereco());

        Usuario usuario = usuarioService.buscarPorNome("Mariano");

        assertNotNull(usuario);
        assertEquals("2", usuario.getId());
        assertEquals("Mariano", usuario.getNome());
        assertEquals("mariano@hotmail.com", usuario.getEmail());
        assertEquals("921325356", usuario.getTelefone());
        assertEquals("Avenida Central", usuario.getEndereco());


    }


}
