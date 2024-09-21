package com.casacultural.cinema.controller;

import com.casacultural.cinema.model.Analise;
import com.casacultural.cinema.model.Filme;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

         @Controller
         @RequestMapping("/filmes")
         public class FilmeController {

                  private final FilmeService filmeService;
                  private final AnaliseService analiseService;

                  public FilmeController(FilmeService filmeService, AnaliseService analiseService) {

                            this.filmeService = filmeService;
                            this.analiseService = analiseService;

                  }
                  
                  @PostMapping("/cadastrar")
                  public String cadastrarFilme(Filme filme, Model model) {
                           filmeService.adicionarFilme(filme); // Adiciona o filme ao serviço
                           return "redirect:/filmes/"; // Redireciona para a lista de filmes após o cadastro
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
                                    model.addAttribute("analises", analiseService.listarAnalisesPorFilme(id)); // Adiciona as análises
                                    return "filmes/detalhes";
                           } else {
                                    return "filmes/erro"; // Retorna uma página de erro caso o filme não seja encontrado
                           }
                  }

                  @GetMapping("/formulario")
                  public String formulario(Model model) {
                
                           model.addAttribute("filme", new Filme(null, "", "", "", 0));
                           return "filmes/formulario";
                
                  }
                  
                  @GetMapping("/{id}/analisar")
                           public String adicionarAnalise(@PathVariable Long id, Model model) {
                                    model.addAttribute("filmeId", id);
                                    return "filmes/analisar";
                            }

                            @PostMapping("/{id}/analisar")
                           public String salvarAnalise(@PathVariable Long id, Analise analise) {
                                    analiseService.adicionarAnalise(analise);
                                    return "redirect:/filmes/" + id; // Redireciona para os detalhes do filme
                           }


         }
