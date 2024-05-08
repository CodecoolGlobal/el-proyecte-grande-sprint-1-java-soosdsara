package com.codecool.my_pokemon_team.service;

import com.codecool.my_pokemon_team.controller.dto.TrainerDTO;
import com.codecool.my_pokemon_team.model.trainer.Trainer;
import com.codecool.my_pokemon_team.repository.TrainerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class TrainerServiceTest {
    @Mock
    private TrainerRepository trainerRepository;

    @InjectMocks
    private TrainerService trainerService;

    @Test
    public void testAddTrainer_happyCase(){
        //Arrange
        String trainerName = "TG";
        String password = "grookey1234";
        TrainerDTO expectedTrainerDTO = new TrainerDTO(trainerName, password);

       /* Trainer trainerEntity = createTrainerEntity(trainerName, password);
        when(trainerRepository.save(trainerEntity)).thenReturn(trainerEntity);*/
        //Act
        TrainerDTO actualTrainerDTO = trainerService.addTrainer(trainerName, password);
        //Assert
        assertEquals(expectedTrainerDTO, actualTrainerDTO);
    }

    private Trainer createTrainerEntity(String name, String password) {
        Trainer trainerEntity = new Trainer();
        trainerEntity.setTrainerName(name);
        trainerEntity.setPassword(password);

        return trainerEntity;
    }

}