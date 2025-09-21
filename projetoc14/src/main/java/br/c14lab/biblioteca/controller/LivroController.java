package br.c14lab.biblioteca.controller;

import br.c14lab.biblioteca.implementacao.LivroIMPL;
import br.c14lab.biblioteca.model.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    @Autowired
    private LivroIMPL livroIMPL;

    @GetMapping
    public List<Livro> listarTodosOsLivros() {
        return livroIMPL.buscarTodosOsLivros();
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Livro> buscarLivroPorIsbn(@PathVariable String isbn) {
        try {
            Livro livro = livroIMPL.buscarPorIsbn(isbn);
            return ResponseEntity.ok(livro);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public void adicionarLivro(@RequestBody Livro livro) {
        livroIMPL.adicionarLivro(livro);
    }

    @PutMapping
    public void atualizarLivro(@RequestBody Livro livro) {
        livroIMPL.atualizarLivro(livro);
    }

    @DeleteMapping("/{isbn}")
    public void removerLivro(@PathVariable String isbn) {
        Livro livro = livroIMPL.buscarPorIsbn(isbn);
        if (livro != null) {
            livroIMPL.removerLivro(livro);
        }
    }
}