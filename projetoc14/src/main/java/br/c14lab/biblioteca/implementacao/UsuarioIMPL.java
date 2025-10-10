package br.c14lab.biblioteca.implementacao;

import br.c14lab.biblioteca.exceptions.UsuarioNaoEncontradoException;
import br.c14lab.biblioteca.implementacao.interfaces.UsuarioRegras;
import br.c14lab.biblioteca.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import  java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Service
public class UsuarioIMPL implements UsuarioRegras {

    private final DataSource dataSource;

    @Autowired
    public UsuarioIMPL(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void adicionarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (id, nome, email, telefone, endereco) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getId());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getTelefone());
            stmt.setString(5, usuario.getEndereco());

            stmt.executeUpdate();
            System.out.println("Usuário adicionado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao adicionar usuário: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Usuario buscarPorId (String id){
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1 , id);
            try (ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    return new Usuario(
                            rs.getString("id") ,
                            rs.getString("nome") ,
                            rs.getString("email") ,
                            rs.getString("telefone") ,
                            rs.getString("endereco")
                    );
                }
            }
        }catch (SQLException e){
            System.err.println("Erro ao buscar usuario" + e.getMessage());
            throw new RuntimeException(e);
        }
        throw new UsuarioNaoEncontradoException("Usuario nao encontrado!");
    }

    @Override
    public List<Usuario> mostrarTodosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                usuarios.add(new Usuario(
                        rs.getString("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        rs.getString("endereco")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return usuarios;
    }

    @Override
    public void atualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuarios SET nome = ?, email = ?, telefone = ?, endereco = ? WHERE id = ? ";
        try (Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1 , usuario.getNome());
            stmt.setString(2 , usuario.getEmail());
            stmt.setString(3 , usuario.getTelefone());
            stmt.setString(4 , usuario.getEndereco());
            stmt.setString(5 , usuario.getId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0){
                System.out.println("Usuario atualizado com sucesso!");
            }else {
                throw new UsuarioNaoEncontradoException("Usuario nao encontrado!");
            }
        }catch (SQLException e){
            System.err.println("Erro ao atualizar usuario" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removerUsuario(String id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1 , id );

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0){
                System.out.println("Usuario removido com sucesso!");
            }else {
                throw new UsuarioNaoEncontradoException("Usuario nao encontrado!");
            }
        }catch (SQLException e){
            System.err.println("Erro ao remover usuario" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Usuario buscarPorNome(String nome) {
        String sql = "SELECT * FROM usuarios WHERE nome = ?";
        try (Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1 , nome);
            try (ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    return new Usuario(
                      rs.getString("id"),
                      rs.getString("nome"),
                      rs.getString("email"),
                      rs.getString("telefone"),
                      rs.getString("endereco")
                    );
                }
            }
        }catch (SQLException e){
            System.out.println("Erro ao buscar usuario" + e.getMessage());
            throw new RuntimeException(e);
        }
        throw new UsuarioNaoEncontradoException("Usuario nao encontrado!");
    }





}