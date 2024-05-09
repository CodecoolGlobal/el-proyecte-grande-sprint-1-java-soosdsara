import { useState, useEffect } from 'react';
import { useLocation } from "react-router-dom";
import Pokemon from '../Components/Pokemon';
import { useNavigate } from 'react-router';

const UserPage = () => {
  const trainerName = JSON.parse(localStorage.getItem("trainerName"));
  const [pokemons, setPokemons] = useState([]);
  const navigate = useNavigate();

  useEffect(() =>{
   
    async function fetchPokemons(){
      const response = await fetch (`/api/pokemons/${trainerName}`);
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
              const newPokemons = [...pokemons]
              for (const pokemon of newPokemons){
                if (pokemon.publicId == e.target.id){
                  pokemon.nickName = e.target.value
                }
              }
              setPokemons(newPokemons);
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