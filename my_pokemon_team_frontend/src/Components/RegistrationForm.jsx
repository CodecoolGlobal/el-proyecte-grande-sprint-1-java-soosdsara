import { useState } from 'react'
import { useNavigate } from 'react-router'

function RegistrationForm() {
    const navigate = useNavigate();
    const [name, setName] = useState("");
    const [password, setPassword] = useState("");
    const [showPassword, setShowPassword] = useState(false);
    const [message, setMessage] = useState({ show: false, content: "" });


    const passwordCriteria = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$/;

    async function postTrainer(e) {
        e.preventDefault();

        if (!passwordCriteria.test(password)) {
            setMessage({ show: true, content: "Password does not meet requirements."});
            return;
        }

        try{
            const response = await fetch('/api/trainer/registration', {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ name, password }),
            });

            if (!response.ok) {
                setMessage({ show: true, content: "Trainer name is taken. Please try another name."});
                throw new Error('Server error: ' + response.status);
            }

            navigate("/");
        } catch (error) {
            console.error(error.message);
        }
    }

    function handleNameChange(e) {
        setName(e.target.value);
        setMessage({ show: false, content: "" });
    }

    function handlePasswordChange(e) {
        setPassword(e.target.value);
        setMessage({ show: false, content: ""});
    }

    function togglePasswordVisibility() {
        setShowPassword(!showPassword);
    }

    return (
        <div>
            <h1>Start your POKEMON journey HERE!</h1>
            <form onSubmit={postTrainer}>
                <table>
                    <tbody>
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
                    </tbody>
                </table>
                <div><small>Password must be at least 8 characters long and contain at least one lowercase letter, one uppercase letter, and one number.</small></div>
                <button>Sign up</button>
            </form>
            {message.show ? <p style={{ color: 'red' }}><small>{message.content}</small></p> : null}
        </div>
    )
}

export default RegistrationForm