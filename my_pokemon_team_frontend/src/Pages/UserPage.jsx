import { useState, useEffect } from 'react';
import { useLocation } from "react-router-dom";
import Pokemon from '../Components/Pokemon';
import { useNavigate } from 'react-router';

const UserPage = () => {
  const trainerId = useLocation().pathname.split("/userpage/")[1];
  const [pokemons, setPokemons] = useState([]);
  const navigate = useNavigate();

  useEffect(() =>{
   
    async function fetchPokemons(){
      const response = await fetch (`/api/pokemons/${trainerId}`);
      console.log(response.status)
      if(response.status != 200){
        navigate("/");
      }
      const pokemons = await response.json();
      console.log(pokemons);
      setPokemons(pokemons);
    }
    fetchPokemons();
  },[])

  return (
    <>
      {pokemons.length == 0 ? <label>You have currently no pokemons.</label>:
       pokemons?.map((pokemon) => <div key={pokemon.species}><Pokemon pokemon={pokemon}></Pokemon></div>)}
    </>
  )
}

export default UserPage