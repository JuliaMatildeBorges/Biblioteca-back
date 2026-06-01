package com.projeto.biblioteca.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.projeto.biblioteca.entity.Espaco;
import com.projeto.biblioteca.entity.Reserva;
import com.projeto.biblioteca.entity.Usuario;
import com.projeto.biblioteca.enums.StatusReserva;
import com.projeto.biblioteca.enums.TipoEspaco;
import com.projeto.biblioteca.model.ReservaDTO;
import com.projeto.biblioteca.repository.EspacoRepository;
import com.projeto.biblioteca.repository.ReservaRepository;
import com.projeto.biblioteca.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class ReservaService extends BaseService<Reserva, ReservaDTO> {

    private final ReservaRepository reservaRepository;
    private final UsuarioRepository usuarioRepository;
    private final EspacoRepository espacoRepository;

    public ReservaService(ReservaRepository reservaRepository, UsuarioRepository usuarioRepository, EspacoRepository espacoRepository) {
        super(reservaRepository);
        this.reservaRepository = reservaRepository;
        this.usuarioRepository = usuarioRepository;
        this.espacoRepository = espacoRepository;
    }

    @Override
    @Transactional
    public ReservaDTO create(ReservaDTO dto) {
        validarReserva(dto, null);
        return toDto(reservaRepository.save(toEntity(dto)));
    }

    @Override
    @Transactional
    public ReservaDTO update(Long id, ReservaDTO dto) {
        validarReserva(dto, id);
        Reserva reserva = toEntity(dto);
        reserva.setId(id);
        return toDto(reservaRepository.save(reserva));
    }

    public List<ReservaDTO> porUsuario(Long usuarioId) {
        return reservaRepository.findByUsuarioIdAndAtivoTrueOrderByInicioDesc(usuarioId)
                .stream()
                .map(this::toDto)
                .toList();
    }

    public List<ReservaDTO> horariosVagos(LocalDate data) {
        List<ReservaDTO> horarios = new ArrayList<>();
        for (Espaco espaco : espacoRepository.findAll()) {
            for (int hora = 8; hora < 22; hora++) {
                LocalDateTime inicio = LocalDateTime.of(data, LocalTime.of(hora, 0));
                LocalDateTime fim = inicio.plusHours(1);
                if (reservaRepository.findConflitos(espaco.getId(), inicio, fim).isEmpty()) {
                    ReservaDTO dto = new ReservaDTO();
                    dto.setEspacoId(espaco.getId());
                    dto.setEspacoNome(espaco.getNome());
                    dto.setEspacoTipo(espaco.getTipo());
                    dto.setInicio(inicio);
                    dto.setFim(fim);
                    horarios.add(dto);
                }
            }
        }
        return horarios;
    }

    @Transactional
    public ReservaDTO checkin(Long id) {
        Reserva reserva = reservaRepository.findById(id).orElseThrow();
        reserva.setStatus(StatusReserva.CHECKIN);
        return toDto(reservaRepository.save(reserva));
    }

    @Transactional
    public ReservaDTO checkout(Long id) {
        Reserva reserva = reservaRepository.findById(id).orElseThrow();
        reserva.setStatus(StatusReserva.FINALIZADA);
        return toDto(reservaRepository.save(reserva));
    }

    @Override
    public Reserva toEntity(ReservaDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId()).orElseThrow();
        Espaco espaco = espacoRepository.findById(dto.getEspacoId()).orElseThrow();
        Reserva reserva = new Reserva();
        reserva.setUsuario(usuario);
        reserva.setEspaco(espaco);
        reserva.setCurso(dto.getCurso());
        reserva.setQuantidadeAlunos(dto.getQuantidadeAlunos());
        reserva.setInicio(dto.getInicio());
        reserva.setFim(dto.getFim());
        reserva.setStatus(dto.getStatus() == null ? StatusReserva.RESERVADA : dto.getStatus());
        return reserva;
    }

    @Override
    public ReservaDTO toDto(Reserva reserva) {
        ReservaDTO dto = new ReservaDTO();
        dto.setId(reserva.getId());
        dto.setUsuarioId(reserva.getUsuario().getId());
        dto.setUsuarioNome(reserva.getUsuario().getNome());
        dto.setUsuarioEmail(reserva.getUsuario().getEmail());
        dto.setEspacoId(reserva.getEspaco().getId());
        dto.setEspacoNome(reserva.getEspaco().getNome());
        dto.setEspacoTipo(reserva.getEspaco().getTipo());
        dto.setCurso(reserva.getCurso());
        dto.setQuantidadeAlunos(reserva.getQuantidadeAlunos());
        dto.setInicio(reserva.getInicio());
        dto.setFim(reserva.getFim());
        dto.setStatus(reserva.getStatus());
        return dto;
    }

    private void validarReserva(ReservaDTO dto, Long reservaIdAtual) {
        // Mantém as regras de reserva no backend para proteger também chamadas diretas à API.
        if (!dto.getFim().isAfter(dto.getInicio())) {
            throw new IllegalArgumentException("O fim da reserva deve ser após o início");
        }
        if (Duration.between(dto.getInicio(), dto.getFim()).toMinutes() < 60) {
            throw new IllegalArgumentException("A reserva deve ter duração mínima de 1 hora");
        }

        Espaco espaco = espacoRepository.findById(dto.getEspacoId()).orElseThrow();
        // Limites institucionais: até 2 alunos por computador e até 5 por sala.
        int limite = espaco.getTipo() == TipoEspaco.COMPUTADOR ? 2 : 5;
        if (dto.getQuantidadeAlunos() > limite || dto.getQuantidadeAlunos() > espaco.getCapacidade()) {
            throw new IllegalArgumentException("Quantidade de alunos excede a capacidade do espaço");
        }

        // Intervalos se sobrepõem quando uma reserva começa antes do fim da outra e termina depois do início.
        boolean temConflito = reservaRepository.findConflitos(dto.getEspacoId(), dto.getInicio(), dto.getFim())
                .stream()
                .anyMatch(reserva -> reservaIdAtual == null || !reserva.getId().equals(reservaIdAtual));
        if (temConflito) {
            throw new IllegalArgumentException("Já existe reserva para este espaço no horário informado");
        }
    }
}
