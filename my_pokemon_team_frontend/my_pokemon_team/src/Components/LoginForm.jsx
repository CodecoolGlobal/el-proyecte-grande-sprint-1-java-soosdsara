import { useState } from 'react'
import { useNavigate } from 'react-router-dom';



function LoginForm() {
    const navigate = useNavigate();
    const [name, setName] = useState("");
    const [password, setPassword] = useState("");

    return (
        <div>
            <h1>Welcome Trainer in My Pokemon Team!</h1>
            <form >
                <div>
                    <label htmlFor="logInName"> Trainer name:
                        <input type="text"
                            name="name"
                            id="logInName"
                            value={name}
                            onChange={e => setName(e.target.value)}
                        />
                    </label>
                </div>
                <div>
                    <label htmlFor="logInPassword"> Password:
                        <input type="password"
                            name="logInPassword"
                            id="logInPassword"
                            value={password}
                            onChange={e => setPassword(e.target.value)}
                        />
                    </label>
                </div>
            </form>
            <div>
                <button onClick={() => navigate("/registration")}>Sign up</button>
                <button>Log in</button>
            </div>
        </div>
    )
}

export default LoginForm