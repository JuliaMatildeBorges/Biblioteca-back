package com.projeto.biblioteca.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.projeto.biblioteca.entity.Espaco;
import com.projeto.biblioteca.enums.TipoEspaco;

@Repository
public interface EspacoRepository extends BaseRepository<Espaco, Long> {

    List<Espaco> findByTipoAndAtivoTrue(TipoEspaco tipo);
}
