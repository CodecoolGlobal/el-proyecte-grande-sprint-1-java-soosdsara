import React from 'react'
import { useState } from 'react';

const Pokemon = (pokemon) => {
  const[id, setId] = useState(pokemon?.id??"");
  const[species, setSpecies] = useState(pokemon?.species??"");
  const[type, setType] = useState(pokemon?.type??"");
  const[pic, setPic] = useState(pokemon?.pic??"");
  const[hp, setHp] = useState(pokemon?.hp??"");
  const[attack, setAttack] = useState(pokemon?.attack??"");
  const[defense, setDefense] = useState(pokemon?.defense??"");
  const[nickName, setNickName] = useState(pokemon?.nickName??"");

  return (
    <div>
      <label>ID:</label><label>{id}</label>
      <label>Species:</label><label>{species}</label>
      <label>Type:</label><label>{type}</label>
      <label>Picture:</label><img src={pic} alt="picture" />
      <label>HP:</label><label>{hp}</label>
      <label>Attack:</label><label>{attack}</label>
      <label>Defense:</label><label>{defense}</label>
      <label>Nickname:</label><label>{nickName}</label>
    </div>
  )
}

export default Pokemon