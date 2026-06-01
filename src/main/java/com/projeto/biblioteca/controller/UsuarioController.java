package com.projeto.biblioteca.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.biblioteca.model.UsuarioDTO;
import com.projeto.biblioteca.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController extends BaseController<UsuarioDTO> {

    public UsuarioController(UsuarioService service) {
        super(service);
    }

}