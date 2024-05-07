package com.codecool.my_pokemon_team.model.pokemon;

import com.codecool.my_pokemon_team.model.trainer.Trainer;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Pokemon {
    @GeneratedValue
    @Id
    private int privateId;
    @Column(unique = true)
    private UUID publicId = UUID.randomUUID();
    private String species;
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

    public int getPrivateId() {
        return privateId;
    }

    public String getSpecies() {
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

    public void setSpecies(String species) {
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
}
