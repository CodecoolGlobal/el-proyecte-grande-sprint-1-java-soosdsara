package com.codecool.my_pokemon_team.controller;

import com.codecool.my_pokemon_team.controller.dto.TrainerDTO;
import com.codecool.my_pokemon_team.model.pokemon.Pokemon;
import com.codecool.my_pokemon_team.model.trainer.Trainer;
import com.codecool.my_pokemon_team.service.TrainerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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

    @GetMapping("/api/trainer/{id}/pokemons")
    public Set<Pokemon> getPokemonsOfTrainer(@PathVariable int id) {
        return trainerService.getPokemonsOfTrainer(id);
    }

}
