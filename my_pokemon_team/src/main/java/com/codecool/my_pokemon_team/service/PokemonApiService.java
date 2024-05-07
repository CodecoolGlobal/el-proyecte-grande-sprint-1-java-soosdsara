package com.codecool.my_pokemon_team.service;

import com.codecool.my_pokemon_team.controller.dto.PokemonDTO;
import com.codecool.my_pokemon_team.model.pokemon.PokemonSpecies;
import com.codecool.my_pokemon_team.repository.PokemonSpeciesRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.List;

@Service
public class PokemonApiService {

    public static final String BASE_URL = "https://pokeapi.co/api/v2";
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final PokemonSpeciesRepository pokemonSpeciesRepository;


    public PokemonApiService(RestTemplate restTemplate, PokemonSpeciesRepository pokemonSpeciesRepository) throws JsonProcessingException {
        this.restTemplate = restTemplate;
        this.pokemonSpeciesRepository = pokemonSpeciesRepository;
        this.objectMapper = new ObjectMapper();
        //saveAllPokemonSpecies();

    }

    public List<PokemonDTO> getSearchedPokemons(String search) throws JsonProcessingException {
        List<PokemonDTO> pokemonDTOS = new ArrayList<>();
        List<PokemonSpecies> speciesStartingWith = pokemonSpeciesRepository.findBySpeciesStartingWith(search);
        for (PokemonSpecies species : speciesStartingWith) {
            PokemonDTO pokemonDTO = getPokemon(species.getSpecies());
            pokemonDTOS.add(pokemonDTO);
        }
        return pokemonDTOS;
    }

    private void saveAllPokemonSpecies() throws JsonProcessingException {
        List<PokemonSpecies> allSpecies = new ArrayList<>();
        String url = String.format("%s/pokemon?limit=1025", BASE_URL);
        JsonNode pokemonObj = getJsonNode(url);
        for (JsonNode nameNode : pokemonObj.get("results")) {
            allSpecies.add(getSpeciesEntity(nameNode));
        }
        pokemonSpeciesRepository.saveAll(allSpecies);
    }

    private PokemonSpecies getSpeciesEntity(JsonNode nameNode) {
        PokemonSpecies pokemonSpecies = new PokemonSpecies();
        pokemonSpecies.setSpecies(nameNode.get("name").asText());
        return pokemonSpecies;
    }

    private PokemonDTO getPokemon(String name) throws JsonProcessingException {
        String url = String.format("%s/pokemon/%s/", BASE_URL, name);
        JsonNode pokemonObj = getJsonNode(url);

        String pic = pokemonObj.get("sprites").get("other").get("home").get("front_default").asText();
        String species = pokemonObj.get("name").asText();
        int hp = getPokemonStat(pokemonObj, 0);
        int attack = getPokemonStat(pokemonObj, 1);
        int defense = getPokemonStat(pokemonObj, 2);
        List<String> types = getPokemonTypes(pokemonObj);

        return new PokemonDTO(species, types, pic, hp, attack, defense);
    }

    private JsonNode getJsonNode(String url) throws JsonProcessingException {
        String response = restTemplate.getForObject(url, String.class);
        return objectMapper.readTree(response);
    }

    private int getPokemonStat(JsonNode pokemonObj, int index) {
        return pokemonObj.get("stats").get(index).get("base_stat").asInt();
    }

    private List<String> getPokemonTypes(JsonNode pokemonObj) {
        List<String> types = new ArrayList<>();
        JsonNode typesNode = pokemonObj.get("types");
        for (JsonNode typeNode : typesNode) {
            types.add(typeNode.get("type").get("name").asText());
        }
        return types;
    }
}

