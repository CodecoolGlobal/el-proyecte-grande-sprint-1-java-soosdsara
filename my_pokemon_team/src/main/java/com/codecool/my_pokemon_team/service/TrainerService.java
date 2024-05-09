package com.codecool.my_pokemon_team.service;

import com.codecool.my_pokemon_team.controller.dto.TrainerDTO;
import com.codecool.my_pokemon_team.model.trainer.Trainer;
import com.codecool.my_pokemon_team.repository.TrainerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class TrainerService {
    private final TrainerRepository trainerRepository;

    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public Trainer findTrainerByName(String trainerName) {
        return trainerRepository.findByTrainerName(trainerName).get();
    }

    public TrainerDTO addTrainer(String name, String password) {
        TrainerDTO trainerDTO = new TrainerDTO(name, password);
        trainerRepository.save(createTrainerEntity(name, password));
        return trainerDTO;
    }

    public boolean checkTrainerName(String name) {
        Optional<Trainer> trainer = trainerRepository.findByTrainerName(name);
        return trainer.isPresent();
    }

    private Trainer createTrainerEntity(String name, String password) {
        Trainer trainerEntity = new Trainer();
        trainerEntity.setTrainerName(name);
        trainerEntity.setPassword(password);

        return trainerEntity;
    }

    @Transactional
    public void updatePassword(long id, String password) {
        trainerRepository.setTrainerEntityPasswordById(id, password);
    }

    public void deleteTrainer(long id) {
        trainerRepository.deleteById(id);
    }
}