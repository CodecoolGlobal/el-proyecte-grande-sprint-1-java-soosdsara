package com.codecool.my_pokemon_team.repository;

import com.codecool.my_pokemon_team.model.trainer.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {

    @Modifying
    @Query("update Trainer trainer set trainer.password = ?2 where trainer.trainerName = ?1")
    void setTrainerEntityPasswordByTrainerName(String trainerName, String password);

    void deleteByTrainerName(String trainerName);
}
