import { useState, useEffect } from "react";
import { useNavigate } from "react-router";
import SearchForm from "../Components/SearchForm";
import PokemonList from "../Components/PokemonList";


function Search() {
  const trainerData = JSON.parse(localStorage.getItem("trainerData"));
  const [pokemons, setPokemons] = useState([]);
  const navigate = useNavigate();


  useEffect(() => {
    if (!trainerData) {
      navigate("/");
    }
  }, []);

  const fetchPokemons = async (search) => {
    const response = await fetch(`/api/pokemons?search=${search}`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${trainerData.jwt}`,
      },
    });
    if (!response.ok) {
      localStorage.removeItem("trainerData");
      navigate("/");
      throw new Error("Failed to fetch pokemon");
    }
    const pokemons = await response.json();
    setPokemons(pokemons);
  }

  const addPokemon = async (pokemon) => {
      const response = await fetch(`/api/pokemons/${trainerData.userName}`, {
        method: "POST",
        headers: { "Content-Type": "application/json",
        Authorization: `Bearer ${trainerData.jwt}`,
         },
        body: JSON.stringify(pokemon),
      });
      if (!response.ok) {
        throw new Error("Failed to add pokemon");
      }
      return response.json
  };

  return (
    <div>
      <SearchForm onSubmit={fetchPokemons} />
      <PokemonList pokemons={pokemons} onAddPokemon={addPokemon} />
    </div>
  );
}

export default Search;
