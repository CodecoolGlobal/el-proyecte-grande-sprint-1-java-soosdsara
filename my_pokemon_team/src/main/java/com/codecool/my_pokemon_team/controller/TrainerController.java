package com.codecool.my_pokemon_team.controller;

import com.codecool.my_pokemon_team.controller.dto.TrainerDTO;
import com.codecool.my_pokemon_team.model.pokemon.PokemonEntity;
import com.codecool.my_pokemon_team.model.trainer.TrainerEntity;
import com.codecool.my_pokemon_team.service.TrainerService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/trainer")
public class TrainerController {

    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping()
    public void addTrainer( @RequestBody TrainerDTO trainerDTO) {
        TrainerEntity trainerEntity = new TrainerEntity();
        trainerService.addTrainer(trainerEntity);
    }
}
