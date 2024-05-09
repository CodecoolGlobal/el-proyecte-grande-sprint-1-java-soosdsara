package com.codecool.my_pokemon_team.controller;

import com.codecool.my_pokemon_team.controller.dto.PokemonDTO;
import com.codecool.my_pokemon_team.service.PokemonApiService;
import com.codecool.my_pokemon_team.service.PokemonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @PostMapping("/{trainerName}")
    public String addPokemon(@PathVariable String trainerName, @RequestBody PokemonDTO pokemonDTO) {
        pokemonService.addPokemon(trainerName, pokemonDTO);
        return "Pokemon added";
    }

    @GetMapping("/{trainerName}")
    public List<PokemonDTO> getPokemonOfTrainer(@PathVariable String trainerName) {
        return pokemonService.getPokemonOfTrainer(trainerName);
    }

    @PatchMapping("/{id}")
    public PokemonDTO patchPokemonOfTrainer(@PathVariable UUID id, @RequestBody String nickName) {
        return pokemonService.updatePokemonNickName(id, nickName);
    }
}
