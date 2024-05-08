import { useState, useEffect } from 'react'
import { useLocation } from "react-router-dom";
import Pokemon from '../Components/Pokemon';

const UserPage = () => {
  const trainerId = useLocation().pathname.split("/userpage/")[1];
  const [pokemons, setPokemons] = useState([]);

  useEffect(() =>{
   
    async function fetchPokemons(){
      const response = await fetch (`/api/pokemons/${trainerId}`);
      const pokemons = await response.json();
      console.log(pokemons);
      setPokemons(pokemons);
    }
    fetchPokemons();
  },[])

  return (
    <>
      {pokemons  && pokemons?.map((pokemon) => <div key={pokemon.species}><Pokemon pokemon={pokemon}></Pokemon></div>)}
    </>
  )
}

export default UserPage