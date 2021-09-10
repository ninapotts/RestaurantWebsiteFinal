import React, { useState } from 'react'
import Home from './Home'
import { useHistory } from "react-router-dom";

const LoggedIn = () => {

    return (
        <div>
            <p>Welcome you are logged in</p>

        </div>
    )
}

const LoggedOut = () => {
    return (
        <div>
            <p>welcome you are not logged in</p>

        </div>
    )
}

function SigninPage(props) {
    const [signedIn, setSignedIn] = useState(false);

    console.log(props)

    const history = useHistory();

    const routeChange = () => {
        history.push({ pathname: '/logIn', setSignedIn });
    }

    return (
        <div>
            <button className="btn btn-primary" onClick={routeChange}>Log In</button>

            {signedIn ? <LoggedIn /> : <LoggedOut />}

            <Home isLoggedIn={signedIn} />
        </div>
    )
}

export default SigninPage
