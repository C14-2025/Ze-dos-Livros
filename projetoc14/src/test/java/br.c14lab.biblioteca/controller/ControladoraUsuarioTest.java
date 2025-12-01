package br.c14lab.biblioteca.controller;

import br.c14lab.biblioteca.implementacao.UsuarioIMPL;
import br.c14lab.biblioteca.model.Usuario;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ControladoraUsuarioTest {

    @Test
    void deveAdicionarUsuarioComSucesso() {
        UsuarioIMPL mockImpl = mock(UsuarioIMPL.class);
        Scanner mockScanner = mock(Scanner.class);

        when(mockScanner.nextInt()).thenReturn(10); // ID
        when(mockScanner.nextLine())
                .thenReturn("")                     // consome \n do nextInt()
                .thenReturn("João Silva")
                .thenReturn("joao@email.com")
                .thenReturn("11999999999")
                .thenReturn("Rua A, 123");

        ControladoraUsuarios ctrl = new ControladoraUsuarios(mockImpl, mockScanner);

        ctrl.adicionarUsuario();

        ArgumentCaptor<Usuario> captor = ArgumentCaptor.forClass(Usuario.class);
        verify(mockImpl).guardarUsuario(captor.capture());

        Usuario u = captor.getValue();

        assertEquals(10, u.getId());
        assertEquals("João Silva", u.getNome());
        assertEquals("joao@email.com", u.getEmail());
        assertEquals("11999999999", u.getTelefone());
        assertEquals("Rua A, 123", u.getEndereco());
    }

    @Test
    void deveAtualizarUsuarioComSucesso() throws Exception {

        UsuarioIMPL mockImpl = mock(UsuarioIMPL.class);
        Scanner mockScanner = mock(Scanner.class);

        Usuario existente = new Usuario(5, "Antigo Nome", "a@a.com", "1111", "Rua Velha");

        when(mockImpl.buscarPorId(5)).thenReturn(existente);

        when(mockScanner.nextInt()).thenReturn(5);
        when(mockScanner.nextLine())
                .thenReturn("")                   // consome \n
                .thenReturn("Novo Nome")
                .thenReturn("novo@email.com")
                .thenReturn("22222222")
                .thenReturn("Rua Nova");

        ControladoraUsuarios ctrl = new ControladoraUsuarios(mockImpl, mockScanner);

        ctrl.atualizarUsuario();

        ArgumentCaptor<Usuario> captor = ArgumentCaptor.forClass(Usuario.class);
        verify(mockImpl).atualizarUsuario(captor.capture());

        Usuario atualizado = captor.getValue();

        assertEquals(5, atualizado.getId());
        assertEquals("Novo Nome", atualizado.getNome());
        assertEquals("novo@email.com", atualizado.getEmail());
        assertEquals("22222222", atualizado.getTelefone());
        assertEquals("Rua Nova", atualizado.getEndereco());
    }



    @Test
    void deveRemoverUsuario() throws Exception {
        UsuarioIMPL mockImpl = mock(UsuarioIMPL.class);
        Scanner mockScanner = mock(Scanner.class);

        when(mockScanner.nextInt()).thenReturn(7);
        when(mockScanner.nextLine()).thenReturn("");

        when(mockImpl.buscarPorId(7))
                .thenReturn(new Usuario(7, "User", "u@u.com", "111", "Rua X"));

        ControladoraUsuarios ctrl = new ControladoraUsuarios(mockImpl, mockScanner);

        ctrl.removerUsuario();

        verify(mockImpl).buscarPorId(7);
        verify(mockImpl).removerUsuario(7);
    }



}
