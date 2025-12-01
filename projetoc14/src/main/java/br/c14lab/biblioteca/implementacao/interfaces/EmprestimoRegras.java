package br.c14lab.biblioteca.implementacao.interfaces;

import br.c14lab.biblioteca.model.Emprestimo;

import java.util.List;

public interface EmprestimoRegras {
    void guardarEmprestimo(Emprestimo emprestimo);
    Emprestimo buscarEmprestimoPorID(int id);
    List<Emprestimo> buscarTodosOsEmprestimos();
    void devolverExemplar(Emprestimo emprestimo);
    Emprestimo buscarPorUsuario(int usuarioId);
    List<Emprestimo> buscarEmprestimosAtivos();
}
