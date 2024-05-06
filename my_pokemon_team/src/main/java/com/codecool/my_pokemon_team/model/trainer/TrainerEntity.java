package com.codecool.my_pokemon_team.model.trainer;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = {}))
public class TrainerEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private final UUID uuid;
    @Column(unique = true)
    private String trainerName;
    @Column(unique = true)
    private String password;

/*    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL)
    private Set<PokemonEntity> pokemonEntities;*/


    public TrainerEntity() {
        this.uuid = UUID.randomUUID();
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
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
}
