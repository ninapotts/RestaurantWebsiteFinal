import React, { useState } from 'react'
import { useHistory } from "react-router-dom";

const LogInForm = (props) => {
    const [userName, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const history = useHistory();

    const signIn = () => {

        //add new user to db

        // const user = { userName: userName, password: password }

        // fetch("http://localhost:7060/user", {
        //     method: 'POST',
        //     headers: {
        //         'Content-Type': 'application/json'
        //     }, body: JSON.stringify(user)
        // }).then(response => response.json()).then(data => { console.log('Success: ', user) }).catch(error => {
        //     console.log('Error: ', error);
        // });

        console.log(props.location)
        console.log(props.location.setSignedIn)
        props.location.setSignedIn(true);

        history.push({ pathname: '/', state: { isLoggedIn: true } });

    }

    const onSubmit = (event) => {
        event.preventDefault();
    };

    return (
        <div style={{ margin: 20, border: '1px solid black', padding: 10 }}>
            <h4>Login Form</h4>
            <form onSubmit={onSubmit}>
                <div className='mb-3'>
                    <label htmlFor='userName' className='form-label'>
                        Username
                    </label>
                    <input
                        type='text'
                        id='userName'
                        className='form-control'
                        value={userName}
                        onChange={(event) => {
                            setUsername(event.target.value);
                        }}
                    />
                </div>

                <div className='mb-3'>
                    <label
                        htmlFor='password'
                        className='form-label'
                        style={{ display: 'block' }}>
                        Password
                    </label>
                    <input type="password" id="password" className='form-control' value={password}
                        onChange={(event) => {
                            setPassword(event.target.value);
                        }} />
                </div>



                <input type='submit' className='btn btn-primary' onClick={signIn} />
            </form>
        </div>
    )
}

export default LogInForm
