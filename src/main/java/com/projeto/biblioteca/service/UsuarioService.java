package com.projeto.biblioteca.service;

import org.springframework.stereotype.Service;

import com.projeto.biblioteca.entity.Usuario;
import com.projeto.biblioteca.model.UsuarioDTO;
import com.projeto.biblioteca.repository.UsuarioRepository;

@Service
public class UsuarioService extends BaseService<Usuario, UsuarioDTO> {

    public UsuarioService(UsuarioRepository repository) {
        super(repository);
    }

}
