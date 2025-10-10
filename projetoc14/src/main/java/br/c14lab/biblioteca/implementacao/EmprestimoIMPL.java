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



    //Atributos ------------------------------------------------------------------------------------
    List<Emprestimo> emprestimos = new ArrayList<>();

    Scanner sc = new Scanner(System.in);
    //-------------------------------------------------------------------------------------------------



    //Métodos ------------------------------------------------------------------------------------
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
        return new ArrayList<>(emprestimos);
    }



    @Override
    public void removerEmprestimo(String id) {
        boolean encontrado = false;

        for (int i = 0; i < emprestimos.size(); i++) {
            Emprestimo e = emprestimos.get(i);

            if (e.getId().equals(id)) {
                emprestimos.remove(i);
                encontrado = true;
                System.out.println("Empréstimo removido com sucesso!");
                break;
            }
        }

        if (encontrado == false) {
            System.out.println("Empréstimo não encontrado.");
        }

    }



    @Override
    public List<Emprestimo> buscarPorUsuario(String usuarioId) {
        return null;
    }



    @Override
    public List<Emprestimo> buscarEmprestimosAtivos() {
        return null;
    }
    //-------------------------------------------------------------------------------------------------



}
