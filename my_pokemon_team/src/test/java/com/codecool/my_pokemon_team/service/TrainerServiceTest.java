package com.codecool.my_pokemon_team.service;

import com.codecool.my_pokemon_team.controller.dto.TrainerDTO;
import com.codecool.my_pokemon_team.model.trainer.Trainer;
import com.codecool.my_pokemon_team.repository.TrainerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class TrainerServiceTest {
    @Mock
    private TrainerRepository trainerRepository;

    @InjectMocks
    private TrainerService trainerService;

    @Test
    public void testAddTrainer_happyCase() {
        //Arrange
        String trainerName = "TG";
        String password = "grookey1234";
        TrainerDTO expectedTrainerDTO = new TrainerDTO(trainerName, password);

        //Act
        TrainerDTO actualTrainerDTO = trainerService.addTrainer(trainerName, password);
        //Assert
        assertEquals(expectedTrainerDTO, actualTrainerDTO);
    }

    @Test
    public void testDeleteTrainer_whenDelete_thenRemoveTrainer() {
        //Arrange
        String trainerName = "TG";
        String password = "grookey1234";
        Long id = 1L;
        Trainer trainerEntity = createTrainerEntity(trainerName, password, id);
        trainerRepository.save(trainerEntity);

        //Act
        trainerService.deleteTrainer(id);
        Optional<Trainer> deletedTrainer = trainerRepository.findById(id);

        //Assert
        assertThat(deletedTrainer).isEmpty();
    }

    private Trainer createTrainerEntity(String name, String password, Long id) {
        Trainer trainerEntity = new Trainer();
        trainerEntity.setId(id);
        trainerEntity.setTrainerName(name);
        trainerEntity.setPassword(password);

        return trainerEntity;
    }

}