package com.codecool.my_pokemon_team.controller.dto;


import java.util.List;
import java.util.UUID;

public record PokemonDTO(UUID publicId, String species, String nickName, List<String> types, String pic, int hp, int attack, int defense) {
}
