package com.codecool.my_pokemon_team.model.pokemon;

import com.codecool.my_pokemon_team.model.trainer.Trainer;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Pokemon {
    @GeneratedValue
    @Id
    private int privateId;
    @Column(unique = true)
    private UUID publicId = UUID.randomUUID();
    @ManyToOne
    private PokemonSpecies species;
    private String nickName;
    @ManyToOne
    private Trainer trainer;
    @ElementCollection(targetClass = PokemonType.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "pokemon_types", joinColumns = @JoinColumn(name = "pokemonId"))
    @Column(name = "pokemon_type", nullable = false)
    private List<PokemonType> types;
    private String pic;
    private int hp;
    private int attack;
    private int defense;

    public UUID getPublicId() {
        return publicId;
    }

    public PokemonSpecies getSpecies() {
        return species;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public String getNickName() {
        return nickName;
    }

    public List<PokemonType> getTypes() {
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

    public void setSpecies(PokemonSpecies species) {
        this.species = species;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public void setTypes(List<PokemonType> types) {
        this.types = types;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return hp == pokemon.hp && attack == pokemon.attack && defense == pokemon.defense && Objects.equals(species, pokemon.species) && Objects.equals(nickName, pokemon.nickName) && Objects.equals(trainer, pokemon.trainer) && Objects.equals(types, pokemon.types) && Objects.equals(pic, pokemon.pic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(species, nickName, trainer, types, pic, hp, attack, defense);
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "privateId=" + privateId +
                ", publicId=" + publicId +
                ", species=" + species +
                ", nickName='" + nickName + '\'' +
                ", trainer=" + trainer +
                ", types=" + types +
                ", pic='" + pic + '\'' +
                ", hp=" + hp +
                ", attack=" + attack +
                ", defense=" + defense +
                '}';
    }
}
