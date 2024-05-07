package com.codecool.my_pokemon_team.repository;

import com.codecool.my_pokemon_team.model.trainer.TrainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository<TrainerEntity, Long> {
}
