package com.codecool.my_pokemon_team.model.pokemon;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class Pokemon {
    @GeneratedValue
    @Id
    private Long id;
    private int pokemonId;
    private String species;
    private String nickName;

    private  PokemonType types;
    private String pic;
    private int hp;
    private int attack;
    private int defense;


    public int getPokemonId() {
        return pokemonId;
    }

    public String getSpecies() {
        return species;
    }

    public String getNickName() {
        return nickName;
    }

    public PokemonType getTypes() {
        return types;
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
