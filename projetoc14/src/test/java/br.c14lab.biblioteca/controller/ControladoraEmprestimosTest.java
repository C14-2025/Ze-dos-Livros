package br.c14lab.biblioteca.controller;

import br.c14lab.biblioteca.exceptions.NaoEncontradoException;
import br.c14lab.biblioteca.exceptions.RegistroDuplicadoException;
import br.c14lab.biblioteca.implementacao.EmprestimoIMPL;
import br.c14lab.biblioteca.model.Emprestimo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ControladoraEmprestimosTest {
    @Test
    void deveRegistrarEmprestimoComSucesso() throws Exception {

        EmprestimoIMPL mockImpl = mock(EmprestimoIMPL.class);
        Scanner mockScanner = mock(Scanner.class);

        when(mockScanner.nextInt()).thenReturn(10, 5);
        when(mockScanner.nextLine()).thenReturn("", "ISBN-123");

        ControladoraEmprestimos ctrl = new ControladoraEmprestimos(mockImpl, mockScanner);

        ArgumentCaptor<Emprestimo> captor = ArgumentCaptor.forClass(Emprestimo.class);

        ctrl.registrarEmprestimo();

        verify(mockImpl).guardarEmprestimo(captor.capture());
        Emprestimo e = captor.getValue();

        assertEquals(10, e.getId());
        assertEquals(5, e.getUsuarioId());
        assertEquals("ISBN-123", e.getLivroIsbn());
        assertEquals(LocalDate.now(), e.getDataEmprestimo());
        assertEquals(LocalDate.now().plusDays(30), e.getDataDevolucao());
        assertFalse(e.isDevolvido());
    }

    @Test
    void deveBuscarPorId() throws Exception {

        EmprestimoIMPL mockImpl = mock(EmprestimoIMPL.class);
        Scanner mockScanner = mock(Scanner.class);

        Emprestimo emp = new Emprestimo(1, "ISBN", 20, LocalDate.now(), LocalDate.now(), false);

        when(mockScanner.nextInt()).thenReturn(1);
        when(mockScanner.nextLine()).thenReturn("");

        when(mockImpl.buscarEmprestimoPorID(1)).thenReturn(emp);

        ControladoraEmprestimos ctrl = new ControladoraEmprestimos(mockImpl, mockScanner);

        ctrl.buscarPorID();

        verify(mockImpl).buscarEmprestimoPorID(1);
    }

    @Test
    void deveTratarBuscarPorIdNaoEncontrado() throws Exception {

        EmprestimoIMPL mockImpl = mock(EmprestimoIMPL.class);
        Scanner mockScanner = mock(Scanner.class);

        when(mockScanner.nextInt()).thenReturn(999);
        when(mockScanner.nextLine()).thenReturn("");

        when(mockImpl.buscarEmprestimoPorID(999))
                .thenThrow(new NaoEncontradoException("ID de empréstimo não encontrada"));

        ControladoraEmprestimos ctrl = new ControladoraEmprestimos(mockImpl, mockScanner);

        ctrl.buscarPorID();

        verify(mockImpl).buscarEmprestimoPorID(999);
    }

    @Test
    void deveBuscarPorUsuario() throws Exception {

        EmprestimoIMPL mockImpl = mock(EmprestimoIMPL.class);
        Scanner mockScanner = mock(Scanner.class);

        Emprestimo emp = new Emprestimo(1, "ISBN", 10, LocalDate.now(), LocalDate.now(), false);

        when(mockScanner.nextInt()).thenReturn(10);
        when(mockScanner.nextLine()).thenReturn("");

        when(mockImpl.buscarPorUsuario(10)).thenReturn(emp);

        ControladoraEmprestimos ctrl = new ControladoraEmprestimos(mockImpl, mockScanner);

        ctrl.buscarPorUsuario();

        verify(mockImpl).buscarPorUsuario(10);
    }

    @Test
    void deveListarTodos() {

        EmprestimoIMPL mockImpl = mock(EmprestimoIMPL.class);
        Scanner mockScanner = mock(Scanner.class);

        List<Emprestimo> lista = List.of(
                new Emprestimo(1, "A", 1, LocalDate.now(), LocalDate.now(), false),
                new Emprestimo(2, "B", 2, LocalDate.now(), LocalDate.now(), true)
        );

        when(mockImpl.buscarTodosOsEmprestimos()).thenReturn(lista);

        ControladoraEmprestimos ctrl = new ControladoraEmprestimos(mockImpl, mockScanner);

        ctrl.listarTodos();

        verify(mockImpl).buscarTodosOsEmprestimos();
    }

    @Test
    void deveListarAtivos() {

        EmprestimoIMPL mockImpl = mock(EmprestimoIMPL.class);
        Scanner mockScanner = mock(Scanner.class);

        List<Emprestimo> ativos = List.of(
                new Emprestimo(1, "A", 1, LocalDate.now(), LocalDate.now(), false)
        );

        when(mockImpl.buscarEmprestimosAtivos()).thenReturn(ativos);

        ControladoraEmprestimos ctrl = new ControladoraEmprestimos(mockImpl, mockScanner);

        ctrl.listarAtivos();

        verify(mockImpl).buscarEmprestimosAtivos();
    }

    @Test
    void deveMarcarComoDevolvido() throws Exception {

        EmprestimoIMPL mockImpl = mock(EmprestimoIMPL.class);
        Scanner mockScanner = mock(Scanner.class);

        Emprestimo emp = new Emprestimo(3, "ISBN", 9,
                LocalDate.now().minusDays(5),
                LocalDate.now().plusDays(25),
                false);

        when(mockScanner.nextInt()).thenReturn(3);
        when(mockScanner.nextLine()).thenReturn("");

        when(mockImpl.buscarEmprestimoPorID(3)).thenReturn(emp);

        ControladoraEmprestimos ctrl = new ControladoraEmprestimos(mockImpl, mockScanner);

        ctrl.marcarComoDevolvido();

        assertTrue(emp.isDevolvido());
        assertEquals(LocalDate.now(), emp.getDataDevolucao());

        verify(mockImpl).buscarEmprestimoPorID(3);
        verify(mockImpl).atualizarEmprestimo(emp);
        verify(mockImpl).devolverExemplar(emp);
    }


}