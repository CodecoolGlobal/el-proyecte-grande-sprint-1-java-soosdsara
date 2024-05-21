package com.codecool.my_pokemon_team.security.service;

import com.codecool.my_pokemon_team.model.trainer.Trainer;
import com.codecool.my_pokemon_team.repository.TrainerRepository;
import com.codecool.my_pokemon_team.service.TrainerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsServiceImpl implements UserDetailsService {
    private final TrainerRepository trainerRepository;
    private static final Logger logger = LoggerFactory.
            getLogger(TrainerService.class);

    public UserDetailsServiceImpl(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Trainer trainer = trainerRepository.findByTrainerName(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        List<SimpleGrantedAuthority> roles = new ArrayList<>();

        return new User(trainer.getTrainerName(), trainer.getPassword(), roles);
    }

}
