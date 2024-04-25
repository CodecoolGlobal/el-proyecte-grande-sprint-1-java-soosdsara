import React from 'react'
import { useState } from 'react';

const Pokemon = ({pokemon}) => {
  const[id, setId] = useState(pokemon?.id);
  const[species, setSpecies] = useState(pokemon?.species);
  const[type, setType] = useState(pokemon?.type);
  const[pic, setPic] = useState(pokemon?.pic);
  const[hp, setHp] = useState(pokemon?.hp);
  const[attack, setAttack] = useState(pokemon?.attack);
  const[defense, setDefense] = useState(pokemon?.defense);
  const[nickName, setNickName] = useState(pokemon?.nickName);

  console.log(pokemon.species);
  return (
    <div>
      <label>ID:</label><label>{id}</label><br></br>
      <label>Species:</label><label>{species}</label><br></br>
      <label>Type:</label><label>{type}</label><br></br>
      <label>Picture:</label><img src={pic} alt="picture" /><br></br>
      <label>HP:</label><label>{hp}</label><br></br>
      <label>Attack:</label><label>{attack}</label><br></br>
      <label>Defense:</label><label>{defense}</label><br></br>
      <label>Nickname:</label><label>{nickName}</label><br></br>
    </div>
  )
}

export default Pokemon