package com.codecool.my_pokemon_team.model.trainer;

import com.codecool.my_pokemon_team.model.pokemon.Pokemon;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Trainer {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String trainerName;
    private String password;
    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL)
    private Set<Pokemon> pokemonEntities = new HashSet<>();

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Pokemon> getPokemonEntities() {
        return pokemonEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trainer trainer = (Trainer) o;
        return Objects.equals(id, trainer.id) && Objects.equals(trainerName, trainer.trainerName) && Objects.equals(password, trainer.password) && Objects.equals(pokemonEntities, trainer.pokemonEntities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, trainerName, password, pokemonEntities);
    }
}
