package br.c14lab.biblioteca;

import br.c14lab.biblioteca.implementacao.LivroIMPL;
import br.c14lab.biblioteca.model.Livro;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class
LivroIMPLIT {

    @Mock
    private DataSource mockDataSource;

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockStatement;

    @Mock
    private ResultSet mockResultSet;

    @InjectMocks
    private LivroIMPL livroService;

    @Test
    void testeMockAdicionarLivro() throws SQLException{
        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);

        Livro livro = new Livro("12345", "Livro Mockado", "Autor Teste",
                "Editora", 2024, 10, "Ficção");

        livroService.adicionarLivro(livro);

        verify(mockStatement, times(1)).executeUpdate();
    }

}
