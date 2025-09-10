package br.c14lab.biblioteca.model;

import br.c14lab.biblioteca.implementacao.interfaces.EmprestimoRegras;

import java.time.LocalDate;

public class Emprestimo {
    private String id;
    private String livroIsbn;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private boolean devolvido;

    //Construtores ------------------------------------------------------------------------------------
    public Emprestimo(String id, String livroIsbn, LocalDate dataEmprestimo, LocalDate dataDevolucao, boolean devolvido) {
        this.id = id;
        this.livroIsbn = livroIsbn;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.devolvido = devolvido;
    }



    public Emprestimo() {
    }
    //-------------------------------------------------------------------------------------------------



    //Getters e Setters -------------------------------------------------------------------------------

    //-------------------------------------------------------------------------------------------------
}
