package com.codecool.my_pokemon_team.service;

import com.codecool.my_pokemon_team.controller.dto.PokemonDTO;
import com.codecool.my_pokemon_team.model.pokemon.Pokemon;
import com.codecool.my_pokemon_team.model.pokemon.PokemonType;
import com.codecool.my_pokemon_team.repository.PokemonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PokemonService {
    private final PokemonRepository pokemonRepository;
    private final TrainerService trainerService;

    public PokemonService(PokemonRepository pokemonRepository, TrainerService trainerService) {
        this.pokemonRepository = pokemonRepository;
        this.trainerService = trainerService;
    }

    public void addPokemon(int trainerId, PokemonDTO pokemonDTO) {
        Pokemon pokemonEntity = new Pokemon();
        pokemonEntity.setSpecies(pokemonDTO.species());
        pokemonEntity.setTrainer(trainerService.findTrainerById(trainerId));
        pokemonEntity.setTypes(getPokemonTypes(pokemonDTO.types()));
        pokemonEntity.setPic(pokemonDTO.pic());
        pokemonEntity.setHp(pokemonDTO.hp());
        pokemonEntity.setAttack(pokemonDTO.attack());
        pokemonEntity.setDefense(pokemonDTO.defense());
        pokemonRepository.saveAndFlush(pokemonEntity);
    }

    private List<PokemonType> getPokemonTypes(List<String> typesStrings) {
        List<PokemonType> types = new ArrayList<>();
        for (String type : typesStrings) {
            types.add(PokemonType.valueOf(type.toUpperCase()));
        }
        return types;
    }

    public List<PokemonDTO> getPokemonOfTrainer(int trainerId) {
        List<Pokemon> pokemonEntityList = pokemonRepository.findByTrainer(
                trainerService.findTrainerById((long)trainerId)
        );
        return pokemonEntityList.stream().map(this::convertEntityToDTO)
                .toList();
    }

    private PokemonDTO convertEntityToDTO(Pokemon pokemonEntity) {
        return new PokemonDTO(
                pokemonEntity.getPublicId(),
                pokemonEntity.getSpecies(),
                convertTypeToString(pokemonEntity.getTypes()),
                pokemonEntity.getPic(),
                pokemonEntity.getHp(),
                pokemonEntity.getAttack(),
                pokemonEntity.getDefense()
        );
    }

    private List<String> convertTypeToString(List<PokemonType> pokemonTypeList) {
        return pokemonTypeList.stream().map(Enum::toString)
                .toList();
    }

}
