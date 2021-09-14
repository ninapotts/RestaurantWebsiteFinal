import React, { useState } from 'react'
import { useHistory } from "react-router-dom";

const NewUserForm = (props) => {
    const [userName, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [jwt, setno] = useState('');

    const history = useHistory();

    const onSubmit = (event) => {
        event.preventDefault();

        const user = { userName: userName, password: password }

        fetch("http://localhost:7060/api/user", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                Authorization: "Bearer " + props.jwt
            }, body: JSON.stringify(user)
        }).then(response => response.json()).then(data => { setUsername(data.userName); setPassword(data.pasword); props.setJwt(data.jwt) }).catch(error => {
            console.log('Error: ', error);
        });

        fetch("http://localhost:7060/api/authenticate", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }, body: JSON.stringify(user)
        }).then(response => response.json()).then(data => { console.log(data); props.setJwt(data.jwt); setno(data.jwt) }).catch(error => {
            console.log('Error: ', error);
        });



        history.push({ pathname: '/loggedIn', state: { user: user, isSignedIn: true, jwt: props.jwt } });
    };

    return (
        <div style={{ margin: 20, border: '1px solid black', padding: 10 }}>
            <h4>New User Form</h4>
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



                <input type='submit' className='btn btn-primary' />
            </form>
        </div>
    )
}

export default NewUserForm
