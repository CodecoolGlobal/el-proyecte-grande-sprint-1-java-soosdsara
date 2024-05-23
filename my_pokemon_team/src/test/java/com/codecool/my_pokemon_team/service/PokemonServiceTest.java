package com.codecool.my_pokemon_team.service;

import com.codecool.my_pokemon_team.controller.dto.PokemonDTO;
import com.codecool.my_pokemon_team.model.pokemon.Pokemon;
import com.codecool.my_pokemon_team.model.pokemon.PokemonSpecies;
import com.codecool.my_pokemon_team.model.pokemon.PokemonType;
import com.codecool.my_pokemon_team.model.trainer.Trainer;
import com.codecool.my_pokemon_team.repository.PokemonRepository;
import com.codecool.my_pokemon_team.repository.PokemonSpeciesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PokemonServiceTest {

    @Captor
    ArgumentCaptor<Pokemon> pokemonCaptor;

    @Mock
    private PokemonRepository pokemonRepository;

    @Mock
    private PokemonSpeciesRepository pokemonSpeciesRepository;

    @Mock
    private TrainerService trainerService;

    @InjectMocks
    private PokemonService pokemonService;

    @Test
    void testAddPokemonWithValidPokemonObject() {
        //Arrange
        String trainerName = "Ash";
        PokemonSpecies pokemonSpecies = new PokemonSpecies();
        pokemonSpecies.setSpecies("charmander");
        pokemonSpecies.setId(1);
        PokemonDTO pokemonDTO = new PokemonDTO(null,
             "charmander",
                null,
                new ArrayList<>(List.of("fire")),
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/4.png",
                39,52,43);
        Trainer trainer = new Trainer();
        trainer.setId(1L);
        trainer.setTrainerName("Ash");
        trainer.setPassword("password");
        when(pokemonSpeciesRepository.findBySpecies("charmander")).
                thenReturn(List.of(pokemonSpecies));
        when(trainerService.findTrainerByName("Ash")).
                thenReturn(trainer);
        Pokemon expectedPokemon = new Pokemon();
        expectedPokemon.setSpecies(pokemonSpecies);
        expectedPokemon.setTypes(List.of(PokemonType.FIRE));
        expectedPokemon.setTrainer(trainer);
        expectedPokemon.setNickName(null);
        expectedPokemon.setPic("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/4.png");
        expectedPokemon.setAttack(52);
        expectedPokemon.setDefense(43);
        expectedPokemon.setHp(39);
        //Act
        pokemonService.addPokemon(trainerName, pokemonDTO);
        //Verify
        verify(pokemonRepository).saveAndFlush(pokemonCaptor.capture());
        Pokemon pokemon= pokemonCaptor.getValue();
        System.out.println(pokemon);
        System.out.println(expectedPokemon);
        assertTrue(pokemon.equals(expectedPokemon));
    }

    @Test
    void getPokemonOfTrainerWithValidTrainerObject() {
        String trainerName = "Ash";
        PokemonSpecies pokemonSpecies = new PokemonSpecies();
        pokemonSpecies.setSpecies("charmander");
        pokemonSpecies.setId(1);
        PokemonDTO pokemonDTOExpected = new PokemonDTO(null,
                "charmander",
                null,
                new ArrayList<>(List.of("FIRE")),
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/4.png",
                39,52,43);
        Trainer trainer = new Trainer();
        trainer.setId(1L);
        trainer.setTrainerName("Ash");
        trainer.setPassword("password");
        when(trainerService.findTrainerByName("Ash")).
                thenReturn(trainer);
        Pokemon expectedPokemon = new Pokemon();
        expectedPokemon.setSpecies(pokemonSpecies);
        expectedPokemon.setTypes(List.of(PokemonType.FIRE));
        expectedPokemon.setTrainer(trainer);
        expectedPokemon.setNickName(null);
        expectedPokemon.setPic("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/4.png");
        expectedPokemon.setAttack(52);
        expectedPokemon.setDefense(43);
        expectedPokemon.setHp(39);
        when(pokemonRepository.findByTrainer(trainer)).thenReturn(
                List.of(expectedPokemon));
        //Act
        PokemonDTO pokemonDTO = pokemonService.getPokemonOfTrainer(trainerName).get(0);
        //Verify
        System.out.println(pokemonDTO);
        System.out.println(pokemonDTOExpected);
        assertTrue(pokemonDTOExpected.equals(pokemonDTO));
    }
}