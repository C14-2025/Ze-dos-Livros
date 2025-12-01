package br.c14lab.biblioteca.exceptions;

public class RegistroDuplicadoException extends RuntimeException {
    public RegistroDuplicadoException(String message) {
        super(message); //Toda vez que o usuário tenta cadastrar algo e ja existe
    }
}
