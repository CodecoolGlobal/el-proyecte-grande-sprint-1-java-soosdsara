package com.codecool.my_pokemon_team.controller;

import com.codecool.my_pokemon_team.controller.dto.TrainerDTO;
import com.codecool.my_pokemon_team.model.JwtResponse;
import com.codecool.my_pokemon_team.security.jwt.JwtUtils;
import com.codecool.my_pokemon_team.service.TrainerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainer")
public class TrainerController {

    private final TrainerService trainerService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;


    public TrainerController(TrainerService trainerService, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.trainerService = trainerService;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/registration")
    public void registerTrainer(@RequestBody TrainerDTO trainerDTO) {
        trainerService.addTrainer(trainerDTO);
    }

    @PatchMapping("{trainerName}")
    @PreAuthorize("isAuthenticated()")
    public void updatePassword(@PathVariable String trainerName, @RequestBody TrainerDTO trainerDTO) {
        trainerService.updatePassword(trainerName, trainerDTO.password());
    }

    @DeleteMapping("{trainerName}")
    @PreAuthorize("isAuthenticated()")
    public void deleteTrainer(@PathVariable String trainerName) {
        trainerService.deleteTrainer(trainerName);
    }

    @PostMapping()
    public ResponseEntity<?> loginTrainer(@RequestBody TrainerDTO trainerDTO) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(trainerDTO.name(), trainerDTO.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        User userDetails = (User) authentication.getPrincipal();
        return ResponseEntity
                .ok(new JwtResponse(jwt, userDetails.getUsername()));
    }
}
