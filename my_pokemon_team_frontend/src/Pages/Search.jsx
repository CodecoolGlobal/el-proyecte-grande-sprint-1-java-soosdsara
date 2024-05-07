import { useParams } from "react-router-dom";
import { useState } from "react";
import SearchForm from "../Components/SearchForm";
import PokemonList from "../Components/PokemonList";


function Search() {
  const { id } = useParams();
  const [pokemons, setPokemons] = useState([]);

  const fetchPokemons = async (search) => {
      const response = await fetch(`/api/pokemons?search=${search}`);
      if (!response.ok) {
        throw new Error("Failed to fetch pokemons");
      }
      const data = await response.json();
      setPokemons(data);
  };

  const handleSearch = (search) => {
    fetchPokemons(search);
  };

  const addPokemon = async (pokemon) => {
      const response = await fetch(`/api/pokemons/${id}`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(pokemon),
      });
      if (!response.ok) {
        throw new Error("Failed to add pokemon");
      }
      return await response.json();
  };

  return (
    <div>
      <SearchForm onSubmit={handleSearch} />
      <PokemonList pokemons={pokemons} onAddPokemon={addPokemon} />
    </div>
  );
}

export default Search;
