package br.c14lab.biblioteca.implementacao;

import br.c14lab.biblioteca.implementacao.interfaces.EmprestimoRegras;
import br.c14lab.biblioteca.model.Emprestimo;
import br.c14lab.biblioteca.model.Usuario;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class EmprestimoIMPL implements EmprestimoRegras {



    //Atributos ------------------------------------------------------------------------------------
    List<Emprestimo> emprestimos = new ArrayList<>();
    //-------------------------------------------------------------------------------------------------



    //Métodos ------------------------------------------------------------------------------------
    @Override
    public void adicionarEmprestimo(Emprestimo emprestimo) {
        emprestimos.add(emprestimo);
    }



    @Override
    public Emprestimo buscarUsuarioPorID(String id) {
        for(Emprestimo e : emprestimos){
            if(e.getId().equals(id)){
                return e;
            }
        }
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
        List<Emprestimo> emprestimosUsuario = new ArrayList<>();

        for (Emprestimo e : this.emprestimos){
            if(e.getUsuarioId().equals(usuarioId)){
                emprestimosUsuario.add(e);
            }
        }
        return emprestimosUsuario;
    }



    @Override
    public List<Emprestimo> buscarEmprestimosAtivos() {
        return null;
    }
    //-------------------------------------------------------------------------------------------------



}
