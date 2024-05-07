package com.codecool.my_pokemon_team.service;

import com.codecool.my_pokemon_team.controller.dto.PokemonDTO;
import com.codecool.my_pokemon_team.controller.dto.TrainerDTO;
import com.codecool.my_pokemon_team.model.pokemon.Pokemon;
import com.codecool.my_pokemon_team.model.pokemon.PokemonType;
import com.codecool.my_pokemon_team.model.trainer.Trainer;
import com.codecool.my_pokemon_team.model.trainer.TrainerEntity;
import com.codecool.my_pokemon_team.repository.TrainerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class TrainerService {

    private final Set<Trainer> trainers;

    private final TrainerRepository trainerRepository;

    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
        this.trainers = new HashSet<>();

        TrainerInitializer trainerInitializer = new TrainerInitializer();
        trainers.add(trainerInitializer.initializeTrainers());
    }

    public TrainerDTO addTrainer(String name, String password) {
        TrainerDTO trainerDTO = new TrainerDTO(name, password);
        trainerRepository.save(createTrainerEntity(name, password));
        return trainerDTO;
    }

    private TrainerEntity createTrainerEntity(String name, String password) {
        TrainerEntity trainerEntity = new TrainerEntity();
        trainerEntity.setTrainerName(name);
        trainerEntity.setPassword(password);

        return trainerEntity;
    }

    public Pokemon addPokemon(int trainerId, PokemonDTO pokemonDTO) {
        Trainer trainer = findTrainerById(trainerId);
        Pokemon pokemon = createPokemonFromDTO(pokemonDTO);
        trainer.addPokemon(pokemon);
        return pokemon;
    }

    public Set<Pokemon> getPokemonsOfTrainer(int trainerId) {
        return trainers.stream().filter(trainer -> trainer.checkTrainerId(trainerId))
                .flatMap(trainer -> trainer.getPokemonTeam().stream()).collect(Collectors.toSet());
    }

    private Trainer findTrainerById(int trainerId) {
        return trainers.stream()
                .filter(t -> t.checkTrainerId(trainerId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Trainer not found with ID: " + trainerId));
    }

    private Pokemon createPokemonFromDTO(PokemonDTO pokemonDTO) {
        List<PokemonType> types = getPokemonTypes(pokemonDTO.types());
        return new Pokemon(
                pokemonDTO.species(),
                types,
                pokemonDTO.pic(),
                pokemonDTO.hp(),
                pokemonDTO.attack(),
                pokemonDTO.defense()
        );
    }

    private List<PokemonType> getPokemonTypes(List<String> typesStrings) {
        List<PokemonType> types = new ArrayList<>();
        for (String type : typesStrings) {
            types.add(PokemonType.valueOf(type.toUpperCase()));
        }
        return types;
    }
    @Transactional
    public void updatePassword(long id, String password){
        trainerRepository.setTrainerEntityPasswordById(id, password);
    }
}
