import { useState } from "react";
import { useParams } from "react-router-dom";

function Search() {
  const { id } = useParams();

  const [search, setSearch] = useState("");
  const [pokemons, setPokemons] = useState([]);

  const fetchPokemons = () => {
    return fetch(`/api/pokemons?search=${search}`).then((res) => res.json());
  };

  async function handleSearch(e) {
    e.preventDefault();
    try {
      const data = fetchPokemons();
      setPokemons(data);
    } catch (error) {
      console.error(error);
    }
  }

  async function addPokemon(pokemon) {
    const response = await fetch(`/api/trainer/${id}`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(pokemon),
    });
    return await response.json();
  }

  return (
    <div>
      <form onSubmit={handleSearch}>
        <input
          name="pokemon"
          value={search}
          onChange={(e) => setSearch(e.target.value)}
        />
        <button type="submit">Search</button>
      </form>
      <ul>
        {pokemons.map((pokemon) => (
          <>
            <li>{pokemon.species}</li>
            <button type="button" onClick={() => addPokemon(pokemon)}>
              Add to my team
            </button>
          </>
        ))}
      </ul>
    </div>
  );
}

export default Search;
