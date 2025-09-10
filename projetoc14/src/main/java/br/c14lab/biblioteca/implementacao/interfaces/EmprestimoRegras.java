package br.c14lab.biblioteca.implementacao.interfaces;

import br.c14lab.biblioteca.model.Emprestimo;

import java.time.LocalDate;
import java.util.List;

public interface EmprestimoRegras {
    void adicionarEmprestimo(String id, String livroIsbn, LocalDate dataEmprestimo, LocalDate dataDevolucaoReal, boolean devolvido);
    Emprestimo buscarUsuarioPorID(String id);
    List<Emprestimo> buscarTodosOsEmprestimos(Emprestimo emprestimo);
    void removerEmprestimo(String id);
    List<Emprestimo> buscarPorUsuario(String usuarioId);
    List<Emprestimo> buscarEmprestimosAtivos();
}
