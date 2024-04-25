package com.codecool.my_pokemon_team.service;

import com.codecool.my_pokemon_team.model.pokemon.Pokemon;
import com.codecool.my_pokemon_team.model.pokemon.PokemonType;
import com.codecool.my_pokemon_team.model.trainer.Trainer;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TrainerService {

    private final Set<Trainer> trainers;

    public TrainerService() {
        this.trainers = new HashSet<>();
        initializeTrainers();
    }

    public Trainer addTrainer(String name, String password) {
        Trainer trainer = new Trainer(name, password);
        this.trainers.add(trainer);
        return trainer;
    }
    public Set<Pokemon> getPokemonsOfTrainer(int trainerId) {
        return trainers.stream().filter(trainer ->trainer.checkTrainerId(trainerId))
                .flatMap(trainer -> trainer.getPokemonTeam().stream()).collect(Collectors.toSet());
    }
    private void initializeTrainers() {
        Trainer trainer = new Trainer("John", "JohnsPassword"
                );
        trainers.add(trainer);
        trainer.addPokemon(new Pokemon(
                "charmeleon",
                List.of(PokemonType.FIRE),
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/5.png",
                58,
                64,
                58
        ));
    }
}
