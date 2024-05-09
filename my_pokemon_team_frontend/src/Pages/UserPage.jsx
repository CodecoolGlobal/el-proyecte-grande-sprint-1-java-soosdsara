import { useState, useEffect } from 'react';
import { useLocation } from "react-router-dom";
import Pokemon from '../Components/Pokemon';
import { useNavigate } from 'react-router';

const UserPage = () => {
  const trainerId = useLocation().pathname.split("/userpage/")[1];
  const [pokemons, setPokemons] = useState([]);
  const navigate = useNavigate();

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
  useEffect(() =>{
    fetchPokemons();
  },[])

  async function inputHandler(e){
    {
        try{
          const response = await fetch(`/api/pokemons/${e.target.id}`, {
              method: "PATCH",
              headers: {
                  "Content-Type": "application/json",
              },
              body: JSON.stringify(e.target.value),
          });
         if (response.ok) {
            fetchPokemons();
          }
        }catch (error){
          console.error( error.message + "Nickname update not successful.")
      }
    }
  }

  return (
    <>
      {pokemons.length == 0 ? <div>
      <label>You have currently no pokemons.</label>
      <button type="button" onClick={() => navigate(`/userpage/${trainerId}/search`)}>Search Pokemons</button>
      </div> :
       pokemons?.map((pokemon) => <div key={pokemon.publicId}><Pokemon pokemon={pokemon}></Pokemon><input 
       id={pokemon.publicId} placeholder={"nickname"} onKeyDown={(e) => {
        if (e.key === "Enter")
            inputHandler(e);
        }}></input></div>)}
    </>
  )
}

export default UserPage