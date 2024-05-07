package com.codecool.my_pokemon_team.repository;

import com.codecool.my_pokemon_team.model.trainer.TrainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends JpaRepository<TrainerEntity, Long> {

    @Modifying
    @Query("update TrainerEntity trainer set trainer.password = ?2 where trainer.id = ?1")
    void setTrainerEntityPasswordById(long id, String password);
}
