import { useState } from 'react'
import { useNavigate } from 'react-router'

function RegistrationForm() {
    const navigate = useNavigate();
    const [name, setName] = useState("");
    const [password, setPassword] = useState("");
    const [showPassword, setShowPassword] = useState(false);
    const [showMessage, setShowMessage] = useState(false);

    async function postTrainer(e) {
        e.preventDefault();
        try{
            const response = await fetch('/api/trainer/registration', {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ name, password }),
            });

            if (!response.ok) {
                setShowMessage(true);
                throw new Error('Server error: ' + response.status);
            }

            navigate("/");
        } catch (error){
            console.error( error.message + " - Please choose another trainer name.")
        }
    }

    function handleNameChange(e) {
        setName(e.target.value)
    }

    function handlePasswordChange(e) {
        setPassword(e.target.value);
    }

    function togglePasswordVisibility() {
        setShowPassword(!showPassword);
    }

    return (
        <div>
            <h1>Start your POKEMON journey HERE!</h1>
            <form onSubmit={postTrainer}>
                <div>
                    {showMessage ? <p style={{ color: 'red' }}>Unfortunately, this trainer name is already taken. Please choose another one!</p> : null}
                    <label htmlFor="RegisterName"> Trainer name:
                        <input type="text"
                            name="RegisterName"
                            id="RegisterName"
                            value={name}
                            onChange={handleNameChange}
                        />
                    </label>
                </div>
                <div>
                    <label htmlFor="RegisterPassword"> Password:
                        <input type={showPassword ? 'text' : 'password'}
                            name="RegisterPassword"
                            id="RegisterPassword"
                            value={password}
                            onChange={handlePasswordChange}
                        />
                    </label>
                    <button type="button" onClick={togglePasswordVisibility}>
                        {showPassword ? 'Hide' : 'Show'} Password
                    </button>
                </div>
                <button>Sign up</button>
            </form>
        </div>
    )
}

export default RegistrationForm