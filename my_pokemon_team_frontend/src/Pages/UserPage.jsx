import { useState, useEffect } from "react";
import Pokemon from "../Components/Pokemon/Pokemon";
import { useNavigate } from "react-router";

const UserPage = () => {
  const trainerData = JSON.parse(localStorage.getItem("trainerData"));
  const [pokemons, setPokemons] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    if (!trainerData) {
      navigate("/");
    }
    fetchPokemons();
  }, []);

  async function fetchPokemons() {
    const response = await fetch(`/api/pokemons/${trainerData.userName}`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${trainerData.jwt}`,
      },
    });
    if (!response.ok) {
      localStorage.removeItem("trainerData");
      navigate("/");
      throw new Error("Failed to fetch pokemon");
    }
    const pokemons = await response.json();
    setPokemons(pokemons);
  }

  async function inputHandler(e) {
    {
      try {
        const response = await fetch(`/api/pokemons/${e.target.id}`, {
          method: "PATCH",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${trainerData.jwt}`,
          },
          body: JSON.stringify(e.target.value),
        });
        if (response.ok) {
          updatePokemons(e);
        }
      } catch (error) {
        console.error(error.message + "Nickname update not successful.");
      }
    }
  }

  function updatePokemons(e) {
    const newPokemons = [...pokemons];
    for (const pokemon of newPokemons) {
      if (pokemon.publicId == e.target.id) {
        pokemon.nickName = e.target.value;
      }
    }
    setPokemons(newPokemons);
  }

  return (
    <>
      {pokemons.length == 0 ? (
        <div>
          <label>You have currently no pokemons.</label>
        </div>
      ) : (
        pokemons?.map((pokemon) => (
          <div key={pokemon.publicId} className={"user_pokemon"}>
            <Pokemon pokemon={pokemon}></Pokemon>
            <input
              id={pokemon.publicId}
              placeholder={"nickname"}
              onKeyDown={(e) => {
                if (e.key === "Enter") inputHandler(e);
              }}
            ></input>
          </div>
        ))
      )}
    </>
  );
};

export default UserPage;
