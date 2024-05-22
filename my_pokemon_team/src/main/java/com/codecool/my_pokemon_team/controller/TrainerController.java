package com.codecool.my_pokemon_team.controller;

import com.codecool.my_pokemon_team.controller.dto.TrainerDTO;
import com.codecool.my_pokemon_team.service.TrainerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trainer")
public class TrainerController {

    private final TrainerService trainerService;


    public TrainerController(TrainerService trainerService, PasswordEncoder passwordEncoder) {
        this.trainerService = trainerService;
    }

    @PostMapping
    public void registerTrainer(@RequestBody TrainerDTO trainerDTO) {
        trainerService.addTrainer(trainerDTO);
    }

    @PatchMapping("{trainerName}")
    @PreAuthorize("isAuthenticated()")
    public void updatePassword(@PathVariable String trainerName, @RequestBody TrainerDTO trainerDTO) {
        trainerService.updatePassword(trainerName, trainerDTO.password());
    }

    @DeleteMapping("{trainerName}")
    @PreAuthorize("isAuthenticated()")
    public void deleteTrainer(@PathVariable String trainerName) {
        trainerService.deleteTrainer(trainerName);
    }

    @GetMapping("/{trainerName}")
    public ResponseEntity<?> loginTrainer(@PathVariable String trainerName) {
        if(trainerService.checkTrainerName(trainerName)) {
            return ResponseEntity.ok("Trainer found");
        }
        return ResponseEntity.badRequest().body("No trainer with this username:" + trainerName);
    }
}
