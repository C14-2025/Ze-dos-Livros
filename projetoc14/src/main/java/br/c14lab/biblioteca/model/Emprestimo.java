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
    public String getId() {
        return id;
    }
    public String getLivroIsbn() {
        return livroIsbn;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate  getDataDevolucao() {
        return dataDevolucao;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }


    //-------------------------------------------------------------------------------------------------
}
