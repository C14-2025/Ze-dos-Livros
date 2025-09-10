package br.c14lab.biblioteca.model;

public class Livro {

    private String isbn;
    private String titulo;
    private String autor;
    private String editora;
    private int anoPublicacao;
    private int quantidadeDisponivel;
    private String categoria;


    //Construtores ------------------------------------------------------------------------------------\
    public Livro(String isbn, String titulo, String autor, String editora, int anoPublicacao, int quantidadeDisponivel, String categoria) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.anoPublicacao = anoPublicacao;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.categoria = categoria;
    }



    public Livro() {
    }
    //-------------------------------------------------------------------------------------------------



    //Getters e Setters --------------------------------------------------------------------------------
    public String getIsbn() {
        return isbn;
    }
    //-------------------------------------------------------------------------------------------------

}
