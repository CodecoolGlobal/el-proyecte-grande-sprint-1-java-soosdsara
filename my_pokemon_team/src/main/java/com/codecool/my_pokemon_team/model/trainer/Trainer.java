package com.codecool.my_pokemon_team.model.trainer;

import com.codecool.my_pokemon_team.model.pokemon.Pokemon;

import java.util.HashSet;
import java.util.Set;

public class Trainer {
    private static int ID = 0;
    private final int trainerId;
    private final String name;
    private String password;
    private final Set<Pokemon> pokemonTeam;

    public Trainer(String name) {
        this.trainerId = ID++;
        this.name = name;
        this.pokemonTeam = new HashSet<>();
    }
}
