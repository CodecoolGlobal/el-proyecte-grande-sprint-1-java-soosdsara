package com.codecool.my_pokemon_team.model.pokemon;

import java.util.List;

public class Pokemon {
    private static int ID = 0;
    private final int pokemonId;
    private final String species;
    private String nickName;
    private final List<PokemonType> type;
    private final String pic;
    private final int hp;
    private final int attack;
    private final int defense;

    public Pokemon(String species, List<PokemonType> type, String pic, int hp, int attack, int defense) {
        this.pokemonId = ID++;
        this.species = species;
        this.type = type;
        this.pic = pic;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.nickName = species;
    }

    public int getPokemonId() {
        return pokemonId;
    }

    public String getSpecies() {
        return species;
    }

    public String getNickName() {
        return nickName;
    }

    public List<PokemonType> getType() {
        return type;
    }

    public String getPic() {
        return pic;
    }

    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }
}
