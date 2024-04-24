package com.codecool.my_pokemon_team.controller.dto;


import java.util.List;

public record PokemonDTO(String species, List<String> types, String pic, int hp, int attack, int defense) {
}
