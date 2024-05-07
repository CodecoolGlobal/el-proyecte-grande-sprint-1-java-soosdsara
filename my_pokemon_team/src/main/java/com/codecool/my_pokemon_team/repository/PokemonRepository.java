package com.codecool.my_pokemon_team.repository;

import com.codecool.my_pokemon_team.model.pokemon.PokemonEntity;
import com.codecool.my_pokemon_team.model.trainer.TrainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PokemonRepository extends JpaRepository<PokemonEntity, Long> {
    List<PokemonEntity> findByTrainer(TrainerEntity trainer);
}
