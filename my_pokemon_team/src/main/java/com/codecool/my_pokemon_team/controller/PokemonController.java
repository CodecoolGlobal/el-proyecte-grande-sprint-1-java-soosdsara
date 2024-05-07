package com.codecool.my_pokemon_team.controller;

import com.codecool.my_pokemon_team.controller.dto.PokemonDTO;
import com.codecool.my_pokemon_team.service.PokemonApiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pokemons")
public class PokemonController {

    private final PokemonApiService pokemonApiService;

    public PokemonController(PokemonApiService pokemonService) {
        this.pokemonApiService = pokemonService;
    }

    @GetMapping
    public List<PokemonDTO> getFilteredPokemons(@RequestParam String search) throws JsonProcessingException {
        return pokemonApiService.getSearchedPokemons(search);
    }


}
