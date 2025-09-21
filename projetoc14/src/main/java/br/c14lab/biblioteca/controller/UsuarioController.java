package br.c14lab.biblioteca.controller;

import br.c14lab.biblioteca.implementacao.UsuarioIMPL;
import br.c14lab.biblioteca.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioIMPL usuarioIMPL;

    @GetMapping
    public List<Usuario> listarTodosUsuarios() {
        return(List<Usuario>) usuarioIMPL.mostrarTodosUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable String id) {
        try {
            Usuario usuario = usuarioIMPL.buscarPorId(id);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public void adicionarUsuario(@RequestBody Usuario usuario) {
        usuarioIMPL.adicionarUsuario(usuario);
    }

    @PutMapping
    public void atualizarUsuario(@RequestBody Usuario usuario) {
        usuarioIMPL.atualizarUsuario(usuario);
    }

    @DeleteMapping("/{id}")
    public void removerUsuario(@PathVariable String id) {
        usuarioIMPL.removerUsuario(id);
    }
}