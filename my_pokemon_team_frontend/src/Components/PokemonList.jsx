import Pokemon from "./Pokemon";


function PokemonList({ pokemons, onAddPokemon }) {
  return (
    <ul>
      {pokemons.map((pokemon) => (
        <li key={pokemon.species}>
          <Pokemon pokemon={pokemon}/>
          <button type="button" onClick={() => onAddPokemon(pokemon)}>
            Add to my team
          </button>
        </li>
      ))}
    </ul>
  );
}

export default PokemonList;
