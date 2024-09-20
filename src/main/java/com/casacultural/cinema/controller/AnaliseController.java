package com.casacultural.cinema.controller;

import com.casacultural.cinema.model.Analise;
import com.casacultural.cinema.model.Filme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

         @Controller
         @RequestMapping("/analises")
         public class AnaliseController {

                  private List<Analise> analises = new ArrayList<>();

                  @Autowired
                  private FilmeService filmeservice;

                  @PostMapping("/{filmeId}")
                  public String adicionarAnalise(@PathVariable Long filmeId, @RequestParam String analiseTexto, @RequestParam int nota) {

                           Filme filme = filmeservice.getFilmeById(filmeId);

                           if (filme != null) {
                                    Analise novaAnalise = new Analise((long) (analises.size() + 1), filme, analiseTexto, nota);
                                    analises.add(novaAnalise);
                           }

                           return "redirect:/filmes/" + filmeId;

                  }
         }
