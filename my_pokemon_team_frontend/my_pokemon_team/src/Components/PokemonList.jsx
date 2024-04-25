

function PokemonList({ pokemons, onAddPokemon }) {
  return (
    <ul>
      {pokemons.map((pokemon) => (
        <li key={pokemon.id}>
          {pokemon.species}
          <button type="button" onClick={() => onAddPokemon(pokemon)}>
            Add to my team
          </button>
        </li>
      ))}
    </ul>
  );
}

export default PokemonList;
