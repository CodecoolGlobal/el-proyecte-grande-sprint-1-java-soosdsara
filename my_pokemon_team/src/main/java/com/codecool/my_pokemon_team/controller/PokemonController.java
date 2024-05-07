package com.codecool.my_pokemon_team.controller;

import com.codecool.my_pokemon_team.controller.dto.PokemonDTO;
import com.codecool.my_pokemon_team.service.PokemonApiService;
import com.codecool.my_pokemon_team.service.PokemonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pokemons")
public class PokemonController {
    private final PokemonApiService pokemonApiService;
    private final PokemonService pokemonService;

    public PokemonController(PokemonApiService pokemonApiService, PokemonService pokemonService) {
        this.pokemonApiService = pokemonApiService;
        this.pokemonService = pokemonService;
    }

    @GetMapping
    public List<PokemonDTO> getFilteredPokemons(@RequestParam String search) throws JsonProcessingException {
        return pokemonApiService.getSearchedPokemons(search);
    }

    @PostMapping("/{id}")
    public String addPokemon(@PathVariable int id, @RequestBody PokemonDTO pokemonDTO) {
        pokemonService.addPokemon(id, pokemonDTO);
        return "Pokemon added";
    }

    @GetMapping({"/{id}"})
    public List<PokemonDTO> getPokemonOfTrainer(@PathVariable int id) {
        return pokemonService.getPokemonOfTrainer(id);
    }
}
