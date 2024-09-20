package com.casacultural.cinema.controller;

import com.casacultural.cinema.model.Filme;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

         @Controller
         @RequestMapping("/filmes")
         public class FilmeController {

                  private final FilmeService filmeService;

                  public FilmeController(FilmeService filmeService) {

                            this.filmeService = filmeService;

                  }

                  @GetMapping("/")
                  public String listarFilmes(Model model) {

                           model.addAttribute("filmes", filmeService.listarTodosFilmes());
                           return "filmes/lista"; // Retorna o template "filmes/lista.html"

                  }

                  @GetMapping("/{id}")
                  public String detalhesDoFilme(@PathVariable Long id, Model model) {
                           Filme filme = filmeService.getFilmeById(id);
                           if (filme != null) {
                                    model.addAttribute("filme", filme);
                                    return "filmes/detalhes"; // Verifique se o template 'detalhes.html' existe em templates/filmes/detalhes.html
                           } else {
                                    return "filmes/erro"; // Retorna uma página de erro caso o filme não seja encontrado
                           }
                  }

                  @GetMapping("/formulario")
                  public String formulario(Model model) {
                
                           model.addAttribute("filme", new Filme(null, "", "", "", 0));
                           return "filmes/formulario";
                
                  }

         }
