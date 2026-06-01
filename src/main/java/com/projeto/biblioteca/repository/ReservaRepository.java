package com.projeto.biblioteca.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projeto.biblioteca.entity.Reserva;

@Repository
public interface ReservaRepository extends BaseRepository<Reserva, Long> {

    List<Reserva> findByUsuarioIdAndAtivoTrueOrderByInicioDesc(Long usuarioId);

    @Query("""
            SELECT r FROM Reserva r
            WHERE r.espaco.id = :espacoId
              AND r.ativo = TRUE
              AND r.status <> com.projeto.biblioteca.enums.StatusReserva.CANCELADA
              AND r.inicio < :fim
              AND r.fim > :inicio
            """)
    List<Reserva> findConflitos(Long espacoId, LocalDateTime inicio, LocalDateTime fim);
}
