package com.casacultural.cinema.controller;

import com.casacultural.cinema.model.Analise;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnaliseService {

    private List<Analise> analises = new ArrayList<>();

    public List<Analise> listarAnalisesPorFilme(Long filmeId) {
        List<Analise> result = new ArrayList<>();
        for (Analise analise : analises) {
            if (analise.getFilme().getId().equals(filmeId)) {
                result.add(analise);
            }
        }
        return result;
    }

    public void adicionarAnalise(Analise analise) {
        analises.add(analise);
    }
}