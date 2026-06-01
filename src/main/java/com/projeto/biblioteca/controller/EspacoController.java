package com.projeto.biblioteca.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.biblioteca.model.EspacoDTO;
import com.projeto.biblioteca.service.EspacoService;

@RestController
@RequestMapping("/espacos")
public class EspacoController extends BaseController<EspacoDTO> {

    public EspacoController(EspacoService service) {
        super(service);
    }
}
