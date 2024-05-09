import { useState } from "react";
import SearchForm from "../Components/SearchForm";
import PokemonList from "../Components/PokemonList";


function Search() {
  const trainerName = JSON.parse(localStorage.getItem("trainerName"));
  const [pokemons, setPokemons] = useState([]);

  const fetchPokemons = async (search) => {
      const response = await fetch(`/api/pokemons?search=${search}`);
      if (!response.ok) {
        setPokemons([])
        throw new Error("No such pokemon found.");
      }
      const data = await response.json();
      setPokemons(data);
  };

  const handleSearch = (search) => {
    fetchPokemons(search);
  };

  const addPokemon = async (pokemon) => {
      const response = await fetch(`/api/pokemons/${trainerName}`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(pokemon),
      });
      if (!response.ok) {
        throw new Error("Failed to add pokemon");
      }
      return await response.json
  };

  return (
    <div>
      <SearchForm onSubmit={handleSearch} />
      <PokemonList pokemons={pokemons} onAddPokemon={addPokemon} />
    </div>
  );
}

export default Search;
