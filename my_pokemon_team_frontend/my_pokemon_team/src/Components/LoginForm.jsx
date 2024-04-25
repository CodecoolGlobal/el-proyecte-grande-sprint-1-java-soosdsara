import { useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom';

function LoginForm() {
    const navigate = useNavigate();
    const { id } = useParams();
    const [name, setName] = useState("");
    const [password, setPassword] = useState("");

    function handleNameChange(e) {
        setName(e.target.value)
    }

    function handlePasswordChange(e) {
        setPassword(e.target.value);
    }

    return (
        <div>
            <h1>Welcome Trainer in My Pokemon Team!</h1>
            <form >
                <div>
                    <label htmlFor="loginName"> Trainer name:
                        <input type="text"
                            name="loginName"
                            id="loginName"
                            value={name}
                            onChange={handleNameChange}
                        />
                    </label>
                </div>
                <div>
                    <label htmlFor="loginPassword"> Password:
                        <input type="password"
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
                <button onClick={() => navigate(`/userpage/${id}`)}>Log in</button>
            </div>
        </div>
    )
}

export default LoginForm