import React from 'react'

export const CreateAccount = () => {

    const [state, setState] = useState({ username: "", password: "" });

    const handleSubmit = e => {
        e.preventDefault();
        console.log(state);
        fetch("http://localhost:7060/api/user", {
            "method": "POST",
            "headers": {
                "Content-Type": "application/json"
            },
            "body": JSON.stringify(state)
        }).then(result => result.json()).then(data => props.setJwt(data.jwt))

        setState({ username: "", password: "" });
    }

    const handleChange = e => {
        const update = {
            [e.target.name]: e.target.value
        }
        console.log(update);
        const newState = { ...state, ...update }
        setState(newState)
    }

    return (
        <form onSubmit={handleSubmit}>
            <fieldset>
                <label htmlFor="username">Username: </label>
                <input type="text" name="username" id="username" onChange={handleChange} value={state.username} />
                <label htmlFor="password">Password: </label>
                <input type="password" name="password" id="password" onChange={handleChange} value={state.password} />
                <input type="submit" />
            </fieldset>
        </form>
    )
}

export default CreateAccount;

