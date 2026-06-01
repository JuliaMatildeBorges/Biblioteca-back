package com.projeto.biblioteca.service;

import org.springframework.stereotype.Service;

import com.projeto.biblioteca.entity.Espaco;
import com.projeto.biblioteca.enums.TipoEspaco;
import com.projeto.biblioteca.model.EspacoDTO;
import com.projeto.biblioteca.repository.EspacoRepository;

@Service
public class EspacoService extends BaseService<Espaco, EspacoDTO> {

    public EspacoService(EspacoRepository repository) {
        super(repository);
    }

    @Override
    public EspacoDTO create(EspacoDTO dto) {
        dto.setCapacidade(capacidadePermitida(dto.getTipo(), dto.getCapacidade()));
        return super.create(dto);
    }

    @Override
    public EspacoDTO update(Long id, EspacoDTO dto) {
        dto.setCapacidade(capacidadePermitida(dto.getTipo(), dto.getCapacidade()));
        return super.update(id, dto);
    }

    private Integer capacidadePermitida(TipoEspaco tipo, Integer capacidadeInformada) {
        int limite = tipo == TipoEspaco.COMPUTADOR ? 2 : 5;
        if (capacidadeInformada > limite) {
            throw new IllegalArgumentException("Capacidade máxima para " + tipo + " é " + limite + " alunos");
        }
        return capacidadeInformada;
    }
}
