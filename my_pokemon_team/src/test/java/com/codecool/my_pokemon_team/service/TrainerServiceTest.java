package com.codecool.my_pokemon_team.service;

import com.codecool.my_pokemon_team.controller.dto.TrainerDTO;
import com.codecool.my_pokemon_team.model.trainer.Trainer;
import com.codecool.my_pokemon_team.repository.TrainerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TrainerServiceTest {
    @Captor
    ArgumentCaptor<Trainer> trainerCaptor;
    @Captor
    ArgumentCaptor<String> stringCaptor;

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

        Trainer expectedTrainer = createTrainerEntity(trainerName, password);
        //Act
        trainerService.addTrainer(expectedTrainerDTO);
        //Assert
        verify(trainerRepository).save(trainerCaptor.capture());
        Trainer trainer = trainerCaptor.getValue();

        assertEquals(expectedTrainer, trainer);
    }

    @Test
    public void testDeleteTrainer_whenDelete_thenRemoveTrainer() {
        //Arrange
        String trainerName = "TG";
        //Act
        trainerService.deleteTrainer(trainerName);
        //Assert
        verify(trainerRepository).deleteByTrainerName(stringCaptor.capture());
        String deletedTrainerName = stringCaptor.getValue();
        assertEquals(trainerName, deletedTrainerName);
    }

    @Test
    public void testUpdatePassword_whenUpdate_thenTrainerObject() {
        //Arrange
        String trainerName = "TG";
        String password = "pika";
        //Act
        trainerService.updatePassword(trainerName, password);
        //Assert
        verify(trainerRepository).setTrainerEntityPasswordByTrainerName(stringCaptor.capture(), stringCaptor.capture());
        String actual = stringCaptor.getAllValues().get(0);
        String newPassword = stringCaptor.getAllValues().get(1);

        assertEquals(actual, trainerName);
        assertEquals(newPassword, password);
    }

    private Trainer createTrainerEntity(String name, String password) {
        Trainer trainerEntity = new Trainer();
        trainerEntity.setTrainerName(name);
        trainerEntity.setPassword(password);

        return trainerEntity;
    }
}