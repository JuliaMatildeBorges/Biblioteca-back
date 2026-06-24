package com.projeto.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.projeto.biblioteca.entity.Usuario;
import com.projeto.biblioteca.enums.PerfilUsuario;
import com.projeto.biblioteca.model.UsuarioDTO;
import com.projeto.biblioteca.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService extends BaseService<Usuario, UsuarioDTO> {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;


    public UsuarioService(UsuarioRepository repository) {
        super(repository);
    }


    @Override
    @Transactional
    public UsuarioDTO create(UsuarioDTO dto) {

        Usuario entity = toEntity(dto);

        entity.setPerfil(PerfilUsuario.USUARIO);
        entity.setNivelAcesso("0");

        entity.setSenha(
            passwordEncoder.encode(entity.getSenha())
        );

        return toDto(repository.save(entity));
    }
    

}
