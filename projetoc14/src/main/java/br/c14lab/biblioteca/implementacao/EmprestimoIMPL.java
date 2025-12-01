package br.c14lab.biblioteca.implementacao;

import br.c14lab.biblioteca.exceptions.NaoEncontradoException;
import br.c14lab.biblioteca.exceptions.RegistroDuplicadoException;
import br.c14lab.biblioteca.implementacao.interfaces.EmprestimoRegras;
import br.c14lab.biblioteca.model.Emprestimo;
import br.c14lab.biblioteca.model.Livro;
import br.c14lab.biblioteca.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class EmprestimoIMPL implements EmprestimoRegras {

    //Atributos
    //----------------------------------------------------------------------------------------------
    private List<Emprestimo> emprestimos = new ArrayList<>();

    // Dependências para testes
    private UsuarioIMPL usuarioIMPL;
    private LivroIMPL livroIMPL;


    //----------------------------------------------------------------------------------------------

    public LivroIMPL getLivroIMPL() {
        return livroIMPL;
    }


    // Construtor PARA TESTES
    //----------------------------------------------------------------------------------------------
    public EmprestimoIMPL(UsuarioIMPL usuarioIMPL, LivroIMPL livroIMPL) {
        this.usuarioIMPL = usuarioIMPL;
        this.livroIMPL = livroIMPL;
    }
    //----------------------------------------------------------------------------------------------



    // Métodos
    //----------------------------------------------------------------------------------------------
    @Override
    public void guardarEmprestimo(Emprestimo aux) {
        Usuario usuario;
        Livro livro;

        for (Emprestimo emp : emprestimos) {
            if (emp.getId() == aux.getId()) {
                throw new RegistroDuplicadoException("[Já existe um empréstimo com este ID!]");
            }
        }

        // Verifica se o usuário existe
        try {
            usuario = usuarioIMPL.buscarPorId(aux.getUsuarioId());
        } catch (NaoEncontradoException e) {
            System.out.println(e.getMessage());
            return;
        }


        // Verifica se o livro existe
        try {
            livro = livroIMPL.buscarPorIsbn(aux.getLivroIsbn());
        } catch (NaoEncontradoException e) {
            System.out.println(e.getMessage());
            return;
        }

        // Verifica se há exemplares disponíveis
        if (livro.getQuantidadeDisponivel() <= 0) {
            System.out.println("[Nenhum exemplar disponível para empréstimo!]");
            return;
        }
        // Reduz um exemplar
        livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() - 1);
        // Atualiza o livro
        try {
            livroIMPL.atualizarLivro(livro);
        } catch (NaoEncontradoException e) {
            System.out.println(e.getMessage());
            return;
        }
        emprestimos.add(aux);
        System.out.println("[Empréstimo registrado com sucesso!]");
    }
    //----------------------------------------------------------------------------------------------



    //----------------------------------------------------------------------------------------------
    // Busca usuario por ID
    @Override
    public Emprestimo buscarEmprestimoPorID(int id) {
        for (Emprestimo e : emprestimos) {
            if (e.getId() == id) {
                return e;
            }
        }
        throw new NaoEncontradoException("Id: "+ id +" não encontrado!");
    }
    //----------------------------------------------------------------------------------------------



    //----------------------------------------------------------------------------------------------
    @Override
    public Emprestimo buscarPorUsuario(int usuarioId) {
        for (Emprestimo e : emprestimos) {
            if (e.getUsuarioId() == usuarioId) {
                return e;
            }
        }
        throw new NaoEncontradoException("Usuario Id: "+ usuarioId +" não encontrado!");
    }
    //----------------------------------------------------------------------------------------------



    //----------------------------------------------------------------------------------------------
    @Override
    public List<Emprestimo> buscarTodosOsEmprestimos() {
        return emprestimos;
    }
    //----------------------------------------------------------------------------------------------



    //----------------------------------------------------------------------------------------------
    @Override
    public List<Emprestimo> buscarEmprestimosAtivos() {
        List<Emprestimo> ativos = new ArrayList<>();
        for (Emprestimo e : emprestimos) {
            if (!e.isDevolvido()) {
                ativos.add(e);
            }
        }
        return ativos;
    }
    //----------------------------------------------------------------------------------------------



    //----------------------------------------------------------------------------------------------
    public void devolverExemplar(Emprestimo emprestimo) {
        try {
            Livro livro = livroIMPL.buscarPorIsbn(emprestimo.getLivroIsbn());
            livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() + 1);
            livroIMPL.atualizarLivro(livro);
        } catch (NaoEncontradoException ex) {
            System.out.println("Aviso: livro não encontrado ao tentar devolver exemplar.");
        }
    }
    //----------------------------------------------------------------------------------------------



    //----------------------------------------------------------------------------------------------
    public void atualizarEmprestimo(Emprestimo emprestimoAtualizado) throws NaoEncontradoException {
        Emprestimo existente;
        try {
            existente = buscarEmprestimoPorID(emprestimoAtualizado.getId());
        } catch (NaoEncontradoException e) {
            System.out.println(e.getMessage());
            return;
        }

        emprestimos.remove(existente);
        emprestimos.add(emprestimoAtualizado);
    }
    //----------------------------------------------------------------------------------------------



}
