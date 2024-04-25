package com.codecool.my_pokemon_team.controller;

import com.codecool.my_pokemon_team.controller.dto.PokemonDTO;
import com.codecool.my_pokemon_team.controller.dto.TrainerDTO;
import com.codecool.my_pokemon_team.model.pokemon.Pokemon;
import com.codecool.my_pokemon_team.model.trainer.Trainer;
import com.codecool.my_pokemon_team.service.TrainerService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrainerController {

    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping("/api/register-trainer")
    public Trainer registerTrainer(@RequestBody TrainerDTO trainerDTO) {
        return trainerService.addTrainer(trainerDTO.name(), trainerDTO.password());
    }

    @PostMapping("/api/trainer/{trainerId}")
    public Pokemon addPokemon(@RequestBody PokemonDTO pokemonDTO, @PathVariable int trainerId) {
        return trainerService.addPokemon(pokemonDTO);
    }

}
