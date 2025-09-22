package br.c14lab.biblioteca;

import br.c14lab.biblioteca.exceptions.UsuarioNaoEncontradoException;
import br.c14lab.biblioteca.implementacao.UsuarioIMPL;
import br.c14lab.biblioteca.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
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
public class TestUsuarioIMPL {

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

        Usuario novoUsuario = new Usuario("1", "carlos@gmail.com",
                "carlos", "123456789", "endereco");

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



//    @Test
//    public void testBuscaUsuarioPorNome(){
//        String nomeEsperado = "carlos";
//        Usuario result = usuarioService.buscarPorNome(nomeEsperado);
//        assertEquals(nomeEsperado, result.getNome());
//    }
//
//    @Test
//    public void testBuscaPorNome_UsuarioNaoEncontrado(){
//        assertThrows(UsuarioNaoEncontradoException.class, () -> {
//            usuarioService.buscarPorNome("josé");
//        });
//    }
//
//    @Test
//    public void testRemoverUsuario(){
//        assertEquals(2, usuarioService.quantidadeUsuarios());
//        usuarioService.removerUsuario("BH");
//        assertEquals(1, usuarioService.quantidadeUsuarios());
//
//    }
//
//    @Test
//    public void testRemoverUsuario_inexistente(){
//        assertEquals(2, usuarioService.quantidadeUsuarios());
//        assertThrows(UsuarioNaoEncontradoException.class,
//                ()-> usuarioService.removerUsuario("MM"));
//        assertEquals(2, usuarioService.quantidadeUsuarios());
//
//    }
//
//    @Test
//    public void testRemoverUsuario_null(){
//        assertEquals(2, usuarioService.quantidadeUsuarios());
//        assertThrows(UsuarioNaoEncontradoException.class,
//                () -> usuarioService.removerUsuario(null));
//
//    }
//
//    @Test
//    public void testRemoverUsuario_todos(){
//        assertEquals(2,usuarioService.quantidadeUsuarios());
//        usuarioService.removerUsuario("AG");
//        usuarioService.removerUsuario("BH");
//        assertEquals(0, usuarioService.quantidadeUsuarios());
//    }
//
//    @Test
//    public void testRemoverUsuario_novamente(){
//        assertEquals(2, usuarioService.quantidadeUsuarios());
//        usuarioService.removerUsuario("BH");
//        assertThrows(UsuarioNaoEncontradoException.class,
//                () -> usuarioService.removerUsuario("BH"));
//
//    }
//
//    @Test
//    public void testBuscaPorId(){
//        String IdEsperado = "BH";
//        Usuario result = usuarioService.buscarPorId(IdEsperado);
//        assertEquals(IdEsperado, result.getId());
//
//    }
//
//    @Test
//    public void testBuscaPorID_IDNaoEncontrado(){
//        assertThrows(UsuarioNaoEncontradoException.class, () -> {
//            usuarioService.buscarPorNome("ES");
//        });
//    }
//
//    @Test
//    public void testAtualizarUsuario(){
//        //testando atualizar seu nome e idade
//        String entradaNome = "igor";
//        String entradaEmail = "Igor@email";
//        String entradaTelefone = "99999999";
//        String entradaEndereco = "EnderecoNovo";
//
//        //criando um usuario pronto, para atualizar no teste
//        Usuario Usuarioteste = new Usuario("a" , "joao@email", "Joao" , "1234567" , "powerguido");
//        usuarioService.adicionarUsuario(Usuarioteste);
//
//        //simula a entrada do scanner
//        Usuarioteste.setNome(entradaNome);
//        Usuarioteste.setEmail(entradaEmail);
//        Usuarioteste.setTelefone(entradaTelefone);
//        Usuarioteste.setEndereco(entradaEndereco);
//
//        //assert
//        assertEquals(entradaNome, Usuarioteste.getNome());
//        assertEquals(entradaEmail, Usuarioteste.getEmail());
//        assertEquals(entradaTelefone, Usuarioteste.getTelefone());
//        assertEquals(entradaEndereco, Usuarioteste.getEndereco());
//    }
}
