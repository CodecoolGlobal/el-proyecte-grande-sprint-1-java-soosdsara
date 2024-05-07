package com.codecool.my_pokemon_team.repository;

import com.codecool.my_pokemon_team.model.pokemon.PokemonSpecies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokemonSpeciesRepository extends JpaRepository<PokemonSpecies, Long> {

    List<PokemonSpecies> findBySpeciesStartingWith(String search);

}
