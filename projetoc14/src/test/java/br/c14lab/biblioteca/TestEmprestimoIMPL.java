package br.c14lab.biblioteca;

import br.c14lab.biblioteca.implementacao.EmprestimoIMPL;
import br.c14lab.biblioteca.model.Emprestimo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TestEmprestimoIMPL {

    @InjectMocks
    private EmprestimoIMPL emprestimoService;

    @BeforeEach
    void setUp() {
        emprestimoService = new EmprestimoIMPL();
    }

    @Test
    void testAdicionarEmprestimoComSucesso() {
        LocalDate hoje = LocalDate.now();
        Emprestimo novoEmprestimo = new Emprestimo("E001", "978-85-7608-862-1", hoje, hoje.plusDays(14), false);

        emprestimoService.adicionarEmprestimo(novoEmprestimo);

        List<Emprestimo> todosEmprestimos = emprestimoService.buscarTodosOsEmprestimos(null);
        assertNotNull(todosEmprestimos);
        assertEquals(1, todosEmprestimos.size());
        assertEquals("E001", todosEmprestimos.get(0).getId());
        assertEquals("978-85-7608-862-1", todosEmprestimos.get(0).getLivroIsbn());
    }

    @Test
    void testRemoverEmprestimoComSucesso() {
        LocalDate hoje = LocalDate.now();
        Emprestimo emprestimo = new Emprestimo("E002", "978-85-7608-267-4", hoje, hoje.plusDays(7), false);
        emprestimoService.adicionarEmprestimo(emprestimo);

        emprestimoService.removerEmprestimo("E002");

        List<Emprestimo> todosEmprestimos = emprestimoService.buscarTodosOsEmprestimos(null);
        assertTrue(todosEmprestimos.isEmpty());
    }

    @Test
    void testRemoverEmprestimoComIdInexistente() {
        LocalDate hoje = LocalDate.now();
        Emprestimo emprestimo = new Emprestimo("E003", "978-65-86041-01-0", hoje, hoje.plusDays(10), false);
        emprestimoService.adicionarEmprestimo(emprestimo);

        emprestimoService.removerEmprestimo("ID_FALSO_999");

        List<Emprestimo> todosEmprestimos = emprestimoService.buscarTodosOsEmprestimos(null);
        assertEquals(1, todosEmprestimos.size());
        assertEquals("E003", todosEmprestimos.get(0).getId());
    }

    @Test
    void testBuscarTodosOsEmprestimosComSucesso() {
        LocalDate hoje = LocalDate.now();
        Emprestimo emprestimo1 = new Emprestimo("E004", "978-85-94318-00-5", hoje.minusDays(5), hoje.plusDays(9), false);
        Emprestimo emprestimo2 = new Emprestimo("E005", "978-85-7608-862-1", hoje, hoje.plusDays(14), false);

        emprestimoService.adicionarEmprestimo(emprestimo1);
        emprestimoService.adicionarEmprestimo(emprestimo2);

        List<Emprestimo> todosEmprestimos = emprestimoService.buscarTodosOsEmprestimos(null);

        assertNotNull(todosEmprestimos);
        assertEquals(2, todosEmprestimos.size());
    }

    @Test
    void testMetodosNaoImplementadosRetornamNull() {
        assertNull(emprestimoService.buscarUsuarioPorID("qualquer-id"));
        assertNull(emprestimoService.buscarPorUsuario("qualquer-user"));
        assertNull(emprestimoService.buscarEmprestimosAtivos());
    }
}