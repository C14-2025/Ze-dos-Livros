package br.c14lab.biblioteca.model;

import java.time.LocalDate;

public class Emprestimo {
    private int id;
    private String livroIsbn;
    private int usuarioId;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private boolean devolvido;

    //Construtores ------------------------------------------------------------------------------------
    public Emprestimo(int id, String livroIsbn, int usuarioId, LocalDate dataEmprestimo, LocalDate dataDevolucao, boolean devolvido) {
        this.id = id;
        this.livroIsbn = livroIsbn;
        this.usuarioId = usuarioId;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.devolvido = devolvido;
    }

    public Emprestimo(int id, int usuarioId, String livroIsbn) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.livroIsbn = livroIsbn;
        this.devolvido = false;
    }

    public Emprestimo() {
    }
    //-------------------------------------------------------------------------------------------------


    //Getters e Setters -------------------------------------------------------------------------------
    public int getId() {
        return id;
    }

    public int getUsuarioId() {
        return usuarioId;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setLivroIsbn(String livroIsbn) {
        this.livroIsbn = livroIsbn;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }

    //-------------------------------------------------------------------------------------------------
}
