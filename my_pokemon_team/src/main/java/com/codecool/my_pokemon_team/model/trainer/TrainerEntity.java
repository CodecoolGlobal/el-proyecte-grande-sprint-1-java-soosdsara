package com.codecool.my_pokemon_team.model.trainer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


@Entity
public class TrainerEntity {
    @Id
    @GeneratedValue
    private Long Id;
    private String trainerName;
    private String password;

    public Long getId() {
        return Id;
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

    public String getName() {
        return trainerName;
    }


}
