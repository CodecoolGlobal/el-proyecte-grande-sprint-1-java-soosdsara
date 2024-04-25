const Pokemon = ({ pokemon }) => {
  return (
    <div>
      <label>Species: {pokemon.species}</label>
      <label></label>
      <br></br>
      <label>
        Type:
        <div>
          {pokemon.types.map((t) => (
            <div key={t}>{t}</div>
          ))}
        </div>
      </label>

      <br></br>
      <label>Picture:</label>
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
