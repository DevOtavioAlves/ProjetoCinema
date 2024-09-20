
package com.casacultural.cinema.controller;

import com.casacultural.cinema.model.Filme;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

         @Service
         public class FilmeService {

                  private Map<Long, Filme> filmes = new HashMap<>();
                  
                  
                  public FilmeService() {

                           filmes.put(1L, new Filme(1L, "Filme A", "Descrição A", "Diretor A", 2022));
                           filmes.put(2L, new Filme(2L, "Filme B", "Descrição B", "Diretor B", 2023));

                  }

                 public List<Filme> listarTodosFilmes() {

                           return List.copyOf(filmes.values());

                 }

                  public Filme getFilmeById(Long id) {

                           return filmes.get(id);

                  }
         }