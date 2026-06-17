package com.projeto.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
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

    public UsuarioService(UsuarioRepository repository) {
        super(repository);
    }

    @Override
    @Transactional
    public UsuarioDTO create(UsuarioDTO dto) {
        System.out.println("ENTREI");

        Usuario entity = toEntity(dto);

        if (entity.getPerfil() == null) {
            entity.setPerfil(PerfilUsuario.USUARIO);
        }

        System.out.println("aqui");
        System.out.println("DTO: " + dto);
        System.out.println("ENTITY: " + entity);
        return toDto(repository.save(entity));
    }
}
