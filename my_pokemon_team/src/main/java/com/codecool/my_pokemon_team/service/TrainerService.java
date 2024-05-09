package com.codecool.my_pokemon_team.service;

import com.codecool.my_pokemon_team.controller.dto.TrainerDTO;
import com.codecool.my_pokemon_team.model.trainer.Trainer;
import com.codecool.my_pokemon_team.repository.TrainerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TrainerService {
    private final TrainerRepository trainerRepository;

    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public Trainer findTrainerById(long trainerId) {
        return trainerRepository.findById(trainerId).get();
    }

    public void addTrainer(TrainerDTO trainerDTO) {
        trainerRepository.save(createTrainerEntity(trainerDTO));
    }

    private Trainer createTrainerEntity(TrainerDTO trainerDTO) {
        Trainer trainerEntity = new Trainer();
        trainerEntity.setTrainerName(trainerDTO.name());
        trainerEntity.setPassword(trainerDTO.password());

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