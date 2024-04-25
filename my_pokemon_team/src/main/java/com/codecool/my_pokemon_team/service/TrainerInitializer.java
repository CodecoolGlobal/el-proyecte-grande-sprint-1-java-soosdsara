package com.codecool.my_pokemon_team.service;

import com.codecool.my_pokemon_team.model.pokemon.PokemonType;
import com.codecool.my_pokemon_team.model.trainer.Trainer;
import com.codecool.my_pokemon_team.model.pokemon.Pokemon;

import java.util.List;

public class TrainerInitializer {

    public Trainer initializeTrainers() {
        Trainer trainer = new Trainer("John", "JohnsPassword");
        trainer.addPokemon(new Pokemon(
                "charmeleon",
                List.of(PokemonType.FIRE, PokemonType.BUG),
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/5.png",
                58,
                64,
                58
        ));
        return trainer;
    }
}
