package com.codecool.my_pokemon_team.model.pokemon;

import jakarta.persistence.*;

@Entity
public class PokemonSpecies {

    @Id
    @GeneratedValue
    private long id;
    private String species;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }
}
