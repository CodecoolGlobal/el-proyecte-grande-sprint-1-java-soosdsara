import { useState, useEffect } from "react";
import Pokemon from "../Components/Pokemon";
import { useNavigate } from "react-router";

const UserPage = () => {
  const trainerName = JSON.parse(localStorage.getItem("trainerName"));
  const [pokemons, setPokemons] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    async function fetchPokemons() {
      const response = await fetch(`/api/pokemons/${trainerName}`);
      if (response.status != 200) {
        navigate("/");
      }
      const pokemons = await response.json();
      console.log(pokemons);
      setPokemons(pokemons);
    }
    fetchPokemons();
  }, []);

  return (
    <>
      {pokemons.length == 0 ? (
        <div>
          <label>You have currently no pokemons.</label>
        </div>
      ) : (
        pokemons?.map((pokemon) => (
          <div key={pokemon.species}>
            <Pokemon pokemon={pokemon}></Pokemon>
          </div>
        ))
      )}
    </>
  );
};

export default UserPage;
