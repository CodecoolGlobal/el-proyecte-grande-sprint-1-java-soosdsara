package com.codecool.my_pokemon_team.repository;

import com.codecool.my_pokemon_team.model.pokemon.Pokemon;
import com.codecool.my_pokemon_team.model.trainer.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    List<Pokemon> findByTrainer(Trainer trainer);
    Pokemon findPokemonByPublicId(UUID publicId);
}
