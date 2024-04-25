package com.codecool.my_pokemon_team.service;

import com.codecool.my_pokemon_team.controller.dto.PokemonDTO;
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
    private final List<String> pokemonNames;
    private final ObjectMapper objectMapper;


    public PokemonApiService(RestTemplate restTemplate) throws JsonProcessingException {
        this.restTemplate = restTemplate;
        this.objectMapper = new ObjectMapper();
        this.pokemonNames = getAllPokemonNames();
    }

    public List<PokemonDTO> getSearchedPokemons(String search) throws JsonProcessingException {
        List<PokemonDTO> pokemonDTOS = new ArrayList<>();
        List<String> names = pokemonNames.stream()
                .filter(pokemonName -> pokemonName.startsWith(search))
                .toList();

        for (String name : names) {
            pokemonDTOS.add(getPokemon(name));
        }
        return pokemonDTOS;
    }

    private List<String> getAllPokemonNames() throws JsonProcessingException {
        List<String> pokemonNames = new ArrayList<>();
        String url = String.format("%s/pokemon?limit=1025", BASE_URL);
        JsonNode pokemonObj = getJsonNode(url);

        for (JsonNode nameNode : pokemonObj.get("results")) {
            pokemonNames.add(nameNode.get("name").asText());
        }
        return pokemonNames;
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

