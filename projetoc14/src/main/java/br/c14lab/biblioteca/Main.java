package br.c14lab.biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        System.out.println("Use o link a baixo para ver o DataBase : ");
        System.out.println("http://localhost:8080/api/livros e http://localhost:8080/api/usuarios");
    }
}