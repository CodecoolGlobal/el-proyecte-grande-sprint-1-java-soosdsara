package com.codecool.my_pokemon_team.service;

import com.codecool.my_pokemon_team.model.trainer.Trainer;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TrainerService {

    private final Set<Trainer> trainers;

    public TrainerService() {
        this.trainers = new HashSet<>();
    }

    public Trainer addTrainer(String name, String password) {
        Trainer trainer = new Trainer(name, password);
        this.trainers.add(trainer);
        return trainer;
    }
}
