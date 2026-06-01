package com.projeto.biblioteca.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.biblioteca.model.ReservaDTO;
import com.projeto.biblioteca.service.ReservaService;

@RestController
@RequestMapping("/reservas")
public class ReservaController extends BaseController<ReservaDTO> {

    private final ReservaService reservaService;

    public ReservaController(ReservaService service) {
        super(service);
        this.reservaService = service;
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<ReservaDTO> porUsuario(@PathVariable Long usuarioId) {
        return reservaService.porUsuario(usuarioId);
    }

    @GetMapping("/horarios-vagos")
    public List<ReservaDTO> horariosVagos(@RequestParam LocalDate data) {
        return reservaService.horariosVagos(data);
    }

    @PatchMapping("/{id}/checkin")
    public ReservaDTO checkin(@PathVariable Long id) {
        return reservaService.checkin(id);
    }

    @PatchMapping("/{id}/checkout")
    public ReservaDTO checkout(@PathVariable Long id) {
        return reservaService.checkout(id);
    }
}
