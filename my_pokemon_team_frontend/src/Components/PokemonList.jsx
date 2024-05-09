/* eslint-disable react/prop-types */
import Pokemon from "./Pokemon/Pokemon";

function PokemonList({ pokemons, onAddPokemon }) {
  return (
    <div>
      {pokemons.length >= 1 ? (pokemons.map((pokemon) => (
        <div key={pokemon.species} className="user_pokemon">
          <Pokemon pokemon={pokemon} />
          <button type="button" onClick={() => onAddPokemon(pokemon)}>
            Add to my team
          </button>
        </div>
      ))) : <div>No pokemon found!</div>}
    </div>
  );
}

export default PokemonList;
