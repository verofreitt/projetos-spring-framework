package com.helloworld.helloworld.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api")
public class HelloWorldController {
	@GetMapping("/hello")
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping("/generation-bsms")
    public List<String> generationBSMs() {
        return Arrays.asList("comunicação", "proatividade", "orientação ao detalhe", "trabalho em equipe", "mentalidade de crescimento", "orientação ao futuro", "responsabilidade pessoal", "persistência");
    }

    @GetMapping("/objetivosAprendizagem")
    public List<String> objetivosAprendizagem() {
        return Arrays.asList("Conseguir aprender explicando para os colegas", "fazer as atividades", "ler os cookbooks");
    }
}
