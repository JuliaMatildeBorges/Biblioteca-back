package com.projeto.biblioteca.repository;

import org.springframework.stereotype.Repository;

import com.projeto.biblioteca.entity.Usuario;

@Repository

public interface UsuarioRepository extends BaseRepository<Usuario, Long>{
    
}
