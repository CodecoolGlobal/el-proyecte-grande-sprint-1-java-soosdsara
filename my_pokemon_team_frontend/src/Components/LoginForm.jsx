import { useState } from "react";
import { useNavigate } from "react-router-dom";

function LoginForm() {
  const navigate = useNavigate();

  const [name, setName] = useState("");
  const [password, setPassword] = useState("");
  const [showMessage, setShowMessage] = useState(false);

  function handleNameChange(e) {
    setName(e.target.value);
  }

  function handlePasswordChange(e) {
    setPassword(e.target.value);
  }

  async function handleLogin(e) {
    e.preventDefault();
    const response = await loginUser();

    if (!response.ok) {
      setShowMessage(true);
      localStorage.removeItem("trainerData");
      throw new Error(`No trainer with this username: ${name}}`);
    } else {
      const responseBody = await response.json();
      localStorage.setItem("trainerData", JSON.stringify(responseBody));
      navigate(`/userpage`);
    }
  }

  async function loginUser() {
    const response = await fetch("/api/trainer", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ name, password }),
    });
    return response;
  }

  return (
    <div className="login">
      <h1>Welcome Trainer in My Pokemon Team!</h1>
      <form>
        <div>
          {showMessage ? (
            <p style={{ color: "red" }}>Incorrect password or user!</p>
          ) : null}
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
