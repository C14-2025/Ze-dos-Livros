package br.c14lab.biblioteca.service;

import br.c14lab.biblioteca.implementacao.LivroIMPL;
import br.c14lab.biblioteca.implementacao.UsuarioIMPL;
import br.c14lab.biblioteca.model.Emprestimo;
import br.c14lab.biblioteca.model.Livro;
import br.c14lab.biblioteca.model.Usuario;
import java.util.List;

public class BibliotecaServiceImpl implements BibliotecaService {
    private LivroIMPL livroIMPL;
    private UsuarioIMPL usuarioIMPL;

    // Construtor para inicializar as dependências
    public BibliotecaServiceImpl() {
        this.livroIMPL = new LivroIMPL();
        this.usuarioIMPL = new UsuarioIMPL();
    }

    // Construtor alternativo para injeção de dependências
    public BibliotecaServiceImpl(LivroIMPL livroIMPL, UsuarioIMPL usuarioIMPL) {
        this.livroIMPL = livroIMPL;
        this.usuarioIMPL = usuarioIMPL;
    }

    @Override
    public void cadastrarLivros(Livro livro) {
        livroIMPL.adicionarLivro(livro);
    }

    @Override
    public Livro ConsultarLivro(int id) {
        return livroIMPL.buscarPorIsbn(String.valueOf(id));
    }

    public Livro consultarLivroPorIsbn(String isbn) {
        return livroIMPL.buscarPorIsbn(isbn);
    }

    @Override
    public List<Livro> listarLivros() {
        return livroIMPL.buscarTodosOsLivros();
    }

    @Override
    public void atualizarLivro(Livro livro) {
        livroIMPL.atualizarLivro(livro);
    }

    @Override
    public void removerLivro(int id) {
        // Primeiro busca o livro pelo ID
        Livro livro = ConsultarLivro(id);
        if (livro != null) {
            livroIMPL.removerLivro(livro);
        }
    }

    public void removerLivroPorIsbn(String isbn) {
        Livro livro = livroIMPL.buscarPorIsbn(isbn);
        if (livro != null) {
            livroIMPL.removerLivro(livro);
        }
    }

    @Override
    public void cadastrarUsuario(Usuario usuario) {
        usuarioIMPL.adicionarUsuario(usuario);
    }

    /*Abaixo, métodos que precisam da classe EmprestimoIMPL*/

    @Override
    public void realizarEmprestimo(Emprestimo emprestimo) {
        // Implementação básica - você precisará expandir isso
        // Verificar se o livro está disponível, se o usuário pode pegar emprestado, etc.
//        Livro livro = emprestimo.getLivro();
//        Usuario usuario = emprestimo.getUsuario();
//
//        // Marcar livro como emprestado
//        livro.setEmprestado(true);
//        livroIMPL.atualizarLivro(livro);
//
//        // Adicionar à lista de empréstimos do usuário (se houver essa funcionalidade)
//        System.out.println("Empréstimo realizado: " + livro.getTitulo() +
//                " para " + usuario.getNome());
    }

    @Override
    public void devolverLivro(Livro livro) {
        // Marcar livro como disponível
//        livro.setEmprestado(false);
//        livroIMPL.atualizarLivro(livro);
//        System.out.println("Livro devolvido: " + livro.getTitulo());
    }

    @Override
    public List<Emprestimo> listarEmprestimos() {
        // Você precisa implementar um sistema para rastrear empréstimos
        // Atualmente não há armazenamento de empréstimos na sua implementação
        System.out.println("Funcionalidade de listar empréstimos não implementada");
        return List.of();
    }

    // Métodos auxiliares para usuários
    public Usuario buscarUsuarioPorId(String id) {
        return usuarioIMPL.buscarPorId(id);
    }

    public void listarTodosUsuarios() {
        usuarioIMPL.mostrarTodosUsuarios();
    }
}