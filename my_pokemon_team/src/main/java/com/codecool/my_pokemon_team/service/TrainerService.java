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



}
