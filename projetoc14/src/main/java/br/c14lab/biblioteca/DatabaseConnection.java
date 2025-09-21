package br.c14lab.biblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
        private static final String URL = "jdbc:h2:./data/biblioteca-db";
        private static final String USUARIO = "sa"; // Usuário padrão do H2
        private static final String SENHA = "";     // Senha padrão do H2

        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        }
    }

