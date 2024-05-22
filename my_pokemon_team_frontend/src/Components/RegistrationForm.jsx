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
        try {
            const response = await fetch('/api/trainer', {
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
        } catch (error) {
            console.error(error.message + " - Please choose another trainer name.")
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
            {showMessage ? <p style={{ color: 'red' }}>Unfortunately, this trainer name is already taken. Please choose another one!</p> : null}
            <form onSubmit={postTrainer}>
                <table>
                    <tr>
                        <td>
                            <label htmlFor="RegisterName"> Trainer name: </label>
                        </td>
                        <td>
                            <input type="text"
                                name="RegisterName"
                                id="RegisterName"
                                value={name}
                                onChange={handleNameChange}
                            />
                        </td>
                        <td></td>
                    </tr>
                
                        <tr>
                            <td>
                                <label htmlFor="RegisterPassword"> Password: </label>
                            </td>
                            <td>
                                <input type={showPassword ? 'text' : 'password'}
                                    name="RegisterPassword"
                                    id="RegisterPassword"
                                    value={password}
                                    onChange={handlePasswordChange}
                                />
                            </td>
                        
                        <td>
                            <button type="button" onClick={togglePasswordVisibility}>
                                {showPassword ? 'Hide' : 'Show'} Password
                            </button>
                        </td>
                        </tr>
                </table>
                <button>Sign up</button>
            </form>
        </div>
    )
}

export default RegistrationForm