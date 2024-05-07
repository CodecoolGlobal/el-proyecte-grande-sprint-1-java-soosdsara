package com.codecool.my_pokemon_team.service;

import com.codecool.my_pokemon_team.model.pokemon.PokemonEntity;
import com.codecool.my_pokemon_team.model.pokemon.PokemonType;
import com.codecool.my_pokemon_team.model.trainer.TrainerEntity;
import com.codecool.my_pokemon_team.repository.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class TrainerService {

    private final TrainerRepository trainerRepository;

    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }


    public TrainerEntity findTrainerById(long trainerId) {
        return trainerRepository.findById(trainerId).get();
    }
    public void addTrainer(TrainerEntity trainer) {
        trainerRepository.save(trainer);
    }

}

