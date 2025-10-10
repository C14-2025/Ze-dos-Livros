package br.c14lab.biblioteca.implementacao;

import br.c14lab.biblioteca.exceptions.EmprestimoException;
import br.c14lab.biblioteca.implementacao.interfaces.EmprestimoRegras;
import br.c14lab.biblioteca.model.Emprestimo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import  java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class EmprestimoIMPL implements EmprestimoRegras {

    List<Emprestimo> emprestimos = new ArrayList<>();
    Scanner sc = new Scanner(System.in);


    @Override
    public void adicionarEmprestimo(Emprestimo emprestimo) {
        emprestimos.add(emprestimo);
    }

    @Override
    public Emprestimo buscarUsuarioPorID(String id) {
        return null;
    }

    @Override
    public List<Emprestimo> buscarTodosOsEmprestimos(Emprestimo emprestimo) {
        return null;
    }

    @Override
    public void removerEmprestimo(String id) {

    }

    @Override
    public List<Emprestimo> buscarPorUsuario(String usuarioId) {
        return null;
    }

    @Override
    public List<Emprestimo> buscarEmprestimosAtivos() {
        return null;
    }
}
