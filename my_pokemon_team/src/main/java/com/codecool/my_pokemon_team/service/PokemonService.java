package com.codecool.my_pokemon_team.service;

import com.codecool.my_pokemon_team.controller.dto.PokemonDTO;
import com.codecool.my_pokemon_team.model.pokemon.PokemonType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class PokemonService {

    private final RestTemplate restTemplate;
    private final List<String> pokemonNames;

    public PokemonService(RestTemplate restTemplate) throws JsonProcessingException {
        this.restTemplate = restTemplate;
        this.pokemonNames = getAllPokemonNames();
    }

    private PokemonDTO getPokemon(String name) throws JsonProcessingException {
        String url = "https://pokeapi.co/api/v2/pokemon/" + name + "/";
        String response = restTemplate.getForObject(url, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode pokemonObj = mapper.readTree(response);

        String pic = getPokemonSprite(pokemonObj);
        String species = getPokemonSpecies(pokemonObj);
        int hp = getPokemonStat(pokemonObj, 0);
        int attack = getPokemonStat(pokemonObj, 1);
        int defense = getPokemonStat(pokemonObj, 2);
        List<PokemonType> types = getPokemonTypes(pokemonObj);

        return new PokemonDTO(species, types, pic, hp, attack, defense);
    }

    public List<PokemonDTO> getSearchedPokemons(String search) throws JsonProcessingException {
        List<PokemonDTO> pokemonDTOS = new ArrayList<>();
        List<String> names = pokemonNames.stream().filter(pokemonName -> pokemonName.startsWith(search)).toList();
        for (String name : names) {
            pokemonDTOS.add(getPokemon(name));
        }
        return pokemonDTOS;
    }

    private List<String> getAllPokemonNames() throws JsonProcessingException {

        List<String> pokemonNames = new ArrayList<>();
        String url = "https://pokeapi.co/api/v2/pokemon?limit=1025";
        String response = restTemplate.getForObject(url, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode pokemonObj = mapper.readTree(response);

        for (JsonNode nameNode : pokemonObj.get("results")) {
            pokemonNames.add(nameNode.get("name").asText());
        }
        return pokemonNames;
    }


    private String getPokemonSprite(JsonNode pokemonObj) {
        return pokemonObj.get("sprites").get("front_default").asText();
    }

    private String getPokemonSpecies(JsonNode pokemonObj) {
        return pokemonObj.get("name").asText();
    }

    private int getPokemonStat(JsonNode pokemonObj, int index) {
        return pokemonObj.get("stats").get(index).get("base_stat").asInt();
    }

    private List<PokemonType> getPokemonTypes(JsonNode pokemonObj) {
        List<PokemonType> types = new ArrayList<>();
        JsonNode typesNode = pokemonObj.get("types");
        for (JsonNode typeNode : typesNode) {
            types.add(PokemonType.valueOf(typeNode.get("type").get("name").asText().toUpperCase()));
        }
        return types;
    }
}

