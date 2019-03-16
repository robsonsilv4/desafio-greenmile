package com.robson.desafiogreenmile.service;

import com.robson.desafiogreenmile.domain.HoraTrabalhada;
import com.robson.desafiogreenmile.repository.HoraTrabalhadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HoraTrabalhadaService {

    @Autowired
    private HoraTrabalhadaRepository horaTrabalhadaRepository;

    public Optional<HoraTrabalhada> find(Long id) {
        Optional<HoraTrabalhada> horaTrabalhada = horaTrabalhadaRepository.findById(id);
        return horaTrabalhada;
    }

    public HoraTrabalhada insert(HoraTrabalhada horaTrabalhada) {
        horaTrabalhada.setId(null);
        horaTrabalhada = horaTrabalhadaRepository.save(horaTrabalhada);
        return horaTrabalhada;
    }
}
