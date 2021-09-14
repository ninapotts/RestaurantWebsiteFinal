import React from 'react'
import { Link } from "react-router-dom";
import Home from './Home';

const LoggedIn = (props) => {

    const loggedInView = () => {
        const name = props.location.state.user.username;

        console.log(name)
        if (props.location.state.user.username == "world") {
            return <Home isSignedIn={true} userType="admin" />

        } else {
            return <Home isSignedIn={true} userType="user" />
        }
    }


    return (
        <div>
            {console.log(props)}
            <Link to='/' className="btn btn-outline-primary">Log Out</Link>
            {loggedInView()}

        </div>
    )
}

export default LoggedIn
