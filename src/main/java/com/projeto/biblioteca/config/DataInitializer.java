package com.projeto.biblioteca.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.projeto.biblioteca.entity.Usuario;
import com.projeto.biblioteca.enums.Instituicao;
import com.projeto.biblioteca.enums.PerfilUsuario;
import com.projeto.biblioteca.enums.TipoUsuario;
import com.projeto.biblioteca.repository.UsuarioRepository;

@Configuration
public class DataInitializer {

    private final PasswordEncoder passwordEncoder;

    public DataInitializer(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner initDatabase(UsuarioRepository repository){
        return args -> {
            salvarUsuario(repository, "admin@admin.com", "00000000000", PerfilUsuario.ADMIN, Instituicao.SENAI, TipoUsuario.COLABORADOR);
            System.out.println("Usuário ADMIN disponível: admin@admin.com / 123456789");
        };
    }

    private void salvarUsuario(UsuarioRepository repository, String email, String cpf, PerfilUsuario perfil, Instituicao instituicao, TipoUsuario tipoUsuario) {
        Usuario usuario = repository.findByEmail(email).orElseGet(Usuario::new);

        usuario.setEmail(email);
        usuario.setCpf(cpf);
        usuario.setPerfil(perfil);
        usuario.setInstituicao(instituicao);
        usuario.setSenha(passwordEncoder.encode("123456789"));
        usuario.setTipo(tipoUsuario);
        repository.save(usuario);
    }
    

}

