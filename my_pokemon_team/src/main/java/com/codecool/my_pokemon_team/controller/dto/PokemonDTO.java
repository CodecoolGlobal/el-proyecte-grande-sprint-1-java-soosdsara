package com.codecool.my_pokemon_team.controller.dto;

import com.codecool.my_pokemon_team.model.pokemon.PokemonType;

import java.util.List;

public record PokemonDTO(String species, List<PokemonType> types, String pic, int hp, int attack, int defense) {
}
