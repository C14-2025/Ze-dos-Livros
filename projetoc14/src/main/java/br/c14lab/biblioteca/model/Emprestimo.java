package br.c14lab.biblioteca.model;

import java.time.LocalDate;

public class Emprestimo {
    private String id;
    private String livroIsbn;
    private String usuarioId;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private boolean devolvido;

    //Construtores ------------------------------------------------------------------------------------
    public Emprestimo(String id, String livroIsbn, String usuarioId, LocalDate dataEmprestimo, LocalDate dataDevolucao, boolean devolvido) {
        this.id = id;
        this.livroIsbn = livroIsbn;
        this.usuarioId = usuarioId;
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

    public void getUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
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
