package com.codecool.my_pokemon_team.service;

import com.codecool.my_pokemon_team.controller.dto.TrainerDTO;
import com.codecool.my_pokemon_team.model.trainer.TrainerEntity;
import com.codecool.my_pokemon_team.repository.TrainerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TrainerService {
    private final TrainerRepository trainerRepository;

    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public TrainerEntity findTrainerById(long trainerId) {
        return trainerRepository.findById(trainerId).get();
    }

    public TrainerDTO addTrainer(String name, String password) {
        TrainerDTO trainerDTO = new TrainerDTO(name, password);
        trainerRepository.save(createTrainerEntity(name, password));
        return trainerDTO;
    }

    private TrainerEntity createTrainerEntity(String name, String password) {
        TrainerEntity trainerEntity = new TrainerEntity();
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