package com.thehecklers.sburrestdemo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class WebController {

    private static final Logger logger = LoggerFactory.getLogger(WebController.class);
    private final CoffeeRepository coffeeRepository;

    public WebController(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
        logger.info("WebController inicializado");
    }

    @GetMapping("/")
    public String index(Model model) {
        try {
            logger.info("Carregando página inicial");
            var coffees = coffeeRepository.findAll();
            logger.info("Encontrados {} cafés", coffees.size());
            model.addAttribute("coffees", coffees);
            return "index";
        } catch (Exception e) {
            logger.error("Erro ao carregar página: ", e);
            return "error";
        }
    }

    @GetMapping("/coffees-page")
    public String coffeesPage(Model model) {
        model.addAttribute("coffees", coffeeRepository.findAll());
        return "index";
    }

    @GetMapping("/teste-thymeleaf")
    public String testeThymeleaf() {
        return "teste";
    }
}