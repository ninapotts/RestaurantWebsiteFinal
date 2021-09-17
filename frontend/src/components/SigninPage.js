import React, { useState } from 'react'
import Home from './Home'
import { useHistory, Redirect, Link } from "react-router-dom";

const LoggedIn = (props) => {

    return (
        <div>
            <p>Welcome you are logged in</p>
            <h3>Hello {props.location}</h3>

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
    const [signedIn, setSignedIn] = useState(props.location.state.isSignedIn);

    const history = useHistory();

    return (
        <div>
            {/* <button className="btn btn-primary" onClick={routeChange}>Create Account</button> */}
            <Link className="btn btn-primary" to="/newUser">Create Account</Link>

            {signedIn ? <LoggedIn userName={props.location.state.userName} /> : <LoggedOut />}

            <Home isLoggedIn={signedIn} />
        </div>
    )
}

export default SigninPage
