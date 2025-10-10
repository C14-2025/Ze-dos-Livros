package br.c14lab.biblioteca.implementacao;


import br.c14lab.biblioteca.exceptions.LivroNaoEncontradoException;
import br.c14lab.biblioteca.implementacao.interfaces.LivroRegras;
import br.c14lab.biblioteca.model.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Service
public class LivroIMPL implements LivroRegras {

    //Atributos ------------------------------------------------------------------------------------
    private final DataSource dataSource;

    @Autowired
    public LivroIMPL(DataSource dataSource){
    this.dataSource = dataSource;
    }
    //-------------------------------------------------------------------------------------------------



    //Métodos ------------------------------------------------------------------------------------
    @Override
    public void adicionarLivro(Livro livro) {
        String sql = "INSERT INTO livros (isbn, titulo, autor, editora, ano_publicacao, quantidade_disponivel, categoria) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getIsbn());
            stmt.setString(2, livro.getTitulo());
            stmt.setString(3, livro.getAutor());
            stmt.setString(4, livro.getEditora());
            stmt.setInt(5, livro.getAnoPublicacao());
            stmt.setInt(6, livro.getQuantidadeDisponivel());
            stmt.setString(7, livro.getCategoria());

            stmt.executeUpdate();
            System.out.println("Livro adicionado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar livro: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }



    @Override
    public Livro buscarPorIsbn(String isbn) {
        String sql = "SELECT * FROM livros WHERE isbn = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, isbn);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Livro(
                            rs.getString("isbn"),
                            rs.getString("titulo"),
                            rs.getString("autor"),
                            rs.getString("editora"),
                            rs.getInt("ano_publicacao"),
                            rs.getInt("quantidade_disponivel"),
                            rs.getString("categoria")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar livro por ISBN: " + e.getMessage());
            throw new RuntimeException(e);
        }
        throw new LivroNaoEncontradoException("Livro com ISBN " + isbn + " não encontrado.");
    }



    @Override
    public List<Livro> buscarTodosOsLivros() {
        List<Livro> listaDeLivros = new ArrayList<>();
        String sql = "SELECT * FROM livros";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                listaDeLivros.add(new Livro(
                        rs.getString("isbn"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("editora"),
                        rs.getInt("ano_publicacao"),
                        rs.getInt("quantidade_disponivel"),
                        rs.getString("categoria")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar todos os livros: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return listaDeLivros;
    }



    @Override
    public void atualizarLivro(Livro livroAtualizado) {
        String sql = "UPDATE livros SET titulo = ?, autor = ?, editora = ?, ano_publicacao = ?, quantidade_disponivel = ?, categoria = ? WHERE isbn = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livroAtualizado.getTitulo());
            stmt.setString(2, livroAtualizado.getAutor());
            stmt.setString(3, livroAtualizado.getEditora());
            stmt.setInt(4, livroAtualizado.getAnoPublicacao());
            stmt.setInt(5, livroAtualizado.getQuantidadeDisponivel());
            stmt.setString(6, livroAtualizado.getCategoria());
            stmt.setString(7, livroAtualizado.getIsbn());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new LivroNaoEncontradoException("Livro com ISBN " + livroAtualizado.getIsbn() + " não encontrado para atualização.");
            }
            System.out.println("Livro com ISBN " + livroAtualizado.getIsbn() + " atualizado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar livro: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }



    @Override
    public void removerLivro(Livro livroASerRemovido) {
        String sql = "DELETE FROM livros WHERE isbn = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livroASerRemovido.getIsbn());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new LivroNaoEncontradoException("Livro com ISBN " + livroASerRemovido.getIsbn() + " não encontrado para remoção.");
            }
            System.out.println("Livro com ISBN " + livroASerRemovido.getIsbn() + " removido com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao remover livro: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }



    @Override
    public List<Livro> buscarPorTituloOuAutor(String titulo, String autor) {
        List<Livro> listaDeLivros = new ArrayList<>();
        String sql = "SELECT * FROM livros WHERE titulo LIKE ? OR autor LIKE ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + titulo + "%");
            stmt.setString(2, "%" + autor + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    listaDeLivros.add(new Livro(
                            rs.getString("isbn"),
                            rs.getString("titulo"),
                            rs.getString("autor"),
                            rs.getString("editora"),
                            rs.getInt("ano_publicacao"),
                            rs.getInt("quantidade_disponivel"),
                            rs.getString("categoria")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar livros por título ou autor: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return listaDeLivros;
    }
}
