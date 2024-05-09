package com.codecool.my_pokemon_team.controller;

import com.codecool.my_pokemon_team.controller.dto.TrainerDTO;
import com.codecool.my_pokemon_team.service.TrainerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trainer")
public class TrainerController {

    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping
    public TrainerDTO registerTrainer(@RequestBody TrainerDTO trainerDTO) {
        return trainerService.addTrainer(trainerDTO.name(), trainerDTO.password());
    }

    @PatchMapping("{id}")//Public id
    public void updatePassword(@PathVariable long id, @RequestBody TrainerDTO trainerDTO) {
        trainerService.updatePassword(id, trainerDTO.password());
    }

    @DeleteMapping("{id}")//Public id
    public void deleteTrainer(@PathVariable long id) {
        trainerService.deleteTrainer(id);
    }
    @GetMapping("/{trainerName}")
    public ResponseEntity<?> getTrainerName(@PathVariable String trainerName) {
        if(trainerService.checkTrainerName(trainerName)) {
            return ResponseEntity.ok("Trainer found");
        }
        return ResponseEntity.badRequest().body("No trainer with this username:" + trainerName);
    }
}
