package br.c14lab.biblioteca.model;

public class Usuario {
    private String id;
    private String nome;
    private String email;
    private String telefone;
    private String endereco;

    //Construtores ------------------------------------------------------------------------------------
    public Usuario(String id, String email, String nome, String telefone, String endereco) {
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
    }



    public Usuario() {
    }

    //Getters e Setters --------------------------------------------------------------------------------
    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEndereco() {
        return endereco;
    }
    //-------------------------------------------------------------------------------------------------



}
