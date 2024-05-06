package com.codecool.my_pokemon_team.repository;

import com.codecool.my_pokemon_team.model.trainer.TrainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends JpaRepository<TrainerEntity, Long> {
}
