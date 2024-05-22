package com.codecool.my_pokemon_team.service;

import com.codecool.my_pokemon_team.controller.dto.TrainerDTO;
import com.codecool.my_pokemon_team.model.trainer.Trainer;
import com.codecool.my_pokemon_team.repository.TrainerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class TrainerService {
    private final TrainerRepository trainerRepository;
    private final PasswordEncoder passwordEncoder;

    public TrainerService(TrainerRepository trainerRepository, PasswordEncoder passwordEncoder) {
        this.trainerRepository = trainerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Trainer findTrainerByName(String trainerName) {
        return trainerRepository.findByTrainerName(trainerName).get();
    }

    public void addTrainer(TrainerDTO trainerDTO) {
        trainerRepository.save(createTrainerEntity(trainerDTO));
    }

    private Trainer createTrainerEntity(TrainerDTO trainerDTO) {
        Trainer trainerEntity = new Trainer();
        trainerEntity.setTrainerName(trainerDTO.name());
        trainerEntity.setPassword(passwordEncoder.encode(trainerDTO.password()));
        return trainerEntity;
    }

    @Transactional
    public void updatePassword(String trainerName, String password) {
        trainerRepository.setTrainerEntityPasswordByTrainerName(trainerName, password);
    }

    @Transactional
    public void deleteTrainer(String trainerName) {
        trainerRepository.deleteByTrainerName(trainerName);
    }

    public boolean checkTrainerName(String name) {
        Optional<Trainer> trainer = trainerRepository.findByTrainerName(name);
        return trainer.isPresent();
    }
}