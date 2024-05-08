import { useState } from "react";
import { useNavigate } from "react-router-dom";

function LoginForm() {
  const navigate = useNavigate();

  const [name, setName] = useState("");
  const [password, setPassword] = useState("");

  function handleNameChange(e) {
    setName(e.target.value);
  }

  function handlePasswordChange(e) {
    setPassword(e.target.value);
  }

  async function handleLogin() {
    const response = await fetch(`/api/trainer/${name}`);
    if (!response.ok) {
      throw new Error("No trainer with this username:" + name);
    }
    //Mentés
    navigate(`/userpage`);
  }

  return (
    <div>
      <h1>Welcome Trainer in My Pokemon Team!</h1>
      <form>
        <div>
          <label htmlFor="loginName">
            {" "}
            Trainer name:
            <input
              type="text"
              name="loginName"
              id="loginName"
              value={name}
              onChange={handleNameChange}
            />
          </label>
        </div>
        <div>
          <label htmlFor="loginPassword">
            {" "}
            Password:
            <input
              type="password"
              name="loginPassword"
              id="loginPassword"
              value={password}
              onChange={handlePasswordChange}
            />
          </label>
        </div>
      </form>
      <div>
        <button onClick={() => navigate("/registration")}>Sign up</button>
        <button onClick={() => handleLogin()}>Log in</button>
      </div>
    </div>
  );
}

export default LoginForm;
