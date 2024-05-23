package com.codecool.my_pokemon_team.controller.dto;


import java.util.List;
import java.util.Objects;
import java.util.UUID;

public record PokemonDTO(UUID publicId, String species, String nickName, List<String> types, String pic, int hp, int attack, int defense) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PokemonDTO that)) return false;
        boolean typesMatching = true;
        for (int i = 0; i < this.types.toArray().length; i++){
            typesMatching &= this.types.get(i).equals(that.types.get(i));
        }
        return typesMatching && hp == that.hp && attack == that.attack && defense == that.defense && Objects.equals(pic, that.pic) && Objects.equals(species, that.species) && Objects.equals(nickName, that.nickName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(species, nickName, pic, hp, attack, defense);
    }
}
