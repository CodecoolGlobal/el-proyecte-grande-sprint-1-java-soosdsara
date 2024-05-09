/* eslint-disable react/prop-types */

import "./Pokemon.css";

const Pokemon = ({ pokemon }) => {
  const species = pokemon.species[0].toUpperCase() + pokemon.species.substring(1); 
  return (
    <div>
      <label>Species: {species}</label>
      <label></label>
      <br></br>
      <label>
        Type: {pokemon.types.map((t, i) => (
          i === 0 && pokemon.types.length > 1 ? <span key={t}>{t + " / "}</span>
            : <span key={t}>{t}</span>
          ))}
      </label>
      <br></br>
      <img src={pokemon.pic} alt="picture" />
      <br></br>
      <label>HP: {pokemon.hp}</label>
      <br></br>
      <label>Attack: {pokemon.attack}</label>
      <br></br>
      <label>Defense: {pokemon.defense}</label>
      <br></br>
      {pokemon.nickName ? <label>Nickname: {pokemon.nickName}</label> : null}
    </div>
  );
};

export default Pokemon;
