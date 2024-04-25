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

    public Trainer(String name, String password) {
        this.trainerId = ID++;
        this.name = name;
        this.password = password;
        this.pokemonTeam = new HashSet<>();
    }

    public boolean checkTrainerId(int trainerId) {
        return this.trainerId == trainerId;
    }

    public void addPokemon(Pokemon pokemon) {
        pokemonTeam.add(pokemon);
        System.out.println(pokemonTeam);
    }

    public String getName() {
        return name;
    }

    public Set<Pokemon> getPokemonTeam() {
        return new HashSet<>(pokemonTeam);
    }

}
