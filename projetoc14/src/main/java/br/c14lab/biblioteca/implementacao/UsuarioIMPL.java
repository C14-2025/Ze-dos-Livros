package br.c14lab.biblioteca.implementacao;

import br.c14lab.biblioteca.DatabaseConnection;
import br.c14lab.biblioteca.exceptions.UsuarioNaoEncontradoException;
import br.c14lab.biblioteca.implementacao.interfaces.UsuarioRegras;
import br.c14lab.biblioteca.model.Usuario;
import org.springframework.stereotype.Service;

import  java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Service
public class UsuarioIMPL implements UsuarioRegras {

    //mudando tudo para que rode com base no sql
    //deixei tudo comentado para ficar como historico do que foi feito e por quem foi feito


//    //Atributos  ------------------------------------------------------------------------------------
//    List<Usuario> usuarios = new ArrayList<>();
//    Scanner sc = new Scanner(System.in);
//    //-------------------------------------------------------------------------------------------------
//
//
//    //Métodos ------------------------------------------------------------------------------------
//    @Override
//    public void adicionarUsuario(Usuario usuario) {
//        usuarios.add(usuario);
//    }
//
//
//
//    @Override
//    public Usuario buscarPorId(String id) {
//        for (Usuario u : usuarios) {
//            if (id != null && id.equals(u.getId())) {
//                return u;
//            }
//        }
//        throw new UsuarioNaoEncontradoException("Usuário não encontrado!");
//    }
//
//
//
//    @Override
//    public void mostrarTodosUsuarios() {
//        for(Usuario u : usuarios )
//        {
//            System.out.println("[Usuário " + u.getId() + ": " + u.getNome() + "]");
//            System.out.println("-------------------------");
//        }
//    }
//
//
//
//    // Método para auxilio em testes
//    public int quantidadeUsuarios() {
//        return usuarios.size();
//    }
//
//
//
//    @Override
//    public void atualizarUsuario(Usuario usuario) {
//        int atualizar;
//
//        System.out.println("[Qual informação deseja atualizar do usuario " + usuario.getNome() + " :]");
//        System.out.println("[1 - Nome       ]");
//        System.out.println("[2 - Email      ]");
//        System.out.println("[3 - Telefone   ]");
//        System.out.println("[4 - Endereco   ]");
//        System.out.println("-------------------------");
//
//        atualizar = sc.nextInt();
//
//        if (atualizar == 1)
//        {
//            String nome;
//            System.out.println("[Digite o novo nome: ]");
//            nome = sc.nextLine();
//
//            usuario.setNome(nome);
//            System.out.println("[Nome atualizado com sucesso!]");
//            System.out.println("-------------------------");
//        }
//        else if (atualizar == 2)
//        {
//            String email;
//            System.out.println("[Digite o novo email: ]");
//            email = sc.nextLine();
//            usuario.setEmail(email);
//            System.out.println("[Email atualizado com sucesso!]");
//            System.out.println("-------------------------");
//        }
//        else if (atualizar == 3)
//        {
//            String telefone;
//            System.out.println("[Digite o novo telefone: ]");
//            telefone = sc.nextLine();
//            usuario.setTelefone(telefone);
//            System.out.println("[Telefone atualizado com sucesso!]");
//            System.out.println("-------------------------");
//        }
//        else if (atualizar == 4)
//        {
//            String endereco;
//            System.out.println("[Digite o novo endereco: ]");
//            endereco = sc.nextLine();
//            usuario.setEndereco(endereco);
//            System.out.println("[Endereco atualizado com sucesso!]");
//            System.out.println("-------------------------");
//        }
//
//        sc.close(); //Fecha o scanner
//    }
//
//
//
//    @Override
//    public void removerUsuario(String id) {
//        Usuario usuarioParaRemover = null;
//
//        for(Usuario u : usuarios )
//        {
//            if(id != null && id.equals(u.getId()))
//            {
//                usuarioParaRemover = u;
//                break;
//            }
//        }
//
//        if (usuarioParaRemover != null)
//        {
//            usuarios.remove(usuarioParaRemover);
//            System.out.println("[Usuário com ID " + id + " removido com sucesso.]");
//            System.out.println("-------------------------");
//        }
//        else
//        {
//            throw new UsuarioNaoEncontradoException("Usuário não encontrado para remoção!");
//        }
//    }
//
//
//    /*  testeUsuarioNaoEncontradoException
//    *
//    * tembém testar o lançamento de exceção, e a busca do usuário*/
//    @Override
//    public Usuario buscarPorNome(String nome) {
//        for(Usuario u : usuarios )
//        {
//            if(nome != null && nome.equals(u.getNome())) {
//                return u;
//            }
//        }
//        throw new UsuarioNaoEncontradoException("Usuario com nome '" + nome + "'não encontrado.");
//    }
//    //-------------------------------------------------------------------------------------------------
//

    //Comeca aqui o codigo do database
    @Override
    public void adicionarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (id, nome, email, telefone, endereco) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
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
        }
    }

    @Override
    public Usuario buscarPorId (String id){
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
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
        }
        throw new UsuarioNaoEncontradoException("Usuario nao encontrado!");
    }

    @Override
    public List<Usuario> mostrarTodosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (Connection conn = DatabaseConnection.getConnection();
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
        }
        return usuarios;
    }

    @Override
    public void atualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuarios SET nome = ?, email = ?, telefone = ?, endereco = ? WHERE id = ? ";
        try (Connection conn = DatabaseConnection.getConnection();
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
        }
    }

    @Override
    public void removerUsuario(String id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
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
        }
    }

    @Override
    public Usuario buscarPorNome(String nome) {
        String sql = "SELECT * FROM usuarios WHERE nome = ?";
        try (Connection conn = DatabaseConnection.getConnection();
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
        }
        throw new UsuarioNaoEncontradoException("Usuario nao encontrado!");
    }





}