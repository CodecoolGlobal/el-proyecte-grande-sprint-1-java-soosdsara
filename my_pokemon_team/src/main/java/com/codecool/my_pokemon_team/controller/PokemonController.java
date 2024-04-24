package com.codecool.my_pokemon_team.controller;

import com.codecool.my_pokemon_team.controller.dto.PokemonDTO;
import com.codecool.my_pokemon_team.service.PokemonApiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PokemonController {

    private final PokemonApiService pokemonService;

    public PokemonController(PokemonApiService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/api/pokemons")
    public List<PokemonDTO> getFilteredPokemons(@RequestParam String search) throws JsonProcessingException {
        return pokemonService.getSearchedPokemons(search);
    }


}
