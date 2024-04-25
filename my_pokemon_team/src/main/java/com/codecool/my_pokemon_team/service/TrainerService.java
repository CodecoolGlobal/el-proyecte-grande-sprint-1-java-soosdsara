package com.codecool.my_pokemon_team.service;

import com.codecool.my_pokemon_team.controller.dto.PokemonDTO;
import com.codecool.my_pokemon_team.model.pokemon.Pokemon;
import com.codecool.my_pokemon_team.model.pokemon.PokemonType;
import com.codecool.my_pokemon_team.model.trainer.Trainer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TrainerService {

    private final Set<Trainer> trainers;

    public TrainerService() {
        this.trainers = new HashSet<>();
    }

    public Trainer addTrainer(String name, String password) {
        Trainer trainer = new Trainer(name, password);
        this.trainers.add(trainer);
        return trainer;
    }

    public Pokemon addPokemon(int trainerId, PokemonDTO pokemonDTO) {
        Trainer trainer = trainers.stream()
                .filter(t -> t.checkTrainerId(trainerId))
                .findFirst()
                .orElseThrow();
        List<PokemonType> types = getPokemonTypes(pokemonDTO.types());
        Pokemon pokemon = new Pokemon(pokemonDTO.species(), types, pokemonDTO.pic(), pokemonDTO.hp(), pokemonDTO.attack(), pokemonDTO.defense());
        trainer.addPokemon(pokemon);
        return pokemon;
    }

    private List<PokemonType> getPokemonTypes(List<String> typesStrings) {
        List<PokemonType> types = new ArrayList<>();
        for (String type : typesStrings) {
            types.add(PokemonType.valueOf(type.toUpperCase()));
        }
        return types;
    }
}
