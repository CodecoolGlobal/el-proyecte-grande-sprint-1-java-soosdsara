package com.codecool.my_pokemon_team.controller;

import com.codecool.my_pokemon_team.controller.dto.TrainerDTO;
import com.codecool.my_pokemon_team.service.TrainerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trainer")
public class TrainerController {

    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping
    public void registerTrainer(@RequestBody TrainerDTO trainerDTO) {
        trainerService.addTrainer(trainerDTO);
    }

    @PatchMapping("{trainerName}")
    public void updatePassword(@PathVariable String trainerName, @RequestBody TrainerDTO trainerDTO) {
        trainerService.updatePassword(trainerName, trainerDTO.password());
    }

    @DeleteMapping("{trainerName}")
    public void deleteTrainer(@PathVariable String trainerName) {
        trainerService.deleteTrainer(trainerName);
    }
}
