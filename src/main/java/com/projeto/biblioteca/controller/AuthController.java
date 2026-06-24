package com.projeto.biblioteca.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.biblioteca.config.JwtUtil;
import com.projeto.biblioteca.entity.Usuario;
import com.projeto.biblioteca.model.AuthDTO;
import com.projeto.biblioteca.repository.UsuarioRepository;
import com.projeto.biblioteca.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")

    public ResponseEntity<?> login(@RequestBody @Valid AuthDTO dto) {
        String email = dto.getEmail();
        String senha = dto.getSenha(); // TEXTO PURO

        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);

        if (usuarioOpt.isPresent() && passwordEncoder.matches(senha, usuarioOpt.get().getSenha())) {
            Usuario usuario = usuarioOpt.get();
            String perfil = usuario.getPerfil().toString();

            String token = jwtUtil.generateToken(email, perfil);

            return ResponseEntity.ok(Map.of(
                "token", token,
                "id", usuario.getId(),
                "nome", usuario.getNome(),
                "email", usuario.getEmail(),
                "tipo", perfil
            ));
        }

        return ResponseEntity.status(401).body("Credenciais Inválidas!");
    }

    @GetMapping("/ping")    
    public ResponseEntity<?> pong(){
        return ResponseEntity.ok(Map.of("message", "Pong!"));
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return ResponseEntity.ok(Map.of(
            "email", authentication.getName(),
            "tipo", authentication.getAuthorities().stream()
                .findFirst()
                .map(authority -> authority.getAuthority().replace("ROLE_", ""))
                .orElse("")
        ));
    }

}
